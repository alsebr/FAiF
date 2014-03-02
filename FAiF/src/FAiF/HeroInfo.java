package FAiF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class HeroInfo extends JPanel{
	int idHeroToShow=-1;
	
	public HeroInfo() {
		setSize(300,300);
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(300, 300));
		setBorder(BorderFactory.createLineBorder(Color.red));
	}
}
