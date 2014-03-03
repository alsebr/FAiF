package HeroAbilitisPackage;

import static FAiF.Constant.*;

import java.util.Random;

import FAiF.FAiF;
import FAiF.Hero;
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
		Hero tmphero=FAiF.gameScreen.heroStock.getHeroById(heroId);
		setAbilityValue1((double)1/(double)(atackPerSecond*(1+(double)tmphero.getHeroStat().agip/100)));
		
		Random randomGenerator = new Random();
		double tmpdd = (double)(randomGenerator.nextInt((int)damageDelta*100))/100+1;
		
		setAbilityValue2(-1*(damage+tmpdd));
		chargeMax=getAbilityValue1();
		if (flagUseMaxChargeNow){
			Projectile tmpprojectile=new Projectile(heroId,getAbilityValue2(),PROJECTILE_FIRST,PROJECTILE_DAMAGE_TYPE_PHYSICAL);
			
			
			tmpprojectile= tmphero.modifyAtackProjectile(tmpprojectile);
					FAiF.gameScreen.projectileStock.addProjectile(tmpprojectile);

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
