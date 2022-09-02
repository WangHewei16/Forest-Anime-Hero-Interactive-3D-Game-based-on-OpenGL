package Functions;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.*;

import GraphicsObjects.Point4f;
import GraphicsObjects.TexSquare;
import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

// Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// This class also set the track and condition of the human movement.

// Do not touch this class, I will be making a version of it for your 3rd Assignment 

public class MainWindow {
	
    private boolean MouseOnepressed = true;
    private boolean dragMode = false;
    private boolean BadAnimation = true;
    private boolean Earth = false;
    private boolean struckA = false;
    private boolean struckD = false;
    private boolean struckW = false;
    private boolean struckS = false;
    private int directionNumber = 1;
    int humanAnimation = 0;
    /**
     * position of pointer
     */
    float x = 400, y = 300;
    /**
     * angle of rotation
     */
    float rotation = 0;
    /**
     * time at last frame
     */
    long lastFrame;
    /**
     * frames per second
     */
    int fps;
    /**
     * last fps time
     */
    long lastFPS;
    long myDelta = 0; // to use for animation
    float Alpha = 0; // to use for animation
    long StartTime; // beginAnimiation
    float flowerHeight = 0;
    boolean flowerTaller = false;
    Arcball MyArcball = new Arcball();
    boolean DRAWGRID = false;
    boolean waitForKeyrelease = true;
    float process;
    float helloProcess = 0;
    boolean hello = false;
    /**
     * Mouse movement
     */
    int LastMouseX = -1;
    int LastMouseY = -1;

    float pullX = 0.0f; // arc ball  X cord.
    float pullY = 0.0f; // arc ball  Y cord.

    int OrthoNumber = 2400; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project
    int LeftOrthoNumber = 2180;
    
    // basic colours
    static float black[] = {0.0f, 0.0f, 0.0f, 1.0f};
    static float white[] = {1.0f, 1.0f, 1.0f, 1.0f};

    static float grey[] = {0.5f, 0.5f, 0.5f, 1.0f};
    static float spot[] = {0.1f, 0.1f, 0.1f, 0.5f};

