open BtreeS;;

(*ABR = Arbre Binaire de Recherche*)

(* Fonction auxiliaire de recherche d'un élément dans un ABR *)
val bst_seek_aux : 'a t_btree * 'a -> bool

(* Fonction de recherche d'un élément dans un ABR *)
val bst_seek : 'a t_btree * 'a -> bool

(* Focntion d'insertion aux feuilles d'un élément dans un ABR *)
val bst_linsert : 'a t_btree * 'a -> 'a t_btree


(* Fonction renvoyant l'élément maximum d'un ABR *)
val bst_max : 'a t_btree -> 'a

(* Fonction de supression de l'élément maximal d'un ABR *)
val bst_rmMax: 'a t_btree -> 'a t_btree

(* Fonction de suppression d'un élément dans un ABR *)
val bst_delete : 'a t_btree * 'a -> 'a t_btree

(* Fonction affichant un ABR *)
val print : int t_btree -> unit

