package CLASS.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;


public class MyScanner {
	
	public static Scanner createScanner() {
        return new Scanner(System.in);
    }

	private static final Map<String, List<Integer>> VALID_COMMANDS = new HashMap<>();

    static {
        VALID_COMMANDS.put("ATTACK", Arrays.asList(1));
        VALID_COMMANDS.put("GO", Arrays.asList(1));
        VALID_COMMANDS.put("HELP", Arrays.asList(0));
		VALID_COMMANDS.put("INVENTORY", Arrays.asList(0));
        VALID_COMMANDS.put("LOOK", Arrays.asList(0,1));
        VALID_COMMANDS.put("QUIT", Arrays.asList(0));
        VALID_COMMANDS.put("TAKE", Arrays.asList(1));
		VALID_COMMANDS.put("TALK", Arrays.asList(1));
        VALID_COMMANDS.put("USE", Arrays.asList(1, 2));
    }

	public static String[] scanning(Scanner scan) {
		String phrase = "?";
		String[] words;
		String[] valid = null;
		Boolean validAction = false;

		while (!validAction){
			if (scan.hasNextLine()){
 				phrase = scan.nextLine();
				words = phrase.split("\\s+");
				if (checkCommand(words[0], words.length-1)){
					valid = words;
					validAction = true;
				}
			}
		}
		return valid;
	}

    public static boolean checkCommand(String word, int nbParameters) {
		if (VALID_COMMANDS.containsKey(word)){
			for (int num : VALID_COMMANDS.get(word)){
				if (nbParameters == num){
					return true;
				}
			}
			System.out.println("Le nombre de paramètres n'est pas bon.");
			return false;
		}
		else {
			System.out.println("La commande n'existe pas.");
			return false;
		} 
	}

	public static String scanCode(Scanner scan) {
		System.out.println("Enter a value :");
		String Code = scan.nextLine();
		return Code;
	}

	public static String scanTxt(Scanner scan) {
		String txt = scan.nextLine();
		return txt;
	}
}