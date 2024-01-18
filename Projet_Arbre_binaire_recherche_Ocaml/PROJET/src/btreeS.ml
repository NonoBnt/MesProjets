type 'a t_btree = EMPTY
                | ROOT of 'a * 'a t_btree * 'a t_btree
;;

let bt_empty() : 'a t_btree =
  EMPTY
;;

let bt_rooting(x, g, d : 'a * 'a t_btree * 'a t_btree) : 'a t_btree =
  ROOT (x, g, d)
;;

let bt_isempty(t : 'a t_btree) : bool =
  match t with
  | EMPTY -> true
  | _ -> false
;;

let bt_root(t  : 'a t_btree) : 'a =
  match t with
  |EMPTY -> failwith("error bt_root : tree is empty ")
  |ROOT(x, g, d) -> x
;;


let bt_subleft( t : 'a t_btree) : 'a t_btree =
  match t with
  |EMPTY -> failwith("error bt_subleft : tree is empty")
  |ROOT(x, g, d) -> g
;;

let bt_subright( t : 'a t_btree) : 'a t_btree =
  match t with
  |EMPTY -> failwith("error bt_subright : tree is empty")
  |ROOT(x, g, d) -> d
;;
