package graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class listeners
{
		
	private final int width = 800, height = 600;
	
	private final int rectY = height/2, rectHeight = 50, rectWidth = 50, speed = 3;
	private int rectX = width/2;
	
	// variable to keep track of our speed
	private int rectSpeed = 0;
	
	// coordinates and radius for our circle
	private int circleX, circleY;
	private final int diam = 30;
	
	// boolean to keep track of whether we should draw our circle
	private boolean drawCircle = false;
	
	// our JFrame will be an instance variable so that we can use it to repaint
	private JFrame frame ;
	
    //graphics initializations
	public listeners() {
		
		// the frame holds the panel
		frame = new JFrame();
		
		// set the window size - notice, no magic numbers!
		frame.setSize(width, height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add our customized panel to the container
		frame.add(new JPanel() 
		{
			public void paint(Graphics g) {
			
				g.setColor(Color.RED);
				g.fillRect(rectX, rectY, rectWidth, rectHeight);
				
				if (drawCircle) {
					g.setColor(Color.blue);
					g.fillOval(circleX, circleY, diam, diam);
				}
				
			}
		});
		
		frame.setLocationRelativeTo(null);
	
		frame.setResizable(false);

		frame.setVisible(true);
		
		// focus is the ability for the program to pay attention to just one component - for example, if you have multiple 
		// text input boxes, we need to know which box to focus on at all times
		frame.setFocusable(true);
		
		// add the listeners onto our container
		frame.addKeyListener(new KeyListener() {

			//note that this only activates once when the key is pressed
			// also - you'll find that Java likes certain keys more than others -
			// play around with which keys to use
			public void keyPressed(KeyEvent e) 
			{
				if (e.getKeyChar() == 'k')
					rectSpeed = speed;
				else if (e.getKeyChar() == 'j')
					rectSpeed = -speed;
				else if (e.getKeyChar() == 'i')
					rectSpeed = (-1/2)*speed;
				else if (e.getKeyChar() == 'm')
					rectSpeed = (int) (0.5*speed);
			}

			// what we want to happen when the user stops pressing a key.
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == 'k' || e.getKeyChar() == 'j' || e.getKeyChar() == 'i' || e.getKeyChar() == 'm')
					rectSpeed = 0;
			}


			public void keyTyped(KeyEvent e) {}
		});
		
		
		frame.addMouseListener(new MouseListener() {
			// what we want to happen when the user presses with the mouse
			public void mousePressed(MouseEvent e) {
				circleX = e.getX() - diam/2;
				circleY = e.getY() - diam/2;
				drawCircle = true;
			}
			
			public void mouseReleased(MouseEvent e) {
				drawCircle = false;
			}

			// we need to have these methods since we implement the listeners, but 
			// we don't need them to have functionality
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		// get our functionality going
		run();
	}

	// This is what we want the code to do as the panel is open.
	public void run() {
		
		// since I have the default close operation, a while(true) condition is ok
		while (true) {
			
			// if we're changing coordinates, etc. of our graphics objects, 
			// always need to repaint!
			frame.getContentPane().repaint();
			
			// do your logic here
			rectX += rectSpeed;
			
			try 
			{
				Thread.sleep(60);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
		
	// very simple main method - create our graphics object
	public static void main(String[] args) {
		listeners runner = new listeners(); 
	}
}
