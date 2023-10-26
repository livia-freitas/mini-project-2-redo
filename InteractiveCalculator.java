package mp2;
import java.util.Scanner;  
import java.io.PrintWriter;

/**
 * @author Livia Stein Freitas
 * Project: Mini-Project 2, "Fun With Fractions", for CSC-207 taught by Samuel Rebelsky
 * 
 * An interface for a calculator that continuously takes input from the user, and returns
 * then result of the mathematical expressions inputted, evaluated left-to-right.
 */

public class InteractiveCalculator {
  public static void main (String[] args) throws Exception{
    try (Scanner myScanner = new Scanner(System.in)) {
      PrintWriter pen = new PrintWriter(System.out, true);
      BFCalculator calculator = new BFCalculator(); 

      pen.println("\nPlease enter a mathematical expression. There should be one whitespace before and after each operator.\n");
      pen.println("Use symbols:\n");
      pen.println("+ for addition\n");
      pen.println("- for subtraction\n");
      pen.println("* for multiplication\n");
      pen.println("÷ for division\n");
      pen.println("Write QUIT to stop the program.\n");

      

      while(true){
        String userInput = myScanner.nextLine();           
        if (userInput.equals("QUIT")){
          break;
        }
        
        if (userInput.contains("STORE")){
         int charIndex = 6;
         char charRegister = userInput.charAt(charIndex);
         calculator.store(charRegister);
         //end of things I modified in laptop
         pen.println("\n");
         continue;
        }          
        pen.println("\n->" + calculator.evaluate(userInput) + "\n");
      }
    }
  }
}
