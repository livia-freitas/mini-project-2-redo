package mp2;

import java.math.BigInteger;
import java.io.PrintWriter;


/**
 * 
 * A simple implementation of BigFractions.
 * 
 * 
 * 
 * @author Samuel A. Rebelsky
 * 
 * @author Noah Mendola (for the parts of the MP taken from Lab 6, which we did together)
 * 
 * @author Livia Stein Freitas
 * 
 * @version 1.2 of August 2023
 * 
 */

public class BigFraction {

  // +------------------+---------------------------------------------

  // | Design Decisions |

  // +------------------+

  /*
   * 
   * (1) Denominators are always positive. Therefore, negative BigFractions are represented
   * 
   * with a negative numerator. Similarly, if a BigFraction has a negative numerator, it
   * 
   * is negative.
   * 
   * 
   * 
   * (2) BigFractions are not necessarily stored in simplified form. To obtain a BigFraction
   * 
   * in simplified form, one must call the `simplify` method.
   * 
   */


  // +--------+-------------------------------------------------------

  // | Fields |

  // +--------+


  /** The numerator of the BigFraction. Can be positive, zero or negative. */

  BigInteger num;


  /** The denominator of the BigFraction. Must be non-negative. */

  BigInteger denom;


  // +--------------+-------------------------------------------------

  // | Constructors |

  // +--------------+


  /**
   * 
   * Build a new BigFraction with numerator num and denominator denom.
   * 
   * 
   * 
   * Warning! Not yet stable.
   * 
   */

  public BigFraction(BigInteger num, BigInteger denom) {

    this.num = num;

    this.denom = denom;

  } // BigFraction(BigInteger, BigInteger)


  /**
   * 
   * Build a new BigFraction with numerator num and denominator denom.
   * 
   * 
   * 
   * Warning! Not yet stable.
   * 
   */

  public BigFraction(int num, int denom) {

    this.num = BigInteger.valueOf(num);

    this.denom = BigInteger.valueOf(denom);

  } // BigFraction(int, int)

  /** Build a BigFraction from an integer with no fractional parts */

  public BigFraction(int number) {

    this.num = BigInteger.valueOf(number);

    this.denom = BigInteger.valueOf(1);

  } // BigFraction(int, int)



  /**
   * 
   * Build a BigFraction that gets its value from a BFCalculator register
   * 
   * @param charRegister
   * @param calculator
   * 
   */

  public BigFraction(char charRegister, BFCalculator calculator) {
    this.num = calculator.registers[charRegister].num;
    this.denom = calculator.registers[charRegister].num;
  }

  /**
   * 
   * Build a new BigFraction by parsing a string.
   *
   * 
   * 
   * Warning! Not yet implemented.
   * 
   */



  public BigFraction(String str) {

    int endOfString = str.length();


    if (!str.contains("/")) {

      int strFraction = Integer.parseInt(str);

      this.num = BigInteger.valueOf(strFraction);

      this.denom = BigInteger.valueOf(1);

    } else {
      int slashIndex = str.indexOf('/');

      String num1 = str.substring(0, slashIndex);

      int numInt = Integer.parseInt(num1);

      this.num = BigInteger.valueOf(numInt);

      String num2 = str.substring(slashIndex + 1, endOfString);

      int numInt2 = Integer.parseInt(num2);

      this.denom = BigInteger.valueOf(numInt2);
    }


  } // BigFraction


  // +---------+------------------------------------------------------

  // | Methods |

  // +---------+


  /**
   * 
   * Express this BigFraction as a double.
   * 
   */

  public double doubleValue() {

    return this.num.doubleValue() / this.denom.doubleValue();

  } // doubleValue()


  /**
   * 
   * Reduces a BigFraction to its simplest form.
   * 
   * @return
   * 
   */

  public BigFraction reduce() {

    BigInteger resultNumerator;

    BigInteger resultDenominator;

    BigInteger numerator = this.num;

    BigInteger denominator = this.denom;

    // 8/64 is being

    BigInteger GCD = numerator.gcd(denominator);

    // the numerator of the result is this object's numerator

    // divided by the gcd between the numerator and denominator

    resultNumerator = numerator.divide(GCD);

    // the denominator of the result is this object's denominator

    // divided by the gcd between the numerator and denominator

    resultDenominator = denominator.divide(GCD);

    return new BigFraction(resultNumerator, resultDenominator);
  }

  /**
   * Add the BigFraction `addMe` to this BigFraction.
   */

