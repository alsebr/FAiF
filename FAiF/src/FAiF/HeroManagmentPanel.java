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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import HeroPackage.Hero_Ifreet;

public class HeroManagmentPanel extends JPanel{

	
	
Image bckground=null;
JScrollPane scrollFrame;
	BattleZoneHome battleZoneHome;
	
	public  HeroManagmentPanel() {
		// TODO Auto-generated constructor stub
	
	
		
	setSize(700,450);
	//setPreferredSize(new Dimension(50, 104));
	setLayout(new FlowLayout());
	//setLayout(null);
	setBorder(BorderFactory.createLineBorder(Color.green));

	
	battleZoneHome=new BattleZoneHome();
	add(battleZoneHome);
	
	add(FAiF.gameScreen.locationScope);
	Hero tmphero=new Hero_Ifreet();
	tmphero.addHeroAbilities();
	FAiF.gameScreen.heroStock.addHero(tmphero);
	
	
	 tmphero=new Hero_Ifreet();
	tmphero.addHeroAbilities();
	FAiF.gameScreen.heroStock.addHero(tmphero);
	/*
	Hero tmphero1=new Hero_Ifreet();
	tmphero1.setFlagIsThisEnemy(true);
	FAiF.gameScreen.heroStock.addHero(tmphero1);
	FAiF.gameScreen.heroStock.addHForce();
	
	Location tmploc=new Location();
	tmploc.addEnemys(tmphero1.getId(), 0, 0);
	add (tmploc);
	*/
/*
	scrollFrame = new JScrollPane(LHoH.gameScreen.locationScope);
	scrollFrame.setAutoscrolls(true);
	scrollFrame.setPreferredSize(new Dimension( 425,390));
	scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollFrame.setOpaque(false);
	scrollFrame.setBorder(null);
	//add (scrollFrame);
	*/
	
	
	

	try {
		bckground = ImageIO.read(new File("data/image/bckground/caven.gif"));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	void addLocation (){
		/*if (LHoH.gameScreen.player.locationActiveNumber<LHoH.gameScreen.player.maxPortal){
				if (LHoH.gameScreen.player.takeResurs(LHoH.gameScreen.player.getLocationAddCostGold(), 0, 0))
					LHoH.gameScreen.locationScope.addLocation();
		}
		*/
	}
	
void addComp(){
removeAll();

//add (LHoH.gameScreen.heroStockScroll);
add (scrollFrame);



//add (LHoH.gameScreen.locationScope);

}

public void paintComponent(Graphics g) {
//removeAll();	
//add (LHoH.gameScreen.heroStock);
//add (LHoH.gameScreen.locationScope);
	g.drawImage(bckground, 0, 0, null);
}

void update (){
	//locationControl.update();
}

}
