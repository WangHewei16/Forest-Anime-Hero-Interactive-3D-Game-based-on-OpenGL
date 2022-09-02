package objects3D;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

/**
 * A class to implement TexCube object
 *
 * Variables:  vertices[] including 8 points, faces[][] including 6 groups of order of points
 * OpenGL Method being used:  GL11.glNormal3f(), GL11.glVertex3f(), GL11.glTexCoord2f()
 *
 * Purpose and details:
 * Taking advantage of loop to draw the texture, color and shadow for each face.
 * Each face lies on a plane, so I compute a normal and using GL11.glNormal3f() method in OpenGL for reflecting light.
 * And I take advantage of GL11.glTexCoord2f() to achieve the effect of texture
 * 
 * @author Wang Hewei
 */

public class TexCube {
	public float fradius;
	Vector3f angle = new Vector3f();
	Vector3f scale = new Vector3f();

	public void setScale(float sx, float sy, float sz) {
		this.scale.set(sx, sy, sz);
	}

	public Vector3f position = new Vector3f();

	public void setPosition(float x, float y, float z) {
		this.position.set(x, y, z);
	}
	public void setAngle(Vector3f angle) {
		this.angle.set(angle);
	}
	public TexCube() {

	}

	// Implement using notes and looking at TexSphere
	public void DrawTexCube(Texture texture)
	{
		// Eight points in a cube
		Point4f vertices[] = {  new Point4f(-1.0f, -1.0f, -1.0f, 0.0f),
				new Point4f(-1.0f, -1.0f, 1.0f, 0.0f),
				new Point4f(-1.0f, 1.0f, -1.0f, 0.0f),
				new Point4f(-1.0f, 1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, -1.0f, -1.0f, 0.0f),
				new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, 1.0f, -1.0f, 0.0f),
				new Point4f(1.0f, 1.0f, 1.0f, 0.0f) };

		// The six groups of order in each face
		int faces[][] = {{ 0, 4, 5, 1 },
						 { 0, 2, 6, 4 },
						 { 0, 1, 3, 2 },
						 { 4, 6, 7, 5 },
						 { 1, 5, 7, 3 },
						 { 2, 3, 7, 6 }};

		GL11.glBegin(GL11.GL_QUADS);

		// Taking advantage of loop to draw the texture and shadow for each face.
		for (int face = 0; face < 6; face++) { // per face

			// Each face lies on a plane, so we can compute a normal and using GL11.glNormal3f() method in OpenGL to draw reflecting light
			Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
			Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
			Vector4f normal = v.cross(w).Normal();
			GL11.glNormal3f(normal.x, normal.y, normal.z);

			// Draw the texture and color for each face using GL11.glVertex3f()
			GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
			GL11.glTexCoord2f(0,1);
		} // per face
		GL11.glEnd();
	}
}
	 