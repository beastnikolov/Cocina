import org.w3c.dom.DOMImplementation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Statistics extends JFrame implements Runnable {
    private Viewer viewer;
    private BufferedImage shopUI;
    private boolean go = true;
    private JLabel goldEarned;
    private JLabel clientsServed;
    private JLabel restaurantReputation;
    private JLabel restaurantUpgrades;

    public Statistics(Viewer v) {
        this.viewer = v;
        try {
            shopUI = ImageIO.read(new File("src//Sprites//UI//statistics.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pintarVentana();
    }

    private void pintarVentana() {
        JLabel background = new JLabel(new ImageIcon(shopUI));

        this.getContentPane().setBackground(new Color(25, 25, 25));
        this.setTitle("Restaurant Shop");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        background.setBounds(0, 0, shopUI.getWidth(), shopUI.getHeight());
        this.setPreferredSize(new Dimension(215, 440));
        this.pack();
        this.setResizable(false);
        this.add(background);
        goldEarned = new JLabel(viewer.getStatisticGoldEarned()+"g");
        goldEarned.setForeground(Color.white);
        goldEarned.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        goldEarned.setBounds(100,40,200,200);
        background.add(goldEarned);
        clientsServed = new JLabel(String.valueOf(viewer.getStatisticClientsServed()));
        clientsServed.setForeground(Color.white);
        clientsServed.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        clientsServed.setBounds(100,95,200,200);
        background.add(clientsServed);
        restaurantReputation = new JLabel(String.valueOf(viewer.getStatisticReputation()));
        restaurantReputation.setForeground(Color.white);
        restaurantReputation.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        restaurantReputation.setBounds(100,155,200,200);
        background.add(restaurantReputation);
        restaurantUpgrades = new JLabel(String.valueOf(viewer.getStatisticUpgrades()));
        restaurantUpgrades.setForeground(Color.white);
        restaurantUpgrades.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        restaurantUpgrades.setBounds(88,215,200,200);
        background.add(restaurantUpgrades);
    }

    private void updateInfo() {
        goldEarned.setText(viewer.getStatisticGoldEarned()+"g");
        clientsServed.setText(String.valueOf(viewer.getStatisticClientsServed()));
        restaurantReputation.setText(String.valueOf(viewer.getStatisticReputation()));
        restaurantUpgrades.setText(String.valueOf(viewer.getStatisticUpgrades()));
    }

    @Override
    public void run() {
        while (go) {
            updateInfo();
        }

    }
}