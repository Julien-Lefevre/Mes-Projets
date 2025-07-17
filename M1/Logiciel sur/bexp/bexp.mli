open Aexp

(* Syntaxe abstraite des expressions booléennes *)

type bexp =
  | True
  | False
  | And of bexp * bexp
  | Or of bexp * bexp
  | Not of bexp
  | Equal of aexp * aexp
  | Inf_Eq of aexp * aexp

(* Conversion en chaîne de caractères *)

val bexp_to_string : bexp -> string

(* Interprétation d'une expression booléenne *)

val binterp : bexp -> valuation -> bool
