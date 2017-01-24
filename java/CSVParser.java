import java.util.*;

public class CSVParser {
	public String parseCSV(String s) {
		if (s.length() == 0) {
			return "";
		}
		boolean inQuote = false;
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!inQuote) {
				if (c == ',' || i == s.length() - 1) {
					res.add(sb.toString());
					sb.setLength(0);	// or create a new StringBuilder
				} else if (c == '"') {
					inQuote = true;
				} else {
					sb.append(c);
				}
			} else {
				if ( c == '"') {
					if (i < s.length() - 1 && s.charAt(i + 1) == '"') {
						while (i + 1 <= s.length() - 1 && s.charAt(i + 1) == '"') {
							i++;
						}
						sb.append('"');
						if (i == s.length() - 1 || (i != s.length() - 1 && s.charAt(i + 1) == ',')) {
							i--;
						}
					} else if (i == s.length() - 1) {
						res.add(sb.toString());
						break;
					} else {
						inQuote = false;
					}
				} else {
					sb.append(c);
				}
			}
		}
		StringBuilder ssb = new StringBuilder();
		for (String ss : res) {
			ssb.append(ss);
			ssb.append('|');
		}
		ssb.deleteCharAt(ssb.length() - 1);	// remove the last '|'
		return ssb.toString();
	}

	public static void main(String[] args) {
		CSVParser s = new CSVParser();
        String input1 = "John,Smith,john.smith@gmail.com,Los Angeles,1";
        System.out.println(s.parseCSV(input1));

        String input2 = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0";
        System.out.println(s.parseCSV(input2));

        String input3 = "\"Alexandra \"\"\"Alex\"\"\"\",Menendez,alex.menendez@gmail.com,Miami,1";
        System.out.println(s.parseCSV(input3));

        String input4 = "\"\"\"Alexandra Alex\"\"\"";
        System.out.println(s.parseCSV(input4));
	}

}
