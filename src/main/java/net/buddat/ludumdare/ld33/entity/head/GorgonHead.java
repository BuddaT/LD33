package net.buddat.ludumdare.ld33.entity.head;

import net.buddat.ludumdare.ld33.entity.PartStats;

public class GorgonHead extends HeadPart {

	public GorgonHead(String name) {
		this.name = name;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;

		this.unlockCost = 200;

		this.HEALTH = 120;
		this.DEFENSE = 20;
		this.ATTACK = 0;
		this.HEALING = 50;
		this.SPEED = 3;
		this.TYPE = PartStats.TYPE_PSI;

		this.imageFile = "images/head_gorgon.png";
	}
}
