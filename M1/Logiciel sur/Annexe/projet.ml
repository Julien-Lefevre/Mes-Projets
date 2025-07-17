(* Partie 1 : Un interpréteur pour un language impératif *)

(* 1.1 Les expressions arithmétiques *)
(* 1.1.1 Syntaxe abstraite *)

(* Question 1 : *)

type aexp =
  | Cst of int
  | Var of string
  | Add of aexp * aexp
  | Sub of aexp * aexp
  | Mult of aexp * aexp
;;

(* Question 2 : *)

let q2_1 : aexp = Cst 2 ;;

let q2_2 : aexp = Add ( Cst 2, Cst 3 );;
let q2_3 : aexp = Sub (Cst 2, Cst 5 );;
let q2_4 : aexp = Mult (Cst 3, Cst 6);;

let q2_5 : aexp = Add (Cst 2, Var "x");;
let q2_6 : aexp = Mult(Cst 4 , Var "y");;
let q2_7 : aexp = Mult(Mult(Cst 3, Var "x"), Var "x");;
let q2_8 : aexp = Add( Mult(Cst 5, Var "x"), Mult(Cst 7, Var "y"));;
let q2_9 : aexp = Add(Mult (Cst 6, Var "x") ,  Mult(Mult(Cst 5, Var "y"), Var "x"));;

(* Question 3 : *)

(* Question 3.1 : *)

let rec aexp_to_string(expr : aexp) : string =
  match expr with
  | Cst x -> string_of_int x
  | Var x -> x
  | Add (x,y) -> "(" ^ (aexp_to_string x) ^ "+" ^ (aexp_to_string y) ^ ")"
  | Sub (x,y) -> "(" ^ (aexp_to_string x) ^ "-" ^ (aexp_to_string y) ^ ")"
  | Mult (x,y) -> "(" ^ (aexp_to_string x) ^ "*" ^ (aexp_to_string y) ^ ")"
;;

(* Question 3.2 : *)

aexp_to_string q2_1 ;;

aexp_to_string q2_2 ;;
aexp_to_string q2_3 ;;
aexp_to_string q2_4 ;;

aexp_to_string q2_5 ;;
aexp_to_string q2_6 ;;
aexp_to_string q2_7 ;;
aexp_to_string q2_8 ;;
aexp_to_string q2_9 ;;

(* 1.1.2 Interprétation *)

(* Question 4 : *)

type valuation = (string * int) list;;

(* Question 5 : *)

let rec ainterp ( expr: aexp) (value : valuation) : int =
  match expr with
  | Cst x -> x
  | Var x -> (try (List.assoc x value) with Not_found -> failwith("erreur variable x non définie"))
  | Add (x,y) -> (ainterp x value) + (ainterp y value)
  | Sub (x,y) -> (ainterp x value) - (ainterp y value )
  | Mult (x,y) -> (ainterp x value) * (ainterp y value)
;;


(* Question 6 : *)
let value_list = [("x", 5) ; ("y", 9)];;

ainterp q2_1 value_list;;

ainterp q2_2 value_list;;
ainterp q2_3 value_list;;
ainterp q2_4 value_list;;

ainterp q2_5 value_list;;
ainterp q2_6 value_list;;
ainterp q2_7 value_list;;
ainterp q2_8 value_list;;
ainterp q2_9 value_list;;

(* 1.1.3 Substitutions *)

(* Question 7 : *)

let rec asubst (name : string) (replace : aexp) (expr : aexp) : aexp =
  match expr with
  | Cst x -> Cst x
  | Var x -> if(x = name) then replace else Var x
  | Add (x, y) -> Add(asubst name replace x, asubst name replace y)
  | Sub (x, y) -> Sub(asubst name replace x, asubst name replace y)
  | Mult (x, y) -> Mult(asubst name replace x, asubst name replace y)
;;

(* Question 8 : *)

aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_1));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_2));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_3));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_4));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_5));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_6));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_7));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_8));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_9));;

(* 1.2 Les expressions booléennes *)
(* 1.2.1 Syntaxe abstraite *)

(* Question 1 : *)
type bexp =
  | True
  | False
  | And of bexp * bexp
  | Or of bexp * bexp
  | Not of bexp
  | Equal of aexp * aexp
  | Inf_Eq of aexp * aexp
;;

