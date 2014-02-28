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

	public void updateElement() {
		for (Projectile projectile : projectileScope) {
			boolean tmpFlagNotUsed = true;
			if (projectile.getProjectileType() == FAiF.gameScreen.PROJECTILE_FIRST) {
				for (int i = heroZonesArray.length - 1; i > -1; i--) {
					if ((tmpFlagNotUsed)
							&& (FAiF.gameScreen.heroStock
									.getHeroByZoneId(heroZonesArray[i].zoneId) != null)) {
						FAiF.gameScreen.heroStock.getHeroByZoneId(
								heroZonesArray[i].zoneId).addProjectile(
								projectile);
						tmpFlagNotUsed = false;

					}
				}
			} else {

				if (projectile.getProjectileType() == FAiF.gameScreen.PROJECTILE_FIRST) {
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
