package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public class Pool {
    public void drawPool(Texture texture1, Texture texture2, float delta) {
        TexCylinder cylinder = new TexCylinder();
        GL11.glPushMatrix();{
            GL11.glRotatef(-90, 1, 0, 0);
            cylinder.DrawCylinder(2.5f, 2, 32, texture1);
            GL11.glPushMatrix();{
                GL11.glTranslatef(0, 0, -0.1f);
                cylinder.DrawCylinder(2.3f, 0.0001f, 32, texture2);
            }GL11.glPopMatrix();
        }GL11.glPopMatrix();
    }
}