    // primary colours
    static float red[] = {1.0f, 0.0f, 0.0f, 1.0f};
    static float green[] = {0.0f, 1.0f, 0.0f, 1.0f};
    static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};

    // secondary colours
    static float yellow[] = {1.0f, 1.0f, 0.0f, 1.0f};
    static float magenta[] = {1.0f, 0.0f, 1.0f, 1.0f};
    static float cyan[] = {0.0f, 1.0f, 1.0f, 1.0f};

    // other colours
    static float orange[] = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float brown[] = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float dkgreen[] = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float pink[] = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};

    // static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};
    // support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process

    Point4f humPos = new Point4f();
    Point4f robotPos = new Point4f(500, 880f, 400, 0);
    Point4f robotPos2 = new Point4f(2000, 880f, -500, 0);
    Point4f robotPos3 = new Point4f(-1000, 880f, -500, 0);
    float ballY = 0;
    boolean onBall = false;
    Set cuttedObjects = new HashSet();
    Set cutted2Objects = new HashSet();
    ArrayList treePoints = new ArrayList();
    public boolean hitAt(Point4f humPos) {
        float x = humPos.x;
        float x2 =  (humPos.x);
        float z = humPos.z;
        float y2 =  (humPos.z);
        boolean canrun = true;
        double smallSt = 9999;
        int hit = -1;
        for (int i = 0; i < 20; i++) {
            Point4f p = (Point4f)trees2[i].pos;
            double x1 = (p.x);
            double y1 = (p.z);

            Double distance = Math.pow((Math.pow((x2-x1),2)+Math.pow((y2-y1),2)),0.5);
            if(distance<smallSt){
                smallSt = distance;
                hit = i;
            }
        }
        if(smallSt<300){
            cutted2Objects.add(hit);
            //cuttedObjects.add(hit);
        }
        smallSt = 9999;
        hit = -1;
        for (int i = 0; i < 20; i++) {
            Point4f p = (Point4f)trees[i].pos;
            double x1 =  (p.x);
            double y1 =  (p.z);
            Double distance=Math.pow((Math.pow((x2-x1),2)+Math.pow((y2-y1),2)),0.5);
            if(distance<smallSt){
                smallSt = distance;
                hit = i;
            }
        }
        if(smallSt<300){
            cuttedObjects.add(hit);
            //cuttedObjects.add(hit);
        }else{

        }

        if(x>=-560 && x<=-430 && z<=110 && z>=-50){
            cuttedObjects.add(1);
            System.out.println(1);
        }
        if(x>=600 && x<=990 && z<=170 && z>=-150){
            System.out.println(2);
            cuttedObjects.add(2);
        }
        if(x>=-1040 && x<=-920 && z<=280 && z>=150 ){
            System.out.println(3);
            cuttedObjects.add(3);
        }
        if(x>=-260 && x<=-180 && z<=410 && z>=330){
            System.out.println(4);
            cuttedObjects.add(4);
        }
        if(x>=-1450 && x<=-1160 && z<=-350 && z>=-640){
            System.out.println(5);
            cuttedObjects.add(5);
        }
        if(x>=1550 && x<=1850 && z<=-380 && z>=-630){
            System.out.println(6);
            cuttedObjects.add(6);
        }
        if(x>=150 && x<=250 && z<=-450 && z>=-550){
            System.out.println(7);
            cuttedObjects.add(7);
        }
        cutTreeHit = 0;
        return canrun;
    }
    public boolean hit(Point4f humPos) {
		float x = humPos.x;
		float z = humPos.z;
		boolean canrun = true;
		if(
//				detect whether hit on the barrier
				x>=-560 && x<=-430 && z<=110 && z>=-50 ||
				x>=600 && x<=990 && z<=170 && z>=-150 ||
				x>=-1040 && x<=-920 && z<=280 && z>=150 ||
				x>=-260 && x<=-180 && z<=410 && z>=330 ||
				x>=-1450 && x<=-1160 && z<=-350 && z>=-640 ||
				x>=1550 && x<=1850 && z<=-380 && z>=-630 ||
				x>=150 && x<=250 && z<=-450 && z>=-550  ||
				ballY>0 && x>=40 && x<=360 && z<=-340 && z>=-650 && onBall == false
		) {
			canrun = false;
		}
			
	return canrun;
	}
    
    public void start() throws IOException {
        StartTime = getTime();
        try {
            Display.setDisplayMode(new DisplayMode(1200, 800));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            update(delta);
            renderGL();
            Display.update();
            Display.sync(120); // cap fps to 120fps
        }
        Display.destroy();
    }
    private boolean cutTree = false;
    private int cutTreeHit = 0;

    public void update(int delta) {
        // rotate quad
        // rotation += 0.01f * delta;
        int MouseX = Mouse.getX();
        int MouseY = Mouse.getY();
        int WheelPostion = Mouse.getDWheel();
        boolean MouseButonPressed = Mouse.isButtonDown(0);
        //System.out.println("( x: "+humPos.x + ", z: " + humPos.z +" )"+ballY);
        if (MouseButonPressed && !MouseOnepressed) {
            MouseOnepressed = true;
            // System.out.println("Mouse drag mode");
            MyArcball.startBall(MouseX, MouseY, 1200, 800);
            dragMode = true;
        } else if (!MouseButonPressed) {
            // System.out.println("Mouse drag mode end ");
            MouseOnepressed = false;
            dragMode = false;
        }

        if (dragMode) {
            MyArcball.updateBall(MouseX, MouseY, 1200, 800);
        }

        if (WheelPostion > 0) {
            OrthoNumber += 10;
        }

        if (WheelPostion < 0) {
            OrthoNumber -= 10;
            if (OrthoNumber < 610) {
                OrthoNumber = 610;
            }
            //  System.out.println("Orth nubmer = " +  OrthoNumber);
        }

//        /** rest key is R*/
//        if (Keyboard.isKeyDown(Keyboard.KEY_R))
//            MyArcball.reset();

        /* bad animation can be turn on or off using A key)*/
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            robotPos.z += 10;
            robotPos.a = -180;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            robotPos.z -= 10;
            robotPos.a = 0;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
        	robotPos.x += 10;
            robotPos.a = -90;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
        	robotPos.x -= 10;
            robotPos.a = 90;
        }
        // x += 0.35f * delta;
        if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
        	humanAnimation = 3;
        	cutTreeHit+=1;
            if(cutTreeHit>10){
                cutTree = true;
            }
            //humPos.a = -180;
            directionNumber = 3;
            if(struckS) {
                struckS = false;
                humPos.z += 10;
            }
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                    public void run() {
                        System.out.println("11232");
                    }
            }, 200000 , 1000);
            hitAt(humPos);
        }
        // x += 0.35f * delta;
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
        	humanAnimation = 1;
        	humPos.a = -180;
            directionNumber = 3;
            process += 0.005f * delta / 50;
            if(struckS) {
        		struckS = false;
        		humPos.z += 10;
        	}
            if(hit(humPos)) {
            	humPos.z += 10;
                //OrthoNumber+=9;
        	}else {
        		//humPos.z -= 30;
        		struckW = true;
        	}
        }
        //  y += 0.35f * delta;
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
        	humanAnimation = 1;
        	humPos.a = 0;
        	directionNumber = 1;
            process += 0.005f * delta / 50;
        	if(struckW) {
        		struckW = false;
        		humPos.z -= 10;
        	}
        	if(hit(humPos)) {
        		humPos.z -= 10;
                //OrthoNumber-=3;
        	} else {
        		//humPos.z += 30;
        		struckS = true;
        	}
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A) ) {
        	humanAnimation = 1;
        	humPos.a = -90;
        	directionNumber = 2;
            process += 0.005f * delta / 50;
        	if(struckA) {
        		struckA = false;
        		humPos.x += 10;
        	}
        	if(hit(humPos)) {
        		humPos.x += 10;
            	//LeftOrthoNumber-=3;
        	} else {
        		//humPos.x -= 35;
        		struckD = true;
        	}
        }
        // BadAnimation = !BadAnimation;
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
        	humanAnimation = 1;
        	humPos.a = 90;
        	directionNumber = 4;
            process += 0.005f * delta / 50;
        	if(struckD) {
        		struckD = false;
        		humPos.x -= 10;
        	}
        	if(hit(humPos)) { 
                //LeftOrthoNumber+=9;
                humPos.x -= 10;
        	} else {
        		//humPos.x += 35;
        		struckA = true;
        	}
        }
        //  y -= 0.35f * delta;
        if (Keyboard.isKeyDown(Keyboard.KEY_F) && ballY<500) {
            ballY += 10;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_H) && ballY>0) {
            ballY -= 10;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            rotation += 0.35f * delta;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            Earth = !Earth;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
            humanAnimation = 2;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
//            hello = true;
            humanAnimation = 3;
            robotPos.x += 10;
            robotPos.a = -90;
        }

        if (!(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D)) && humanAnimation == 1){
            process = 0;
            humanAnimation = 0;
        }

        if (humanAnimation == 2) {
            process += 0.005f * delta / 50;
            if (process > 0.2) {
                humanAnimation = 0;
                process = 0;
                flowerTaller = true;
            }
        }

        if (humanAnimation == 3) {
            process += 0.005f * delta / 50;
            if (process > 0.1) {
                hello = true;
                humanAnimation = 0;
                process = 0;
            }
        }

        if (hello && helloProcess < 0.3) {
            helloProcess += 0.005f * delta / 50;
            if (helloProcess > 0.3) {
                hello = false;
                helloProcess = 0;
            }
        }

        if (flowerTaller && flowerHeight < 5) {
            flowerHeight += 0.05f;
        }


