

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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Group extends JPanel {



	BattleZone[] heroZonesArray=new BattleZone[3];
	
	
	double power;
	double winR;
	int status; // 1- live 3 -end


	
	private String locationName;

	Image imageGold, imageSoul, imageTear;

	protected Group() {
		
		setSize(90*4+10,120);
		//setPreferredSize(new Dimension(81, 106));
		setLayout(new FlowLayout());
		//setLayout(new FlowLayout());
		setBorder(BorderFactory.createLineBorder(Color.red));
		
		for (int i = 0; i < heroZonesArray.length; i++) {
			heroZonesArray[i]=new BattleZone();
			add(heroZonesArray[i]);
		}
		revalidate();
		
	}
	

	
	
}
