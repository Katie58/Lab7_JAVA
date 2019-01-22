package lab7;
import java.util.Scanner;

public class RegEx {
static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean retry = true;
		greeting();
		while (retry) {
			String[] dataSet = { "Name", "email", "phone number", "date", "HTML elements" };
			boolean valid;
			
			do {
				valid = validateName(userInput(dataSet[0]));
				validate(valid, dataSet[0]);
			} while (!valid);
			
			do {
				valid = validateEmail(userInput(dataSet[1]));
				validate(valid, dataSet[1]);
			} while (!valid);

			do {
				valid = validatePhone(userInput(dataSet[2]));
				validate(valid, dataSet[2]);
			} while (!valid);
			
			do {
				valid = validateDate(userInput(dataSet[3]));
				validate(valid, dataSet[3]);
			} while (!valid);
			
			do {
				valid = validateHtml(userInput(dataSet[4]));
				validate(valid, dataSet[4]);
			} while (!valid);
			
			retry = retry();
		}
		exit();
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
	
	private static void validate(boolean valid, String data) {
		if (valid) {
			System.out.println(data + " is valid!");
		} else {
			System.out.println("Sorry, " + data + " is not valid!");
		}
	}
	
//	private static void valid(String[] dataSet) {	
//		String[] input = new String[5];
//		for (int i = 0; i < 5; i++) {
//			input[i] = userInput(dataSet[i]);
//			String[] methods = { validateName(dataSet[i]), validateEmail(dataSet[i]), validatePhone(dataSet[i]), validateDate(dataSet[i]), validateHtml(dataSet[i]) };
//		}
//			boolean valid = validateName(userInput(data));
//			if (valid) {
//				System.out.println(data + " is valid!");
//			} else {
//				System.out.println("Sorry, " + data + " is not valid!");
//			}	
//		}
//	}
	
	private static boolean validateName(String name) {
		return name.matches("[A-Z]{1}[A-z]{0,30}");
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
	
	private static boolean validateDate(String date) {
		return date.matches("[0-3]{1}[0-9]{1}/[0-1]{1}[0-9]{1}/[1-2]{1}[09]{1}[0-9]{2}");
		/////dd/mm/yyyy
	}
	
	private static boolean validateHtml(String html) {
		String[] splitStr = html.trim().split("\\s+");
		if (!splitStr[0].matches("<[\\w$]+>")) {
			for (String word : splitStr) {
				System.out.print("( " + word + " )");
			}
			return false;
		} else {
			String element = "</" + splitStr[0].substring(1, splitStr[0].length());
			if (element.matches(splitStr[1])) {
				return true;
			} else {
				System.out.println("( " + element + " ) : ( " + splitStr[1] + " )");
				return false;
			}
		}
		//return html.matches("<[a-z]+[\\w$]>");
		/////</p> jhdjfkhoadhf  </p> is valid
		/////<h1 </h1> is not valid
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
