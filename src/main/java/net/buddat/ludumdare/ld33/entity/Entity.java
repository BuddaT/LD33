package net.buddat.ludumdare.ld33.entity;

import java.util.ArrayList;
import java.util.HashMap;

import net.buddat.ludumdare.ld33.entity.body.ElementalBody;
import net.buddat.ludumdare.ld33.entity.body.GooBody;
import net.buddat.ludumdare.ld33.entity.body.GorgonBody;
import net.buddat.ludumdare.ld33.entity.body.HumanBody;
import net.buddat.ludumdare.ld33.entity.feet.ElementalFeet;
import net.buddat.ludumdare.ld33.entity.feet.HumanFeet;
import net.buddat.ludumdare.ld33.entity.feet.LavaFeet;
import net.buddat.ludumdare.ld33.entity.feet.TalonFeet;
import net.buddat.ludumdare.ld33.entity.head.DragonHead;
import net.buddat.ludumdare.ld33.entity.head.ElementalHead;
import net.buddat.ludumdare.ld33.entity.head.GorgonHead;
import net.buddat.ludumdare.ld33.entity.head.HumanHead;
import net.buddat.ludumdare.ld33.entity.larm.HugeSwordLArm;
import net.buddat.ludumdare.ld33.entity.larm.HumanLArm;
import net.buddat.ludumdare.ld33.entity.larm.MaceLArm;
import net.buddat.ludumdare.ld33.entity.larm.OrbLArm;
import net.buddat.ludumdare.ld33.entity.rarm.HumanRArm;
import net.buddat.ludumdare.ld33.entity.rarm.PotionRArm;
import net.buddat.ludumdare.ld33.entity.rarm.ShieldRArm;
import net.buddat.ludumdare.ld33.entity.rarm.SwordRArm;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Entity {

	public static final int PART_HEAD = 0, PART_BODY = 1, PART_FEET = 2,
			PART_LARM = 3, PART_RARM = 4;

	private static final HashMap<Integer, ArrayList<Part>> ALL_PARTS;

	private final HashMap<Integer, Part> partList = new HashMap<Integer, Part>();

	public void setPart(Part bp, int partIdx) {
		if (partList.containsKey(partIdx))
			partList.remove(partIdx);

		partList.put(partIdx, bp);
	}

	public Part getPart(int partIdx) {
		return partList.get(partIdx);
	}

	public int getAttack() {
		int toReturn = 0;

		for (Part p : partList.values()) {
			if (!p.isDead())
				toReturn += p.getPartStats().getAttack();
		}

		return toReturn;
	}

	public int getHealth() {
		int toReturn = 0;

		for (Part p : partList.values()) {
			if (!p.isDead())
				toReturn += p.getPartStats().getHealth();
		}

		return toReturn;
	}

	public int getMaxHealth() {
		int toReturn = 0;

		for (Part p : partList.values()) {
			toReturn += p.getPartStats().getMaxHealth();
		}

		return toReturn;
	}

	public abstract void render(GameContainer gc, Graphics g);

	public abstract void update(GameContainer gc, int delta);

	public abstract void init(GameContainer gc);

	public static ArrayList<Part> getPartList(int partIdx) {
		return ALL_PARTS.get(partIdx);
	}

	public static void initParts(GameContainer gc) {
		for (ArrayList<Part> pList : ALL_PARTS.values())
			for (Part p : pList)
				p.init(gc);
	}

	static {
		ALL_PARTS = new HashMap<Integer, ArrayList<Part>>();

		{
			ArrayList<Part> partList = new ArrayList<Part>();
			partList.add(new HumanHead("Human"));
			partList.add(new GorgonHead("Gorgon"));
			partList.add(new ElementalHead("Elemental"));
			partList.add(new DragonHead("Dragon"));

			ALL_PARTS.put(PART_HEAD, partList);
		}

		{
			ArrayList<Part> partList = new ArrayList<Part>();
			partList.add(new HumanBody("Human"));
			partList.add(new GorgonBody("Gorgon"));
			partList.add(new ElementalBody("Elemental"));
			partList.add(new GooBody("Goo"));

			ALL_PARTS.put(PART_BODY, partList);
		}

		{
			ArrayList<Part> partList = new ArrayList<Part>();
			partList.add(new HumanFeet("Human"));
			partList.add(new TalonFeet("Talon"));
			partList.add(new ElementalFeet("Elemental"));
			partList.add(new LavaFeet("Bucket"));

			ALL_PARTS.put(PART_FEET, partList);
		}

		{
			ArrayList<Part> partList = new ArrayList<Part>();
			partList.add(new HumanRArm("Empty"));
			partList.add(new ShieldRArm("Shield"));
			partList.add(new SwordRArm("Sword"));
			partList.add(new PotionRArm("Potion"));

			ALL_PARTS.put(PART_RARM, partList);
		}

		{
			ArrayList<Part> partList = new ArrayList<Part>();
			partList.add(new HumanLArm("Sword"));
			partList.add(new OrbLArm("Orb"));
			partList.add(new HugeSwordLArm("Hugesword"));
			partList.add(new MaceLArm("Mace"));

			ALL_PARTS.put(PART_LARM, partList);
		}
	}

}
