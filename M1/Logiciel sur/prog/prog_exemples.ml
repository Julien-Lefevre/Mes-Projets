#load "aexp.cmo";;
open Aexp;;
#load "bexp.cmo";;
open Bexp;;
#load "prog.cmo";;
open Prog;;
(* 1.3 Les commandes du language *)
(* 1.3.1 Syntaxe abstraite *)

(* Question 2 : *)
let prog_1 : prog = Affect("y", Cst 7);;
let prog_2 : prog = Seq(
                        Affect("z", Add(Cst 3, Cst 4)),
                        Affect("x", Mult(Cst 2, Var "x")));;
let prog_3 : prog = Seq(
                        Affect("n", Cst 3),
                        If(
                            Inf_Eq(Var "n", Cst 4 ),
                            Affect("n", Add(Mult(Cst 2, Var "n"), Cst 3)),
                            Affect("n", Add(Var "n", Cst 1))
                      ));;
let prog_4 : prog = Repeat (Cst 10, Affect("x", Add(Var "x", Cst 1)));;

(* Question 3 : *)
prog_to_string prog_1 ;;
prog_to_string prog_2 ;;
prog_to_string prog_3 ;;
prog_to_string prog_4 ;;

(* Question 5 : *)
let test_selfcompose : int = selfcompose (fun x -> x + 2) 10 0;;

(* Question 7 : *)
let factoriel : prog =
  Seq (
      Affect ("res", Cst 1),
      Repeat (Var "x",
              Seq (
                  Affect ("res",
                          Mult (Var "res", Var "x")),
                  Affect ("x", Sub (Var "x", Cst 1))
                )
        )
    )
;;

exec factoriel [("x", 5)];;

let fibo : prog =
  Seq (Affect ("tempres", Cst 0),
       Seq (Affect ("res", Cst 1),
            Repeat (Var "x",
                    Seq (
                        Affect ("temp", Var "res"),
                        Seq (
                            Affect ("res", Add (Var "tempres", Var "res")),
                            Affect ("tempres", Var "temp")
                          )
                      )
              )
         )
    )
;;

exec fibo [("x", 7)];;

