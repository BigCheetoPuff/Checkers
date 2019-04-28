public class Checker {
    private int x;
    private int y;
    private Type type;
    private Color color;
    public final static int SIZE = 95;
    private boolean chosen;
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Checker(Color c, int x, int y){
        this.x = x;
        this.y = y;
        type = Type.REGULAR;
        this.color = c;

        chosen = false;
    }


    public void setChosen(boolean a){
        chosen = a;

    }

    public boolean isChosen(){return chosen;}

    enum Type{
        REGULAR,
        KING
    }
}
