open BtreeS ;;
open Bst ;;


(* EXERCICE 2 : Implantation d'un module AVL *)

(* QUESTION 1 *)

let left_rotate (t : 'a t_btree) : 'a t_btree =
  
  let new_t : 'a t_btree ref = ref (bt_empty()) in
  new_t := bt_rooting(bt_root(bt_subright(t)), 
                      bt_rooting(bt_root(t), bt_subleft(t), bt_subleft(bt_subright(t))), 
                      bt_subright(bt_subright(t)));
  !new_t
;;

let right_rotate (t : 'a t_btree) : 'a t_btree =
  let new_t : 'a t_btree ref = ref (bt_empty()) in
  new_t := bt_rooting(bt_root(bt_subleft(t)),
                               bt_subleft(bt_subleft(t)),
                               bt_rooting(bt_root(t), bt_subright(bt_subleft(t)), bt_subright(t)));
  !new_t
;;


let right_left_rotate (t : 'a t_btree) : 'a t_btree =
  let new_t : 'a t_btree ref = ref (bt_empty()) in
  new_t := bt_rooting(bt_root(t), bt_subleft(t), right_rotate(bt_subright(t)));
  new_t := left_rotate(!new_t);
  !new_t
;;



let left_right_rotate (t : 'a t_btree) : 'a t_btree =
  let new_t : 'a t_btree ref = ref (bt_empty()) in
  new_t := bt_rooting(bt_root(t), left_rotate(bt_subleft(t)), bt_subright(t));
  new_t := right_rotate(!new_t);
  !new_t
;;


(*-----------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------*)


(* QUESTION 2 *)

let rec getHeight(t : 'a t_btree) : int =
  if(bt_isempty(t))
  then 0
  else 1 + max (getHeight(bt_subleft(t))) (getHeight(bt_subright(t)))
;;

let imbalance2(t: 'a t_btree) : int  =
  if bt_isempty(t)
  then 0
  else getHeight(bt_subleft(t)) - getHeight(bt_subright(t))
;;

let rec imbalanceUpdate(t: ('a * int) t_btree) : ('a * int) t_btree =
  if bt_isempty(t)
  then bt_empty()
  else 
    let (r, imb) = bt_root(t) in
    let newImb : int = imbalance2(t) in
    bt_rooting((r, newImb), imbalanceUpdate(bt_subleft(t)), imbalanceUpdate(bt_subright(t)))
;;

let rec rebalance_aux(t : ('a * int) t_btree) : ('a * int) t_btree =
  let (value, balance) = bt_root(t) in
  let new_t : ('a * int) t_btree ref = ref (bt_rooting(bt_root(t), bt_subleft(t), bt_subright(t))) in
  if (balance = 2)
  then (
      let (value_subleft, balance_subleft) : ('a * int) = bt_root(bt_subleft(t)) in
      if (balance_subleft = -1)
      then new_t := left_right_rotate(!new_t)
      else 
        if (balance_subleft = 1 || balance_subleft = 0)
        then new_t := right_rotate(!new_t)
        else new_t := bt_rooting(bt_root(!new_t), rebalance_aux(bt_subleft(!new_t)), bt_subright(!new_t))
  )
  else (
    let (value_subright, balance_subright): 'a * int = bt_root(bt_subright(t)) in
    if (balance_subright = -1 || balance_subright = 0)
    then new_t := left_rotate(!new_t)
    else 
      if (balance_subright = 1)
      then new_t := right_left_rotate(!new_t)
      else new_t := bt_rooting(bt_root(!new_t), bt_subleft(!new_t), rebalance_aux(bt_subright(!new_t)))
    );
  !new_t
;;

let rec rebalance(t: ('a * int) t_btree) : ('a * int) t_btree =
  if bt_isempty(t)
  then bt_empty()
  else let (value, balance) = bt_root(t) in
       let tree : ('a * int) t_btree ref = ref t in
       if (balance = -1 || balance = 0 || balance = 1)
       then tree := bt_rooting(bt_root(!tree), rebalance(bt_subleft(!tree)), rebalance(bt_subright(!tree)))
       else tree := rebalance_aux(!tree);
  !tree
;;

let rec printavl(t : (int * int) t_btree) : unit =
  if bt_isempty(t)
  then Printf.printf "EMPTY\n"
  else
    (
      let (x,y) : int*int = bt_root(t) in
      Printf.printf " %d , %d\n%!" x y ;
      Printf.printf "gauche";
      printavl(bt_subleft(t));
      Printf.printf "droite";
      printavl(bt_subright(t));
    )
;;



(*-----------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------*)


(* QUESTION 3 *)



let rec avl_insert_aux(t, value : ('a*int) t_btree * 'a) : ('a*int) t_btree =
  if bt_isempty(t)
  then bt_rooting((value, 0), bt_empty(), bt_empty())
  else
    let (r, imb) : 'a * int = bt_root(t) in
    if r = value
    then t
    else
      if r < value
      then bt_rooting((r, imb), bt_subleft(t), avl_insert_aux(bt_subright(t), value))
      else bt_rooting((r, imb), avl_insert_aux(bt_subleft(t), value), bt_subright(t))
;;

let avl_insert(t, value : ('a*int) t_btree * 'a) : ('a*int) t_btree =
  if bt_isempty(t)
  then bt_rooting((value, 0), bt_empty(), bt_empty())
  else
    let tree : ('a*int) t_btree ref = ref t in
    tree := avl_insert_aux(!tree, value);
    rebalance(imbalanceUpdate(!tree))
;;


let rec avl_max(t : 'a t_btree) : 'a =
  if bt_isempty(bt_subright(t))
  then bt_root(t)
  else avl_max(bt_subright(t))
;;


let rec avl_rmMax(t : 'a t_btree) : 'a t_btree =
  if bt_isempty(t)
  then failwith("Error avl.ml : avl_rmMax : arbre vide")
  else
    let (l,r) : 'a t_btree * 'a t_btree = (bt_subleft(t), bt_subright(t)) in
    if bt_isempty(r)
    then l
    else bt_rooting(bt_root(t), l, avl_rmMax(r))
  ;;

let rec avl_delete_aux(t, x : ('a*int) t_btree * 'a ) : ('a*int) t_btree =
  if bt_isempty(t)
  then failwith("erreur avl_delete : tree is empty or value doesn't exits in the tree")
  else
    let (value,balance) = bt_root(t)
    in
    if value = x
    then
      let (l,r) : ('a*int) t_btree * ('a*int) t_btree = (bt_subleft(t), bt_subright(t)) in
      if bt_isempty(r)
      then
        if bt_isempty(l)
        then bt_empty()
        else l
      else 
       if bt_isempty(l)
       then r
       else bt_rooting(avl_max(bt_subleft(t)), avl_rmMax(l), bt_subright(t))
    else
       if value < x
       then bt_rooting(bt_root(t), bt_subleft(t), avl_delete_aux(bt_subright(t), x))
       else bt_rooting(bt_root(t), avl_delete_aux(bt_subleft(t), x), bt_subright(t))
 ;;

 let avl_delete(t, x : ('a*int) t_btree * 'a ) : ('a*int) t_btree =
   if bt_isempty(t)
   then failwith("erreur avl_delete : tree is empty or value doesn't exits in the tree")
   else 
    let tree : ('a*int) t_btree ref = ref t in
    let final_tree : ('a*int) t_btree ref = ref (bt_empty()) in
    tree := avl_delete_aux(!tree, x);
    while (!tree <> !final_tree) do
      final_tree := !tree;
      tree := rebalance(imbalanceUpdate(!tree))
    done;
    !tree

;;



(*-----------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------*)


(* QUESTION 4 *)


let rec avl_seek_aux(t, x : ('a*int) t_btree * 'a ) : bool =
  if bt_isempty(t)
  then false
  else
    let (value, balance) : 'a * int = bt_root(t) in
    if value = x
    then true
    else
      if value < x
      then avl_seek_aux(bt_subright(t), x)
      else avl_seek_aux(bt_subleft(t), x)
;;

let avl_seek(t, x : ('a*int) t_btree * 'a) : bool = 
  if bt_isempty(t)
  then failwith("error bst_seek : tree is empty")
  else avl_seek_aux(t, x)
;;

