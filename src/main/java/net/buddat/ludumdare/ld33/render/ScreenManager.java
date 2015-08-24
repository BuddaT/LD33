package net.buddat.ludumdare.ld33.render;

import java.util.HashMap;

import net.buddat.ludumdare.ld33.util.Constants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ScreenManager {

	private final HashMap<Integer, Screen> screenMap;

	private int currentScreen = Constants.SCREEN_TITLE;

	public ScreenManager() {
		screenMap = new HashMap<Integer, Screen>();
	}

	public void render(GameContainer gc, Graphics g) {
		render(gc, g, currentScreen);
	}

	public void render(GameContainer gc, Graphics g, int screenIndex) {
		getScreen(screenIndex).render(gc, g);
	}

	public void update(GameContainer gc, int delta, boolean allScreens) {
		if (allScreens) {
			for (Screen s : screenMap.values())
				s.update(gc, delta);
		} else {
			getCurrentScreen().update(gc, delta);
		}
	}

	public void init(GameContainer gc) {
		TitleScreen tScreen = new TitleScreen();
		tScreen.init(gc);

		DesignScreen dScreen = new DesignScreen();
		dScreen.init(gc);

		BattleScreen bScreen = new BattleScreen();
		bScreen.init(gc);

		addScreen(tScreen, Constants.SCREEN_TITLE);
		addScreen(dScreen, Constants.SCREEN_DESIGN);
		addScreen(bScreen, Constants.SCREEN_BATTLE);
	}

	public void addScreen(Screen s, int idx) {
		screenMap.put(idx, s);
	}

	public Screen getScreen(int idx) {
		return screenMap.get(idx);
	}

	public void setCurrentScreen(int newScreen) {
		currentScreen = newScreen;
	}

	public Screen getCurrentScreen() {
		return screenMap.get(currentScreen);
	}

	public int getCurrentScreenIndex() {
		return currentScreen;
	}
}
