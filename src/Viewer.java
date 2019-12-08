
import javafx.scene.media.MediaPlayer;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;

public class Viewer extends Canvas implements Runnable {
    private static int gold = 0;
    private BufferedImage goldSprite;
    private static int client_id = 0;
    private boolean go = true;
    private Thread thread;
    private Random random = new Random();
    private Chef chef;
    private Client client;
    private Table table;
    private Table tableB;
    private Table tableC;
    private Light light;
    private NPC npc;
    private ChristmasLight christmasLight;
    private ArrayList<Chef> chefArrayList = new ArrayList<>();
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Table> tableArrayList = new ArrayList<>();
    private ArrayList<Light> lightArrayList = new ArrayList<>();
    private ArrayList<NPC> npcArrayList = new ArrayList<>();
    private ArrayList<ChristmasLight> christmasLightArrayList = new ArrayList<>();
    private BufferedImage tavernSprite;
    private BufferedImage currencyWindow;
    private BufferedImage controlsWindow;
    private BufferedImage upgradePost;
    private String[] MnameArray = {"Mario","Pere","Jose","Sergi","Toni","Pepe"};
    private String[] FnameArray = {"Laura","Sandra","Silvia","Maria","Lisa"};
    private Shop shop = new Shop(this);
    private Statistics statistics = new Statistics(this);
    private int clientSpawnRate = 20000;
    private int statisticGoldEarned = 0;
    private int statisticClientsServed = 0;
    private int statisticReputation = 0;
    private int statisticUpgrades = 0;
    private BufferedImage smileyFace;
    private BufferedImage smileyFaceBad;
    private Clip clip;


    public Viewer(){
        this.setBackground(new Color(25,25,25));
        try {
            tavernSprite = ImageIO.read(new File("src//tavernv3.png"));
            currencyWindow = ImageIO.read(new File("src//Sprites//UI//currency.png"));
            controlsWindow = ImageIO.read(new File("src//Sprites//UI//controls.png"));
            upgradePost = ImageIO.read(new File("src//Sprites//UI//upgradepost.png"));
            smileyFace = ImageIO.read(new File("src//Sprites//UI//smiley.png"));
            smileyFaceBad = ImageIO.read(new File("src//Sprites//UI//smiley2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                int key = keyEvent.getKeyCode();
                if (key == KeyEvent.VK_B) {
                   shop.setVisible(true);
                }
                if (key == KeyEvent.VK_N) {
                    statistics.setVisible(true);
                }
                if (key == KeyEvent.VK_M) {
                    if (clip.isActive()) {
                        clip.stop();
                    } else {
                        clip.start();
                    }
                }
            }
        });

    }

    private void createNPCs() {
        npc = new NPC("Santa",66,330,1);
        npcArrayList.add(npc);
        thread = new Thread(npc);
        thread.start();
        npc = new NPC("Jumi",666,285,2);
        npcArrayList.add(npc);
        thread = new Thread(npc);
        thread.start();
        npc = new NPC("Rat",466,285,3);
        npcArrayList.add(npc);
        thread = new Thread(npc);
        thread.start();
    }

