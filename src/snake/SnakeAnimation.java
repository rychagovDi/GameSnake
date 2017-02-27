package snake;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static snake.Constants.SHOW_DELAY;

public class SnakeAnimation implements Runnable {
  private String name;
  private boolean stopFlag;
  private Thread t;
  private int animDelay;
  private AllSnake snake;
  private Graphics g;

  SnakeAnimation(AllSnake snake) {
    this.name = "Thread of animation";
    this.stopFlag = true;
    this.snake = snake;
    animDelay = SHOW_DELAY / 6;
    t = new Thread(this, name);
    t.start();
  }

  void setGrapics(Graphics g) {
    if (g == null)
      this.g = g;
    System.out.println(g);
  }

  synchronized void resume() {
    stopFlag = false;
    notify();
  }

  synchronized void stop() {
    stopFlag = true;
  }

  synchronized void join() {
    try {
      t.join(SHOW_DELAY);
    } catch (InterruptedException ex) {
      Logger.getLogger(SnakeAnimation.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void run() {
    while (true) {
      if (g != null) {
        try {
          while (stopFlag) {
            synchronized (this) {
              wait();
            }
          }
          for (int i = 1; i < 7; i++) {
            if (g == null) {
              System.out.println("wtf");
            }
            snake.paintAnim(g, i, i);
            Thread.sleep(animDelay);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        stop();
      }
    }
  }
}
