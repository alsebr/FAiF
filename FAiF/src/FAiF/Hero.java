package FAiF;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import static FAiF.Constant.*;

public class Hero extends JPanel implements DragGestureListener,
		DragSourceListener, MouseListener {
	DragSource dragSource;

	List<Projectile> projectileScope = new ArrayList<Projectile>();
	List<HeroEffect> heroEffects = new ArrayList<HeroEffect>();
	
	int[] itemArray=new int[] {-1,-1,-1};
	
	private boolean flagDieThisTick = false;
	private boolean flagLvlUpThisTick = false;
	private boolean flagIsSilenced = false;
	private boolean flagIsThisEnemy = false;
	private int grade;
	private boolean flagIsUsesAbility = false;
	
	public boolean isUsesAbilityNow(){
		if(status==CREATURE_FIGHT_ALIVE) return true;
		return false;
	}
	
	double exp;
	double hpMax = 250;
	double hpCurrent = 250;
	public String name;
	protected Image image;
	public int status=CREATURE_OUT_OF_BATTLE; // 1 - live, 0 - dead, 2 - strage, 3-removed
	int lvl;
	private int zone;
	private int idHero;
	public int getIdHero() {
		return idHero;
	}

	public void setIdHero(int idHero) {
		this.idHero = idHero;
	}

	double expNeedExp;
	private HeroStat heroStat;
	private HeroStat heroStat_bonus = new HeroStat(0, 0, 0, 0, 0);
	String htmlTextHeroTip = "";
	double statPointForLvl;
	HeroStat statPointForLvlRatio;
	private HeroStat statPointForLvlRatioPlayerModify;
	private int heroStatPlayerPref = 0;
	HeroStat statPointForLvlRatioFinal;
	double deltaExp;

	public String getHeroTip() {
		return htmlTextHeroTip;
	}

	public boolean isFlagIsThisEnemy() {
		return flagIsThisEnemy;
	}

	public void setFlagIsThisEnemy(boolean flagIsThisEnemy) {
		this.flagIsThisEnemy = flagIsThisEnemy;
	}

	public double getStatPointForLvl() {
		return statPointForLvl;
	}

	public void setStatPointForLvl(double statPointForLvl) {
		this.statPointForLvl = statPointForLvl;
	}

	public int getId() {
		return idHero;
	}

	public void setId(int id) {
		this.idHero = id;
	}

	public boolean isOutOfBattle(){
		if (status==CREATURE_OUT_OF_BATTLE) return true;
		return false;
	}
	
	public Hero() {
		setSize(80, 105);
		setPreferredSize(new Dimension(80, 105));
		setBorder(BorderFactory.createLineBorder(Color.yellow));

		Random randomGenerator = new Random();
		idHero = randomGenerator.nextInt(32000);

		dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_COPY_OR_MOVE, this);

		this.addMouseListener(this);
		zone = 1;
		//status = 1;
	}

	public void addHeroAbilities() {

	}

	
	public double doPhysModifier(double damage){
		double tmpd=damage;
		
		tmpd=(double)tmpd*((double)getHeroStat().strp/100+1);
		return tmpd;
	}
	
	public Projectile modifyAtackProjectile(Projectile projectile){
		if (projectile.getDamageType()==PROJECTILE_DAMAGE_TYPE_PHYSICAL){
			projectile.setDmg(doPhysModifier(projectile.getDmg()));
		}
		return projectile;
	}
	
	public void init(String name, Image inImage, double inDeltaExp,
			double statPointPerLvl, HeroStat heroStat, HeroStat heroStatRatio,
			String htmlTextHeroTip) {

		Random randomGenerator = new Random();
		idHero = randomGenerator.nextInt(32000);

		this.name = name;
		this.exp = 1;
		
		this.lvl = 1;
		this.zone = 1;
		this.deltaExp = inDeltaExp;
		this.image = inImage;
		this.setHeroStat(heroStat);
		this.statPointForLvlRatio = heroStatRatio;
		this.statPointForLvl = statPointPerLvl;
		this.htmlTextHeroTip = htmlTextHeroTip;
		this.hpMax=this.heroStat.vitp*10;
		this.hpCurrent=this.hpMax;
	}

	public boolean isDead() {
		if (status == CREATURE_FIGHT_DEAD)
			return true;
		return false;
	}
	public boolean isAliveInFight() {
		if (status == CREATURE_FIGHT_ALIVE)
			return true;
		return false;
	}

	public String getHeroName() {
		return name;
	}

	
	void lvlUp() {
		setFlagLvlUpThisTick(true);
		lvl += 1;
		expNeedExp = deltaExp * ((double) lvl + (double) lvl * lvl / 20);
		heroStat.intp += getHeroStatPerLvlFinal().intp;
		heroStat.strp += getHeroStatPerLvlFinal().strp;
		heroStat.vitp += getHeroStatPerLvlFinal().vitp;
		heroStat.agip += getHeroStatPerLvlFinal().agip;
		heroStat.lckp += getHeroStatPerLvlFinal().lckp;


	}
	void addExp(double addExp) {
		exp += addExp;
		while (expNeedExp < exp) {
			lvlUp();
		}
	}
	
 public 	void removeItemFromHero(int idItem){
		for (int i = 0; i < itemArray.length; i++) {
			if (itemArray[i]==idItem){
				itemArray[i]=-1;
			}
		}
	}

	HeroStat getHeroStatPerLvlFinal() {
		HeroStat tmpherostat = new HeroStat(10, 10, 10, 10, 10);
		/*
		 * HeroStat playerModify = new HeroStat(0, 0, 0);
		 * 
		 * if (heroStatPlayerPref == 0) { playerModify = new HeroStat(1, 1, 1);
		 * } if (heroStatPlayerPref == 1) { playerModify = new HeroStat(5, 2,
		 * 1); } if (heroStatPlayerPref == 2) { playerModify = new HeroStat(1,
		 * 5, 2); } if (heroStatPlayerPref == 3) { playerModify = new
		 * HeroStat(2, 1, 5); }
		 * 
		 * tmpherostat.intp = statPointForLvlRatio.intp * playerModify.intp;
		 * tmpherostat.vitp = statPointForLvlRatio.vitp * playerModify.vitp;
		 * tmpherostat.strp = statPointForLvlRatio.strp * playerModify.strp;
		 * 
		 * double stabilze = tmpherostat.intp + tmpherostat.vitp +
		 * tmpherostat.strp;
		 * 
		 * tmpherostat.intp = tmpherostat.intp / stabilze * statPointForLvl;
		 * tmpherostat.vitp = tmpherostat.vitp / stabilze * statPointForLvl;
		 * tmpherostat.strp = tmpherostat.strp / stabilze * statPointForLvl;
		 */
		return tmpherostat;
	}



	void resetTick() {
		heroStat_bonus.strp = 0;
		heroStat_bonus.vitp = 0;
		heroStat_bonus.intp = 0;
		setFlagDieThisTick(false);
		setFlagLvlUpThisTick(false);
		setFlagIsSilenced(false);
	}

	void useAbilitis(){
	
		//HeroAbility
		if (isAliveInFight()){
		
			
			
		}
	}
	
	protected void updateElement() {
		expNeedExp = deltaExp * ((double) lvl + (double) lvl * lvl / 20);
		
		
		useAbilitis();
		
		for (Projectile projectile : projectileScope) {
			hpCurrent += projectile.getDmg();
			heroEffects.add(new HeroEffect(projectile.getDmg()));
		}
		projectileScope.removeAll(projectileScope);
		
		if (hpCurrent<=0) status=CREATURE_FIGHT_DEAD;
		
		
		
		List<HeroEffect> tmpHeroEffects = new ArrayList<HeroEffect>();
		  for (HeroEffect heroEffect : heroEffects) {
			
			heroEffect.updateElement();
			if (heroEffect.isFlagSelfDestroy()) tmpHeroEffects.add(heroEffect);
		}
		  heroEffects.removeAll(tmpHeroEffects);
		
	}

	void setStatus(int inStatus) {
		status = inStatus;
	}

	public void mouseClicked(MouseEvent arg0) {
		FAiF.gameScreen.heroInfo.setIdHeroToShow(idHero);
		
	}
	
	

	void reDrow(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(image, 0, 0, null);
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(2));

		g2.setFont(new Font("Arial", Font.BOLD, 16));
		
		  g2.drawString(Integer.toString((int) status), 15, 48);
		
		  g2.drawString(Integer.toString((int) lvl), 10, 15);
		  
		g2.setColor(new Color(210, 60, 60, 128));
		
		  
		g2.fillRect(0, (int) (105*(hpCurrent/hpMax)), 80,105-(int) (105*(hpCurrent/hpMax)));
		 
		List<HeroEffect> tmpHeroEffects = new ArrayList<HeroEffect>();
		  for (HeroEffect heroEffect : heroEffects) {
			heroEffect.reDrow(g);
			//heroEffect.updateElement();
			//if (heroEffect.isFlagSelfDestroy()) tmpHeroEffects.add(heroEffect);
		}
		  //heroEffects.removeAll(tmpHeroEffects);
		  
		  
		  
		  //double expNeedExp = deltaExp * ((double) lvl + (double) lvl * lvl /
		  g2.setColor(Color.red);
		  double expNeedExp = deltaExp * ((double) lvl + (double) lvl * lvl / 20);
			double expNeedExpPr = deltaExp
					* ((double) (lvl - 1) + (double) (lvl - 1) * (lvl - 1) / 20);
			g2.fillRect(3, 10, 3,
					(int) (90 * (exp - expNeedExpPr) / (expNeedExp - expNeedExpPr)));		 
	}

	public void paintComponent(Graphics g) {

		reDrow(g);
	}

	public void dragGestureRecognized(DragGestureEvent evt) {

		if (!flagIsThisEnemy) {

			status=CREATURE_OUT_OF_BATTLE;
				Transferable transferable = new StringSelection(
						Integer.toString(idHero));// !!!!!
				dragSource.startDrag(evt, DragSource.DefaultCopyDrop,
						transferable, this);
			
		}
	}

	void startFight(){
		hpCurrent=hpMax;
		projectileScope.removeAll(projectileScope);
		
		status=CREATURE_FIGHT_ALIVE;
	}
	
	void stopFight(){
		hpCurrent=hpMax;
		projectileScope.removeAll(projectileScope);
		
		status=CREATURE_OUT_OF_BATTLE;
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
		return HeroStat.summ(heroStat, heroStat_bonus);
	}

	public HeroStat getPureHeroStat() {
		return heroStat;
	}

	public void setHeroStat(HeroStat heroStat) {
		this.heroStat = heroStat;
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

	public void addProjectile(Projectile projectile) {
		projectileScope.add(projectile);
	}


}
