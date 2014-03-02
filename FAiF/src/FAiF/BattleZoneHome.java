package FAiF;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.text.ZoneView;


public class BattleZoneHome extends BattleZone{
BattleZoneHome(){
super();
zoneId=1;
setSize(500,130);
setLayout(new FlowLayout());
setPreferredSize(new Dimension(570, 120));
setBorder(BorderFactory.createLineBorder(Color.white));

}
}
