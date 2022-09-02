package objects3D;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;

/**
 * A class to implement Human object
 * 
 * 
 * Purpose and details:
 * I achieve a humanoid character who has left/right chest, neck, shoulder, arm, elbow, forearm, hand
 * I use design some algorithm and adjust some parameters to make humanoid character has better the animation such as some parameters related to rotates 
 * In addition, I add the left and right eye for the human
 * 
 * @author Wang Hewei
 */

public class Human {

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

	public Human() {

	}

	public void DrawHuman(float delta, int animation,Texture[] texture, float process) {
		float theta = (float) (process * 2 * Math.PI)*5;
		float LimbRotation = 0;
		float TallerRotation = 0;
		float FrontRotation = 0;
//		if (GoodAnimation) {
//			LimbRotation = (float) Math.cos(theta) * 45;
//		} else {
//			LimbRotation = 0;
//		}
		switch (animation) {
			case 0:
				LimbRotation = 0;
				break;
			case 1:
				LimbRotation = (float) Math.cos(theta) * 45;
				break;
			case 2:
				TallerRotation = - (float) Math.cos(theta) * 50;
				FrontRotation = (float) (1.3 * TallerRotation);
				break;
			case 3:
				float f = (float) Math.sin(theta) * 180;
				if (f <= 120) {
					TallerRotation = f;
					FrontRotation = TallerRotation * 0.6f;
				}
				else {
					TallerRotation = 120;
					FrontRotation = 72 - 70 * (f-130)/50;
				}
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		texture[0].bind();
		TexSphere sphere = new TexSphere();
		TexCylinder cylinder = new TexCylinder();
		GL11.glPushMatrix();
			{
				GL11.glTranslatef(0.0f, 0.5f, 0.0f);
				sphere.DrawTexSphere(0.5f, 32, 32,texture[2]);

				// chest
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
				GL11.glPushMatrix();
				{
					GL11.glTranslatef(0.0f, 0.5f, 0.0f);
					sphere.DrawTexSphere(0.5f, 32, 32,texture[3]);
	
					// neck
					GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					GL11.glPushMatrix();
					{
						GL11.glTranslatef(0.0f, 0.0f, 0.0f);
						GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
						cylinder.DrawCylinder(0.15f, 0.7f, 32,texture[4]);
	
						// head
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.0f, 0.0f, 1.0f);
							sphere.DrawTexSphere(0.5f, 32, 32,texture[2]);
							GL11.glPopMatrix();
							GL11.glPushMatrix();
							{
								// left eye
								GL11.glTranslatef(0.24f, 0.35f, 1.1f);
								sphere.DrawTexSphere(0.1f, 32, 32,texture[5]);
								
							}
							GL11.glPopMatrix();
							
							GL11.glPushMatrix();
							{
								// right eye
								GL11.glTranslatef(-0.24f, 0.35f, 1.1f);
								sphere.DrawTexSphere(0.1f, 32, 32,texture[5]);
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
	
						// left shoulder
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.5f, 0.4f, 0.0f);
							sphere.DrawTexSphere(0.25f, 32, 32,texture[4]);
	
							// left arm
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							GL11.glPushMatrix();
							{
								GL11.glTranslatef(0.0f, 0.0f, 0.0f);
								GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
								GL11.glRotatef(18.0f,0.0f,1.0f,0.0f);
								if (animation < 2){
									GL11.glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
								}
								else if (animation == 2){
									GL11.glRotatef(TallerRotation, 1.0f, 0.0f, 0.0f);
								}
								else if (animation == 3) {
									GL11.glRotatef(TallerRotation, 0f, 1.0f, 0.0f);
								}
								cylinder.DrawCylinder(0.15f, 0.7f, 32,texture[3]);
	
								// left elbow
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								GL11.glPushMatrix();
								{
									GL11.glTranslatef(0.0f, 0.0f, 0.75f);
									sphere.DrawTexSphere(0.2f, 32, 32,texture[4]);
	
									// left forearm
									GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
									GL11.glPushMatrix();
									{
										GL11.glTranslatef(0.0f, 0.0f, 0.0f);
										GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
										if (animation < 2){
											GL11.glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
										}
										else if (animation == 2){
											GL11.glRotatef(FrontRotation, 1.0f, 0.0f, 0.0f);
										}
										else if (animation == 3) {
											GL11.glRotatef(FrontRotation, 0f, 1.0f, 0.0f);
										}
										cylinder.DrawCylinder(0.1f, 0.7f, 32,texture[4]);
	
										// left hand
										GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
										GL11.glPushMatrix();
										{
											GL11.glTranslatef(0.0f, 0.0f, 0.75f);
											sphere.DrawTexSphere(0.2f, 32, 32,texture[5]);
											GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
											
										}
										GL11.glPopMatrix();
									}
									GL11.glPopMatrix();
								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
						// to chest
	
						// right shoulder
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(-0.5f, 0.4f, 0.0f);
							sphere.DrawTexSphere(0.25f, 32, 32,texture[4]);
	
							// right arm							
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							GL11.glPushMatrix();
							{
								GL11.glTranslatef(0.0f, 0.0f, 0.0f);
								GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
								GL11.glRotatef(-18.0f, 0.0f, 1.0f, 0.0f);
								if (animation < 2){
									GL11.glRotatef(LimbRotation, -1.0f, 0.0f, 0.0f);
								}
								else if (animation == 2){
									GL11.glRotatef(TallerRotation, 1.0f, 0.0f, 0.0f);
								}
								cylinder.DrawCylinder(0.15f, 0.7f, 32,texture[3]);
	
								// right elbow
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								GL11.glPushMatrix();
								{
									GL11.glTranslatef(0.0f, 0.0f, 0.75f);
									sphere.DrawTexSphere(0.2f, 32, 32,texture[3]);
	
									// right forearm
									GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
									GL11.glPushMatrix();
									{
										GL11.glTranslatef(0.0f, 0.0f, 0.0f);
										GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
										if (animation < 2){
											GL11.glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
										}
										else if (animation == 2){
											GL11.glRotatef(FrontRotation, 1.0f, 0.0f, 0.0f);
										}
										cylinder.DrawCylinder(0.1f, 0.7f, 32,texture[3]);
	
										// right hand
										GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
										GL11.glPushMatrix();
										{
											GL11.glTranslatef(0.0f, 0.0f, 0.75f);
											sphere.DrawTexSphere(0.2f, 32, 32,texture[5]);
											GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
										}
										GL11.glPopMatrix();
									}
									GL11.glPopMatrix();
								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
						// chest
	
					}
					GL11.glPopMatrix();
	
					// pelvis
	
					// right hip
					GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					GL11.glPushMatrix();
					{
						GL11.glTranslatef(-0.5f, -0.2f, 0.0f);
	
						sphere.DrawTexSphere(0.25f, 32, 32,texture[1]);
	
						// right high leg
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.0f, 0.0f, 0.0f);
							GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
	
							GL11.glRotatef((LimbRotation) + 90, 1.0f, 0.0f, 0.0f);
							cylinder.DrawCylinder(0.15f, 0.7f, 32,texture[2]);
	
							// right knee
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							GL11.glPushMatrix();
							{
								GL11.glTranslatef(0.0f, 0.0f, 0.75f);
								// let the legs bend differently in different positions
								GL11.glRotatef(LimbRotation<0 ? 0 : -LimbRotation*2, 1.0f, 0.0f, 0.0f);
								sphere.DrawTexSphere(0.25f, 32, 32,texture[0]);
	
								// right low leg
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								GL11.glPushMatrix();
								{
									GL11.glTranslatef(0.0f, 0.0f, 0.0f);
									cylinder.DrawCylinder(0.15f, 0.7f, 32,texture[2]);
	
									// right foot
									GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									GL11.glPushMatrix();
									{
										GL11.glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.DrawTexSphere(0.3f, 32, 32,texture[4]);
	
									}
									GL11.glPopMatrix();
								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
					}
					GL11.glPopMatrix();
	
					// pelvis
	
					// left hip
					GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					GL11.glPushMatrix();
					{
						GL11.glTranslatef(0.5f, -0.2f, 0.0f);
	
						sphere.DrawTexSphere(0.25f, 32, 32,texture[0]);
	
						// left high leg
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.0f, 0.0f, 0.0f);
							GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							GL11.glRotatef((-LimbRotation) + 90, 1.0f, 0.0f, 0.0f);
							cylinder.DrawCylinder(0.15f, 0.7f, 32,texture[2]);
	
							// left knee
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							GL11.glPushMatrix();
							{
								GL11.glTranslatef(0.0f, 0.0f, 0.75f);
								// let the legs bend differently in different positions
								GL11.glRotatef(-LimbRotation<0 ? 0 : (LimbRotation)*2, 1.0f, 0.0f, 0.0f);
								sphere.DrawTexSphere(0.25f, 32, 32,texture[0]);
	
								// left low leg
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								GL11.glPushMatrix();
								{
									GL11.glTranslatef(0.0f, 0.0f, 0.0f);
									cylinder.DrawCylinder(0.15f, 0.7f, 32,texture[1]);
	
									// left foot
									GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									GL11.glPushMatrix();
									{
										GL11.glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.DrawTexSphere(0.3f, 32, 32,texture[4]);
	
									}
									GL11.glPopMatrix();
								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
					}
					GL11.glPopMatrix();
					// left hip
				}
				GL11.glPopMatrix();
			}
			GL11.glDisable(GL11.GL_TEXTURE_2D);
		}
}