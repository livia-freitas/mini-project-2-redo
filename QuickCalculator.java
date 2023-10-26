package mp2; 
import java.io.PrintWriter;

/**
 * @author Livia Stein Freitas
 * Project: Mini-Project 2, "Fun With Fractions", for CSC-207 taught by Samuel Rebelsky
 * 
 * An interface for a calculator that takes expressions in the form of a string from the command line,
 * then evaluates them left-to-right (no priority.)
 */

 public class QuickCalculator {
  /**
   * @param args
   * @throws Exception
   */
  public static void main (String[] args) throws Exception{     
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calculator = new BFCalculator(); 

    pen.println("\nPlease enter a mathematical expression. There should be one whitespace before and after each operator.\n");
    pen.println("Use symbols:\n");
    pen.println("+ for addition\n");
    pen.println("- for subtraction\n");
    pen.println("* for multiplication\n");
    pen.println("÷ for division\n");
    pen.println("Write QUIT to stop the program.\n");

    for(String s: args){
      if (s.contains("STORE")){
        int storeIndex = s.lastIndexOf("STORE");
        int charIndex = storeIndex + 6;
        char charRegister = s.charAt(charIndex);
        calculator.store(charRegister);
        continue;
      }//if(s.contains"STORE")
      pen.println("\n->" + calculator.evaluate(s) + "\n");           
    } //for(String s: args)
  } // main()
} // QuickCalculator
