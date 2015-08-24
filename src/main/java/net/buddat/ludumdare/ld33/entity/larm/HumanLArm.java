package net.buddat.ludumdare.ld33.entity.larm;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class HumanLArm extends LArmPart {

	public HumanLArm(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 0;
		this.isUnlocked = true;

		this.HEALTH = 100;
		this.DEFENSE = 5;
		this.ATTACK = 100;
		this.HEALING = 0;
		this.SPEED = 5;
		this.TYPE = PartStats.TYPE_NONE;

		this.imageFile = "images/larm_humansword.png";
	}
}
