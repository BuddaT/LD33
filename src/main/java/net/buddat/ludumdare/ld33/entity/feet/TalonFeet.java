package net.buddat.ludumdare.ld33.entity.feet;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class TalonFeet extends FeetPart {

	public TalonFeet(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH / 2;
		this.height = DEFAULT_HEIGHT / 2;

		this.unlockCost = 150;

		this.HEALTH = 25;
		this.DEFENSE = 15;
		this.ATTACK = 50;
		this.HEALING = 0;
		this.SPEED = 3;
		this.TYPE = PartStats.TYPE_EARTH;

		this.imageFile = "images/feet_talon.png";
	}
}
