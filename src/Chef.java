import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Chef implements Runnable {
    private int id;
    private boolean inKitchen = false;
    private boolean go = true;
    private Table table;
    private int xPos;
    private int yPos;
    private BufferedImage sprite;
    private int movementVariation;

    public Chef(int id,Table table,int xPos, int yPos,int movementVariation) {
        this.id = id;
        this.table = table;
        this.xPos = xPos;
        this.yPos = yPos;
        this.movementVariation = movementVariation;
        try {
            sprite = ImageIO.read(new File("src//zchef.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        while (go) {
            movetoKitchen();
            movetoCounter();
            Cook(random.nextInt(4));
            }
        }


    public void drawChef(Graphics g) {
        if (!inKitchen) {
            g.drawImage(sprite,xPos,yPos,64,64,null);
        }
    }

    private void Cook(int dishes) {
        if (dishes == 0) {
            table.putDish(id,1);
        } else {
            table.putDish(id,dishes);
        }
    }

    private void movetoKitchen() {
        while (xPos > 160 + movementVariation) {
            xPos--;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (yPos > 180 + movementVariation) {
            yPos--;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        inKitchen = true;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void movetoCounter() {
        inKitchen = false;
        while (yPos < 260) {
            yPos++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (xPos < 400 + movementVariation) {
            xPos++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getChefCollider() {
        return new Rectangle(xPos,yPos,64,64);
    }
}
