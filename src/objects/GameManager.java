package objects;

import java.util.List;
import java.util.Random;

import main.GameObject;
import main.SceneManager;

public class GameManager extends GameObject {
	public GameManager(String Name){
		super(Name) ;
	}
	public void Update(){
		super.Update();
		setupEnemies(SceneManager.getInstance().GetAllGameObjectsInScene()) ; 
	}
	public void setupEnemies(List<GameObject> gos) {
		int enemyCounter = 0;
		for (GameObject enemies : gos) {
			if (enemies.getName().equals("Enemy")) {
				enemyCounter++;
			}
		}
		if (enemyCounter < 1) {
			Random random = new Random();
			float pos_X = random.nextInt(9 - 1 + 1) + 1;
			float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
			new Enemy("Assets/PlaneSprites/Enemy B-17.png", "Enemy", pos_X, pos_Y);
		}

	}
}