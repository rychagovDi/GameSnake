package snake;

import java.awt.*;

import static snake.Constants.BLOCK_SIZE;

public class SnakeBlock {

  private int x;
  private int y;
  private int color;
  private String way;

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

  String getWay() {
    return way;
  }

  void setWay(String way) {
    this.way = way;
  }

  int getColor() {
    return color;
  }

  void setColor(int color) {
    this.color = color;
  }

  void move() {
    switch (way) {
      case "right":
        x++;
        break;
      case "left":
        x--;
        break;
      case "up":
        y--;
        break;
      case "down":
        y++;
        break;
    }

  }

  void paint(Graphics g) {
    g.setColor(new Color(color));
    g.fillRect(x * BLOCK_SIZE + 1, y * BLOCK_SIZE + 1, BLOCK_SIZE, BLOCK_SIZE);
  }

  void paint(Graphics g, int xShift, int yShift) {
    g.setColor(new Color(color));
    g.fillRect(x * BLOCK_SIZE + 1 + xShift, y * BLOCK_SIZE + 1 + yShift, BLOCK_SIZE, BLOCK_SIZE);
  }
}
