package DataStructures;

import java.util.ArrayList;

public class WhitespaceRemover {

	public static String removeAllWhitespace(String equation) {
		String[] splittedEquation = equation.split("");
		ArrayList<String> result = new ArrayList<String>();
		for (int x = 0; x < splittedEquation.length; x++) {
			if (!splittedEquation[x].equals(" ")) {
				result.add(splittedEquation[x]);
			}
		}
		return listToString(result);
	}

	private static String listToString(ArrayList<String> list) {
		String result = "";
		for (int x = 0; x < list.size(); x++) {
			result = result + list.get(x);
		}
		return result;
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
		if (str.charAt(0) == ' ') {
			str = removeWhitespace(str.substring(1));
		} else if (str.charAt(str.length() - 1) == ' ') {
			str = removeWhitespace(str.substring(0, str.length() - 1));
		} else {
			return str;
		}
		return str;
	}
	
}
