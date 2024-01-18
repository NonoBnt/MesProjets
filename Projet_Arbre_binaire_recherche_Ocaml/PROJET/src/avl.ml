open BtreeS;;
open Bst;;
open UseBtree;;



let rec balance(t : ('a * int) t_btree) : ('a * int) t_btree =
  if bt_isempty(t)
  then bt_empty()
  else
    let (g, d) : int * int = (heigth(bt_subleft(t)), heigth(bt_subright(t))) in
    let new_balance : int = g-d in
    let (x, y) = bt_root(t) in
    let new_root = (x, new_balance) in
    bt_rooting(new_root, balance(bt_subleft(t)), balance(bt_subright(t)))
;;

let left_rotate (t : ('a * int) t_btree) : ('a * int) t_btree =
  if bt_isempty(t)
  then failwith("error left_rotate : tree is empty")
  else
    let t2 : ('a * int) t_btree = bt_rooting(bt_root(bt_subright(t)), 
                                        bt_rooting(bt_root(t), bt_subleft(t), bt_subleft(bt_subright(t))), 
                                        bt_subright(bt_subright(t))); in
    balance(t2)
;;

let right_rotate (t : ('a * int) t_btree) : ('a * int) t_btree =
  if bt_isempty(t)
  then failwith("error left_rotate : tree is empty")
  else
    let t2 : ('a * int) t_btree = bt_rooting(bt_root(bt_subleft(t)),
                                        bt_subleft(bt_subleft(t)),
                                        bt_rooting(bt_root(t), bt_subright(bt_subleft(t)), bt_subright(t))) in
    balance(t2)
;;

let right_left_rotate (t : ('a * int) t_btree) : ('a * int) t_btree =
  if bt_isempty(t)
  then failwith("error left_rotate : tree is empty")
  else
    let t2 : ('a * int) t_btree = bt_rooting(bt_root(t), bt_subleft(t), right_rotate(bt_subright(t))) in
    left_rotate(t2)
;;

let left_right_rotate (t : ('a * int) t_btree) : ('a * int) t_btree =
  if bt_isempty(t)
  then failwith("error left_rotate : tree is empty")
  else
    let t2 : ('a * int) t_btree = bt_rooting(bt_root(t), left_rotate(bt_subleft(t)), bt_subright(t)) in
    right_rotate(t2)
;;

let rec reequilibrer_aux( t : ('a * int) t_btree) : ('a * int) t_btree =
  let (value, t_balance) = bt_root(t) in
  if t_balance = 2
  then
    let (value_left, balance_left) = bt_root(bt_subleft(t)) in
    if balance_left = -1
    then left_right_rotate(t)
    else
      if balance_left = 1 || balance_left = 0
      then right_rotate(t)
      else bt_rooting(bt_root(t), reequilibrer_aux(bt_subleft(t)), bt_subright(t))
  else
    let (value_left, balance_right) = bt_root(bt_subright(t)) in
    if balance_right = -1 || balance_right = 0
    then left_rotate(t)
    else
      if balance_right = 1
      then right_left_rotate(t)
      else bt_rooting(bt_root(t), bt_subleft(t), reequilibrer_aux(bt_subright(t)))
;;

let rec reequilibrer( t : ('a * int) t_btree) : ('a * int) t_btree =
  if bt_isempty(t)
  then bt_empty()
  else
    let (value, t_balance) = bt_root(t) in
    if t_balance >= -1 && t_balance <= 1
    then bt_rooting(bt_root(t), reequilibrer(bt_subleft(t)), reequilibrer(bt_subright(t)))
    else reequilibrer_aux(t)
;;

let rec avl_insert(t , x : ('a * int) t_btree * 'a) : ('a * int) t_btree =
  if bt_isempty(t)
  then
    bt_rooting((x,0), bt_empty(), bt_empty())
  else
    let (y, z) = bt_root(t) in
    if y < x
    then
      let t2 = bt_rooting(bt_root(t), bt_subleft(t), avl_insert(bt_subright(t), x)) in
      let t3 = balance(t2) in
      reequilibrer(t3)
    else
      let t2 = bt_rooting(bt_root(t), avl_insert(bt_subleft(t), x), bt_subright(t)) in
      let t3 = balance(t2) in
      reequilibrer(t3)
;;

let rec avl_delete(t, x : ('a * int) t_btree * 'a) : ('a * int) t_btree =
 if bt_isempty(t)
 then failwith("erreur bst_delete : tree is empty or vakue doesn't exits in the tree")
 else
   let (y, z) = bt_root(t) in
   if y = x
   then
     let (l,r) : ('a * int) t_btree * ('a * int) t_btree = (bt_subleft(t), bt_subright(t)) in
     if bt_isempty(r)
     then
       if bt_isempty(l)
       then bt_empty()
       else
         let t2 : ('a * int) t_btree = bt_rooting(bt_root(l), bt_subleft(l), bt_subright(l)) in
         let t3 = balance(t2) in
         reequilibrer(t3)
     else
       let t2 : ('a * int) t_btree = bt_rooting(bt_root(r), l, bt_subright(r)) in
       let t3 = balance(t2) in
       reequilibrer(t3)
   else
     let (y, z) = bt_root(t) in
     if y < x
      then
        let t2 : ('a * int) t_btree = bt_rooting(bt_root(t), bt_subleft(t), avl_delete(bt_subright(t), x)) in
        let t3 = balance(t2) in
        reequilibrer(t3)
      else
        let t2 : ('a * int) t_btree = bt_rooting(bt_root(t), avl_delete(bt_subleft(t), x), bt_subright(t)) in
        let t3 = balance(t2) in
        reequilibrer(t3)
;;
