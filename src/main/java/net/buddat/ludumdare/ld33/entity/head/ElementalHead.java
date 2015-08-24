package net.buddat.ludumdare.ld33.entity.head;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class ElementalHead extends HeadPart {

	public ElementalHead(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;

		this.unlockCost = 300;

		this.HEALTH = 80;
		this.DEFENSE = 10;
		this.ATTACK = 20;
		this.HEALING = 0;
		this.SPEED = 10;
		this.TYPE = PartStats.TYPE_AIR;

		this.imageFile = "images/head_elemental.png";
	}
}
