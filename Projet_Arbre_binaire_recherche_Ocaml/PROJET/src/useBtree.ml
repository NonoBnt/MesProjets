open BtreeS;;

let rec size(t : 'a t_btree) : int =
  if (bt_isempty(t))
  then 0
  else
    1 + size(bt_subleft(t)) + size(bt_subright(t))
;;

let rec heigth(t : 'a t_btree) : int =
  if bt_isempty(t)
  then 0
  else
    let (g, d) : int * int = (heigth(bt_subleft(t)), heigth(bt_subright(t))) in
    if g >= d
    then 1 + g
    else 1 + d
;;

let rec btree_to_string(t : 'a t_btree ) : string =
  if bt_isempty(t)
  then "empty"
  else
    "(" ^ string_of_int(bt_root(t)) ^ "," ^ btree_to_string(bt_subleft(t)) ^ "," ^ btree_to_string(bt_subright(t)) ^ ")"
;;
