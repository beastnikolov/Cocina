import javax.imageio.ImageIO;
import javax.management.relation.RelationNotFoundException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ChristmasLight implements Runnable {
    private BufferedImage xmaslight;
    private BufferedImage lightgreen;
    private BufferedImage lightblue;
    private int posX;
    private int posY;
    private int width;
    private int height;
    private boolean go = true;
    private Random random = new Random();

    public ChristmasLight(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        try {
            xmaslight = ImageIO.read(new File("src//xmaslight1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width = xmaslight.getWidth();
        this.height = xmaslight.getHeight();
    }

    public void loadLight() throws IOException {
        if (random.nextInt(3) == 0) {
            xmaslight = ImageIO.read(new File("src//xmaslight1.png"));
        } else if (random.nextInt(3) == 1) {
            xmaslight = ImageIO.read(new File("src//xmaslight2.png"));
        } else {
            xmaslight = ImageIO.read(new File("src//xmaslight3.png"));
        }
    }

    public void paint(Graphics g) {
        g.drawImage(xmaslight,posX,posY,width,height,null);
    }

    private void animate() {
        try {
            loadLight();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
