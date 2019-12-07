import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthUI;
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
    private String facing = "front";
    private String chefType = "";

    public Chef(int id,Table table,int xPos, int yPos,int movementVariation) {
        this.id = id;
        this.table = table;
        this.xPos = xPos;
        this.yPos = yPos;
        this.movementVariation = movementVariation;
        try {
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void loadImages() throws IOException {
        if (table.getTableType().equals("TableA")) {
            this.chefType = "chefA";
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + chefType + "_front.png"));
        } else if (table.getTableType().equals("TableB")) {
            this.chefType = "chefB";
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + chefType + "_front.png"));
        } else if (table.getTableType().equals("TableC")) {
            this.chefType = "chefC";
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + chefType + "_front.png"));
        }
    }

    public void updateSprite(String facingPos) throws IOException {
        if (facingPos.equals("front")) {
            this.setFacing("front");
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + chefType + "_front.png"));
        } else if (facingPos.equals("back")) {
            this.setFacing("back");
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + chefType + "_back.png"));
        } else if (facingPos.equals("left")) {
            this.setFacing("left");
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + chefType + "_left.png"));
        } else if (facingPos.equals("right")) {
            this.setFacing("right");
            sprite = ImageIO.read(new File("src//Sprites//Humans//" + chefType + "_right.png"));
        }

    }

    @Override
    public void run() {
        Random random = new Random();
        while (go) {
            try {
                movetoKitchen();
                movetoCounter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cook(random.nextInt(4));
            }
        }


    public void drawChef(Graphics g) {
        if (!inKitchen) {
            g.drawImage(sprite,xPos,yPos,32,32,null);
        }
    }

    private void Cook(int dishes) {
        if (dishes == 0) {
            table.putDish(id,1);
        } else {
            table.putDish(id,dishes);
        }
    }

    private void movetoKitchen() throws InterruptedException {
        Random random = new Random();
        if (this.chefType.equals("chefA")) {
            while (xPos < 190 + movementVariation) {
                try {
                    updateSprite("right");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                xPos++;
                Thread.sleep(10);
            }
        } else {
            while (xPos > 190 + movementVariation) {
                try {
                    updateSprite("left");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                xPos--;
                Thread.sleep(10);
            }
        }
        while (yPos > 90 + movementVariation) {
            try {
                updateSprite("back");
            } catch (IOException e) {
                e.printStackTrace();
            }
            yPos--;
            Thread.sleep(10);
        }

        inKitchen = true;
        if (random.nextInt(2) == 0) {
            Thread.sleep(5000);
        } else {
            Thread.sleep(4000);
        }
    }

    private void movetoCounter() throws InterruptedException {
        try {
            updateSprite("front");
        } catch (IOException e) {
            e.printStackTrace();
        }
        inKitchen = false;
        while (yPos < 135) {
            yPos++;
            Thread.sleep(10);
        }
        if (chefType.equals("chefA")) {
            while (xPos > 140 + movementVariation) {
                try {
                    updateSprite("left");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                xPos--;
                Thread.sleep(10);
            }
        } else if (chefType.equals("chefB")) {
            while (xPos < 260 + movementVariation) {
                try {
                    updateSprite("right");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                xPos++;
                Thread.sleep(10);
            }
        } else if (chefType.equals("chefC")) {
            while (xPos < 470 + movementVariation) {
                try {
                    updateSprite("right");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                xPos++;
                Thread.sleep(10);
            }
        }
        try {
            updateSprite("front");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(300);
    }

    public Rectangle getChefCollider() {
        return new Rectangle(xPos,yPos,64,64);
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }
}
