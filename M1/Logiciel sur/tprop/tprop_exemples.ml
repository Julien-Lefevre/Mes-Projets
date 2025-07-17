#load "aexp.cmo";;
open Aexp;;
#load "tprop.cmo";;
open Tprop;;
(* 1.4 Triplets de Hoare et validité *)
(* 1.4.1 Syntaxe abstraite des formules de la logique des propositions *)

(* Question 2 : *)
let tprop_1 : tprop = True;;

let tprop_2 : tprop = And(True, False);;
let tprop_3 : tprop = Not(True);;
let tprop_4 : tprop = Or(True, False);;
let tprop_5 : tprop = Impl(False, Or(True, False));;

let tprop_6 : tprop = Equal(Cst 2 , Cst 4);;
let tprop_7 : tprop = Equal(
                          Add(Cst 3 , Cst 5),
                          Mult(Cst 2, Cst 4 )
                        );;
let tprop_8 : tprop = Equal(
                          Mult(Cst 2, Var "x"),
                          Add(Var "y", Cst 1)
                        );;

let tprop_9 : tprop = Inf_Eq(
                          Add(Cst 3, Var "x"),
                          Mult(Cst 4, Var "y")
                        );;
let tprop_10 : tprop = And(
                           Inf_Eq(Cst 5, Cst 7),
                           Inf_Eq(
                               Add(Cst 8 , Cst 9),
                               Mult(Cst 4 , Cst 5)
                             )
                         );;

let tprop_11 : tprop = Impl(
                           Equal(Var "x", Cst 1),
                           Inf_Eq(Var "y", Cst 0)
                         );;

(* Question 3 : *)
prop_to_string tprop_1 ;;
prop_to_string tprop_2 ;;
prop_to_string tprop_3 ;;
prop_to_string tprop_4 ;;
prop_to_string tprop_5 ;;
prop_to_string tprop_6 ;;
prop_to_string tprop_7 ;;
prop_to_string tprop_8 ;;
prop_to_string tprop_9 ;;
prop_to_string tprop_10 ;;
prop_to_string tprop_11 ;;

(* Question 5 : *)
let valuate : valuation = [("x", 7);("y", 3)];;
pinterp tprop_1 valuate;;
pinterp tprop_2 valuate;;
pinterp tprop_3 valuate;;
pinterp tprop_4 valuate;;
pinterp tprop_5 valuate;;
pinterp tprop_6 valuate;;
pinterp tprop_7 valuate;;
pinterp tprop_8 valuate;;
pinterp tprop_9 valuate;;
pinterp tprop_10 valuate;;
pinterp tprop_11 valuate;;

(* Question 7 : *)
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_1));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_2));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_3));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_4));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_5));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_6));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_7));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_8));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_9));;

prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_10));;
prop_to_string (psubst "y" (Add(Var "k", Cst 2))  (psubst "x" (Mult(Cst 3, Var "y")) tprop_11));;
