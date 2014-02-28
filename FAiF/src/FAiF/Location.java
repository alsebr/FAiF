package FAiF;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Location extends JPanel {

	GroupHero groupHero;
	GroupEnemy groupEnemy;
	int status=FAiF.gameScreen.LOCATION_NOT_FIGHT;
	double status_timer=0;
	private String mLocationName;

	protected Location() {
		groupHero = new GroupHero();
		groupEnemy = new GroupEnemy();
		add(groupHero);
		add(groupEnemy);
		
		setSize(90 * 4*2 + 20, 125);
		setBorder(BorderFactory.createLineBorder(Color.yellow));
		revalidate();
		
	}

	protected void init() {

	}

	void reDrow(Graphics g) {

	}

	void updateElement() {
		if (status==FAiF.gameScreen.LOCATION_FIGHT_NOW){
			groupEnemy.updateElement();
			groupHero.updateElement();
		}else{
			
		}
	}

	public void addEnemys(int heroId1,int heroId2,int heroId3){
		groupEnemy.addEnemys(heroId1,heroId2,heroId3);
	}
	
	
	public boolean transportProjectileToGroup(Projectile projectile){
	
		if (groupHero.checkHereHeroWithId(projectile.getFromId())){
			groupEnemy.addProjectile(projectile);
			return true;
		}
		if (groupEnemy.checkHereHeroWithId(projectile.getFromId())){
			groupHero.addProjectile(projectile);
			return true;
		}
		
		return false;
	}
	
	public void paintComponent(Graphics g) {

		System.out.println("repaint");

		// removeAll();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		reDrow(g);
	}

}
