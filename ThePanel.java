import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThePanel extends JPanel implements ActionListener
{
    // instance variables - replace the example below with your own
    public ThePanel(){
        super();
        setPreferredSize(new Dimension(TheFrame.WIDTH,TheFrame.HEIGHT));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
        });

        Timer t = new Timer(10,this);
        t.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }

    public void actionPerformed(ActionEvent e){
        repaint();
    }


}