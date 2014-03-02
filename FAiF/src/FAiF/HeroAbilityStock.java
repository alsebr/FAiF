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

	
	
	void updateElement() {

		List<HeroAbility> tmpScope = new ArrayList<HeroAbility>();

		
			allScope.addAll(allScopeTmp);
			allScopeTmp.removeAll(allScopeTmp);
		

			for (HeroAbility heroAbility : allScope) {
				if (FAiF.gameScreen.heroStock.getHeroById(heroAbility.getHeroId()).isUsesAbilityNow()){
					
				
				heroAbility.useAbility();
				}
			}
			
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
