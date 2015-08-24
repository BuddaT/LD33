package net.buddat.ludumdare.ld33.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Screen {

	public void render(GameContainer gc, Graphics g);

	public void update(GameContainer gc, int delta);

	public void init(GameContainer gc);

}
