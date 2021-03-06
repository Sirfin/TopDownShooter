package framework.geometry;
import framework.components.Collider;
import framework.main.*; 
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Fin T�ter
 * @version 1.0 
 */
public class QuadTree {
	 
	  private final int MAX_OBJECTS = 5; //Objects we hold in one node
	  private final int MAX_LEVELS = 5; //Max node Depth

	  private final int level; // which level we are on
	  private final List<GameObject> objects; // the object we hold
	  private final Rectangle2D.Float bounds; //Size of the wuad
	  private final QuadTree[] nodes ;  // List that holds our nodes
	 
	 /*
	  * Constructor
	  */
	  public QuadTree(int pLevel, Rectangle2D.Float pBounds) {
	   level = pLevel;
	   objects = new ArrayList<>();
	   bounds = pBounds;
	   nodes = new QuadTree[4];
	  }
	  /*
	   * Clears the quadtree
	   */
	   public void clear() {
	     objects.clear();
	   
	     for (int i = 0; i < nodes.length; i++) {
	       if (nodes[i] != null) {
	         nodes[i].clear();
	         nodes[i] = null;
	       }
	     }
	   }
	   /*
	    * Splits the node into 4 subnodes
	    */
	    private void split() {
	      int subWidth = (int)(bounds.getWidth() / 2);
	      int subHeight = (int)(bounds.getHeight() / 2);
	      int x = (int)bounds.getX();
	      int y = (int)bounds.getY();
	    
	      nodes[0] = new QuadTree(level+1, new Rectangle2D.Float(x + subWidth, y, subWidth, subHeight));
	      nodes[1] = new QuadTree(level+1, new Rectangle2D.Float(x, y, subWidth, subHeight));
	      nodes[2] = new QuadTree(level+1, new Rectangle2D.Float(x, y + subHeight, subWidth, subHeight));
	      nodes[3] = new QuadTree(level+1, new Rectangle2D.Float(x + subWidth, y + subHeight, subWidth, subHeight));
	    }
	    /*
	     * Determine which node the object belongs to. -1 means
	     * object cannot completely fit within a child node and is part
	     * of the parent node
	     */
	     private int getIndex(GameObject pObject) {
	       int index = -1;
	       double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
	       double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
	       Collider pColl = pObject.getCollider() ; 
	       // Object can completely fit within the top quadrants
	       boolean topQuadrant = (pColl.getY() < horizontalMidpoint &&pColl.getY() + pColl.getHeight() < horizontalMidpoint);
	       // Object can completely fit within the bottom quadrants
	       boolean bottomQuadrant = (pColl.getY() > horizontalMidpoint);
	     
	       // Object can completely fit within the left quadrants
	       if (pColl.getX() < verticalMidpoint && pColl.getX() + pColl.getWidth() < verticalMidpoint) {
	          if (topQuadrant) {
	            index = 1;
	          }
	          else if (bottomQuadrant) {
	            index = 2;
	          }
	        }
	        // Object can completely fit within the right quadrants
	        else if (pColl.getX() > verticalMidpoint) {
	         if (topQuadrant) {
	           index = 0;
	         }
	         else if (bottomQuadrant) {
	           index = 3;
	         }
	       }
	     
	       return index;
	     }
	     /*
	      * Insert the object into the quadtree. If the node
	      * exceeds the capacity, it will split and add all
	      * objects to their corresponding nodes.
	      */
	      public void insert(GameObject pObject) {
	        if (nodes[0] != null) {
	          int index = getIndex(pObject);
	      
	          if (index != -1) {
	            nodes[index].insert(pObject);
	      
	            return;
	          }
	        }
	      
	        objects.add(pObject);
	      
	        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
	           if (nodes[0] == null) { 
	              split(); 
	           }
	      
	          int i = 0;
	          while (i < objects.size()) {
	            int index = getIndex(objects.get(i));
	            if (index != -1) {
	              nodes[index].insert(objects.remove(i));
	            }
	            else {
	              i++;
	            }
	          }
	        }
	      }
	      /*
	       * Return all objects that could collide with the given object
	       */
	       public void retrieve(List<GameObject> returnObjects, GameObject pObject) {
	         int index = getIndex(pObject);
	         if (index != -1 && nodes[0] != null) {
	           nodes[index].retrieve(returnObjects, pObject);
	         }
	         returnObjects.addAll(objects);

	       }
	}