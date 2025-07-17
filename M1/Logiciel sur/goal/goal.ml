#load "tprop.cmo";;
open Tprop;;
#load "hoare_triple.cmo";;
open Hoare_triple;;
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
(* voir fichier "goal_exemples.ml" *)

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


(* fonctions supplémentaires pour afficher une liste de goal *)

(* fonction auxiliaire *)
let rec printgoals_aux (gl : goal list) : unit =
  match gl with
  | x :: y ->
     (
       print_goal(x);
       printgoals_aux(y)
     )
  | [] -> Printf.printf ("_____________________________________\n");
;;

(* fonction principale, affiche le contenu de la liste ou un message indiquant que la preuve est terminée car la liste est vide *)
let printgoals (gl : goal list) : unit =
  match gl with
  |[] -> Printf.printf("La liste de preuve est vide, preuve terminée !")
  |_ -> printgoals_aux(gl)
;;
