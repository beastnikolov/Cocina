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
    private int currentUpgrade = 0;
    private int dishPrice = 6;


    public Table(int dishes,int xPos,int yPos,String tableType) {
        this.dishes = dishes;
        this.xPos = xPos;
        this.yPos = yPos;
        this.tableType = tableType;
        try {
            loadTableSprites();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTableSprites() throws IOException {
        if (tableType.equals("TableA")) {
            if (currentUpgrade == 0) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableA1.png"));
            } else if (currentUpgrade == 1) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableA2.png"));
            } else if (currentUpgrade == 2) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableA3.png"));
            }

        } else if (tableType.equals("TableB")) {
            if (currentUpgrade == 0) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableB1.png"));
            } else if (currentUpgrade == 1) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableB2.png"));
            } else if (currentUpgrade == 2) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableB3.png"));
            }
        } else if (tableType.equals("TableC")) {
            if (currentUpgrade == 0) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableDrink.png"));
            } else if (currentUpgrade == 1) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableDrink2.png"));
            } else if (currentUpgrade == 2) {
                dishSprite = ImageIO.read(new File("src//Sprites//Food//zfoodTableDrink3.png"));
            }
        }
    }

    public void drawTable(Graphics g) {
        g.setColor(Color.red);
        //g.drawRect(xPos, yPos, 190, 60);
    }

    public void drawDishes(Graphics g) {
        int whiteSpace = 0;

        g.setColor(Color.red);
        if (tableType.equals("TableA")) {
            whiteSpace = 0;
            g.drawImage(dishSprite,818,165,16,16,null);
        } else if (tableType.equals("TableB")) {
            whiteSpace = 0;
            g.drawImage(dishSprite,818,192,16,16,null);
        } else if (tableType.equals("TableC")) {
            whiteSpace = 0;
            g.drawImage(dishSprite,818,215,16,16,null);
        }
        for (int i = 0; i < dishes; i++) {
            g.drawImage(dishSprite,xPos+whiteSpace, yPos+20,16,16, null);
            whiteSpace = whiteSpace + 12;
        }
    }

    public synchronized void takeDish(Client client) {
        while (tableEmpty) {
            try {
                //System.out.println("Client: " + client.getName() + " | ID: " + client.getId() + " is waiting for a dish.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (dishes <= 0) {
            tableEmpty = true;
        } else {
            this.dishes--;
            client.setEaten(true);
            //System.out.println("Client: " + client.getName() + " | ID: " + client.getId() + " has taken a dish.");
        }
    }

    public synchronized void putDish(int id, int numberofDishes) {
        //System.out.println("Chef ID: " + id + " added " + numberofDishes + " dishes to the table");
        this.dishes = this.dishes + numberofDishes;
        if (this.dishes > 6) {
            this.dishes = 6;
        }
        tableEmpty = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
        //System.out.println("Table " +  this.getTableType() + " | Current dishes: " + dishes);
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

    public int getCurrentUpgrade() {
        return currentUpgrade;
    }

    public void setCurrentUpgrade(int currentUpgrade) {
        this.currentUpgrade = currentUpgrade;
    }

    public int getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(int dishPrice) {
        this.dishPrice = dishPrice;
    }
}
