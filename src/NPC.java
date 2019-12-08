import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NPC implements Runnable {
    private boolean go = true;
    private String npc_name;
    private BufferedImage sprite;
    private int posX;
    private int posY;
    private int npcVariation;

    public NPC(String npc_name, int posX, int posY, int npcVariation) {
        this.npc_name = npc_name;
        this.posX = posX;
        this.posY = posY;
        this.npcVariation = npcVariation;
        try {
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_front.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (go) {

            try {
                if (this.npcVariation==3) {
                    animateRodent();
                } else {
                    animate();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void drawNPC(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("MS Gothic",Font.PLAIN,10));
        if (npcVariation==1) {
            g.drawImage(sprite,posX,posY,38,38,null);
            g.drawString(npc_name,posX+5,posY);
        } else if (npcVariation==2) {
            g.drawImage(sprite,posX,posY,54,54,null);
            g.drawString(npc_name,posX+15,posY);
        } else {
            g.drawImage(sprite,posX,posY,24,24,null);
        }
        if (npcVariation==3) {
            //
        }
    }

    private void animateRodent() throws IOException, InterruptedException {
        while (this.posY < 600) {
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_front.png"));
            posY++;
            Thread.sleep(10);
        }
        while (this.posX > 200) {
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_left.png"));
            posX--;
            Thread.sleep(10);
        }
        while (this.posY > 300) {
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_back.png"));
            posY--;
            Thread.sleep(10);
        }
        while (this.posX < 550) {
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_right.png"));
            posX++;
            Thread.sleep(10);
        }
    }

    private void animate() throws IOException {
        Random random = new Random();
        if (random.nextInt(5)==1) {
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_front.png"));
        } else if (random.nextInt(5)==2){
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_right.png"));
        } else if (random.nextInt(5)==3){
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_left.png"));
        } else if (random.nextInt(5)==4){
            sprite = ImageIO.read(new File("src//Sprites//Humans//npc" + npcVariation + "_back.png"));
        }

    }
}
