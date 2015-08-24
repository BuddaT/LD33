package net.buddat.ludumdare.ld33.entity.feet;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class HumanFeet extends FeetPart {

	public HumanFeet(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH / 2;
		this.height = DEFAULT_HEIGHT / 2;

		this.unlockCost = 0;
		this.isUnlocked = true;

		this.HEALTH = 50;
		this.DEFENSE = 5;
		this.ATTACK = 20;
		this.HEALING = 0;
		this.SPEED = 1;
		this.TYPE = PartStats.TYPE_NONE;

		this.imageFile = "images/feet_human.png";
	}
}
