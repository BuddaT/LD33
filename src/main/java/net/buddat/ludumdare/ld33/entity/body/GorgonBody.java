package net.buddat.ludumdare.ld33.entity.body;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class GorgonBody extends BodyPart {

	public GorgonBody(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH * 2;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 150;

		this.HEALTH = 200;
		this.DEFENSE = 20;
		this.ATTACK = 50;
		this.HEALING = 0;
		this.SPEED = 3;
		this.TYPE = PartStats.TYPE_WATER;

		this.imageFile = "images/body_gorgon.png";
	}
}
