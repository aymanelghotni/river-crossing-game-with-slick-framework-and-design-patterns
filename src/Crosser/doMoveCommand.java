package Crosser;

public class doMoveCommand implements Command {

	GameController game=null;
	GameController currentGame=null;
	ICrossingStrategy level=null;
	public doMoveCommand(ICrossingStrategy level, GameController game) {
		this.level=level;
		this.game=game;
		this.currentGame=game;
	}
	public void execute() {
		game.doMove(game.getBoatCrossers(), game.isBoatOnTheLeftBank());
	}
	public GameController undo()
	{
		this.game=currentGame;
		game.undo();
		return game;
	}
}
