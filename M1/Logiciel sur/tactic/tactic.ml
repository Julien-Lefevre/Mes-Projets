#load "aexp.cmo";;
open Aexp;;
#load "bexp.cmo";;
open Bexp;;
#load "tprop.cmo";;
open Tprop;;
#load "prog.cmo";;
open Prog;;
#load "hoare_triple.cmo";;
open Hoare_triple;;
#load "goal.cmo";;
open Goal;;
(* 2.1.3 Le language des tactiques *)
(* Question 6 : *)

type tactic =
  (* Logique des propositions *)
  | And_Intro
  | Or_Intro_1
  | Or_Intro_2
  | Impl_Intro
  | Not_Intro
  | And_Elim_1 of string 
  | And_Elim_2 of string 
  | Or_Elim of string
  | Impl_Elim of string * string
  | Not_Elim of string * string
  | Exact of string
  | Assume of tprop
  | Admit

  (* logique de Hoare *)
  | HSkip
  | HAssign
  | HIf
  | HRepeat of string
  | HCons of tprop * tprop
  | HSEq of tprop
;;

(* 2.2 Appliquer une tactique à un but *)
let rec bool2prop (b : bexp) : tprop =
  match b with
  | True -> True
  | False -> False
  | And(b1,b2) -> And(bool2prop b1, bool2prop b2)
  | Or(b1,b2) -> Or(bool2prop b1, bool2prop b2)
  | Not(b) -> Not(bool2prop b)
  | Equal(a1, a2) -> Equal(a1, a2)
  | Inf_Eq(a1,a2) -> Inf_Eq(a1,a2)
;;

let fresh_ident =
  let prefix = "H" and count = ref 0 in
  (* Fonction pour obtenir le prochain identifiant *)
  let get_ident () =
    count := !count + 1;
    prefix ^ (string_of_int (!count))
  in
  (* Fonction pour réinitialiser le compteur à zéro *)
  let reset () = count := 0 in
  (get_ident, reset)
;;

let (next_ident, reset_ident) = fresh_ident;;

