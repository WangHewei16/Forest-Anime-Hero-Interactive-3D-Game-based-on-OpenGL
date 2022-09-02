package GraphicsObjects;
import org.lwjgl.opengl.GL11;
/**
 * A class to implement Circle object
 * 
 * Purpose and details:
 * Taking advantage of a for loop with two angles and two points, two vectors to get the norm and draw circle based on it.
 *  
 * @author Wang Hewei
 */
public class Circle {

    public void drawCircle() {
        GL11.glBegin(GL11.GL_TRIANGLES);
        for (float i = 0; i <= 32; i++){
            float angle1 = (float) (Math.PI * 2 * i / 32);
            float angle2 = (float) ((Math.PI * 2 * i + Math.PI * 2) / 32);
            float x1 = (float) Math.cos(angle1);
            float y1 = (float) Math.sin(angle1);
            float x2 = (float) Math.cos(angle2);
            float y2 = (float) Math.sin(angle2);
            Vector4f vector1 = new Point4f(x1, y1, 0, 0).MinusPoint(new Point4f(0, 0, 0, 0));
            Vector4f vector2 = new Point4f(x2, y2, 0, 0).MinusPoint(new Point4f(0, 0, 0, 0));
            Vector4f norm = vector1.cross(vector2).Normal();
            GL11.glNormal3f(norm.x, norm.y, norm.z);
            GL11.glVertex3f(x1, y1, 0);
            GL11.glNormal3f(norm.x, norm.y, norm.z);
            GL11.glVertex3f(x2, y2, 0);
            GL11.glNormal3f(norm.x, norm.y, norm.z);
            GL11.glVertex3f(0, 0, 0);
        }
        GL11.glEnd();
    }
}