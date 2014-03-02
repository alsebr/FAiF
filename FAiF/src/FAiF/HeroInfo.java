package FAiF;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class HeroInfo extends JPanel{
	private int idHeroToShow=-1;
	ItemPlace[] itemPlaceArray = new ItemPlace[3];
	
	
	JLabel heroHtmlInfo;
	public HeroInfo() {
		setSize(300,300);
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(300, 300));
		setBorder(BorderFactory.createLineBorder(Color.red));
		
		heroHtmlInfo= new JLabel();
		heroHtmlInfo.setBorder(BorderFactory.createLineBorder(Color.red));
		heroHtmlInfo.setSize(300,200);
		
		heroHtmlInfo.setPreferredSize(new Dimension(300, 200));
		add(heroHtmlInfo);
		
		for (int i = 0; i < itemPlaceArray.length; i++) {
			itemPlaceArray[i]=new ItemPlace();
			itemPlaceArray[i].init(i);
			add(itemPlaceArray[i]);
		}
		
	}
	public int getIdHeroToShow() {
		return idHeroToShow;
	}
	public void setIdHeroToShow(int idHeroToShow) {
		this.idHeroToShow = idHeroToShow;
	}
	
	public void updateElement(){
		String htmltext="";
		htmltext="<html";
		Hero tmpHero=null;
		tmpHero=FAiF.gameScreen.heroStock.getHeroById(idHeroToShow);
				if (tmpHero!=null){
					HeroStat heroPureStat=tmpHero.getPureHeroStat();
					HeroStat heroBonusStat=tmpHero.getPureHeroStat();
					htmltext = "<html>";
					htmltext += "<br>Lvl: " + tmpHero.lvl;
					htmltext += "<br>Str: " + String.format("%.3g%n",heroPureStat.strp)+"+"+String.format("%.3g%n",heroBonusStat.strp)+" ("+String.format("%.2g%n", tmpHero.getHeroStatPerLvlFinal().strp)+" fLvl)";
					htmltext += "<br>Vit: " + String.format("%.3g%n",heroPureStat.vitp)+"+"+String.format("%.3g%n",heroBonusStat.vitp)+" ("+String.format("%.2g%n", tmpHero.getHeroStatPerLvlFinal().vitp)+" fLvl)";
					htmltext += "<br>Int: " + String.format("%.3g%n",heroPureStat.intp)+"+"+String.format("%.3g%n",heroBonusStat.intp)+" ("+String.format("%.2g%n", tmpHero.getHeroStatPerLvlFinal().intp)+" fLvl)";
					htmltext += "<br>Stat for Lvl: " + String.format("%.3g%n",tmpHero.getStatPointForLvl());
					htmltext += "<br>";
					
					htmltext += "<br>Exp: " + (int) tmpHero.exp + "/"
							+ (int) tmpHero.expNeedExp;
					htmltext += "<br>";
					htmltext += "<br>ExpLvl: " + tmpHero.deltaExp;	
				}
				heroHtmlInfo.setText(htmltext);
	}
	
}
