package framework.components;

import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
/**
 * Oval Collider component, used for collision
 * @author Fin
 *@version 1.0
 */
public class OvalCollider extends Collider {
	private double factor;

	/**
	 * Constructor to init collider with manual size
	 * @param collidingEllipse our Collsion Shape
	 * @param initObject mother Object
	 */
	public OvalCollider(Ellipse2D.Float collidingEllipse, GameObject initObject) {
		super(initObject);
		this.collidingShape = collidingEllipse ; 
		this.HasOverridenSize = true ; 
	}
	/**
	 * Constructor to init collider with size of object
	 * @param scale_factor radius
	 * @param initObject mother Object
	 */
	public OvalCollider(GameObject initObject, double scale_factor) {
		super(initObject);
		this.factor = scale_factor;
		this.collidingShape = this.gameObjectToShape(initObject) ; 
		
	}

	@Override
	void setX(float newX) {
		Ellipse2D.Float toSet = (Ellipse2D.Float)this.collidingShape ; 
		toSet.x = newX ; 
	}

	@Override
	void setY(float newY) {
		Ellipse2D.Float toSet = (Ellipse2D.Float)this.collidingShape ; 
		toSet.y = newY ; 
	
	}
	@Override
	public float getX(){
		Ellipse2D.Float toSet = (Ellipse2D.Float)this.collidingShape ; 
		return toSet.x ; 
	}
	@Override
	public float getY(){
		Ellipse2D.Float toSet = (Ellipse2D.Float)this.collidingShape ; 
		return toSet.y ; 
	}

	@Override
	Shape gameObjectToShape(GameObject object) {
		Point2D.Float point = object.getPosition() ; 
		Ellipse2D.Float rec = new Ellipse2D.Float() ; 
		Dimension d = new Dimension(object.getTransform().getSize()) ; 
		rec.x = point.x ; 
		rec.y = point.y ; 
		Camera camera = SceneManager.getInstance().getMainCamera() ; 	
		double height = (1000*camera.getViewRect().height*d.height)/camera.getGameWindow().getHeight()  ; 
		double width  = (1000*camera.getViewRect().width*d.width)/camera.getGameWindow().getWidth()  ; 
		rec.width = (float) width / (int)(1000 * factor) ;
		rec.height = (float) height / (int)(1000 * factor) ;
		return rec ; 
	}

}
