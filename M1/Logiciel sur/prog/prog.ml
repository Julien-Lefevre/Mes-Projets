#load "aexp.cmo";;
open Aexp;;
#load "bexp.cmo";;
open Bexp;;
(* 1.3 Les commandes du language *)
(* 1.3.1 Syntaxe abstraite *)

(* Question 1 : *)
type prog =
  | Skip
  | Affect of string * aexp
  | Seq of prog * prog
  | If of bexp * prog * prog
  | Repeat of aexp * prog
;;

(* Question 2 : *)
(* voir le fichier "prog_exemples.ml" *)

(* Question 3 : *)
let rec prog_to_string (p : prog) : string =
  match p with
  | Skip -> "skip"
  | Affect(var, exp) -> var ^ " := " ^ (aexp_to_string exp)
  | Seq(p1, p2) -> (prog_to_string p1) ^ "; " ^(prog_to_string p2)
  | If(b, p1, p2) -> "if " ^ (bexp_to_string b) ^ " then " ^ (prog_to_string p1) ^ " else " ^(prog_to_string p2)
  | Repeat(a, p) -> "repeat " ^ (aexp_to_string a) ^ " do " ^ (prog_to_string p) ^ " od"
;;
(* voir le fichier "prog_exemples.ml" *)


(* Question 4 : *)
let rec selfcompose (func : ('a -> 'a)) (n : int) : ('a -> 'a) =
  if(n = 0)
  then (fun n -> n)
  else fun x -> func((selfcompose func (n-1)) x)
;;

(* Question 5 : *)
(* voir le fichier "prog_exemples.ml" *)

(* Question 6 : *)
let rec exec (p : prog) (value : valuation) : valuation =
  match p with
  | Skip -> value
  | Affect(var, exp) -> (var, ainterp exp value) :: value
  | Seq(p1,p2) -> exec p2 (exec p1 value)
  | If(b, p1,  p2) -> if (binterp b value)
                      then exec p1 value
                      else exec p2 value
  | Repeat(a, p) -> let nbiter = ainterp a value in
                    selfcompose (exec p) nbiter value
;;

(* Question 7 : *)
(* voir le fichier "prog_exemples.ml" *)
