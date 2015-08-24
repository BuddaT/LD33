package net.buddat.ludumdare.ld33.entity.rarm;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class SwordRArm extends RArmPart {

	public SwordRArm(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 200;

		this.HEALTH = 50;
		this.DEFENSE = 10;
		this.ATTACK = 100;
		this.HEALING = 0;
		this.SPEED = 5;
		this.TYPE = PartStats.TYPE_NONE;

		this.imageFile = "images/rarm_sword.png";
	}
}
