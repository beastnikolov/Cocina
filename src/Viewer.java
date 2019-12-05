import javafx.scene.control.Tab;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private ArrayList<Chef> chefArrayList = new ArrayList<>();
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Table> tableArrayList = new ArrayList<>();
    private BufferedImage tavernSprite;
    private String[] MnameArray = {"Mario","Pere","Jose","Sergi","Pascual","Salvador"};
    private String[] FnameArray = {"Laura","Antonia","Silvia","Maria","Patricia"};

    public Viewer(){
        this.setBackground(Color.black);
        try {
            tavernSprite = ImageIO.read(new File("src//ztavern.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            goldSprite = ImageIO.read(new File("src//zcoin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.createBufferStrategy(2);
        createTables();
        createChefs();
        createClients();
        while (go) {
            this.paint();
            if (random.nextInt(500) == 1) {
                createClients();
            }

        }
    }

    private void createChefs() {
        chef = new Chef(1,table,400,260,random.nextInt(10));
        thread = new Thread(chef);
        thread.start();
        chefArrayList.add(chef);
        chef = new Chef(2,tableB,400,260,random.nextInt(10));
        thread = new Thread(chef);
        chefArrayList.add(chef);
        thread.start();
    }

    private void createTables() {
        table = new Table(6,220,325,"Fish");
        tableArrayList.add(table);
        tableB = new Table(4,480,325,"Cake");
        tableArrayList.add(tableB);
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
        graphics.drawImage(goldSprite,930,720,null);
        graphics.drawString(String.valueOf(gold),965,740);
        for (Table t: tableArrayList) {
            t.drawTable(graphics);
            t.drawDishes(graphics);
        }
        for (Chef c: chefArrayList) {
            c.drawChef(graphics);
        }
        for (int i = clientArrayList.size()-1; i >= 0; i--) {
            if (clientArrayList.get(i)!=null) {
                clientArrayList.get(i).drawClient(graphics);
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

        if (random.nextInt(2)==0) {
            tableChoice = table;
        } else {
            tableChoice = tableB;
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
        return new Client(client_id,name,tableChoice,430,720,gender,clientArrayList,movementVariation,this);
    }

    public static int getGold() {
        return gold;
    }

    public static void setGold(int gold) {
        Viewer.gold = gold;
    }
}
