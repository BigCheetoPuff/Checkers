import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class IntegratedPanel extends JPanel implements ActionListener
{

    private Player player;
    private Board board;
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private AppID turn;
    private AppID id;
    private boolean connected;
    // instance variables - replace the example below with your own
    public IntegratedPanel(int port){
        super();
        setPreferredSize(new Dimension(TheFrame.WIDTH-20,TheFrame.HEIGHT-20));

        //HOST IS BLACK

        player = new Player(Color.RED); //???
        board = new Board();
        turn = AppID.CLIENT;
        id = AppID.HOST;

        try {
            System.out.println("Opening server socket...");
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(30 * 1000);
        }catch (IOException e){
            e.printStackTrace();
        }



        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(turn == id){
                    playCheckers(e.getX(),e.getY());
                }
            }


        });

        Timer t = new Timer(10,this);
        initConnection();
        t.start();

    }

    public IntegratedPanel(String hostname,int port){
        super();
        setPreferredSize(new Dimension(TheFrame.WIDTH-20,TheFrame.HEIGHT-20));
        //CLIENT IS RED
        player = new Player(Color.BLACK); //???
        board = new Board();
        turn = AppID.CLIENT;
        id = AppID.CLIENT;
        try{
            System.out.println("Trying to open socket...");
            socket = new Socket(hostname,port);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Socket failed");
        }




        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(turn == id){
                    playCheckers(e.getX(),e.getY());
                }
            }


        });

        Timer t = new Timer(10,this);
        initConnection();
        t.start();

    }

    public void initConnection(){

        try{
            if(id == AppID.CLIENT){
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

            }
            else{
                System.out.println("Waiting for client to connect...");
                Socket playerSocket = serverSocket.accept();
                out = new ObjectOutputStream(playerSocket.getOutputStream());
                in = new ObjectInputStream(playerSocket.getInputStream());
                System.out.println("Client connected");

            }

            System.out.println("-Connection secured");
        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public void getMoveInfo() {

        try {
            MovePair mp = (MovePair)(in.readObject());
            board.setChecker(mp.getNew().getX(), mp.getNew().getY(), mp.getOld());

            board.setChecker(mp.getOld().getX(), mp.getOld().getY(), null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendMoveInfo(Checker oldChecker,Checker newChecker) {
        try {
            MovePair mp = new MovePair(oldChecker, newChecker);
            out.writeObject(mp);
            out.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void playCheckers(int mx, int my) {

            if (board.checkGameOver() == GameState.STILL_PLAYING) {



                Checker chosen = board.findChosen();

                if (chosen != null) {

                    Checker newChecker = null;

                    if ((newChecker = player.move(board, chosen, mx, my)) != null) {

                        System.out.println("Hello");
                        sendMoveInfo(chosen, newChecker);
                        turn = turn == AppID.CLIENT ? AppID.HOST : AppID.CLIENT;
                        //chosen.setChosen(false);
                    }
                } else {
                    //get mouse grab as checker

                    int posx = mx / Checker.SIZE;
                    int posy = my / Checker.SIZE;
                    chosen = board.getChecker(posx, posy);
                    if(chosen != null && chosen.getColor() == player.getColor())
                        chosen.setChosen(true);
                    // get mouse release

                }
            }


                //update and draw


        }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        board.render(g);


    }

    public void actionPerformed(ActionEvent e){
        repaint();

        //if(turn != id){
            //POSSIBLE PROBLEM OF BLOCKING
            getMoveInfo();
          //  turn = id;
        //}
    }


}
