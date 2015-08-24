package net.buddat.ludumdare.ld33.entity.enemy;

import java.util.Random;

import net.buddat.ludumdare.ld33.Game;
import net.buddat.ludumdare.ld33.entity.Entity;
import net.buddat.ludumdare.ld33.entity.Part;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class BasicEnemy extends Entity {

	private static final Random RAND = new Random(System.currentTimeMillis());

	private int lastAttempt = 0;

	private Entity playerEntity;

	@Override
	public void render(GameContainer gc, Graphics g) {
		getPart(Entity.PART_BODY).render(gc, g, false);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		Part bodyPart = getPart(Entity.PART_BODY);
		bodyPart.update(gc, delta);
		
		if (playerEntity == null)
			playerEntity = Game.getInstance().getPlayerEntity();

		if (bodyPart.getPartStats().canSwing()) {
			lastAttempt += delta;
			if (lastAttempt < 200)
				return;
			lastAttempt = 0;

			if (RAND.nextInt(5) == 0) {
				int idx = RAND.nextInt(5);
				while (playerEntity.getPart(idx).isDead()) {
					if (playerEntity.getHealth() <= 0)
						break;
					
					idx = RAND.nextInt(5);
				}

				bodyPart.action(this, playerEntity, idx);
			}
		}
	}

	@Override
	public void init(GameContainer gc) {
		Part newPart = new BasicEnemyPart("Basic");
		newPart.init(gc);
		setPart(newPart, Entity.PART_BODY);
	}

}
