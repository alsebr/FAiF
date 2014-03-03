package ItemPackage;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;





import FAiF.FAiF;
import FAiF.HeroAbility;
import FAiF.Item;
import HeroAbilitisPackage.HeroAbility_AutoAtack;


public class Item_Weapon1 extends Item{
public Item_Weapon1(int grade) {

	super();
	
	//this.grade=grade;
	this.grade=5;
try {
	image = ImageIO.read(new File("data/image/item/item13.gif"));
} catch (IOException e) {
}

double tmpValue=0;
if (grade==0) tmpValue=30;
if (grade==1) tmpValue=70;
if (grade==2) tmpValue=120;
if (grade==3) tmpValue=180;

//setTtl(10);
String tmptext;
tmptext="<html>";
tmptext+="Пылающий меч. <br>Пассивно:<br> +"+tmpValue+" мощь при сражении в Бесконечной башне";
setToolTipText(tmptext);

setName("Пылающий меч");


HeroAbility tmpability;
tmpability=new HeroAbility_AutoAtack(10, 5, 1);
addAbilityToItem(tmpability);



}



}
