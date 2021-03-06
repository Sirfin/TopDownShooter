package shooter.scenes;

import framework.main.Scene;
import framework.main.SceneManager;
import framework.rendering.Camera;
import shooter.options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class MenuScene extends Scene {
	private JButton StartButton ;
	private JButton QuitButton ;
	private JButton OptionButton ;
	private JLabel BackgroundImage ;
	private JButton BackButton ;
	private JButton MutedButton ;
	private void MainMenu(){
		StartButton.setVisible(true);
		QuitButton.setVisible(true);
		OptionButton.setVisible(true);
		BackButton.setVisible(false);
		MutedButton.setVisible(false);
	}
	private void OptionMenu(){
		StartButton.setVisible(false);
		QuitButton.setVisible(false);
		OptionButton.setVisible(false);
		BackButton.setVisible(true);
		MutedButton.setVisible(true);
	}
	
	
	@Override
	public void init() {
		main_Camera = new Camera(1000,1000,new Rectangle2D.Float(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		main_Camera.OverrideLayout(null);
		ImageIcon icon = new ImageIcon(new ImageIcon(	this.getClass().getResource("/Assets/Menu/play_buttons.png")).getImage()) ;
		StartButton = new JButton(icon) ; 
		int x = main_Camera.getGameWindow().getWidth()/2 - icon.getIconWidth()/2 ;
		int y = main_Camera.getGameWindow().getHeight() /8 - 100  ;
		StartButton.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
		StartButton.addActionListener(e -> SceneManager.getInstance().SetScene(new ShooterMainScene()));
		StartButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        StartButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/play_buttons_pressed.png")).getImage()));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        StartButton.setIcon(new ImageIcon(new ImageIcon(	this.getClass().getResource("/Assets/Menu/play_buttons.png")).getImage()));
		    }
		});
		StartButton.setOpaque(false);
		StartButton.setContentAreaFilled(false);
		StartButton.setBorderPainted(false);
		main_Camera.AddGUIElement(StartButton);

		
		
		
		ImageIcon icon2 = new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/exit_buttons.png")).getImage()) ;
		QuitButton = new JButton(icon2) ; 
		x = main_Camera.getGameWindow().getWidth()/2 - icon2.getIconWidth()/2 ;
		y =  5 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		QuitButton.setBounds(x, y, icon2.getIconWidth(), icon2.getIconHeight());
		QuitButton.setOpaque(false);
		QuitButton.setContentAreaFilled(false);
		QuitButton.setBorderPainted(false);
		QuitButton.addActionListener(e -> System.exit(0));
		QuitButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        QuitButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/exit_buttons_pressed.png")).getImage()));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        QuitButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/exit_buttons.png")).getImage()));
		    }
		});
		main_Camera.AddGUIElement(QuitButton);
		
		
		icon2 = new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/optionst_buttons.png")).getImage()) ;
		OptionButton = new JButton(icon2) ; 
		x = main_Camera.getGameWindow().getWidth()/2 - icon2.getIconWidth()/2 ;
		y =  3 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		OptionButton.setBounds(x, y, icon2.getIconWidth(), icon2.getIconHeight());
		OptionButton.setOpaque(false);
		OptionButton.setContentAreaFilled(false);
		OptionButton.setBorderPainted(false);
		OptionButton.addActionListener(e -> OptionMenu());
		
		OptionButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        OptionButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/optionst_buttons_pressed.png")).getImage()));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        OptionButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/optionst_buttons.png")).getImage()));
		    }
		});
		main_Camera.AddGUIElement(OptionButton);
		
		
		icon2 = new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/BackButton.png")).getImage().getScaledInstance(100, 100, 0)) ;
		BackButton = new JButton(icon2) ; 
		x = 0 ;
		y = main_Camera.getGameWindow().getHeight() - 150  ; 
		BackButton.setBounds(x, y, icon2.getIconWidth(), icon2.getIconHeight());
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.addActionListener(e -> MainMenu());
		main_Camera.AddGUIElement(BackButton);

		MutedButton = new JButton(icon2) ; 
		
		System.out.println(Options.getMuted());
		 if (Options.getMuted()){
        	 icon2 = (new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/ButtonMuted.png")).getImage()));
        }else{
        	 icon2 = (new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/ButtonNotMuted.png")).getImage()));
        }
		 x = main_Camera.getGameWindow().getWidth()/2 - icon2.getIconWidth()/2  ;
			y = main_Camera.getGameWindow().getHeight()/2  - icon2.getIconHeight()/2 ; 
		MutedButton.setIcon(icon2);
		MutedButton.setBounds(x, y, icon2.getIconWidth(),icon2.getIconHeight() );
		MutedButton.setOpaque(false);
		MutedButton.setContentAreaFilled(false);
		MutedButton.setBorderPainted(false);	
		MutedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.ToggleAudio();
                if (Options.getMuted()){
                	 MutedButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/ButtonMuted.png")).getImage()));
                }else{
                	 MutedButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Menu/ButtonNotMuted.png")).getImage()));
                }
            }
        });
		main_Camera.AddGUIElement(MutedButton);
		
		ImageIcon image = new ImageIcon(new ImageIcon(this.getClass().getResource("/Assets/Splashscreens/background.jpg")).getImage().getScaledInstance( main_Camera.getGameView().getWidth(), main_Camera.getGameView().getHeight()
				, Image.SCALE_DEFAULT)) ;
		BackgroundImage = new JLabel("",image,JLabel.CENTER) ; 
		BackgroundImage.setBounds(0, 0, main_Camera.getGameView().getWidth(), main_Camera.getGameView().getHeight());
		main_Camera.AddGUIElement(BackgroundImage);		
		MainMenu() ; 
	}
	
	public void finishScene(){
	main_Camera.RemoveGUIElement(StartButton);
	main_Camera.RemoveGUIElement(QuitButton);
	main_Camera.RemoveGUIElement(OptionButton);
	main_Camera.RemoveGUIElement(BackgroundImage);
	}
	
}
