import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BufferedDrawing extends JFrame implements ActionListener, ChangeListener {
	
	private JButton randomLineButton = new JButton("Random");
	private Random rand = new Random();
	private BufferedImage image;
	private Graphics2D g2d;
	SpinnerModel model = new SpinnerNumberModel(1, 1, 7, 1);
	JSpinner strokeSize = new JSpinner(model);
	Rectangle r = new Rectangle(5,5,20,20);
	JPanel canvas;

	public BufferedDrawing(String title) {
	super(title);
	setSize(400,400);
	image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	canvas = new JPanel() {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
		};
		canvas.setPreferredSize(new Dimension(getWidth(),getHeight()));
	g2d = (Graphics2D)canvas.getGraphics();
	g2d.setColor(Color.BLACK);
	

		
		JPanel buttons = new JPanel();
		buttons.add(randomLineButton);
		JButton n = new JButton("GO");n.addActionListener(this);
		buttons.add(n);
		g2d.draw(r);
		buttons.add(new JLabel("    Stroke Size:"));
		buttons.add(strokeSize);
		add(buttons,BorderLayout.NORTH);
		add(canvas);
		
		randomLineButton.addActionListener(this);
		strokeSize.addChangeListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(randomLineButton)) {
			Integer x = rand.nextInt(this.getWidth());
			Integer y = rand.nextInt(this.getHeight());
			Integer width = rand.nextInt(this.getWidth()-x);
			Integer height = rand.nextInt(this.getHeight()-y);
			g2d.drawRect(x,y,width,height);

			this.repaint();
		}
		else {
	        ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                //...Perform a task...
	            	r.translate(1, 5);
	            	//g2d.drawImage(image, 0, 0, Color.WHITE,null);
	            	canvas.repaint();
	            	g2d.draw(r);
	                System.out.println("Reading SMTP Info.");
	            }
	        };
	        Timer timer = new Timer(1000, taskPerformer);
	        timer.setRepeats(true);
	        timer.start();
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(strokeSize)) {
			Integer val = (Integer) strokeSize.getValue();
			g2d.setStroke(new BasicStroke((float) val));
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
		BufferedDrawing frame = new BufferedDrawing("Drawing Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
