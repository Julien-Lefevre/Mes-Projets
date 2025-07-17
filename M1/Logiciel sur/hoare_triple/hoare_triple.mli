open Aexp
open Tprop
open Prog
type hoare_triple = {
  pre : tprop;
  prog : prog;
  post : tprop;
}

(* Vérifie la validité d’un triplet de Hoare pour une valuation donnée *)
val htvalid_test : hoare_triple -> valuation -> bool

(* Affiche un triplet de Hoare sous forme de chaîne *)
val hoare_to_string : hoare_triple -> string
