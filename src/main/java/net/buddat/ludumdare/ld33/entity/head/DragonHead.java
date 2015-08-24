package net.buddat.ludumdare.ld33.entity.head;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class DragonHead extends HeadPart {

	public DragonHead(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;

		this.unlockCost = 900;

		this.HEALTH = 200;
		this.DEFENSE = 20;
		this.ATTACK = 100;
		this.HEALING = 10;
		this.SPEED = 3;
		this.TYPE = PartStats.TYPE_FIRE;

		this.imageFile = "images/head_dragon.png";
	}
}
