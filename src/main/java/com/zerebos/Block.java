package com.zerebos;
import javafx.scene.paint.Color;

public class Block extends javafx.scene.shape.Rectangle {
	
	//setTranslateX
	//contains
	
	private Color blockColor;
	private double blockSpeed;
	private double blockSize;
	
	public Block(Color color, double speed, double size) {
		super();
		this.setX(Utils.randomInt(0, (int) (Config.CANVAS_WIDTH-size)));
		this.setY(0-Config.BLOCK_CEILING_BUFFER-size);
//		this.setX(Utils.randomInt(0, (int) (Config.CANVAS_WIDTH-size)));
//		this.setY(Utils.randomInt(0, (int) (Config.CANVAS_HEIGHT-size)));
		this.setWidth(size);
		this.setHeight(size);
		this.setFill(color);
		this.blockColor = color;
		this.blockSpeed = speed;
		this.blockSize = size;
	}
	
	public void moveBlock() {
		this.setY(this.getY() + (this.blockSpeed*Config.BLOCK_SPEED_BASE));
	}
	
	public Boolean exitedStage() {
		if (this.getTranslateY()+this.getY() > Config.CANVAS_HEIGHT)
			return true;
		else
			return false;
	}
	
	public double getPointValue() {
		double speedVal = ((this.blockSpeed-Config.BLOCK_SPEED_MIN)/(Config.BLOCK_SPEED_MAX-Config.BLOCK_SPEED_MIN));
		double sizeVal = ((1/(Config.BLOCK_SIZE_MIN-Config.BLOCK_SIZE_MAX))*this.blockSize + (Config.BLOCK_SIZE_MAX/(Config.BLOCK_SIZE_MAX - Config.BLOCK_SIZE_MIN)));
		return speedVal*sizeVal*Config.BLOCK_POINT_MAX;
	}
	
	public Color getColor() {return this.blockColor;}
	public double getSpeed() {return this.blockSpeed;}
	public double getBlockSize() {return this.blockSize;}
	
	public static Block generateBlock() {
		return new Block(Utils.randomColor(), Utils.randomDouble(Config.BLOCK_SPEED_MIN, Config.BLOCK_SPEED_MAX), Utils.randomDouble(Config.BLOCK_SIZE_MIN, Config.BLOCK_SIZE_MAX));
	}
	
}
