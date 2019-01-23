package lab7;
import java.util.Scanner;

public class RegEx {
static Scanner scnr = new Scanner(System.in);
static int index;
	
	public static void main(String[] args) {
		boolean retry = true;
		greeting();
		while (retry) {
			String[] dataSet = { "Name", "email", "phone number", "date", "HTML elements" };
			boolean valid;
			
			for (String data : dataSet) {
				for (int i = 0; i < dataSet.length; i++) {
					if (dataSet[i] == data) {
						index = i;
					}
				}
				do {
					valid = validateArray(userInput(data));
					validate(valid, data);
				} while (!valid);
			}
			retry = retry();
		}
		exit();
		scnr.close();
	}
	
	private static void greeting() {
		System.out.println("Welcome to the Data Validation Tool...");
	}
	
	private static String userInput(String dataRequest) {
		do {
		System.out.print("Please enter a valid " + dataRequest + ": ");
		} while (!scnr.hasNextLine());
		return scnr.nextLine();
	}
	
	private static boolean validateArray(String input) {
		boolean[] methods = new boolean[] { validateName(input), validateEmail(input), validatePhone(input), validateDate(input), validateHtml(input) };
		return methods[index];
	}
	
	private static void validate(boolean valid, String data) {
		if (valid) {
			System.out.println(data + " is valid!");
		} else {
			System.out.println("Sorry, " + data + " is not valid!");
		}
	}
	
	private static boolean validateName(String fullName) {
		String[] names = fullName.split(" ");
		for (String name : names) {
			if (!name.matches("[A-Z]{1}[A-z]{0,30}")) {
				return false;
			}
		}
		return true;
		////can only have alphabetic chars, start with capital, max length 30
	}
	
	private static boolean validateEmail(String email) {
		return email.matches("[A-z\\d]{5,30}@[A-z\\d]{5,10}.[A-z\\d]{2,3}");
		////combo of alphanumeric chars & length 5-30 before @
		////after @ combo of alphanumeric chars & length 5-10 before .
		////after . combo of alphanumeric chars & length 2 or 3
	}
	
	private static boolean validatePhone(String phone) {
		return phone.matches("\\d{3}-\\d{3}-\\d{4}");
		////###-###-####
	}
	
	private static boolean validateDate(String fullDate) {
		if (fullDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
			String[] dates = fullDate.split("/");
			int month = Integer.parseInt(dates[1]);
			if (month < 1 || month > 12) {
				return false;
			} 
			int days = Integer.parseInt(dates[0]);
			if (days < 1 || days > 31) {
				return false;
			}
			int year = Integer.parseInt(dates[2]);
			if (month == 2) {
				if ((leapYear(year) && days > 29) || (!leapYear(year) && days > 28)) {
					return false;
				} 
			}
			if (days > maxDaysInMonth(month)) {
				return false;
			}
		}
		return true;
		/////dd/mm/yyyy
	}
	
	private static int maxDaysInMonth(int month) {
		if (month == 2) {
			return 29;
		} else if (month == 9 || month == 4 || month == 6 || month == 11) {
			return 30;
		} else {
			return 31;
		}
	}
	
	private static boolean leapYear(int year) {
		if (year % 4 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean validateHtml(String html) {
		boolean valid = true;
		String tagPrefix;
		String tagSuffix;
		boolean tags = true;
		while(valid && tags) {
			html = html.trim();
			int firstIndexLeft = html.indexOf("<");
			int firstIndexRight = html.indexOf(">");
			int lastIndexLeft = html.lastIndexOf("<");
			int lastIndexRight = html.lastIndexOf(">");
			if (firstIndexLeft != 0 || lastIndexRight != (html.length() - 1)) {
				return false;
			}	
			
			tagPrefix = html.substring(firstIndexLeft, firstIndexRight + 1);
			tagSuffix = html.substring(lastIndexLeft, lastIndexRight + 1);
			valid = validateHtmlTags(tagPrefix, tagSuffix);
			
			html = html.substring(firstIndexRight + 1, lastIndexLeft);
			tags = ((html.indexOf("<") != -1) && (html.indexOf(">") != -1) && (html.indexOf("<") != html.lastIndexOf("<")) && (html.indexOf(">") != html.lastIndexOf(">")));
		}  
		return valid;
		/////<p>  </p> is valid
		/////<h1 </h1> is not valid
	}
	
	private static boolean validateHtmlTags(String tagPrefix, String tagSuffix) {
		if (!tagPrefix.matches("<[\\w$]+>")) {
			return false;
		} else {
			String element = "</" + tagPrefix.substring(1, tagPrefix.length());
			return element.matches(tagSuffix);
		}	
	}
	
	private static boolean retry() {
		char first = ' ';
		System.out.print("\nPlay Again? (y/n) ");
		if (scnr.hasNextLine()) {
			first = scnr.next().charAt(0);
		}
		
		while(first != 'y' && first != 'Y' && first != 'n' && first != 'N') {
			System.out.println("What was that?... type 'y' to continue or 'n' to exit");
			if (scnr.hasNext()) {
				first = scnr.next().charAt(0);
			}
		}		
		if (first == 'y' || first == 'Y') {
			scnr.nextLine();
			return true;
		}
		else {
			return false;
		}
	}
	
	private static void exit() {
		System.out.println("GOODBYE!");
	}
	
}
