package snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import static snake.Constants.*;

public class AllSnake {

  private ArrayList<SnakeBlock> snake = new ArrayList<>();
  private ArrayList<String> rotateList = new ArrayList<>();
  private SnakeBlock head = new SnakeBlock();

  AllSnake() {
    head.setX(START_POSITION);
    head.setY(START_POSITION);
    head.setWay(START_WAY);
    head.setColor(COLOR_OF_HEAD);
    snake.add(head);
    for (int i = 0; i < START_LENGTH - 1; i++) {
      addBlock();
    }
  }

  ArrayList getSnakeList() {
    return snake;
  }

  int getSnakeLenght() {
    return snake.size();
  }

  void move() {
    for (SnakeBlock snakeBlock : snake) {
      snakeBlock.setWay(whereToTurn(snakeBlock));
      snakeBlock.move();
    }
  }

  void addBlock() {

    SnakeBlock block = new SnakeBlock();
    int tailX = snake.get(snake.size() - 1).getX();
    int tailY = snake.get(snake.size() - 1).getY();
    String tailWay = snake.get(snake.size() - 1).getWay();

    switch (snake.get(snake.size() - 1).getWay()) {
      case "right":
        block.setX(tailX - 1);
        block.setY(tailY);
        block.setWay(tailWay);
        block.setColor(COLOR_OF_BODY);
        snake.add(block);
        break;
      case "left":
        block.setX(tailX + 1);
        block.setY(tailY);
        block.setWay(tailWay);
        block.setColor(COLOR_OF_BODY);
        snake.add(block);
        break;
      case "up":
        block.setX(tailX);
        block.setY(tailY + 1);
        block.setWay(tailWay);
        block.setColor(COLOR_OF_BODY);
        snake.add(block);
        break;
      case "down":
        block.setX(tailX);
        block.setY(tailY - 1);
        block.setWay(tailWay);
        block.setColor(COLOR_OF_BODY);
        snake.add(block);
        break;
    }
  }

  void rotate(int code) {
    switch (code) {
      case UP:
        if (!snake.get(0).getWay().equals("down")) {
          snake.get(0).setWay("up");
          rotateList.add("" + snake.get(0).getX() + " " + snake.get(0).getY() + " " + snake.get(0).getWay());
        }
        break;
      case DOWN:
        if (!snake.get(0).getWay().equals("up")) {
          snake.get(0).setWay("down");
          rotateList.add("" + snake.get(0).getX() + " " + snake.get(0).getY() + " " + snake.get(0).getWay());
        }
        break;
      case LEFT:
        if (!snake.get(0).getWay().equals("right")) {
          snake.get(0).setWay("left");
          rotateList.add("" + snake.get(0).getX() + " " + snake.get(0).getY() + " " + snake.get(0).getWay());
        }
        break;
      case RIGHT:
        if (!snake.get(0).getWay().equals("left")) {
          snake.get(0).setWay("right");
          rotateList.add("" + snake.get(0).getX() + " " + snake.get(0).getY() + " " + snake.get(0).getWay());
        }
        break;
    }
  }

  private String whereToTurn(SnakeBlock snakeBlock) {

    ArrayList<String> secondRotateList = new ArrayList<>();

    for (String position : rotateList) {

      String[] pos = position.split(" ");

      if (pos[0].equals("" + snakeBlock.getX()) && pos[1].equals("" + snakeBlock.getY())) {
        if (snakeBlock.equals(snake.get(snake.size() - 1))) {
          secondRotateList.addAll(rotateList.subList(1, rotateList.size()));
          rotateList.clear();
          rotateList.addAll(secondRotateList);
        }
        return pos[2];
      }
    }

    return snakeBlock.getWay();
  }

  void isCrash(AllWall allWall) {
    for (int i = 1; i < snake.size(); i++) {
      if (snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()
              || allWall.isCrash(snake.get(0).getX(), snake.get(0).getY())) {
        GameSnake.setGameOver(true);
      }
    }
  }

  boolean isAte(FoodBlock foodBlock) {
    if (foodBlock.getX() == snake.get(0).getX() && foodBlock.getY() == snake.get(0).getY()) {
      return true;
    } else
      return false;
  }

  void paint(Graphics g) {
    try {
      for (int i = 0; i < snake.size(); i++) {
        snake.get(i).paint(g);
      }
    } catch (ConcurrentModificationException e) {
      System.out.println(e);
    }
  }

  void paintAnim(Graphics g, int xShift, int yShift) {
    try {
      for (int i = 0; i < snake.size(); i++) {
        switch (snake.get(i).getWay()) {
          case ("up"):
            snake.get(i).paint(g, xShift * 0, yShift * -1);
            break;
          case ("down"):
            snake.get(i).paint(g, xShift * 0, yShift);
            break;
          case ("left"):
            snake.get(i).paint(g, xShift * -1, yShift * 0);
            break;
          case ("right"):
            snake.get(i).paint(g, xShift, yShift * 0);
            break;
        }
      }
    } catch (ConcurrentModificationException e) {
      System.out.println(e);
    }
  }
}
