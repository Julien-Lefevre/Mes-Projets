open Tprop
open Hoare_triple

type conclusion =
  | Prop of tprop
  | Hoare of hoare_triple

type goal = {
  context : (string * tprop) list;
  conclusion : conclusion;
}

(* Fonctions d'affichage *)
val print_list : (string * tprop) list -> unit
val print_goal : goal -> unit
val printgoals : goal list -> unit
