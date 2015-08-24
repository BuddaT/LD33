package net.buddat.ludumdare.ld33;

import net.buddat.ludumdare.ld33.entity.Entity;
import net.buddat.ludumdare.ld33.entity.MonsterEntity;
import net.buddat.ludumdare.ld33.render.ScreenManager;
import net.buddat.ludumdare.ld33.util.Constants;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {

	public static Game instance = null;

	private ScreenManager screenMan;

	private MonsterEntity playerEntity;

	private Image genBackground;

	public Game(String title) {
		super(title);
	}

	public ScreenManager getScreenManager() {
		return screenMan;
	}

	public Image getGeneralBackground() {
		return genBackground;
	}

	public MonsterEntity getPlayerEntity() {
		return playerEntity;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		screenMan.render(gc, g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Entity.initParts(gc);

		genBackground = new Image("images/general_bg.png");

		screenMan = new ScreenManager();
		screenMan.init(gc);

		playerEntity = new MonsterEntity();
		playerEntity.init(gc);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		screenMan.update(gc, delta, false);
		playerEntity.update(gc, delta);
	}

	public void reset(GameContainer gc) throws SlickException {

	}

	public static void main(String[] args) {
		try {
			final AppGameContainer gameContainer;
			gameContainer = new AppGameContainer(Game.getInstance());
			gameContainer.setDisplayMode(Constants.GAME_WIDTH,
					Constants.GAME_HEIGHT, Constants.FULLSCREEN);
			gameContainer.setShowFPS(Constants.DEV_SHOW_FPS);
			gameContainer.setTargetFrameRate(Constants.TARGET_FPS);
			gameContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static Game getInstance() {
		if (instance == null)
			instance = new Game(Constants.GAME_TITLE);

		return instance;
	}

}