package comp228.kj.wk4.lab2;

// Import Scanner for reading user input and BigDecimal for math
import java.math.BigDecimal;
import java.util.Scanner;

// Create MainDriver class
public class MainDriver {

    public static void main(String[] args) {

        // Create scanner object to get user input from keyboard
        Scanner scanner = new Scanner(System.in);

        // Create an array of 5 objects
        Interest[] investments = new Interest[5];

        System.out.println("INTEREST CALCULATION");

        // Counter for loop
        int count = 0;

        // Loop 5 times to create 5 objects
        while (count < 5) {
            System.out.println("\nEnter details for Investment #" + (count + 1) + ":");

            try {
                // Gets input from user for principal
                System.out.print("Enter Principal: ");
                String pInput = scanner.nextLine().trim();

                // Checks if user entered a whole integer without decimals
                if (pInput.matches("^\\d+$")) {
                    throw new IllegalArgumentException("Principal must be a decimal number, not whole integer.");
                }

                // Gets input from user for rate
                System.out.print("Enter Rate: ");
                String rInput = scanner.nextLine().trim();

                // Checks if user entered a whole integer without decimals
                if (rInput.matches("^\\d+$")) {
                    throw new IllegalArgumentException("Rate must be a decimal/floating-point number, not a pure integer.");
                }

                // Gets input from user for time
                System.out.print("Enter Time in years: ");
                String tInput = scanner.nextLine().trim();

                // Convert inputs to numbers
                int time = Integer.parseInt(tInput);
                BigDecimal principal = new BigDecimal(pInput);
                BigDecimal rate = new BigDecimal(rInput);

                // Create object and store in array
                investments[count] = new Interest(principal, rate, time);
                
                // Increase counter
                count++; 

            } catch (NumberFormatException e) {
                // Shows error message for invalid characters
                System.out.println("Error: Input contains invalid characters.");
            } catch (IllegalArgumentException e) {
                // Shows error message
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Loops through array to display output for all 5 objects
        System.out.println("\nRESULTS");
        for (int i = 0; i < investments.length; i++) {
            Interest inv = investments[i];

            System.out.println("\nInvestment #" + (i + 1));
            
            // Call overloaded methods with BigDecimal
            BigDecimal siBD = inv.calculateSimpleInterest(inv.getPrincipal(), inv.getRate(), inv.getTime());
            BigDecimal ciBD = inv.calculateCompoundInterest(inv.getPrincipal(), inv.getRate(), inv.getTime());

            // Call overloaded methods with double
            double siDouble = inv.calculateSimpleInterest(inv.getPrincipal().doubleValue(), inv.getRate().doubleValue(), inv.getTime());
            double ciDouble = inv.calculateCompoundInterest(inv.getPrincipal().doubleValue(), inv.getRate().doubleValue(), inv.getTime());

            // Print calculated values rounded to 2 decimal places
            System.out.printf("Simple Interest(BD): $%.2f\n", siBD);
            System.out.printf("Simple Interest(Double): $%.2f\n", siDouble);
            System.out.printf("Compound Interest(BD): $%.2f\n", ciBD);
            System.out.printf("Compound Interest(Double): $%.2f\n", ciDouble);
        }

        // Close scanner
        scanner.close();
    }
}
