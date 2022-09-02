package objects3D;

import org.lwjgl.opengl.GL11;

/**
 * A class to implement Sphere object
 * 
 * OpenGL Method being used:  GL11.glNormal3f(), GL11.glVertex3f(), GL11.glBegin(GL11.GL_QUADS), GL11.glEnd()
 * 
 * 
 * Purpose and details:
 * Taking advantage of loop to draw the color and shadow for each face. 
 * Each face lies on a plane, so we can compute a normal and using GL11.glNormal3f() method in OpenGL for reflecting light.
 * 
 * @author Wang Hewei
 */

public class Sphere {

	public Sphere() {
		
	}

	// Implement using notes and examine Tetrahedron to aid in the coding look at lecture 7, 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawSphere(float radius, float nSlices, float nSegments) {
		
		// Using Quads, so it needs to set up our increments for the parametric equations
		// Using the nSlices and nSegments instances to judge the smallest segment's value
		float inctheta = (float) ((2.0f * Math.PI) / nSlices); 
		float incphi = (float) (Math.PI / nSegments);
		GL11.glBegin(GL11.GL_QUADS);
		
		// Using two loops in order to build up our sphere
		// Outer loop to get the longitude angle
		for(float theta=(float) -Math.PI; theta<Math.PI; theta+=inctheta){ 
			// Inner loop to get the latitude angle
			for(float phi=(float) -(Math.PI/2.0f); phi<(Math.PI/2.0f); phi+=incphi) {
				// Plus the minimum unit of latitude angle and the minimum unit of longitude angle to the original angle for each time
				float angleLongitudeAngle = theta + inctheta;
				float angleLatitudeAngle = phi + incphi;
				
				// If changed to a vector, every point on our sphere is own normal
				// To make the sphere looks smoother, we use GL11.glNormal3f() before GL11.glVertex3f() each time
				// Using rectangles to fill the sphere
				GL11.glNormal3f((float) (radius * Math.cos(phi) * Math.cos(theta)), (float) (radius * Math.cos(phi) * Math.sin(theta)), (float) ((float) radius * Math.sin(phi)));
				GL11.glVertex3f((float) (radius * Math.cos(phi) * Math.cos(theta)), (float) (radius * Math.cos(phi) * Math.sin(theta)), (float) ((float) radius * Math.sin(phi)));
				GL11.glNormal3f((float) (radius * Math.cos(phi) * Math.cos(angleLongitudeAngle)), (float) (radius * Math.cos(phi) * Math.sin(angleLongitudeAngle)), (float) ((float) radius * Math.sin(phi)));
				GL11.glVertex3f((float) (radius * Math.cos(phi) * Math.cos(angleLongitudeAngle)), (float) (radius * Math.cos(phi) * Math.sin(angleLongitudeAngle)), (float) ((float) radius * Math.sin(phi)));
				GL11.glNormal3f((float) (radius * Math.cos(angleLatitudeAngle) * Math.cos(angleLongitudeAngle)), (float) (radius * Math.cos(angleLatitudeAngle) * Math.sin(angleLongitudeAngle)), (float) ((float) radius * Math.sin(angleLatitudeAngle)));
				GL11.glVertex3f((float) (radius * Math.cos(angleLatitudeAngle) * Math.cos(angleLongitudeAngle)), (float) (radius * Math.cos(angleLatitudeAngle) * Math.sin(angleLongitudeAngle)), (float) ((float) radius * Math.sin(angleLatitudeAngle)));
				GL11.glNormal3f((float) (radius * Math.cos(angleLatitudeAngle) * Math.cos(theta)), (float) (radius * Math.cos(angleLatitudeAngle) * Math.sin(theta)), (float) ((float) radius * Math.sin(angleLatitudeAngle)));
				GL11.glVertex3f((float) (radius * Math.cos(angleLatitudeAngle) * Math.cos(theta)), (float) (radius * Math.cos(angleLatitudeAngle) * Math.sin(theta)), (float) ((float) radius * Math.sin(angleLatitudeAngle)));
			}
		}
		GL11.glEnd();
	}
}

 