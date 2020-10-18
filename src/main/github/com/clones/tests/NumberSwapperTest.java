package github.com.clones.tests;

import github.com.clones.tests.playground.NumberHelper;
import github.com.clones.tests.playground.NumberSwapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberSwapperTest {

    @Test
    void swapNumbersUsingNumberSwapper() {
        var numberSwapper = new NumberSwapper(10, 20);
        numberSwapper.swap();

        assertEquals(10, numberSwapper.getSecondNumber());
        assertEquals(20, numberSwapper.getFirstNumber());
    }

    @Test
    void swapNumbersUsingNumberHelper() {
        var result = NumberHelper.swap(10, 20);

        assertEquals(10, result.getSecondNumber());
        assertEquals(20, result.getFirstNumber());
    }
}