(* Question 2 : *)
let bexp_1 : bexp = True;;

let bexp_2 : bexp = And(True, False);;
let bexp_3 : bexp = Not(True);;
let bexp_4 : bexp = Or(True, False);;

let bexp_5 : bexp = Equal(Cst 2, Cst 4);;
let bexp_6 : bexp = Equal(
                        Add(Cst 3, Cst 5),
                        Mult(Cst 2, Cst 4)
                      );;
let bexp_7 : bexp = Equal(
                        Mult(Cst(2), Var "x"),
                        Add(Var "y", Cst 1)
                      );;

let bexp_8 : bexp = Inf_Eq(Cst 5, Cst 7);;
let bexp_9 : bexp = And(
                        Inf_Eq(
                            Add(Cst 8 , Cst 9),
                            Mult(Cst 4 , Cst 5)
                          ),
                        Inf_Eq(
                            Add(Cst 3 , Var "x"),
                            Mult(Cst 4, Var "y")
                          )
                      );;

(* Question 3 : *)
let rec bexp_to_string(expr : bexp) : string =
  match expr with
  | True -> "True"
  | False -> "False"
  | And(x, y) -> "(" ^ (bexp_to_string x) ^ " && " ^ (bexp_to_string y) ^ ")"
  | Or(x, y) -> "(" ^ (bexp_to_string x) ^ " || " ^ (bexp_to_string y) ^ ")"
  | Not x -> "!" ^ (bexp_to_string x) ^ ""
  | Equal(x, y) -> "(" ^ (aexp_to_string x) ^ " = " ^ (aexp_to_string y) ^ ")"
  | Inf_Eq(x, y) -> "(" ^ (aexp_to_string x) ^ " <= " ^ (aexp_to_string y) ^ ")"
;;

bexp_to_string bexp_1;;

bexp_to_string bexp_2;;
bexp_to_string bexp_3;;
bexp_to_string bexp_4;;

bexp_to_string bexp_5;;
bexp_to_string bexp_6;;
bexp_to_string bexp_7;;

bexp_to_string bexp_8;;
bexp_to_string bexp_9;;

(* 1.2.2 Interprétation *)

(* Question 4 : *)
let rec binterp (expr : bexp) (value : valuation) : bool =
  match expr with
  | True -> true
  | False -> false
  | And(x, y) -> (binterp x value) && (binterp y value)
  | Or(x, y) -> (binterp x value) || (binterp y value)
  | Not x -> not(binterp x value)
  | Equal(x, y) -> (ainterp x value) = (ainterp y value)
  | Inf_Eq(x, y) -> (ainterp x value) <= (ainterp y value)
;;

(* Question 5 : *)

let value_list2 : valuation = [("x", 7);("y", 3)];;

binterp bexp_1 value_list2;;

binterp bexp_2 value_list2;;
binterp bexp_3 value_list2;;
binterp bexp_4 value_list2;;

binterp bexp_5 value_list2;;
binterp bexp_6 value_list2;;
binterp bexp_7 value_list2;;

binterp bexp_8 value_list2;;
binterp bexp_9 value_list2;;

(* 1.3 Les commandes du language *)
(* 1.3.1 Syntaxe abstraite *)

(* Question 1 : *)
type prog =
  | Skip
  | Affect of string * aexp
  | Seq of prog * prog
  | If of bexp * prog * prog
  | Repeat of aexp * prog
;;

(* Question 2 : *)
let prog_1 : prog = Affect("y", Cst 7);;
let prog_2 : prog = Seq(
                        Affect("z", Add(Cst 3, Cst 4)),
                        Affect("x", Mult(Cst 2, Var "x")));;
let prog_3 : prog = Seq(
                        Affect("n", Cst 3),
                        If(
                            Inf_Eq(Var "n", Cst 4 ),
                            Affect("n", Add(Mult(Cst 2, Var "n"), Cst 3)),
                            Affect("n", Add(Var "n", Cst 1))
                      ));;
let prog_4 : prog = Repeat (Cst 10, Affect("x", Add(Var "x", Cst 1)));;

