package FAiF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import HeroPackage.Hero_Ifreet;
import HeroPackage.Hero_SmallShadow;



public class LocationScope extends JPanel{
	
	static List<Location> allScope = new ArrayList<Location>();
	
	

void addLocation (){
	
	String inName;
	int inPower;
	double inwinR;
	Image inEnemy=null;;
	double inbonus50Gold,inbonus50Soul,inbonus50Tear,inbonusALLexp;
	//Location(String inName,int inPower, int inwinR, Image inEnemy,double inbonus50Gold,double inbonus50Soul, double inbonus50Tear, double inbonusALLexp){
	
	Random random = new Random();
	
	
	inName="ERROR";
	inPower=65;
	inwinR=0.35;
	try {
		inEnemy = ImageIO.read(new File("data/image/loc/loc2.gif"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	

	
	
	//Location tmpL = new Location(inName,inPower,inwinR,inEnemy,inbonus50Gold,inbonus50Soul,inbonus50Tear,inbonusALLexp);
	//allScope.add(tmpL);
	//add(tmpL);
	
}

public int getNumberAliveLocation(){
	int count=0;
	for (Location location : allScope) {
		if (location.status==1) count++;

}
	return count;
}


public LocationScope() {

	setPreferredSize(new Dimension(580, 280));

	setBorder(BorderFactory.createLineBorder(Color.black));

}

public void initiate(){
	
	Location tmploc=new Location();
	
	allScope.add(tmploc);
	Hero tmphero1=new Hero_SmallShadow();
	FAiF.gameScreen.heroStock.addHero(tmphero1);
	FAiF.gameScreen.heroStock.addHForce();
	tmploc.addEnemys(tmphero1.getId(), 0, 0);
	tmphero1.addHeroAbilities();
}

void reDrow (Graphics g){
	removeAll();
	for (Location location : allScope) {
			add(location);

	}
	
	for (Location location : allScope) {
		if (location.status==2) add(location);

}
	for (Location location : allScope) {
		if (location.status==3) add(location);

}
}

Location tmpLocationToAdd=null;

void addLocationToScope(Location tmploc){
	tmpLocationToAdd=tmploc;
}

void updateElement (){
	for (Location location : allScope) {
		location.updateElement();

}
	if (tmpLocationToAdd!=null){
		allScope.add(tmpLocationToAdd);
		tmpLocationToAdd=null;
	}
	
}

public boolean transportProjectileToGroup(Projectile projectile){
	for (Location location : allScope) {
		if(location.transportProjectileToGroup(projectile)){
			return true;
		}
			
		
	}
	return false;
}

public Location getLocationByHeroId(int id){
	for (Location location: allScope) {
		//if (location.hero1.getHeroId()==id) return location;
	}
	
	
	return null;
}





public void paintComponent(Graphics g) {
	
	
 
	//removeAll();
	Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    reDrow(g);
}



}
