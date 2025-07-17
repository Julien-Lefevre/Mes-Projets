package CLASS;

import java.util.*;

import CLASS.Game.*;


public class Main {
    public static void main(String[] args) {

        TheGame game = TheGame.getInstance();
        game.initGame();
        Scanner scan = MyScanner.createScanner();
        Boolean theEnd = false;
        game.theHero.help();
        System.out.println("\n\nYou just woke up, it's 6 AM. This is your last chance. 3 weeks ago, you got expelled from your last school because you weremaking trouble, and your behaviour was unacceptable. It was your fifth school of the year and your parent are very upset.\nSo much so that they gave you an ultimatum : 'YOU'D BETTER PASS YOUR EXAMS MICHAEL, OTHERWISE WE'LL SEND YOU TO MILITARY SCHOOL !!! '\nYou only have one option, find the ExamsSubject before the end of the day. Good luck.");
        System.out.println("Go to the [LivingRoom] and talk with your mom");

        while (!theEnd){
            game.doAction(scan);
        }

        scan.close();
    }


}