    private void createLights() {
        light = new Light(120,50);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(570,50);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(630,100);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(343,150);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(86,427);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(86,300);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(86,556);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(632,300);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(632,427);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        light = new Light(632,586);
        lightArrayList.add(light);
        thread = new Thread(light);
        thread.start();
        christmasLight = new ChristmasLight(170,35);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(185,35);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(200,35);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(475,30);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(460,30);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(445,30);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(60,30);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(35,40);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(65,60);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(40,85);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(70,90);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(90,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(105,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(120,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(135,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(150,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(165,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(180,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(195,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(210,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(225,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(240,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(255,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(270,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(285,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(300,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(315,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(330,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(345,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(360,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(375,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(390,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(405,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(420,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(435,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(450,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(465,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(480,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(495,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(510,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(525,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(540,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(555,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
        christmasLight = new ChristmasLight(570,180);
        christmasLightArrayList.add(christmasLight);
        thread = new Thread(christmasLight);
        thread.start();
    }


    @Override
    public void run() {
        thread = new Thread(statistics);
        thread.start();
        try {
            goldSprite = ImageIO.read(new File("src//Sprites//UI//gold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.createBufferStrategy(2);
        playMusic();
        createLights();
        createTables();
        createChefs();
        createNPCs();
        createClients();
        while (go) {
            this.paint();
            if (random.nextInt(clientSpawnRate) == 1) {
                createClients();
            }

        }
    }

    private void createChefs() {
        chef = new Chef(1,"Ramsey",table,130,135,random.nextInt(10));
        thread = new Thread(chef);
        thread.start();
        chefArrayList.add(chef);
        chef = new Chef(2,"Chicote",tableB,260,135,random.nextInt(10));
        thread = new Thread(chef);
        chefArrayList.add(chef);
        thread.start();
        chef = new Chef(3,"Thomas",tableC,490,135,random.nextInt(10));
        thread = new Thread(chef);
        chefArrayList.add(chef);
        thread.start();
    }

    private void createTables() {
        table = new Table(0,120,155,"TableA");
        tableArrayList.add(table);
        tableB = new Table(0,250,155,"TableB");
        tableArrayList.add(tableB);
        tableC = new Table(0,480,155,"TableC");
        tableArrayList.add(tableC);
    }

    private void createClients() {
        client = generateClient();
        thread = new Thread(client);
        thread.start();
        clientArrayList.add(client);
    }



    public void paint(){
        BufferStrategy bs;
        Graphics graphics;
        bs = this.getBufferStrategy();
        if (bs == null) {
            return;
        }
        graphics = bs.getDrawGraphics();
        graphics.drawImage(tavernSprite,0,0,null);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("MS Gothic",Font.PLAIN,10));
        graphics.drawImage(currencyWindow,780,50,null);
        graphics.drawImage(controlsWindow,780,300,null);
        graphics.drawImage(upgradePost,780,500,null);
        graphics.drawImage(goldSprite,810,132,32,32,null);
        graphics.drawString(String.valueOf(gold),940,152);
        graphics.drawString(String.valueOf(table.getDishes()),940,176);
        graphics.drawString(String.valueOf(tableB.getDishes()),940,200);
        graphics.drawString(String.valueOf(tableC.getDishes()),940,224);
        ///
        graphics.drawString("Chef Cooking time: " + chef.getChefSpeed() + "ms",810,590);
        graphics.drawString("Client Spawn rate: 1 every " + clientSpawnRate,810,605);
        graphics.drawString("Table 1 Upgrade stage: " + table.getCurrentUpgrade(),810,620);
        graphics.drawString("Table 2 Upgrade stage: " + tableB.getCurrentUpgrade(),810,635);
        graphics.drawString("Table 3 Upgrade stage: " + tableC.getCurrentUpgrade(),810,650);
        for (Light l: lightArrayList) {
            l.paint(graphics);
        }
        for (ChristmasLight cl: christmasLightArrayList) {
            cl.paint(graphics);
        }
        for (Table t: tableArrayList) {
            t.drawTable(graphics);
            t.drawDishes(graphics);
        }
        for (Chef c: chefArrayList) {
            c.drawChef(graphics);
        }
        for (NPC npc: npcArrayList) {
            npc.drawNPC(graphics);
        }
        for (int i = clientArrayList.size()-1; i >= 0; i--) {
            if (clientArrayList.get(i)!=null) {
                clientArrayList.get(i).drawClient(graphics);
                if (clientArrayList.get(i).getSmiley() == 1) {
                    graphics.drawImage(smileyFace,clientArrayList.get(i).getxPos()+8,clientArrayList.get(i).getyPos()-20,null);
                } else if (clientArrayList.get(i).getSmiley() == 2) {
                    graphics.drawImage(smileyFaceBad,clientArrayList.get(i).getxPos()+8,clientArrayList.get(i).getyPos()-25,null);
                }
             }
        }
        bs.show();
        super.paint(graphics);
        graphics.dispose();
    }


    private Client generateClient() {
        String name;
        String gender;
        int movementVariation;
        Table tableChoice;

        if (random.nextInt(3)==0) {
            tableChoice = table;
        } else if (random.nextInt(3) == 1) {
            tableChoice = tableB;
        } else {
            tableChoice = tableC;
        }
        if (random.nextInt(2)==0){
            gender = "Male";
        } else {
            gender = "Female";
        }
        if (gender.equals("Male")) {
            name = MnameArray[random.nextInt(6)];
        } else {
            name = FnameArray[random.nextInt(5)];
        }
        movementVariation = random.nextInt(10);
        client_id++;
        return new Client(client_id,name,tableChoice,370,720,gender,clientArrayList,movementVariation,this);
    }

    public void upgradeTable(String tableType) {
        if (tableType.equals("TableA")) {
            if (table.getCurrentUpgrade() == 0) {
                table.setCurrentUpgrade(1);
                table.setDishPrice(10);
            } else if (table.getCurrentUpgrade() == 1) {
                table.setCurrentUpgrade(2);
                table.setDishPrice(14);
            }
            try {
                table.loadTableSprites();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tableType.equals("TableB")) {
            if (tableB.getCurrentUpgrade() == 0) {
                tableB.setCurrentUpgrade(1);
                tableB.setDishPrice(10);
            } else if (tableB.getCurrentUpgrade() == 1) {
                tableB.setCurrentUpgrade(2);
                tableB.setDishPrice(14);
            }
            try {
                tableB.loadTableSprites();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tableType.equals("TableC")) {
            if (tableC.getCurrentUpgrade() == 0) {
                tableC.setCurrentUpgrade(1);
                tableC.setDishPrice(10);
            } else if (tableC.getCurrentUpgrade() == 1) {
                tableC.setCurrentUpgrade(2);
                tableC.setDishPrice(14);
            }
            try {
                tableC.loadTableSprites();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void upgradeChef() {
        for (Chef c: chefArrayList) {
            if (c.getUpgradeStage() == 0) {
                c.setChefSpeed(2000);
                c.setUpgradeStage(1);
            } else if (c.getUpgradeStage() == 1) {
                c.setChefSpeed(200);
                c.setUpgradeStage(2);
            }
        }
    }

    public int getUpgradeLevel(String object) {
        int upgradeLevel = 0;
        if (object.equals("Client")) {
            if (getClientSpawnRate() == 20000) {
                upgradeLevel = 0;
            } else if (getClientSpawnRate() == 15000) {
                upgradeLevel = 1;
            } else if (getClientSpawnRate() == 8000) {
                upgradeLevel = 2;
            }
        } else if (object.equals("Chef")) {
            upgradeLevel = chef.getUpgradeStage();
        } else if (object.equals("TableA")) {
            upgradeLevel = table.getCurrentUpgrade();
        } else if (object.equals("TableB")) {
            upgradeLevel = tableB.getCurrentUpgrade();
        } else if (object.equals("TableC")) {
            upgradeLevel = tableC.getCurrentUpgrade();
        }
        return upgradeLevel;
    }

    public void upgradeClientSpawnRate() {
        if (getClientSpawnRate() == 20000) {
            this.setClientSpawnRate(15000);
        } else if (getClientSpawnRate() == 15000){
            this.setClientSpawnRate(8000);
        }
    }

    private void playMusic() {
        File musicPath = new File("src//Music//track.wav");
        InputStream in = null;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }


    }




    public static int getGold() {
        return gold;
    }

    public static void setGold(int gold) {
        Viewer.gold = gold;
    }

    public int getStatisticGoldEarned() {
        return statisticGoldEarned;
    }

    public void setStatisticGoldEarned(int statisticGoldEarned) {
        this.statisticGoldEarned = statisticGoldEarned;
    }

    public int getStatisticClientsServed() {
        return statisticClientsServed;
    }

    public void setStatisticClientsServed(int statisticClientsServed) {
        this.statisticClientsServed = statisticClientsServed;
    }

    public int getStatisticReputation() {
        return statisticReputation;
    }

    public void setStatisticReputation(int statisticReputation) {
        this.statisticReputation = statisticReputation;
    }

    public int getStatisticUpgrades() {
        return statisticUpgrades;
    }

    public void setStatisticUpgrades(int statisticUpgrades) {
        this.statisticUpgrades = statisticUpgrades;
    }

    public int getClientSpawnRate() {
        return clientSpawnRate;
    }

    public void setClientSpawnRate(int clientSpawnRate) {
        this.clientSpawnRate = clientSpawnRate;
    }
}
