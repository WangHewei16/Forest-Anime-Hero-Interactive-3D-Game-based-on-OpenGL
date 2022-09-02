package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Circle;
/**
 * A class to implement sun flower object
 *
 * Purpose and details:
 * Taking advantage of a TexCylinder, TexSphere and some circle to draw a sun flower object.
 * Using for loop to calculate the number and distribution of petals.
 * 
 * @author Wang Hewei
 */

public class Sunflower {

    public void drawFlower(Texture texture1, Texture texture2, float de, float height) {
        float theta = (float) (0.0003 * de * Math.PI);
        TexCylinder cylinder = new TexCylinder();
        TexSphere sphere = new TexSphere();
        Circle circle = new Circle();
        GL11.glPushMatrix();{
             GL11.glTranslatef(0, 0, 0.2f);
             GL11.glRotatef((float) (Math.sin(theta)*30), 0, 1, 0);
             cylinder.DrawCylinder(0.2f, 5f + height, 32, texture1);
             GL11.glPushMatrix();{
                    GL11.glTranslatef(0, 0, 5 + height);
                    GL11.glRotatef(90, 1, 0, 0);
                    sphere.DrawTexSphere(2, 32, 32, texture2);
                    GL11.glPushMatrix();{
                    	// Calculate the number and distribution of petals
                        for (float i = 0; i < Math.PI * 2; i += Math.PI * 2 / 7) {
                            GL11.glPushMatrix();{
                                GL11.glTranslatef((float) Math.cos(i)*2f, (float) Math.sin(i)*2f, 0);
                                circle.drawCircle();
                            }GL11.glPopMatrix();
                       }
                  }GL11.glPopMatrix();
            }GL11.glPopMatrix();
       }GL11.glPopMatrix();
    }
}
