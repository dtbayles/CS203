package edu.byu.cs203.junit.advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ComplexTest")
class ComplexTest {

  private Complex complex = null;

  @BeforeEach
  void setup() {
    complex=new Complex();
  }

  @Nested
  @DisplayName("Factorial Tests")
  class factorial {
    @ParameterizedTest(name=("Factorial of {0}"))
    @CsvSource({"0,0", "2,2", "5,120"})
    @DisplayName("Example for Factorial")
    void factorial(long number, long expected) {
      assertEquals(expected, complex.factorial(number));
    }

    @ParameterizedTest(name=("Factorial of {0}"))
    @CsvSource({"-20"})
    @DisplayName("Example for Factorial of a Negative Number")
    void factorialOfNegativeNumber(long number) {
      Exception e=assertThrows(ArithmeticException.class, () -> {
        complex.factorial(number);
      });
    }
  }

  @Nested
  @DisplayName("Square Root Tests")
  class squareRoot {
    @ParameterizedTest(name=("Square Root of {0}"))
    @CsvSource({"1,1", "2.0,1.414213562373095", "25000000,5000.0"})
    @DisplayName("Example for Square Root")
    void squareRoot(double number, double expected) {
      assertEquals(expected, complex.squareRoot(number));
    }

    @ParameterizedTest(name=("Square Root of {0}"))
    @CsvSource({"-20.0,-9999999999.0"})
    @DisplayName("Example for Square Root of Negative Numbers or NaN Values")
    void squareRootOfNegativeNumber(double number) {
      assertEquals(Double.NaN, complex.squareRoot(number));
    }

    @Test
    @DisplayName("Example for Square Root of Positive Infinity")
    void squareRootOfPositiveInfin() {
      assertEquals(Double.POSITIVE_INFINITY, complex.squareRoot(Double.POSITIVE_INFINITY));
    }

    @ParameterizedTest(name=("Square Root of {0}"))
    @CsvSource({"0.0, 0.0"})
    @DisplayName("Example for Square Root of 0")
    void squareRootOf0(double number, double expected) {
      assertEquals(expected, complex.squareRoot(number));
    }
  }

  @Nested
  @DisplayName("Natural Log Tests")
  class lnTests {
    @ParameterizedTest(name=("Natural log of {0}"))
    @CsvSource({"20,2.9957322218850715", "1.0,0.0", "0.01,-4.605170", "1000000.0,13.8155"})
    @DisplayName("Example for ln")
    void ln(double number, double expected) {
      assertEquals(expected, complex.ln(number), 0.00005);
    }

    @ParameterizedTest(name=("Natural log of {0}"))
    @CsvSource({"0"})
    @DisplayName("Example for ln of 0")
    void lnOf0(double number) {
      assertEquals(Double.NEGATIVE_INFINITY, complex.ln(number));
    }

    @ParameterizedTest(name=("Natural log of {0}"))
    @CsvSource({"-1.0", "-9999999999.0"})
    @DisplayName("Example for ln of Negative Numbers")
    void lnOfNegativeNumbers(double number) {
      assertEquals(Double.NaN, complex.ln(number));
    }
  }
}