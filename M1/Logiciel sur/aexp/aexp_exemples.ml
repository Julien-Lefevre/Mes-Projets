#load "aexp.cmo";;
open Aexp;;

(* Partie 1 : Un interpréteur pour un language impératif *)

(* 1.1 Les expressions arithmétiques *)
(* 1.1.1 Syntaxe abstraite *)

(* Question 2 : *)
let q2_1 : aexp = Cst 2 ;;

let q2_2 : aexp = Add ( Cst 2, Cst 3 );;
let q2_3 : aexp = Sub (Cst 2, Cst 5 );;
let q2_4 : aexp = Mult (Cst 3, Cst 6);;

let q2_5 : aexp = Add (Cst 2, Var "x");;
let q2_6 : aexp = Mult(Cst 4 , Var "y");;
let q2_7 : aexp = Mult(Mult(Cst 3, Var "x"), Var "x");;
let q2_8 : aexp = Add( Mult(Cst 5, Var "x"), Mult(Cst 7, Var "y"));;
let q2_9 : aexp = Add(Mult (Cst 6, Var "x") ,  Mult(Mult(Cst 5, Var "y"), Var "x"));;

(* Question 3.2 : *)

aexp_to_string q2_1 ;;

aexp_to_string q2_2 ;;
aexp_to_string q2_3 ;;
aexp_to_string q2_4 ;;

aexp_to_string q2_5 ;;
aexp_to_string q2_6 ;;
aexp_to_string q2_7 ;;
aexp_to_string q2_8 ;;
aexp_to_string q2_9 ;;

(* 1.1.2 Interprétation *)

(* Question 6 : *)
let value_list = [("x", 5) ; ("y", 9)];;

ainterp q2_1 value_list;;

ainterp q2_2 value_list;;
ainterp q2_3 value_list;;
ainterp q2_4 value_list;;

ainterp q2_5 value_list;;
ainterp q2_6 value_list;;
ainterp q2_7 value_list;;
ainterp q2_8 value_list;;
ainterp q2_9 value_list;;

(* 1.1.3 Substitutions *)

(* Question 8 : *)

aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_1));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_2));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_3));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_4));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_5));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_6));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_7));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_8));;
aexp_to_string (asubst "y" (Add(Var "z", Cst 2)) (asubst "x" (Cst 7) q2_9));;
