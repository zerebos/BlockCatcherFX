import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class BlockCatcher extends Application {
	
	public static long lastTime = 0;
	
	private Boolean batMoving = false;
	private Boolean toRight = false;
	Player bat = new Player();
	double points = 0;
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Block Catcher");
        Group root = new Group();
        Scene scene = new Scene(root, Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT, Color.WHITE);
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int i=0; i<1; i++) {
        	blocks.add(Block.generateBlock());
        }
        Text roundCaption = new Text();
        roundCaption.setText("POINTS:");
        roundCaption.setTextOrigin(VPos.TOP);
        roundCaption.setFill(Color.rgb(51, 102, 51));
        Font f = new Font("Impact", 18);
        roundCaption.setFont(f);
        roundCaption.setTranslateX(0);
        roundCaption.setTranslateY(10);
        Text pointsCaption = new Text();
        pointsCaption.setText("0");
        pointsCaption.setTextOrigin(VPos.TOP);
        pointsCaption.setFill(Color.rgb(51, 102, 51));
        pointsCaption.setFont(f);
        pointsCaption.setTranslateX(70);
        pointsCaption.setTranslateY(10);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        root.getChildren().add(roundCaption);
        root.getChildren().add(pointsCaption);
        root.getChildren().add(bat);
        root.getChildren().addAll(blocks);
        new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				//System.out.println("now " + now + " last " + lastTime);
				long currentDiff = now - lastTime;
				if (currentDiff > .5E9) {
					Block b = Block.generateBlock();
					blocks.add(b);
					root.getChildren().add(b);
					lastTime = now;
				}
				if (batMoving)
					bat.move(toRight);
                Iterator<Block> blockIterator = blocks.iterator();
                while (blockIterator.hasNext()) {
                    Block block = blockIterator.next();
                    block.moveBlock();
                    if (block.exitedStage()) {
                    	block.setVisible(false);
                        blockIterator.remove();
                        //System.out.println(bat.getX()+" "+bat.getY()+" "+bat.getWidth()+" "+bat.getHeight());
                        //System.out.println("Block out ("+ block.getSpeed() + "," + block.getBlockSize() + ") ["+ block.getPointValue() +"], "+blocks.size()+" blocks left.");
                    }
                    if (block.intersects(bat.getX(), bat.getY(), bat.getWidth(), bat.getHeight())) {
                    	block.setVisible(false);
                        blockIterator.remove();
                        points += block.getPointValue();
                        pointsCaption.setText(df.format(points));
                        System.out.println("Points: "+points);
                    }
                }
			}
        	
        }.start();
      
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if ((ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.TRACK_PREV)) {
                	toRight = false;
                    batMoving = true;
                    
                }
                if ((ke.getCode() == KeyCode.RIGHT || ke.getCode() == KeyCode.TRACK_NEXT)) {
                	toRight = true;
                    batMoving = true;
                    
                }
            }
        });
        		

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
            	 if (ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.RIGHT ||
                         ke.getCode() == KeyCode.TRACK_PREV || ke.getCode() == KeyCode.TRACK_NEXT) {
                     batMoving = false;
                     ke.consume();
                 }
            }
        });
        
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent m) {
            	 bat.moveTo(m.getX());
            }
        });
        
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}