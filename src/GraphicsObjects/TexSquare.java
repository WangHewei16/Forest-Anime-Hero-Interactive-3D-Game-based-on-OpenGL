package GraphicsObjects;

import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

/**
 * A class to implement Square object
 * 
 * Purpose and details:
 * Taking advantage of a for loop with two angles and two points, two vectors to get the norm and draw circle based on it.
 *  
 * @author Wang Hewei
 */
public class TexSquare {
	// basic colours
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
	
	public TexSquare() {
		
	}
	
	public void DrawTexSquare(Texture texture) {
		Point4f vertices[] = {  
			new Point4f(0.0f, 0.0f, 0.0f, 0.0f), 
			new Point4f(0.0f, 2.0f, 0.0f, 0.0f),
			new Point4f(1.0f, 2.0f, 0.0f, 0.0f), 
			new Point4f(1.0f, 0.0f, 0.0f, 0.0f),
		};
	int faces[][] = {{ 0, 1, 2, 3 }};
	GL11.glEnable(GL11.GL_TEXTURE_2D);
	for (int face = 0; face < 1; face++) { 
		texture.bind(); // per face set the texture picture
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(white[0], white[1], white[2]);
		Vector4f vector1 = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
		Vector4f vector2 = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
		Vector4f norm = vector1.cross(vector2).Normal();
		GL11.glNormal3f(norm.x, norm.y, norm.z);
		//set the Corresponding points for texture 
		GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
		GL11.glTexCoord2f(5,0);
		//set the Corresponding points for texture 
		GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
		GL11.glTexCoord2f(0,0); 
		//set the Corresponding points for texture 
		GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
		GL11.glTexCoord2f(0,5);
		//set the Corresponding points for texture 
		GL11.glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
		GL11.glTexCoord2f(5,5);
		GL11.glEnd();
		//GL11.
		} // per face
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	 }
}