  public BigFraction add(BigFraction addMe) {

    BigInteger resultNumerator;

    BigInteger resultDenominator;


    // The denominator of the result is the

    // product of this object's denominator

    // and addMe's denominator

    resultDenominator = this.denom.multiply(addMe.denom);

    // The numerator is more complicated

    resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    BigFraction result = new BigFraction(resultNumerator, resultDenominator);
    // Return the computed value

    return result.reduce();

  }// add(BigFraction)


  /**
   * 
   * Multiply two BigFractions.
   * 
   * @param multiplyMe
   * 
   * @return
   * 
   */

  public BigFraction multiply(BigFraction multiplyMe) {

    BigInteger resultNumerator;

    BigInteger resultDenominator;

    // The numerator of the result is the

    // product of this object's and multiplyMe's numerators.

    resultNumerator = this.num.multiply(multiplyMe.num);

    // The denominator of the result is the

    // product of this object's and multiplyMe's denominators.

    resultDenominator = this.denom.multiply(multiplyMe.denom);

    BigFraction result = new BigFraction(resultNumerator, resultDenominator);
    // Return the computed value

    return result.reduce();

  }

  /**
   * 
   * Divide one BigFraction by another.
   * 
   * @param divideMe
   * 
   * @return
   * 
   */
  public BigFraction divide(BigFraction divideMe) {

    BigInteger resultNumerator;

    BigInteger resultDenominator;

    // The numerator of the result is the

    // product of this object's numerator and divideMe's denominator

    resultNumerator = this.num.multiply(divideMe.denom);

    // The denominator of the result is the

    // product of this object's denominator and divideMe's numerator

    resultDenominator = this.denom.multiply(divideMe.num);

    BigFraction result = new BigFraction(resultNumerator, resultDenominator);
    // Return the computed value

    return result.reduce();

  }

  /**
   * 
   * Subtracts one BigFraction from another.
   * 
   * @param subtractMe
   * 
   * @return
   * 
   */

  public BigFraction subtract(BigFraction subtractMe) {

    BigInteger resultNumerator;

    BigInteger resultDenominator;

    // The denominator of the result is the

    // product of this object's denominator

    // and subtractMe's denominator

    resultDenominator = this.denom.multiply(subtractMe.denom);

    // The numerator of the result is

    // this object's numerator multiplied by subtractMe's denominator

    // minus subtractMe's numerator multiplied by this object's denominator

    resultNumerator =
        (this.num.multiply(subtractMe.denom)).subtract(subtractMe.num.multiply(this.denom));

    BigFraction result = new BigFraction(resultNumerator, resultDenominator);
    // Return the computed value

    return result.reduce();

  }



  public BigFraction calculate(String operator, BigFraction calculateMe) {
    if (operator.equals("+")) {
      return this.add(calculateMe);
    } else if (operator.equals("-")) {
      return this.subtract(calculateMe);
    } else if (operator.equals("*")) {
      return this.multiply(calculateMe);
    } else if (operator.equals("÷")) {
      return this.divide(calculateMe);
    } else {
      return null;
    }
  }

  public BigFraction fractional() {

    BigInteger resultNumerator;

    BigInteger resultDenominator;


    resultNumerator = this.num.mod(this.denom);

    resultDenominator = this.denom;

    BigFraction result = new BigFraction(resultNumerator, resultDenominator);
    // Return the computed value

    return result.reduce();

  }



  /**
   * 
   * Get the denominator of this BigFraction.
   * 
   */

  public BigInteger denominator() {

    return this.denom;

  } // denominator()



  /**
   * 
   * Get the numerator of this BigFraction.
   * 
   */

  public BigInteger numerator() {

    return this.num;

  } // numerator()



  /**
   * 
   * Convert this BigFraction to a string for ease of printing.
   * 
   */

  public String toString() {

    // Special case: It's zero

    if (this.num.equals(BigInteger.ZERO)) {

      return "0";

    } // if it's zero


    // Lump together the string represention of the numerator,

    // a slash, and the string representation of the denominator

    // return this.num.toString().concat("/").concat(this.denom.toString());

    return this.num + "/" + this.denom;

  } // toString()


  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {


    PrintWriter pen = new PrintWriter(System.out, true);

    BigFraction e = new BigFraction("2");

    BigFraction m = new BigFraction("1");

    BigFraction n = new BigFraction("3/9");

    pen.println("TESTS: ");
    pen.println(e);
    pen.println(m);
    pen.println("2 + 3/9 = " + e.add(n));
    pen.println("2 divided by 3/9 = " + e.divide(n));
    pen.println("2 minus 1 = " + e.subtract(m));
    pen.println("3/9 simplified is: " + n.reduce());
    pen.println("1 times 2 is: " + m.calculate("*", e));



  }// main

} // class BigFraction
