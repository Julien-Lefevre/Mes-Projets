#load "aexp.cmo";;
open Aexp;;
#load "bexp.cmo";;
open Bexp;;
#load "tprop.cmo";;
open Tprop;;
#load "prog.cmo";;
open Prog;;
(* 1.4.4 Les triplets de Hoare *)

(* Question 8 : *)
type hoare_triple = {pre : tprop; prog : prog; post : tprop};;

(* Question 9 : *)
(* voir fichier "hoare_triple_exemple.ml" *)

(* Question 10 : *)
let htvalid_test (h : hoare_triple) (value : valuation) : bool =
  if(pinterp h.pre value)
  then
    let valuation : valuation = (exec h.prog value) in
    pinterp h.post valuation
  else
    true
;;

(* fonction supplémentaire pour afficher un triplet de Hoare *)
let hoare_to_string (h : hoare_triple) : string =
  "{" ^ prop_to_string(h.pre) ^ "} " ^ prog_to_string(h.prog) ^ "{" ^ prop_to_string(h.post) ^"}"
;;
