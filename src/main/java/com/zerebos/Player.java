package com.zerebos;
public class Player extends javafx.scene.shape.Rectangle {
	
	//setTranslateX
	//contains
	
	public Player() {
		super();
		this.setX(0);
		this.setY(Config.CANVAS_HEIGHT-20-Config.PLAYER_HEIGHT);
		this.setWidth(Config.PLAYER_WIDTH);
		this.setHeight(Config.PLAYER_HEIGHT);
		this.setFill(Config.PLAYER_COLOR);
		this.setX((Config.CANVAS_WIDTH/2)-(Config.PLAYER_WIDTH/2));
		this.setArcHeight(15);
		this.setArcWidth(15);
	}
	
    public void move(Boolean toRight) {
        double x = this.getX();
        x += toRight ? Config.PLAYER_SPEED : -Config.PLAYER_SPEED;
        if (x < 0) {
            x = 0;
        }
        if (x + Config.PLAYER_WIDTH > Config.CANVAS_WIDTH) {
            x = Config.CANVAS_WIDTH - Config.PLAYER_WIDTH;
        }
        this.setX(x);
    }
    
    public void moveTo(double x) {
    	double newx = x-Config.PLAYER_WIDTH/2;
    	if (newx < 0) {newx = 0;}
    	if (newx > Config.CANVAS_WIDTH - Config.PLAYER_WIDTH) {newx=Config.CANVAS_WIDTH - Config.PLAYER_WIDTH;}
        
        this.setX(newx);
    }
	
}
