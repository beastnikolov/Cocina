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

    public Chef(int id,Table table,int xPos, int yPos) {
        this.id = id;
        this.table = table;
        this.xPos = xPos;
        this.yPos = yPos;
        try {
            sprite = ImageIO.read(new File("src//zchef.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (go) {
            movetoKitchen();
            movetoCounter();
            Random random = new Random();
            if (random.nextInt(500) == 4) {
                Cook(random.nextInt(4));
            }
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
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void movetoKitchen() {
        while (xPos > 160) {
            xPos--;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (yPos > 180) {
            yPos--;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        inKitchen = true;
        try {
            Thread.sleep(100);
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
        while (xPos < 400) {
            xPos++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Rectangle getChefCollider() {
        return new Rectangle(xPos,yPos,64,64);
    }
}
