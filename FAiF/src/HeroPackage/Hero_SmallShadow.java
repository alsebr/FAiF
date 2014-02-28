package HeroPackage;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FAiF.FAiF;
import FAiF.Hero;
import FAiF.HeroStat;
import HeroAbilitisPackage.HeroAbility_AutoAtack;






public class Hero_SmallShadow extends Hero {
	public Hero_SmallShadow() {
		super();
				
		String name="Малая тень";
		double costGold=26;
		double costSoul=4;
		double costTear=0;
		double deltaExp=18;
		double strp=21;
		double vitp=90;
		double intp=31;
		double agip=32;
		double lckp=31;
		double statPointPerLvl=17;
		double strToPowerRatio=1;
		double vitToTTLRatio=2;		
		String htmlTextHeroTip = "Сердце огня.";
		htmlTextHeroTip += "<br> Любой ваш огненный предмет увеличивает мощь Ифрита на <b>Int</b>";
		Image image=null;
		try {
			image = ImageIO.read(new File("data/image/hero/demon4.gif"));
		} catch (IOException e) {
		}
		
		HeroStat heroStatRatio=new HeroStat(0.33, 0.34, 0.33,0.1,0.1); // summ ==1
		HeroStat heroStat=new HeroStat(strp, vitp, intp,agip,lckp);	
		//public void init(String name,  Image inImage, double inDeltaExp, double statPointPerLvl, HeroStat heroStat, HeroStat heroStatRatio,double strToPowerRatio,double vitToTTLRatio) {
		init(name, image, deltaExp, statPointPerLvl,heroStat,heroStatRatio,htmlTextHeroTip);
		
		//LHoH.gameScreen.heroAbilityStock.useAllAbilityByHero(heroId);
		//LHoH.gameScreen.heroAbilityStock.addAbility(new HeroAbility_Ifreet(getId()));
		
	}


	
	public void addHeroAbilities(){
		FAiF.gameScreen.heroAbilityStock.addAbility(new HeroAbility_AutoAtack(getId()));
	}



}
