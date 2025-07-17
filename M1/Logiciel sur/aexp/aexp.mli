(* aexp.mli *)

(* Syntaxe abstraite *)

type aexp =
  | Cst of int
  | Var of string
  | Add of aexp * aexp
  | Sub of aexp * aexp
  | Mult of aexp * aexp

val aexp_to_string : aexp -> string

(* Interprétation *)

type valuation = (string * int) list

val ainterp : aexp -> valuation -> int

(* Substitutions *)

val asubst : string -> aexp -> aexp -> aexp
