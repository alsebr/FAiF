


public class FAiF {

	//static MainMenu mainMenu;
	public static GameScreen gameScreen;
	FAiF() {
		
		gameScreen = new GameScreen();
		gameScreen.initiate();
	}
	public static void main(String[] args) {
		FAiF app = new FAiF();
		setFormActiveGameScreen();

	}
	static void setFormActiveGameScreen() {
		gameScreen.setVisible(true);
		

	}
}
