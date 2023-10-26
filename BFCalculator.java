package mp2;
import java.util.regex.Pattern;

/**
 * @author Livia Stein Freitas
 * Project: Mini-Project 2, "Fun With Fractions", for CSC-207 taught by Samuel Rebelsky
 * 
 * A calculator class that takes in string mathematical expressions, evaluates them,
 * returns the result, and stores the last result upon request.
 */

public class BFCalculator {
  
  BigFraction[] registers; 
  BigFraction lastValue;

  public BFCalculator(){
    this.registers = new BigFraction[26];
  }

  /**
   * Stores the result computed by the calculator.
   * 
   * @param register
   */
  public void store(char register){
    int numRegister = (int) register - 97;
    registers[numRegister] = lastValue;
  }
 
  /**
   * Takes in a mathematical expression and returns the result
   * @param exp
   * @return
   */
  public BigFraction evaluate(String exp){
   
    Pattern operators = Pattern.compile("(\\s\\+\\s|\\s\\*\\s|\\s\\-\\s|\\s\\÷\\s)");
    Pattern digits = Pattern.compile("\\p{Alnum}\\/\\p{Alnum}\\s|\\s\\p{Alnum}\\/\\p{Alnum}\\s|\\s\\p{Alnum}\\/\\p{Alnum}|\\p{Alnum}\\s|\\s\\p{Alnum}\\s|\\s\\p{Alnum}"); 

    String[] argumentArray = operators.split(exp);
    //issue
    int numArgs = argumentArray.length;
    String[] operatorArray = digits.split(exp);
    int i = 0;
    BigFraction result = new BigFraction("0/1");
    BigFraction[] newArgumentArray = new BigFraction[numArgs];
    
    while (i < numArgs){ //change strings into BigFractions and gets the value of registers
      boolean isAlpha =  Pattern.matches("\\p{Lower}", argumentArray[i]);
      if(isAlpha){
        char[] tempCharArray = argumentArray[i].toCharArray();
        char charReg = tempCharArray[0];
        newArgumentArray[i] = registers[charReg - 97];
      } else {
        newArgumentArray[i] = new BigFraction(argumentArray[i]);
      }
      i++;
    }
    
    i = 0; //resetting i

    BigFraction argOne = (newArgumentArray[i]);
     
     i = 0;
     
    while(i + 1 < numArgs){
      String currentOperator = operatorArray[i + 1];
      BigFraction argTwo = (newArgumentArray[i + 1]);
     result = argOne.calculate(currentOperator, argTwo); 
     argOne = result;
     i++;
    } 
    
    this.lastValue = result;
    return result;
  }
  
}
