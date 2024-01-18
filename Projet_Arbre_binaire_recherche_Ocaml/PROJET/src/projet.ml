#load "btreeS.cmo";;
open BtreeS;;
#load "bst.cmo";;
open Bst;;
#load "useBtree.cmo";;
open UseBtree;;
#load "avl.cmo";;
open Avl;;

(*--------------------------------------------------------------------------------------*)
(*---------------------------------- Exercice 1 ----------------------------------------*)
(*--------------------------------------------------------------------------------------*)


(*----------------------------*)
(*-------- Question 1 --------*)
(*----------------------------*)

(* initialisation de Ramdom pour utiliser la fonction Random.int *)
Random.init(1000);;

(* fonction bst_rnd_create : crée un arbre binaire de recherche avec des valeurs aléatoires *)
let rec bst_rnd_create(nb, nb2, res : int * int * 'a t_btree) : 'a t_btree =
  if nb <= 0
  then res
  else
    let res2 = bst_linsert(res, Random.int nb2) in
    bst_rnd_create(nb -1, nb2, res2)
;;

(*----------------------------*)
(*-------- Question 2 --------*)
(*----------------------------*)

(* fonction balance : determine le déséquilibre de la racine de l'arbre passé en paramètre *)
let rec balance(t: 'a t_btree) : int =
  if bt_isempty(t)
  then 0
  else
    let (g, d) : int * int = (heigth(bt_subleft(t)), heigth(bt_subright(t))) in
    abs(g-d)
;;


let rec average_balance_aux(t : 'a t_btree) : int =
  if bt_isempty(t)
  then 0
  else
    let x = balance(t) + average_balance_aux(bt_subleft(t)) + average_balance_aux(bt_subright(t)) in
    x
;;

let average_balance(t : 'a t_btree) : float =
  if bt_isempty(t)
  then failwith("error average_balance : tree is empty")
  else
    let taille : int = size(t) in
    float_of_int(average_balance_aux(t)) /. float_of_int(taille)
;;

(* fonction average_tree_balance : determine le déséquilibre moyen des arbres binaires de recherche à partir de x arbres crées avec des valeurs aléatoires *)
(* y est la taille de l'arbre que l'on souhaite *)
let average_tree_balance(x, y : int * int) : float =
  let res : float ref = ref 0. in
  for i = 0 to x
  do
    (
      let t : int t_btree = bst_rnd_create(y, 200, bt_empty()) in
      res := !res +. average_balance(t);
    )
  done;
  !res/. float_of_int(x)
;;

(*----------------------------*)
(*-------- Question 3 --------*)
(*----------------------------*)

(* créée une liste qui contient une suite de chiffres allant de x à x + y *)
let rec to_list_up(x, y: int * int) : int list =
  if x == y
  then []
  else x :: to_list_up(x +1, y)
;;

(* créée une liste qui contient une suite de chiffres allant de x à x - y *)
let rec to_list_down(x, y: int * int) : int list =
  if x == y || x == 0
  then []
  else x :: to_list_down(x-1, y)
;;

(* créée une liste de 1000 éléments contenant plusieurs suites de chiffres, chaque suite de chiffres commence à un chiffre aléatoire x2 et se finit à x2 +y/10 *)
(* y est la taille des arbres voulu divisé par 10 car il y a une boucle for qui s'execute 10 fois donc 10 * (y/10) reviens à y et permet de faire plusieurs suite et non une seule *)
let rec list_arbre_up(x, y : int * int) : int list =
  let res : int list ref = ref [] in
  for i = 0 to 9
  do
    let x2 : int = Random.int x in
    res := to_list_up(x2, x2 + y/10) @ !res;
  done;
  !res
;;

(* créée une liste de 1000 éléments contenant plusieurs suites de chiffres, chaque suite de chiffres commence à un chiffre aléatoire x2 et se finit à x2 - y/10 *)
(* y est la taille des arbres voulu divisé par 10 car il y a une boucle for qui s'execute 10 fois donc 10 * (y/10) reviens à y et permet de faire plusieurs suite et non une seule *)
let rec list_arbre_down(x, y : int * int) : int list =
  let res : int list ref = ref [] in
  for i = 0 to 9
  do
    let x2 : int = Random.int x in
    res := to_list_down(x2, x2 - y/10) @ !res;
  done;
  !res
;;


(* calcule le déséquilibre moyen d'arbres créés à partir d'une liste de plusieurs suites croissantes *)
let average_tree_balance_up(x, y: int * int): float =
  let res : float ref = ref 0. in
  for i = 0 to x
  do
    (
      let t : int t_btree =  bst_lbuild(list_arbre_up(200, y)) in
      res := !res +. average_balance(t);
    )
  done;
  !res /. float_of_int(x)
;;

(* calcule le déséquilibre moyen d'arbres créés à partir d'une liste de plusieurs suites décroissantes *)
let average_tree_balance_down(x, y : int * int) : float =
  let res : float ref = ref 0. in
  for i = 0 to x
  do
    (
      let t : int t_btree =  bst_lbuild(list_arbre_up(200, y)) in
      res := !res +. average_balance(t);
    )
  done;
  !res/.float_of_int(x)
;;

(* nombre d'arbres à créer pour le test *)
let t : int = 1000;;

(* taille des arbres que l'on créé pour le test *)
let taille : int = 10;;
(*let taille : int = 100;;*)
(*let taille : int = 1000;;*)

(*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*)
(* Chacunes des expérimentations ci-dessous ont été réalisé 10 fois d'affilée et les valeurs ont été reportées dans un tableau pour avoir une estimation significative du déséquilibre moyen *)

(* cas avec des suites croissantes *) 
average_tree_balance_up(t, taille);;

(* cas avec des suites décroissantes *) 
average_tree_balance_down(t, taille);;

(* cas avec des valeurs aléatoires *) 
average_tree_balance(t, taille);;


(*--------------------------------------------------------------------------------------*)
(*---------------------------------- Exercice 2 ----------------------------------------*)
(*--------------------------------------------------------------------------------------*)


(*----------------------------*)
(*------- Question 2.2 -------*)
(*----------------------------*)

(* fonction pour créer un avl à partir de valeur aléatoires *)
let rec avl_rnd_create (nb, nbrd, res : int * int * ('a * int) t_btree) : ('a * int) t_btree =
  if nb <= 0
  then res
  else
    let res2 = avl_insert(res, Random.int nbrd) in
    avl_rnd_create(nb-1, nbrd, res2)
;;

(* fonction qui évalue le temps d'execution d'un programme, fonction issue du fichier "ap2util.ml", qui nous a été fournie l'année précedente *)
let time_eval(myfunc, myparam : ('a -> 'b) * 'a) : float = 
  let mem_tm : float = Sys.time() in
    (
    ignore(myfunc(myparam)) ;
    Sys.time() -. mem_tm
    )
;;

(* calcule le temps d'execution de l'opération d'insertion dans un avl dans le but de déterminer la complexité *)
let avl_complexite_insert(taille, nb : int * int) : float =
  let avl : ('a * int) t_btree = avl_rnd_create(taille, 200, bt_empty()) in
  let x : int = Random.int nb in
  let value : float ref = ref 0. in
  for i = 0 to 1000
  do
    (
      value := !value +. time_eval(avl_insert,(avl, x));
    )
  done;
  !value /. 1000.
;;

(* calcule le temps d'execution de l'opération de recherche dans un avl dans le but de déterminer la complexité *)
let avl_complexite_seek(taille, nb : int * int) : float =
  let avl : ('a * int) t_btree = avl_rnd_create(taille, 200, bt_empty()) in
  let x : int = Random.int nb in
  let value : float ref = ref 0. in
  for i = 0 to 1000
  do
    (
      value := !value +. time_eval(bst_seek,(avl, (x, 0)));
    )
  done;
  !value /. 1000.
;;

(* calcule le temps d'execution de l'opération de suppression dans un avl dans le but de déterminer la complexité *)
let avl_complexite_delete(taille, nb : int * int) : float =
  let avl : ('a * int) t_btree = avl_rnd_create(taille, 200, bt_empty()) in
  let x : int = Random.int nb in
  let value : float ref = ref 0. in
  for i = 0 to 1000
  do
    (
      value := !value +. time_eval(avl_delete,(avl, x));
    )
  done;
  !value /. 1000.
;;

