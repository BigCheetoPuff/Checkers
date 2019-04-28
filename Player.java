public class Player {



    private Color color;

    public Player(Color c){
        color = c;

    }

    public Checker move(Board board,Checker checker, int mx, int my) {
        int nx = 0, ny = 0;
        Checker.Type type = checker.getType();
        System.out.println("Checker at x:" + checker.getX() + " y:" + checker.getY() + " color is " + checker.getColor());
        //forward left
        if (mx < checker.getX() * Checker.SIZE && mx > (checker.getX() - 1) * Checker.SIZE
                && my > (checker.getY() - 1) * Checker.SIZE && my < checker.getY() * Checker.SIZE) {
            nx = checker.getX() - 1;
            ny = checker.getY() - 1;
            System.out.println("tried going forward left");
            //forward right
        } else if (mx > (checker.getX() + 1) * Checker.SIZE && mx < (checker.getX() + 2) * Checker.SIZE
                && my > (checker.getY() - 1) * Checker.SIZE && my < checker.getY() * Checker.SIZE) {
            nx = checker.getX() + 1;
            ny = checker.getY() - 1;
            System.out.println("tried going forward right");
        }

        //back right
        else if (type == Checker.Type.KING) {
            if (mx > (checker.getX() + 1) * Checker.SIZE && mx < (checker.getX() + 2) * Checker.SIZE
                && my > (checker.getY() + 1) * Checker.SIZE && my < (checker.getY() + 2) * Checker.SIZE){

                nx = checker.getX() + 1;
                ny = checker.getY() + 1;
                System.out.println("tried going back right");

            }
            else if(mx < checker.getX() * Checker.SIZE && mx > (checker.getX() - 1) * Checker.SIZE
                    && my > (checker.getY() + 1) * Checker.SIZE && my < (checker.getY() + 2) * Checker.SIZE){
                nx = checker.getX() - 1;
                ny = checker.getY() + 1;
                System.out.println("tried going back left");
            }
    }

                else{
            System.out.println("Cant jump to that position");
                    return null;
                }

        int oldx = checker.getX();
        int oldy = checker.getY();

            if (board.hasChecker(nx, ny)) {
                System.out.println("There was a checker already there");
                if (board.typeOfChecker(nx, ny) != color) {
                    System.out.println("It was of the opposite color and we took it");
                    board.setChecker(nx, ny, checker);
                    board.getChecker(nx,ny).setChosen(false);
                    board.setChecker(oldx,oldy, null);
                    if (color == Color.BLACK) {
                        board.take(Color.RED);
                    } else {
                        board.take(Color.BLACK);
                    }

                } else {
                    System.out.println("It was the same color and didnt take");
                    return null;
                }
            } else {
                System.out.println("No checker there. Took it");
                board.setChecker(nx, ny, checker);
                board.getChecker(nx,ny).setChosen(false);
                board.setChecker(oldx,oldy, null);
            }
        System.out.println("Returned moved checker at " + board.getChecker(nx,ny).getX() +  " " + board.getChecker(nx,ny).getY());

        return board.getChecker(nx,ny);
        }

        public Color getColor(){return color;}



}
