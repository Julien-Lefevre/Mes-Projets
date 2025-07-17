#load "aexp.cmo";;
open Aexp;;
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
(* voir dan le fichier "tprop_exemples.ml" *)

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
(* voir dan le fichier "tprop_exemples.ml" *)

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
(* voir dan le fichier "tprop_exemples.ml" *)

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
(* voir dan le fichier "tprop_exemples.ml" *)
