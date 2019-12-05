import javax.swing.*;
import java.awt.*;

public class Program extends JFrame {
    private static Viewer viewer = new Viewer();
    private Thread thread;

    public Program() {
        this.pintarVentana();
        thread = new Thread(viewer);
        thread.start();
    }

    private void pintarVentana() {
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Cocina - Mario Nikolov");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(1024, 800));
        this.pintarCanvas();
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    private void pintarCanvas() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(viewer, gbc);
    }

    public static void main(String[] args) {
        Program program = new Program();
    }
}