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
#load "tactic.cmo";;
open Tactic;;

let (next_ident, reset_ident) = fresh_ident;;

(* 2.1.3 Le language des tactiques *)
(* Question 3 : *)
let apply_tactic_test =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Prop(
                                                           Impl (
                                                               Impl (Or (Equal(Var "P",Var "P"),
                                                                         Equal(Var "Q",Var "Q")),
                                                                     Equal(Var "R",Var "R")),
                                                               And (
                                                                   Impl (Equal(Var "P",Var "P"),
                                                                         Equal(Var "R",Var "R")),
                                                                   Impl (Equal(Var "Q",Var "Q"),
                                                                         Equal(Var "R",Var "R"))
                                                                 )
                                                             )
                            )} in
    print_goal proof_goal;
    
    Printf.printf ("Impl_Intro : \n");
    let proof_list = apply_tactic Impl_Intro proof_goal in
    print_goal (List.hd proof_list);
    
    Printf.printf ("And_Intro : \n");
    let proof_list2 = apply_tactic And_Intro (List.hd proof_list) in
    printgoals proof_list2;
    
    Printf.printf ("Impl_Intro : \n");
    let proof_list3 = List.append (apply_tactic Impl_Intro (List.hd proof_list2))  (List.tl proof_list2) in
    printgoals proof_list3;

    Printf.printf ("Assume (Or(Equal(Var \"P\",Var \"P\"), Equal(Var \"Q\",Var \"Q\"))) : \n");
    let proof_list4 = List.append
                        (apply_tactic
                           (Assume (Or(Equal(Var "P",Var "P"), Equal(Var "Q",Var "Q"))))
                           (List.hd proof_list3))
                        (List.tl proof_list3) in
    printgoals proof_list4;

    Printf.printf ("Impl_Elim : \n");
    let proof_list5 = List.append (
                          apply_tactic
                            (Impl_Elim ("H1", "H3")) (List.hd proof_list4))
                        (List.tl proof_list4) in
    printgoals proof_list5;
    
    Printf.printf ("Exact : \n");
    let proof_list6 = List.append (
                          apply_tactic
                            (Exact "H4") (List.hd proof_list5))
                        (List.tl proof_list5) in
    printgoals proof_list6;

    Printf.printf ("Or_Intro_1 : \n");
    let proof_list7 = List.append (
                          apply_tactic
                            (Or_Intro_1) (List.hd proof_list6))
                        (List.tl proof_list6) in
    printgoals proof_list7;

    Printf.printf ("Exact : \n");
    let proof_list8 = List.append (
                          apply_tactic
                            (Exact "H2") (List.hd proof_list7))
                        (List.tl proof_list7) in
    printgoals proof_list8;

    Printf.printf ("Impl_Intro : \n");
    let proof_list9 = List.append (
                          apply_tactic
                            (Impl_Intro) (List.hd proof_list8))
                        (List.tl proof_list8) in
    printgoals proof_list9;

    Printf.printf ("Assume (Or(Equal(Var \"P\",Var \"P\"), Equal(Var \"Q\",Var \"Q\"))) : \n");
    let proof_list10 = List.append
                        (apply_tactic
                           (Assume (Or(Equal(Var "P",Var "P"), Equal(Var "Q",Var "Q"))))
                           (List.hd proof_list9))
                        (List.tl proof_list9) in
    printgoals proof_list10;

    Printf.printf ("Impl_Elim : \n");
    let proof_list11 = List.append (
                          apply_tactic
                            (Impl_Elim ("H1", "H6")) (List.hd proof_list10))
                        (List.tl proof_list10) in
    printgoals proof_list11;

    Printf.printf ("Exact : \n");
    let proof_list12 = List.append (
                          apply_tactic
                            (Exact "H7") (List.hd proof_list11))
                        (List.tl proof_list11) in
    printgoals proof_list12;

    Printf.printf ("Or_Intro_2 : \n");
    let proof_list13 = List.append (
                          apply_tactic
                            (Or_Intro_2) (List.hd proof_list12))
                        (List.tl proof_list12) in
    printgoals proof_list13;

    Printf.printf ("Exact : \n");
    let proof_list14 = List.append (
                          apply_tactic
                            (Exact "H5") (List.hd proof_list13))
                        (List.tl proof_list13) in
    printgoals proof_list14;
  )
