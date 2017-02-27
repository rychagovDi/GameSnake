package snake;

public class Constants {

  final static String TITLE_OF_PROGRAM = "Snake";
  final static String START_WAY = "right";

  final static int BLOCK_SIZE = 6;
  final static int ARC_RADIUS = 5;
  final static int FIELD_WIDTH = 80; // in block
  final static int FIELD_HEIGHT = 45; // in block
  final static int START_LOCATION = 180; // (Window)
  final static int START_LENGTH = 10; // in block
  final static int START_POSITION = 10; // (Snake) in block

  final static int LEFT = 37; // key codes
  final static int UP = 38;
  final static int RIGHT = 39;
  final static int DOWN = 40;
  final static int SHOW_DELAY = 100;

  final static int RED = 0x0000FF;
  final static int PINK = 0xFF00FF;
  final static int ORANGE = 0x0080FF;
  final static int YELLOW = 0x00FFFF;
  final static int WHITE = 0xFFFFFF;
  final static int GREEN = 0x00FF00;
  final static int GREY = 0x808080;
  final static int SEA = 0x008080;

  final static int COLOR_OF_HEAD = ORANGE;
  final static int COLOR_OF_BODY = GREY;
  final static int COLOR_OF_FOOD = PINK;
  final static int COLOR_OF_WALL = SEA;

  private Constants() {
  }
}
