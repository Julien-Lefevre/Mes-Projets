open Tprop
open Bexp
open Goal

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

  (* Logique de Hoare *)
  | HSkip
  | HAssign
  | HIf
  | HRepeat of string
  | HCons of tprop * tprop
  | HSEq of tprop

(* Convertit une expression booléenne en proposition logique *)
val bool2prop : bexp -> tprop

(* Fournit un nouvel identfiant *)
val fresh_ident : (unit -> string) * (unit -> unit)

(* Applique une tactique propositionnelle à un but *)
val apply_prop_tactic : tactic -> goal -> goal list

(* Applique une tactique de Hoare à un but *)
val apply_hoare_tactic : tactic -> goal -> goal list

(* Applique une tactique, logique propositionnelle ou de Hoare *)
val apply_tactic : tactic -> goal -> goal list