;;

(* Question 4 : *)

let apply_tactic_hoare_1 =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = Equal(Var "x", Cst 2);
                                                            prog = Skip;
                                                            post = Equal(Var "x", Cst 2)}
                                                         )
                            } in
    print_goal proof_goal;

    Printf.printf ("HSkip : \n");
    let proof_list = apply_tactic HSkip proof_goal in
    printgoals proof_list;
  )
;;

let apply_tactic_hoare_2 =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = Inf_Eq(Add(Var "y", Cst 1), Cst 4);
                                                            prog = Affect("y", Add(Var "y", Cst 1));
                                                            post = Inf_Eq(Var "y", Cst 4)}
                                                         )
                            } in
    print_goal proof_goal;
    Printf.printf ("HAssign : \n");
    let proof_list = apply_tactic HAssign proof_goal in
    printgoals proof_list;
  )
;;

let apply_tactic_hoare_3 =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = Equal(Var "y", Cst 5);
                                                            prog = Affect("x", Add(Var "y", Cst 1));
                                                            post = Equal(Var "x", Cst 6)}
                                                         )
                            } in
    printgoals [proof_goal];
    
    Printf.printf ("HCons(Equal(Add(Var \"y\", Cst 1), Cst 6) : \n");
    let proof_list = apply_tactic (HCons(Equal(Add(Var "y", Cst 1), Cst 6),Equal(Var "x", Cst 6)))  proof_goal in
    printgoals proof_list;

    Printf.printf("Impl_Intro : \n");
    let proof_list2 = List.append (apply_tactic Impl_Intro (List.hd proof_list)) (List.tl proof_list) in
    printgoals proof_list2;

    Printf.printf("Admit : \n");
    let proof_list3 = List.append (apply_tactic Admit (List.hd proof_list2)) (List.tl proof_list2) in
    printgoals proof_list3;

     Printf.printf("HAssign : \n");
    let proof_list4 = List.append (apply_tactic HAssign (List.hd proof_list3)) (List.tl proof_list3) in
    printgoals proof_list4;

     Printf.printf("Impl_Intro : \n");
    let proof_list5 = List.append (apply_tactic Impl_Intro (List.hd proof_list4)) (List.tl proof_list4) in
    printgoals proof_list5;

     Printf.printf("Exact H2 : \n");
    let proof_list6 = List.append (apply_tactic (Exact("H2")) (List.hd proof_list5)) (List.tl proof_list5) in
    printgoals proof_list6;
  )
;;

