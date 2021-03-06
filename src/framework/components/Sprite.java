package framework.components;

import framework.main.GameObject;
import framework.main.SceneManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class for our Sprite Component which holds the Image Infos
 * 
 * @author Fin
 *
 */
public class Sprite extends Component {
	private BufferedImage my_sprite_image; // image of the sprite ;
	private final GameObject my_object;// GameObject the sprite is assigned to ;
	public int rotation = 0  ; 
	/**
	 * Init the Sprite with a path to an image
	 * 
	 * @param PathToSprite Path to the image
	 * @param my mother Object
	 */
	public Sprite(String PathToSprite, GameObject my) {
		my_object = my;
		try {
			// We convert the Sprite to the best Format for the graphics card
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = env.getDefaultScreenDevice();
			GraphicsConfiguration config = device.getDefaultConfiguration(); // convert
																				// Image
																				// to
																				// a
																				// memory
																				// type
																				// that
																				// given
																				// Graphic
																				// cards
																				// supports
			my_sprite_image = ImageIO.read(this.getClass().getResource(PathToSprite));
			BufferedImage tmp = config.createCompatibleImage(my_sprite_image.getWidth(), my_sprite_image.getHeight(),
					Transparency.TRANSLUCENT);
			tmp.getGraphics().drawImage(my_sprite_image, 0, 0, null);
			my_sprite_image = tmp;

		} catch (IOException e) {
			// TODO Auto-generated catch block,
			e.printStackTrace();
		}
		// Setting the size of the sprite to the size of gameObject
		my_object.setDimension(new Dimension(this.getWidth(), this.getHeight()));
	}
	/**
	 * init the Sprite with an already loaded image
	 * @param image image that the Sprite should hold
	 * @param my Mother Object
	 */
	public Sprite(BufferedImage image, GameObject my) {
		my_object = my;
		// We convert the Sprite to the best format for the graphics card
		
		my_sprite_image = image;
		BufferedImage tmp = SceneManager.config.createCompatibleImage(my_sprite_image.getWidth(), my_sprite_image.getHeight(),
				Transparency.BITMASK);
		tmp.getGraphics().drawImage(my_sprite_image, 0, 0, null);
		my_sprite_image = tmp;

		// Setting the size of the sprite to the size of gameObject
		this.resize(my_object.getTransform().getSize());
		my_object.setDimension(new Dimension(this.getWidth(), this.getHeight()));
	}

	/**
	 * Function to rotate Images
	 * 
	 * @param src Image to rotate
	 * @param degrees degree which we shall rotate the picture
	 * @return rotatedImage rotated src
	 */
	private static BufferedImage rotateImage(BufferedImage src, double degrees) { 	
		AffineTransform affineTransform = AffineTransform.getRotateInstance(Math.toRadians(degrees), src.getWidth() / 2,
				src.getHeight() / 2);
		BufferedImage rotatedImage = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
		Graphics2D g = (Graphics2D) rotatedImage.getGraphics();
		g.setTransform(affineTransform);
		g.drawImage(src, 0, 0, null);
		return rotatedImage;
	}

	/**
	 * API Interface to rotate a sprite
	 * 
	 * @param winkel rotate
	 */
	public void rotate(int winkel) {
		my_sprite_image = rotateImage(my_sprite_image, winkel);
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return my_sprite_image.getHeight();
	}

	/**
	 * @return width
	 */
	public int getWidth() {
		return my_sprite_image.getWidth();
	}

	/**
	 * @return Image
	 */
	public BufferedImage getImage() {
		return my_sprite_image;
	}

	/**
	 * If the size of the gameObject changes we have to change our sprite size
	 * 
	 * @param d the Dimensions to resize to.
	 */
	public void resize(Dimension d) {
		my_sprite_image = getScaledImage(my_sprite_image, (int) d.getWidth(), (int) d.getHeight());
	}

	/**
	 * Resizes an image using a Graphics2D object backed by a BufferedImage.
	 * 
	 * @param srcImg
	 *            - source image to scale
	 * @param w
	 *            - desired width
	 * @param h
	 *            - desired height
	 * @return - the new resized image
	 */
	private BufferedImage getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}

	@Override
	public void ComponentUpdate() {

	}

}
