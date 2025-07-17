open Aexp
open Bexp
(* Type des commandes du langage impératif *)
type prog =
  | Skip
  | Affect of string * aexp
  | Seq of prog * prog
  | If of bexp * prog * prog
  | Repeat of aexp * prog

(* Convertit un programme en chaîne de caractères *)
val prog_to_string : prog -> string

(* Applique une fonction à elle-même n fois *)
val selfcompose : ('a -> 'a) -> int -> ('a -> 'a)

(* Exécute un programme à partir d’une valuation initiale *)
val exec : prog -> valuation -> valuation
