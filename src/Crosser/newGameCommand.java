package Crosser;

public class newGameCommand implements Command {

	ICrossingStrategy level=null;
	GameController game=null;
	public newGameCommand(ICrossingStrategy level,GameController game) {
		this.level=level;
		this.game=game;
	}
	public void execute() {
		
		game.newGame(level);
	}

	

}
