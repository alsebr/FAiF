package FAiF;

import HeroPackage.Hero_Ifreet;

public class GroupEnemy extends Group{
	@Override
	protected
	
		void init (){
			for (int i = 0; i < heroZonesArray.length; i++) {
				heroZonesArray[i] = new BattleZoneEnemy();
				add(heroZonesArray[i]);
			}		
			
			
		}
	
	public void addEnemys (int heroId1,int heroId2,int heroId3){
		Hero[] tmpHeroArray = new Hero[3];
		tmpHeroArray[0]=FAiF.gameScreen.heroStock.getHeroById(heroId1);
		tmpHeroArray[1]=FAiF.gameScreen.heroStock.getHeroById(heroId2);
		tmpHeroArray[2]=FAiF.gameScreen.heroStock.getHeroById(heroId3);
		
		
		for (int i = 0; i < tmpHeroArray.length; i++) {
			if(tmpHeroArray[i]!=null)tmpHeroArray[i].setZone(heroZonesArray[i].getZoneId());
		}
		
		
	}
}
