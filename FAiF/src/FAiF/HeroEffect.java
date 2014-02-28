package FAiF;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class HeroEffect {
double ttl;
double value;
private boolean flagSelfDestroy=false;
double x,y;


public HeroEffect( double value) {
this.ttl=1.5;
this.value=value;
Random random = new Random();

x=10+random.nextInt(40);
y=100-random.nextInt(80);
}

public void updateElement(){
	ttl-=(double)1/60;
	y-=(double)10/60;
	if (ttl<0){
		setFlagSelfDestroy(true);
	}
}

void reDrow(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Color.red);
	g2.setStroke(new BasicStroke(2));

	g2.setFont(new Font("Arial", Font.BOLD, 16));
	//g2.setColor(new Color(210, 60, 60, 128));
	
	  g2.drawString(Integer.toString((int) value), (int)x, (int)y);
}

public boolean isFlagSelfDestroy() {
	return flagSelfDestroy;
}

public void setFlagSelfDestroy(boolean flagSelfDestroy) {
	this.flagSelfDestroy = flagSelfDestroy;
}
}
