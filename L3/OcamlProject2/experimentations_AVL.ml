#load "btreeS.cmo";;
open BtreeS;;
#load "bst.cmo";;
open Bst;;
#load "avl.cmo" ;;
open Avl;;


(* Exercice 2. Expérimentations avec les arbres AVL *)

(* Question 1 *)


let avl_rnd_create(nbElt, borne : int * int) : 'a t_btree =
  let t : 'a t_btree ref = ref (bt_empty()) in
  let rand: int ref = ref 0 in
  while (bt_size(!t) < nbElt) do
    rand := Random.int(borne);
    t := avl_insert(!t, !rand)
  done;
  !t
;;



let rec cpx_avl_seek_aux(t, x, cpx : 'a t_btree * 'b * int ) : int =
  if bt_isempty(t)
  then cpx
  else (
    let (value, balance)  = bt_root(t) in
    if value = x
    then cpx
    else
      if value < x
      then cpx_avl_seek_aux(bt_subright(t), x, cpx + 1)
      else cpx_avl_seek_aux(bt_subleft(t), x, cpx + 1)
)
;;

let cpx_avl_seek(t, x : 'a t_btree * 'b) : int = 
  if bt_isempty(t)
  then failwith("error bst_seek : tree is empty")
  else cpx_avl_seek_aux(t, x, 0)
;;


let rec cpx_avl_insert_aux(t, value, cpx : 'a t_btree * 'b * int) : int =
  if bt_isempty(t)
  then cpx
  else
    let (r, imb) : 'b * int = bt_root(t) in
    if r = value
    then cpx
    else
      if r < value
      then cpx_avl_insert_aux(bt_subright(t), value, cpx + 1)
      else cpx_avl_insert_aux(bt_subleft(t), value, cpx + 1)
;;

let cpx_avl_insert(t, value : 'a t_btree * 'b) : int =
  if bt_isempty(t)
  then 0
  else
    let cpx : int ref = ref 0 in
    cpx := cpx_avl_insert_aux(t, value, !cpx);
    (*rebalance(imbalanceUpdate(!tree)) *)
    !cpx
;;


let rec cpx_avl_delete_aux(t, x, cpx : 'a t_btree * 'b * int) : int =
  if bt_isempty(t)
  then cpx
  else
    let (value,balance) = bt_root(t)
    in
    if value = x
    then
      let (l,r) : 'a t_btree * 'a t_btree = (bt_subleft(t), bt_subright(t)) in
      if bt_isempty(r)
      then
        if bt_isempty(l)
        then cpx
        else cpx
      else 
       if bt_isempty(l)
       then cpx
       else cpx
    else
       if value < x
       then cpx_avl_delete_aux(bt_subright(t), x, cpx + 1)
       else cpx_avl_delete_aux(bt_subleft(t), x, cpx + 1)
 ;;

 let cpx_avl_delete(t, x : 'a t_btree * 'b ) : int =
   if bt_isempty(t)
   then failwith("erreur cpx_avl_delete : tree is empty or value doesn't exits in the tree")
   else cpx_avl_delete_aux(t, x, 0)
    (*let tree : 'a t_btree ref = ref t in
    let final_tree : 'a t_btree ref = ref (bt_empty()) in
    tree := cpx_avl_delete_aux(!tree, x);
    while (!tree <> !final_tree) do
      final_tree := !tree;
      tree := rebalance(imbalanceUpdate(!tree))
    done;
    !tree*)
;;



let randTree : 'a t_btree ref = ref (bt_empty()) in
let borne : int = 1000 in
for i = 0 to 12 do
  randTree := avl_rnd_create(5, borne); 
  Printf.printf "complexité avl_seek, arbre de taille 5 = %d\n" (cpx_avl_seek(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_insert, arbre de taille 5 = %d\n" (cpx_avl_insert(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_delete, arbre de taille 5 = %d\n" (cpx_avl_delete(!randTree, Random.int(borne)));
  randTree := avl_rnd_create(10, borne); 
  Printf.printf "complexité avl_seek, arbre de taille 10 = %d\n" (cpx_avl_seek(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_insert, arbre de taille 10 = %d\n" (cpx_avl_insert(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_delete, arbre de taille 10 = %d\n" (cpx_avl_delete(!randTree, Random.int(borne)));
  randTree := avl_rnd_create(20, borne); 
  Printf.printf "complexité avl_seek, arbre de taille 20 = %d\n" (cpx_avl_seek(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_insert, arbre de taille 20 = %d\n" (cpx_avl_insert(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_delete, arbre de taille 20 = %d\n" (cpx_avl_delete(!randTree, Random.int(borne)));
  randTree := avl_rnd_create(50, borne);
  Printf.printf "complexité avl_insert, arbre de taille 50 = %d\n" (cpx_avl_insert(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_seek, arbre de taille 50 = %d\n" (cpx_avl_seek(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_delete, arbre de taille 50 = %d\n" (cpx_avl_delete(!randTree, Random.int(borne)));
  randTree := avl_rnd_create(100, borne);
  Printf.printf "complexité avl_seek, arbre de taille 100 = %d\n" (cpx_avl_seek(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_insert, arbre de taille 100 = %d\n" (cpx_avl_insert(!randTree, Random.int(borne)));
  Printf.printf "complexité avl_delete, arbre de taille 100 = %d\n" (cpx_avl_delete(!randTree, Random.int(borne)))
done
;;






(* QUESTION 2*)

let randList2(sizel: int) : 'a list =
let l : 'a list ref = ref [] in
let rand : int ref = ref 0 in
  for i = 0 to sizel-1 do
    rand := !rand + Random.int(100) + 1;
    l := !l @ [!rand]
  done;
  !l
;;


let listRandomSize2(nbList, listSizeMax : int * int) : 'a list =
  let mylist : 'a list ref = ref [] in
  for k = 1 to nbList do
    let newlist : 'a list = randList(listSizeMax) in
    mylist := !mylist @ newlist;
  done;
  !mylist 
;;

let listIncreasingSize2(nbList : int) : 'a list =
  let mylist : 'a list ref = ref [] in
  for k = 1 to nbList do
    let newlist : 'a list = randList(k) in
    mylist := !mylist @ newlist;
  done;
  !mylist 
;;

let listDecreasingSize2(nbList : int) : 'a list =
  let mylist : 'a list ref = ref [] in
  for k = 0 to nbList - 1 do
    let newlist : 'a list = randList(nbList - k) in
    mylist := !mylist @ newlist;
  done;
  !mylist 
;;


let listFixedSize2(nbList, listSize : int * int) : 'a list =
  let mylist : 'a list ref = ref [] in
  for k = 1 to nbList do
    let newlist : 'a list = randList(listSize) in
    mylist := !mylist @ newlist;
  done;
  !mylist 
;;


let listToAVL(l : 'a list) : 'a t_btree =
  let t : 'a t_btree ref = ref (bt_empty()) in
  let thelist : 'a list ref = ref l in
  while (!thelist <> []) do
    t := avl_insert(!t, List.hd(!thelist));
    thelist := List.tl(!thelist);
  done;
  !t
;;





