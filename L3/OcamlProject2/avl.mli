open BtreeS ;;
open Bst ;;

(* AVL = Arbre Binaire de Recherche Equilibré *)

(* Fonction appliquant une rotation à droite *)
val right_rotate : 'a t_btree -> 'a t_btree

(* Fonction appliquant une rotation à gauche *)
val left_rotate : 'a t_btree -> 'a t_btree

(* Fonction appliquant une rotation droite-gauche *)
val right_left_rotate : 'a t_btree -> 'a t_btree

(* Fonction appliquant une rotation gauche-droite *)
val left_right_rotate : 'a t_btree -> 'a t_btree

val getHeight : 'a t_btree -> int

(* Focntion calculant l'équilibre d'un noeud *)
val imbalance2 : 'a t_btree -> int

(* Fonction parcourant un AVL afin d'en déterminer l'équilibre en chaque noeud *)
val imbalanceUpdate : ('a * int) t_btree -> ('a * int) t_btree

(* Fonction auxiliaire de rééquilibrage d'un AVL *)
val rebalance_aux : ('a * int) t_btree -> ('a * int) t_btree

(* Fonction de rééquilibrage d'un AVL *)
val rebalance : ('a * int) t_btree -> ('a * int) t_btree

(* Fonction d'affichage d'un AVL d'entiers *)
val printavl : (int * int) t_btree -> unit

(* Fonction auxiliaire d'insertion d'un élément dans un AVL *)
val avl_insert_aux : ('a*int) t_btree * 'a -> ('a*int) t_btree

(* Fonction d'insertion dans un AVL *)
val avl_insert : ('a*int) t_btree * 'a -> ('a*int) t_btree

(* Fonction retournant l'élément maximal d'un AVL *)
val avl_max : 'a t_btree -> 'a

(* Fonction supprimant l'élément maximal d'un AVL *)
val avl_rmMax : 'a t_btree -> 'a t_btree

(* Fonction auxiliaire de suppression d'un élément dans un AVL *)
val avl_delete_aux : ('a*int) t_btree * 'a  -> ('a*int) t_btree 

(* Fonction de suppression d'un élément dans un AVL *)
val avl_delete : ('a*int) t_btree * 'a  -> ('a*int) t_btree

(* Fonction auxiliaire de recherche d'un élément dans un AVL *)
val avl_seek_aux : ('a*int) t_btree * 'a  -> bool

(*Fonction de recherche d'un élément dans un AVL *)
val avl_seek : ('a*int) t_btree * 'a -> bool
