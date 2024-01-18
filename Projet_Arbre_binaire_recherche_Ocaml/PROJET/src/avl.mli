open BtreeS;;
open Bst;;
open UseBtree;;

val balance : ('a * int) t_btree -> ('a * int) t_btree ;;

val left_rotate : ('a * int) t_btree -> ('a * int) t_btree ;;

val right_rotate : ('a * int) t_btree -> ('a * int) t_btree ;;

val right_rotate  : ('a * int) t_btree -> ('a * int) t_btree ;;

val right_left_rotate  : ('a * int) t_btree -> ('a * int) t_btree ;;

val left_right_rotate  : ('a * int) t_btree -> ('a * int) t_btree ;;

val reequilibrer_aux : ('a * int) t_btree -> ('a * int) t_btree ;;

val reequilibrer : ('a * int) t_btree -> ('a * int) t_btree ;;

val avl_insert : ('a * int) t_btree * 'a -> ('a * int) t_btree ;;

val avl_delete : ('a * int) t_btree * 'a -> ('a * int) t_btree ;;
 
