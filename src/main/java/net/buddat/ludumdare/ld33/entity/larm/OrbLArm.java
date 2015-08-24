package net.buddat.ludumdare.ld33.entity.larm;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class OrbLArm extends LArmPart {

	public OrbLArm(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 200;

		this.HEALTH = 70;
		this.DEFENSE = 10;
		this.ATTACK = 40;
		this.HEALING = 0;
		this.SPEED = 8;
		this.TYPE = PartStats.TYPE_PSI;

		this.imageFile = "images/larm_orb.png";
	}
}
