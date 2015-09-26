package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class BackgroundFrame {
	public int width;
	public int height;
	public JPanel main_menu;


	public BackgroundFrame(int width, int height) {
		this.width = width;
		this.height = height;
		final JFrame background = new JFrame();
		background.setSize(this.width, this.height);
		
		JPanel main_menu = new JPanel();
		main_menu.setLayout(new BoxLayout(main_menu, BoxLayout.Y_AXIS));
		main_menu.add(Box.createRigidArea(new Dimension(0,320)));
		
		JLabel title = new JLabel("Loderunner");
		title.setFont(new Font("Times New Roman", Font.BOLD, 60));
		title.setForeground(Color.RED);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		main_menu.add(title);
		main_menu.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton new_game = new JButton("New Game");
		new_game.setAlignmentX(Component.CENTER_ALIGNMENT);
		main_menu.add(new_game);
		main_menu.add(Box.createRigidArea(new Dimension(0,20)));

		JButton load_game = new JButton("Load Game");
		load_game.setAlignmentX(Component.CENTER_ALIGNMENT);
		main_menu.add(load_game);
		main_menu.add(Box.createRigidArea(new Dimension(0,20)));

		JButton exit = new JButton("Exit");
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		main_menu.add(exit);
		main_menu.add(Box.createRigidArea(new Dimension(0,20)));

		JButton help = new JButton("Help");
		help.setAlignmentX(Component.CENTER_ALIGNMENT);
		main_menu.add(help);

		class MyButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {

				JButton button_selected = (JButton) e.getSource();
				if (button_selected.getText() == "Load Game") {
					try {
						JPanel other = (JPanel) button_selected.getParent();
						background.remove(other);
						background.repaint();
						Scanner save = new Scanner(new File("src/loderunner/Level_Data"));
						int lvl = save.nextInt();
						int score = save.nextInt();
						int HighScore = save.nextInt();
						String name = save.next();
						Scanner scanner = new Scanner(new File("src/loderunner/level_"+lvl));
						Block[][] level = new Block[22][22];
						for (int j = 0; j < 22; j++) {
							for (int i = 0; i < 22; i++) {
								int value = scanner.nextInt();
								if(value == 1){
									level[i][j] = new Brick(i, j);
								}
								else if(value == 0){
									level[i][j] = new Air(i, j);
								}
								else if(value == 2){
									level[i][j] = new Ladder(i, j);
								}
								else if(value == 3){
									level[i][j] = new Gold(i, j);
								}
								else if(value == 4){
									level[i][j] = new Board(i, j);
								}
								else if(value == 5){
									level[i][j] = new Pipe(i, j);
								}
								else if(value == 6){
									level[i][j] = new Plank(i, j);
								}
								else if(value == 7){
									level[i][j] = new Spawn(i, j);
								}
								
							}
						}
						BackgroundComponent data = new BackgroundComponent(level, name);
						data.setScore(score, HighScore);
						data.setlevel(lvl);
						background.add(BorderLayout.CENTER, data);
						background.validate();
						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					};
				}

				if (((JButton) e.getSource()).getText() == "Exit")
					System.exit(0);

				if (((JButton) e.getSource()).getText() == "New Game") {
					String name = JOptionPane
							.showInputDialog("What is your name?");
					if(!(name == null)){
						if(name.isEmpty()){
							name = "Anonymous";
						}
					JPanel other = (JPanel) button_selected.getParent();
					background.remove(other);
					background.repaint();
					try {
						Scanner scanner = new Scanner(new File("src/loderunner/level_1"));
						Block[][] level1 = new Block[22][22];
						for (int j = 0; j < 22; j++) {
							for (int i = 0; i < 22; i++) {
								int value = scanner.nextInt();
								if(value == 1){
									level1[i][j] = new Brick(i, j);
								}
								else if(value == 0){
									level1[i][j] = new Air(i, j);
								}
								else if(value == 2){
									level1[i][j] = new Ladder(i, j);
								}
								else if(value == 3){
									level1[i][j] = new Gold(i, j);
								}
								else if(value == 4){
									level1[i][j] = new Board(i, j);
								}
								else if(value == 5){
									level1[i][j] = new Pipe(i, j);
								}
								else if(value == 6){
									level1[i][j] = new Plank(i, j);
								}
								else if(value == 7){
									level1[i][j] = new Spawn(i, j);
								}
							}
						}
						BackgroundComponent data = new BackgroundComponent(level1,name);
						data.setlevel(1);
						background.add(BorderLayout.CENTER, data);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					

					background.validate();

				}
				}
				if (((JButton) e.getSource()).getText() == "Help") {
					help_function();
				}
			}

		}

		ActionListener main_listener = new MyButtonListener();

		exit.addActionListener(main_listener);

		new_game.addActionListener(main_listener);


		help.addActionListener(main_listener);

		main_menu.setBackground(Color.BLACK);
		main_menu.setOpaque(true);

		background.add(BorderLayout.CENTER, main_menu);
		background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background.setVisible(true);

	}

	public void help_function() {
		String s = "This box teaches you to play lode runner\n"
				+ " Use the right and left arrow keys to move your character"
				+ " Left or Right.\n"
				+ " You can break blocks by pressing x or y.\n"
				+ " You can move up and down arrows by moving\n"
				+ " upto ladders and pressing either the up or\n"
				+ "down arrow keys.\n";

		String html1 = "<html><body style='width: ";
		String html2 = "px'>";

		JOptionPane.showMessageDialog(null, new JLabel(html1 + "200" + html2
				+ s));
		// JOptionPane.showMessageDialog(null, new JLabel(html1+"300"+html2+s));
		//
		// JFrame help_frame = new JFrame();
		// help_frame.setSize(300, 300);
		//
		// JPanel help_panel = new JPanel();
		// help_panel.setBackground(Color.BLACK);
		// help_panel.setOpaque(true);
		//
		// JLabel help_label = new JLabel("<html>" + help_text + "</html>");
		// help_panel.add(help_label);
		// help_frame.add(help_panel);
		//
		// help_frame.getContentPane().setBackground(Color.BLUE);
		// help_frame.setVisible(true);
	}

}