//        if (waitForKeyrelease) // check done to see if key is released
//        {
//            if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
//                DRAWGRID = !DRAWGRID;
//                Keyboard.next();
//                if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
//                    waitForKeyrelease = true;
//                } else {
//                    waitForKeyrelease = false;
//                }
//            }
//        }
//
//        /** to check if key is released */
//        if (Keyboard.isKeyDown(Keyboard.KEY_G) == false) {
//            waitForKeyrelease = true;
//        } else {
//            waitForKeyrelease = false;
//        }


        // keep quad on the screen
        if (x < 0)
            x = 0;
        if (x > 1200)
            x = 1200;
        if (y < 0)
            y = 0;
        if (y > 800)
            y = 800;

        updateFPS(); // update FPS Counter

        LastMouseX = MouseX;
        LastMouseY = MouseY;
    }

    /**
     * Calculate how many milliseconds have passed since last frame.
     *
     * @return milliseconds passed since last frame
     */
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
        return delta;
    }

    /**
     * Get the accurate system time
     *
     * @return The system time in milliseconds
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    /**
     * Calculate the FPS and set it in the title bar
     */
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public void initGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        changeOrth();
        MyArcball.startBall(634, 346, 1200, 1200);
        MyArcball.updateBall(634, 346, 1200, 800);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
        lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

        FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
        lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

        FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
        lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

        FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
        lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();

        FloatBuffer lightPos5 = BufferUtils.createFloatBuffer(4);
        lightPos5.put(-200f).put(750f).put(0).put(0).flip();
        
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos); // specify the
        // position
        // of the
        // light
        GL11.glEnable(GL11.GL_LIGHT0); // switch light #0 on // I've setup specific materials so in real light it will look abit strange

        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos);   // specify the
        // position
        // of the
        // light
        GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));

        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3);  // specify
        // the
        // position
        // of the
        // light
        GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

        GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4);  // specify
        
        
        // the
        // position
        // of the
        // light
        GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
        GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

        //****
        GL11.glLight(GL11.GL_LIGHT4, GL11.GL_POSITION, lightPos5);  // specify
        GL11.glEnable(GL11.GL_LIGHT4); 
        //GL11.glLight(GL11.GL_LIGHT5, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));
        //****
        
        GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
        GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
        // on
        GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
       
        
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        } //load in texture
    }


    public void changeOrth() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(1200 - LeftOrthoNumber, OrthoNumber, (800 - (LeftOrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);
        MyArcball.getMatrix(CurrentMatrix);
        GL11.glLoadMatrix(CurrentMatrix);

        // Look At
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(60f, 1, 0.1f, 1000000);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GLU.gluLookAt(humPos.x, 2800, humPos.z-2300, humPos.x, 0, humPos.z, 0.0f, 1.0f, 0.0f);
    }

    private Tree[] trees;
    private Tree2[] trees2;
    Human MyHuman = new Human();
    Point4f lightPos = new Point4f(100,1500,100,0);

    /*
     * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load
     *
     */
    public void renderGL() {
        changeOrth();
        
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor3f(0.5f, 0.5f, 1.0f);

        myDelta = getTime() - StartTime;
        float delta = ((float) myDelta) / 8000;

        // code to aid in animation
        float theta = (float) (delta * 2 * Math.PI);
        float thetaDeg = delta * 360;
        float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
        float posn_y = (float) Math.sin(theta);
        
        /*
         *  This code draws a grid to help you view the human models movement
         *  You may change this code to move the grid around and change its starting angle as you please
         */
        
        if (DRAWGRID) {
            GL11.glPushMatrix();
            Grid MyGrid = new Grid();
            GL11.glTranslatef(600, 400, 0);
            GL11.glScalef(200f, 200f, 200f);
            MyGrid.DrawGrid();
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix(); {
        	// Step on hot air balloon platform
            if (humPos.x > 40 && humPos.x < 360 && humPos.z < -340 && humPos.z > -650 && ballY >= 0.0) {
            	onBall = true;
            	if(!(humPos.x > 40 && humPos.x < 360 && humPos.z < -340 && humPos.z > -650) && ballY > 0.0 || ballY == 0.0) {
                	onBall = false;
                }
                GL11.glTranslatef(300 + humPos.x, 535 + ballY+80, humPos.z);
            }else {
                GL11.glTranslatef(300 + humPos.x, 535, humPos.z);
            }
            GL11.glPushMatrix(); {
                GL11.glTranslatef(0, -125, 0);
                GL11.glScalef(120f, 10f, 120f);
                Color.black.bind();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                shadow.DrawTexSphere(1, 23, 23, texture);
            }
            GL11.glPopMatrix();
            GL11.glScalef(90f, 90f, 90f);
            GL11.glRotatef(humPos.a, 0, 1, 0);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
            Color.white.bind();
            texture.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
            MyHuman.DrawHuman(delta, humanAnimation, textures, process); // give a delta for the Human object ot be animated
        }
        GL11.glPopMatrix();

        lightPos.x = posn_x * 1000.0f;
        lightPos.z = posn_y * 1000.0f;

        //lightPos.z = (float) cos(getTime()) * 2000.0f;
        FloatBuffer lightPosfb = BufferUtils.createFloatBuffer(4);
        lightPosfb.put(lightPos.x).put(lightPos.y).put(lightPos.z).put(0).flip();
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosfb);

        GL11.glPushMatrix();
        GL11.glTranslatef(lightPos.x,lightPos.y,lightPos.z);

        // let the body of the human rotate, first let the human face the right direction before roate then, let it start to rotate
        GL11.glRotatef(180, 0, 1, 0);
        GL11.glRotatef(thetaDeg, 0.0f, -1.0f, 0.0f);

        GL11.glScalef(140f, 140f, 140f);
        sun.DrawTexSphere(1,32,32,textures[12]);
        GL11.glPopMatrix();

        GL11.glPushMatrix();{
            ball.pos.y = 440f + ballY;
            ball.Draw();
        }
        GL11.glPopMatrix();

        GL11.glPushMatrix();{
            GL11.glTranslatef(robotPos.x, 0, robotPos.z);
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 410, 0);
                GL11.glScalef(220f, 10f, 220f);
                Color.black.bind();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                shadow.DrawTexSphere(1, 23, 23, texture);
            }
            GL11.glPopMatrix();
            GL11.glRotatef(robotPos.a, 0, 1, 0);
            robot.Draw(hello);
        }
        GL11.glPopMatrix();

        
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(robotPos2.x, 0, robotPos2.z);


            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 410, 0);
                GL11.glScalef(220f, 10f, 220f);
                Color.black.bind();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                shadow.DrawTexSphere(1, 23, 23, texture);
            }
            GL11.glPopMatrix();

            GL11.glRotatef(robotPos2.a, 0, 1, 0);
            robot2.Draw(false);
        }
        GL11.glPopMatrix();

        
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(robotPos3.x, 0, robotPos3.z);
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 410, 0);
                GL11.glScalef(220f, 10f, 220f);
                Color.black.bind();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                shadow.DrawTexSphere(1, 23, 23, texture);
            }
            GL11.glPopMatrix();
            GL11.glRotatef(robotPos3.a, 0, 1, 0);
            robot3.Draw(false);
        }
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
		{
			GL11.glTranslatef(-200, 730, 0);
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glScalef(50f, 50f, 50f);

			if(!cuttedObjects.contains(1)){
                Lamp light = new Lamp();
                light.DrawStreetlight();
            }

		}
		GL11.glPopMatrix();
		// ----------------- pool -----------------