let apply_prop_tactic (t : tactic) (g : goal) : goal list =
  match t with
  | And_Intro ->
     (match g.conclusion with
      | Prop(And(x1, x2)) ->
         [{context = g.context; conclusion = Prop(x1)};
          {context = g.context; conclusion = Prop(x2)}]
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Or_Intro_1 ->
     (match g.conclusion with
      | Prop(Or(x1, _)) -> [{context = g.context; conclusion = Prop(x1)}]
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Or_Intro_2 ->
     (match g.conclusion with
      | Prop(Or(_, x2)) -> [{context = g.context; conclusion = Prop(x2)}]
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Impl_Intro ->
     (match g.conclusion with
      | Prop(Impl(x1, x2)) ->
          let h = next_ident () in
          [{context = List.append g.context [(h, x1)]; conclusion = Prop(x2)}]
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Not_Intro ->
     (match g.conclusion with
      | Prop(Not(x)) ->
         let h = next_ident () in
         [{context = List.append g.context [(h, x)]; conclusion = Prop(False)}]
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | And_Elim_1(x) ->
     (match List.assoc x g.context with
      | And(p1, _) ->
         let h = next_ident () in
         [{context = List.append g.context [(h, p1)]; conclusion = g.conclusion}]
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | And_Elim_2(x) ->
     (match List.assoc x g.context with
      | And(_, p2) ->
         let h = next_ident () in
         [{context = List.append g.context [(h, p2)]; conclusion = g.conclusion}]
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Or_Elim(x) ->
     (match List.assoc x g.context with
      | Or(p1, p2) ->
         let h1 = next_ident () in
         let h2 = next_ident () in
         let context_without_x = List.remove_assoc x g.context in
         [
           {context = List.append context_without_x [(h1, p1)]; conclusion = g.conclusion};
           {context = List.append context_without_x [(h2, p2)]; conclusion = g.conclusion}
         ]
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Impl_Elim(h, h1) ->
     (match List.assoc h g.context with
      | Impl(p1, p2) ->
         let h1Value = List.assoc h1 g.context in
         if p1 = h1Value then
           let h2 = next_ident () in
           [{context = List.append g.context [(h2, p2)]; conclusion = g.conclusion}]
         else failwith("Impossible d'appliquer la tactique ici1")
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Not_Elim(h, h1) ->
     (match List.assoc h g.context with
      | Not(x) ->
         let h1Value = List.assoc h1 g.context in
         if x = h1Value then
           let h2 = next_ident () in
           [{context = List.append g.context [(h2, False)]; conclusion = g.conclusion}]
         else failwith("Impossible d'appliquer la tactique ici")
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Exact(x) ->
     let xValue = List.assoc x g.context in
     (match g.conclusion with
      | Prop(p) when p = xValue -> []
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | Assume(p) ->
     let h = next_ident () in
     [{context = List.append g.context [(h, p)]; conclusion = g.conclusion};
      {context = g.context; conclusion = Prop(p)}]

  | Admit ->
     (match g.conclusion with
      | Prop(Equal(_, _)) | Prop(Not(Equal(_, _))) -> []
      | Prop(True) -> []
      | _ -> failwith("Impossible d'appliquer la tactique ici"))

  | _ -> failwith("Tactique inconnue")
;;

let apply_hoare_tactic (t : tactic) (g : goal) : goal list =
  match t with
  | HSkip -> (match g.conclusion with
              | Hoare x -> (match x.prog with
                            | Skip -> []
                            | _ -> failwith "Impossible d'appliquer la tactique ici")
              | _ -> failwith "Impossible d'appliquer la tactique ici, il s'agit d'une proposition")

  | HAssign -> (match g.conclusion with
                | Hoare x -> (match x.prog with
                              | Affect(var, expr) ->
                                let expected_pre = psubst var expr x.post in
                                if x.pre = expected_pre then []
                                else failwith "Erreur : la précondition ne correspond pas à la postcondition"
                              | _ -> failwith "Impossible d'appliquer la tactique ici")
                | _ -> failwith "Impossible d'appliquer la tactique ici, il s'agit d'une proposition")

  | HIf -> (match g.conclusion with
            | Hoare x -> (match x.prog with
                          | If(b, p1, p2) ->
                            let newB = bool2prop b in
                            [ { context = g.context; conclusion = Hoare { pre = And(x.pre, newB); prog = p1; post = x.post } };
                              { context = g.context; conclusion = Hoare { pre = And(x.pre, Not(newB)); prog = p2; post = x.post } } ]
                          | _ -> failwith "Impossible d'appliquer la tactique ici")
            | _ -> failwith "Impossible d'appliquer la tactique ici, il s'agit d'une proposition")

  | HCons (p1, p2) -> (match g.conclusion with
                      | Hoare x -> [
                          { context = g.context; conclusion = Prop (Impl(p1, x.pre)) };
                          { context = g.context; conclusion = Hoare { pre = p1; prog = x.prog; post = p2 } };
                          { context = g.context; conclusion = Prop (Impl(x.post, p2)) }
                        ]
                      | _ -> failwith "Impossible d'appliquer la tactique ici, il s'agit d'une proposition")

  | HSEq p -> (match g.conclusion with
              | Hoare x -> (match x.prog with
                            | Seq(p1, p2) ->
                              [ { context = g.context; conclusion = Hoare { pre = x.pre; prog = p1; post = p } };
                                { context = g.context; conclusion = Hoare { pre = p; prog = p2; post = x.post } } ]
                            | _ -> failwith "Impossible d'appliquer la tactique ici")
              | _ -> failwith "Impossible d'appliquer la tactique ici, il s'agit d'une proposition")

  | HRepeat i -> (match g.conclusion with
                 | Hoare { pre = pre; prog = Repeat(e, c); post = post } ->
                   let invariant =
                     match post with
                     | And(inv, Equal(Var i', Add(e', Cst 1))) when i' = i && e' = e -> inv
                     | _ -> failwith "Postcondition mal formée : attendue sous la forme I ∧ i = e + 1"
                   in
                   let corps = {
                     context = g.context;
                     conclusion = Hoare {
                       pre = And(invariant, Inf_Eq(Var i, e));
                       prog = c;
                       post = psubst i (Add(Var i, Cst 1)) invariant
                     }
                   } in
                   let repeat_triplet = {
                     context = g.context;
                     conclusion = Hoare {
                       pre = psubst i (Cst 1) invariant;
                       prog = Repeat(e, c);
                       post = post
                     }
                   } in
                   [corps; repeat_triplet]
                 | _ -> failwith "HRepeat ne peut s'appliquer que sur un triplet de Hoare avec Repeat")

  | _ -> failwith "Tactique inconnue"
;;

let apply_tactic (t : tactic) (g : goal) : goal list =
  match g.conclusion with
  |Prop(_) -> apply_prop_tactic t g
  |Hoare(_) -> apply_hoare_tactic t g
;;

(* Question 3 : *)
(* voir dans le fichier "tactic_exemples.ml" *)

(* Question 4 : *)
(* voir dans le fichier "tactic_exemples.ml" *)

(* Question 5 : *)
(* voir dans le fichier "tactic_exemples.ml" *)
