import javax.swing.*;
import java.awt.*;

public class Shop extends JFrame {
    Viewer viewer;

    public Shop(Viewer v) {
        this.viewer = v;
        this.pintarVentana();
    }

    private void pintarVentana() {
        this.getContentPane().setBackground(Color.black);
        this.setTitle("Restaurant Shop");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(400,400));
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }


}
