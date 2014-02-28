

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

public class HeroManagmentPanel extends JPanel{
	class LocationControl extends JPanel{
		JLabel addLocationInfo=new JLabel();
		JButton addLocation = new JButton();
		public LocationControl() {
			setPreferredSize(new Dimension(420, 40));
			setLayout(new FlowLayout());
			setBorder(BorderFactory.createLineBorder(Color.white));
			setOpaque(false);
			
			addLocation.setText("Открыть портал");
			add(addLocation);
			
			addLocation.addActionListener(new ActionListener(){
		    	  public void actionPerformed(ActionEvent e) {
						//LHoH.gameScreen.locationPanel.addLocation();
						
		    	  }
		    	});
			
			int locNumber, locNumberMax;
			
			


		
			/*
			String tmptext;
			tmptext="<html> <p align=center><Font color=white>";
			tmptext+="Открыто порталов: "+locNumber+" из "+locNumberMax;
			tmptext+="<br>Стоимость открытия: "+LHoH.gameScreen.player.locationAddCostGold+" золота";
			*/
			//addLocationInfo.setText(tmptext);
			add (addLocationInfo);
		}
		
		void update (){


		}
	}
	
	
Image bckground=null;
JScrollPane scrollFrame;
	LocationControl locationControl =new LocationControl();
	public  HeroManagmentPanel() {
		// TODO Auto-generated constructor stub
	
	
		
	setSize(700,450);
	//setPreferredSize(new Dimension(50, 104));
	setLayout(new FlowLayout());
	//setLayout(null);
	//setBorder(BorderFactory.createLineBorder(Color.green));

	
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
add (locationControl);


//add (LHoH.gameScreen.locationScope);

}

public void paintComponent(Graphics g) {
//removeAll();	
//add (LHoH.gameScreen.heroStock);
//add (LHoH.gameScreen.locationScope);
	g.drawImage(bckground, 0, 0, null);
}

void update (){
	locationControl.update();
}

}
