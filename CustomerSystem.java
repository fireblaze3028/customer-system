// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.Scanner;
// More packages may be imported in the space below

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below

        String allUserData;

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
                // Any necessary variables may be added to this if section, but nowhere else in the code
                allUserData = enterCustomerInfo();

            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static String enterCustomerInfo() {
        // Initialise scanner
        Scanner customerReader = new Scanner(System.in);

        // Ask the user for their first and last name, city, postal code, and credit card number
        System.out.println("Please enter your first name:");
        String firstName = customerReader.nextLine();

        System.out.println("Please enter your last name:");
        String lastName = customerReader.nextLine();

        System.out.println("Please enter your city:");
        String city = customerReader.nextLine();

        System.out.println("Please enter the postal code:");
        String postalCode = customerReader.nextLine();

        // If the postal code is invalid
        while (validatePostalCode(postalCode) == false) {
            System.out.println("That postal code isn't valid. Please try again:");
            postalCode = customerReader.nextLine();
        }

        System.out.println("Please enter your credit card number:");
        String creditCardNumber = customerReader.nextLine();

        // If the credit card is invalid
        while (validateCreditCard(creditCardNumber) == false) {
            System.out.println("That credit card number isn't valid. Please try again:");
            creditCardNumber = customerReader.nextLine();
        }

        // Cannot close the scanner since it closes all other scanners

        String allUserData = firstName.concat(", ").concat(lastName).concat(", ").concat(city).concat(", ").concat(postalCode).concat(", ").concat(creditCardNumber); 
        return allUserData;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static boolean validatePostalCode(String postalCode){ 
        // This is temporary
        return true;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static boolean validateCreditCard(String creditCard){
        // Initialise variables
        int sum1 = 0;
        int sum2 = 0;
        int addBy = 0;
        String creditCardReversed = "";

        // If the credit card has less than 9 digits
        if (creditCard.length() < 9) {
            return false;
        }
        
        // Reverse the credit card
        for (int i = 0; i < creditCard.length(); i++) {
            creditCardReversed += creditCard.charAt((creditCard.length()-1)-i);
        }

        // Add the odd numbers for the credit card
        for (int i = 0; i < creditCardReversed.length(); i += 2) {
            // Try to add the odd numbers if they are numbers
            try {
                sum1 += Integer.parseInt(creditCardReversed.substring(i,i+1));
            }
            // Return false if the character isn't a number
            catch (Exception e) {
                return false;
            }
        }
        // Double the even numbers and add them together
        for (int i = 1; i < creditCardReversed.length(); i += 2) {
            // Try to add the even numbers if they are numbers
            try {
                addBy += Integer.parseInt(creditCardReversed.substring(i, i+1));
            }
            // Return false if the character isn't a number
            catch (Exception e) {
                return false;
            }
            // Double the integer
            addBy *= 2;
            // If the integer is 10 or more, add the individual digits together
            if (addBy > 9) {
                addBy = (addBy % 10) + (addBy/10); 
            }
            // Increment sum2 by addBy
            sum2 += addBy;
            // Reset addBy so it calculates sum2 correctly
            addBy = 0;
        }
        // If the last digit has a 0, return true
        if ((sum1 + sum2) % 10 == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(){
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}