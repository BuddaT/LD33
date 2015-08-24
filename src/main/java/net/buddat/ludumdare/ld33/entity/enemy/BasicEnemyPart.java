package net.buddat.ludumdare.ld33.entity.enemy;

import java.util.Random;

import net.buddat.ludumdare.ld33.Game;
import net.buddat.ludumdare.ld33.entity.Part;
import net.buddat.ludumdare.ld33.entity.PartStats;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BasicEnemyPart extends Part {

	private static final Random RAND = new Random(System.currentTimeMillis());

	private static final int ROTATE_SPEED = 500, ROTATE_AMNT = 8;
	private int rotation = ROTATE_AMNT;
	private int lastRotate = 0;

	protected int HEALTH, DEFENSE, ATTACK, HEALING, SPEED, TYPE;
	protected String imageFile;

	private Image bgImage;
	private Color tint;

	public BasicEnemyPart(String name) {
		this.name = name;

		this.width = 256;
		this.height = 256;

		int modifier = Game.getInstance().getPlayerEntity().getKillCount();

		this.HEALTH = (int) (500 + (modifier * (RAND.nextFloat() * 100)));
		this.DEFENSE = (int) (10 + (modifier * (RAND.nextFloat() * 2)));
		this.ATTACK = (int) (30 + (modifier * (RAND.nextFloat() * 50)));
		this.HEALING = (int) (10 + (modifier * (RAND.nextFloat() * 10)));
		this.SPEED = (int) (3 + (modifier * (RAND.nextFloat())));
		this.TYPE = PartStats.TYPE_NONE;

		switch (RAND.nextInt(6)) {
			case 0:
				this.TYPE = PartStats.TYPE_AIR;
				break;
			case 1:
				this.TYPE = PartStats.TYPE_EARTH;
				break;
			case 2:
				this.TYPE = PartStats.TYPE_FIRE;
				break;
			case 3:
				this.TYPE = PartStats.TYPE_NONE;
				break;
			case 4:
				this.TYPE = PartStats.TYPE_PSI;
				break;
			case 5:
				this.TYPE = PartStats.TYPE_WATER;
				break;
		}

		this.imageFile = "images/enemy_basic.png";

		String hex = String.format("#%02x%02x%02x", RAND.nextInt(255),
				RAND.nextInt(255), RAND.nextInt(255));

		tint = Color.decode(hex);
	}

	public void setTint(Color c) {
		this.tint = c;
	}

	@Override
	public void render(GameContainer gc, Graphics g, boolean highlight) {
		Color c = getPartStats().canSwing() ? Color.white : Color.gray;

		if (isDead()) {
			isHighlighted = false;
			c = Color.red;
		}

		if (!isDead())
			g.rotate(600, 408, rotation);

		g.drawImage(bgImage, 472, 280, c);
		g.drawImage(getImage(), 472, 280, tint);

		if (!isDead())
			g.rotate(600, 408, -rotation);
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
			bgImage = new Image("images/enemy_basicbg.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (getPartStats() == null)
			setPartStats(new PartStats(HEALTH, DEFENSE, ATTACK, HEALING,
					SPEED, TYPE));
	}

}
