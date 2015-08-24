package net.buddat.ludumdare.ld33.render;

import net.buddat.ludumdare.ld33.Game;
import net.buddat.ludumdare.ld33.entity.Entity;
import net.buddat.ludumdare.ld33.entity.MonsterEntity;
import net.buddat.ludumdare.ld33.entity.Part;
import net.buddat.ludumdare.ld33.entity.enemy.BasicEnemy;
import net.buddat.ludumdare.ld33.util.Constants;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.FontUtils;

public class BattleScreen implements Screen {

	private MonsterEntity playerEntity;

	private Entity currentEnemy;

	private Image floorGlow, background, winBackground;

	@Override
	public void render(GameContainer gc, Graphics g) {
		Game.getInstance().getGeneralBackground().draw(0, 0);

		floorGlow.draw(0, 400, Color.cyan);
		floorGlow.draw(400, 400, Color.orange);
		background.draw(0, 0);

		/*
		 * Render Monster Preview
		 */
		if (playerEntity != null) {
			playerEntity.render(gc, g, true);

			int percentFull = (int) (100.0f / playerEntity.getMaxHealth() * playerEntity
					.getHealth());
			int width = percentFull * 3;

			g.setColor(Color.decode("#5a4719"));
			g.fillRect(50, 10, 304, 24);
			g.setColor(Color.white);
			g.fillRect(48, 8, 304, 24);
			g.setColor(Color.black);
			g.fillRect(50, 10, 300, 20);
			g.setColor(Color.decode("#1ca929"));
			g.fillRect(50, 10, width, 20);
		}

		if (currentEnemy != null) {
			currentEnemy.render(gc, g);

			int percentFull = (int) (100.0f / currentEnemy.getMaxHealth() * currentEnemy
					.getHealth());
			int width = percentFull * 3;

			g.setColor(Color.decode("#5a4719"));
			g.fillRect(450, 10, 304, 24);
			g.setColor(Color.white);
			g.fillRect(448, 8, 304, 24);
			g.setColor(Color.black);
			g.fillRect(450, 10, 300, 20);
			g.setColor(Color.decode("#c91c1c"));
			g.fillRect(450, 10, width, 20);

			if (currentEnemy.getHealth() <= 0) {
				winBackground.draw(200, 380);
				FontUtils.drawCenter(Constants.fontMsg,
						"You killed the Adventurer!", 200, 420, 400,
						Color.decode("#585045"));
				FontUtils.drawCenter(Constants.fontMsg,
						currentEnemy.getMaxHealth() / 10
								+ " bones added to your collection.", 200, 450,
						400, Color.decode("#585045"));
			}

			if (playerEntity.getHealth() <= 0) {
				winBackground.draw(200, 380);
				FontUtils.drawCenter(Constants.fontMsg, "You were slain!", 200,
						420, 400, Color.decode("#585045"));
				FontUtils.drawCenter(Constants.fontMsg,
						"Some bones were stolen from you.", 200, 450, 400,
						Color.decode("#585045"));
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if (playerEntity == null) {
			playerEntity = Game.getInstance().getPlayerEntity();
		}

		if (currentEnemy == null) {
			currentEnemy = new BasicEnemy();
			currentEnemy.init(gc);
		}

		if (currentEnemy != null) {
			currentEnemy.update(gc, delta);

			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (playerEntity.getHealth() <= 0) {
					playerEntity.spendCurrency(100);
					playerEntity.refreshCooldowns();

					Game.getInstance().getScreenManager()
							.setCurrentScreen(Constants.SCREEN_DESIGN);
					currentEnemy = null;
				} else if (currentEnemy.getHealth() <= 0) {
					playerEntity.addReward(currentEnemy.getMaxHealth() / 10);
					playerEntity.refreshCooldowns();
					Game.getInstance().getScreenManager()
							.setCurrentScreen(Constants.SCREEN_DESIGN);

					currentEnemy = null;
				} else {
					Part clickedPart = playerEntity.getHighlightedPart();
					if (clickedPart != null) {
						if (clickedPart.getPartStats().canSwing())
							clickedPart.action(playerEntity, currentEnemy,
									Entity.PART_BODY);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc) {
		try {
			floorGlow = new Image("images/design_baseGlow.png");
			background = new Image("images/battle_bg.png");
			winBackground = new Image("images/battle_winbg.png");

			Constants.fontMsg = new UnicodeFont(Constants.FONT_INFO_NAME, 28,
					true, false);
			Constants.fontMsg.addAsciiGlyphs();
			Constants.fontMsg.getEffects().add(
					new ColorEffect(java.awt.Color.WHITE));
			Constants.fontMsg.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
