package newSystem;
import java.awt.*;
import javax.swing.*;

/**
 * @author Arun Rawlani
 * <p>The ReservationGUI aims to initialize the GUI of the reservation system. It also sets up the
 * main data model for the system where the GUI User thread and automated Broker threads will manipulate
 * the AirplaneSeats object.</p>
 * <p> Any of the 3 threads i.e. either the user or the automated threads will manipulate the AirplaneSeats
 * object which will then manipulate the buttons in the GUI to respond to kind of action been taken on it</p>
 */
public class ReservationGUI {
	public static JButton[][] buttons = new JButton[50][4];
	public static AirplaneSeats seats = new AirplaneSeats(50,4,buttons);
	/**
	 * Initializing the GUI and starting the automated threads to start manipulating AirplaneSeats object.
	 * @param args
	 */
	public static void main(String[] args){
		initGUI();
		startBrokerThreads();
	}
	
	/**
	 * initGUI is the initializer method for the GUI as well as creates all the UI objects in it.
	 */
	public static void initGUI(){
		JPanel jp = new JPanel(new GridLayout(50,1));
		for(int i =0;i<50;i++){
			JPanel inside = new JPanel(new FlowLayout());
			JTextField jtf = new JTextField("Seat Row "+(i+1));
			jtf.setEditable(false);
			jtf.setPreferredSize(new Dimension(100,20));
			inside.add(jtf);
			for(int j = 0;j<4;j++){
				JButton jb = new JButton(""+(j+1));
				jb.setPreferredSize(new Dimension(120,20));
				buttons[i][j] = jb;
				jb.addActionListener(
						new ButtonClickListener(i, j, seats));
				inside.add(jb);
				if(j == 1) inside.add(Box.createRigidArea(new Dimension(60,0)));
			}
			jp.add(inside);

		}
		JScrollPane scrollPane = new JScrollPane(jp);
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);;
		JFrame frame = new JFrame("Seat Reservation Form");
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); //displays the final GUI form
	}
	
	/**
	 * The thread objects are formed that automatically start reserving seats in the system
	 */
	public static void startBrokerThreads(){
		Thread t1 = new Thread(new Broker(seats, "ID 1"));
		Thread t2 = new Thread(new Broker(seats, "ID 2"));
		t2.start();
		t1.start();
	}
}
