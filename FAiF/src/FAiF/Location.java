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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import static FAiF.Constant.*;
public class Location extends JPanel {

	GroupHero groupHero;
	GroupEnemy groupEnemy;
	int status=LOCATION_NOT_FIGHT;
	double status_timer=-1;
	private String mLocationName;

	double rewardXP;
	static List<Reward> rewardScope = new ArrayList<Reward>();
	
	class Reward{
		Item rewardItem;
		double chance;
		public Reward(Item rewardItem, double chance) {
			this.rewardItem = rewardItem;
			this.chance = chance;
		}

	}
	
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
		rewardXP=50;
	}

	void reDrow(Graphics g) {

	}

	void playerWin(){
		for (Reward reward : rewardScope) {
			if (frameworkFAiF.checkChance(reward.chance)){
				//item add
			}
		}
		
		groupHero.addExp(rewardXP);
		
	}
	
	void updateElement() {
		
		switch (status) {
		case LOCATION_NOT_FIGHT:
			if (groupHero.isHereHeroes()){
				status=LOCATION_PREFER_TO_FIGHT;
			}
			break;
		case LOCATION_PREFER_TO_FIGHT:
			if (status_timer<0)  status_timer=3;
			status_timer-=(double)1/60;
			if (status_timer<=0){
				status_timer=-1;
				status=LOCATION_FIGHT_NOW;
				groupHero.startFightHeroesInGroup();
				groupEnemy.startFightHeroesInGroup();
			}
			break;
		case LOCATION_FIGHT_NOW:
			groupEnemy.updateElement();
			groupHero.updateElement();
			if(groupHero.checkIsHeroesChange()) {
				status=LOCATION_NOT_FIGHT;
				groupHero.stopFightHeroesInGroup();
				groupEnemy.stopFightHeroesInGroup();
			}
			
			
			if (!groupEnemy.isHereAliveInFightHeroes()&&groupHero.isHereAliveInFightHeroes()){
				status=LOCATION_AFTER_FIGHT_WIN;
				groupHero.stopFightHeroesInGroup();
				groupEnemy.stopFightHeroesInGroup();
				playerWin();
			}
			if (!groupHero.isHereAliveInFightHeroes()){
				status=LOCATION_AFTER_FIGHT_DEFEAT;
				groupHero.stopFightHeroesInGroup();
				groupEnemy.stopFightHeroesInGroup();
			}
			
			break;
		case LOCATION_AFTER_FIGHT_WIN:
		case LOCATION_AFTER_FIGHT_DEFEAT:
			if (status_timer<0)  status_timer=3;
			status_timer-=(double)1/60;
			if (status_timer<=0){
				status_timer=-1;
				status=LOCATION_NOT_FIGHT;

			}
			break;
		default:
			break;
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
