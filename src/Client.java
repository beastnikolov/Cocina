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
    private boolean eaten = false;
    private String facing = "back";


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
            updateSprite("back");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSprite(String facingPos) throws IOException {
        if (facingPos.equals("front")) {
            this.setFacing("front");
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + Gender.toLowerCase() + "_front.png"));
        } else if (facingPos.equals("back")) {
            this.setFacing("back");
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + Gender.toLowerCase() + "_back.png"));
        } else if (facingPos.equals("left")) {
            this.setFacing("left");
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + Gender.toLowerCase() + "_left.png"));
        } else if (facingPos.equals("right")) {
            this.setFacing("right");
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + Gender.toLowerCase() + "_right.png"));
        }

    }


    @Override
    public void run() {
        Random random = new Random();
        while (go) {
            try {
                movetoTable(table);

            } catch (InterruptedException |IOException e) {
                e.printStackTrace();
            }
            try {
                takeDish();
                if (eaten) {
                    viewer.setStatisticClientsServed(viewer.getStatisticClientsServed() + 1);
                    movetoExit(table);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            }
        }


    private void movetoTable(Table table) throws InterruptedException, IOException {
        while (yPos > table.getyPos() + 125 + movementVariation) {
            yPos--;
            Thread.sleep(10);
        }
        if (table.getTableType().equals("TableA")) {
            updateSprite("left");
            while (xPos > table.getxPos() + 20 + movementVariation) {
                xPos--;
                Thread.sleep(10);
            }
            updateSprite("back");
            while (yPos > table.getyPos() + 45) {
                yPos--;
                Thread.sleep(10);
            }
        } else if (table.getTableType().equals("TableB")) {
            updateSprite("left");
            while (xPos > table.getxPos() + 20) {
                xPos--;
                Thread.sleep(10);
            }
            updateSprite("back");
            while (yPos > table.getyPos() + 45) {
                yPos--;
                Thread.sleep(10);
            }
        } else if (table.getTableType().equals("TableC")) {
            updateSprite("right");
            while (xPos < table.getxPos() + 20 + movementVariation) {
                xPos++;
                Thread.sleep(10);
            }
            updateSprite("back");
            while (yPos > table.getyPos() + 45) {
                yPos--;
                Thread.sleep(10);
            }
        }

    }

    private void movetoExit(Table table) throws InterruptedException, IOException {
        if (table.getTableType().equals("TableA")) {
            updateSprite("front");
            while (yPos < table.getyPos() +150 + movementVariation) {
                yPos++;
                Thread.sleep(10);
            }
            updateSprite("right");
            while (xPos < table.getxPos() + 240 + movementVariation) {
                xPos++;
                Thread.sleep(10);
            }
        } else if (table.getTableType().equals("TableB")) {
            updateSprite("front");
            while (yPos < table.getyPos() + 150 + movementVariation) {
                yPos++;
                Thread.sleep(10);
            }
            updateSprite("right");
            while (xPos < table.getxPos() + 110 + movementVariation) {
                xPos++;
                Thread.sleep(10);
            }
        } else if (table.getTableType().equals("TableC")) {
            updateSprite("front");
            while (yPos < table.getyPos() + 125 + movementVariation) {
                yPos++;
                Thread.sleep(10);
            }
            updateSprite("left");
            while (xPos > table.getxPos() - (100 + movementVariation)) {
                xPos--;
                Thread.sleep(10);
            }
        }
        updateSprite("front");
        while (yPos < 720) {
            yPos++;
            Thread.sleep(10);
        }
        leaveRestaurant();
        //System.out.println("Client: " + name + " | ID: " + id + " left.");
    }

    public void leaveRestaurant() {
        clientArrayList.remove(this);
        go = false;
    }



    public void drawClient(Graphics g){
        g.drawImage(sprite,xPos,yPos,32,32,null);
        g.setColor(Color.white);
        g.setFont(new Font("MS Gothic",Font.PLAIN,10));
        g.drawString(name,xPos+5,yPos);
    }

    private void takeDish() throws InterruptedException {
        Thread.sleep(200);
        table.takeDish(this);
        Thread.sleep(100);
        pay();
    }

    private void pay() {
        //System.out.println(table.getTableType() + " | Current dish price: " + table.getDishPrice());
        if (table.getTableType().equals("TableA")) {
            viewer.setGold(viewer.getGold() + table.getDishPrice());
            viewer.setStatisticGoldEarned(viewer.getStatisticGoldEarned() + table.getDishPrice());
        } else if (table.getTableType().equals("TableB")) {
            viewer.setGold(viewer.getGold() + (table.getDishPrice() - 2));
            viewer.setStatisticGoldEarned(viewer.getStatisticGoldEarned() + (table.getDishPrice() - 2));
        } else if (table.getTableType().equals("TableC")) {
            viewer.setGold(viewer.getGold() + (table.getDishPrice() - 4));
            viewer.setStatisticGoldEarned(viewer.getStatisticGoldEarned() + (table.getDishPrice() - 4));
        }

    }

    public Rectangle getClientCollider() {
        return new Rectangle(xPos,yPos,64,64);
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }
}
