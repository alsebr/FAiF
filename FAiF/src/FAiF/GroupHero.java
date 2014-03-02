package FAiF;

public class GroupHero extends Group{

	@Override
	protected
	
		void init (){
			for (int i = 0; i < heroZonesArray.length; i++) {
				heroZonesArray[i] = new BattleZoneHero();
				add(heroZonesArray[i]);
			}		
		}

	
	public void addExp(double exp){
		for (int i = 0; i < heroZonesArray.length; i++) {
			Hero tmHero=FAiF.gameScreen.heroStock.getHeroByZoneId(heroZonesArray[i]
					.getZoneId());
			if ( tmHero!= null) {
				tmHero.addExp(exp);
			}
		}
	}
	
}
