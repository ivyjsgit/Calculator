package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

	public static String removeAllWhitespace(String equation) {
		return equation.replaceAll(" ", "");
	}

	public static String listToString(ArrayList<String> list) {
        String output = Arrays.toString(list.toArray()).replace("[", "").replace("]", "");
		output = output.replaceAll(",", "");
		return output;
	}
	
	public static String changeToDouble(String string) {
		if (!string.contains(".")) {
			string = string + ".0";
		}
		return string;
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
		return result;
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
	
	public static String[] listToArray(ArrayList<String> list) {
		String[] array = new String[list.size()];
		for (int x = 0; x < list.size(); x++) {
			array[x] = list.get(x);
		}
		return array;
	}

	public static String removeWhitespaceAndParenthesis(String result) {
		result = removeWhitespace(result);
		result = removeParenthesis(result);
		return result;
	}

	private static String removeParenthesis(String str) {
		if (str.charAt(0) == '(') {
			str = removeParenthesis(str.substring(1));
		} else if (str.charAt(str.length() - 1) == ')') {
			str = removeParenthesis(str.substring(0, str.length() - 1));
		} else {
			return str;
		}
		return str;
	}
	
}
