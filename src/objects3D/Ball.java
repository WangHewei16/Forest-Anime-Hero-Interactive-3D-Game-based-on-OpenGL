package objects3D;

import GraphicsObjects.Point4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Ball {
    private TexCube rope;
    private TexCube root;

    Texture texture1, texture2, texture3, texture4;
    private TexSphere leaf1;
    private TexSphere leaf2;
    private TexSphere leaf3;
    public Point4f pos;

    public Ball(Texture texture1, Texture texture2, Texture texture3, Texture texture4, Point4f pos) {
        this.texture1 = texture1;
        this.texture2 = texture2;
        this.texture3 = texture3;
        this.texture4 = texture4;
        leaf1 = new TexSphere();
        leaf2 = new TexSphere();
        leaf3 = new TexSphere();
        rope = new TexCube();
        root = new TexCube();
        this.pos = pos;
    }

    public void Draw() {
        GL11.glPushMatrix();
        GL11.glTranslatef(pos.x + 200, pos.y + 700, pos.z);
        GL11.glScalef(140f, 140f, 140f);
        Color.white.bind();
        texture1.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        // leaf1.DrawTexCube(texture1);
        leaf1.DrawTexSphere(1, 23, 23, texture1);
        GL11.glPopMatrix();

        GL11.glPushMatrix();


        GL11.glTranslatef(pos.x, pos.y + 750, pos.z);

        GL11.glScalef(190f, 190f, 190f);

        Color.white.bind();
        texture2.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        // leaf1.DrawTexCube(texture1);
        leaf2.DrawTexSphere(1, 23, 23, texture2);
        GL11.glPopMatrix();

        GL11.glPushMatrix();


        GL11.glTranslatef(pos.x - 200, pos.y + 700, pos.z);
        GL11.glScalef(140f, 140f, 140f);

        Color.white.bind();
        texture3.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        // leaf1.DrawTexCube(texture1);
        leaf3.DrawTexSphere(1, 23, 23, texture3);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(pos.x, pos.y + 280, pos.z);
        GL11.glScalef(5f, 300f, 5f);


        Color.white.bind();
        texture4.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        rope.DrawTexCube(texture4);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(pos.x, pos.y + 5, pos.z);
        GL11.glScalef(150f, 30f, 150f);


        Color.white.bind();
        texture4.bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        root.DrawTexCube(texture4);
        GL11.glPopMatrix();
    }
}
