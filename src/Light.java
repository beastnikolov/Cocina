import javax.imageio.ImageIO;
import javax.management.relation.RelationNotFoundException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Light implements Runnable {
    private BufferedImage light;
    private int posX;
    private int posY;
    private int width;
    private int height;
    private boolean go = true;

    public Light(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        try {
            light = ImageIO.read(new File("src//light2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width = light.getWidth();
        this.height = light.getHeight();
    }

    public void paint(Graphics g) {
        g.drawImage(light,posX,posY,width,height,null);
    }

    private void animate() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            this.height = 50;
            this.width = 50;
        } else {
            this.height = 48;
            this.width = 48;
        }
    }

    @Override
    public void run() {
        while (go) {
            animate();
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
