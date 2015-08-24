package net.buddat.ludumdare.ld33.render;

import net.buddat.ludumdare.ld33.Game;
import net.buddat.ludumdare.ld33.entity.Entity;
import net.buddat.ludumdare.ld33.entity.MonsterEntity;
import net.buddat.ludumdare.ld33.entity.Part;
import net.buddat.ludumdare.ld33.entity.PartStats;
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

public class DesignScreen implements Screen {

	private static final int X_START = 20, Y_START = 40, X_GAP = 84,
			WID_HEI = 64;

	private Image baseGlow;
	private Image uiBackground, tooltipBackground;
	private Image btnBack, btnBackHover;

	private MonsterEntity playerEntity;
	private final ScreenManager screenManager;

	private int mouseX, mouseY;
	private int clickX, clickY;

	private Part hoveredPart = null;
	private int hoveredType = -1;

	public DesignScreen() {
		screenManager = Game.getInstance().getScreenManager();
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		Game.getInstance().getGeneralBackground().draw(0, 0);

		baseGlow.draw(400, 400);
		uiBackground.draw(0, 0);

		if (mouseX >= 750 && mouseX <= 782 && mouseY >= 10 && mouseY <= 42) {
			btnBackHover.draw(750, 10, 32, 32);
		} else {
			btnBack.draw(750, 10, 32, 32);
		}

		hoveredPart = null;
		hoveredType = -1;

		renderCategory(g, "Head", Entity.PART_HEAD, 0);
		renderCategory(g, "Body", Entity.PART_BODY, 110);
		renderCategory(g, "Feet", Entity.PART_FEET, 220);
		renderCategory(g, "Left Hand", Entity.PART_LARM, 330);
		renderCategory(g, "Right Hand", Entity.PART_RARM, 440);

		if (playerEntity != null) {
			FontUtils.renderString(Constants.fontDesign, "Bones: "
					+ playerEntity.getCurrency(), 360, 560, 200,
					FontUtils.ALIGN_RIGHT, true, Color.decode("#585045"));
		}

		if (hoveredPart != null && playerEntity != null) {
			int textY = mouseY + 2;
			tooltipBackground.draw(mouseX, mouseY, 100, 120);

			String name = hoveredPart.getName()
					+ (hoveredPart.isUnlocked() ? "" : ":"
							+ hoveredPart.getCost());
			FontUtils.renderString(
					Constants.fontInfo,
					name,
					mouseX + 95,
					textY,
					100,
					FontUtils.ALIGN_RIGHT,
					false,
					hoveredPart.isUnlocked() ? Color.decode("#333333") : Color
							.decode("#aa9a13"));
			textY += Constants.fontInfo.getHeight("A");

			PartStats currentStats = playerEntity.getPart(hoveredType)
					.getPartStats();

			name = "health: "
					+ hoveredPart.getPartStats().getHealth()
					+ (hoveredPart.getPartStats().getHealth() < hoveredPart
							.getPartStats().getMaxHealth() ? ":20" : "");
			FontUtils.renderString(
					Constants.fontInfo,
					name,
					mouseX + 95,
					textY,
					100,
					FontUtils.ALIGN_RIGHT,
					false,
					hoveredPart.getPartStats().getHealth() < hoveredPart
							.getPartStats().getMaxHealth() ? Color
							.decode("#aa9a13") : Color.decode("#333333"));

			textY += Constants.fontInfo.getHeight("A");
			renderCompString("defense: ", currentStats.getDefense(),
					hoveredPart.getPartStats().getDefense(), textY);

			textY += Constants.fontInfo.getHeight("A");
			renderCompString("damage: ", currentStats.getAttack(), hoveredPart
					.getPartStats().getAttack(), textY);

			textY += Constants.fontInfo.getHeight("A");
			renderCompString("healing: ", currentStats.getHealing(),
					hoveredPart.getPartStats().getHealing(), textY);

			textY += Constants.fontInfo.getHeight("A");
			renderCompString("atk speed: ", currentStats.getSpeed(),
					hoveredPart.getPartStats().getSpeed(), textY);

			textY += Constants.fontInfo.getHeight("A");
			FontUtils.renderString(
					Constants.fontInfo,
					"type: "
							+ PartStats.getTypeString(hoveredPart
									.getPartStats().getType()), mouseX + 95,
					textY, 100, FontUtils.ALIGN_RIGHT, false,
					Color.decode("#333333"));
		}

		/*
		 * Render Monster Preview
		 */
		if (playerEntity != null) {
			g.translate(400, 0);
			playerEntity.render(gc, g);
			g.translate(-400, 0);
		}

		clickX = -1;
		clickY = -1;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if (playerEntity == null)
			playerEntity = Game.getInstance().getPlayerEntity();

		mouseX = gc.getInput().getMouseX();
		mouseY = gc.getInput().getMouseY();

		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			clickX = gc.getInput().getMouseX();
			clickY = gc.getInput().getMouseY();

			if (clickX >= 750 && clickX <= 782 && clickY >= 10 && clickY <= 42) {
				screenManager.setCurrentScreen(Constants.SCREEN_BATTLE);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc) {
		try {
			baseGlow = new Image("images/design_baseGlow.png");
			uiBackground = new Image("images/design_uibg.png");
			tooltipBackground = new Image("images/design_tooltipbg.png");
			btnBack = new Image("images/btn_back.png");
			btnBackHover = new Image("images/btn_back_h.png");

			Constants.fontDesign = new UnicodeFont(Constants.FONT_TITLE_NAME,
					20, false, false);
			Constants.fontDesign.addAsciiGlyphs();
			Constants.fontDesign.getEffects().add(
					new ColorEffect(java.awt.Color.WHITE));
			Constants.fontDesign.loadGlyphs();

			Constants.fontInfo = new UnicodeFont(Constants.FONT_INFO_NAME, 18,
					true, false);
			Constants.fontInfo.addAsciiGlyphs();
			Constants.fontInfo.getEffects().add(
					new ColorEffect(java.awt.Color.WHITE));
			Constants.fontInfo.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private void renderShadowString(String s, int x, int y) {
		FontUtils.renderString(Constants.fontDesign, s, x - 1, y + 1, -1,
				FontUtils.ALIGN_LEFT, false, Color.black);
		FontUtils.renderString(Constants.fontDesign, s, x, y, -1,
				FontUtils.ALIGN_LEFT, false, Color.decode("#585045"));
	}

	private void renderCompString(String s, int current, int hovered, int yPos) {
		FontUtils.renderString(
				Constants.fontInfo,
				s + hovered,
				mouseX + 95,
				yPos,
				100,
				FontUtils.ALIGN_RIGHT,
				false,
				hovered > current ? Color.decode("#1ca929")
						: hovered < current ? Color.decode("#c91c1c") : Color
								.decode("#333333"));
	}

	private void renderCategory(Graphics g, String category, int partType,
			int yOff) {
		int xPos = X_START;
		int yPos = Y_START + yOff;

		renderShadowString(category, xPos, yPos - 30);

		for (Part p : Entity.getPartList(partType)) {
			if (playerEntity != null && playerEntity.getPart(partType) == p) {
				g.setColor(Color.green);
				g.fillRect(xPos - 2, yPos - 2, WID_HEI + 4, WID_HEI + 4);
			}

			if (partType != Entity.PART_LARM && partType != Entity.PART_RARM)
				p.getImage().draw(xPos, yPos, WID_HEI, WID_HEI,
						p.isUnlocked() ? Color.white : Color.darkGray);
			else {
				p.getImage().draw(xPos, yPos, xPos + WID_HEI, yPos + WID_HEI,
						0, 64, p.getImage().getWidth(),
						p.getImage().getHeight() - 64,
						p.isUnlocked() ? Color.white : Color.darkGray);
			}

			if (mouseX >= xPos && mouseX <= xPos + WID_HEI && mouseY >= yPos
					&& mouseY <= yPos + WID_HEI) {
				hoveredPart = p;
				hoveredType = partType;
			}

			if (clickX >= xPos && clickX <= xPos + WID_HEI && clickY >= yPos
					&& clickY <= yPos + WID_HEI) {
				if (p.isUnlocked()) {
					if (playerEntity.getPart(partType) == p
							&& p.getPartStats().getHealth() < p.getPartStats()
									.getMaxHealth()) {
						if (playerEntity.getCurrency() >= 20) {
							playerEntity.spendCurrency(20);
							p.getPartStats().setHealth(
									p.getPartStats().getMaxHealth());
						}
					} else
						playerEntity.setPart(p, partType);
				} else {
					if (playerEntity.getCurrency() >= p.getCost()) {
						playerEntity.spendCurrency(p.getCost());
						p.setUnlocked(true);
					}
				}
			}

			xPos += X_GAP;
		}
	}
}