(* Nous n'avons pas réussi à faire les preuves des deux parties suivantes *)
(*
let apply_tactic_hoare_4 =
  (
    reset_ident();
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {
                                                             pre = True;
                                                             prog = Seq(
                                                                        Affect("z", Var "x"),
                                                                        Seq(
                                                                            Affect("z",
                                                                                   Add(Var "z", Var "y")),
                                                                            Affect("u", Var "z")));
                                                             post = Equal(Var "u",
                                                                          Add(Var "x" , Var "y"))}
                                                         )
                            } in
    print_goal proof_goal;

    Printf.printf ("HSEq : \n");
    let proof_list = apply_tactic (HSEq(Equal(Var "z", Var "x"))) proof_goal in
    printgoals proof_list;

    Printf.printf ("HCons : \n");
    let proof_list1 = List.append (apply_tactic (HCons(Equal(Var "x", Var "x"), Equal(Var "z", Var "x"))) (List.hd proof_list)) (List.tl proof_list) in
    printgoals proof_list1;

    Printf.printf ("Impl_Intro : \n");
    let proof_list2 = List.append (apply_tactic Impl_Intro (List.hd proof_list1)) (List.tl proof_list1) in
    printgoals proof_list2;

    Printf.printf ("Admit : \n");
    let proof_list3 = List.append (apply_tactic Admit (List.hd proof_list2)) (List.tl proof_list2) in
    printgoals proof_list3;

   Printf.printf ("HAssign : \n");
    let proof_list4 = List.append (apply_tactic HAssign (List.hd proof_list3)) (List.tl proof_list3) in
    printgoals proof_list4;

    Printf.printf ("Impl_Intro : \n");
     let proof_list5 = List.append (apply_tactic Impl_Intro (List.hd proof_list4)) (List.tl proof_list4) in
     printgoals proof_list5;

     Printf.printf ("Exact H2 : \n");
     let proof_list6 = List.append (apply_tactic (Exact "H2") (List.hd proof_list5)) (List.tl proof_list5) in
     printgoals proof_list6;

      Printf.printf ("HSEq : \n");
    let proof_list7 = List.append (apply_tactic (HSEq(Equal(Var "z", Add(Var "x", Var "y")))) (List.hd proof_list6)) (List.tl proof_list6) in
    printgoals proof_list7;

    Printf.printf ("HAssign : \n");
    let proof_list8 = List.append (apply_tactic HAssign (List.hd proof_list7)) (List.tl proof_list7) in
    printgoals proof_list8;
  )
;;
*)


(*
let apply_tactic_hoare_5 =
  (
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = True;
                                                            prog = If(Inf_Eq(Var "v", Cst 0), Affect ("r", Sub(Cst 0,Var "v")), Affect("r", Var "v")) ;
                                                            post = Inf_Eq(Cst 0, Var "r")}
                                                         )
                            } in
    print_goal proof_goal;

    Printf.printf ("HSkip : \n");
    let proof_list = apply_tactic HSkip proof_goal in
    printgoals proof_list;
  )
;;
 *)

(*
let apply_tactic_hoare_6 =
  (
    let proof_goal : goal = {context= []; conclusion = Hoare(
                                                           {pre = Equal(Var "x", Var "y");
                                                            prog = Repeat(Cst 10, Affect("x", Add(Var "x", Cst 1)));
                                                            post = Inf_Eq(Var "x", Add(Var "y", Cst 10))
                                                           }
                                                         )
                            } in
    print_goal proof_goal;

    Printf.printf ("HSkip : \n");
    let proof_list = apply_tactic HSkip proof_goal in
    printgoals proof_list;
  )
;;
 *)

(* Question 5 : *)

htvalid_test (
    {pre = Equal(Var "x", Cst 2);
     prog = Skip;
     post = Equal(Var "x", Cst 2)})
  [("x", 2)];;

htvalid_test (
    {pre = Inf_Eq(Add(Var "y", Cst 1), Cst 4);
     prog = Affect("y", Add(Var "y", Cst 1));
     post = Inf_Eq(Var "y", Cst 4)})
  [("y", 0)];;

htvalid_test (
    {pre = Equal(Var "y", Cst 5);
     prog = Affect("x", Add(Var "y", Cst 1));
     post = Equal(Var "x", Cst 6)})
  [("y", 5)];;

htvalid_test (
    {pre = True;
     prog = Seq(
                Affect("z", Var "x"),
                Seq(
                    Affect("z",
                           Add(Var "z", Var "y")),
                    Affect("u", Var "z")));
     post = Equal(Var "u",
                  Add(Var "x" , Var "y"))})
  [("x", 5); ("y", 6)];;

htvalid_test (
    {pre = True;
     prog = If(Inf_Eq(Var "v", Cst 0), Affect ("r", Sub(Cst 0,Var "v")), Affect("r", Var "v")) ;
     post = Inf_Eq(Cst 0, Var "r")})
  [("v", 5)];;

htvalid_test (
    {pre = Equal(Var "x", Var "y");
     prog = Repeat(Cst 10, Affect("x", Add(Var "x", Cst 1)));
     post = Inf_Eq(Var "x", Add(Var "y", Cst 10))})
  [("x", 5);("y", 5)];;
