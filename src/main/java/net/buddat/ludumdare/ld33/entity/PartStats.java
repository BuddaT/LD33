package net.buddat.ludumdare.ld33.entity;

import java.util.Random;

public class PartStats {

	public static final int TYPE_WATER = 0, TYPE_FIRE = 1, TYPE_PSI = 2,
			TYPE_AIR = 3, TYPE_EARTH = 4, TYPE_NONE = -10;

	private static final Random DMG_RANDOM = new Random(
			System.currentTimeMillis());

	private final int maxhealth;

	private int health;

	private int defense;

	private int attack;

	private int healing;

	private int speed;

	private int type;

	private int lastAttack = 99999;
	private final int cooldownTime;

	private int damagedTime = 0;

	public PartStats(int health, int defense, int attack, int healing,
			int speed, int type) {
		this.health = health;
		this.maxhealth = health;
		this.defense = defense;
		this.attack = attack;
		this.type = type;
		this.healing = healing;
		this.speed = speed;

		this.cooldownTime = (int) (1000.0f / (speed / 10.0f));
	}

	public void damagePart(Entity attacker, PartStats fromPart,
			boolean triggerCooldown) {
		int attack = fromPart.getAttack();
		float modifier = 1.0f;

		if (isWeakTo(fromPart.getType()))
			modifier += 0.5f;
		else if (isStrongTo(fromPart.getType()))
			modifier -= 0.3f;

		modifier -= defense / 100f;
		modifier += (DMG_RANDOM.nextFloat() / 2) - 0.25f;

		health -= (int) (attack * modifier);

		if (health < 0)
			health = 0;

		damagedTime = 400;

		fromPart.startCooldown();
		if (triggerCooldown)
			startCooldown();
	}

	public boolean isWeakTo(int fromPartType) {
		int thisType = type;

		if (thisType == 0)
			thisType = 5;

		if (thisType - 1 == fromPartType)
			return true;

		return false;
	}

	public boolean isStrongTo(int fromPartType) {
		int thisType = type;

		if (thisType == 4)
			thisType = -1;

		if (thisType + 1 == fromPartType)
			return true;

		return false;
	}

	public boolean canSwing() {
		return lastAttack > cooldownTime && getHealth() > 0;
	}

	public int getDamagedTime() {
		return damagedTime;
	}

	public void startCooldown() {
		lastAttack = 0;
	}

	public void resetCooldown() {
		lastAttack = 99999;
	}

	public void update(int delta) {
		lastAttack += delta;
		damagedTime -= delta;
	}

	public void heal(int amnt) {
		if (health + amnt <= maxhealth)
			health += amnt;
		else
			health = maxhealth;
	}

	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxhealth;
	}

	public void setHealth(int newHealth) {
		health = newHealth;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int newDefense) {
		defense = newDefense;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int newAttack) {
		attack = newAttack;
	}

	public int getHealing() {
		return healing;
	}

	public void setHealing(int newInt) {
		healing = newInt;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	public int getType() {
		return type;
	}

	public void setType(int newType) {
		type = newType;
	}

	public static final String getTypeString(int type) {
		switch (type) {
			case TYPE_WATER:
				return "water";
			case TYPE_FIRE:
				return "fire";
			case TYPE_PSI:
				return "psi";
			case TYPE_AIR:
				return "air";
			case TYPE_EARTH:
				return "earth";
			default:
				return "normal";
		}
	}
}
