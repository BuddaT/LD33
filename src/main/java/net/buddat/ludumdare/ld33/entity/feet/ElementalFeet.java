package net.buddat.ludumdare.ld33.entity.feet;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class ElementalFeet extends FeetPart {

	public ElementalFeet(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH / 2;
		this.height = DEFAULT_HEIGHT / 2;

		this.unlockCost = 250;

		this.HEALTH = 80;
		this.DEFENSE = 10;
		this.ATTACK = 0;
		this.HEALING = 20;
		this.SPEED = 2;
		this.TYPE = PartStats.TYPE_AIR;

		this.imageFile = "images/feet_elemental.png";
	}
}
