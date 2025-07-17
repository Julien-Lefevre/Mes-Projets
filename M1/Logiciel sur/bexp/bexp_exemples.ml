#load "aexp.cmo";;
open Aexp;;
#load "bexp.cmo";;
open Bexp;;

(* 1.2.1 Syntaxe abstraite *)

(* Question 2 : *)
let bexp_1 : bexp = True;;

let bexp_2 : bexp = And(True, False);;
let bexp_3 : bexp = Not(True);;
let bexp_4 : bexp = Or(True, False);;

let bexp_5 : bexp = Equal(Cst 2, Cst 4);;
let bexp_6 : bexp = Equal(
                        Add(Cst 3, Cst 5),
                        Mult(Cst 2, Cst 4)
                      );;
let bexp_7 : bexp = Equal(
                        Mult(Cst(2), Var "x"),
                        Add(Var "y", Cst 1)
                      );;

let bexp_8 : bexp = Inf_Eq(Cst 5, Cst 7);;
let bexp_9 : bexp = And(
                        Inf_Eq(
                            Add(Cst 8 , Cst 9),
                            Mult(Cst 4 , Cst 5)
                          ),
                        Inf_Eq(
                            Add(Cst 3 , Var "x"),
                            Mult(Cst 4, Var "y")
                          )
                      );;

(* Question 3 : *)
bexp_to_string bexp_1;;

bexp_to_string bexp_2;;
bexp_to_string bexp_3;;
bexp_to_string bexp_4;;

bexp_to_string bexp_5;;
bexp_to_string bexp_6;;
bexp_to_string bexp_7;;

bexp_to_string bexp_8;;
bexp_to_string bexp_9;;

(* 1.2.2 Interprétation *)
(* Question 5 : *)

let value_list2 : valuation = [("x", 7);("y", 3)];;

binterp bexp_1 value_list2;;

binterp bexp_2 value_list2;;
binterp bexp_3 value_list2;;
binterp bexp_4 value_list2;;

binterp bexp_5 value_list2;;
binterp bexp_6 value_list2;;
binterp bexp_7 value_list2;;

binterp bexp_8 value_list2;;
binterp bexp_9 value_list2;;
