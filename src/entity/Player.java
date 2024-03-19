package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY= gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
				
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				
			}
			//Check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//if collision is false, the player can't move
			if(collisionOn == false) {
				switch(direction) {
					case "up":
						worldY -= speed;
						break;
					case "down":
						worldY += speed;
						break;
					case "left":
						worldX -= speed;
						break;
					case "right":
						worldX += speed;
						break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNumber == 1) {
					spriteNumber = 2; 
				}
				else{
					spriteNumber = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		switch(direction) {
		case"up":
			if(spriteNumber ==1) 
				image = up1;
			if(spriteNumber==2) 
				image = up2;
			break;
		case"down":
			if(spriteNumber==1)
				image = down1;
			if(spriteNumber==2)
				image = down2;
			break;
		case"left":
			if(spriteNumber==1)
				image = left1;
			if(spriteNumber==2)
				image = left2;
			break;
		case"right":
			if(spriteNumber==1)
				image = right1;
			if(spriteNumber==2)
				image = right2;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_left_2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
