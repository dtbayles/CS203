package edu.byu.cs203.junit.advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SimpleTest")
class SimpleTest {

  private Simple simple = null;

  @BeforeEach
  void setup() {
    simple=new Simple();
  }

  @ParameterizedTest(name=("Adding {0} + {1}"))
  @CsvSource({"0,0", "-4,-20", "20,2", "-4,5", "4,-6"})
  @DisplayName("Example for Add")
  void add(int number1, int number2) {
    assertEquals(number1+number2, simple.add(number1, number2));
  }

  @ParameterizedTest(name=("Subtracting {0} - {1}"))
  @CsvSource({"0,0", "-4,-20", "20,2", "-4,0", "4,-6"})
  @DisplayName("Example for Subtract")
  void subtract(int number1, int number2) {
    assertEquals(number1-number2, simple.subtract(number1, number2));
  }

  @ParameterizedTest(name=("Multiplying {0} * {1}"))
  @CsvSource({"0,0", "-4,-20", "20,2", "-4,5", "4,-6"})
  @DisplayName("Example for Multiply")
  void multiply(int number1, int number2) {
    assertEquals(number1*number2, simple.multiply(number1, number2));
  }

  @ParameterizedTest(name=("Dividing {0} / {1}"))
  @CsvSource({"-20,-4,5", "20,2,10", "-4,2,-2", "4,-2,-2"})
  @DisplayName("Example for Divide")
  void divide(int number1, int number2, int expected) {
      assertEquals(expected, simple.divide(number1, number2));
  }

  @ParameterizedTest(name=("Dividing {0} / {1}"))
  @CsvSource({"0,0"})
  @DisplayName("Example for Divide by 0")
  void divide(int number1, int number2) {
    Exception exception = assertThrows(ArithmeticException.class, () -> {
      simple.divide(number1,number2);
    });
  }

  @ParameterizedTest(name=("{0} to the power of {1}"))
  @CsvSource({"0,0", "-4,-20", "20,2", "-4,5", "4,-6"})
  @DisplayName("Example for Power")
  void power(int number1, int number2) {
    int result = number1;
    for (int i = 1; i < number2; i++) {
      result *= result;
    }
    assertEquals(result, simple.power(number1, number2));
  }
}