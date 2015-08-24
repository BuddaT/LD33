package net.buddat.ludumdare.ld33.entity.head;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class HumanHead extends HeadPart {

	public HumanHead(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;

		this.unlockCost = 0;
		this.isUnlocked = true;

		this.HEALTH = 100;
		this.DEFENSE = 10;
		this.ATTACK = 10;
		this.HEALING = 10;
		this.SPEED = 1;
		this.TYPE = PartStats.TYPE_NONE;

		this.imageFile = "images/head_human.png";
	}
}
