package net.buddat.ludumdare.ld33.entity;

import java.util.Random;

import net.buddat.ludumdare.ld33.Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class Part {

	public static final int DEFAULT_WIDTH = 64, DEFAULT_HEIGHT = 64;
	private static final Random RAND = new Random(System.currentTimeMillis());

	protected int width, height;

	protected int unlockCost = 500;

	protected boolean isUnlocked = false;

	protected String name;

	private Image partImage;

	private PartStats partStats;

	protected boolean isHighlighted = false;

	protected Rectangle bounds;

	public boolean isUnlocked() {
		return isUnlocked;
	}

	public int getCost() {
		return unlockCost;
	}

	public void setUnlocked(boolean unlocked) {
		isUnlocked = unlocked;
	}

	public boolean isHighlighted() {
		return isHighlighted;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setImage(Image newImage) {
		partImage = newImage;
	}

	public Image getImage() {
		return partImage;
	}

	public PartStats getPartStats() {
		return partStats;
	}

	public void setPartStats(PartStats newStats) {
		partStats = newStats;
	}

	public boolean isDead() {
		return partStats.getHealth() <= 0;
	}

	public void action(Entity from, Entity target, int targetPart) {
		if (getPartStats().getAttack() > 0) {
			target.getPart(targetPart)
					.getPartStats()
					.damagePart(from, getPartStats(),
							from != Game.getInstance().getPlayerEntity());
		}

		if (getPartStats().getHealing() > 0) {
			int part = RAND.nextInt(5);
			Part p = from.getPart(part);

			if (p != null) {
				p.getPartStats()
						.heal((int) (getPartStats().getHealing() * (RAND
								.nextFloat() + 0.5f)));
				getPartStats().startCooldown();
			}
		}
	}

	public abstract void render(GameContainer gc, Graphics g, boolean highlight);

	public abstract void update(GameContainer gc, int delta);

	public void init(GameContainer gc) {
		try {
			partImage = new Image("images/body_default.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void resetCooldown() {
		getPartStats().resetCooldown();
	}
}
