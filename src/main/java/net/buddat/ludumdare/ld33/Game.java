package net.buddat.ludumdare.ld33;

import net.buddat.ludumdare.ld33.util.Constants;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {

	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

	}

	@Override
	public void init(GameContainer gc) throws SlickException {

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {

	}

	public static void main(String[] args) {
		try {
			final AppGameContainer gameContainer;
			gameContainer = new AppGameContainer(new Game(Constants.GAME_TITLE));
			gameContainer.setDisplayMode(Constants.GAME_WIDTH,
					Constants.GAME_HEIGHT, Constants.FULLSCREEN);
			gameContainer.setShowFPS(Constants.DEV_SHOW_FPS);
			gameContainer.setTargetFrameRate(Constants.TARGET_FPS);
			gameContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void reset(GameContainer gc) throws SlickException {

	}
}