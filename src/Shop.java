import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shop extends JFrame {
    private Viewer viewer;
    private BufferedImage shopUI;
    private BufferedImage upgradeIcon;
    private int price1 = 10;
    private int price2 = 10;
    private int price3 = 10;
    private int price4 = 10;
    private int price5 = 10;
    private JLabel AlertSuccess;
    private JLabel Alert;
    private JLabel AlertMax;

    public Shop(Viewer v) {
        this.viewer = v;
        try {
            shopUI = ImageIO.read(new File("src//Sprites//UI//upgradeshop.png"));
            upgradeIcon = ImageIO.read(new File("src//Sprites//UI//upgrade.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pintarVentana();
    }

    private void pintarVentana() {
        JLabel background = new JLabel(new ImageIcon(shopUI));

        this.getContentPane().setBackground(new Color(25,25,25));
        this.setTitle("Restaurant Shop");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        background.setBounds(0,0,shopUI.getWidth(),shopUI.getHeight());
        this.setPreferredSize(new Dimension(215,440));
        this.pack();
        this.setResizable(false);
        this.add(background);
        shopButtons(background);
    }

    private void shopButtons(JLabel background) {
        JLabel upgrade1 = new JLabel(new ImageIcon(upgradeIcon));
        upgrade1.setBounds(153,76,upgradeIcon.getWidth(),upgradeIcon.getHeight());
        background.add(upgrade1);

        upgrade1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeTransaction("upgradeChef");
            }

        });

        JLabel upgrade2 = new JLabel(new ImageIcon(upgradeIcon));

        upgrade2.setBounds(153,136,upgradeIcon.getWidth(),upgradeIcon.getHeight());
        background.add(upgrade2);

        upgrade2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeTransaction("upgradeClient");
            }

        });

        JLabel upgrade3 = new JLabel(new ImageIcon(upgradeIcon));

        upgrade3.setBounds(153,194,upgradeIcon.getWidth(),upgradeIcon.getHeight());
        background.add(upgrade3);

        upgrade3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeTransaction("upgradeTable1");
            }

        });

        JLabel upgrade4 = new JLabel(new ImageIcon(upgradeIcon));

        upgrade4.setBounds(153,254,upgradeIcon.getWidth(),upgradeIcon.getHeight());
        background.add(upgrade4);

        upgrade4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeTransaction("upgradeTable2");
            }

        });

        JLabel upgrade5 = new JLabel(new ImageIcon(upgradeIcon));

        upgrade5.setBounds(153,314,upgradeIcon.getWidth(),upgradeIcon.getHeight());
        background.add(upgrade5);

        upgrade5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeTransaction("upgradeTable3");
            }

        });

        JLabel Jprice1 = new JLabel(price1 + "g");
        Jprice1.setForeground(Color.white);
        Jprice1.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        Jprice1.setBounds(155,-30,200,200);
        background.add(Jprice1);
        JLabel Jprice2 = new JLabel(price2 + "g");
        Jprice2.setForeground(Color.white);
        Jprice2.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        Jprice2.setBounds(155,30,200,200);
        background.add(Jprice2);
        JLabel Jprice3 = new JLabel(price3 + "g");
        Jprice3.setForeground(Color.white);
        Jprice3.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        Jprice3.setBounds(155,88,200,200);
        background.add(Jprice3);
        JLabel Jprice4 = new JLabel(price4 + "g");
        Jprice4.setForeground(Color.white);
        Jprice4.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        Jprice4.setBounds(155,148,200,200);
        background.add(Jprice4);
        JLabel Jprice5 = new JLabel(price5 + "g");
        Jprice5.setForeground(Color.white);
        Jprice5.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        Jprice5.setBounds(155,207,200,200);
        background.add(Jprice5);
        Alert = new JLabel("<html>Insufficient gold to </br>                       purchase this upgrade!</html>");
        Alert.setForeground(Color.white);
        Alert.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        Alert.setBounds(15,260,200,200);
        background.add(Alert);
        Alert.setVisible(false);
        AlertSuccess = new JLabel("Upgraded Successfully!");
        AlertSuccess.setForeground(Color.white);
        AlertSuccess.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        AlertSuccess.setBounds(15,260,200,200);
        background.add(AlertSuccess);
        AlertSuccess.setVisible(false);
        AlertMax = new JLabel("You can't upgrade anymore!");
        AlertMax.setForeground(Color.white);
        AlertMax.setFont(new Font("MS Gothic", Font.PLAIN, 10));
        AlertMax.setBounds(15,260,200,200);
        background.add(AlertMax);
        AlertMax.setVisible(false);
    }

    private void makeTransaction(String type) {
        int currentUpgrade = 0;

        AlertMax.setVisible(false);
        Alert.setVisible(false);
        AlertSuccess.setVisible(false);
        if (type.equals("upgradeChef")) {
            currentUpgrade = viewer.getUpgradeLevel("Chef");
            if (currentUpgrade == 2) {
                AlertMax.setVisible(true);
                return;
            } else {
                if (viewer.getGold() >= price1) {
                    viewer.setGold(viewer.getGold() - price1);
                    viewer.upgradeChef();
                    viewer.setStatisticUpgrades(viewer.getStatisticUpgrades() + 1);
                    AlertSuccess.setVisible(true);
                } else {
                    Alert.setVisible(true);
                }
            }
        } else if (type.equals("upgradeClient")) {
            currentUpgrade = viewer.getUpgradeLevel("Client");
            if (currentUpgrade == 2) {
                AlertMax.setVisible(true);
                return;
            } else {
                if (viewer.getGold() >= price2) {
                    viewer.setGold(viewer.getGold() - price2);
                    //Upgrade Client
                    viewer.setStatisticUpgrades(viewer.getStatisticUpgrades() + 1);
                    AlertSuccess.setVisible(true);
                } else {
                    Alert.setVisible(true);
                }
            }
        } else if (type.equals("upgradeTable1")) {
            currentUpgrade = viewer.getUpgradeLevel("TableA");
            if (currentUpgrade == 2) {
                AlertMax.setVisible(true);
                return;
            } else {
                if (viewer.getGold() >= price3) {
                    viewer.setGold(viewer.getGold() - price3);
                    viewer.upgradeTable("TableA");
                    viewer.setStatisticUpgrades(viewer.getStatisticUpgrades() + 1);
                    AlertSuccess.setVisible(true);
                } else {
                    Alert.setVisible(true);
                }
            }
        } else if (type.equals("upgradeTable2")) {
            currentUpgrade = viewer.getUpgradeLevel("TableB");
            if (currentUpgrade == 2) {
                AlertMax.setVisible(true);
                return;
            } else {
                if (viewer.getGold() >= price4) {
                    viewer.setGold(viewer.getGold() - price4);
                    viewer.upgradeTable("TableB");
                    viewer.setStatisticUpgrades(viewer.getStatisticUpgrades() + 1);
                    AlertSuccess.setVisible(true);
                } else {
                    Alert.setVisible(true);
                }
            }
        } else if (type.equals("upgradeTable3")) {
            currentUpgrade = viewer.getUpgradeLevel("TableCs");
            if (currentUpgrade == 2) {
                AlertMax.setVisible(true);
                return;
            } else {
                if (viewer.getGold() >= price5) {
                    viewer.setGold(viewer.getGold() - price5);
                    viewer.upgradeTable("TableC");
                    viewer.setStatisticUpgrades(viewer.getStatisticUpgrades() + 1);
                    AlertSuccess.setVisible(true);
                } else {
                    Alert.setVisible(true);
                }
            }
        }
        if (viewer.getStatisticUpgrades() > 10) {
            viewer.setStatisticUpgrades(10);
        }

    }


}
