package FAiF;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeroAbilityStock {
	//private List<HeroAbility> allScope = new ArrayList<HeroAbility>();
	private PriorityQueue<HeroAbility> allScope = new PriorityQueue<HeroAbility>();
	private PriorityQueue<HeroAbility> allScopeTmp = new PriorityQueue<HeroAbility>();
	
	HeroAbilityStock() {

	}

	
	void useAbilityForHeroId(int heroid){
		List<HeroAbility> tmpScope =getAllHeroAbilityByHeroId(heroid);
		Hero tmphero=FAiF.gameScreen.heroStock.getHeroById(heroid);
		for (HeroAbility heroAbility : tmpScope) {
			if (heroAbility.isFlagIsActiveAbility()){
				if (tmphero.isAliveInFight()){
					heroAbility.useAbility();
					heroAbility.useAbilityCA(heroid);	
				}
			}else{
				heroAbility.useAbilityCA(heroid);	
			}
			
		}
	}
	
	void useAbilitisForAllHero(){
		for (Hero hero : FAiF.gameScreen.heroStock.allScope) {
			useAbilityForHeroId(hero.getIdHero());
		}
	}
	
	
	
	void updateElement() {

		
		
		List<HeroAbility> tmpScope = new ArrayList<HeroAbility>();

		
		
			allScope.addAll(allScopeTmp);
			allScopeTmp.removeAll(allScopeTmp);
		

			for (HeroAbility heroAbility : allScope) {
			//	if (FAiF.gameScreen.heroStock.getHeroById(heroAbility.getHeroId()).isUsesAbilityNow()){
					
				
				//heroAbility.useAbility();
				}
			
			useAbilitisForAllHero();
			
	/*	
		for (HeroAbility heroAbility : allScope) {
			if (heroAbility.isFlagRemoveThisTick()) {
				tmpScope.add(heroAbility);
			}
		}
		
		for (HeroAbility heroAbility : tmpScope) {
				allScope.remove(heroAbility);
		}

		for (HeroAbility heroAbility : allScope) {
			heroAbility.updateAbility();

		}
		*/
	}

	public void addAbility(HeroAbility heroAbility) {
		//private List<HeroAbility> allScope = new ArrayList<HeroAbility>();
		
		
		
		allScopeTmp.add(heroAbility);
	}

	public HeroAbility getHeroAbilityByItemId(int itemId) {

		for (HeroAbility heroAbility : allScope) {
			if (heroAbility.getItemId() == itemId)
				return heroAbility;
		}
		return null;
	}

	
	public List<HeroAbility> getAllHeroAbilityByHeroId(int heroId){
		List<HeroAbility> tmpScope = new ArrayList<HeroAbility>();
		
		Hero tmphero=FAiF.gameScreen.heroStock.getHeroById(heroId);
		for (int itemId : tmphero.itemArray) {
			tmpScope.addAll( getAllHeroAbilityByItemId(itemId));
		}
		
		return tmpScope;
	}
	
	public List<HeroAbility> getAllHeroAbilityByItemId(int itemId) {
		List<HeroAbility> tmpScope = new ArrayList<HeroAbility>();
		for (HeroAbility heroAbility : allScope) {
			if (heroAbility.getItemId() == itemId)
				tmpScope.add(heroAbility);
		}
		return tmpScope;
	}
	
	public String getAllTipHeroAbilityByItemId(int itemId) {
		List<HeroAbility> tmpScope = new ArrayList<HeroAbility>();
		for (HeroAbility heroAbility : allScope) {
			if (heroAbility.getItemId() == itemId)
				tmpScope.add(heroAbility);
		}
		
		String tmptext="";
		for (HeroAbility heroAbility : tmpScope) {
			tmptext+=heroAbility.getAbilityTip()+"<br>";
		}
		return tmptext;
	}
	
	
	String getAllAbilityTipByHero(int heroId) {
		String tmptText = "";
		
		
		for (HeroAbility heroAbility : allScope) {
			if ((heroAbility.heroId == heroId)||(heroAbility.heroId == -1))
				tmptText += heroAbility.getAbilityTip() + "<br>";
		}



		return tmptText;
	}

	void useAllAbilityByHero(int heroId) {
		for (HeroAbility heroAbility : allScope) {
			if (heroAbility.heroId == heroId)
				heroAbility.useAbility();
			if (heroAbility.heroId == -1)
				heroAbility.useAbilityForHeroId(heroId);
		}

		}

		void useAllAbilityBoss() {
			for (HeroAbility heroAbility : allScope) {
				if (heroAbility.heroId == -2)
					heroAbility.bossUse();

			}
		

	}

}
