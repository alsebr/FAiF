package FAiF;

public class Projectile {
private double dmg;
private int fromId;
private int projectileType;
public Projectile(int fromId, double dmg, int projectileType) {
	this.dmg=dmg;
	this.fromId=fromId;
	this.setProjectileType(projectileType);
}
public double getDmg() {
	return dmg;
}
public void setDmg(double dmg) {
	this.dmg = dmg;
}
public int getFromId() {
	return fromId;
}
public void setFromId(int fromId) {
	this.fromId = fromId;
}
public int getProjectileType() {
	return projectileType;
}
public void setProjectileType(int projectileType) {
	this.projectileType = projectileType;
}
}
