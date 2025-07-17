type 'a t_btree = EMPTY
                |ROOT of 'a * 'a t_btree * 'a t_btree
;;



let bt_empty () : 'a t_btree =
  EMPTY
;;

let bt_rooting (x, g, d : 'a * 'a t_btree * 'a t_btree) : 'a t_btree =
   ROOT (x,g,d)
;;

let bt_isempty (a : 'a t_btree) : bool =
  match a with
  |EMPTY -> true
  | _ -> false
;;

let bt_root (a : 'a t_btree) : 'a =
  match a with
|EMPTY -> failwith ("in bt_root : tree is empty")
|ROOT (x,g,d) -> x
;;

let bt_subleft (abr : 'a t_btree) : 'a t_btree =
  match abr with
|EMPTY -> failwith ("in bt_subleft : tree is empty")
|ROOT (x,g,d) -> g
;;

let bt_subright (abr : 'a t_btree) : 'a t_btree =
  match abr with
|EMPTY -> failwith ("in bt_subright : tree is empty")
|ROOT (x,g,d) -> d
;;
