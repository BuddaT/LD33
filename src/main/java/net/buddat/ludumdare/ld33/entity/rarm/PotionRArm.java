package net.buddat.ludumdare.ld33.entity.rarm;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class PotionRArm extends RArmPart {

	public PotionRArm(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 250;

		this.HEALTH = 400;
		this.DEFENSE = 10;
		this.ATTACK = 0;
		this.HEALING = 80;
		this.SPEED = 2;
		this.TYPE = PartStats.TYPE_WATER;

		this.imageFile = "images/rarm_potion.png";
	}
}
