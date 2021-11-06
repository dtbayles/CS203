import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleCoverageFunctionsTest {
    SimpleCoverageFunctions testClass;

    @BeforeEach
    public void setUp() throws Exception {
        testClass = new SimpleCoverageFunctions();
    }

    @Test
    public void addTwoNumTest(){
        int sum = testClass.addTwoNum(1, 2);
        assertEquals(3, sum);
    }

    @Test
    void returnLargest() {
        int largest = testClass.returnLargest(5, 2);
        assertEquals(5, largest);
        largest = testClass.returnLargest(2, 5);
        assertEquals(5, largest);
    }

    @Test
    void doWeirdStuff() {
        int weirdResult = testClass.doWeirdStuff(1, 2, 3);
        assertEquals(2, weirdResult);
        weirdResult = testClass.doWeirdStuff(2, 1, 3);
        assertEquals(0, weirdResult);
        weirdResult = testClass.doWeirdStuff(3, 2, 2);
        assertEquals(2, weirdResult);
    }
}