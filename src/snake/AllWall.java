package snake;

import java.awt.*;
import java.util.ArrayList;

import static snake.Constants.*;

public class AllWall {

  ArrayList<WallBlock> wall = new ArrayList<>();
  WallBlock wallBlock;

  AllWall() {
    builtWall();
  }

  private void builtWall() {
    for (int i = 0; i < FIELD_HEIGHT; i++) {
      wallBlock = new WallBlock();
      wallBlock.setX(0);
      wallBlock.setY(i);
      wall.add(wallBlock);

      wallBlock = new WallBlock();
      wallBlock.setX(FIELD_WIDTH - 1);
      wallBlock.setY(i);
      wall.add(wallBlock);
    }

    for (int i = 0; i < FIELD_WIDTH; i++) {
      wallBlock = new WallBlock();
      wallBlock.setX(i);
      wallBlock.setY(0);
      wall.add(wallBlock);

      wallBlock = new WallBlock();
      wallBlock.setX(i);
      wallBlock.setY(FIELD_HEIGHT - 1);
      wall.add(wallBlock);
    }
  }

  boolean isCrash(int x, int y) {
    for (WallBlock wallBlock : wall) {
      if (wallBlock.getX() == x & wallBlock.getY() == y) {
        return true;
      }
    }
    return false;
  }

  void paint(Graphics g) {
    for (WallBlock wallBlock : wall) {
      g.setColor(new Color(COLOR_OF_WALL));
      g.fillRect(wallBlock.getX() * BLOCK_SIZE + 1, wallBlock.getY() * BLOCK_SIZE + 1, BLOCK_SIZE, BLOCK_SIZE);
    }
  }


}
