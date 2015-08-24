package net.buddat.ludumdare.ld33.entity.body;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class GooBody extends BodyPart {

	public GooBody(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH * 2;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 250;

		this.HEALTH = 50;
		this.DEFENSE = 50;
		this.ATTACK = 100;
		this.HEALING = 20;
		this.SPEED = 5;
		this.TYPE = PartStats.TYPE_NONE;

		this.imageFile = "images/body_goo.png";
	}
}
