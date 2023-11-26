import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void checkIfCoordinateIsValid() {
        assertTrue(Main.isValidCoordinate("(1,1)") );
    }
    @Test
    void checkIfCoordinateIsNOTValid() {
        assertFalse(Main.isValidCoordinate("(1,3)") );
    }

}