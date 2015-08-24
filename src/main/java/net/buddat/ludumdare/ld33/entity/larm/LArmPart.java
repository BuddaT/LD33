package net.buddat.ludumdare.ld33.entity.larm;

import net.buddat.ludumdare.ld33.entity.Part;
import net.buddat.ludumdare.ld33.entity.PartStats;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class LArmPart extends Part {

	private static final int ROTATE_SPEED = 150, ROTATE_AMNT = -8;
	private int rotation = ROTATE_AMNT;
	private int lastRotate = 0;

	protected int HEALTH, DEFENSE, ATTACK, HEALING, SPEED, TYPE;
	protected String imageFile;

	@Override
	public void render(GameContainer gc, Graphics g, boolean highlight) {
		isHighlighted = bounds.contains(gc.getInput().getMouseX(), gc
				.getInput().getMouseY())
				&& highlight;
		Color c = (isHighlighted ? (getPartStats().canSwing() ? Color.green
				: Color.gray) : (getPartStats().canSwing() ? Color.white
				: Color.gray));

		if (isDead()) {
			isHighlighted = false;
			c = Color.red;
		}

		if (!isDead())
			g.rotate(332, 214 + getHeight(), rotation);
		g.drawImage(getImage(), 268, 214, c);
		if (!isDead())
			g.rotate(332, 214 + getHeight(), -rotation);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if ((lastRotate += delta) > ROTATE_SPEED) {
			if (rotation == ROTATE_AMNT)
				rotation = -ROTATE_AMNT;
			else
				rotation = ROTATE_AMNT;

			lastRotate = -150;
		}

		getPartStats().update(delta);
	}

	@Override
	public void init(GameContainer gc) {
		super.init(gc);

		try {
			setImage(new Image(imageFile));
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (getPartStats() == null)
			setPartStats(new PartStats(HEALTH, DEFENSE, ATTACK, HEALING,
					SPEED, TYPE));

		bounds = new Rectangle(300, 310, getWidth(), getHeight());
	}

}
