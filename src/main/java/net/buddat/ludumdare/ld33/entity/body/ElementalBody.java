package net.buddat.ludumdare.ld33.entity.body;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class ElementalBody extends BodyPart {

	public ElementalBody(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH * 2;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 100;

		this.HEALTH = 500;
		this.DEFENSE = 40;
		this.ATTACK = 0;
		this.HEALING = 0;
		this.SPEED = 0;
		this.TYPE = PartStats.TYPE_EARTH;

		this.imageFile = "images/body_elemental.png";
	}
}
