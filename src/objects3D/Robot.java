package objects3D;

import GraphicsObjects.Point4f;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.util.LinkedList;

import static java.lang.Math.cos;
/**
 * A class to implement big robot object
 *
 * Purpose and details:
 * Taking advantage of some TexCubes to draw this robot.
 * Set the time and use for loop so that the robot can automatically swing his arm and march on the spot.
 *
 * @author Wang Hewei
 */
public class Robot {
    LinkedList<TexCube> cubes = new LinkedList<TexCube>();
    Texture[] textures;

    public Point4f pos;
    public Robot(Texture[] textures, Point4f pos){
        this.textures = textures;
        TexCube head = new TexCube();
        TexCube mouth = new TexCube();
        TexCube body = new TexCube();
        TexCube eye = new TexCube();
        TexCube leftJoin = new TexCube();
        TexCube rightJoin = new TexCube();
        TexCube leftLeg = new TexCube();
        TexCube rightLeg = new TexCube();
        TexCube eyeLeft = new TexCube();

        //draw_head
        head.setScale(1.5f,1.5f,1.5f);
        head.setPosition(0.0f, 2.0f, 0.0f);
        cubes.add(head);

        //draw_eye
        eye.setPosition(0.8f, 2.2f, -1.5f);
        eye.setScale(0.2f, 0.2f, 0.2f);
        cubes.add(eye);
        eyeLeft.setPosition(-0.8f, 2.2f, -1.5f);
        eyeLeft.setScale(0.2f, 0.2f, 0.2f);
        cubes.add(eyeLeft);
        mouth.setPosition(0.0f, 1.2f, -1.5f);
        mouth.setScale(0.2f, 0.2f, 0.2f);
        cubes.add(mouth);
        
        //body
        body.setPosition(-.0f, .0f, .0f);
        body.setScale(1f, 2.0f, 1f);

        cubes.add(body);
        leftJoin.setPosition(1.5f, .0f, .0f);
        leftJoin.setScale(1.5f, 0.3f, 0.3f);

        cubes.add(leftJoin);
        rightJoin.setPosition(-1.5f, .0f, .0f);
        rightJoin.setScale(1.5f, 0.3f, 0.3f);

        cubes.add(rightJoin);
        leftLeg.setPosition(.5f, -3.5f, .0f);
        leftLeg.setScale(0.3f, 1.5f, 0.3f);

        cubes.add(leftLeg);
        rightLeg.setPosition(-.5f, -3.5f, .0f);
        rightLeg.setScale(0.3f, 1.5f, 0.3f);
        cubes.add(rightLeg);
        this.pos = pos;
    }
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    long lastTime = getTime();
    long startTime = getTime();
    public void Draw(boolean hello){
        long thisTime = getTime();
        long myDelta = getTime() - lastTime;
        long myDelta2 = getTime() - startTime;
        float delta = ((float) myDelta2) / 8000;

        // code to aid in animation
        float theta = (float) (delta * 20 * Math.PI);
        float thetaDeg = delta * 360;
        float diff = ((float) myDelta) / 1000;

        for (int i = 0; i < cubes.size(); i++) {
            GL11.glPushMatrix();
            TexCube s = (TexCube) cubes.get(i);
            if(diff > 0.1 && i == 6){
                //tmp.rotateY(angle);
                if (!hello){
                    s.fradius = (float) cos(theta) * 0.5f;
                }
                else {
                    s.fradius = (float) cos(theta * 3) * 0.8f;
                }
                s.setAngle(  new Vector3f(0,0,1));
                lastTime = thisTime;
            }
            if(diff > 0.1 && i == 5){
                //tmp.rotateY(angle);
                if (!hello){
                    s.fradius = (float) cos(theta) * 0.5f;
                }
                else {
                    s.fradius = (float) cos(theta * 3) * 0.8f;
                }
                s.setAngle(  new Vector3f(0,0,-1));
                lastTime = thisTime;
            }
            if(diff > 0.1 && i == 7){
                //tmp.rotateY(angle);
                if (!hello){
                    s.fradius = (float) cos(theta) * 0.5f;
                }
                else {
                    s.fradius = (float) cos(theta * 3) * 0.8f;
                }
                s.setAngle(  new Vector3f(1,0,0));
                lastTime = thisTime;
            }
            if(diff > 0.1 && i > 7){
                //tmp.rotateY(angle);
                if (!hello){
                    s.fradius = (float) cos(theta) * 0.5f;
                }
                else {
                    s.fradius = (float) cos(theta * 3) * 0.8f;
                }
                s.setAngle(  new Vector3f(-1,0,0));
                lastTime = thisTime;
            }
            int f = 100;
            int sf = 100;
            int af = 100;

            GL11.glTranslatef(s.position.x*f, pos.y + s.position.y*f, s.position.z*f);
            GL11.glRotatef(s.fradius*25, s.angle.x*af, s.angle.y*af, s.angle.z*af);
            GL11.glScalef(s.scale.x*sf, s.scale.y*sf, s.scale.z*sf);
            Color.white.bind();
            textures[i].bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
            s.DrawTexCube(textures[i]);
            GL11.glPopMatrix();
        }
    }
}
