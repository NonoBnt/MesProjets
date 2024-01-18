(** File for tree*)

(** type for a tree *)
type +'a t_btree;;

(** create an empty tree *)
val bt_empty : unit -> 'a t_btree;;

(** create a tree with a root and 2 leafs *)
val bt_rooting : 'a * 'a t_btree * 'a t_btree -> 'a t_btree;;

(** check if the tree is empty *)
val bt_isempty : 'a t_btree -> bool;;

(** return the root of a tree *)
val bt_root : 'a t_btree -> 'a;;

(** return the left tree*)
val bt_subleft : 'a t_btree -> 'a t_btree ;;

(** return the right tree *)
val bt_subright : 'a t_btree -> 'a t_btree ;;
