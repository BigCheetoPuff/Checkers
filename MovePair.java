import java.io.Serializable;

public class MovePair implements Serializable {
    Checker oldC,newC;

    public Checker getOld() {
        return oldC;
    }

    public Checker getNew() {
        return newC;
    }

    public MovePair(Checker oldC, Checker newC){
        this.newC = newC;
        this.oldC = oldC;

    }
}
