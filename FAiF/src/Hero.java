

import java.awt.datatransfer.StringSelection;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class Hero extends JPanel implements DragGestureListener,
		DragSourceListener, MouseListener {
	DragSource dragSource;

	public double getPower() {
		double tmpPower = getPurePower()+ getBonusPower();

	//if (tmpPower<1)tmpPower=1;
		//System.out.println(tmpPower);
		return tmpPower ;
	}

	public double getPurePower() {
		return getHeroStat().strp * strToPowerRatio;
	}

	public double getBonusPower() {
		return getPower_bonus();
	}

	public void setPower(double power) {
		this.power = power;
	}



	public String getHeroTip() {
		return htmlTextHeroTip;
	}

	private boolean flagDieThisTick = false;
	private boolean flagLvlUpThisTick = false;
	private boolean flagIsSilenced = false;
	double exp;

	protected double ttl;

	public double getTtl() {
		return ttl;
	}

	public void addTtl(double ttl) {
		this.ttl += ttl;
		if (this.ttl<0) this.ttl=0;
	}

	public String name;
	protected String classH;
	protected Image image;
	public int status; // 1 - live, 0 - dead, 2 - strage, 3-removed
	int lvl;
	private int zone;
	private int id;
	double expNeedExp;
	private HeroStat heroStat;
	
	private HeroStat heroStat_bonus= new HeroStat(0,0,0,0,0);
	String htmlTextHeroTip = "";
	private double power;
	private double power_bonus;

	private double costGold, costSoul, costTear;

	double statPointForLvl;
	public double getStatPointForLvl() {
		return statPointForLvl;
	}

	public void setStatPointForLvl(double statPointForLvl) {
		this.statPointForLvl = statPointForLvl;
	}

	HeroStat statPointForLvlRatio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private HeroStat statPointForLvlRatioPlayerModify;
	private int heroStatPlayerPref = 0;
	HeroStat statPointForLvlRatioFinal;
	double strToPowerRatio = 2;
	double vitToTTLRatio = 2;

	double deltaExp;
	double deltaPower;

	public Hero() {
		setSize(80, 105);
		setPreferredSize(new Dimension(80, 105));
		setBorder(BorderFactory.createLineBorder(Color.black));

		Random randomGenerator = new Random();
		id = randomGenerator.nextInt(32000);

		dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_COPY_OR_MOVE, this);

		this.addMouseListener(this);

	}

	public void addTTL() {

	}

	public void addHeroAbilities() {

	}

	public void init(String name, Image inImage, double inDeltaExp,
			double statPointPerLvl, HeroStat heroStat, HeroStat heroStatRatio,
			double strToPowerRatio, double vitToTTLRatio,
			String htmlTextHeroTip, double costGold, double costSoul,
			double costTear) {

		Random randomGenerator = new Random();
		id = randomGenerator.nextInt(32000);

		this.name = name;
		// this.power = power;
		this.exp = 1;

		this.ttl = 0;

		this.status = 1;
		this.lvl = 1;
		this.zone = 1;
		this.deltaExp = inDeltaExp;
		this.image = inImage;
		this.setHeroStat(heroStat);
		this.statPointForLvlRatio = heroStatRatio;
		this.statPointForLvl = statPointPerLvl;
		this.strToPowerRatio = strToPowerRatio;
		this.vitToTTLRatio = vitToTTLRatio;
		this.htmlTextHeroTip = htmlTextHeroTip;
		this.setCostGold(costGold);
		this.costSoul = costSoul;
		this.setCostTear(costTear);
	}

	boolean isDead() {
		if (status == 0)
			return true;
		return false;
	}

	public String getHeroName() {
		return name;
	}

	public void addPower(double addP) {
		power += addP;

	}

	void addExp(double addExp) {
		exp += addExp;
	}

	HeroStat getHeroStatPerLvlFinal() {
		HeroStat tmpherostat = new HeroStat(0, 0, 0,0,0);
/*
		HeroStat playerModify = new HeroStat(0, 0, 0);

		if (heroStatPlayerPref == 0) {
			playerModify = new HeroStat(1, 1, 1);
		}
		if (heroStatPlayerPref == 1) {
			playerModify = new HeroStat(5, 2, 1);
		}
		if (heroStatPlayerPref == 2) {
			playerModify = new HeroStat(1, 5, 2);
		}
		if (heroStatPlayerPref == 3) {
			playerModify = new HeroStat(2, 1, 5);
		}

		tmpherostat.intp = statPointForLvlRatio.intp * playerModify.intp;
		tmpherostat.vitp = statPointForLvlRatio.vitp * playerModify.vitp;
		tmpherostat.strp = statPointForLvlRatio.strp * playerModify.strp;

		double stabilze = tmpherostat.intp + tmpherostat.vitp
				+ tmpherostat.strp;

		tmpherostat.intp = tmpherostat.intp / stabilze * statPointForLvl;
		tmpherostat.vitp = tmpherostat.vitp / stabilze * statPointForLvl;
		tmpherostat.strp = tmpherostat.strp / stabilze * statPointForLvl;
*/
		return tmpherostat;
	}

	void lvlUp() {
		setFlagLvlUpThisTick(true);
		lvl += 1;

		heroStat.intp += getHeroStatPerLvlFinal().intp;
		heroStat.strp += getHeroStatPerLvlFinal().strp;
		heroStat.vitp += getHeroStatPerLvlFinal().vitp;

		// LHoH.gameScreen.bottomInfo.chat.addTextChat(name + " достиг " + lvl +
		// " уровня, его мощь теперь " + (int) power);
	}

	double getLeftTime() {

		return getHeroStat().vitp * vitToTTLRatio - ttl;
	}

	double getLifeTimeTotal() {

		return getHeroStat().vitp * vitToTTLRatio;
	}

	void resetTick() {
		power_bonus = 0;

		heroStat_bonus.strp=0;
		heroStat_bonus.vitp=0;
		heroStat_bonus.intp=0;
		setFlagDieThisTick(false);
		setFlagLvlUpThisTick(false);
		setFlagIsSilenced(false);
	}

	protected void Update() {

	}

	public String getStatusTip(){
	
		//if (isFlagIsSilenced()) return "Молчание";
		
		//return LHoH.gameScreen.heroAbilityStock.getAllAbilityTipByHero(getId());
		return "";
	}
	
	void setStatus(int inStatus) {
		status = inStatus;
	}

	public void mouseClicked(MouseEvent arg0) {
//		if (arg0.getClickCount() == 2) {
	//		LHoH.gameScreen.heroViewScreen.activate(id);
		//}

	}

	void reDrow(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(image, 0, 0, null);
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(2));

		g2.setFont(new Font("Arial", Font.BOLD, 16));

		int min, sec;
		min = (int) (getLeftTime() / 60);
		sec = (int) (getLeftTime() - min * 60);

		g2.drawString(Integer.toString((int) getPower()), 15, 98);

		switch (status) {
		case 0:
			g2.drawString("DEAD", 5, 15);

			break;
		case 1:

			g2.drawString(min + ":" + sec, 5, 15);

			break;
		case 2:
			g2.drawString("Страж", 15, 50);
			g2.drawString(min + ":" + sec, 5, 15);

			break;
		default:
			break;
		}

		// exp
		g2.setColor(Color.red);

		double expNeedExp = deltaExp * ((double) lvl + (double) lvl * lvl / 20);
		double expNeedExpPr = deltaExp
				* ((double) (lvl - 1) + (double) (lvl - 1) * (lvl - 1) / 20);
		g2.fillRect(3, 10, 3,
				(int) (90 * (exp - expNeedExpPr) / (expNeedExp - expNeedExpPr)));
	}

	public void paintComponent(Graphics g) {

		// removeAll();
		// Graphics2D g2 = (Graphics2D) g;
		// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		reDrow(g);
	}

	public void dragGestureRecognized(DragGestureEvent evt) {
		if (status == 1) {
			Transferable transferable = new StringSelection(
					Integer.toString(id));// !!!!!
			dragSource.startDrag(evt, DragSource.DefaultCopyDrop, transferable,
					this);
		}
	}

	public void dragEnter(DragSourceDragEvent evt) {

		// Called when the user is dragging this drag source and enters the drop
		// target

		System.out.println("Drag enter");

	}

	public void dragOver(DragSourceDragEvent evt) {

		// Called when the user is dragging this drag source and moves over the
		// drop target

		System.out.println("Drag over");

	}

	public void dragExit(DragSourceEvent evt) {

		// Called when the user is dragging this drag source and leaves the drop
		// target

		System.out.println("Drag exit");

	}

	public void dropActionChanged(DragSourceDragEvent evt) {

		// Called when the user changes the drag action between copy or move

		System.out.println("Drag action changed");

	}

	public void dragDropEnd(DragSourceDropEvent evt) {

		// Called when the user finishes or cancels the drag operation

		System.out.println("Drag action End");

	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	public HeroStat getHeroStat() {
		System.out.println("1     "+heroStat.strp);
		System.out.println("2     "+this.heroStat_bonus.strp);
		//System.out.println(tmpPower);
		return HeroStat.summ(heroStat, heroStat_bonus);
	}
	
	public HeroStat getPureHeroStat() {
		return heroStat;
	}

	public void setHeroStat(HeroStat heroStat) {
		this.heroStat = heroStat;
	}

	public double getPower_bonus() {
		return power_bonus;
	}

	public void setPower_bonus(double power_bonus) {
		this.power_bonus = power_bonus;
	}

	public void addPower_bonus(double power_bonus) {
		this.power_bonus += power_bonus;
	}

	public boolean isFlagLvlUpThisTick() {
		return flagLvlUpThisTick;
	}

	public void setFlagLvlUpThisTick(boolean flagLvlUpThisTick) {
		this.flagLvlUpThisTick = flagLvlUpThisTick;
	}

	public boolean isFlagDieThisTick() {
		return flagDieThisTick;
	}

	public void setFlagDieThisTick(boolean flagDieThisTick) {
		this.flagDieThisTick = flagDieThisTick;
	}

	public boolean isRemoved() {
		if (status == 3) {
			return true;
		}
		return false;
	}

	public void setRemoved() {
		status = 3;
		zone = -1;
	}

	public HeroStat getStatPointForLvlRatioPlayerModify() {
		return statPointForLvlRatioPlayerModify;
	}

	public void setStatPointForLvlRatioPlayerModify(
			HeroStat statPointForLvlRatioPlayerModify) {
		this.statPointForLvlRatioPlayerModify = statPointForLvlRatioPlayerModify;
	}

	public int getHeroStatPlayerPref() {
		return heroStatPlayerPref;
	}

	public void setHeroStatPlayerPref(int heroStatPlayerPref) {
		this.heroStatPlayerPref = heroStatPlayerPref;
	}

	public double getCostSoul() {
		return costSoul;
	}

	public void setCostSoul(double costSoul) {
		this.costSoul = costSoul;
	}

	public double getCostTear() {
		return costTear;
	}

	public void setCostTear(double costTear) {
		this.costTear = costTear;
	}

	public double getCostGold() {
		return costGold;
	}

	public void setCostGold(double costGold) {
		this.costGold = costGold;
	}

	public HeroStat getHeroStat_bonus() {
		return heroStat_bonus;
	}

	public void addHeroStat_bonus(HeroStat heroStat_bonus) {
		
		
		this.heroStat_bonus.strp += heroStat_bonus.strp;
		this.heroStat_bonus.vitp += heroStat_bonus.vitp;
		this.heroStat_bonus.intp += heroStat_bonus.intp;


		System.out.println(this.heroStat_bonus.strp);
		
	}

	public boolean isFlagIsSilenced() {
		return flagIsSilenced;
	}

	public void setFlagIsSilenced(boolean flagIsSilenced) {
		this.flagIsSilenced = flagIsSilenced;
	}

}
