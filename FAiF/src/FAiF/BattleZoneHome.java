package FAiF;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.text.ZoneView;


public class BattleZoneHome extends BattleZone{
BattleZoneHome(){
super();
zoneId=1;
setSize(300,200);
setLayout(new FlowLayout());
setPreferredSize(new Dimension(300, 200));

}
}
