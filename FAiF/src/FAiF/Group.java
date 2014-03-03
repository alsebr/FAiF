package FAiF;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
public abstract class Group extends JPanel {
	BattleZone[] heroZonesArray = new BattleZone[3];
	List<Projectile> projectileScope = new ArrayList<Projectile>();

	protected Group() {

		setSize(90 * 4 + 10, 120);
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createLineBorder(Color.red));

		revalidate();

		init();

	}

	protected void init() {

	}

	public boolean isHereAliveInFightHeroes(){
		for (int i = 0; i < heroZonesArray.length; i++) {
			Hero tmphero=FAiF.gameScreen.heroStock.getHeroByZoneId(heroZonesArray[i]
					.getZoneId());
			if (tmphero != null) {
				if (tmphero.isAliveInFight())return true;
			}
		}
		
		return false;
	}
	
	
	public boolean isHereHeroes() {
		for (int i = 0; i < heroZonesArray.length; i++) {
			if (FAiF.gameScreen.heroStock.getHeroByZoneId(heroZonesArray[i]
					.getZoneId()) != null) {
				return true;
			}
		}
		return false;
	}

	public boolean checkHereHeroWithId(int heroId) {

		for (int i = 0; i < heroZonesArray.length; i++) {
			if (heroZonesArray[i].getZoneId() == FAiF.gameScreen.heroStock
					.getHeroById(heroId).getZone()) {
				return true;
			}
		}

		return false;
	}

	public void addProjectile(Projectile projectile) {
		projectileScope.add(projectile);
	}

	public void startFightHeroesInGroup() {
		for (int i = heroZonesArray.length - 1; i > -1; i--) {
			if ((FAiF.gameScreen.heroStock
					.getHeroByZoneId(heroZonesArray[i].zoneId) != null)) {
				FAiF.gameScreen.heroStock.getHeroByZoneId(
						heroZonesArray[i].zoneId).startFight();

			}
		}
	}

	public void stopFightHeroesInGroup() {
		for (int i = heroZonesArray.length - 1; i > -1; i--) {
			if ((FAiF.gameScreen.heroStock
					.getHeroByZoneId(heroZonesArray[i].zoneId) != null)) {
				FAiF.gameScreen.heroStock.getHeroByZoneId(
						heroZonesArray[i].zoneId).stopFight();

			}
		}
	}

	public boolean checkIsHeroesChange() {
		for (int i = heroZonesArray.length - 1; i > -1; i--) {
			if ((FAiF.gameScreen.heroStock
					.getHeroByZoneId(heroZonesArray[i].zoneId) != null)) {
				if (FAiF.gameScreen.heroStock.getHeroByZoneId(
						heroZonesArray[i].zoneId).isOutOfBattle())
					return true;

			}
		}
		return false;
	}

	public boolean isChanged() {
		// TO-DO 123
		return false;
	}

	public void updateElement() {
		for (Projectile projectile : projectileScope) {
			boolean tmpFlagNotUsed = true;
			if (projectile.getProjectileType() == PROJECTILE_FIRST) {
				for (int i = heroZonesArray.length - 1; i > -1; i--) {
					Hero tmphero=FAiF.gameScreen.heroStock.getHeroByZoneId(heroZonesArray[i].zoneId);
					if ((tmpFlagNotUsed)&& ( tmphero!= null)) {
						if (!tmphero.isDead()){
							tmphero.addProjectile(projectile);
							tmpFlagNotUsed = false;	
						}
						

					}
				}
			} else {

				if (projectile.getProjectileType() == PROJECTILE_ALL) {
					for (int i = heroZonesArray.length - 1; i > -1; i--) {
						if ((FAiF.gameScreen.heroStock
								.getHeroByZoneId(heroZonesArray[i].zoneId) != null)) {
							FAiF.gameScreen.heroStock.getHeroByZoneId(
									heroZonesArray[i].zoneId).addProjectile(
									projectile);

						}
					}
				}
			}
		}
		projectileScope.removeAll(projectileScope);
	}
}
