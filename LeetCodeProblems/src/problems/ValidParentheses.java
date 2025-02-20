package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidParentheses {
	public static boolean isValid(String s) {
		List<String> listBracket = new ArrayList<>(List.of(s.split("")));
		List<String> leftBracket = List.of("(", "[", "{");
		Map<String, String> pair = new HashMap<>();
		pair.put("(", ")");
		pair.put("[", "]");
		pair.put("{", "}");
		if (listBracket.size() % 2 != 0 || listBracket.isEmpty())
			return false;
		else {
			String firstBracket = listBracket.get(0);
			String relatingBracket = "";
			int firstIndex = 0;
			int secondIndex = 0;
			boolean foundPair = false;
			for (Map.Entry<String, String> entry : pair.entrySet()) {
				if (entry.getKey().equals(firstBracket)) {
					relatingBracket = entry.getValue();
				}
			}
			if (!leftBracket.contains(firstBracket))
				return false;
			for (int i = 1; i < listBracket.size(); i++) {
				if (listBracket.get(i).equals(firstBracket)) {
					firstBracket = listBracket.get(i);
					firstIndex = i;
				}
				if (listBracket.get(i).equals(relatingBracket)) {
					foundPair = true;
					secondIndex = i;
					break;
				}
			}
			if (foundPair) {
				// Make sure number brackets between pair brackets is even
				if ((secondIndex - firstIndex) % 2 == 0)
					return false;
				listBracket.remove(secondIndex);
				listBracket.remove(firstIndex);
			} else
				return false;

			if (listBracket.isEmpty())
				return true;
			else {
				StringBuilder sb = new StringBuilder();
				listBracket.stream().forEach(br -> sb.append(br));
				return isValid(sb.toString());
			}
		}
	}

	public static void main(String[] args) {
		if (isValid("[{]}"))
			System.out.println("True");
		else
			System.out.println("False");
	}
}

