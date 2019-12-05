import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Table {
    private int dishes;
    private boolean tableEmpty = false;
    private BufferedImage dishSprite;
    private String tableType;
    private int xPos;
    private int yPos;


    public Table(int dishes,int xPos,int yPos,String tableType) {
        this.dishes = dishes;
        this.xPos = xPos;
        this.yPos = yPos;
        this.tableType = tableType;
        if (tableType.equals("Fish")) {
            try {
                dishSprite = ImageIO.read(new File("src//zdish.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                dishSprite = ImageIO.read(new File("src//zcake.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void drawTable(Graphics g) {
        g.setColor(Color.red);
       // g.drawRect(xPos, yPos, 190, 60);
    }

    public void drawDishes(Graphics g) {
        int whiteSpace = 0;

        g.setColor(Color.red);
        if (tableType.equals("Fish")) {
            whiteSpace = 0;
        } else {
            whiteSpace = 40;
        }
        for (int i = 0; i < dishes; i++) {
            g.drawImage(dishSprite,xPos+whiteSpace, yPos+20,32,32, null);
            whiteSpace = whiteSpace + 30;
        }
    }

    public synchronized void takeDish(int id,String name) {
        while (tableEmpty) {
            try {
              //  System.out.println("Client: " + name + " | ID: " + id + " is waiting for a dish.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (dishes == 0) {
            tableEmpty = true;
        } else {
            this.dishes--;
           // System.out.println("Client: " + name + " | ID: " + id + " has taken a dish.");
        }
    }

    public synchronized void putDish(int id, int numberofDishes) {
       // System.out.println("Chef ID: " + id + " added " + numberofDishes + " dishes to the table");
        this.dishes = this.dishes + numberofDishes;
        if (this.dishes > 6) {
            this.dishes = 6;
        }
        tableEmpty = false;
        notifyAll();
    }

    public int getDishes() {
        return dishes;
    }

    public Rectangle getTablecollider() {
        return new Rectangle(xPos,yPos,190,60);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}
