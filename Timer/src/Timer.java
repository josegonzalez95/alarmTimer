

import java.awt.Font;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Timer {
	static String time;
	static JFrame frame;
	static int count;
	boolean check = false;
	public Timer() {
		frame = new JFrame();
		frame.setLocation(750,250);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setFocusable(true);



		JPanel panel = new JPanel(new GridBagLayout());
		JLabel timer = new JLabel("Seconds left: ");



		panel.add(timer);

		timer.setFont(new Font(timer.getFont().getName(), Font.PLAIN, 40));



		Scanner scan = new Scanner(System.in);
		time = JOptionPane.showInputDialog(frame, "Enter a number to set timer in seconds: ");

		frame.add(panel);
		count = Integer.parseInt(time);

		new Thread() {
			int counter = Integer.parseInt(time);	
			public void run() {
				while(counter >= 0) {
					timer.setText("Seconds left: " + (counter--));

					try{
						Thread.sleep(1000);
						if(counter < 0) {
							playSong("alarm.wav");	
						}
					} catch(Exception e) {}
				}
			}
		}.start();		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Timer();

	}	
	public static void playSong(String filePath) {
		
		try {
			File musicPath = new File(filePath);
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				JOptionPane.showMessageDialog(frame, "Time is up!");
				clip.stop();
			}else {
				JOptionPane.showMessageDialog(frame, "Can't find file");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}   
}

