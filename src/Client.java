import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Client implements Runnable {
    private int id;
    private String Gender;
    private boolean go = true;
    private Table table;
    private int xPos;
    private int yPos;
    private BufferedImage sprite;

    public Client(int id,Table table,int xPos, int yPos,String gender) {
        this.id = id;
        this.table = table;
        this.xPos = xPos;
        this.yPos = yPos;
        this.Gender = gender;
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
            if (random.nextInt(500) == 5) {
                takeDish();
            }
        }
    }

    public void drawClient(Graphics g){
        g.drawImage(sprite,xPos,yPos,64,64,null);
    }

    private void takeDish() {
        table.takeDish(id);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getClientCollider() {
        return new Rectangle(xPos,yPos,64,64);
    }

}
