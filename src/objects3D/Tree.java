package objects3D;

import GraphicsObjects.Point4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 * A class to implement Tree object
 * 
 * Purpose and details:
 * Taking advantage of some TexCubes and TexSphere to draw the first kind of tree.
 * Tree will have the shadow below it.
 * 
 * @author Wang Hewei
 */

public class Tree {
    private TexCube leaf;
    Texture texture1,texture2;
    private TexCube root;
    private TexSphere shadow;
    public Point4f pos;
    public Tree(Texture texture1,Texture texture2,Point4f pos){
        this.texture1 = texture1;
        this.texture2 = texture2;
        root =  new TexCube();
        shadow =  new TexSphere();
        leaf =  new TexCube();
        this.pos = pos;
    }
    public void Draw(){
        GL11.glPushMatrix();

        GL11.glTranslatef(pos.x, pos.y-20, pos.z);
        if(pos.x % 2 == 0){

            GL11.glScalef(60f, 10f, 60f);
        }else{
            GL11.glScalef(40f, 10f, 40f);
        }
        Color.black.bind();
        texture1.bind();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        // leaf1.DrawTexCube(texture1);
        shadow.DrawTexSphere(1,10,10,texture1);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(pos.x, pos.y+100, pos.z);
        if(pos.x % 2 == 0){
            GL11.glScalef(60f, 60f, 60f);
        }else{
            GL11.glScalef(40f, 40f, 40f);
        }
        Color.white.bind();
        texture1.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        root.DrawTexCube(texture1);
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
        leaf.DrawTexCube(texture2);
        GL11.glPopMatrix();
    }
}
