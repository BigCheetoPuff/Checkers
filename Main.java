import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {




            SwingUtilities.invokeLater(new Runnable(){

                public void run() {

                    if (args.length == 1) {
                        if (args[0].equals("-host")) {

                            TheFrame frame = new TheFrame(new IntegratedPanel(8080));

                        } else if (args[0].equals("-client")) {

                                TheFrame frame = new TheFrame(new IntegratedPanel("DESKTOP-2LEV871", 8080));



                        }

                    }
                    else{
                        System.out.println("Please put in -host or -client parameter");
                    }


                }


            });


            }
    }
