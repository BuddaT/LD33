package net.buddat.ludumdare.ld33.render;

import net.buddat.ludumdare.ld33.Game;
import net.buddat.ludumdare.ld33.entity.head.DragonHead;
import net.buddat.ludumdare.ld33.entity.head.HeadPart;
import net.buddat.ludumdare.ld33.entity.head.HumanHead;
import net.buddat.ludumdare.ld33.util.Constants;
import net.buddat.ludumdare.ld33.util.FontUtils;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineZigzagEffect;

public class TitleScreen implements Screen {

	private final ScreenManager screenManager;

	private TextButton designButton;
	private TextButton fightButton;
	private TextButton quitButton;

	private HeadPart headPart, headPart2;

	private boolean cheatMoney = false;

	private Image bgImage;

	public TitleScreen() {
		screenManager = Game.getInstance().getScreenManager();
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		Game.getInstance().getGeneralBackground().draw(0, 0);

		bgImage.draw(0, 0);

		FontUtils.renderString(Constants.fontTitle, Constants.GAME_TITLE,
				Constants.GAME_WIDTH / 2, 50, Constants.GAME_WIDTH,
				FontUtils.ALIGN_CENTER, true, Color.white);

		designButton.render(gc, g);
		fightButton.render(gc, g);
		quitButton.render(gc, g);

		{
			g.translate(0, 100);
			headPart.render(gc, g, false);
			g.translate(0, -100);
		}

		{
			g.translate(400, 100);
			headPart2.render(gc, g, false);
			g.translate(-400, -100);
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		headPart.update(gc, delta);
		headPart2.update(gc, delta);

		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (designButton.intersects(gc.getInput().getMouseX(), gc
					.getInput().getMouseY())) {
				screenManager.setCurrentScreen(designButton.getResult());
			}

			if (fightButton.intersects(gc.getInput().getMouseX(), gc.getInput()
					.getMouseY())) {
				screenManager.setCurrentScreen(fightButton.getResult());
			}

			if (quitButton.intersects(gc.getInput().getMouseX(), gc.getInput()
					.getMouseY())) {
				System.exit(1);
			}
		}

		if (!cheatMoney) {
			if (gc.getInput().isKeyDown(Input.KEY_LCONTROL))
				if (gc.getInput().isKeyDown(Input.KEY_LALT))
					if (gc.getInput().isKeyDown(Input.KEY_M)) {
						cheatMoney = true;
						Game.getInstance().getPlayerEntity().addReward(10000);
					}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc) {
		try {
			bgImage = new Image("images/title_bg.png");

			Constants.fontTitle = new UnicodeFont(Constants.FONT_TITLE_NAME,
					54, true, false);
			Constants.fontTitle.addAsciiGlyphs();
			Constants.fontTitle.getEffects().add(
					new ColorEffect(java.awt.Color.WHITE));
			Constants.fontTitle.getEffects().add(
					new OutlineZigzagEffect(2, java.awt.Color.BLACK));
			Constants.fontTitle.loadGlyphs();

			Constants.fontMenu = new UnicodeFont(Constants.FONT_INFO_NAME, 54,
					true, false);
			Constants.fontMenu.addAsciiGlyphs();
			Constants.fontMenu.getEffects().add(
					new ColorEffect(java.awt.Color.WHITE));
			Constants.fontMenu.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}

		int xPos = Constants.GAME_WIDTH / 2
				- Constants.fontMenu.getWidth("Build-a-Monster") / 2;
		designButton = new TextButton(Constants.fontMenu, "Build-a-Monster",
				Constants.SCREEN_DESIGN, xPos, 320, Color.decode("#585045"),
				Color.decode("#a8a8a1"));

		xPos = Constants.GAME_WIDTH / 2 - Constants.fontMenu.getWidth("Fight!")
				/ 2;
		fightButton = new TextButton(Constants.fontMenu, "Fight!",
				Constants.SCREEN_BATTLE, xPos, 380, Color.decode("#585045"),
				Color.decode("#a8a8a1"));

		xPos = Constants.GAME_WIDTH / 2 - Constants.fontMenu.getWidth("Exit")
				/ 2;
		quitButton = new TextButton(Constants.fontMenu, "Exit",
				Constants.SCREEN_BATTLE, xPos, 440, Color.decode("#585045"),
				Color.decode("#a8a8a1"));

		headPart = new HumanHead("Title");
		headPart.init(gc);
		headPart2 = new DragonHead("Title");
		headPart2.init(gc);
	}

	class TextButton {

		private final int xStart, yStart, width, height;

		private final String text;

		private final Color normalColor, highlightColor;

		private final int result;
		private final UnicodeFont font;

		TextButton(UnicodeFont font, String text, int result, int x, int y,
				Color c, Color c1) {
			this.font = font;
			this.text = text;
			this.result = result;

			this.normalColor = c;
			this.highlightColor = c1;

			this.xStart = x;
			this.yStart = y;
			this.width = font.getWidth(text);
			this.height = font.getHeight(text);
		}

		int getResult() {
			return result;
		}

		void render(GameContainer gc, Graphics g) {
			font.drawString(xStart - 2, yStart + 2, text, Color.black);
			font.drawString(
					xStart,
					yStart,
					text,
					intersects(gc.getInput().getMouseX(), gc.getInput()
							.getMouseY()) ? highlightColor : normalColor);
		}

		boolean intersects(int xPos, int yPos) {
			if (xPos >= xStart && xPos <= xStart + width && yPos >= yStart
					&& yPos <= yStart + height)
				return true;

			return false;
		}
	}

}
