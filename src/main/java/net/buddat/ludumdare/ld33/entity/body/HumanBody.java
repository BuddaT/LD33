package net.buddat.ludumdare.ld33.entity.body;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class HumanBody extends BodyPart {

	public HumanBody(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH * 2;
		this.height = DEFAULT_HEIGHT * 2;

		this.unlockCost = 0;
		this.isUnlocked = true;

		this.HEALTH = 300;
		this.DEFENSE = 20;
		this.ATTACK = 0;
		this.HEALING = 50;
		this.SPEED = 1;
		this.TYPE = PartStats.TYPE_NONE;

		this.imageFile = "images/body_human.png";
	}
}
