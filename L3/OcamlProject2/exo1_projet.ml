#load "btreeS.cmo";;
open BtreeS ;;
#load "bst.cmo" ;;
open Bst ;;
open Random ;;


(* QUESTION 1 *)
let randInit  = Random.self_init();;


let rec bt_size(t : 'a t_btree): int =
  if bt_isempty(t)
  then 0
  else
    1 + bt_size(bt_subleft(t)) + bt_size(bt_subright(t))
;;


let bst_rnd_create(nbElt, borne : int * int) : 'a t_btree =
  let t : 'a t_btree ref = ref (bt_empty()) in
  let rand: int ref = ref 0 in
  while (bt_size(!t) < nbElt) do
    rand := Random.int(borne);
    t := bst_linsert(!t, !rand)
  done;
  !t
;;


(*-----------------------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------------------*)


(* QUESTION 2 *)


let rec getHeight_bst(t : 'a t_btree) : int =
  if(bt_isempty(t))
  then 0
  else 1 + max (getHeight_bst(bt_subleft(t))) (getHeight_bst(bt_subright(t)))
;;


let rec imbalance_aux(t: 'a t_btree) : int =
  if bt_isempty(t)
  then 0
  else abs(getHeight_bst(bt_subleft(t)) - getHeight_bst(bt_subright(t))) + imbalance_aux(bt_subleft(t)) + imbalance_aux(bt_subright(t))
;;

let  imbalance(t: 'a t_btree) : float =
  if bt_isempty(t)
  then 0.0
  else (float_of_int(imbalance_aux(t))) /. (float_of_int(bt_size(t)))
;;

let avgImbalance(nbTree: int): unit =
  let avg : float ref = ref 0.0 in
  for i = 1 to nbTree do
    avg := !avg +. imbalance(bst_rnd_create(100,10000))
  done;
  let average : float = !avg/.(float_of_int(nbTree)) in
  Printf.printf "%f\n" average; 
;;


(* Expérience de la question 2 (vous retrouverez les résultats le fichier PDF joint) *)

for k = 1 to 25 do
  avgImbalance(400)
done
;;




(*-----------------------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------------------*)
(*-----------------------------------------------------------------------------------------------*)



(* QUESTION 3 *)

let randList(sizel: int) : 'a list =
let l : 'a list ref = ref [] in
let rand : int ref = ref 0 in
  for i = 0 to sizel-1 do
    rand := !rand + Random.int(100) + 1;
    l := !l @ [!rand]
  done;
  !l
;;


let listRandomSize(nbList, listSizeMax : int * int) : 'a list =
  let mylist : 'a list ref = ref [] in
  for k = 1 to nbList do
    let newlist : 'a list = randList(listSizeMax) in
    mylist := !mylist @ newlist;
  done;
  !mylist 
;;

let listIncreasingSize(nbList : int) : 'a list =
  let mylist : 'a list ref = ref [] in
  for k = 1 to nbList do
    let newlist : 'a list = randList(k) in
    mylist := !mylist @ newlist;
  done;
  !mylist 
;;

let listDecreasingSize(nbList : int) : 'a list =
  let mylist : 'a list ref = ref [] in
  for k = 0 to nbList - 1 do
    let newlist : 'a list = randList(nbList - k) in
    mylist := !mylist @ newlist;
  done;
  !mylist 
;;


let listFixedSize(nbList, listSize : int * int) : 'a list =
  let mylist : 'a list ref = ref [] in
  for k = 1 to nbList do
    let newlist : 'a list = randList(listSize) in
    mylist := !mylist @ newlist;
  done;
  !mylist 
;;


let listToBst(l : 'a list) : 'a t_btree =
  let t : 'a t_btree ref = ref (bt_empty()) in
  let thelist : 'a list ref = ref l in
  while (!thelist <> []) do
    t := bst_linsert(!t, List.hd(!thelist));
    thelist := List.tl(!thelist);
  done;
  !t
;;




let avgImbalanceList(nbrTest: int): unit =
  let avgRandSize: float ref = ref 0. in
  let avgIncrSize: float ref = ref 0. in
  let avgDecrSize: float ref = ref 0. in
  let avgFixeSize: float ref = ref 0. in
  let treeList: 'a t_btree ref = ref (bt_empty()) in
  for i = 1 to nbrTest do
    treeList := listToBst(listRandomSize(10, 10));
    avgRandSize := !avgRandSize +. imbalance(!treeList);

    treeList := listToBst(listIncreasingSize(10));
    avgIncrSize := !avgIncrSize +. imbalance(!treeList);

    treeList := listToBst(listDecreasingSize(10));
    avgDecrSize := !avgDecrSize +. imbalance(!treeList);

    treeList := listToBst(listFixedSize(10, 10));
    avgFixeSize := !avgFixeSize +. imbalance(!treeList)
  done;
  avgRandSize := !avgRandSize /. (float_of_int (nbrTest));
  Printf.printf "%f\n"  !avgRandSize;
  
  avgIncrSize := !avgIncrSize /. float_of_int(nbrTest);
  Printf.printf "%f\n"  !avgIncrSize;
  
  avgDecrSize := !avgDecrSize /. float_of_int(nbrTest);
  Printf.printf "%f\n"  !avgDecrSize;
  
  avgFixeSize := !avgFixeSize /. float_of_int(nbrTest);
  Printf.printf "%f\n"  !avgFixeSize;
;;



(* Expérimentation de la question 3 *)
for k = 1 to 25 do
  avgImbalanceList(100)
done
;;