(* Question 3 : *)
let rec prog_to_string (p : prog) : string =
  match p with
  | Skip -> "skip"
  | Affect(var, exp) -> var ^ " := " ^ (aexp_to_string exp)
  | Seq(p1, p2) -> (prog_to_string p1) ^ "; " ^(prog_to_string p2)
  | If(b, p1, p2) -> "if " ^ (bexp_to_string b) ^ " then " ^ (prog_to_string p1) ^ " else " ^(prog_to_string p2)
  | Repeat(a, p) -> "repeat " ^ (aexp_to_string a) ^ " do " ^ (prog_to_string p) ^ " od"
;;

prog_to_string prog_1 ;;
prog_to_string prog_2 ;;
prog_to_string prog_3 ;;
prog_to_string prog_4 ;;

(* Question 4 : *)

let rec selfcompose (func : ('a -> 'a)) (n : int) : ('a -> 'a) =
  if(n = 0)
  then (fun n -> n)
  else fun x -> func((selfcompose func (n-1)) x)
;;

(* Question 5 : *)
let test_selfcompose : int = selfcompose (fun x -> x + 2) 10 0;;

(* Question 6 : *)
let rec exec (p : prog) (value : valuation) : valuation =
  match p with
  | Skip -> value
  | Affect(var, exp) -> (var, ainterp exp value) :: value
  | Seq(p1,p2) -> exec p2 (exec p1 value)
  | If(b, p1,  p2) -> if (binterp b value)
                      then exec p1 value
                      else exec p2 value
  | Repeat(a, p) -> let nbiter = ainterp a value in
                    selfcompose (exec p) nbiter value
;;

(* Question 7 : *)
let factoriel : prog =
  Seq (
      Affect ("out", Cst 1),
      Repeat (Var "in",
              Seq (
                  Affect ("out",
                          Mult (Var "out", Var "in")),
                  Affect ("in", Sub (Var "in", Cst 1))
                )
        )
    )
;;

exec factoriel [("in", 5)];;

let fibo : prog =
  Seq (Affect ("tempres", Cst 0),
       Seq (Affect ("res", Cst 1),
            Repeat (Var "x",
                    Seq (
                        Affect ("temp", Var "res"),
                        Seq (
                            Affect ("res", Add (Var "tempres", Var "res")),
                            Affect ("tempres", Var "temp")
                          )
                      )
              )
         )
    )
;;

exec fibo [("x", 7)];;c

(* 1.4 Triplets de Hoare et validité *)
(* 1.4.1 Syntaxe abstraite des formules de la logique des propositions *)

(* Question 1 : *)
type tprop =
  | True
  | False
  | And of tprop * tprop
  | Or of tprop * tprop
  | Impl of tprop * tprop
  | Not of tprop
  | Equal of aexp * aexp
  | Inf_Eq of aexp * aexp
;;

(* Question 2 : *)
let tprop_1 : tprop = True;;

let tprop_2 : tprop = And(True, False);;
let tprop_3 : tprop = Not(True);;
let tprop_4 : tprop = Or(True, False);;
let tprop_5 : tprop = Impl(False, Or(True, False));;

let tprop_6 : tprop = Equal(Cst 2 , Cst 4);;
let tprop_7 : tprop = Equal(
                          Add(Cst 3 , Cst 5),
                          Mult(Cst 2, Cst 4 )
                        );;
let tprop_8 : tprop = Equal(
                          Mult(Cst 2, Var "x"),
                          Add(Var "y", Cst 1)
                        );;

let tprop_9 : tprop = Inf_Eq(
                          Add(Cst 3, Var "x"),
                          Mult(Cst 4, Var "y")
                        );;
let tprop_10 : tprop = And(
                           Inf_Eq(Cst 5, Cst 7),
                           Inf_Eq(
                               Add(Cst 8 , Cst 9),
                               Mult(Cst 4 , Cst 5)
                             )
                         );;

let tprop_11 : tprop = Impl(
                           Equal(Var "x", Cst 1),
                           Inf_Eq(Var "y", Cst 0)
                         );;

