(** type for binary trees *)
type 'a t_btree

(** create empty tree *)
val bt_empty : unit -> 'a t_btree

(** create root and sons *)
val bt_rooting : 'a * 'a t_btree * 'a t_btree -> 'a t_btree

(** check if root is empty or not *)
val bt_isempty : 'a t_btree -> bool

(** return the value of root *)
val bt_root : 'a t_btree -> 'a

(** return the root's left son tree *)
val bt_subleft : 'a t_btree -> 'a t_btree

(** return the root's right son tree *)
val bt_subright : 'a t_btree -> 'a t_btree
