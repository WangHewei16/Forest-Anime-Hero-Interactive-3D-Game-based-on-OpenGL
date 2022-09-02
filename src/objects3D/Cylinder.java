package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

/**
 * A class to implement Cylinder object
 * 
 * OpenGL Method being used:  GL11.glNormal3f(), GL11.glVertex3f(), GL11.glBegin(GL11.GL_QUADS), GL11.glEnd()
 * 
 * Purpose and details:
 * The cylinder is an extruded polygon, I can turn each edge into a vertical strip to achieve it.
 * To make the cylinder smoother, I provide normals for each vertex based on the tangent plane.
 * Cylinder is vertical, so normal is horizontal, which means normal lines stick straight out through each vertex.
 * 
 * @author Wang Hewei
 */

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI instead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding look at lecture 7, 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments ) 
	{
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
			
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, height);//
			
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, height);//

			// draw bottom (red) triangle
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, 0);
			
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, 0);
			
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, height);
			

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
	}
}
