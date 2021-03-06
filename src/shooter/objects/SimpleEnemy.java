package shooter.objects;

import framework.main.GameObject;
import java.awt.geom.Point2D;

/**
 * Created by Eike Nils on 22.06.2017.
 */
public class SimpleEnemy extends Enemy {

    /**
     * Constructor for SimpleEnemy.
     * @param PathToSprite The path to the sprite.
     * @param Name The Name of the game-object.
     * @param posX Starting-coordinate X.
     * @param posY Starting-coordinate Y.
     */
    public SimpleEnemy(String PathToSprite, String Name, float posX, float posY) {
        super(PathToSprite, Name, posX, posY);
        setHealth(100);
        setValue(20);
    }

    /**
     * Implementation of abstract void.
     * Shoot a single bullet.
     */
    @Override
    public void shoot() {
        if(lastMove == 1) {
            GameObject MyBullet = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + 1f));
            MyBullet.Rotate(180 - 27) ;
        }
        if(lastMove == 0) {
            GameObject MyBullet = new MissleEnemy(200,new Point2D.Float(-1, +4)) ;
            MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + 1f));
            MyBullet.Rotate(180 + 27) ;
        }
        if(lastMove == 2) {
            GameObject MyBullet = new MissleEnemy(200,new Point2D.Float(0, +4)) ;
            MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + 1f));
            MyBullet.Rotate(180) ;
        }
    }

}