(* Question 3 : *)
let rec prop_to_string (prop : tprop) : string =
  match prop with
  | True -> "true"
  | False -> "false"
  | And(p1,p2) -> "(" ^ (prop_to_string p1) ^ " /\\ " ^ (prop_to_string p2) ^ ")"
  | Or(p1,p2) -> "(" ^ (prop_to_string p1) ^ " \\/ " ^ (prop_to_string p2) ^ ")"
  | Impl(p1,p2) -> "(" ^ (prop_to_string p1) ^ " -> " ^ (prop_to_string p2) ^ ")"
  | Not(p) -> "~(" ^ (prop_to_string p) ^ ")"
  | Equal(a1,a2) ->
     if(a1 = a2)
     then (aexp_to_string a1)
     else "(" ^ (aexp_to_string a1) ^ " = " ^ (aexp_to_string a2) ^ ")"
  | Inf_Eq(a1,a2) -> "(" ^ (aexp_to_string a1) ^ " <= " ^ (aexp_to_string a2) ^ ")"
;;

prop_to_string tprop_1 ;;
prop_to_string tprop_2 ;;
prop_to_string tprop_3 ;;
prop_to_string tprop_4 ;;
prop_to_string tprop_5 ;;
prop_to_string tprop_6 ;;
prop_to_string tprop_7 ;;
prop_to_string tprop_8 ;;
prop_to_string tprop_9 ;;
prop_to_string tprop_10 ;;
prop_to_string tprop_11 ;;

(* Question 4 : *)
let rec pinterp (prop : tprop) (value : valuation) : bool =
  match prop with
  | True -> true
  | False -> false
  | And(p1,p2) -> (pinterp p1 value) && (pinterp p2 value) 
  | Or(p1,p2) -> (pinterp p1 value) || (pinterp p2 value) 
  | Impl(p1,p2) -> (not (pinterp p1 value)) || (pinterp p2 value) 
  | Not(p) -> not (pinterp p value)
  | Equal(a1,a2) -> (ainterp a1 value) = (ainterp a2 value) 
  | Inf_Eq(a1,a2) -> (ainterp a1 value) <= (ainterp a2 value)
;;

(* Question 5 : *)
let valuate : valuation = [("x", 7);("y", 3)];;
pinterp tprop_1 valuate;;
pinterp tprop_2 valuate;;
pinterp tprop_3 valuate;;
pinterp tprop_4 valuate;;
pinterp tprop_5 valuate;;
pinterp tprop_6 valuate;;
pinterp tprop_7 valuate;;
pinterp tprop_8 valuate;;
pinterp tprop_9 valuate;;
pinterp tprop_10 valuate;;
pinterp tprop_11 valuate;;

(* Question 6 : *)
let rec psubst (name : string) (expr : aexp) (prop : tprop) : tprop =
  match prop with
  | True -> True
  | False -> False
  | And(p1,p2) -> And((psubst name expr p1),(psubst name expr p2))
  | Or(p1,p2) -> Or((psubst name expr p1),(psubst name expr p2))
  | Impl(p1,p2) -> Impl((psubst name expr p1),(psubst name expr p2))
  | Not(p) -> Not (psubst name expr p)
  | Equal(a1,a2) -> Equal((asubst name expr a1),(asubst name expr a2))
  | Inf_Eq(a1,a2) -> Inf_Eq((asubst name expr a1),(asubst name expr a2))
;;

(* Question 7 : *)
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_1));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_2));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_3));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_4));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_5));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_6));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_7));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_8));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_9));;

prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_10));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_11));;

(* 1.4.4 Les triplets de Hoare *)

(* Question 8 : *)
type hoare_triple = {pre : tprop; prog : prog; post : tprop};;

(* Question 9 : *)
let hoare_1 : hoare_triple = {pre = (Equal(Var "x", Cst 2)); prog = Skip; post = (Equal(Var "x", Cst 2))};;

let hoare_2 : hoare_triple = {pre = Equal(Var "x", Cst 2) ; prog = Affect("x",Cst 3) ; post = Equal(Var"x", Cst 3)};;
let hoare_3 : hoare_triple = {
    pre = True;
    prog = If(
               Inf_Eq(Var "x", Cst 0),
               Affect("r", Sub(Cst 0, Var "x")),
               Affect("r", Var "x");
             );
    post = Inf_Eq(Cst 0, Var "r")
  };;


let hoare_4 : hoare_triple = {pre = And(
                                        Equal(Var "in", Cst 5),
                                        Equal(Var "out", Cst 1));
                              prog = factoriel;
                              post = And(
                                        Equal(Var "in", Cst 0),
                                        Equal(Var "out", Cst 120));
                             };;

(* Question 10 : *)
let htvalid_test (h : hoare_triple) (value : valuation) : bool =
  if(pinterp h.pre value)
  then
    let valuation : valuation = (exec h.prog value) in
    pinterp h.post valuation
  else
    true
