package net.buddat.ludumdare.ld33.entity.feet;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class LavaFeet extends FeetPart {

	public LavaFeet(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH / 2;
		this.height = DEFAULT_HEIGHT / 2;

		this.unlockCost = 400;

		this.HEALTH = 100;
		this.DEFENSE = 50;
		this.ATTACK = 100;
		this.HEALING = 0;
		this.SPEED = 1;
		this.TYPE = PartStats.TYPE_FIRE;

		this.imageFile = "images/feet_lavabucket.png";
	}
}
