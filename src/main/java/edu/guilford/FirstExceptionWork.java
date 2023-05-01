package edu.guilford;

import java.util.Scanner;

public class FirstExceptionWork {
    public static void main( String[] args ) //throws BadNumberException
    {
        Scanner scan = new Scanner(System.in);
        String entered = "";
        int n = 0;
        boolean valid = false; // did the user enter correct content?
        do { // a do loop is guaranteed to run at least once; a while loop may never run at all
            // difference between checking the condition at the end of the loop (for do)
            // and at the beginning of the loop (for while)
            try { // try the following code
                System.out.println("Enter an integer: ");
                entered = scan.next(); // next gets the next token (not the whole line of input including the end of line)
                n = Integer.parseInt(entered); // try to convert the string to an integer
                if (n == 0) {
                    throw new BadNumberException("Bad number " + n);
                }
                valid = true; // we want to get here because it worked
            } catch (NumberFormatException ex) {
                // print out the stack trace for more detailed debuggin
                ex.printStackTrace();
                System.err.println("Input was not an integer " + entered);
                System.err.flush(); // flush the error stream or buffer so that the 
                // error message is printed before the next prompt
            } catch (BadNumberException ex) {
                ex.printStackTrace();
                System.exit(1); // normal exit is 0; 1 means there was an error
                // and it's possible to see other error codes as well
            }
        } while (!valid);

        System.out.println("The user entered " + n + "; valid = " + valid);
    }

    // We can write our own exception classes
    private static class BadNumberException extends Exception {
        // All we need in this class is a constructor that tells what is supposed to happen
        // when this exception is thrown
        public BadNumberException(String message) {
            // our constructor has one parameter: the message we want to send when this exception is thrown
            super(message);
        }
    }
}
