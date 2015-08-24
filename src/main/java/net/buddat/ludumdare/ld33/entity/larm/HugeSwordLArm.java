package net.buddat.ludumdare.ld33.entity.larm;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class HugeSwordLArm extends LArmPart {

	public HugeSwordLArm(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 400;

		this.HEALTH = 200;
		this.DEFENSE = 30;
		this.ATTACK = 300;
		this.HEALING = 0;
		this.SPEED = 2;
		this.TYPE = PartStats.TYPE_EARTH;

		this.imageFile = "images/larm_hugesword.png";
	}
}
