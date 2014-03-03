package HeroAbilitisPackage;

import java.util.Random;

import FAiF.FAiF;
import FAiF.HeroAbility;
import FAiF.Projectile;
import FAiF.ProjectileStock;
import FAiF.HeroStock;

public class HeroAbility_AutoAtack extends HeroAbility {

	private double damage;
	private double damageDelta;
	private double atackPerSecond;

	public HeroAbility_AutoAtack(double damage,double damageDelta,double atackPerSecond ) {
		super();
		init(-1, "Автоатака");
		
		
		this.damage=damage;
		this.damageDelta=damageDelta;
		this.atackPerSecond=atackPerSecond;
	
		
		setFlagIsActiveAbility(true);
	}

	@Override
	protected void useAbilityInUpdate() {

	}
	
	@Override
	public void useAbilityCA(int heroId) {
		setAbilityValue1(1/atackPerSecond);
		
		Random randomGenerator = new Random();
		int tmpdd = randomGenerator.nextInt((int)damageDelta)+1;
		
		setAbilityValue2(-1*(damage+tmpdd));
		chargeMax=getAbilityValue1();
		if (flagUseMaxChargeNow){
			FAiF.gameScreen.projectileStock.addProjectile(new Projectile(heroId,getAbilityValue2(),FAiF.gameScreen.PROJECTILE_FIRST));

		}
	}

	public String getAbilityTip() {
		String htmltext;
		htmltext=getName();
		double maxd=damage+damageDelta;
		htmltext+=": Физический урон "+damage+"-"+maxd+"; атак в секунду "+atackPerSecond;
		
		return htmltext;
	}

}
