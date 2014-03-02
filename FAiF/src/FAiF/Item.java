package FAiF;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Item extends JPanel implements MouseListener{
	int id;
	protected Image image;
	protected double charge_now=0;
	protected double charge_max=0;
	private String name="ERROR";
	private double ttl=-1000;
	private int ownerHeroId;
	private int ownerHeroSlotNumber;
	private boolean flagRemoveThisTick=false;
	private boolean flagItemWasActivated=false;
	
	public double getTtl() {
		return ttl;
	}

	

	public void setTtl(double ttl) {
		this.ttl = ttl;
	}

	protected int grade=0;
	public Item() {
		setSize(80	,105);
		setPreferredSize(new Dimension(80,105));
		setBorder(BorderFactory.createLineBorder(Color.black));
		Random randomGenerator = new Random();
	    id=randomGenerator.nextInt(32000);
	    this.addMouseListener(this);
	}
	
	public void activateItem (){
		//ttl-=(double)1/60;
	}
	
	void checkCharge (){
		
	}
	
	void doActionBeforeTtlDead(){
		
	}
	
	void checkTTL(){
		if (ttl>-1000){
			ttl-=(double)1/60;
			
			if (ttl<=0){
				doActionBeforeTtlDead();
				setFlagRemoveThisTick(true);
			}
		}
	}
	
	void update (){
		checkCharge();
		checkTTL();
		checkTimerAction();
		
	}
	
	protected void checkTimerAction() {
		// TODO Auto-generated method stub
		
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	
	void reDrow (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		
		//g.setColor(new Color(255,255,255,128));
		//g.fillRect(0, 0,80 ,105);
		
		g.drawImage(image, 0, 0, null);
		

		
		if(grade>0){
			Image pentagr=null;
			try {
				pentagr = ImageIO.read(new File("data/image/interface/pentagr.gif"));
			} catch (IOException e) {
			}
			for (int i = 0; i < grade; i++) {
				g.drawImage(pentagr, 3, 70-i*33, null);	
			}
			
		}
		
		
	}
	
	protected int getId(){
		return id;
	}
	
	protected void SelfDestroy (){
		setFlagRemoveThisTick(true);
	}

	public void paintComponent(Graphics g) {
		
		
	 
		//removeAll();
		//Graphics2D g2 = (Graphics2D) g;
	    //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    reDrow(g);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount()==2){
			
		
		activateItem();
		}
		
	}



	public boolean isFlagRemoveThisTick() {
		return flagRemoveThisTick;
	}



	public void setFlagRemoveThisTick(boolean flagRemoveThisTick) {
		this.flagRemoveThisTick = flagRemoveThisTick;
	}



	public boolean isFlagItemWasActivated() {
		return flagItemWasActivated;
	}



	public void setFlagItemWasActivated(boolean flagItemWasActivated) {
		this.flagItemWasActivated = flagItemWasActivated;
	}

}
