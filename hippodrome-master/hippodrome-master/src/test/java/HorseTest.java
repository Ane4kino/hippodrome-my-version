import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    Horse horse = new Horse("My Horse", 10.0, 5.5);
    @Test
    void getName() {
        assertEquals("My Horse", horse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(5.5, horse.getDistance());
        Horse horse2 = new Horse("Name", 10.0);
        assertNotNull(horse2.getDistance());
    }

    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}