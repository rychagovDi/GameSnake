package snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static snake.Constants.*;

public class FoodBlock {

  private int x;
  private int y;
  private int color;
  private final Random random;

  FoodBlock(AllSnake allSnake) {
    this.random = new Random();
    newFood(allSnake);
  }

  int getX() {
    return x;
  }

  void setX(int x) {
    this.x = x;
  }

  int getY() {
    return y;
  }

  void setY(int y) {
    this.y = y;
  }

  int getColor() {
    return color;
  }

  void setColor(int color) {
    this.color = color;
  }

  private void newFood(AllSnake allSnake) {

    while (!isRightPos(allSnake)) {
      setX(random.nextInt(FIELD_WIDTH - BLOCK_SIZE));
      setY(random.nextInt(FIELD_HEIGHT - BLOCK_SIZE));
      setColor(COLOR_OF_FOOD);
    }
  }

  private boolean isRightPos(AllSnake allSnake) {

    ArrayList<SnakeBlock> snake = allSnake.getSnakeList();
    for (SnakeBlock snakeBlock : snake) {
      if (snakeBlock.getX() == x & snakeBlock.getY() == y) {
        return false;
      }
    }
    return !(x <= 0 || y <= 0);
  }

  void paint(Graphics g) {
    g.setColor(new Color(color));
    g.fillRect(x * BLOCK_SIZE + 1, y * BLOCK_SIZE + 1, BLOCK_SIZE, BLOCK_SIZE);
  }
}
