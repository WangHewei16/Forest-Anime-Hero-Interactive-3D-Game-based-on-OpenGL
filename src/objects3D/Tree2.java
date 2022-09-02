package objects3D;

import GraphicsObjects.Point4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
/**
 * A class to implement Tree2 object
 * 
 * Purpose and details:
 * Taking advantage of some TexCubes and TexSpheres to draw the second kind of tree.
 * Tree will have the shadow below it.
 * 
 * @author Wang Hewei
 */

public class Tree2 {
    private TexCube root;
    Texture texture1,texture2;
    private TexSphere leaf1;
    private TexSphere leaf2;
    private TexSphere shadow;
    private TexSphere leaf3;
    public Point4f pos;
    public float rotation;
    public Tree2(Texture texture1, Texture texture2, Point4f pos){
        this.texture1 = texture1;
        this.texture2 = texture2;
        leaf1 =  new TexSphere();
        leaf2 =  new TexSphere();
        leaf3 =  new TexSphere();
        shadow =  new TexSphere();
        root =  new TexCube();
        this.pos = pos;
    }
    public void Draw(){
        GL11.glPushMatrix();

        if(pos.x % 2 == 0){
            GL11.glTranslatef(pos.x, pos.y-30, pos.z);
            GL11.glScalef(120f, 10f, 120f);
        }else{
            GL11.glTranslatef(pos.x, pos.y-10, pos.z);
            GL11.glScalef(80f, 10f, 80f);
        }

        Color.black.bind();
        texture1.bind();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        // leaf1.DrawTexCube(texture1);
        shadow.DrawTexSphere(1,23,23,texture1);
        GL11.glPopMatrix();

        GL11.glPushMatrix();

        if(pos.x % 2 == 0){
            GL11.glTranslatef(pos.x, pos.y+200, pos.z);
            GL11.glScalef(120f, 120f, 120f);
        }else{
            GL11.glTranslatef(pos.x, pos.y+100, pos.z);
            GL11.glScalef(80f, 80f, 80f);
        }

        Color.white.bind();
        texture1.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
       // leaf1.DrawTexCube(texture1);
        leaf1.DrawTexSphere(1,10,10,texture1);
        GL11.glPopMatrix();

        GL11.glPushMatrix();

        if(pos.x % 2 == 0){
            GL11.glTranslatef(pos.x, pos.y+300, pos.z);
            GL11.glRotatef(90, 0, 0, 1);
            GL11.glScalef(110f, 110f, 110f);
        }else{
            GL11.glTranslatef(pos.x, pos.y+200, pos.z);
            GL11.glRotatef(90, 0, 0, 1);
            GL11.glScalef(80f, 80f, 80f);
        }

        Color.white.bind();
        texture1.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        // leaf1.DrawTexCube(texture1);
        leaf2.DrawTexSphere(1,10,10,texture1);
        GL11.glPopMatrix();

        GL11.glPushMatrix();

        if(pos.x % 2 == 0){
            GL11.glTranslatef(pos.x, pos.y+400, pos.z);
            GL11.glScalef(100f, 100f, 100f);
        }else{
            GL11.glTranslatef(pos.x, pos.y+300, pos.z);
            GL11.glScalef(80f, 80f, 80f);
        }

        Color.white.bind();
        texture1.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        // leaf1.DrawTexCube(texture1);
        leaf3.DrawTexSphere(1,10,10,texture1);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(pos.x, pos.y+40, pos.z);
        if(pos.x % 2 == 0){
            GL11.glScalef(20f, 80f, 20f);
        }else{

            GL11.glScalef(10f, 60f, 10f);
        }


        Color.white.bind();
        texture2.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        root.DrawTexCube(texture2);
        GL11.glPopMatrix();
    }
}
