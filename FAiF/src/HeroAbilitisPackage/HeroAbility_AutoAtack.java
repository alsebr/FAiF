package HeroAbilitisPackage;

import FAiF.FAiF;
import FAiF.HeroAbility;
import FAiF.Projectile;
import FAiF.ProjectileStock;
import FAiF.HeroStock;

public class HeroAbility_AutoAtack extends HeroAbility {

	

	public HeroAbility_AutoAtack(int heroId) {
		super();
		init(heroId, "Автоатака");
		
		flagIsChargedAbility=true;
		chargeMax=2;
		
	}

	@Override
	protected void useAbilityInUpdate() {

	}
	
	@Override
	public void useAbilityCA() {
		setAbilityValue1(4-FAiF.gameScreen.heroStock.getHeroById(heroId).getHeroStat().agip/10);
		setAbilityValue2(-1*FAiF.gameScreen.heroStock.getHeroById(heroId).getHeroStat().strp);
		chargeMax=getAbilityValue1();
		if (flagUseMaxChargeNow){
			FAiF.gameScreen.projectileStock.addProjectile(new Projectile(heroId,getAbilityValue2(),FAiF.gameScreen.PROJECTILE_FIRST));

		}
	}

	public String getAbilityTip() {
		return "";
	}

}
