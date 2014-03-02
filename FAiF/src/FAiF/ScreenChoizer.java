package FAiF;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
public class ScreenChoizer extends JPanel{
	
	JButton buttonTower = new JButton();
	
	JButton buttonHero = new JButton();
	JButton buttonLocation = new JButton();
	JButton buttonMission = new JButton();
	JButton buttonOption = new JButton();
	JButton buttonItem = new JButton();
	JButton buttonTown = new JButton();
	
	Image bckground;
	
	public ScreenChoizer() {
		setSize(100,450);
		//setPreferredSize(new Dimension(50, 104));
		setLayout(new FlowLayout());
		//setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		try {
			bckground = ImageIO.read(new File("data/image/bckground/left.gif"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//buttonTower.imageUpdate(ImageIO.read(new File("data/image/bos/bos1.gif")), false, 1, 1, 60, 60);
		Image image1=null;
		try {
			image1 = ImageIO.read(new File("data/image/interface/scrChoize/i1.gif"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		//buttonTower.setIcon(new ImageIcon(image1));
		
		buttonTown.setPreferredSize(new Dimension(75, 75));
		buttonTown.setText("Town");
		add(buttonTown);
		
		buttonTown.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent e) {
					//LHoH.gameScreen.activateTownPanel();
	    	  }
	    	});
		
		buttonHero.setPreferredSize(new Dimension(75, 75));
		buttonHero.setText("Hero");
		add(buttonHero);
		
		buttonHero.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent e) {
					FAiF.gameScreen.activateHeroManagmentPanel();
	    	  }
	    	});
		
		buttonLocation.setPreferredSize(new Dimension(75, 75));
		buttonLocation.setText("Fight");
		add(buttonLocation);
		
		buttonLocation.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent e) {
					FAiF.gameScreen.activateBattlePanel();
	    	  }
	    	});
		
		/*
		buttonMission.setPreferredSize(new Dimension(75, 75));
		buttonMission.setText("Boss");
		add(buttonMission);		
		
		buttonMission.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent e) {
					LHoH.gameScreen.activateBossPanel();
	    	  }
	    	});
		*/
		
		buttonTower.setPreferredSize(new Dimension(75, 75));
		buttonTower.setText("Tower");
		add(buttonTower);		
		
		buttonTower.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent e) {
					//LHoH.gameScreen.activateTowerPanel();
	    	  }
	    	});
		
		
		buttonOption.setPreferredSize(new Dimension(75, 75));
		buttonOption.setText("Option");
		//add(buttonOption);
		
		buttonItem.setPreferredSize(new Dimension(75, 75));
		buttonItem.setText("Items");
		add(buttonItem);
		
		buttonItem.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent e) {
					//LHoH.gameScreen.activateItemPanel();
	    	  }
	    	});
		
		
		
	}
	public void paintComponent(Graphics g) {
		//removeAll();	
		//add (LHoH.gameScreen.heroStock);
		//add (LHoH.gameScreen.locationScope);
			g.drawImage(bckground, 0, 0, null);
		//setBackground(Color.black);
			
		}

}
