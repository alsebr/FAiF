package FAiF;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.security.acl.Owner;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ItemPlace extends JPanel implements DropTargetListener{
	
	protected int itemPlaceId;
	protected int itemPlaceSlotNumber;
	
	ItemPlace(){
	setSize(85,110);
	setPreferredSize(new Dimension(85, 110));
	setLayout(new GridBagLayout());
	//setLayout(new FlowLayout());
	setBorder(BorderFactory.createLineBorder(Color.black));
	
	Random randomGenerator = new Random();
	itemPlaceId=randomGenerator.nextInt(32000);
	
	new DropTarget(this, this);
	
	
	
	}
	
	
	void init(int slotNumber){
		
		itemPlaceSlotNumber=slotNumber;
	}

	public void paintComponent(Graphics g) {
		
		//System.out.println("repaint BZ");
	 revalidate();

		Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    reDrow(g2);

	}

	/*
	public int getItemId(){
		
		for (Hero hero : HeroStock.allScope) {
			
			if ((hero.getZone()==zoneId)){
    			
    			return(hero.getId());
    		}
    			
    		
    		}
    	
		return 0;
	}
	*/
	void reDrow(Graphics g){
	    
	    	removeAll();
	    	
	    	
	    	for (Item	 item : FAiF.gameScreen.itemStock.allScope) {
	    		
	    		if (item.getOwnerHeroId()==FAiF.gameScreen.heroInfo.getIdHeroToShow()){
	    			if (item.getOwnerHeroSlotNumber()==itemPlaceSlotNumber){
	    				add(item);
	    			}
	    		}
	    	}
		
	}

	
		
		    public void dragEnter(DropTargetDragEvent evt) {
		
		// Called when the user is dragging and enters this drop target
		
		
		
		    }
		
		    public void dragOver(DropTargetDragEvent evt) {
		
		// Called when the user is dragging and moves over this drop target
		
		System.out.println("Drop over");
		
		    }
		
		    public void dragExit(DropTargetEvent evt) {
		
		// Called when the user is dragging and leaves this drop target
		
		
		
		    }
		
		    public void dropActionChanged(DropTargetDragEvent evt) {
		
		// Called when the user changes the drag action between copy or move
		
		
		    }
		
		    public void drop(DropTargetDropEvent evt) {
		
		// Called when the user finishes or cancels the drag operation
		    	
		try {
		
		    Transferable transferable = evt.getTransferable();
		
		    if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		
		  evt.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
		
		  String dragContents = (String) transferable.getTransferData(DataFlavor.stringFlavor);
		
		  evt.getDropTargetContext().dropComplete(true);
		
		  String[] tmpstr=dragContents.split("	");
		  
		  if (tmpstr!=null){
			  if (Integer.parseInt(tmpstr[0])==100){
				  int idHeroShow=FAiF.gameScreen.heroInfo.getIdHeroToShow();
				  if (idHeroShow!=-1){
					 Item tmpitem=FAiF.gameScreen.itemStock.getItemById(Integer.parseInt(tmpstr[1]));
					 tmpitem.setOwnerHeroId(FAiF.gameScreen.heroInfo.getIdHeroToShow());
					 tmpitem.setOwnerHeroSlotNumber(itemPlaceSlotNumber);
				  }
			  }
		  }
		  
		  // We append the label text to the text area when dropped
		
		  
		  
		
/*
		  if (zoneId!=1){
			  for (Hero hero : HeroStock.allScope) {
					if (hero.getZone()==zoneId) {
						hero.setZone(1);
						
					}
				} 
		  }
	*/	  
		  
		  
		  
		  
		  for (Hero hero : HeroStock.allScope) {
			if (hero.getId()==Integer.parseInt(dragContents)) {
			//	hero.setZone(zoneId);
				
			}
		}
		  
		    } else {
		
		  evt.rejectDrop();
		
		    }
		
		} catch (IOException e) {
		
		    evt.rejectDrop();
		
		} catch (UnsupportedFlavorException e) {
		
		    evt.rejectDrop();
		
		}
		
		    }
		
		 


}
