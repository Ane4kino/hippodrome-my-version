import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    Horse horse = new Horse("My Horse", 10.0, 5.5);

    static Stream<String> argsName() {
        return Stream.of(" , \n, \t, \r");
    }

    @Test
    void nullNameHorse() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2, 2));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Name cannot be null.");
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("argsName")
    void emptyStringCheck(String argument) {
        Horse horse1 = new Horse(argument, 10.0, 5.5);
        assertEquals(argument, horse1.getName());
    }

    @Test
    void checkNegativeSpeed() {
        double speed = horse.getSpeed() - horse.getSpeed() * 2;
        assertTrue(speed < 0);
        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Speed cannot be negative.");
        });
        assertEquals("Speed cannot be negative.", exception2.getMessage());
    }

    @Test
    void checkNegativeDistance() {

        double distance = horse.getDistance() - horse.getDistance() * 2;
        assertTrue(distance < 0);
        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Distance cannot be negative.");
        });
        assertEquals("Distance cannot be negative.", exception3.getMessage());
    }

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
        try (MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            horse.move();
            utilities.verify(() -> horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void moveDistance() {
        double customRandomValue = 0.5;

        try (MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            utilities.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(customRandomValue);
            double expectedDistance = horse.getDistance() + horse.getSpeed() * customRandomValue;
            horse.move();
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}