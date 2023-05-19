import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void getHorses() {
      Throwable exception = assertThrows(IllegalArgumentException.class, ()->{
          throw new IllegalArgumentException("Exception message");});
      assertEquals("Exception message",exception.getMessage());
    }

    @Test
    void move() {
    }

    @Test
    void getWinner() {
    }
}