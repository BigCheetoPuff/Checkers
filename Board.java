import java.awt.*;

public class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private int blackCount = 12;
    private int redCount = 12;
    private Checker[][] spaces;
    private GameState gameState;

    public Board() {
        Color color;
        spaces = new Checker[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if ((i % 2)  == (j % 2)) {

                    if(i < 3) {
                        color = Color.BLACK;
                        spaces[i][j] = new Checker(color, j,i);
                    }
                    else if(i > 4){
                        color = Color.RED;
                        spaces[i][j] = new Checker(color, j,i);
                    }


                }

            }
        }


    }

    public GameState checkGameOver() {
        if (blackCount == 0)
            return GameState.RED_WIN;
        else if (redCount == 0)
            return GameState.BLACK_WIN;
        else
            return GameState.STILL_PLAYING;
    }

    public boolean isOnBoard(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    public boolean hasChecker(int x, int y) {
        return spaces[y][x] != null;
    }

    public Color typeOfChecker(int x, int y) {
        return spaces[y][x].getColor();
    }

    public void setChecker(int nx, int ny, Checker checker) {
        if(checker != null) {
            checker.setX(nx);
            checker.setY(ny);
            if(checker.getColor() == Color.RED && ny == 0)
                checker.setType(Checker.Type.KING);
            else if(checker.getColor() == Color.BLACK && ny == HEIGHT-1)
                checker.setType(Checker.Type.KING);
        }
        spaces[ny][nx] = checker;


    }

    public void take(Color c) {
        if (c == Color.BLACK) {
            blackCount--;
        } else {
            redCount--;
        }
    }

    public Checker getChecker(int nx, int ny) {
        return spaces[ny][nx];
    }

    public void render(Graphics g) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {

                if ((i % 2) == (j % 2)) {
                    g.setColor(java.awt.Color.BLACK);
                } else {
                    g.setColor(java.awt.Color.WHITE);
                }
                g.fillRect(j * Checker.SIZE, i * Checker.SIZE, Checker.SIZE, Checker.SIZE);
                //System.out.println(spaces[i][j].getColor());
                if (spaces[i][j] != null) {
                    if (spaces[i][j].getColor() == Color.BLACK) {

                        g.setColor(java.awt.Color.DARK_GRAY);
                        g.fillOval(j * Checker.SIZE, i * Checker.SIZE, Checker.SIZE - 2, Checker.SIZE - 2);
                        System.out.println("Drawing black");
                        if (spaces[i][j].isChosen()) {
                            g.setColor(java.awt.Color.YELLOW);
                            g.drawOval(j * Checker.SIZE, i * Checker.SIZE, Checker.SIZE - 2, Checker.SIZE - 2);
                        }
                    } else if (spaces[i][j].getColor() == Color.RED) {
                        g.setColor(java.awt.Color.RED);
                        g.fillOval(j * Checker.SIZE, i * Checker.SIZE, Checker.SIZE - 2, Checker.SIZE - 2);
                        if (spaces[i][j].isChosen()) {
                            g.setColor(java.awt.Color.YELLOW);
                            g.drawOval(j * Checker.SIZE, i * Checker.SIZE, Checker.SIZE - 2, Checker.SIZE - 2);
                        }
                    }
                }
            }
        }
    }

    public Checker findChosen() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if(spaces[i][j] != null) {
                    if (spaces[i][j].isChosen())
                        return spaces[i][j];
                }
            }

        }
        return null;

    }

    public void setChosen(boolean a ){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if(spaces[i][j] != null) {
                    if (spaces[i][j].isChosen())
                        spaces[i][j].setChosen(false);
                }
            }

        }

    }
}


