package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ConcurrentModificationException;

import static snake.Constants.*;

public class GameSnake extends JFrame {

  private Canvas canvas = new Canvas();
  private AllSnake allSnake = new AllSnake();
  private FoodBlock foodBlock;
  private AllWall allWall;
  private static boolean gameOver = false;
  private static boolean isKeyPressed = false;
  //private SnakeAnimation animation;

  public static void main(String[] args) {
    new GameSnake().play();
  }

  GameSnake() {
    setTitle(TITLE_OF_PROGRAM);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(FIELD_WIDTH, FIELD_HEIGHT);
    setBounds(START_LOCATION, START_LOCATION, FIELD_WIDTH * BLOCK_SIZE + BLOCK_SIZE, FIELD_HEIGHT * BLOCK_SIZE + BLOCK_SIZE * 5);
    setResizable(false);
    canvas.setBackground(Color.darkGray);
    canvas.setSize(FIELD_WIDTH, FIELD_HEIGHT);


    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (!gameOver & !isKeyPressed) {
          if (e.getKeyCode() == DOWN
                  || e.getKeyCode() == UP
                  || e.getKeyCode() == LEFT
                  || e.getKeyCode() == RIGHT) {
            isKeyPressed = true;
            allSnake.rotate(e.getKeyCode());
          }
        }
      }
    });
    add(BorderLayout.CENTER, canvas);
    setVisible(true);
  }

  private void play() {
    //animation = new SnakeAnimation(allSnake);
    //animation.stop();

    foodBlock = new FoodBlock(allSnake);
    allWall = new AllWall();
    while (!gameOver) {
      try {
//               animation.resume();
        Thread.sleep(SHOW_DELAY);
//               animation.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      allSnake.isCrash(allWall);

      if (gameOver) {
        break;
      }

      allSnake.move();

      try {
        canvas.repaint();
      } catch (ConcurrentModificationException e) {
        System.out.println(e);
      }

      if (allSnake.isAte(foodBlock)) {
        setTitle(TITLE_OF_PROGRAM + ": Score = " + allSnake.getSnakeLenght());
        foodBlock = new FoodBlock(allSnake);
        allSnake.addBlock();
      }

      isKeyPressed = false;
    }
  }

  static void setGameOver(boolean gOver) {
    gameOver = gOver;
  }

  private final class Canvas extends JPanel { // my canvas for painting


    @Override
    public void paint(Graphics g) {
      super.paint(g);
      allSnake.paint(g);
//            for (int i = 1; i<7; i ++){
//               allSnake.paintAnim(g,i,i);
//                try {
//                    Thread.sleep(SHOW_DELAY/6);
//                    System.out.println("In paint");     
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(GameSnake.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
      foodBlock.paint(g);
      allWall.paint(g);
    }
  }
}
