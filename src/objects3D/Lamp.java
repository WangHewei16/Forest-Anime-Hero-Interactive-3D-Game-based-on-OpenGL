package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

/**
 * A class to implement Lamp object
 * 
 * 
 * Purpose and details:
 * Take advantage of two cylinders to draw lights and poles.
 * 
 * @author Wang Hewei
 */

public class Lamp {
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

	public Lamp() {

	}

	public void DrawStreetlight() {
		Cylinder cylinder = new Cylinder();
		GL11.glPushMatrix();
		{
			GL11.glColor3f(brown[0], brown[1], brown[2]);
			GL11.glTranslatef(0.0f, 5f, 0.0f);
			GL11.glRotatef(90, 1, 0, 0);
			cylinder.DrawCylinder(0.125f, 12f, 32);

		GL11.glPushMatrix();
		{
			GL11.glColor3f(yellow[0], yellow[1], yellow[2]);
			GL11.glScalef(0.1f, 0.3f, 0.1f);
			GL11.glTranslatef(0f, 0f, 0f);
			GL11.glRotatef(60, 0, 1, 0);
			cylinder.DrawCylinder(2, 15, 32);
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		}
	}
}



