import javafx.scene.control.Tab;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Viewer extends Canvas implements Runnable{
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
        this.createBufferStrategy(2);
        createTables();
        createChefs();
        createClients();
        while (go) {
            this.paint();
            /*System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x +
                    ", " +
                    MouseInfo.getPointerInfo().getLocation().y + ")");

             */


        }
    }

    private void createChefs() {
        chef = new Chef(1,table,400,260);
        thread = new Thread(chef);
        thread.start();
        chefArrayList.add(chef);
    }

    private void createTables() {
        table = new Table(6,220,325,"Fish");
        tableArrayList.add(table);
        tableB = new Table(4,480,325,"Cake");
        tableArrayList.add(tableB);
    }

    private void createClients() {
        client = new Client(1,table,370,380,"Male");
        thread = new Thread(client);
        thread.start();
        clientArrayList.add(client);
        client = new Client(2,table,400,380,"Female");
        thread = new Thread(client);
        thread.start();
        clientArrayList.add(client);
        client = new Client(3,table,430,380,"Female");
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
        //graphics.setColor(Color.ORANGE);
        //graphics.fillRect(50,50,1024-100,768-100);
        for (Table t: tableArrayList) {
            t.drawTable(graphics);
            t.drawDishes(graphics);
        }
        for (Chef c: chefArrayList) {
            c.drawChef(graphics);
        }
        for (Client cl: clientArrayList) {
            cl.drawClient(graphics);
        }
        bs.show();
        super.paint(graphics);
        graphics.dispose();
    }



}
