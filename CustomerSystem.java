/**
 * Date: April 8th, 2021
 * Names: Marc Fernandes and Ryan Mah
 * Teacher: Mr. Ho
 * Description: A system that lets a user enter and verify data.
 */

// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.Scanner;
// More packages may be imported in the space below
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.NumberFormatException;
import java.util.Random;

class CustomerSystem{
    public static void main(String[] args) throws FileNotFoundException{
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below

        String allUserData = "";

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
                generateCustomerDataFile(allUserData);
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

    /**
     * Lets the user enter their info.
     * 
     * @return All the data that the user put in.
     */
    public static String enterCustomerInfo() throws FileNotFoundException{
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

        String allUserData = firstName.concat(",").concat(lastName).concat(",").concat(city).concat(",").concat(postalCode).concat(",").concat(creditCardNumber); 
        return allUserData;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */

    /**
     * Validate postal code inputted though the method, then determines valid or not, returns it back to enterCustomerInfo() method
     * 
     * @param String postalCode - String of users inputted postal code
     * @throws FileNotFoundException
     * @return - True if card is valid
     * @return - False if card is invalid
    */
    public static boolean validatePostalCode(String postalCode) throws FileNotFoundException{
        // Reinitialize a scanner since I am asking for an input
        // Can't close reader, closes all scanners
        Scanner files = new Scanner(System.in);
        // Making sure postal code file is created and in same folder as this program
        System.out.println("Make sure you have the postal codes file on your system and in same folder as this program in order to verify");
        // Create the text file and location of where the postal code chart is in 
        // before the verifying of the postal code, since I can't hardcode it, and 
        //  this is my computer so you won't have my file
        System.out.println("Enter file name and location (eg. C:\\Users\\Ryan Mah\\Desktop\\SAM\\customer-system\\postalCC)");
        String fileNameLocation = files.nextLine(); // where user inputs file name and location
        // Create a file instance to reference the text file in java
        File textFile = new File(fileNameLocation + ".txt");

        // We create a scanner instance to read the file in java
        // Scanner reader = new Scanner(System.in)
        Scanner text = new Scanner(textFile);

        // For now its false unless otherwise
        boolean valid=false;

        // If Postal code is more than 2 characters
        if(postalCode.length()>=3) {
          String postal=postalCode.substring(0,3); // Taking the postal codes from charts
          while(text.hasNextLine()) {
            String line = text.nextLine();
            String code = line.substring(0,3);
            // Checking if it contains the Postal codes 
            if(code.contains(postal)){ 
              // Returns True
              valid= true;
            }

          }
          // Close text
          text.close();
        }
         return valid; // Returns False
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */

    /**
     * Verifies the credit card entered in using the Luhn Algorithm.
     * 
     * @param creditCard - A string that contains the credit card number
     * @return A boolean statement depending on if the credit card is valid or not. 
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
            catch (NumberFormatException e) {
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
            catch (NumberFormatException e) {
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

    /**
     * Generates  CSV file of Customer Data with a Unique ID
     *  
     * @param String userData - String of data that was collected
     * @exception IOException (Used twice in catch)
     * @return the file exists or not
    */
    public static void generateCustomerDataFile(String userData)  {
        // Reinitialize without closing
        Scanner data = new Scanner(System.in);
        // For ID numbers
        Random rand = new Random();
        // All random per character for the ID
        int randNum = rand.nextInt(1)+1;
        int randNum2 = rand.nextInt(9);
        int randNum3 = rand.nextInt(9);
        int randNum4 = rand.nextInt(9);
        int randNum5 = rand.nextInt(9);
        int randNum6 = rand.nextInt(9);
        int randNum7 = rand.nextInt(9);
        int randNum8 = rand.nextInt(9);

        // Input File information (Make sure file and this  program are in the same folder)
        System.out.println("Enter file name (eg. C:\\Users\\Ryan Mah\\Desktop\\SAM\\customer-system\\generation)");
        String fileNameLocation = data.nextLine(); // where user inputs file name and location
        File newFile = new File(fileNameLocation + ".csv");
        // If File exists
        System.out.println(newFile.exists());
        // Saying it is generated in the CSV file
        System.out.println("Your information has been generated");
        
        // Creates new file if not already
        try {
            newFile.createNewFile();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        try{
            // What's being generated in the file
            FileWriter fw = new FileWriter(newFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Information");
            bw.newLine();
            bw.write(userData);
            bw.newLine();
            // Unique ID
            bw.write("ID is: " + randNum + randNum2 + randNum3 + randNum4 + randNum5 + randNum6 + randNum7 + randNum8);
            bw.flush();
            bw.close();
        }
        catch (IOException e){ // Catches any Exception
            e.printStackTrace();
        }

    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}