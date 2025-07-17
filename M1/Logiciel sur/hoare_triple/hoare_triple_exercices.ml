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
(* Question 9 : *)
let hoare_1 : hoare_triple = {pre = (Equal(Var "x", Cst 2)); prog = Skip; post = (Equal(Var "x", Cst 2))};;

let hoare_2 : hoare_triple = {pre = Equal(Var "x", Cst 2) ; prog = Affect("x",Cst 3) ; post = Equal(Var"x", Cst 3)};;
let hoare_3 : hoare_triple = {
    pre = True;
    prog = If(
               Inf_Eq(Var "x", Cst 0),
               Affect("r", Sub(Cst 0, Var "x")),
               Affect("r", Var "x");
             );
    post = Inf_Eq(Cst 0, Var "r")
  };;

let factoriel : prog =
  Seq (
      Affect ("out", Cst 1),
      Repeat (Var "in",
              Seq (
                  Affect ("out",
                          Mult (Var "out", Var "in")),
                  Affect ("in", Sub (Var "in", Cst 1))
                )
        )
    )
;;

let hoare_4 : hoare_triple = {pre = And(
                                        Equal(Var "in", Cst 5),
                                        Equal(Var "out", Cst 1));
                              prog = factoriel;
                              post = And(
                                        Equal(Var "in", Cst 0),
                                        Equal(Var "out", Cst 120));
                             };;

(* Question 10 : *)

let value : valuation = [("x", 2);("in", 5);("out", 1)];;
htvalid_test hoare_1 value;;
htvalid_test hoare_2 value;;
htvalid_test hoare_3 value;;
htvalid_test hoare_4 value;;
