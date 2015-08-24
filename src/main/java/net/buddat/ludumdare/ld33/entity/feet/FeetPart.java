package net.buddat.ludumdare.ld33.entity.feet;

import net.buddat.ludumdare.ld33.entity.Part;
import net.buddat.ludumdare.ld33.entity.PartStats;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class FeetPart extends Part {

	private static final int ROTATE_SPEED = 300, ROTATE_AMNT = -8;
	private int rotation = ROTATE_AMNT;
	private int lastRotate = 0;

	protected int HEALTH, DEFENSE, ATTACK, HEALING, SPEED, TYPE;
	protected String imageFile;

	private Image flippedImage;
	private Rectangle bounds2;

	@Override
	public void render(GameContainer gc, Graphics g, boolean highlight) {
		isHighlighted = (bounds.contains(gc.getInput().getMouseX(), gc
				.getInput().getMouseY())
				|| bounds2.contains(gc.getInput().getMouseX(), gc.getInput()
.getMouseY()))
				&& highlight;
		Color c = (isHighlighted ? (getPartStats().canSwing() ? Color.green
				: Color.gray) : (getPartStats().canSwing() ? Color.white
				: Color.gray));

		if (isDead()) {
			isHighlighted = false;
			c = Color.red;
		}

		if (!isDead())
			g.rotate(136, 510 + getHeight(), rotation);
		g.drawImage(getImage(), 104, 510, c);
		if (!isDead())
			g.rotate(136, 510 + getHeight(), -rotation);

		if (!isDead())
			g.rotate(264, 510 + getHeight(), rotation);
		g.drawImage(flippedImage, 232, 510, c);
		if (!isDead())
			g.rotate(264, 510 + getHeight(), -rotation);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if ((lastRotate += delta) > ROTATE_SPEED) {
			if (rotation == ROTATE_AMNT)
				rotation = -ROTATE_AMNT;
			else
				rotation = ROTATE_AMNT;

			lastRotate = 0;
		}

		getPartStats().update(delta);
	}

	@Override
	public void init(GameContainer gc) {
		super.init(gc);

		try {
			setImage(new Image(imageFile));
			flippedImage = getImage().getFlippedCopy(true, false);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (getPartStats() == null)
			setPartStats(new PartStats(HEALTH, DEFENSE, ATTACK, HEALING,
					SPEED, TYPE));

		bounds = new Rectangle(104, 478 + getHeight(), getWidth() * 2,
				getHeight() * 2);
		bounds2 = new Rectangle(232, 478 + getHeight(), getWidth() * 2,
				getHeight() * 2);
	}

}