;;

let value : valuation = [("x", 2);("in", 5);("out", 1)];;
htvalid_test hoare_1 value;;
htvalid_test hoare_2 value;;
htvalid_test hoare_3 value;;
htvalid_test hoare_4 value;;

let hoare_to_string (h : hoare_triple) : string =
  "{" ^ prop_to_string(h.pre) ^ "} " ^ prog_to_string(h.prog) ^ "{" ^ prop_to_string(h.post) ^"}"
;;


(* Partie 2 : Un (mini) prouveur en logique de Hoare *)

(* 2.1 Les buts de preuves et le langage des tactiques *)
(* 2.1.1 Les buts de preuves *)

(* Question 1 : *)
type conclusion =
  | Prop of tprop
  | Hoare of hoare_triple
;;
type goal = {context : (string * tprop) list; conclusion :conclusion};;

(* Question 2 : *)
let goal_1 : goal = {
    context = [("H", (Impl(Or(Equal(Var "P", Var "P"), Equal(Var "Q", Var"Q")), Equal(Var "R", Var "R"))));("H2", Equal(Var "P", Var "P"))];
    conclusion = Prop(Or( Equal(Var "P", Var "P"),  Equal(Var "Q", Var "Q")))
  };;
 
let goal_2 : goal = {
    context = [];
    conclusion = Hoare({
                       pre = Equal(Var "x", Cst (-3));
                       prog = If(Inf_Eq(Var "x", Cst 0), Affect("x", Sub(Cst 0, Var "x")), Affect("x", Var "x"));
                       post = Equal(Var "x", Cst 3)
                   })
  };;

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


let print_list (l : (string * tprop) list) : unit =
  List.iter (fun (s, x) ->
    Printf.printf "%s : %s\n" s (prop_to_string x)
  ) l
;;

let print_goal (g : goal) : unit =
  print_list g.context;
  Printf.printf "========================\n";
  match g.conclusion with
  | Prop(x) -> Printf.printf "%s\n\n" (prop_to_string x)
  | Hoare(x) -> Printf.printf "%s\n\n" (hoare_to_string x)
;;


print_goal goal_1;;
print_goal goal_2;;


(* 2.1.2 La règle de déduction pour la boucle repeat *)

(* Question 4 : *)
(*
    ( x = y + i - 1)        i <= 10
    --------------------------------^I
    {( x = y + i - 1) /\ ( i <= 10)} x:= x+1 {( x = y + (i + 1) - 1)}
--------------------------------------------------------------------------- repeat(i)
{ x = y + 1 - 1} repeat 10 do x:= x+1 {( x = y + i - 1) /\ ( i = 10 + 1)}
-------------------------------------------------------------------------
            {x = y} repeat 10 do x:= x+1 od {x = y + 10}
 *)

(* Question 5 : *)

(* Code décoré *)
(*
{(r=0) /\ (n=1)}
{2r = 0}
{(1(1-1) = r2) /\ n = 1)}
repeat 5 do
  {( (i(i-1) = r2) /\ (n = i) ) /\ (i <= 5)}
  {((i+1) i = (r+n)2) /\ (n+1 = i+1)}
  r := r + n
  {((i+1) i = r2) /\ (n+1 = i+1)}
  n := n + 1
  {((i+1) i = r2) /\ (n = i+1)}
od
{((i(i-1) = r*2) /\ (n = i)) /\ (i = 5 + 1)}
{(r=15) /\ (n=6)}
 *)

(* Arbre de preuve *)
(*
      i(i-1) = r2       n = i
      --------------------------^I
      (i(i-1) = r2) /\ (n = i)         i = 5 + 1                (i+1) * i = r2       n+1 = i+1               (i+1) i = r2       n+1 = i+1                 (i+1) i = (r+n)2        n+1 = i+1
      ---------------------------------------------^I          ----------------------------------^I       ----------------------------------^I          --------------------------------------^I
      {((i(i-1) = r2) /\ (n = i)) /\ (i = 5 + 1)} r := r + n {((i+1) i = r2) /\ (n+1 = i+1)}                 {((i+1) i = r2) /\ (n+1 = i+1)} n := n + 1 {((i+1) i = (r+n)2) /\ (n+1 = i+1)}
      ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                     {((i(i-1) = r2) /\ (n = i)) /\ (i = 5 + 1)} r := r + n; n := n + 1 {((i+1) i = (r+n)2) /\ (n+1 = i+1)}
                                  ---------------------------------------------------------------------------------------------------------------- repeat(i)
                                  {(1(1-1) = r2) /\ (n = 1)} repeat 5 do r := r + n; n := n + 1 {((i(i-1) = r*2) /\ (n = i)) /\ (i = 5 + 1)}
                                  ----------------------------------------------------------------------------------------------------------------
                                              {(r = 0) ∧ (n = 1)} repeat 5 do r := r + n; n := n + 1 od {(r = 15) ∧ (n = 6)}
*)

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

