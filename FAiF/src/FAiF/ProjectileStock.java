package FAiF;

import java.util.ArrayList;
import java.util.List;

public class ProjectileStock {
	static List<Projectile> allScope = new ArrayList<Projectile>();

public void addProjectile (Projectile projectile){
	allScope.add(projectile);
}

public void updateElement(){
	for (Projectile projectile : allScope) {
		FAiF.gameScreen.locationScope.transportProjectileToGroup(projectile);
	
	}
	allScope.removeAll(allScope);
}

}
