package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;

public class WhitespaceRemover {

	public static String removeAllWhitespace(String equation) {
		return equation.replaceAll(" ", "");
	}

	private static String listToString(ArrayList<String> list) {
        String output = Arrays.toString(list.toArray()).replace("[", "").replace("]", "");
		output.replaceAll(",", "");
		return output;
	}
	
	public static String removeExtraWhitespace(String function) {
		String[] splittedFunction = function.split(" ");
		for (int x = 0; x < splittedFunction.length; x++) {
			splittedFunction[x] = removeWhitespace(splittedFunction[x]);
		}
		String result = arrayToString(splittedFunction);
		return result;
	}

	private static String arrayToString(String[] splittedFunction) {
		String result = String.join(" ", splittedFunction);
		return result.substring(1);
	}

	public static String removeWhitespace(String str) {
		System.out.println(str);
		if (str.charAt(0) == ' ') {
			str = removeWhitespace(str.substring(1));
		} else if (str.charAt(str.length() - 1) == ' ') {
			str = removeWhitespace(str.substring(0, str.length() - 1));
		} else {
			System.out.println(str);
			return str;
		}
		System.out.println(str);
		return str;
	}
	
}
