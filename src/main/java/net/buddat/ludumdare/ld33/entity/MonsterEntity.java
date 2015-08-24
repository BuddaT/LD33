package net.buddat.ludumdare.ld33.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class MonsterEntity extends Entity {

	private Part selectedPart = null;

	private int currency = 0;
	private int killCount = 0;

	public int getCurrency() {
		return currency;
	}

	public void spendCurrency(int amount) {
		currency -= amount;
		if (currency < 0)
			currency = 0;
	}

	public int getKillCount() {
		return killCount;
	}

	public void addReward(int rewardValue) {
		currency += rewardValue;
		killCount += 1;
	}

	public Part getSelectedPart() {
		return selectedPart;
	}

	public void setSelectedPart(Part p) {
		selectedPart = p;
	}

	public Part getHighlightedPart() {
		if (getPart(Entity.PART_HEAD).isHighlighted())
			return getPart(Entity.PART_HEAD);

		if (getPart(Entity.PART_BODY).isHighlighted())
			return getPart(Entity.PART_BODY);

		if (getPart(Entity.PART_FEET).isHighlighted())
			return getPart(Entity.PART_FEET);

		if (getPart(Entity.PART_RARM).isHighlighted())
			return getPart(Entity.PART_RARM);

		if (getPart(Entity.PART_LARM).isHighlighted())
			return getPart(Entity.PART_LARM);

		return null;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		render(gc, g, false);
	}

	public void render(GameContainer gc, Graphics g, boolean highlight) {
		g.setColor(Color.white);

		getPart(Entity.PART_BODY).render(gc, g, highlight);
		getPart(Entity.PART_HEAD).render(gc, g, highlight);
		getPart(Entity.PART_FEET).render(gc, g, highlight);
		getPart(Entity.PART_RARM).render(gc, g, highlight);
		getPart(Entity.PART_LARM).render(gc, g, highlight);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		getPart(PART_HEAD).update(gc, delta);
		getPart(PART_BODY).update(gc, delta);
		getPart(PART_FEET).update(gc, delta);
		getPart(PART_RARM).update(gc, delta);
		getPart(PART_LARM).update(gc, delta);
	}

	@Override
	public void init(GameContainer gc) {
		setPart(Entity.getPartList(PART_HEAD).get(0), PART_HEAD);
		setPart(Entity.getPartList(PART_BODY).get(0), PART_BODY);
		setPart(Entity.getPartList(PART_FEET).get(0), PART_FEET);
		setPart(Entity.getPartList(PART_RARM).get(0), PART_RARM);
		setPart(Entity.getPartList(PART_LARM).get(0), PART_LARM);
	}

	public void refreshCooldowns() {
		getPart(PART_HEAD).resetCooldown();
		getPart(PART_BODY).resetCooldown();
		getPart(PART_FEET).resetCooldown();
		getPart(PART_RARM).resetCooldown();
		getPart(PART_LARM).resetCooldown();
	}

}
