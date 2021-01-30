package Crosser;

public class resetCommand implements Command {

	ICrossingStrategy level=null;
	ICrossingController game=null;
	public resetCommand(ICrossingStrategy level) {
		this.level=level;
		this.game=new GameController(level);
	}
	@Override
	public void execute() {
		game.resetGame();
		
	}
	
	

}
