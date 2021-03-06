package FAiF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ItemPackage.Item_Weapon1;



public class ItemStock extends JPanel {
	public static List<Item> allScope = new ArrayList<Item>();

	public ItemStock() {
		setPreferredSize(new Dimension(200, 200));
		new FlowLayout(FlowLayout.CENTER, 3, 3);
		setSize(200, 200);
		setBorder(BorderFactory.createLineBorder(Color.white));


//allScope.add(new Item_BrokenClock(1));

		allScope.add(new Item_Weapon1(1));

		setOpaque(false);
		

	}

	public void paintComponent(Graphics g) {

		System.out.println("repaint");

		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		reDrow(g);
	}

	void updateElement() {

		List<Item> tmpScope = new ArrayList<Item>();
		
		for (Item item : allScope) {
			if (item.isFlagRemoveThisTick()) {
				tmpScope.add(item);
				
			}
			
		}

		for (Item item : tmpScope) {
				allScope.remove(item);
		}
		
		
		
		for (Item item : allScope) {
			item.updateElement();

		}

	}

	void reDrow(Graphics g) {
		revalidate();
		removeAll();
		List<Item> tmpScope = new ArrayList<Item>();
		for (Hero hero : FAiF.gameScreen.heroStock.allScope) {
			for (int i = 0; i < hero.itemArray.length; i++) {
			tmpScope.add(getItemById(hero.itemArray[i]));	
			}
		}
		List<Item> tmpScope2 = new ArrayList<Item>();
		tmpScope2.addAll(allScope);
		tmpScope2.removeAll(tmpScope);

		for (Item item : tmpScope2) {
			add(item);
		}
	}

	boolean getItem(String nameItem) {
		for (Item item : allScope) {
			if (item.getName() == nameItem) {
				allScope.remove(item);
				return true;
			}
		}
		return false;
	}
	
	public Item getItemById(int id) {
		for (Item item : allScope) {
			if (item.getIdItem() == id) {
				return item;
				
			}
		}
		return null;
	}

	public int checkItemCount(String nameItem) {
		int count = 0;
		for (Item item : allScope) {
			if (item.getName() == nameItem) {
				count++;

			}
		}
		return count;
	}
	
	public boolean checkItemWithId(int itemId) {
		
		for (Item item : allScope) {
			if (item.getIdItem() == itemId) {
				return true;

			}
		}
		return false;
	}

}
