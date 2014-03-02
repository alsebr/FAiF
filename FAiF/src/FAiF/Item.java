package FAiF;

import static FAiF.Constant.CREATURE_OUT_OF_BATTLE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
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

public class Item extends JPanel implements DragGestureListener,
DragSourceListener, MouseListener{
	DragSource dragSource;
	int idItem;
	protected Image image;
	protected double charge_now=0;
	protected double charge_max=0;
	private String name="ERROR";
	private double ttl=-1000;
	private int ownerHeroId=-1;
	private int ownerHeroSlotNumber=-1;
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
	    idItem=randomGenerator.nextInt(32000);
	    this.addMouseListener(this);
	    
	    dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_COPY_OR_MOVE, this);
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
	
	protected int getIdItem(){
		return idItem;
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



	@Override
	public void dragDropEnd(DragSourceDropEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dragEnter(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dragExit(DragSourceEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dragOver(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dropActionChanged(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dragGestureRecognized(DragGestureEvent arg0) {
		

	//	status=CREATURE_OUT_OF_BATTLE;
			Transferable transferable = new StringSelection("100"+"	"+getIdItem());
			dragSource.startDrag(arg0, DragSource.DefaultCopyDrop,
					transferable, this);
		
	
		
	}



	public int getOwnerHeroId() {
		return ownerHeroId;
	}



	public void setOwnerHeroId(int ownerHeroId) {
		this.ownerHeroId = ownerHeroId;
	}



	public int getOwnerHeroSlotNumber() {
		return ownerHeroSlotNumber;
	}



	public void setOwnerHeroSlotNumber(int ownerHeroSlotNumber) {
		this.ownerHeroSlotNumber = ownerHeroSlotNumber;
	}

}
