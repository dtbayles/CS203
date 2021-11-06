import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HarderCoverageFunctionsTest {
    HarderCoverageFunctions testClass;

    @BeforeEach
    public void setUp() throws Exception {
        testClass = new HarderCoverageFunctions();
    }

    @Test
    public void castSpellsTest() {
        String result = testClass.castSpells(0, 0, 0, 0);
        assertEquals("You win!", result);
        result = testClass.castSpells(1, 0, 0, 0);
        assertEquals("Monster is still alive", result);
        result = testClass.castSpells(0, 0, 0, 5);
        assertEquals("The spell fizzles in front of you.", result);
        result = testClass.castSpells(0, 2, 0, 5);
        assertEquals("The spell fizzles in front of you.", result);
        result = testClass.castSpells(0, 2, 2, 5);
        assertEquals("The spell destroys the monster!", result);
        result = testClass.castSpells(4, 2, 2, 5);
        assertEquals("The spell zaps the monster!", result);
    }


    @Test
    public void truthFinderTest(){
        String result = testClass.truthFinder(true, true, true);
        assertEquals("YOU FOUND THE TRUTH!", result);
        result = testClass.truthFinder(true, false, true);
        assertEquals("Well I guess that is true too", result);
        result = testClass.truthFinder(true, false, false);
        assertEquals("Well that might be true", result);
        result = testClass.truthFinder(false, false, false);
        assertEquals("Are you even trying to find the truth?", result);
        result = testClass.truthFinder(false, true, false);
        assertEquals("Well that might be true", result);
    }
}