let rec printgoals_aux (gl : goal list) : unit =
  match gl with
  | x :: y ->
     (
       print_goal(x);
       printgoals_aux(y)
     )
  | [] -> Printf.printf ("_____________________________________\n");
;;

let printgoals (gl : goal list) : unit =
  match gl with
  |[] -> Printf.printf("La liste de preuve est vide, preuve terminée !")
  |_ -> printgoals_aux(gl)
;;

(* Question 3 : *)
let apply_tactic_test =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Prop(
                                                           Impl (
                                                               Impl (Or (Equal(Var "P",Var "P"),
                                                                         Equal(Var "Q",Var "Q")),
                                                                     Equal(Var "R",Var "R")),
                                                               And (
                                                                   Impl (Equal(Var "P",Var "P"),
                                                                         Equal(Var "R",Var "R")),
                                                                   Impl (Equal(Var "Q",Var "Q"),
                                                                         Equal(Var "R",Var "R"))
                                                                 )
                                                             )
                            )} in
    print_goal proof_goal;
    
    Printf.printf ("Impl_Intro : \n");
    let proof_list = apply_tactic Impl_Intro proof_goal in
    print_goal (List.hd proof_list);
    
    Printf.printf ("And_Intro : \n");
    let proof_list2 = apply_tactic And_Intro (List.hd proof_list) in
    printgoals proof_list2;
    
    Printf.printf ("Impl_Intro : \n");
    let proof_list3 = List.append (apply_tactic Impl_Intro (List.hd proof_list2))  (List.tl proof_list2) in
    printgoals proof_list3;

    Printf.printf ("Assume (Or(Equal(Var \"P\",Var \"P\"), Equal(Var \"Q\",Var \"Q\"))) : \n");
    let proof_list4 = List.append
                        (apply_tactic
                           (Assume (Or(Equal(Var "P",Var "P"), Equal(Var "Q",Var "Q"))))
                           (List.hd proof_list3))
                        (List.tl proof_list3) in
    printgoals proof_list4;

    Printf.printf ("Impl_Elim : \n");
    let proof_list5 = List.append (
                          apply_tactic
                            (Impl_Elim ("H1", "H3")) (List.hd proof_list4))
                        (List.tl proof_list4) in
    printgoals proof_list5;
    
    Printf.printf ("Exact : \n");
    let proof_list6 = List.append (
                          apply_tactic
                            (Exact "H4") (List.hd proof_list5))
                        (List.tl proof_list5) in
    printgoals proof_list6;

    Printf.printf ("Or_Intro_1 : \n");
    let proof_list7 = List.append (
                          apply_tactic
                            (Or_Intro_1) (List.hd proof_list6))
                        (List.tl proof_list6) in
    printgoals proof_list7;

    Printf.printf ("Exact : \n");
    let proof_list8 = List.append (
                          apply_tactic
                            (Exact "H2") (List.hd proof_list7))
                        (List.tl proof_list7) in
    printgoals proof_list8;

    Printf.printf ("Impl_Intro : \n");
    let proof_list9 = List.append (
                          apply_tactic
                            (Impl_Intro) (List.hd proof_list8))
                        (List.tl proof_list8) in
    printgoals proof_list9;

    Printf.printf ("Assume (Or(Equal(Var \"P\",Var \"P\"), Equal(Var \"Q\",Var \"Q\"))) : \n");
    let proof_list10 = List.append
                        (apply_tactic
                           (Assume (Or(Equal(Var "P",Var "P"), Equal(Var "Q",Var "Q"))))
                           (List.hd proof_list9))
                        (List.tl proof_list9) in
    printgoals proof_list10;

    Printf.printf ("Impl_Elim : \n");
    let proof_list11 = List.append (
                          apply_tactic
                            (Impl_Elim ("H1", "H6")) (List.hd proof_list10))
                        (List.tl proof_list10) in
    printgoals proof_list11;

    Printf.printf ("Exact : \n");
    let proof_list12 = List.append (
                          apply_tactic
                            (Exact "H7") (List.hd proof_list11))
                        (List.tl proof_list11) in
    printgoals proof_list12;

    Printf.printf ("Or_Intro_2 : \n");
    let proof_list13 = List.append (
                          apply_tactic
                            (Or_Intro_2) (List.hd proof_list12))
                        (List.tl proof_list12) in
    printgoals proof_list13;

    Printf.printf ("Exact : \n");
    let proof_list14 = List.append (
                          apply_tactic
                            (Exact "H5") (List.hd proof_list13))
                        (List.tl proof_list13) in
    printgoals proof_list14;
  )
