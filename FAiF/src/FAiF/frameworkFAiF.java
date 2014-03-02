package FAiF;

import java.util.Random;

public class frameworkFAiF {
public static boolean checkChance(double chance){
	Random randomGenerator = new Random();
	if(randomGenerator.nextInt(1000)<chance*1000) return true;
	return false;	
}
}
