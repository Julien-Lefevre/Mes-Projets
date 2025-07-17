#load "aexp.cmo";;
open Aexp;;
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
(* voir dans le fichier "bexp_exemples.ml" *)

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
(* voir les tests d'exécution dans le fichier "bexp_exemples.ml" *)


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
(* voir dans le fichier "bexp_exemples.ml" *)
