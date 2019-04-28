import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    /*

    private Player player;
    private Board board;
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public Client(int port) {
        player = new Player(Color.BLACK); //???
        board = new Board();
        try {
            serverSocket = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Client(String hostname, int port){

        try{
            socket = new Socket(hostname,port);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void run(){
        try{
            if(serverSocket == null){
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                playCheckers();
                socket.close();
                out.close();
                in.close();
            }
            else{
                Socket playerSocket = serverSocket.accept();
                out = new ObjectOutputStream(playerSocket.getOutputStream());
                in = new ObjectInputStream(playerSocket.getInputStream());

                playCheckers();
                serverSocket.close();
                out.close();
                in.close();
            }
        }catch(IOException e){

        }

    }


    public void getMoveInfo() {

        try {
            MovePair mp = (MovePair)(in.readObject());
            board.setChecker(mp.getNew().getX(), mp.getNew().getY(), mp.getNew().getColor());

            board.setChecker(mp.getOld().getX(), mp.getOld().getY(), Color.NONE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendMoveInfo(Checker oldChecker,Checker newChecker) {
        try {
            MovePair mp = new MovePair(oldChecker, newChecker);
            out.writeObject(mp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void playCheckers() {
        boolean keepPlaying = true;

        while (keepPlaying) {

            while (board.checkGameOver() == GameState.STILL_PLAYING) {
                getMoveInfo();
                Checker oldChecker = null;
                Checker newChecker = null;
                do {

                    //get mouse grab as checker
                    int posx = gx / Checker.SIZE;
                    int posy = gx / Checker.SIZE;
                    oldChecker = board.getChecker(posx, posy);
                    oldChecker.setChosen(true);
                    // get mouse release
                    newChecker = player.move(board, oldChecker, mx, my);


                } while (newChecker == oldChecker);
                sendMoveInfo(oldChecker, newChecker);
                //update and draw

            }


            //end connection

        }
    }

    public void reset(){
        board = new Board();

    }

*/
}
