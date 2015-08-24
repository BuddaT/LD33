package net.buddat.ludumdare.ld33.entity.rarm;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class HumanRArm extends RArmPart {

	public HumanRArm(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 0;
		this.isUnlocked = true;

		this.HEALTH = 150;
		this.DEFENSE = 0;
		this.ATTACK = 25;
		this.HEALING = 0;
		this.SPEED = 5;
		this.TYPE = PartStats.TYPE_NONE;

		this.imageFile = "images/rarm_human.png";
	}
}
