import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HippodromeTest {
    private Random random = new Random();

    @Test
    void nullExceptionHippodrome() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Horses cannot be null.");
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void emptyListExceptionHippodrome() {
        List<Horse> nullList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(nullList));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Horses cannot be empty.");
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void move() {
        List<Horse> mockList = new ArrayList<>();
        int numberOfHorses = 50;

        for (int i = 0; i < numberOfHorses; i++) {
            mockList.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockList);
        hippodrome.move();

        for (Horse horse : mockList) {
            Mockito.verify(horse).move();
        }

    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("1", 1, 2);
        Horse horse2 = new Horse("2", 2, 3);
        Horse horse3 = new Horse("3", 3, 4);
        Horse horse4 = new Horse("4", 4, 5);
        Hippodrome hippodrome=new Hippodrome(List.of(horse1, horse2, horse3, horse4));
        assertSame(horse4,hippodrome.getWinner());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();

        int numberOfHorses = 30;

        for (int i = 0; i < numberOfHorses; i++) {
            String name = "Horse " + (i + 1);
            double speed = random.nextDouble() * 20;
            double distance = random.nextDouble() * 100;

            Horse horse = new Horse(name, speed, distance);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }
}