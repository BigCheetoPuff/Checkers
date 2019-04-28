import javax.swing.*;


public class TheFrame extends JFrame
{

    static final int WIDTH = 820;
    static final int HEIGHT = 820;

    // instance variables - replace the example below with your own
    public TheFrame(IntegratedPanel p){
        
        this.getContentPane().add(p);
       this.setSize(WIDTH,HEIGHT);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
       this.setLocationRelativeTo(null);

    }

}
