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
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Item extends JPanel implements DragGestureListener,
		DragSourceListener, MouseListener {
	DragSource dragSource;
	int idItem;
	protected Image image;
	private String name = "ERROR";
private int itemType;
	private boolean flagRemoveThisTick = false;
	protected int grade = 0;
	protected int sharpen = 0;
	protected List<Integer> abilityScope = new ArrayList<Integer>();
	String[] gradePrefix= new String[] {"Сломанный",
			"Никчемный",
			" ",
			"Необычный",
			"Мастерский",
			"Великий",
			"Уникальный",
			"Эпичный",
			"Легендарный",
			"Божественный"};
	String[] gradeColor= new String[] {"black",
			"black",
			"black",
			"black",
			"black",
			"green",
			"blue",
			"magenta",
			"orange",
			"yellow"};
	
	public Item() {
		setSize(80, 105);
		setPreferredSize(new Dimension(80, 105));
		setBorder(BorderFactory.createLineBorder(Color.black));
		Random randomGenerator = new Random();
		idItem = randomGenerator.nextInt(32000);
		this.addMouseListener(this);

		dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_COPY_OR_MOVE, this);
	}

	void init(){
		
	}
	
	public void activateItem() {

	}





	void updateElement() {
		String htmlText="<html><p>";
		htmlText+=getNameHtml()+"</p>";
		
		htmlText+=FAiF.gameScreen.heroAbilityStock.getAllTipHeroAbilityByItemId(idItem);
		
		setToolTipText(htmlText);
	}

	public String getName() {
		return name;
	}
	
	public String getNameHtml() {
		String htmlText=gradePrefix[grade]+" "+name;
		 htmlText="<font color="+gradeColor[grade]+">"+htmlText+"</font>";
		return htmlText;
	}

	public void setName(String name) {
		this.name = name;
	}

	void reDrow(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(image, 0, 0, null);
		g2.drawString("I", 10, 15);
	}

	protected int getIdItem() {
		return idItem;
	}

	protected void SelfDestroy() {
		setFlagRemoveThisTick(true);
	}

	public void paintComponent(Graphics g) {
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
		if (arg0.getClickCount() == 2) {
			activateItem();
		}
	}

	public boolean isFlagRemoveThisTick() {
		return flagRemoveThisTick;
	}

	public void setFlagRemoveThisTick(boolean flagRemoveThisTick) {
		this.flagRemoveThisTick = flagRemoveThisTick;
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

		Transferable transferable = new StringSelection("100" + "	"
				+ getIdItem());
		dragSource.startDrag(arg0, DragSource.DefaultCopyDrop, transferable,
				this);

	}

	

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public void addAbilityToItem(HeroAbility heroAbility){
		FAiF.gameScreen.heroAbilityStock.addAbility(heroAbility);
		abilityScope.add(heroAbility.getHeroAbilityId());
		heroAbility.setItemId(idItem);
	}
	
}
