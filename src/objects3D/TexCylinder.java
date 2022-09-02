package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class TexCylinder {

	
	public TexCylinder() { 
	}
	
	// remember to use Math.PI instead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding look at lecture 7, 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments,Texture texture) 
	{
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		texture.bind();
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		// using a loop around a tube's circumference
		for ( float i = (float) 0.0; i < nSegments; i+=1.0 ){
			float angle = (float) (Math.PI * 2.0 * i / nSegments);
			float nextAngle = (float) (Math.PI  * (i + 1.0) * 2.0 / nSegments);

			// calculate sin and cosine, multiple by value of radius
			float x1 = (float) Math.sin(angle) * radius;
			float y1 = (float) Math.cos(angle) * radius;
			float x2 = (float) Math.sin(nextAngle) * radius;
			float y2 = (float) Math.cos(nextAngle) * radius;

		
			// draw top (green) triangle
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, 0);//
			GL11.glTexCoord2f((float) (angle/(Math.PI*2)),0);
			
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, height);//
			GL11.glTexCoord2f((float) (angle/(Math.PI*2)),1);
			
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, height);//

			// draw bottom (red) triangle
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, 0);
		
			
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, 0);
			GL11.glTexCoord2f((float) (nextAngle/(Math.PI*2)),0);
			
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, height);
			GL11.glTexCoord2f((float) (nextAngle/(Math.PI*2)),1);
			
			// Compute a normal and using GL11.glNormal3f() method in OpenGL to draw reflecting light
			Vector4f vector1 = new Point4f(0, 0, 0, 0).MinusPoint(new Point4f(x1, y1, 0, 0));
			Vector4f vector2 = new Point4f(0, 0, 0, 0).MinusPoint(new Point4f(x2, y2, 0, 0));
			Vector4f normal = vector1.cross(vector2).Normal();
			GL11.glNormal3f(normal.x, normal.y, normal.z);
			GL11.glVertex3f(x1, y1, 0);
			GL11.glVertex3f(x2, y2, 0);
			GL11.glVertex3f(0, 0, 0);
			GL11.glVertex3f(x1, y1, height);
			GL11.glVertex3f(x2, y2, height);
			GL11.glVertex3f(0, 0, height);
		}
		
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
