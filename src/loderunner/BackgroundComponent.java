package loderunner;

/**
 * @author Team 19 - Nithin Perumal, Timothy Smith, Karan Shishoo
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BackgroundComponent extends JComponent {
	private Block[][] Blocks;
	private Hero hero;
	private Guard guard;
	private Guard guard2;
	private Guard guard3;
	private Guard guard4;
	private int level;
	private int score = 0;
	private int lvlscore = 0;
	private int highscore = 0;
	private String Name;
	private int gold = 0;
	private boolean magicladder;
	private ArrayList<Guard> guards = new ArrayList<Guard>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private int levelRestart;
	private int spawn_X;
	private int spawn_Y;
	private int guardgold = 0;
	private static final int FRAMES_PER_SECOND = 30;
	private static final long REPAINT_INTERVAL_MS = 1000 / FRAMES_PER_SECOND;

	public BackgroundComponent(Block[][] numbers, String name) {
		this.Name = name;
		this.Blocks = numbers;
		this.magicladder = false;
		this.hero = new Hero(7 * 35 + 7, 19 * 35 + 15, this.Blocks);
		this.guard = new Guard(9 * 35 + 7, 19 * 35 + 15, this.Blocks,false);
		this.guard2 = new Guard(10 * 35 + 7, 19 * 35 + 15, this.Blocks,false);
		this.guard3 = new Guard(11 * 35 + 7, 19 * 35 + 15, this.Blocks,false);
		this.guard4 = new Guard(8 * 35 + 7, 19 * 35 + 15, this.Blocks,false);
		this.gold = 0;
		getGold();
		getSpawn();

		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (hero.isCorrect) {
					if (key == KeyEvent.VK_LEFT) {
						if (!(hero.checkFall())) {
							hero.updatePosition(-5, 0);
						}
					}
					if (key == KeyEvent.VK_RIGHT) {
						if (!(hero.checkFall())) {
							hero.updatePosition(5, 0);
						}

					}
				}
				if (key == KeyEvent.VK_UP) {
					hero.updatePosition(0, 5);
				}
				if (key == KeyEvent.VK_DOWN) {
					hero.updatePosition(0, -5);
					if (Blocks[(int) (hero.getX() / 35)][(int) (hero.getY() / 35)]
							.getType() == "Pipe"
							|| Blocks[(int) (hero.getX() / 35)][(int) ((hero
									.getY() + 35) / 35)].getType() == "Plank") {
						hero.y += 8;
						hero.isFalling = true;
						hero.isCorrect = false;
					}
				}
				if (key == KeyEvent.VK_Q) {
					System.exit(0);
				}
				if (key == KeyEvent.VK_U) {
					upLevel();
					getGold();
					hero = new Hero(7 * 35, 19 * 35 + 15, Blocks);
					guard = new Guard(9 * 35 + 7, 19 * 35 + 15, Blocks,false);
					guard2 = new Guard(10 * 35 + 7, 19 * 35 + 15, Blocks,false);
					guard3 = new Guard(11 * 35 + 7, 19 * 35 + 15, Blocks,false);
					guard4 = new Guard(13 * 35 + 7, 19 * 35 + 15, Blocks,false);
					magicladder = false;
					score = 0;
					lvlscore = score;
				}
				if (key == KeyEvent.VK_D) {
					downLevel();
					getGold();
					hero = new Hero(7 * 35, 19 * 35 + 15, Blocks);
					guard = new Guard(9 * 35 + 7, 19 * 35 + 15, Blocks,false);
					guard2 = new Guard(10 * 35 + 7, 19 * 35 + 15, Blocks,false);
					guard3 = new Guard(11 * 35 + 7, 19 * 35 + 15, Blocks,false);
					guard4 = new Guard(13 * 35 + 7, 19 * 35 + 15, Blocks,false);
					magicladder = false;
					score = 0;
					lvlscore = score;
				}
				if (key == KeyEvent.VK_Z) {
					Dig_left();
				}
				if (key == KeyEvent.VK_X) {
					Dig_right();
				}
				if (key == KeyEvent.VK_S) {
					Save();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				return;

			}

			@Override
			public void keyTyped(KeyEvent e) {
				return;

			}

		});

		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						while (hero.checkFall()) {
							Thread.sleep(REPAINT_INTERVAL_MS);
							hero.y += 3;
							hero.updatePosition(0, 0);
							repaint();
						}
						while (!(hero.isCorrect)) {
							Thread.sleep(REPAINT_INTERVAL_MS);
							hero.y += 3;
							hero.updatePosition(0, 0);
							repaint();
						}
						Thread.sleep(REPAINT_INTERVAL_MS);
						repaint();
					}
				} catch (InterruptedException exception) {
					// Do nothing
				}
			}
		};

		Thread t1 = new Thread(repainter);
		t1.start();

	}

	protected void Save() {
		try {
			PrintWriter writer = new PrintWriter(new File(
					"src/loderunner/Level_Data"));
			writer.println(this.level + " " + this.lvlscore + " "
					+ this.highscore + " " + this.Name);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void Dig_right() {
		int x = (int) (hero.getX() / 35);
		int y = (int) (hero.getY() / 35);
		if (Blocks[x + 1][y + 1].getType() == "Brick") {
			Blocks[x + 1][y + 1] = new Broken(x + 1, y + 1);
		}
	}

	protected void Dig_left() {
		int x = (int) (hero.getX() / 35);
		int y = (int) (hero.getY() / 35);
		if (Blocks[x - 1][y + 1].getType() == "Brick") {
			Blocks[x - 1][y + 1] = new Broken(x - 1, y + 1);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fill(new Rectangle(750, 750));
		if (this.gold == 0 && !this.magicladder) {
			this.magicladder = !this.magicladder;
			Blocks[1][1] = new Ladder(1, 1);
			Blocks[1][0] = new Ladder(1, 0);
		}
		if (this.gold == 0 && (int) (hero.getY() / 35) == 0) {
			upLevel();
			getGold();
			this.lvlscore = this.score;
			this.magicladder = !this.magicladder;
			hero = new Hero(7 * 35, 19 * 35 + 15, Blocks);
			guard = new Guard(9 * 35 + 7, 19 * 35 + 15, this.Blocks,false);
			guard2 = new Guard(10 * 35 + 7, 19 * 35 + 15, Blocks,false);
			guard3 = new Guard(11 * 35 + 7, 19 * 35 + 15, Blocks,false);
			guard4 = new Guard(13 * 35 + 7, 19 * 35 + 15, Blocks,false);

		}
		if (this.highscore < this.score)
			this.highscore = this.score;
		for (int i = 0; i < this.Blocks.length; i++) {
			for (int j = 0; j < this.Blocks[0].length; j++) {
				if (this.Blocks[i][j].getType() == "Broken") {
					this.Blocks[i][j].incrementCounter();
					if (this.Blocks[i][j].getCounter() > 100) {
						this.Blocks[i][j] = new Brick(i, j);
						if ((int) ((this.hero.getX() + 10) / 35) == i
								&& (int) (this.hero.getY() / 35) == j) {
							dead();
						}
					}
					if ((int) ((this.guard.getX() - 5) / 35) == i
							&& (int) (this.guard.getY() / 35) == j) {
						int count = this.Blocks[i][j].getCounter();
						this.Blocks[i][j] = new DeadGuard(i, j, count);
						if(guard.hasGold()){
							this.Blocks[i][j-1] = new Gold(i,j-1);
							this.guardgold--;
						}
						guard.died = true;
						
					}
					if ((int) ((this.guard2.getX() - 5) / 35) == i
							&& (int) (this.guard2.getY() / 35) == j) {
						int count = this.Blocks[i][j].getCounter();
						this.Blocks[i][j] = new DeadGuard(i, j, count);
						if(guard2.hasGold()){
							this.Blocks[i][j-1] = new Gold(i,j-1);
							this.guardgold--;
						}
						guard2.died = true;
					}
					if ((int) ((this.guard3.getX() - 5) / 35) == i
							&& (int) (this.guard3.getY() / 35) == j) {
						int count = this.Blocks[i][j].getCounter();
						this.Blocks[i][j] = new DeadGuard(i, j, count);
						if(guard3.hasGold()){
							this.Blocks[i][j-1] = new Gold(i,j-1);
							this.guardgold--;
						}
						guard3.died = true;
					}
					if ((int) ((this.guard4.getX() - 5) / 35) == i
							&& (int) (this.guard4.getY() / 35) == j) {
						int count = this.Blocks[i][j].getCounter();
						this.Blocks[i][j] = new DeadGuard(i, j, count);
						if(guard4.hasGold()){
							this.Blocks[i][j-1] = new Gold(i,j-1);
							this.guardgold--;
						}
						guard4.died = true;
					}

				}
				if (this.Blocks[i][j].isCharacterInSameSpot(hero, guard)) {
					this.hero.x = 25 * 35;
					this.hero.y = 25 * 35;
					dead();
				}
				if (this.Blocks[i][j].isCharacterInSameSpot(hero, guard2)) {
					this.hero.x = 25 * 35;
					this.hero.y = 25 * 35;
					dead();
				}
				if (this.Blocks[i][j].isCharacterInSameSpot(hero, guard3)) {
					this.hero.x = 25 * 35;
					this.hero.y = 25 * 35;
					dead();
				}
				if (this.Blocks[i][j].isCharacterInSameSpot(hero, guard4)) {
					this.hero.x = 25 * 35;
					this.hero.y = 25 * 35;
					dead();
				}
				if (this.Blocks[i][j].getType() == "DeadGuard") {
					this.Blocks[i][j].incrementCounter();
					if (this.Blocks[i][j].getCounter() > 120) {
						this.Blocks[i][j] = new Brick(i, j);
						if ((int) ((this.guard.getX() - 15) / 35) == i
								&& (int) (this.guard.getY() / 35) == j) {
							guard = new Guard(this.spawn_X * 35 + 7,
									this.spawn_Y * 35 + 15, this.Blocks,false);
							guard.died = false;
						}
						if ((int) ((this.guard2.getX() - 15) / 35) == i
								&& (int) (this.guard2.getY() / 35) == j) {
							guard2 = new Guard(this.spawn_X * 35 + 7,
									this.spawn_Y * 35 + 15, this.Blocks,false);
							guard2.died = false;
						}
						if ((int) ((this.guard3.getX() - 15) / 35) == i
								&& (int) (this.guard3.getY() / 35) == j) {
							guard3 = new Guard(this.spawn_X * 35 + 7,
									this.spawn_Y * 35 + 15, this.Blocks,false);
							guard3.died = false;
						}
						if ((int) ((this.guard4.getX() - 15) / 35) == i
								&& (int) (this.guard4.getY() / 35) == j) {
							guard4 = new Guard(this.spawn_X * 35 + 7,
									this.spawn_Y * 35 + 15, this.Blocks,false);
							guard.died = false;
						}
					}
				}
				if (this.Blocks[i][j].getType() == "Brick") {
					if ((int) ((this.guard.getX()) / 35) == i
							&& (int) (this.guard.getY() / 35) == j) {
						guard = new Guard(this.spawn_X * 35 + 7,
								this.spawn_Y * 35 + 15, this.Blocks,false);
					}
					if ((int) ((this.guard2.getX()) / 35) == i
							&& (int) (this.guard2.getY() / 35) == j) {
						guard2 = new Guard(this.spawn_X * 35 + 7,
								this.spawn_Y * 35 + 15, this.Blocks,false);
					}
					if ((int) ((this.guard3.getX()) / 35) == i
							&& (int) (this.guard3.getY() / 35) == j) {
						guard3 = new Guard(this.spawn_X * 35 + 7,
								this.spawn_Y * 35 + 15, this.Blocks,false);
					}
					if ((int) ((this.guard4.getX()) / 35) == i
							&& (int) (this.guard4.getY() / 35) == j) {
						guard4 = new Guard(this.spawn_X * 35 + 7,
								this.spawn_Y * 35 + 15, this.Blocks,false);
					}
				}
				if (this.Blocks[i][j].getType() == "Gold"
						&& (this.hero.getX() + 5) >= i * 35
						&& (this.hero.getX() - 5) <= i * 35
						&& (this.hero.getY()) >= j * 35
						&& (this.hero.getY()) <= (j + 1) * 35) {
					this.score++;
					this.Blocks[i][j] = new Air(i, j);
					getGold();
				}
				if (this.Blocks[i][j].getType() == "Gold"
						&& (this.guard.getX() + 5) >= i * 35
						&& (this.guard.getX() - 5) <= i * 35
						&& (this.guard.getY()) >= j * 35
						&& (this.guard.getY()) <= (j + 1) * 35
						&& !(this.guard.hasGold())) {
					this.guardgold++;
					this.guard.gold = true;
					this.Blocks[i][j] = new Air(i, j);
					getGold();
				}
				if (this.Blocks[i][j].getType() == "Gold"
						&& (this.guard2.getX() + 5) >= i * 35
						&& (this.guard2.getX() - 5) <= i * 35
						&& (this.guard2.getY()) >= j * 35
						&& (this.guard2.getY()) <= (j + 1) * 35
						&& !(this.guard2.hasGold())) {
					this.guardgold++;
					this.guard2.gold = true;
					this.Blocks[i][j] = new Air(i, j);
					getGold();
				}
				if (this.Blocks[i][j].getType() == "Gold"
						&& (this.guard3.getX() + 5) >= i * 35
						&& (this.guard3.getX() - 5) <= i * 35
						&& (this.guard3.getY()) >= j * 35
						&& (this.guard3.getY()) <= (j + 1) * 35
						&& !(this.guard3.hasGold())) {
					this.guardgold++;
					this.guard3.gold = true;
					this.Blocks[i][j] = new Air(i, j);
					getGold();
				}
				if (this.Blocks[i][j].getType() == "Gold"
						&& (this.guard4.getX() + 5) >= i * 35
						&& (this.guard4.getX() - 5) <= i * 35
						&& (this.guard4.getY()) >= j * 35
						&& (this.guard4.getY()) <= (j + 1) * 35
						&& !(this.guard4.hasGold())) {
					this.guardgold++;
					this.guard4.gold = true;
					this.Blocks[i][j] = new Air(i, j);
					getGold();
				}
			}
		}
		for (int i = 0; i < this.Blocks.length; i++) {
			for (int j = 0; j < this.Blocks[0].length; j++) {
				g2.setColor(Blocks[i][j].getColor());
				g2.fill(Blocks[i][j].getShape());
				g2.draw(Blocks[i][j].getShape());
			}
		}

		g2.setColor(hero.getColor());
		g2.fill(hero.getShape());

		g2.setColor(guard.getColor());
		g2.fill(guard.getShape());
		guard.Chase(hero, 3, 3);
		guards.add(guard);

		g2.setColor(guard2.getColor());
		g2.fill(guard2.getShape());
		guard2.Chase(hero, 3, 3);
		guards.add(guard2);

		g2.setColor(guard3.getColor());
		g2.fill(guard3.getShape());
		guard3.Chase(hero, 3, 3);
		guards.add(guard3);

		g2.setColor(guard4.getColor());
		g2.fill(guard4.getShape());
		guard4.Chase(hero, 3, 3);
		guards.add(guard4);

		g2.setColor(Color.RED);
		Font f = new Font("Times New Roman", Font.BOLD, 20);
		g2.setFont(f);
		g2.drawString("Score: " + this.score, 70, 25);
		g2.drawString("Highscore: " + this.highscore, 195, 25);
		g2.drawString("Gold left: " + this.gold, 335, 25);
		g2.drawString("Player: " + this.Name, 465, 25);
		setFocusable(true);
		requestFocusInWindow();

	}

	public void downLevel() {
		if (level == 2) {
			level--;
			getGold();
			try {
				Scanner scanner = new Scanner(
						new File("src/loderunner/level_1"));
				Block[][] level1 = new Block[22][22];
				for (int j = 0; j < 22; j++) {
					for (int i = 0; i < 22; i++) {
						int value = scanner.nextInt();
						if (value == 1) {
							level1[i][j] = new Brick(i, j);
						} else if (value == 0) {
							level1[i][j] = new Air(i, j);
						} else if (value == 2) {
							level1[i][j] = new Ladder(i, j);
						} else if (value == 3) {
							level1[i][j] = new Gold(i, j);
						} else if (value == 4) {
							level1[i][j] = new Board(i, j);
						} else if (value == 5) {
							level1[i][j] = new Pipe(i, j);
						} else if (value == 6) {
							level1[i][j] = new Plank(i, j);
						} else if (value == 7) {
							level1[i][j] = new Spawn(i, j);
						}

					}
				}
				this.Blocks = level1;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (level == 3) {
			try {
				level--;
				getGold();
				Scanner scanner = new Scanner(
						new File("src/loderunner/level_2"));
				Block[][] level2 = new Block[22][22];
				for (int j = 0; j < 22; j++) {
					for (int i = 0; i < 22; i++) {
						int value = scanner.nextInt();
						if (value == 1) {
							level2[i][j] = new Brick(i, j);
						} else if (value == 0) {
							level2[i][j] = new Air(i, j);
						} else if (value == 2) {
							level2[i][j] = new Ladder(i, j);
						} else if (value == 3) {
							level2[i][j] = new Gold(i, j);
						} else if (value == 4) {
							level2[i][j] = new Board(i, j);
						} else if (value == 5) {
							level2[i][j] = new Pipe(i, j);
						} else if (value == 6) {
							level2[i][j] = new Plank(i, j);
						} else if (value == 7) {
							level2[i][j] = new Spawn(i, j);
						}
					}
				}
				this.Blocks = level2;
				repaint();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void upLevel() {
		if (level == 2) {
			try {
				level++;
				getGold();
				Scanner scanner = new Scanner(
						new File("src/loderunner/level_3"));
				Block[][] level3 = new Block[22][22];
				for (int j = 0; j < 22; j++) {
					for (int i = 0; i < 22; i++) {
						int value = scanner.nextInt();
						if (value == 1) {
							level3[i][j] = new Brick(i, j);
						} else if (value == 0) {
							level3[i][j] = new Air(i, j);
						} else if (value == 2) {
							level3[i][j] = new Ladder(i, j);
						} else if (value == 3) {
							level3[i][j] = new Gold(i, j);
						} else if (value == 4) {
							level3[i][j] = new Board(i, j);
						} else if (value == 5) {
							level3[i][j] = new Pipe(i, j);
						} else if (value == 6) {
							level3[i][j] = new Plank(i, j);
						} else if (value == 7) {
							level3[i][j] = new Spawn(i, j);
						}
					}
				}
				this.Blocks = level3;
				repaint();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (level == 1) {
			try {
				level++;
				getGold();
				Scanner scanner = new Scanner(
						new File("src/loderunner/level_2"));
				Block[][] level2 = new Block[22][22];
				for (int j = 0; j < 22; j++) {
					for (int i = 0; i < 22; i++) {
						int value = scanner.nextInt();
						if (value == 1) {
							level2[i][j] = new Brick(i, j);
						} else if (value == 0) {
							level2[i][j] = new Air(i, j);
						} else if (value == 2) {
							level2[i][j] = new Ladder(i, j);
						} else if (value == 3) {
							level2[i][j] = new Gold(i, j);
						} else if (value == 4) {
							level2[i][j] = new Board(i, j);
						} else if (value == 5) {
							level2[i][j] = new Pipe(i, j);
						} else if (value == 6) {
							level2[i][j] = new Plank(i, j);
						} else if (value == 7) {
							level2[i][j] = new Spawn(i, j);
						}
					}
				}
				this.Blocks = level2;
				repaint();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void setScore(int Score, int Highscore) {
		this.score = Score;
		this.lvlscore = Score;
		this.highscore = Highscore;
	}

	public void setlevel(int lvl) {
		this.level = lvl;
	}

	public void help_function() {
		String s = "Your Hero has Died."
				+ " You will now be returned to the start of the level."
				+ "You can restore your game from this level if you quit the game";

		String html1 = "<html><body style='width: ";
		String html2 = "px'>";

		JOptionPane.showMessageDialog(null, new JLabel(html1 + "200" + html2
				+ s));
	}

	public void restartLevel() {
		level++;
		downLevel();
	}

	public void getGold() {
		this.gold = 0;
		for (int i = 0; i < this.Blocks.length; i++) {
			for (int j = 0; j < this.Blocks[0].length; j++) {
				if (Blocks[i][j].getType() == "Gold") {
					gold++;
				}
			}
		}
		this.gold += this.guardgold;
	}

	public void dead() {
		Save();
		help_function();
		upLevel();
		downLevel();
		this.score = 0;
		this.lvlscore = 0;
		getGold();
		this.hero = new Hero(7 * 35 + 7, 19 * 35 + 15, this.Blocks);
		guard = new Guard(9 * 35 + 7, 19 * 35 + 15, this.Blocks,false);
		guard2 = new Guard(10 * 35 + 7, 19 * 35 + 15, Blocks,false);
		guard3 = new Guard(11 * 35 + 7, 19 * 35 + 15, Blocks,false);
		guard4 = new Guard(13 * 35 + 7, 19 * 35 + 15, Blocks,false);
	}

	public void getSpawn() {
		for (int i = 0; i < this.Blocks.length; i++) {
			for (int j = 0; j < this.Blocks[0].length; j++) {
				if (Blocks[i][j].getType() == "Spawn") {
					spawn_X = i;
					spawn_Y = j;
				}
			}
		}
	}
}
