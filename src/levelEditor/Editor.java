package levelEditor;

import org.lwjgl.util.glu.GLU;

import king.jaiden.util.*;

import static org.lwjgl.opengl.GL11.*;

public class Editor extends ApplicationWindow {
	private World world;
	private Menu controls;
	private Coord fontSize;
	private int layers, rows, columns;
	private Coord3D selectPos;
	public Editor(IntCoord windowDimensions, int fov, String windowTitle, boolean isFullscreen, int matrixMode){
		super(windowDimensions, fov, windowTitle, isFullscreen, matrixMode);
	}
	public void init() {
		world = new World();
		dst = -10;
		
		fontSize = new Coord(10,20);

		controls = new Menu();
		controls.setVisible(true);
		controls.setColor(Color.CLEAR);
		controls.setDimensions(new Coord(1280,720));
		controls.setPadding(0);
				
		xRot = 45;
		yRot = 45;
		xPan = -7;
		zPan = -7;
		dstMod = 100;
		
		layers = 10;
		rows = 10;
		columns = 10;
		
		Label l1 = new Label("Controls",fontSize);
		l1.setSize(Size.MATCH_PARENT_WIDTH);
		l1.setDimensions(new Coord(1,20));
		l1.setTextAlignment(TextUtil.LEFT);
		
		Label l2 = new Label("Mouse to rotate and zoom",fontSize);
		l2.setSize(Size.MATCH_PARENT_WIDTH);
		l2.setDimensions(new Coord(1,20));
		l2.setTextAlignment(TextUtil.LEFT);
		
		controls.add(l1);
		controls.add(l2);
		
		selectPos = new Coord3D(0,0,0);
		
		world.generateTerrain();
	}
	public static void main(String[] args){
		new Editor(new IntCoord(1280,720),70,"Turret Game",false,ApplicationWindow.THREE_DIMENSIONAL);
	}
	public void draw(){
		super.draw();
		drawWorld();
		drawHUD();
	}
	public void input(){
		super.input();
		
	}
	public void drawWorld(){
		world.draw();
		drawBB();
		glPushMatrix();
			DrawUtil.setColor(Color.WHITE);
			glTranslated(0,-0.55,0);
			glBegin(GL_QUADS);
				glVertex3d(-1,0,-1);
				glVertex3d(-1,0,layers);
				glVertex3d(columns,0,layers);
				glVertex3d(columns,0,-1);
				
				glVertex3d(-1,0,-1);
				glVertex3d(columns+0.5,0,-1);
				glVertex3d(columns+0.5,0,layers);
				glVertex3d(-1,0,layers);
			glEnd();
		glPopMatrix();
	}
	public void drawBB(){
//		glBegin();
	}
	public void drawHUD(){
		glPushMatrix();
			setup2DMatrix();
			glTranslated(-620,0,0);
			controls.draw();
		glPopMatrix();
		setup3DMatrix();
	}
}
