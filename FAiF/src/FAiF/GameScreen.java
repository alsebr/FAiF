package FAiF;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;



public class GameScreen extends JFrame {






HeroManagmentPanel heroManagmentPanel;
BattlePanel battlePanel;
ScreenChoizer screenChoizer;
LocationScope locationScope;

	Timer timer = new Timer();
	JPanel actionPart = new JPanel();
	public HeroStock heroStock;
	public HeroAbilityStock heroAbilityStock;
	public ProjectileStock projectileStock;
	public ItemStock itemStock;
	public HeroInfo heroInfo;
	//JButton optionsButton = new JButton();
	//public HeroStock heroStock = new HeroStock();
	//JScrollPane heroStockScroll;
	
	//public HeroAbilityStock heroAbilityStock=new HeroAbilityStock();
/*
	public LocationScope locationScope;
	
	
	public BottomInfo bottomInfo = new BottomInfo();
	LocationPanel locationPanel;
	BossPanel bossPanel;
	HeroPanel heroPanel;
	ItemPanel itemPanel;
	public TowerPanel towerPanel;
	JPanel actionPart = new JPanel();
	public Player player = new Player();
	Taverna taverna;
	HeroViewScreen heroViewScreen;
*/

	GameScreen() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(804, 600);
		setLayout(null);
		

	}
	
	void initiate() {

		ToolTipManager.sharedInstance().setInitialDelay(0);
		
		//heroViewScreen=new HeroViewScreen();
		heroAbilityStock=new HeroAbilityStock();
		locationScope=new LocationScope();
		projectileStock=new ProjectileStock();
		
		heroStock=new HeroStock();
		
		battlePanel=new BattlePanel();
		screenChoizer = new ScreenChoizer();
		
		heroInfo=new HeroInfo();
		itemStock = new ItemStock();
		heroManagmentPanel=new HeroManagmentPanel();
		
		locationScope.initiate();
		setResizable(false);
		
		
		screenChoizer.setBounds(0, 0, screenChoizer.getWidth(),
				screenChoizer.getHeight());
		add(screenChoizer);
		
		actionPart.setBounds(100, 0, 700, 600);
		actionPart.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		actionPart.setLayout(null);
		add(actionPart);
		activateBattlePanel();
		//locationScope = new LocationScope();

		//itemStock = new ItemStock();
		//itemPanel = new ItemPanel();

//		createHeroStockScroll();

		//towerPanel=new TowerPanel();
		//bossPanel = new BossPanel();
		//locationPanel = new LocationPanel();

		//taverna = new Taverna();
		//heroPanel = new HeroPanel();
/*
		screenChoizer.setBounds(0, 0, screenChoizer.getWidth(),
				screenChoizer.getHeight());
		add(screenChoizer);

		bottomInfo.setBounds(0, 450, bottomInfo.getWidth(),
				bottomInfo.getHeight());
		add(bottomInfo);

		actionPart.setBounds(100, 0, 700, 600);
		actionPart.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		actionPart.setLayout(null);
		add(actionPart);
		activateHeroPanel();
		activateLocationPanel();
*/
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

				gameTick();

			}
		}, 17, 17);

		
		

	}

	void 	gameTick(){
		
		heroAbilityStock.updateElement();
		projectileStock.updateElement();
		locationScope.updateElement();
		itemStock.updateElement();
		heroStock.updateElement();
		heroInfo.updateElement();
		repaint();
	}
	
	
	void activateHeroManagmentPanel() {
		actionPart.removeAll();
		//locationPanel.addComp();
		actionPart.add(heroManagmentPanel);
	}
	
	void activateBattlePanel() {
		actionPart.removeAll();
		//locationPanel.addComp();
		actionPart.add(battlePanel);
	}
	
/*
	void activateHeroPanel() {
		actionPart.removeAll();
		heroPanel.addComp();
		actionPart.add(heroPanel);
	}

	void activateTownPanel() {
		actionPart.removeAll();
		// heroPanel.addComp();
		actionPart.add(townPanel);
	}

	void activateItemPanel() {
		actionPart.removeAll();
		// heroPanel.addComp();
		actionPart.add(itemPanel);
	}

	void activateLocationPanel() {
		actionPart.removeAll();
		locationPanel.addComp();
		actionPart.add(locationPanel);
	}

	void activateBossPanel() {
		actionPart.removeAll();
		bossPanel.addComp();
		actionPart.add(bossPanel);
	}
	
	void activateTowerPanel() {
		actionPart.removeAll();
		towerPanel.addComp();
		actionPart.add(towerPanel);
	}

	private void gameTick() {
		System.out.println("---Tick");
		update();
		reDrow();

	}

	void update() {
		heroStock.update();
		locationScope.update();
		player.update();
		bossPanel.update();
		taverna.update();
		locationPanel.update();
		itemStock.update();
		towerPanel.update();

		heroAbilityStock.update();
		heroPanel.update();
		
		heroViewScreen.update();
	}

	void reDrow() {
		repaint();
	}

	
	void createHeroStockScroll (){
		heroStockScroll = new JScrollPane(LHoH.gameScreen.heroStock);
		heroStockScroll.setAutoscrolls(true);
		heroStockScroll.setPreferredSize(new Dimension(260, 370));
		heroStockScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		heroStockScroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		heroStockScroll.setOpaque(false);
		heroStockScroll.setBorder(null);
	}
	
	void initiate() {

		heroViewScreen=new HeroViewScreen();

		
		setResizable(false);
		locationScope = new LocationScope();

		itemStock = new ItemStock();
		itemPanel = new ItemPanel();

		createHeroStockScroll();

		towerPanel=new TowerPanel();
		bossPanel = new BossPanel();
		locationPanel = new LocationPanel();

		taverna = new Taverna();
		heroPanel = new HeroPanel();

		screenChoizer.setBounds(0, 0, screenChoizer.getWidth(),
				screenChoizer.getHeight());
		add(screenChoizer);

		bottomInfo.setBounds(0, 450, bottomInfo.getWidth(),
				bottomInfo.getHeight());
		add(bottomInfo);

		actionPart.setBounds(100, 0, 700, 600);
		actionPart.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		actionPart.setLayout(null);
		add(actionPart);
		activateHeroPanel();
		activateLocationPanel();

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

				gameTick();

			}
		}, 17, 17);

		LHoH.gameScreen.bottomInfo.chat.addTextChat("Великая битва началась");

	}
	*/
}
