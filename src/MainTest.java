import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void checkIfCoordinateIsValid() {
        assertTrue(Main.isValidCoordinate("(1,1)") );
    }
    @Test
    void checkIfCoordinateIsNOTValid() {
        assertFalse(Main.isValidCoordinate("(1,3)") );
    }
    @Test
    void checkFunctionReturnInt() {
        var number = Main.validateInputIsIntWithText("1");
        assertEquals(1, number);
    }
}