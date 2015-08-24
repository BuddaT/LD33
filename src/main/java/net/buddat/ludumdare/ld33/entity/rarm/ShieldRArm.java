package net.buddat.ludumdare.ld33.entity.rarm;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class ShieldRArm extends RArmPart {

	public ShieldRArm(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 100;

		this.HEALTH = 300;
		this.DEFENSE = 30;
		this.ATTACK = 0;
		this.HEALING = 0;
		this.SPEED = 0;
		this.TYPE = PartStats.TYPE_NONE;

		this.imageFile = "images/rarm_shield.png";
	}
}
