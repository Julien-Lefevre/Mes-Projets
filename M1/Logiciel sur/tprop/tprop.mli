open Aexp
type tprop =
  | True
  | False
  | And of tprop * tprop
  | Or of tprop * tprop
  | Impl of tprop * tprop
  | Not of tprop
  | Equal of aexp * aexp
  | Inf_Eq of aexp * aexp

(* Convertit une formule propositionnelle en chaîne de caractères. *)
val prop_to_string : tprop -> string

(* Interprétation booléenne d'une formule propositionnelle dans une valuation donnée. *)
val pinterp : tprop -> valuation -> bool

(* Substitution dans une formule propositionnelle :
    remplace toutes les occurrences d'une variable par une expression. *)
val psubst : string -> aexp -> tprop -> tprop