;;

(* Question 4 : *)

let apply_tactic_hoare_1 =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = Equal(Var "x", Cst 2);
                                                            prog = Skip;
                                                            post = Equal(Var "x", Cst 2)}
                                                         )
                            } in
    print_goal proof_goal;

    Printf.printf ("HSkip : \n");
    let proof_list = apply_tactic HSkip proof_goal in
    printgoals proof_list;
  )
;;

let apply_tactic_hoare_2 =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = Inf_Eq(Add(Var "y", Cst 1), Cst 4);
                                                            prog = Affect("y", Add(Var "y", Cst 1));
                                                            post = Inf_Eq(Var "y", Cst 4)}
                                                         )
                            } in
    print_goal proof_goal;
    Printf.printf ("HAssign : \n");
    let proof_list = apply_tactic HAssign proof_goal in
    printgoals proof_list;
  )
;;

let apply_tactic_hoare_3 =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = Equal(Var "y", Cst 5);
                                                            prog = Affect("x", Add(Var "y", Cst 1));
                                                            post = Equal(Var "x", Cst 6)}
                                                         )
                            } in
    printgoals [proof_goal];
    
    Printf.printf ("HCons(Equal(Add(Var \"y\", Cst 1), Cst 6) : \n");
    let proof_list = apply_tactic (HCons(Equal(Add(Var "y", Cst 1), Cst 6),Equal(Var "x", Cst 6)))  proof_goal in
    printgoals proof_list;

    Printf.printf("Impl_Intro : \n");
    let proof_list2 = List.append (apply_tactic Impl_Intro (List.hd proof_list)) (List.tl proof_list) in
    printgoals proof_list2;

    Printf.printf("Admit : \n");
    let proof_list3 = List.append (apply_tactic Admit (List.hd proof_list2)) (List.tl proof_list2) in
    printgoals proof_list3;

     Printf.printf("HAssign : \n");
    let proof_list4 = List.append (apply_tactic HAssign (List.hd proof_list3)) (List.tl proof_list3) in
    printgoals proof_list4;

     Printf.printf("Impl_Intro : \n");
    let proof_list5 = List.append (apply_tactic Impl_Intro (List.hd proof_list4)) (List.tl proof_list4) in
    printgoals proof_list5;

     Printf.printf("Exact H2 : \n");
    let proof_list6 = List.append (apply_tactic (Exact("H2")) (List.hd proof_list5)) (List.tl proof_list5) in
    printgoals proof_list6;
  )
;;

