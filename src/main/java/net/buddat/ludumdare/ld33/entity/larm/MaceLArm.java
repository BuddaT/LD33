package net.buddat.ludumdare.ld33.entity.larm;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class MaceLArm extends LArmPart {

	public MaceLArm(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 500;

		this.HEALTH = 30;
		this.DEFENSE = 5;
		this.ATTACK = 150;
		this.HEALING = 0;
		this.SPEED = 3;
		this.TYPE = PartStats.TYPE_WATER;

		this.imageFile = "images/larm_mace.png";
	}
}