//		GL11.glPushMatrix();
//		{
//			GL11.glTranslatef(1700, 385, 100);
//			GL11.glRotatef(90, 0, 1, 0);
//			GL11.glScalef(50f, 50f, 50f);
//			Pool pool = new Pool();
//			pool.drawPool(texture, textures[11], delta);
//		}
//		GL11.glPopMatrix();
        //------------------------------------
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(1100, 385, 0);
			
			GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 25, 0);
                GL11.glScalef(80f, 10f, 80f);
                Color.black.bind();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                shadow.DrawTexSphere(1, 10, 10, texture);
            }
            GL11.glPopMatrix();
            Color.yellow.bind();
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glScalef(50f, 50f, 50f);
			Sunflower flower = new Sunflower();
			flower.drawFlower(textures[6], textures[13], delta, flowerHeight);
		}
		
		GL11.glPopMatrix();
		
		
		
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0, 385, 1300);
			
			GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 25, 0);
                GL11.glScalef(40f, 5f, 40f);
                Color.black.bind();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                shadow.DrawTexSphere(1, 10, 10, texture);
            }
            GL11.glPopMatrix();
            Color.yellow.bind();
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glScalef(30f, 30f, 30f);
			Sunflower flower = new Sunflower();
			flower.drawFlower(textures[6], textures[13], myDelta, 0);
		}
		GL11.glPopMatrix();
		
		
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(-1090, 450, 1300);
			
			GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 25, 0);
                GL11.glScalef(40f, 5f, 40f);
                Color.black.bind();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                shadow.DrawTexSphere(1, 10, 10, texture);
            }
            GL11.glPopMatrix();
            Color.yellow.bind();
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glScalef(30f, 30f, 30f);
			Sunflower flower = new Sunflower();
			flower.drawFlower(textures[6], textures[13], myDelta, 0);
		}
		GL11.glPopMatrix();
		
        for (int i = 0; i < 20; i++) {
            GL11.glPushMatrix();
            if( i == 12){
               if(cuttedObjects.contains(3) ){
                   GL11.glPopMatrix();
                   continue;
               }
            }
            if( i == 19){
                if(cuttedObjects.contains(4) ){
                    GL11.glPopMatrix();
                    continue;
                }
            }
            if(!cutted2Objects.contains(i)){
                this.trees2[i].Draw();
            }
            if(!cuttedObjects.contains(i)){
                this.trees[i].Draw();
            }


            GL11.glPopMatrix();
        }

        {
			GL11.glTranslatef(-5000f, -10, -5000f);
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glScalef(9000, 9000, 9000);
			TexSquare texsquare = new TexSquare();
			texsquare.DrawTexSquare(textures[7]);
        }
    }

    public static void main(String[] argv) throws IOException {
        MainWindow hello = new MainWindow();
        hello.start();
    }

    Texture texture;
    Texture textureTrunk;
    Texture[] textures;
    Texture[] robotTextures;
    Texture[] robotTextures2;
    Texture[] robotTextures3;
    Ball ball;
    TexCube MyGlobe = new TexCube();
    TexSphere sun = new TexSphere();
    Robot robot;
    Robot robot2;
    Robot robot3;
    TexSphere shadowHum;
    TexSphere shadow;
    /*
     * Any additional textures for your assignment should be written in here.
     * Make a new texture variable for each one so they can be loaded in at the beginning
     */
    public void init() throws IOException {
        textures = new Texture[14];
        robotTextures = new Texture[9];   
        robotTextures2 = new Texture[9]; 
        robotTextures3 = new Texture[9]; 
        textures[0] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/cloth01.bmp"));
        textures[1] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/cloth02.bmp"));
        textures[2] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/cloth03.bmp"));
        textures[3] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/cloth04.bmp"));
        textures[4] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/cloth05.bmp"));
        textures[5] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/cloth06.bmp"));
        textures[6] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/leaf.jpeg"));
        textures[7] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/leaf.jpeg"));
        textures[8] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/leaf.jpeg"));
        textures[9] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball1.bmp"));
        textures[10] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball.bmp"));
        textures[11] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball2.bmp"));
        textures[12] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/2021.jpg"));
        textures[13] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/orange.png"));
        
        robotTextures[0] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        robotTextures[1] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball.bmp"));
        robotTextures[2] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball1.bmp"));
        robotTextures[3] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball2.bmp"));
        robotTextures[4] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball2.bmp"));
        robotTextures[5] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball2.bmp"));
        robotTextures[6] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball2.bmp"));
        robotTextures[7] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        robotTextures[8] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        
        robotTextures2[0] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        robotTextures2[1] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball.bmp"));
        robotTextures2[2] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball2.bmp"));
        robotTextures2[3] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball1.bmp"));
        robotTextures2[4] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball.bmp"));
        robotTextures2[5] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball.bmp"));
        robotTextures2[6] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball.bmp"));
        robotTextures2[7] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        robotTextures2[8] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        
        robotTextures3[0] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        robotTextures3[1] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball2.bmp"));
        robotTextures3[2] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball1.bmp"));
        robotTextures3[3] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball.bmp"));
        robotTextures3[4] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball1.bmp"));
        robotTextures3[5] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball1.bmp"));
        robotTextures3[6] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/ball1.bmp"));
        robotTextures3[7] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        robotTextures3[8] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/head.bmp"));
        
        texture = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/cube.jpeg"));
        textureTrunk = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/trunk.bmp"));
        System.out.println("Texture loaded okay ");
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Random r = new Random(1);
        this.trees = new Tree[20];
        this.trees2 = new Tree2[20];
        for (int i = 0; i < 20; i++) {
            float ran1 = r.nextInt(2000);
            float ran2 = r.nextInt(2030) - 5;
            if (i % 2 == 0) {
                ran1 = -ran1;
            }
            Point4f pos = new Point4f(ran1, 440f, ran2, 0);
            treePoints.add(pos);
            Point4f size = new Point4f(ran1, 440f, ran2, 0);
            this.trees2[i] = new Tree2(textures[6], texture, pos);
        }
        for (int i = 0; i < 20; i++) {
            float ran1 = r.nextInt(2000);
            float ran2 = r.nextInt(2030) - 5;
            if (i % 2 == 0) {
                ran1 = -ran1;
            }
            Point4f pos = new Point4f(ran1, 440f, ran2, 0);
            Point4f size = new Point4f(ran1, 440f, ran2, 0);
            this.trees[i] = new Tree(textures[6], texture, pos);
        }
        Point4f pos = new Point4f(500, 440f + ballY, -500, 0);
        ball = new Ball(textures[9], textures[10], textures[11], texture, pos);
        robot = new Robot(robotTextures, robotPos);
        robot2 = new Robot(robotTextures2, robotPos2);
        robot3 = new Robot(robotTextures3, robotPos3);
        shadow = new TexSphere();
        shadowHum = new TexSphere();
    }
}
