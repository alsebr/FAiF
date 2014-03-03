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

public class LocationScope extends JPanel {
	static List<Location> allScope = new ArrayList<Location>();
	private Location tmpLocationToAdd = null;

	public LocationScope() {
		setPreferredSize(new Dimension(620, 1280));
		setBorder(BorderFactory.createLineBorder(Color.white));

	}

	void addLocation() {
	}

	public void initiate() {

		Location tmploc = new Location();

		allScope.add(tmploc);
		Hero tmphero1 = new Hero_SmallShadow();
		FAiF.gameScreen.heroStock.addHero(tmphero1);
		FAiF.gameScreen.heroStock.addHForce();
		tmploc.addEnemys(tmphero1.getId(), 0, 0);
		tmploc.init();
		tmphero1.addHeroAbilities();

		tmploc = new Location();

		allScope.add(tmploc);
		tmphero1 = new Hero_Ifreet();
		FAiF.gameScreen.heroStock.addHero(tmphero1);
		FAiF.gameScreen.heroStock.addHForce();
		tmploc.addEnemys(tmphero1.getId(), 0, 0);
		tmploc.init();
		tmphero1.addHeroAbilities();
	}

	void reDrow(Graphics g) {
		removeAll();
		for (Location location : allScope) {
			add(location);
		}
	}

	void addLocationToScope(Location tmploc) {
		tmpLocationToAdd = tmploc;
	}

	void updateElement() {
		for (Location location : allScope) {
			location.updateElement();

		}
		if (tmpLocationToAdd != null) {
			allScope.add(tmpLocationToAdd);
			tmpLocationToAdd = null;
		}

	}

	public boolean transportProjectileToGroup(Projectile projectile) {
		for (Location location : allScope) {
			if (location.transportProjectileToGroup(projectile)) {
				return true;
			}

		}
		return false;
	}

	public Location getLocationByHeroId(int id) {
		for (Location location : allScope) {
			// if (location.hero1.getHeroId()==id) return location;
		}

		return null;
	}

	public void paintComponent(Graphics g) {

		// removeAll();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		reDrow(g);
	}

}