(* Nous n'avons pas réussi à faire les preuves des deux parties suivantes *)
(*
let apply_tactic_hoare_4 =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {
                                                             pre = True;
                                                             prog = Seq(
                                                                        Affect("z", Var "x"),
                                                                        Seq(
                                                                            Affect("z",
                                                                                   Add(Var "z", Var "y")),
                                                                            Affect("u", Var "z")));
                                                             post = Equal(Var "u",
                                                                          Add(Var "x" , Var "y"))}
                                                         )
                            } in
    print_goal proof_goal;

    Printf.printf ("HSEq : \n");
    let proof_list = apply_tactic (HSEq(Equal(Var "z", Var "x"))) proof_goal in
    printgoals proof_list;

    Printf.printf ("HCons : \n");
    let proof_list1 = List.append (apply_tactic (HCons(Equal(Var "x", Var "x"), Equal(Var "z", Var "x"))) (List.hd proof_list)) (List.tl proof_list) in
    printgoals proof_list1;

    Printf.printf ("Impl_Intro : \n");
    let proof_list2 = List.append (apply_tactic Impl_Intro (List.hd proof_list1)) (List.tl proof_list1) in
    printgoals proof_list2;

    Printf.printf ("Admit : \n");
    let proof_list3 = List.append (apply_tactic Admit (List.hd proof_list2)) (List.tl proof_list2) in
    printgoals proof_list3;

   Printf.printf ("HAssign : \n");
    let proof_list4 = List.append (apply_tactic HAssign (List.hd proof_list3)) (List.tl proof_list3) in
    printgoals proof_list4;

    Printf.printf ("Impl_Intro : \n");
     let proof_list5 = List.append (apply_tactic Impl_Intro (List.hd proof_list4)) (List.tl proof_list4) in
     printgoals proof_list5;

     Printf.printf ("Exact H2 : \n");
     let proof_list6 = List.append (apply_tactic (Exact "H2") (List.hd proof_list5)) (List.tl proof_list5) in
     printgoals proof_list6;

      Printf.printf ("HSEq : \n");
    let proof_list7 = List.append (apply_tactic (HSEq(Equal(Var "z", Add(Var "x", Var "y")))) (List.hd proof_list6)) (List.tl proof_list6) in
    printgoals proof_list7;

    Printf.printf ("HAssign : \n");
    let proof_list8 = List.append (apply_tactic HAssign (List.hd proof_list7)) (List.tl proof_list7) in
    printgoals proof_list8;
  )
;;
*)


(*
let apply_tactic_hoare_5 =
  (
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = True;
                                                            prog = If(Inf_Eq(Var "v", Cst 0), Affect ("r", Sub(Cst 0,Var "v")), Affect("r", Var "v")) ;
                                                            post = Inf_Eq(Cst 0, Var "r")}
                                                         )
                            } in
    print_goal proof_goal;

    Printf.printf ("HSkip : \n");
    let proof_list = apply_tactic HSkip proof_goal in
    printgoals proof_list;
  )
;;
 *)

(*
let apply_tactic_hoare_6 =
  (
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = Equal(Var "x", Var "y");
                                                            prog = Repeat(Cst 10, Affect("x", Add(Var "x", Cst 1)));
                                                            post = Inf_Eq(Var "x", Add(Var "y", Cst 10))
                                                           }
                                                         )
                            } in
    print_goal proof_goal;

    Printf.printf ("HSkip : \n");
    let proof_list = apply_tactic HSkip proof_goal in
    printgoals proof_list;
  )
;;
 *)

(* Question 5 : *)

htvalid_test (
    {pre = Equal(Var "x", Cst 2);
     prog = Skip;
     post = Equal(Var "x", Cst 2)})
  [("x", 2)];;

htvalid_test (
    {pre = Inf_Eq(Add(Var "y", Cst 1), Cst 4);
     prog = Affect("y", Add(Var "y", Cst 1));
     post = Inf_Eq(Var "y", Cst 4)})
  [("y", 0)];;

htvalid_test (
    {pre = Equal(Var "y", Cst 5);
     prog = Affect("x", Add(Var "y", Cst 1));
     post = Equal(Var "x", Cst 6)})
  [("y", 5)];;

htvalid_test (
    {pre = True;
     prog = Seq(
                Affect("z", Var "x"),
                Seq(
                    Affect("z",
                           Add(Var "z", Var "y")),
                    Affect("u", Var "z")));
     post = Equal(Var "u",
                  Add(Var "x" , Var "y"))})
  [("x", 5); ("y", 6)];;

htvalid_test (
    {pre = True;
     prog = If(Inf_Eq(Var "v", Cst 0), Affect ("r", Sub(Cst 0,Var "v")), Affect("r", Var "v")) ;
     post = Inf_Eq(Cst 0, Var "r")})
  [("v", 5)];;

htvalid_test (
    {pre = Equal(Var "x", Var "y");
     prog = Repeat(Cst 10, Affect("x", Add(Var "x", Cst 1)));
     post = Inf_Eq(Var "x", Add(Var "y", Cst 10))})
  [("x", 5);("y", 5)];;
