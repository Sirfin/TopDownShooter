package shooter.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import framework.main.GameObject;
import framework.main.SceneManager;
import shooter.UI.HealthBar;

public class GameManager extends GameObject {
	JLabel ScoreLabel ;
	JLabel FPSLabel ;

	JProgressBar m_healthbar = new JProgressBar() ;
	JProgressBar m_bossbar = new JProgressBar() ;
	JLabel Stats ;
	Boolean boss = false;
	Boolean bossKilled = false;
	Player main_player  ;
	int Score = 0 ;
	int killedEnemies = 0;
	int Wave = 1;
	private boolean waves;
	Boss m_boss ; 
	public Player getPlayer(){
		return this.main_player ; 
	}

	public static int MAX_ENEMY_NUMBER = 5;

	public GameManager(String Name){
		super(Name) ;
		main_player = 	new Player("Assets/PlaneSprites/1.png","MainPlayer") ;
		ScoreLabel = new JLabel("Score: ",JLabel.LEFT);
		framework.main.SceneManager.getInstance().getMainCamera().AddGUIElement(ScoreLabel);
		ScoreLabel.setFont(new Font("Serif", Font.PLAIN, 35));	
		ScoreLabel.setText(ScoreLabel.getText() + Score);
		m_healthbar.setUI(new HealthBar());
		m_bossbar.setUI(new HealthBar());
		Stats = new JLabel() ; 
		framework.main.SceneManager.getInstance().getMainCamera().AddGUIElement(Stats);
		framework.main.SceneManager.getInstance().getMainCamera().AddGUIElement(m_healthbar);
		framework.main.SceneManager.getInstance().getMainCamera().AddGUIElement(m_bossbar);
		Stats.setFont(new Font("Serif", Font.PLAIN, 35));
		Stats.setText("HP:");
		m_healthbar.setMaximum(main_player.getHealth());
		m_healthbar.setValue(main_player.getHealth());
		m_bossbar.setVisible(false);
		
	}
	public void StartWaves(){
		waves = true ;
	}
	public void Update(){
		super.Update();
		if (waves) setupEnemies(SceneManager.getInstance().GetAllGameObjectsInScene()) ;		
		drawHealth() ; 
		if (killedEnemies >= (Wave * 10)) {
			if (boss != null){
			if (!boss) {
				System.out.println("Killed Enemies: " + killedEnemies);
				System.out.println("Boss created");
				m_boss = new Boss("Assets/PlaneSprites/Enemy B-17.png", "Boss", 5, -3);
				boss = true;
				m_bossbar.setMaximum(m_boss.getHealth());
				m_bossbar.setVisible(true);
				m_bossbar.setValue(m_boss.getHealth());
			}
			}

		}
	}
	
	public void drawHealth(){
		if (main_player != null) m_healthbar.setValue(this.main_player.getHealth()) ; 
		if (m_boss != null) m_bossbar.setValue(this.m_boss.getHealth());
	}
	
	public int getScore(){
		return Score ;
	}

	public void AddScore(int points){
		Score += points ; 
		ScoreLabel.setText("Score: " + Score);
		
	}
	
	public void setupEnemies(List<GameObject> gos) {
		int enemyCounter = 0;
		for (GameObject enemies : gos) {
			if (enemies.getName().equals("Enemy")) {
				enemyCounter++;
			}
		}
		if (enemyCounter < MAX_ENEMY_NUMBER && !boss) {
			Random random = new Random(System.nanoTime());
			float pos_X = random.nextInt(9 - 1 + 1) + 1;
			float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
			new AdvancedEnemy("Assets/PlaneSprites/Advanced/JU87B2.png", "Enemy", pos_X, pos_Y);
			Meteorit m = new Meteorit("Meteo") ;
			m.setPosition(new Point2D.Float(pos_X, pos_Y));
		}

	}
	public void addKilledEnemy() {
		killedEnemies++;
	}
	public void killedBoss() {
		bossKilled = true;
		boss = false;
		Wave++;
		killedEnemies = 0;
		m_bossbar.setVisible(false);
	}
}
