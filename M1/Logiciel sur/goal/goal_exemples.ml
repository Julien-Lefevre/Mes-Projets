#load "aexp.cmo";;
open Aexp;;
#load "bexp.cmo";;
open Bexp;;
#load "tprop.cmo";;
open Tprop;;
#load "prog.cmo";;
open Prog;;
#load "hoare_triple.cmo";;
open Hoare_triple;;
#load "goal.cmo";;
open Goal;;
(* Question 2 : *)
let goal_1 : goal = {
    context = [("H", (Impl(Or(Equal(Var "P", Var "P"), Equal(Var "Q", Var"Q")), Equal(Var "R", Var "R"))));("H2", Equal(Var "P", Var "P"))];
    conclusion = Prop(Or( Equal(Var "P", Var "P"),  Equal(Var "Q", Var "Q")))
  };;
 
let goal_2 : goal = {
    context = [];
    conclusion = Hoare({
                       pre = Equal(Var "x", Cst (-3));
                       prog = If(Inf_Eq(Var "x", Cst 0), Affect("x", Sub(Cst 0, Var "x")), Affect("x", Var "x"));
                       post = Equal(Var "x", Cst 3)
                   })
  };;

(* test de print_goal *)
print_goal goal_1;;
print_goal goal_2;;
