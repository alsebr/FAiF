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
	
}
