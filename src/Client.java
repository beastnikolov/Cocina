import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Client implements Runnable {
    private int id;
    private String name;
    private String Gender;
    private boolean go = true;
    private Table table;
    private int xPos;
    private int yPos;
    private BufferedImage sprite;
    private int movementVariation;
    private Viewer viewer;
    private ArrayList<Client> clientArrayList;


    public Client(int id,String name, Table table, int xPos, int yPos, String gender, ArrayList<Client> clientArrayList,int movementVariation,Viewer viewer) {
        this.id = id;
        this.name = name;
        this.table = table;
        this.xPos = xPos;
        this.yPos = yPos;
        this.Gender = gender;
        this.clientArrayList = clientArrayList;
        this.movementVariation = movementVariation;
        this.viewer = viewer;
        try {
            if (this.Gender.equals("Male")) {
                sprite = ImageIO.read(new File("src//zman.png"));
            } else {
                sprite = ImageIO.read(new File("src//zwoman.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        Random random = new Random();
        while (go) {
            try {
                movetoTable(table);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (random.nextInt(500) == 5) {
                try {
                    takeDish();
                    movetoExit(table);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void movetoTable(Table table) throws InterruptedException {
        while (yPos > table.getyPos() + 140 + movementVariation) {
            yPos--;
            Thread.sleep(10);
        }
        if (table.getTableType().equals("Fish")) {
            while (xPos > table.getxPos() + 50 + movementVariation) {
                xPos--;
                Thread.sleep(10);
            }
            while (yPos > table.getyPos() + 100 + movementVariation) {
                yPos--;
                Thread.sleep(10);
            }
        } else {
            while (xPos < table.getxPos() + 50 + movementVariation) {
                xPos++;
                Thread.sleep(10);
            }
            while (yPos > table.getyPos() + 100 + movementVariation) {
                yPos--;
                Thread.sleep(10);
            }
        }

    }

    private void movetoExit(Table table) throws InterruptedException {
        if (table.getTableType().equals("Fish")) {
            while (yPos < table.getyPos() +150 + movementVariation) {
                yPos++;
                Thread.sleep(10);
            }
            while (xPos < table.getxPos() + 200 + movementVariation) {
                xPos++;
                Thread.sleep(10);
            }
        } else {
            while (yPos < table.getyPos() + 150 + movementVariation) {
                yPos++;
                Thread.sleep(10);
            }
            while (xPos > table.getxPos() - 50 + movementVariation) {
                xPos--;
                Thread.sleep(10);
            }
        }
        while (yPos < 720) {
            yPos++;
            Thread.sleep(10);
        }
        leaveRestaurant();
    }

    public void leaveRestaurant() {
        clientArrayList.remove(this);
        go = false;
    }


    public void drawClient(Graphics g){
        g.drawImage(sprite,xPos,yPos,64,64,null);
        g.setColor(Color.white);
        g.setFont(new Font("MS Gothic",Font.PLAIN,10));
        g.drawString(name,xPos+name.length(),yPos);
    }

    private void takeDish() throws InterruptedException {
        Thread.sleep(200);
        table.takeDish(id);
        Thread.sleep(100);
        pay();
    }

    private void pay() {
        if (table.getTableType().equals("Fish")) {
            viewer.setGold(viewer.getGold() + 10);
        } else {
            viewer.setGold(viewer.getGold() + 5);
        }
    }

    public Rectangle getClientCollider() {
        return new Rectangle(xPos,yPos,64,64);
    }



}
