package FAiF;

import java.io.ObjectInputStream.GetField;
import java.util.Random;

public class HeroAbility implements Comparable {
	private String name;
	protected int heroId;
	private double abilityValue1;
	private double abilityValue2;
	private double abilityValue3;
	private double abilityValue4;
	private int heroAbilityId;
	protected int itemId = 0;
	private boolean flagRemoveThisTick = false;
	private boolean flagThisIsTmpBossAbility = false;
	protected boolean flagUseMaxChargeNow = false;
	protected boolean flagIsChargedAbility=false;
	
	protected double chargeMax=0;
	protected double chargeCurrent=0;

	private int sequenceNumber = 20; // 20-standart 10- stat(first) 30-last

	
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	protected HeroAbility() {
		Random randomGenerator = new Random();
		setHeroAbilityId(randomGenerator.nextInt(32000));
	}

	protected void init(int heroId, String name) {
		this.heroId = heroId;
		this.setName(name);
	}

	public String getAbilityTip() {
		return getName();
	}

	protected void updateAbility() {

		
		useAbilityInUpdate();
	}

	
	protected void useAbilityInUpdate() {
		
	}
	
	public void chargeCheck(){
		chargeCurrent+=(double)1/60;
		if (chargeCurrent>=chargeMax){
			flagUseMaxChargeNow=true;
			chargeCurrent=0;
		}
	}
	
	public int getHeroId() {
		return heroId;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	public void useAbility() {
		flagUseMaxChargeNow=false;
		if (flagIsChargedAbility)chargeCheck();
		useAbilityCA();
	}

	public void useAbilityCA(){
		
	}
	public void useAbilityForHeroId(int id) {
	}

	public double getAbilityValue1() {
		return abilityValue1;
	}

	public void setAbilityValue1(double abilityValue1) {
		this.abilityValue1 = abilityValue1;
	}

	public double getAbilityValue2() {
		return abilityValue2;
	}

	public void setAbilityValue2(double abilityValue2) {
		this.abilityValue2 = abilityValue2;
	}

	public void selfDestroy() {
		setFlagRemoveThisTick(true);
	}

	public int getHeroAbilityId() {
		return heroAbilityId;
	}

	public void setHeroAbilityId(int heroAbilityId) {
		this.heroAbilityId = heroAbilityId;
	}

	public boolean isFlagRemoveThisTick() {
		return flagRemoveThisTick;
	}

	public void setFlagRemoveThisTick(boolean flagRemoveThisTick) {
		this.flagRemoveThisTick = flagRemoveThisTick;
	}

	public double getAbilityValue3() {
		return abilityValue3;
	}

	public void setAbilityValue3(double abilityValue3) {
		this.abilityValue3 = abilityValue3;
	}

	public double getAbilityValue4() {
		return abilityValue4;
	}

	public void setAbilityValue4(double abilityValue4) {
		this.abilityValue4 = abilityValue4;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumberDefault() {
		this.sequenceNumber = 20;
	}

	public void setSequenceNumberStatModify() {
		this.sequenceNumber = 10;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub

		if (sequenceNumber > ((HeroAbility) arg0).getSequenceNumber())
			return 1;
		if (sequenceNumber < ((HeroAbility) arg0).getSequenceNumber())
			return -1;
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void bossUse() {

	}

	public void dieIfNoItemWithId(int itemId) {

	}

	public boolean isFlagThisIsTmpBossAbility() {
		return flagThisIsTmpBossAbility;
	}

	public void setFlagThisIsTmpBossAbility(boolean flagThisIsTmpBossAbility) {
		this.flagThisIsTmpBossAbility = flagThisIsTmpBossAbility;
	}

}
