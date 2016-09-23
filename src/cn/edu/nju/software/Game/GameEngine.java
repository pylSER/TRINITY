package cn.edu.nju.software.Game;

import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

import com.jme3.input.MouseInput;
import com.jme3.math.FastMath;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.post.filters.DepthOfFieldFilter;
import com.jme3.post.filters.FadeFilter;
import com.jme3.post.filters.LightScatteringFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.plugins.ogre.OgreMeshKey;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Torus;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.app.SimpleApplication;
import com.jme3.effect.ParticleEmitter;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.MaterialList;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Plane;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.BillboardControl;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.PssmShadowRenderer;
import com.jme3.shadow.PssmShadowRenderer.CompareMode;
import com.jme3.shadow.PssmShadowRenderer.FilterMode;
import com.jme3.system.AppSettings;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.texture.Texture2D;
import com.jme3.util.SkyFactory;
import com.jme3.util.TangentBinormalGenerator;
import com.jme3.water.SimpleWaterProcessor;
import com.jme3.water.WaterFilter;
import com.jme3.asset.TextureKey;
import com.jme3.asset.plugins.HttpZipLocator;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.audio.AudioNode;
import com.jme3.audio.LowPassFilter;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.objects.PhysicsCharacter;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.ParticleMesh.Type;
import com.jme3.effect.shapes.EmitterSphereShape;

public class GameEngine extends SimpleApplication{
	private Geometry window;
	private Geometry geo;
	private Geometry frame;
	private Geometry stop;
	private Geometry lead;
	private Geometry rectangle;
	private Geometry getBack1;
	private Geometry getBack2;
	private Geometry frame1;
	private Geometry frame2;
	private Geometry frame3;
	private Geometry framePic1;
	private Geometry framePic2;
	private Geometry framePic3;
	private Geometry pilebook1;
	private Geometry pilebook2;
	private Geometry pilebook3;
	private Geometry book11;
	private Geometry book12;
	private Geometry book31;
	private Geometry book32;
	private Geometry book33;
	private Geometry book51;
	private Geometry book52;
	private Geometry book53;
	private Geometry book21;
	private Geometry book22;
	private Geometry waterball;
	private Geometry ballbase;
	private Geometry lightcontrol;
	private Geometry stair;
	private Geometry reBoxg;
	private Geometry reBoxg1;
	private Geometry reBoxg2;
	private Geometry Ball11;
	private Geometry Ball21;
	private Node bookshief;
	private Node movingNode1;
	private Node movingNode2;
	private Node movingNode3;
	private Node root;
	private Node gameLevel;

	private Vector3f camDir = new Vector3f();
	private Vector3f camLeft = new Vector3f();
	private Vector3f walkDirection = new Vector3f();
	private Spatial sky;
	private AudioNode audio_nature;
	private AudioNode audio_main;
	private BitmapText helloText;
	private BulletAppState bulletAppState;
	private DirectionalLight sun;
	private DirectionalLight sun1;
	private DirectionalLight sun2;
	private DirectionalLight bublight;
	private DirectionalLight s;
	private DirectionalLight l;
	private RigidBodyControl landscape;
	private BulletAppState bulletAppState1;
	private RigidBodyControl landscape1;
	private ParticleEmitter fire;
	private ParticleEmitter emit;
	private ParticleEmitter emit2;
	private PhysicsCharacter player;
	private FilterPostProcessor fpp;
	private FadeFilter fade;
	private static GameEngine app;
	private float angle = 0f;
	private float n = 0.5f;
	private float a=0f;
	private boolean permit = false;
	private boolean permit1 = true;
	private boolean permit2 = false;
	private boolean back = false;
	private boolean back2 = false;
	private boolean go = false;
	private boolean begin = false;
	private boolean isfade = false;
	private boolean swi1 = false;
	private boolean swi2 = false;
	private boolean swi3 = false;
	private boolean left = false, right = false, up = false, down = false;
	private boolean restartlevel1=false;
	private boolean restartlevel3=false;
	private boolean getwaterball=false;
	private boolean getpower=false;
	private boolean islighton=false;
	private boolean delete=false;
	private boolean trans1=false;
	private boolean trans2=false;
	private boolean startLevel1=false;
	private boolean startLevel2=false;
	private boolean startLevel3=false;
	private boolean passLevel1=false;
	private boolean passLevel2=false;
	private boolean permitpass=false;
	private boolean permitpass2=false;
	private boolean floatUp=false;
	private int tcount = 0;
	private int booksdeteched=0;
	private Geometry box3;

	private RigidBodyControl landscape3;
	private BulletAppState bulletAppState3;
	private Geometry cube1Geo;
	private Vector3f lightDir3 = new Vector3f(-4.9236743f, -1.27054665f,5.896916f);
	private WaterFilter water3;
	private TerrainQuad terrain3;
	private Material matRock3;
	private AudioNode waves;
	private LowPassFilter underWaterAudioFilter = new LowPassFilter(0.5f, 0.1f);
	private LowPassFilter underWaterReverbFilter = new LowPassFilter(0.5f, 0.1f);
	private LowPassFilter aboveWaterAudioFilter = new LowPassFilter(1, 1);
	private float time = 0.0f;
	private float waterHeight = 0.0f;
	private float initialWaterHeight = 90f;// 0.8f;
	private boolean uw = false;
	private int bricksPerLayer = 10;
	private int brickLayers = 32;

	private static float brickWidth = 1.5f, brickHeight = 0.5f, brickDepth = 0.5f;
	private float radius = 6f;
	private float angleT = 0;

	private Material matTo1;
	private Material mat2To2;
	private Material matTo3;
	private PssmShadowRenderer bsr;
	private Sphere bulletT;
	private Box brick;
	private SphereCollisionShape bulletCollisionShapeT;

	private BulletAppState bulletAppStateT;

	private Vector3f camDir1 = new Vector3f();
	private Vector3f camUp1 = new Vector3f();
	private Vector3f camLocation1 = new Vector3f();
	private Vector3f camLeft1 = new Vector3f(); 
	
	private Geometry cube1Geo2;
    private Geometry cube1Geo3;
	private Geometry cube1Geo4;
    private Geometry cube1Geo5;
	private Geometry cube1Geo6;
	private Geometry cube1Geo7;
	private Geometry cube1Geo8;
	private boolean music=true;
	private int vol=3;
	private DirectionalLight dl;
	private boolean restartlevel2=false;
	private AmbientLight am;
	//judge for level 2
	private boolean elephantPick=false;
	private boolean stonePick=false;
	private boolean swordPick=false;
	private BitmapText textlv2;
	private Spatial elephant;
	private boolean fakeballPick=false;
	 Geometry Ball31;
	  Geometry Ball32;
	  Geometry cube2Geo;
	  Geometry cube2Geo2;
	  protected BitmapText textlv3;
	  boolean bombPick=false;
	  boolean ball31=false;
	  boolean ball32=false;
	  boolean startspin=false;
	  boolean explose=false;
	  
	  Geometry cube1Geo14;
	  Geometry cube1Geo13;
	  Geometry cube1Geo12;
	  Geometry cube1Geo11;

	  Geometry cube1Geo10;
	  Geometry cube1Geo9;
	  
	  Geometry cube2Geo3;
	  Geometry cube2Geo4;
	  private ParticleEmitter effect;
	  private SphereCollisionShape bulletCollisionShape;
	  
	  private RigidBodyControl rc=new RigidBodyControl(0f);
	  Geometry cube2Geo2ba ;
	  boolean getback=false;
	  private AudioNode audio_boom;
	  private AudioNode audio_end;
	  
	public static void main(String[] args) {

		app = new GameEngine();
		app.setShowSettings(false);
		app.setDisplayFps(false);
		app.setDisplayStatView(false);
		AppSettings settings = new AppSettings(true);
		app.setSettings(settings);
		settings.setFullscreen(true);
		settings.setResolution(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		Start lood = new Start();
		lood.go();
		app.start();
	}

	public void simpleInitApp() {
		keyMouse();
		initAudioMain();
		flyCam.setEnabled(false);

		Sphere star = new Sphere(32, 32, 0.7f, false, true);
		window = new Geometry("Start", star);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setTexture("ColorMap",
				assetManager.loadTexture("scene/start3(2).jpg"));
		mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		window.setQueueBucket(Bucket.Transparent);
		window.setLocalTranslation(new Vector3f(-6, -1.8f, -5));
		window.setMaterial(mat);
		rootNode.attachChild(window);
		// Start button

		Sphere set = new Sphere(32, 32, 0.6f, false, true);
		geo = new Geometry("Set", set);
		set.setTextureMode(Sphere.TextureMode.Projected);
		TangentBinormalGenerator.generate(set);

		Material mat1 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		mat1.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/Set3.jpg"));
		mat1.setFloat("Shininess", 5f);
		geo.setLocalTranslation(new Vector3f(-10, 0, -8));
		geo.rotate(1.5f, -1.6f, 0);
		geo.setMaterial(mat1);
		// Set button

		Sphere home = new Sphere(32, 32, 0.6f, false, true);
		frame = new Geometry("Home", home);
		home.setTextureMode(Sphere.TextureMode.Projected);
		TangentBinormalGenerator.generate(home);

		Material mat2 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		mat2.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/Home3.jpg"));
		mat2.setFloat("Shininess", 5f);
		frame.setLocalTranslation(new Vector3f(-2, -4, -5));
		frame.rotate(1.5f, -1.6f, 0);
		frame.setMaterial(mat2);
		// Home button

		Sphere exit = new Sphere(32, 32, 0.5f, false, true);
		stop = new Geometry("Exit", exit);
		exit.setTextureMode(Sphere.TextureMode.Projected);
		TangentBinormalGenerator.generate(exit);

		Material mat3 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		mat3.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/Quit3.jpg"));
		mat3.setFloat("Shininess", 3f);
		stop.setLocalTranslation(new Vector3f(0, -3, -6));
		stop.rotate(1.5f, -1.5f, 0);
		stop.setMaterial(mat3);

		// Exit button
		Quad trinity = new Quad(7, 1.2f);
		Geometry framePic = new Geometry("Trinity", trinity);
		Material mats = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mats.setTexture("ColorMap",
				assetManager.loadTexture("scene/TRINITY.png"));
		mats.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		framePic.setQueueBucket(Bucket.Transparent);
		framePic.setMaterial(mats);
		framePic.setLocalTranslation(new Vector3f(-0.5f, 2.1f, 0));

		sun = new DirectionalLight();
		sun.setDirection(new Vector3f(1, 0, -2).normalizeLocal());
		sun.setColor(ColorRGBA.Yellow);
		rootNode.addLight(sun);

		// Add light

		movingNode1 = new Node("Parent");
		movingNode1.setLocalTranslation(new Vector3f(-4, -3, 3));
		movingNode1.attachChild(geo);
		rootNode.attachChild(movingNode1);

		movingNode2 = new Node("Parent");
		movingNode2.setLocalTranslation(new Vector3f(-1, 2, -10));
		movingNode2.attachChild(frame);
		rootNode.attachChild(movingNode2);

		movingNode3 = new Node("Parent");
		movingNode3.setLocalTranslation(new Vector3f(-2, 3, -5));
		movingNode3.attachChild(stop);
		rootNode.attachChild(movingNode3);

		root = new Node("parentparent");
		root.attachChild(window);
		root.attachChild(movingNode1);
		root.attachChild(movingNode2);
		root.attachChild(movingNode3);
		root.attachChild(framePic);
		rootNode.attachChild(root);

		Texture west = assetManager.loadTexture("scene/side2.jpg");
		Texture east = assetManager.loadTexture("scene/side1.jpg");
		Texture north = assetManager.loadTexture("scene/back.jpg");
		Texture south = assetManager.loadTexture("scene/front.jpg");
		Texture up = assetManager.loadTexture("scene/up.jpg");
		Texture down = assetManager.loadTexture("scene/down.jpg");

		sky = SkyFactory.createSky(assetManager, west, east, north, south, up,
				down);
		rootNode.attachChild(sky);

		flyCam.setMoveSpeed(100);
		this.cam.setFrustumFar(2000);
		camDir1=cam.getDirection();
		camUp1=cam.getUp();
		camLocation1=cam.getLocation();
		camLeft1=cam.getLeft();
		
	}

	public void simpleUpdate(float tpf) {

		window.rotate(0, tpf, 0);
		if (permit1) {
			angle += n*tpf;
			angle %= FastMath.TWO_PI;
			movingNode1.setLocalTranslation(new Vector3f(5 + FastMath
					.cos(angle) * 5f, -1 - FastMath.sin(angle) * 2f,
					3 + FastMath.sin(angle) * 4f));
			movingNode2.setLocalTranslation(new Vector3f(-5
					+ FastMath.sin(angle) * 3f, 3 + FastMath.cos(angle) * 4f,
					-1 + FastMath.sin(angle) * 4f));
			movingNode3.setLocalTranslation(new Vector3f(-5
					+ FastMath.cos(angle) * 3f, FastMath.sin(angle) * 3f,
					FastMath.sin(angle) * 5f));
		}

		if (permit) {
			sky.rotate(0, 0.4f * tpf, 0.02f * tpf);
			root.move(10 * tpf, 0, 0);
		}
		if (permit2) {
			sky.rotate(0, -0.4f * tpf, 0);
			root.move(-10 * tpf, 0, 0);
		}
		if (back2) {
			sky.rotate(0, 0.4f * tpf, 0);
			root.move(10 * tpf, 0, 0);
		}
		if (back) {
			sky.rotate(0, -0.4f * tpf, -0.02f * tpf);
			root.move(-10 * tpf, 0, 0);
		}
		if(trans1){
			root.setLocalTranslation(new Vector3f(40, 0, 0));
			trans1=false;
		}
		if(trans2){
			root.setLocalTranslation(new Vector3f(-25.5f, 0, 0));		
			trans2=false;
		}
		if (go) {
			rectangle.move(0, 0, tpf * 120);
		}
		if (begin) {
			Vector3f camDir = cam.getDirection().clone().multLocal(0.6f);
			Vector3f camLeft = cam.getLeft().clone().multLocal(0.4f);
			walkDirection.set(0, 0, 0);
			if (left)
				walkDirection.addLocal(camLeft);
			if (right)
				walkDirection.addLocal(camLeft.negate());
			if (up)
				walkDirection.addLocal(camDir);
			if (down)
				walkDirection.addLocal(camDir.negate());
			player.setWalkDirection(walkDirection);
			cam.setLocation(player.getPhysicsLocation());
			angle=0;
		}
		if(restartlevel1){
			startLevel1();
			guiNode.detachChild(helloText);
			if(islighton){
				rootNode.removeLight(bublight);
				islighton=false;
			}
			restartlevel1=false;
		}
		if (swi1) {
			guiNode.detachChild(helloText);
			rootNode.detachChild(rectangle);
			rootNode.detachChild(emit);
			rootNode.detachChild(emit2);
			startLevel1();
			swi1 = false;	
		}	
		if(booksdeteched==9){
				bookshief.move(0,0,6);
				booksdeteched=0;
		}
		if(restartlevel2){
			guiNode.detachChild(helloText);
			startLevel2();
			restartlevel2=false;
		}
		if (swi3) {
			super.simpleUpdate(tpf);
			// box.updateGeometricState();
			time += tpf;
			waterHeight = (float) Math.cos(((time * 0.6f) % FastMath.TWO_PI)) * 1.5f;
			water3.setWaterHeight(initialWaterHeight + waterHeight);
			if (water3.isUnderWater() && !uw) {

				waves.setDryFilter(new LowPassFilter(0.5f, 0.1f));
				uw = true;
			}
			if (!water3.isUnderWater() && uw) {
				uw = false;
				// waves.setReverbEnabled(false);
				waves.setDryFilter(new LowPassFilter(1, 1f));
				// waves.setDryFilter(new LowPassFilter(1,1f));
			}
		}
		if(restartlevel3){
			audio_boom.stop();
			guiNode.detachChild(helloText);
			startLevel3();
			restartlevel3=false;
		}
		if(startspin){
			cube2Geo2ba.rotate(0, 6*tpf, 0);

		}
		
		if(getback){
			rootNode.detachAllChildren();
			guiNode.detachAllChildren();
			rootNode.removeLight(s);
			rootNode.removeLight(l);
			viewPort.clearProcessors();
			water3.setEnabled(false);
			allpass();
			getback=false;
		}
		if(floatUp){
			helloText.move(0, 150*tpf, 0);
		}
		if(permitpass){
			guiNode.detachAllChildren();
			rootNode.detachAllChildren();
			back();
			permitpass=false;
		}
		if(permitpass2){
			a += tpf;
			a %=FastMath.TWO_PI;
			Ball11.setLocalTranslation(FastMath.sin(a)*6f-4, FastMath.cos(a)*8f-3, FastMath.sin(a)*5f-23);
			Ball21.setLocalTranslation(FastMath.cos(a)*8f-6, FastMath.cos(a)*6f-3, FastMath.cos(a)*8f-20);
			Ball32.setLocalTranslation(FastMath.sin(a)*5f-8, FastMath.sin(a)*5f-4, FastMath.sin(a)*6f-25);
		}
	}

	public void keyMouse() {
		inputManager.addMapping("Lefts", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("Rights", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("Ups", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("Downs", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addMapping("Pick target", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		inputManager.addMapping("SwitchMusic",new KeyTrigger(KeyInput.KEY_P));
		inputManager.addMapping("VolUp",  new KeyTrigger(KeyInput.KEY_PGUP));
		inputManager.addMapping("VolDown",  new KeyTrigger(KeyInput.KEY_PGDN));
		inputManager.addListener(actionListener, "Lefts");
		inputManager.addListener(actionListener, "Rights");
		inputManager.addListener(actionListener, "Ups");
		inputManager.addListener(actionListener, "Downs");
		inputManager.addListener(actionListener, "Pause", "Pick target");
		inputManager.addListener(actionListener,"SwitchMusic");
		inputManager.addListener(actionListener,"VolUp");
		inputManager.addListener(actionListener,"VolDown");
	}

	private ActionListener actionListener = new ActionListener() {
		public void onAction(String name, boolean isPressed, float tpf) {

			if (isPressed && name.equals("Pause")) {
				permit1 = !permit1;				
			} else if(isPressed&&name.equals("SwitchMusic")){
		        	if(music){
		        		audio_main.pause();
		        		music=false;
		        	}
		        	else{
		        		audio_main.play();
		        		music=true;
		        	}
		    } else if(isPressed&&name.equals("VolUp")){
				   vol=vol+3;
				   audio_main.setVolume(vol);
			} else if(isPressed&&name.equals("VolDown")){
				   if(vol>0){
					   vol=vol-3;
				   audio_main.setVolume(vol);
				   }
			} else if (isPressed && name.equals("Pick target")) {
				CollisionResults results = new CollisionResults();
				Vector2f click2d = inputManager.getCursorPosition();
				Vector3f click3d = cam.getWorldCoordinates(
						new Vector2f(click2d.x, click2d.y), 0f).clone();
				Vector3f dir = cam
						.getWorldCoordinates(
								new Vector2f(click2d.x, click2d.y), 1f)
						.subtractLocal(click3d).normalizeLocal();
				Ray ray = new Ray(click3d, dir);
				rootNode.collideWith(ray, results);

				if (results.size() > 0) {
					Geometry target = results.getClosestCollision()
							.getGeometry();

					if (target.getName().equals("Start"))
						play();
					else if (target.getName().equals("Set"))
						set();
					else if (target.getName().equals("Home"))
						home();
					else if (target.getName().equals("Exit"))
						exit();
					else if (target.getName().equals("GetBack1"))
						flyHome1();
					else if (target.getName().equals("GetBack2"))
						flyHome2();
					else if (target.getName().equals("level1")) {
						rootNode.detachAllChildren();
						startLevel1();
					} else if (target.getName().equals("level2")&&passLevel1) {
						rootNode.detachAllChildren();
						startLevel2();
					} else if (target.getName().equals("level3")&&passLevel2) {
						rootNode.detachAllChildren();
						startLevel3();
					}
				}
			}
		}
	};

	@SuppressWarnings("deprecation")
	public void play() {
		audio_main.stop();

		Quad b2 = new Quad(20f, 20f);
		rectangle = new Geometry("rectangle", b2);
		Material mat4 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat4.setTexture("ColorMap",
				assetManager.loadTexture("scene/passover.png"));
		rectangle.setMaterial(mat4);
		rectangle.setLocalTranslation(new Vector3f(-10, -10, -600));

		emit = new ParticleEmitter("Emitter", Type.Triangle, 300);
		emit.setShape(new EmitterSphereShape(Vector3f.ZERO, 90f));
		emit.setGravity(0, 0, -150);
		emit.setLowLife(5);
		emit.setHighLife(10);
		emit.setInitialVelocity(new Vector3f(0, 0, 0));
		emit.setImagesX(15);
		emit.setNumParticles(1000);
		emit.setParticlesPerSec(300);

		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture",
				assetManager.loadTexture("Effects/Smoke/Smoke.png"));
		emit.setMaterial(mat);
		emit.setStartColor(ColorRGBA.Blue);
		emit.setEndColor(ColorRGBA.White);
		emit2 = emit.clone();
		emit2.move(3, 0, 0);

		rootNode.detachAllChildren();
		rootNode.attachChild(emit);
		rootNode.attachChild(emit2);
		rootNode.attachChild(rectangle);

		go = true;

		initAudio();

		Timer timer1 = new Timer();
		class Task10 extends TimerTask {
			public void run() {
				swi1 = true;
			}
		}
		timer1.schedule(new Task10(), 29000);

	}

	public void set() {

		Sphere mark1 = new Sphere(32, 32, 0.3f);
		getBack1 = new Geometry("GetBack1", mark1);
		Material mat5 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat5.setTexture("ColorMap",
				assetManager.loadTexture("scene/Return3.jpg"));
		mat5.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		getBack1.setQueueBucket(Bucket.Transparent);
		getBack1.setLocalTranslation(new Vector3f(-46.5f, 3.5f, -2));
		getBack1.setMaterial(mat5);
		root.attachChild(getBack1);

		Quad title1 = new Quad(4, 0.8f);
		frame1 = new Geometry("Settings", title1);
		Material mat6 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat6.setTexture("ColorMap",
				assetManager.loadTexture("scene/Settings.png"));
		mat6.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		frame1.setQueueBucket(Bucket.Transparent);
		frame1.setMaterial(mat6);
		frame1.setLocalTranslation(new Vector3f(-46, 3f, -2));
		frame1.setLocalRotation(new Quaternion(0, -0.01f, 0, -0.1f));

		Quad title2 = new Quad(10, 6);
		frame2 = new Geometry("Instruction", title2);
		Material mat7 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat7.setTexture("ColorMap",
				assetManager.loadTexture("scene/options.png"));
		mat7.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		frame2.setQueueBucket(Bucket.Transparent);
		frame2.setMaterial(mat7);
		frame2.setLocalTranslation(new Vector3f(-47, -4, -2));
		frame2.setLocalRotation(new Quaternion(0, -0.01f, 0, -0.1f));

		root.attachChild(frame1);
		root.attachChild(frame2);
		permit = true;
		Timer timer = new Timer();

		class Task3 extends TimerTask {
			public void run() {
				permit = false;
				trans1=true;
			}
		}
		timer.schedule(new Task3(), 4000);
	}

	public void home() {

		Sphere mark2 = new Sphere(32, 32, 0.3f);
		getBack2 = new Geometry("GetBack2", mark2);
		Material mat6 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat6.setTexture("ColorMap",
				assetManager.loadTexture("scene/Return3.jpg"));
		mat6.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		getBack2.setQueueBucket(Bucket.Transparent);
		getBack2.setLocalTranslation(new Vector3f(32.5f, 3.5f, -2));
		getBack2.setMaterial(mat6);
		root.attachChild(getBack2);

		Quad title1 = new Quad(2, 0.8f);
		frame3 = new Geometry("Settings", title1);
		Material mat7 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat7.setTexture("ColorMap", assetManager.loadTexture("scene/Home.png"));
		mat7.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		frame3.setQueueBucket(Bucket.Transparent);
		frame3.setMaterial(mat7);
		frame3.setLocalTranslation(new Vector3f(30, 3.3f, -3));
		frame3.setLocalRotation(new Quaternion(0, 0.01f, 0, -0.1f));

		Quad picture1 = new Quad(5, 4);
		framePic1 = new Geometry("level1", picture1);
		Material mat8 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat8.setTexture("ColorMap",
				assetManager.loadTexture("scene/level1.png"));
		framePic1.setMaterial(mat8);
		framePic1.setLocalTranslation(new Vector3f(19, -2f, -3));
		framePic1.setLocalRotation(new Quaternion(0, 0.02f, 0, -0.1f));

		Quad picture2 = new Quad(5, 4);
		framePic2 = new Geometry("level2", picture2);
		Material mat9 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat9.setTexture("ColorMap",
				assetManager.loadTexture("scene/level2.png"));
		framePic2.setMaterial(mat9);
		framePic2.setLocalTranslation(new Vector3f(23.8f, -2f, -3));
		framePic2.setLocalRotation(new Quaternion(0, 0.03f, 0, -0.1f));

		Quad picture3 = new Quad(5, 4);
		framePic3 = new Geometry("level3", picture3);
		Material mat10 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat10.setTexture("ColorMap",
				assetManager.loadTexture("scene/level3.png"));
		framePic3.setMaterial(mat10);
		framePic3.setLocalTranslation(new Vector3f(29, -2f, -3));
		framePic3.setLocalRotation(new Quaternion(0, 0.04f, 0, -0.1f));

		root.attachChild(frame3);
		root.attachChild(framePic1);
		root.attachChild(framePic2);
		root.attachChild(framePic3);

		permit2 = true;
		Timer timer = new Timer();

		class Task4 extends TimerTask {
			public void run() {
				permit2 = false;
				trans2=true;			
			}
		}
		timer.schedule(new Task4(), 2500);

	}

	public void exit() {

		fpp = new FilterPostProcessor(assetManager);
		fade = new FadeFilter(3); // e.g. 2 seconds
		fpp.addFilter(fade);
		viewPort.addProcessor(fpp);
		fade.fadeOut();
		Timer timer = new Timer();

		class Task5 extends TimerTask {
			public void run() {
				app.stop();
			}
		}
		timer.schedule(new Task5(), 3000);
	}

	public void flyHome1() {
		back = true;
		Timer timer = new Timer();

		class Task6 extends TimerTask {
			public void run() {
				back = false;			
			}
		}
		timer.schedule(new Task6(), 4000);
	}

	public void flyHome2() {
		back2 = true;
		Timer timer = new Timer();

		class Task7 extends TimerTask {
			public void run() {
				back2 = false;
			}
		}
		timer.schedule(new Task7(), 2500);
	}

	public void initAudio() {
		tcount=0;
		audio_nature = new AudioNode(assetManager, "Audio/gettin.wav", true);
		audio_nature.setLooping(false); // activate continuous playing
		audio_nature.setPositional(false);
		audio_nature.setVolume(3);
		rootNode.attachChild(audio_nature);
		audio_nature.play();

		guiNode.detachAllChildren();
		guiFont = assetManager.loadFont("Interface/Fonts/font.fnt");
		helloText = new BitmapText(guiFont, false);
		helloText.setSize(40f);
		helloText.setLocalTranslation((float) (Toolkit.getDefaultToolkit()
				.getScreenSize().width * 0.3), (float) (0.5 * Toolkit
				.getDefaultToolkit().getScreenSize().height), 0);
		guiNode.attachChild(helloText);
		Timer timer = new Timer();
		class MyTask extends TimerTask {
			public void run() {
				if (tcount == 0) {
					helloText.setText("          随着地球科技迅速发展");
				}
				if (tcount == 1) {
					helloText.setText("人类不断向宇宙深处发送无线电,试图发现外星文明");
				}
				if (tcount == 2) {
					helloText.setText("    但是,却暴露了地球在宇宙中的坐标");
				}
				if (tcount == 3) {
					helloText.setText("  宇宙中某些高度发达的文明在捕获地球位置后");
				}
				if (tcount == 4)
					helloText.setText("    认为人类的存在是对这一星球的浪费");
				if (tcount == 5)
					helloText.setText("    于是计划对地球实施一次“文明大清洗”");
				if (tcount == 6)
					helloText.setText("         从而在其上建立自己的文明");
				if (tcount == 7)
					helloText.setText("                       " + " 然而");
				if (tcount == 8) {
					helloText.setText("联合国科学家收到了未知来源的警告信号\n从而得知了此次危机……");
				}
				if (tcount == 9)
					helloText.setText("                       " + " 但是");
				if (tcount == 10) {
					helloText.setText("          就像一切狗血剧情的发展");
				}
				if (tcount == 11) {
					helloText.setText("        总会有一个被选中的“The one”");
				}
				if (tcount == 12) {
					helloText.setText("          作为拯救世界的主人公");
				}
				if (tcount == 13) {
					helloText.setText("          寻找隐藏在各地的智子");
				}
				if(tcount==14){
					helloText.setText("          解决三体危机……");
				}
				if (tcount == 15) {
					cancel();
				}
				tcount++;
			}
		}
		timer.schedule(new MyTask(), 5500, 1500);

	}

	public void initAudioMain() {

		audio_main = new AudioNode(assetManager, "Audio/pinball.wav", true);
		audio_main.setLooping(false); // activate continuous playing
		audio_main.setPositional(false);
		audio_main.setVolume(3);
		rootNode.attachChild(audio_main);
		audio_main.play();
	}

	public void startLevel1() {
		startLevel1=true;
		startLevel2=false;
		startLevel3=false;
		rootNode.detachAllChildren();
		rootNode.removeLight(sun);
		audio_main.stop();
		initCrossHairs();
		inputManager.removeListener(actionListener);
		baseFor1();

	}

	public void startLevel2() {
		startLevel1=false;
		startLevel2=true;
		startLevel3=false;
		rootNode.detachAllChildren();
		rootNode.removeLight(sun);
		audio_main.stop();
		initCrossHairs();
		inputManager.removeListener(actionListener);
		baseFor2();
	}

	public void startLevel3() {
		startLevel1=false;
		startLevel2=false;
		startLevel3=true;
		audio_main.stop();
		rootNode.removeLight(sun);
		initCrossHairs();
		inputManager.removeListener(actionListener);
		flyCam.setMoveSpeed(2000);
		baseFor3();
	}

	@SuppressWarnings("deprecation")
	public void baseFor1() {
		flyCam.setEnabled(true);
		inputManager.setCursorVisible(false);
		
		cam.setLocation(new Vector3f(-27.0f, 1.0f, 75.0f));
		cam.setRotation(new Quaternion(0.03f, 0.9f, 0f, 0.4f));
		bulletAppState1 = new BulletAppState();
		stateManager.attach(bulletAppState1);

		keyMouseForGame();

		assetManager.registerLocator("wildhouse.zip", ZipLocator.class);
		rootNode.attachChild(SkyFactory.createSky(assetManager,
				"Textures/Sky/Bright/BrightSky.dds", false));
		
		Spatial scene = assetManager.loadModel("main.scene");
		scene.setLocalScale(2f);
		rootNode.attachChild(scene);

		sun1 = new DirectionalLight();
		Vector3f lightDir = new Vector3f(1f, -1, 1);
		sun1.setDirection(lightDir);
		sun1.setColor(ColorRGBA.White.clone().multLocal(2.5f));
		rootNode.addLight(sun1);

		sun2 = new DirectionalLight();
		Vector3f lightDir2 = new Vector3f(1, -2, -10);
		sun2.setDirection(lightDir2);
		sun2.setColor(ColorRGBA.White.clone().multLocal(1f));
		rootNode.addLight(sun2);
		
		final int SHADOWMAP_SIZE = 1024;
		DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(
				assetManager, SHADOWMAP_SIZE, 3);
		dlsr.setLight(sun1);
		viewPort.addProcessor(dlsr);

		DirectionalLightShadowFilter dlsf = new DirectionalLightShadowFilter(
				assetManager, SHADOWMAP_SIZE, 3);
		dlsf.setLight(sun1);
		dlsf.setEnabled(true);
		FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
		fpp.addFilter(dlsf);
		dlsf.setShadowIntensity(0.5f);
		viewPort.addProcessor(fpp);

		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setTexture("ColorMap",
				assetManager.loadTexture("Interface/Logo/Monkey.jpg"));
		// add lightPos Geometry
		Sphere lite = new Sphere(8, 8, 3.0f);
		Geometry lightSphere = new Geometry("lightsphere", lite);
		lightSphere.setMaterial(mat);
		Vector3f lightPos = lightDir.multLocal(-400);
		lightSphere.setLocalTranslation(lightPos);
		rootNode.attachChild(lightSphere);

		SimpleWaterProcessor waterProcessor = new SimpleWaterProcessor(
				assetManager);
		waterProcessor.setReflectionScene(rootNode);
		waterProcessor.setDebug(false);
		waterProcessor.setLightPosition(lightPos);
		waterProcessor.setRefractionClippingOffset(1.0f);

		// setting the water plane
		Vector3f waterLocation = new Vector3f(0, -20, 0);
		waterProcessor.setPlane(new Plane(Vector3f.UNIT_Y, waterLocation
				.dot(Vector3f.UNIT_Y)));
		new WaterUI(inputManager, waterProcessor);
		waterProcessor.setWaterColor(ColorRGBA.Brown);
		waterProcessor.setDebug(false);

		Quad quad = new Quad(800, 800);

		// the texture coordinates define the general size of the waves
		quad.scaleTextureCoordinates(new Vector2f(12f, 12f));

		Geometry water = new Geometry("water", quad);
		water.setShadowMode(ShadowMode.Receive);
		water.setLocalRotation(new Quaternion().fromAngleAxis(
				-FastMath.HALF_PI, Vector3f.UNIT_X));
		water.setMaterial(waterProcessor.getMaterial());
		water.setLocalTranslation(-500, -48, 300);
		water.addControl(new RigidBodyControl(0));
		water.getControl(RigidBodyControl.class).getCollisionShape()
				.setScale(new Vector3f(2, 2, 2));
		bulletAppState1.getPhysicsSpace().add(water);
		// -500, -41, 300
		rootNode.attachChild(water);

		viewPort.addProcessor(waterProcessor);


		CollisionShape sceneShape = CollisionShapeFactory
				.createMeshShape((Node) scene);
		landscape1 = new RigidBodyControl(sceneShape, 0);

		scene.addControl(landscape1);

		CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f,
				6f, 1);
		player = new CharacterControl(capsuleShape, 0.05f);
		player.setJumpSpeed(30);
		player.setFallSpeed(50);
		player.setGravity(40);
		player.setPhysicsLocation(new Vector3f(-30, 0, 0));

		// We attach the scene and the player to the rootnode and the physics
		// space,
		// to make them appear in the game world.

		bulletAppState1.getPhysicsSpace().add(landscape1);
		bulletAppState1.getPhysicsSpace().add(player);

		bookshief = new Node("Parent");

		Box piece1 = new Box(1f, 1.8f, 4.2f);
		Geometry selfpart1 = new Geometry("1", piece1);
		Material self = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		self.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/woodtexture.jpg"));
		self.setFloat("Shininess", 5f);
		selfpart1.setMaterial(self);
		selfpart1.setLocalTranslation(new Vector3f(12, -27.5f, -19));
		selfpart1.addControl(new RigidBodyControl(0));
		selfpart1.getControl(RigidBodyControl.class).getCollisionShape();
        bulletAppState1.getPhysicsSpace().add(selfpart1);
		bookshief.attachChild(selfpart1);

		Box piece2 = new Box(1f, 5.7f, 0.05f);
		Geometry selfpart2 = new Geometry("2", piece2);
		Material self2 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		self2.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/woodtexture.jpg"));
		self2.setFloat("Shininess", 5f);
		selfpart2.setMaterial(self2);
		selfpart2.setLocalTranslation(new Vector3f(0, 3.35f, 4.25f));
		bookshief.attachChild(selfpart2);

		Box piece3 = new Box(1f, 0.05f, 4.2f);
		Geometry selfpart4 = new Geometry("3", piece3);
		Material self3 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		self3.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/woodtexture.jpg"));
		self3.setFloat("Shininess", 2f);
		selfpart4.setMaterial(self3);
		selfpart4.setLocalTranslation(new Vector3f(0, 9f, 0));
		bookshief.attachChild(selfpart4);

		Geometry selfpart3 = selfpart2.clone();
		selfpart3.setLocalTranslation(new Vector3f(0, 3.35f, -4.25f));
		bookshief.attachChild(selfpart3);

		Geometry selfpart5 = selfpart4.clone();
		selfpart5.setLocalTranslation(new Vector3f(0, 6.5f, 0));
		bookshief.attachChild(selfpart5);

		Geometry selfpart6 = selfpart4.clone();
		selfpart6.setLocalTranslation(new Vector3f(0, 4f, 0));
		bookshief.attachChild(selfpart6);
		

		Box book1=new Box(0.6f,0.7f,1.5f);
		pilebook1 = new Geometry("Book1", book1);
		Material cover1 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		cover1.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/Manybooks.png"));
		cover1.setFloat("Shininess", 2f);
		pilebook1.setMaterial(cover1);
		pilebook1.setLocalTranslation(new Vector3f(0, 7.2f,2.7f));
		bookshief.attachChild(pilebook1);
		
		Box book2=new Box(0.6f,0.8f,2f);
		pilebook2 = new Geometry("Book2", book2);
		pilebook2.setMaterial(cover1);
		pilebook2.setLocalTranslation(new Vector3f(0, 4.8f,-2f));
		bookshief.attachChild(pilebook2);
		
		Box book3=new Box(0.6f,0.7f,1f);
		pilebook3 = new Geometry("Book3", book3);		
		pilebook3.setMaterial(cover1);
		pilebook3.setLocalTranslation(new Vector3f(0, 2f,0));
		bookshief.attachChild(pilebook3);
//		
		Box bookwith1=new Box(0.6f,1f,0.1f);
		Material cover2 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		cover2.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/Onebook.png"));
		cover2.setFloat("Shininess", 2f);
		book11=new Geometry("Book11",bookwith1);
		book12=new Geometry("Book12",bookwith1);
		book11.setMaterial(cover2);
		book12.setMaterial(cover2);
		book11.setLocalTranslation(new Vector3f(0,7.5f,-4f));
		book12.setLocalTranslation(new Vector3f(0,5f,1.8f));
//		
		Box bookwith2=new Box(0.6f,0.7f,0.2f);
		Material cover3 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		cover3.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/twobooks.png"));
		cover3.setFloat("Shininess", 2f);
		book21=new Geometry("Book21",bookwith2);
		book22=new Geometry("Book22",bookwith2);
		book21.setMaterial(cover3);
		book22.setMaterial(cover3);
		book21.setLocalTranslation(new Vector3f(0,2f,-3.5f));
		book22.setLocalTranslation(new Vector3f(0,2f,2f));
//		
		Box bookwith3=new Box(0.6f,0.8f,0.3f);
		Material cover4 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		cover4.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/threebooks.png"));
		cover4.setFloat("Shininess", 2f);
		book31=new Geometry("Book31",bookwith3);
		book32=new Geometry("Book32",bookwith3);
		book33=new Geometry("Book33",bookwith3);
		book31.setMaterial(cover4);
		book32.setMaterial(cover4);
		book33.setMaterial(cover4);
		book31.setLocalTranslation(new Vector3f(0,7.3f,-2.1f));
		book32.setLocalTranslation(new Vector3f(0,4.8f,0.5f));
		book33.setLocalTranslation(new Vector3f(0,2f,3.6f));
//		
		Box bookwith5=new Box(0.6f,0.7f,0.5f);
		Material cover5 = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		cover5.setTexture("DiffuseMap",
				assetManager.loadTexture("scene/fivebooks.png"));
		cover5.setFloat("Shininess", 2f);
		book51=new Geometry("Book51",bookwith5);
		book52=new Geometry("Book52",bookwith5);
		book53=new Geometry("Book53",bookwith5);
		book51.setMaterial(cover5);
		book52.setMaterial(cover5);
		book53.setMaterial(cover5);
		book51.setLocalTranslation(new Vector3f(0,7.2f,-0.6f));
		book52.setLocalTranslation(new Vector3f(0,4.7f,2.7f));
		book53.setLocalTranslation(new Vector3f(0,2f,-2f));
		
		bookshief.attachChild(book11);
		bookshief.attachChild(book12);
		bookshief.attachChild(book21);
		bookshief.attachChild(book22);
		bookshief.attachChild(book31);
		bookshief.attachChild(book32);
		bookshief.attachChild(book33);
		bookshief.attachChild(book51);
		bookshief.attachChild(book52);
		bookshief.attachChild(book53);
	
		bookshief.setLocalTranslation(new Vector3f(12, -27, -19));
		rootNode.attachChild(bookshief);

		Sphere sphereMesh = new Sphere(32, 32, 0.5f);
		waterball = new Geometry("Shinyrock", sphereMesh);
		Material sphereMat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		sphereMat.setTexture("ColorMap",
				assetManager.loadTexture("scene/waterball.jpg"));
		waterball.setMaterial(sphereMat);
		waterball.setLocalTranslation(-35, -48.1f, 75); 
		waterball.rotate(1.6f, 0, 0); 
		rootNode.attachChild(waterball);
		
		Torus test = new Torus(30, 30,0.2f, 0.5f);
	    ballbase = new Geometry("Base", test);
	    Material testMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	    testMat.setTexture("ColorMap", assetManager.loadTexture("scene/waterball.jpg"));  
	    ballbase.setMaterial(testMat);
	    ballbase.setLocalTranslation(6.7f,-16.6f,-31); 
	    ballbase.rotate(1.6f, 0, 0);         
	    rootNode.attachChild(ballbase);

	    Box Mesh = new Box(0.5f,0.5f,0.01f);
	    lightcontrol = new Geometry("Control",Mesh);
	    sphereMesh.setTextureMode(Sphere.TextureMode.Projected); // better quality on spheres
	    TangentBinormalGenerator.generate(sphereMesh);           // for lighting effect
	    Material Mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	    Mat.setTexture("ColorMap", assetManager.loadTexture("scene/button.jpg"));
	    lightcontrol.setMaterial(Mat);
	    lightcontrol.setLocalTranslation(-3.13f,-22.35f,-4.275f); 
	    rootNode.attachChild(lightcontrol);
		
	    Box picture = new Box(0.11f,3f,3f);
	    Geometry painting = new Geometry("Picture",picture);
	    sphereMesh.setTextureMode(Sphere.TextureMode.Projected); // better quality on spheres
	    TangentBinormalGenerator.generate(sphereMesh);           // for lighting effect
	    Material pic = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
	    pic.setTexture("DiffuseMap", assetManager.loadTexture("scene/painting.png"));
	    pic.setFloat("Shininess", 2f);
	    painting.setMaterial(pic);
	    painting.setLocalTranslation(-2,-24,-29); 
	    rootNode.attachChild(painting);
		
	    Box door = new Box(1f,0.01f,2f);
	    stair = new Geometry("Stair",door);
	    Material stairs = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
	    stairs.setTexture("DiffuseMap", assetManager.loadTexture("scene/stairs2.jpg"));
	    stairs.setFloat("Shininess", 2f);
	    stair.setMaterial(stairs);
	    stair.setLocalTranslation(12.65f,-28.85f,-21.5f); 
	    rootNode.attachChild(stair);
		
	    guiFont = assetManager.loadFont("Interface/Fonts/font.fnt"); 
		textlv2 = new BitmapText(guiFont, false);
		textlv2.setSize(30f); 
        textlv2.setLocalTranslation((float) (Toolkit.getDefaultToolkit().getScreenSize().width*0.7), (float) (Toolkit.getDefaultToolkit().getScreenSize().height*0.9), 0);
        textlv2.setColor(ColorRGBA.White);
        textlv2.setText("It is said that the house has a basement");
        guiNode.attachChild(textlv2);
		begin = true;

	}

	public void baseFor2() {
		guiFont = assetManager.loadFont("Interface/Fonts/font.fnt"); 
		textlv2 = new BitmapText(guiFont, false);
        textlv2.setSize(30f); 
        textlv2.setLocalTranslation((float) (Toolkit.getDefaultToolkit().getScreenSize().width*0.7), (float) (Toolkit.getDefaultToolkit().getScreenSize().height*0.9), 0);
        textlv2.setText("You have not find the TRINITY Proton yet");
        guiNode.attachChild(textlv2);
		

		inputManager.setCursorVisible(false);
		flyCam.setEnabled(true);
		bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        flyCam.setMoveSpeed(100);
        keyMouseForGame();

        this.cam.setFrustumFar(2000);

        dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White.clone().multLocal(2));
        dl.setDirection(new Vector3f(-1, -1, -1).normalize());


        am = new AmbientLight();
        am.setColor(ColorRGBA.White.mult(2));
      
        rootNode.addLight(dl);
        rootNode.addLight(am);
        
        assetManager.registerLocator("quake3level.zip", ZipLocator.class);

        MaterialList matList = (MaterialList) assetManager.loadAsset("Scene.material");
        OgreMeshKey key = new OgreMeshKey("main.meshxml", matList);
        gameLevel = (Node) assetManager.loadAsset(key);
        gameLevel.setLocalScale(0.1f);

        // add a physics control, it will generate a MeshCollisionShape based on the gameLevel
        gameLevel.addControl(new RigidBodyControl(0));

        player = new PhysicsCharacter(new SphereCollisionShape(5), .01f);
        player.setJumpSpeed(20);
        player.setFallSpeed(30);
        player.setGravity(40);

        player.setPhysicsLocation(new Vector3f(60, 10, -60));

        rootNode.attachChild(gameLevel);

        getPhysicsSpace().addAll(gameLevel);
        getPhysicsSpace().add(player); 
       
        
        
        elephant = (Spatial) assetManager.loadModel("Models/Elephant/Elephant.mesh.xml");
        float scale = 0.09f;
        elephant.scale(scale, scale, scale);
        elephant.setLocalTranslation(32,3,42);
        rootNode.attachChild(elephant);
        
        
        Box empty = new Box( 6f,6f,6f);
        cube1Geo7 = new Geometry("Empty", empty);
        cube1Geo7.setLocalTranslation(new Vector3f(32, 6, 42));
        Material cube1Matc7 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture cube1Texc7 = assetManager.loadTexture("scene/Transparent.png");
        cube1Matc7.setTexture("ColorMap", cube1Texc7);
        cube1Geo7.setMaterial(cube1Matc7);
        cube1Matc7.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        cube1Geo7.setQueueBucket(Bucket.Transparent);
        cube1Geo7.addControl(new RigidBodyControl(0));
		cube1Geo7.getControl(RigidBodyControl.class).getCollisionShape();
		bulletAppState.getPhysicsSpace().add(cube1Geo7);
        rootNode.attachChild(cube1Geo7);
        
        
        
        Box coffin1 = new Box( 8f,2f,3f);
        cube1Geo = new Geometry("My Textured Box", coffin1);
        cube1Geo.setLocalTranslation(new Vector3f(-211, -20.5f, -29));
        Material cube1Matc1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture cube1Texc1 = assetManager.loadTexture("scene/tabut.jpg");
        cube1Matc1.setTexture("ColorMap", cube1Texc1);
        cube1Geo.setMaterial(cube1Matc1);
        cube1Geo.addControl(new RigidBodyControl(0));
		cube1Geo.getControl(RigidBodyControl.class).getCollisionShape();
		bulletAppState.getPhysicsSpace().add(cube1Geo);
        rootNode.attachChild(cube1Geo);
        
        Box coffin2 = new Box( 8f,2f,3f);
        cube1Geo2 = new Geometry("My Textured Box2", coffin2);
        cube1Geo2.setLocalTranslation(new Vector3f(-211, -20.5f, -54));
        Material cube1Mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture cube1Tex = assetManager.loadTexture("scene/tabut.jpg");
        cube1Mat.setTexture("ColorMap", cube1Tex);
        cube1Geo2.setMaterial(cube1Mat);
        cube1Geo2.addControl(new RigidBodyControl(0));
		cube1Geo2.getControl(RigidBodyControl.class).getCollisionShape();
		bulletAppState.getPhysicsSpace().add(cube1Geo2);
        rootNode.attachChild(cube1Geo2);
     
        
        Box coffin3 = new Box( 8f,2f,3f);
        cube1Geo3 = new Geometry("My Textured Box3", coffin3);
        cube1Geo3.setLocalTranslation(new Vector3f(-211, -25f, -41));
        Material cube1Mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture cube1Tex3 = assetManager.loadTexture("scene/tabut.jpg");
        cube1Mat3.setTexture("ColorMap", cube1Tex3);
        cube1Geo3.setMaterial(cube1Mat3);
        cube1Geo3.addControl(new RigidBodyControl(0));
		cube1Geo3.getControl(RigidBodyControl.class).getCollisionShape();
		bulletAppState.getPhysicsSpace().add(cube1Geo3);
        rootNode.attachChild(cube1Geo3);
        
        Box question =new Box(0.1f,7,9);
        cube1Geo4 = new Geometry("Question", question);
        cube1Geo4.setLocalTranslation(new Vector3f(-250, -18f, -43));
        Material cube1Mat4 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture cube1Tex4 = assetManager.loadTexture("scene/question.jpg");
        cube1Mat4.setTexture("ColorMap", cube1Tex4);
        cube1Geo4.setMaterial(cube1Mat4);
        rootNode.attachChild(cube1Geo4);
        
        Box sword =new Box(5,3,0.1f);
        cube1Geo5 = new Geometry("Sword", sword);
        cube1Geo5.setLocalTranslation(new Vector3f(-175f, 16f, 68f));
        Material cube1Mat5 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture cube1Tex5 = assetManager.loadTexture("scene/sword.png");
        cube1Mat5.setTexture("ColorMap", cube1Tex5);
        cube1Geo5.setMaterial(cube1Mat5);
        cube1Mat5.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        cube1Geo5.setQueueBucket(Bucket.Transparent);

        rootNode.attachChild(cube1Geo5);
        
        Sphere ballInElephant = new Sphere(32,32, 1f);
        cube1Geo6 = new Geometry("Shiny rock", ballInElephant);
        Material sphereMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sphereMat.setTexture("ColorMap", assetManager.loadTexture("scene/stone.jpg"));
        cube1Geo6.setMaterial(sphereMat);
        cube1Geo6.setLocalTranslation(32,9,42);
        rootNode.attachChild(cube1Geo6);
        
        Sphere ballTrue = new Sphere(32,32, 1f);
        cube1Geo8 = new Geometry("Trueball", ballTrue);
        Material sphereMat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sphereMat2.setTexture("ColorMap", assetManager.loadTexture("scene/stone.jpg"));
        cube1Geo8.setMaterial(sphereMat2);
        
        cube1Geo8.setLocalTranslation(new Vector3f(-177f, 40f, 24f));
        rootNode.attachChild(cube1Geo8);
        
        
        begin=true;
	}

	@SuppressWarnings("deprecation")
	public void baseFor3() {
		guiFont = assetManager.loadFont("Interface/Fonts/font.fnt"); 
		textlv3 = new BitmapText(guiFont, false);
        textlv3.setSize(30f); 
        textlv3.setLocalTranslation((float) (Toolkit.getDefaultToolkit().getScreenSize().width*0.7), (float) (Toolkit.getDefaultToolkit().getScreenSize().height*0.9), 0);
        textlv3.setText("You have not find the TRINITY Proton yet");
        guiNode.attachChild(textlv3);
    	
    	
    	inputManager.setCursorVisible(false);
		flyCam.setEnabled(true);
    	Node mainScene = new Node("Main Scene");
        rootNode.attachChild(mainScene);
        bulletAppState3 = new BulletAppState();
		stateManager.attach(bulletAppState3);

		bulletCollisionShape = new SphereCollisionShape(0.4f);
        createTerrain(mainScene);
        tower();
        s = new DirectionalLight();
        s.setDirection(lightDir3);
        s.setColor(ColorRGBA.White.clone().multLocal(0.7f));
        rootNode.addLight(s);
        keyMouseForGame();

        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f,
				6f, 1);
		player = new CharacterControl(capsuleShape, 0.05f);
		player.setJumpSpeed(0);
		player.setFallSpeed(10);
		player.setGravity(5);
		player.setPhysicsLocation(new Vector3f(-327.21957f, 61.6459f,
				126.884346f));

		bulletAppState3.getPhysicsSpace().add(player);

        l = new DirectionalLight();
        l.setDirection(Vector3f.UNIT_Y.mult(-1));
        l.setColor(ColorRGBA.White.clone().multLocal(0.3f));
        rootNode.addLight(l);
        
        cam.setLocation(new Vector3f(-327.21957f, 61.6459f, 126.884346f));
        cam.setRotation(new Quaternion(0.052168474f, 0.9443102f, -0.18395276f, 0.2678024f));


        cam.setRotation(new Quaternion().fromAngles(new float[]{FastMath.PI * 0.06f, FastMath.PI * 0.65f, 0}));
        Spatial sky = SkyFactory.createSky(assetManager, "Scenes/Beach/FullskiesSunset0068.dds", false);
        sky.setLocalScale(350);
        rootNode.attachChild(sky);
        cam.setFrustumFar(4000);
        water3 = new WaterFilter(rootNode, lightDir3);
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(water3);
        BloomFilter bloom = new BloomFilter();
        //bloom.getE
        bloom.setExposurePower(55);
        bloom.setBloomIntensity(1.0f);
        fpp.addFilter(bloom);
        LightScatteringFilter lsf = new LightScatteringFilter(lightDir3.mult(-300));
        lsf.setLightDensity(1.0f);
        fpp.addFilter(lsf);
        DepthOfFieldFilter dof = new DepthOfFieldFilter();
        dof.setFocusDistance(0);
        dof.setFocusRange(100);
        fpp.addFilter(dof);

        water3.setWaveScale(0.003f);
        water3.setMaxAmplitude(2f);
        water3.setFoamExistence(new Vector3f(1f, 4, 0.5f));
        water3.setFoamTexture((Texture2D) assetManager.loadTexture("Common/MatDefs/Water/Textures/foam2.jpg"));
        //water.setNormalScale(0.5f);

        //water.setRefractionConstant(0.25f);
        water3.setRefractionStrength(0.2f);
        //water.setFoamHardness(0.6f);

        water3.setWaterHeight(initialWaterHeight);
        uw = cam.getLocation().y < waterHeight;

        waves = new AudioNode(assetManager, "Sound/Environment/Ocean Waves.ogg", false);
        waves.setLooping(true);
        waves.setReverbEnabled(true);
        if (uw) {
            waves.setDryFilter(new LowPassFilter(0.5f, 0.1f));
        } else {
            waves.setDryFilter(aboveWaterAudioFilter);
        }
        waves.play();
        
        //  
        viewPort.addProcessor(fpp);
        
        
        createBox();
        //createFire();
        swi3=true;
        begin=true;
	}

	private void createTerrain(Node rootNode) {
		
		matRock3 = new Material(assetManager,
				"Common/MatDefs/Terrain/TerrainLighting.j3md");
		matRock3.setBoolean("useTriPlanarMapping", false);
		matRock3.setBoolean("WardIso", true);
		matRock3.setTexture("AlphaMap",
				assetManager.loadTexture("Textures/Terrain/splat/alphamap.png"));
		Texture heightMapImage = assetManager
				.loadTexture("Textures/Terrain/splat/mountains512.png");
		Texture grass = assetManager
				.loadTexture("Textures/Terrain/splat/grass.jpg");
		grass.setWrap(WrapMode.Repeat);
		matRock3.setTexture("DiffuseMap", grass);
		matRock3.setFloat("DiffuseMap_0_scale", 64);
		Texture dirt = assetManager
				.loadTexture("Textures/Terrain/splat/dirt.jpg");
		dirt.setWrap(WrapMode.Repeat);
		matRock3.setTexture("DiffuseMap_1", dirt);
		matRock3.setFloat("DiffuseMap_1_scale", 16);
		Texture rock = assetManager
				.loadTexture("Textures/Terrain/splat/road.jpg");
		rock.setWrap(WrapMode.Repeat);
		matRock3.setTexture("DiffuseMap_2", rock);
		matRock3.setFloat("DiffuseMap_2_scale", 128);
		Texture normalMap0 = assetManager
				.loadTexture("Textures/Terrain/splat/grass_normal.jpg");
		normalMap0.setWrap(WrapMode.Repeat);
		Texture normalMap1 = assetManager
				.loadTexture("Textures/Terrain/splat/dirt_normal.png");
		normalMap1.setWrap(WrapMode.Repeat);
		Texture normalMap2 = assetManager
				.loadTexture("Textures/Terrain/splat/road_normal.png");
		normalMap2.setWrap(WrapMode.Repeat);
		matRock3.setTexture("NormalMap", normalMap0);
		matRock3.setTexture("NormalMap_1", normalMap2);
		matRock3.setTexture("NormalMap_2", normalMap2);

		AbstractHeightMap heightmap = null;
		try {
			heightmap = new ImageBasedHeightMap(heightMapImage.getImage(),
					0.25f);
			heightmap.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		terrain3 = new TerrainQuad("terrain", 65, 513, heightmap.getHeightMap());
		List<Camera> cameras = new ArrayList<Camera>();
		cameras.add(getCamera());
		terrain3.setMaterial(matRock3);
		terrain3.setLocalScale(new Vector3f(5, 5, 5));
		terrain3.setLocalTranslation(new Vector3f(0, -30, 0));
		terrain3.setLocked(false); // unlock it so we can edit the height

		terrain3.setShadowMode(ShadowMode.Receive);
		TerrainLodControl control = new TerrainLodControl(terrain3, cameras);
		terrain3.addControl(control);
		terrain3.addControl(new RigidBodyControl(0));

		

		// We attach the scene and the player to the rootnode and the physics
		// space,
		// to make them appear in the game world.
		bulletAppState3.getPhysicsSpace().add(terrain3);
		

		rootNode.attachChild(terrain3);

	}

	@SuppressWarnings("deprecation")
	public void tower() {
		bulletAppStateT = new BulletAppState();
		bulletAppStateT.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
		// bulletAppState.setEnabled(false);
		stateManager.attach(bulletAppStateT);
		bulletT = new Sphere(32, 32, 0.4f, true, false);
		bulletT.setTextureMode(TextureMode.Projected);
		bulletCollisionShapeT = new SphereCollisionShape(0.4f);

		initMaterial();
		
		brick = new Box(Vector3f.ZERO, brickWidth, brickHeight, brickDepth);
		brick.scaleTextureCoordinates(new Vector2f(1f, .5f));
		reBoxg = new Geometry("brick", brick);
		reBoxg.setMaterial(matTo1);
		// bulletAppState.getPhysicsSpace().enableDebug(assetManager);
		
		//initFloor();
		initTower();
		//initCrossHairs();
		// this.cam.setLocation(new Vector3f(0, 25f, 8f));
		// cam.lookAt(Vector3f.ZERO, new Vector3f(0, 1, 0));
		// cam.setFrustumFar(80);
		inputManager.addMapping("shoot", new MouseButtonTrigger(
				MouseInput.BUTTON_LEFT));
		inputManager.addListener(actionListener, "shoot");
		rootNode.setShadowMode(ShadowMode.Off);
		bsr = new PssmShadowRenderer(assetManager, 1024, 2);
		bsr.setDirection(new Vector3f(-1, -1, -1).normalizeLocal());
		bsr.setLambda(0.55f);
		bsr.setShadowIntensity(0.6f);
		bsr.setCompareMode(CompareMode.Hardware);
		bsr.setFilterMode(FilterMode.PCF4);
		viewPort.addProcessor(bsr);
	}

	public void initMaterial() {
		matTo1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		TextureKey key = new TextureKey(
				"Textures/Terrain/BrickWall/BrickWall.jpg");
		key.setGenerateMips(true);
		Texture tex = assetManager.loadTexture(key);
		matTo1.setTexture("ColorMap", tex);

		mat2To2 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		TextureKey key2 = new TextureKey("Textures/Terrain/Rock/Rock.PNG");
		key2.setGenerateMips(true);
		Texture tex2 = assetManager.loadTexture(key2);
		mat2To2.setTexture("ColorMap", tex2);

		matTo3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		TextureKey key3 = new TextureKey("Textures/Terrain/Pond/Pond.jpg");
		key3.setGenerateMips(true);
		Texture tex3 = assetManager.loadTexture(key3);
		tex3.setWrap(WrapMode.Repeat);
		matTo3.setTexture("ColorMap", tex3);
	}

	public void initTower() {
		double tempX = 0;
		double tempY = 0;
		double tempZ = 0;
		angleT = 0f;
		for (int i = 0; i < brickLayers; i++) {
			// Increment rows
			if (i != 0)
				tempY += brickHeight * 2;
			else
				tempY = brickHeight;
			// Alternate brick seams
			angleT = 360.0f / bricksPerLayer * i / 2f;
			for (int j = 0; j < bricksPerLayer; j++) {
				tempZ = Math.cos(Math.toRadians(angleT)) * radius;
				tempX = Math.sin(Math.toRadians(angleT)) * radius;
				Vector3f vt = new Vector3f((float) (tempX)-260, (float) (tempY)+60f,
						(float) (tempZ)-160);
				
					if (i == brickLayers - 1) {
						if (j % 2 == 0) {
							addBrick(vt);
						}
					}
					// Create main tower
					else {
						addBrick(vt);
	
					}
				angleT += 360.0 / bricksPerLayer;
			}
		}
	}

	private PhysicsSpace getPhysicsSpaceT() {
		return bulletAppState3.getPhysicsSpace();
	}

	public void addBrick(Vector3f ori) {
		reBoxg1=reBoxg.clone();
		reBoxg1.setLocalTranslation(ori);
		reBoxg1.rotate(0f, (float) Math.toRadians(angleT), 0f);
		reBoxg1.addControl(new RigidBodyControl());
		reBoxg1.getControl(RigidBodyControl.class).getCollisionShape();
		bulletAppState3.getPhysicsSpace().add(reBoxg1);
		reBoxg1.setShadowMode(ShadowMode.CastAndReceive);
		this.rootNode.attachChild(reBoxg1);
		//this.getPhysicsSpaceT().add(reBoxg);
	}

	@SuppressWarnings("deprecation")
	public void initFloor() {
		Box floorBox = new Box(Vector3f.ZERO, 1000f, 0f, 500f);
		floorBox.scaleTextureCoordinates(new Vector2f(3, 6));

		Geometry floor = new Geometry("floor", floorBox);
		floor.setMaterial(matTo3);
		floor.setShadowMode(ShadowMode.Receive);
		floor.setLocalTranslation(new Vector3f(0, 220, 0));
		floor.addControl(new RigidBodyControl(0));
		this.rootNode.attachChild(floor);
		this.getPhysicsSpaceT().add(floor);
	}

	private PhysicsSpace getPhysicsSpace() {
		return bulletAppState.getPhysicsSpace();
	}

	public void keyMouseForGame() {
		inputManager.deleteMapping( SimpleApplication.INPUT_MAPPING_EXIT );
		inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addMapping("Back", new KeyTrigger(KeyInput.KEY_ESCAPE));
		inputManager.addMapping("Pick", new MouseButtonTrigger(
				MouseInput.BUTTON_LEFT));
		inputManager.addListener(action, "Left");
		inputManager.addListener(action, "Right");
		inputManager.addListener(action, "Up");
		inputManager.addListener(action, "Down");
		inputManager.addListener(action, "Space");
		inputManager.addListener(action, "Back");
		inputManager.addListener(action, "Pick");
	}
	private ActionListener action=new ActionListener(){
	public void onAction(String binding, boolean value, float tpf) {
		Timer time=new Timer();
		
		if (binding.equals("Left")) {
			if (value)
				left = true;
			else
				left = false;
		} else if (binding.equals("Right")) {
			if (value)
				right = true;
			else
				right = false;
		} else if (binding.equals("Up")) {
			if (value)
				up = true;
			else
				up = false;
		} else if (binding.equals("Down")) {
			if (value)
				down = true;
			else
				down = false;
		} else if (binding.equals("Space")) {
			player.jump();
		} else if (binding.equals("Back")){	
			back();
		} else if (binding.equals("Pick")) {
			CollisionResults results = new CollisionResults();
			Ray ray = new Ray(cam.getLocation(), cam.getDirection());
			rootNode.collideWith(ray, results);

			if (results.size() > 0) {
				Geometry target = results.getClosestCollision().getGeometry();

				if(target.getName().equals("Book11")){
					booksdeteched++;
					bookshief.detachChild(book11);
				}else if(target.getName().equals("Book51")){
					booksdeteched++;
					bookshief.detachChild(book51);
				}else if(target.getName().equals("Book32")){
					booksdeteched++;
					bookshief.detachChild(book32);
				}else if(target.getName().equals("Book52")){
					booksdeteched++;
					bookshief.detachChild(book52);
				}else if(target.getName().equals("Book33")){
					booksdeteched++;
					bookshief.detachChild(book33);
				}else if(target.getName().equals("Book53")){
					booksdeteched++;
					bookshief.detachChild(book53);
				}else if(target.getName().equals("Book1")){
					booksdeteched++;
					bookshief.detachChild(pilebook1);
				}else if(target.getName().equals("Book2")){
					booksdeteched++;
					bookshief.detachChild(pilebook2);
				}else if(target.getName().equals("Book3")){
					booksdeteched++;
					bookshief.detachChild(pilebook3);
				}else if(target.getName().equals("Shinyrock")&&!getwaterball){
					getwaterball=true;
					rootNode.detachChild(waterball);
					textlv2.setText("This is an energy ball\n"+"It's a part of power supplying");	
					guiNode.attachChild(textlv2);
					
				}else if(target.getName().equals("Base")){
					
					if(getwaterball){
						guiNode.detachChild(textlv2);
						waterball.setLocalTranslation(6.7f,-16.5f,-31);
						rootNode.attachChild(waterball);
						getpower=true;
						
						textlv2.setText("The owner of the house has some art collcections");
						guiNode.attachChild(textlv2);
						
					}
					else{
						textlv2.setText("It's a part of power supplying");
						
						guiNode.attachChild(textlv2);
					}
						
				}else if(target.getName().equals("Control")&&getpower&&!islighton){
					bublight = new DirectionalLight();
					bublight.setColor(ColorRGBA.White.clone().multLocal(0.7f));
					bublight.setDirection(new Vector3f(-1, 1, 0).normalize());
					guiNode.detachChild(textlv2);
					rootNode.addLight(bublight);
					islighton=true;
				}else if(target.getName().equals("Stair")){
					rootNode.detachAllChildren();
					guiNode.detachChild(textlv2);
					rootNode.removeLight(sun1);
					rootNode.removeLight(sun2);
					if(islighton)
						rootNode.removeLight(bublight);
					passLevel1=true;
					startLevel2();
				}else if(target.getName().equals("Book31")||target.getName().equals("Book12")
						||target.getName().equals("Book21")||target.getName().equals("Book22")){
					
					guiFont = assetManager.loadFont("Interface/Fonts/font.fnt");
					helloText = new BitmapText(guiFont, false);
					helloText.setText("Game Over");
					helloText.setSize(150f);
					helloText.setColor(ColorRGBA.Red);
					helloText.setLocalTranslation(Toolkit.getDefaultToolkit()
							.getScreenSize().width*0.3f,  Toolkit
							.getDefaultToolkit().getScreenSize().height*0.5f, 0);
					guiNode.attachChild(helloText);
					guiNode.detachChild(textlv2);
					rootNode.removeLight(sun1);
					rootNode.removeLight(sun2);
					rootNode.detachAllChildren();
					getpower=false;
					getwaterball=false;
					booksdeteched=0;
					
					class level1Task extends TimerTask{
						public void run(){
							restartlevel1=true;
						}
					}time.schedule(new level1Task(), 2000);
				}
				if(target.getName().equals("Sword")){
		        	  rootNode.detachChild(cube1Geo5);
		        	  textlv2.setText("    A sword?");
		        	  swordPick=true;
		          }
		     
		        if(target.getName().equals("Empty")&&swordPick){
		        	rootNode.detachChild(elephant);
		        	rootNode.detachChild(cube1Geo7);
		        	textlv2.setText("                   Elephant is slashed.");
		        	elephantPick=true;
		        }
		        
		        if(target.getName().equals("Shiny rock")&&elephantPick){
		        	rootNode.detachChild(cube1Geo6);
		        	
		        	textlv2.setText("                   TRINITY Proton Acquired!");
		        	fakeballPick=true;
		        }
		        if(target.getName().equals("Trueball")){
		        	rootNode.detachChild(cube1Geo8);
		        	textlv2.setText("                   Another fake proton?");
		        	stonePick=true;
		        }
		        
		        if((target.getName().equals("My Textured Box2")||target.getName().equals("My Textured Box3"))&&fakeballPick){     
		        
		        	textlv2.setText("                The proton does not work!!\n You have to find the real one!"); 
		        }
		        
		        if(target.getName().equals("My Textured Box")&&fakeballPick){     
			        
		        	textlv2.setText("                When you are looking at the sky,\n you can walk towards it ");
		        }
		        
		        if(target.getName().equals("My Textured Box2")&&stonePick){		       
	        		rootNode.detachAllChildren();
	        		rootNode.removeLight(dl);
	        		guiNode.detachChild(textlv2);
	        		passLevel2=true;
	        		startLevel3();
		        }
		        if((target.getName().equals("My Textured Box")||target.getName().equals("My Textured Box3"))&&stonePick){
		        	guiFont = assetManager.loadFont("Interface/Fonts/font.fnt"); 
		             helloText = new BitmapText(guiFont, false); 
		             helloText.setSize(150f); 
		             helloText.setColor(ColorRGBA.Red);
		             helloText.setLocalTranslation((float) (Toolkit.getDefaultToolkit().getScreenSize().width*0.3), (float) (0.5*Toolkit.getDefaultToolkit().getScreenSize().height), 0);
		             helloText.setText("Game Over");
		             guiNode.attachChild(helloText);
	        		rootNode.detachAllChildren();
	        		stonePick=false;
	        		fakeballPick=false;
	        		swordPick=false;
	        		elephantPick=false;
	        		guiNode.detachChild(textlv2);
	        		rootNode.removeLight(dl);
	        		rootNode.removeLight(am);
					
					class level2Task extends TimerTask{
						public void run(){
							restartlevel2=true;
						}
					}time.schedule(new level2Task(), 2000);      		
		        }
		        
		        if(target.getName().equals("Empty2")){
		        	  textlv3.setText("      I have been trapped here for days\n"+"find a bomb and explose the tower\n"+"Free me,please!!");
		        }   

		        
		        if(target.getName().equals("Bomb")){
		        	  rootNode.detachChild(cube2Geo);
		        	  textlv3.setText("      A box of dynamite acquired");
		        	  bombPick=true;
		        }   
		        
		        if(target.getName().equals("lv3Ball1")&&ball32){
		        	  rootNode.detachChild(Ball31);
		        	  textlv3.setText("      TRINITY Protons Acquired");
		        	  ball31=true;
		          }
		        
		        if(target.getName().equals("lv3Ball2")&&ball31){
		        	  rootNode.detachChild(Ball32);
		        	  textlv3.setText("      TRINITY Protons Acquired");
		        	  ball32=true;
		          }
		        
		        if(target.getName().equals("lv3Ball2")&&(!ball31)){
		        	  rootNode.detachChild(Ball32);
		        	 textlv3.setText("      Maybe another proton is in the vicinty\n"+"Proton information:\n"+"Be careful with a Ninja!");
		        	  ball32=true;
		          }
		        
		        if(target.getName().equals("lv3Ball1")&&(!ball32)){
		        	  rootNode.detachChild(Ball31);
		        	 textlv3.setText("      Maybe another proton is in the vicinty\n"+"Proton information:\n"+"Be careful with a Ninja!");
		        	  ball31=true;
		          }
		        if(target.getName().equals("Baguareal")&&(ball32)&&(ball31)){
		        	 textlv3.setText("        ");
		        	 initExplosion();
		        	 startspin=true;
		        	 class level3Task1 extends TimerTask{
							public void run(){
								getback=true;
								startspin=false;
							}
						}time.schedule(new level3Task1(), 8000);
					
		        	 
		        	 
		        	 
		          }
		        if(target.getName().equals("brick")&&!value&&(bombPick)){
		        	
		        	
		        	guiFont = assetManager.loadFont("Interface/Fonts/font.fnt");
					helloText = new BitmapText(guiFont, false);
					helloText.setText("           Game Over\n"+"You killed yourself in explosion");
					helloText.setSize(100f);
					helloText.setColor(ColorRGBA.Red);
					helloText.setLocalTranslation(Toolkit.getDefaultToolkit()
							.getScreenSize().width*0.15f,  Toolkit
							.getDefaultToolkit().getScreenSize().height*0.7f, 0);
					guiNode.attachChild(helloText);
					guiNode.detachChild(textlv3);
		        	viewPort.clearProcessors();
		        	rootNode.detachAllChildren();
		        	rootNode.removeLight(l);
		        	rootNode.removeLight(s);
		        	waves.stop();
		        	initBoom() ;
		        	
		        	class delay10 extends TimerTask{
		        		public void run(){
		        			restartlevel3=true;
		        		}
		        	}time.schedule(new delay10(), 2000);
		        }
			}
		}
	}
	};

	private void initCrossHairs() {
		guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		BitmapText ch = new BitmapText(guiFont, false);
		ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
		ch.setText("+"); // crosshairs
		ch.setLocalTranslation(
				// center
				settings.getWidth() / 2
						- guiFont.getCharSet().getRenderedSize() / 3 * 2,
				settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
		guiNode.attachChild(ch);
	}

	public void initFade(int n) {
		fpp = new FilterPostProcessor(assetManager);
		fade = new FadeFilter(n); // e.g. 2 seconds
		fpp.addFilter(fade);
		viewPort.addProcessor(fpp);
	}

private void createBox() {
    	
    	bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);

    	
        Sphere ball31 = new Sphere(32,32, 3f);
        Ball31 = new Geometry("lv3Ball1", ball31);
        Material sphereMatm = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sphereMatm.setTexture("ColorMap", assetManager.loadTexture("scene/ball32.jpg"));
        Ball31.setMaterial(sphereMatm);
        Ball31.setLocalTranslation(-280,235,-450);
        rootNode.attachChild(Ball31);
       
        Sphere ball32 = new Sphere(32,32, 3f);
        Ball32 = new Geometry("lv3Ball2", ball32);
        Material sphereMatx = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sphereMatx.setTexture("ColorMap", assetManager.loadTexture("scene/ball31.jpg"));
        Ball32.setMaterial(sphereMatx);
        Ball32.setLocalTranslation(350,100,-480);
        rootNode.attachChild(Ball32);
        

    	Box cube2Mesh = new Box(3f,6f,3f);
	    cube2Geo = new Geometry("Bomb", cube2Mesh);
	    Material cube2Mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	    cube2Mat.setTexture("ColorMap", assetManager.loadTexture("scene/bomb2.png"));
	    cube2Geo.setMaterial(cube2Mat);
	    rootNode.attachChild(cube2Geo);
	    cube2Geo.setLocalTranslation(63,249,63); 		
    	
	 
	    		
	    Box bagua = new Box(20f,0.1f,20f);
	    cube2Geo2 = new Geometry("Bagua", bagua);
	    Material cube2Mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	    cube2Mat2.setTexture("ColorMap", assetManager.loadTexture("scene/bagua.jpg"));
	    cube2Geo2.setMaterial(cube2Mat2);
	    cube2Geo2.setLocalTranslation(-260,60,-160); 
	    cube2Geo2.addControl(new RigidBodyControl(0));
		cube2Geo2.getControl(RigidBodyControl.class).getCollisionShape();
		bulletAppState3.getPhysicsSpace().add(cube2Geo2);
	    rootNode.attachChild(cube2Geo2);
    	
    	
	    Box baguareal = new Box(20f,0.1f,20f);
	    cube2Geo2ba = new Geometry("Baguareal", baguareal);
	    Material cube2Mat2ba = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	    cube2Mat2ba.setTexture("ColorMap", assetManager.loadTexture("scene/bagua.jpg"));
	    cube2Geo2ba.setMaterial(cube2Mat2ba);
	    cube2Geo2ba.setLocalTranslation(new Vector3f(-180f, 75f,
				450f)); 
	    cube2Geo2ba.addControl(new RigidBodyControl(0));
		cube2Geo2ba.getControl(RigidBodyControl.class).getCollisionShape();
		bulletAppState3.getPhysicsSpace().add(cube2Geo2ba);
	    rootNode.attachChild(cube2Geo2ba);
	   
	    
	    
	    
	    
	    
	    
	    Spatial ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        ninja.scale(0.03f, 0.03f, 0.03f);
        ninja.rotate(0.0f, 0.0f, 0.0f);
        ninja.setLocalTranslation(-260,60f,-160);
        rootNode.attachChild(ninja);
	    
        Box empty = new Box( 2f,7f,2f);
        cube1Geo7 = new Geometry("Empty2", empty);
        cube1Geo7.setLocalTranslation(-260,60f,-160);
        Material cube1Matc7 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture cube1Texc7 = assetManager.loadTexture("scene/Transparent.png");
        cube1Matc7.setTexture("ColorMap", cube1Texc7);
        cube1Geo7.setMaterial(cube1Matc7);
        cube1Matc7.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        cube1Geo7.setQueueBucket(Bucket.Transparent);
        cube1Geo7.addControl(new RigidBodyControl(0));
		cube1Geo7.getControl(RigidBodyControl.class).getCollisionShape();
		bulletAppState.getPhysicsSpace().add(cube1Geo7);
        rootNode.attachChild(cube1Geo7);
    }

	public void back(){
		keyMouse();
		rootNode.detachAllChildren();
		guiNode.detachAllChildren();
		rootNode.addLight(sun);
		rootNode.attachChild(sky);
		rootNode.attachChild(root);
		if(startLevel1){
			assetManager.unregisterLocator("wildhouse.zip", ZipLocator.class);
			rootNode.removeLight(sun1);
			rootNode.removeLight(sun2);
			if(islighton)
				rootNode.removeLight(bublight);						
		}
		if(startLevel2){
			assetManager.unregisterLocator("quake3level.zip", ZipLocator.class);
			rootNode.removeLight(dl);
			rootNode.removeLight(am);
		}
		if(startLevel3){
			
			rootNode.removeLight(s);
			rootNode.removeLight(l);
			viewPort.clearProcessors();
			waves.stop();
			water3.setEnabled(false);
		}
		player = new PhysicsCharacter(new SphereCollisionShape(5), .01f);
		player.setPhysicsLocation(new Vector3f(0,0,10));
		player.setWalkDirection(new Vector3f(0,0,-1)); 
		flyCam.setEnabled(false);
	
		cam.setFrame(camLocation1, camLeft1, camUp1, camDir1);
		inputManager.addListener(actionListener);
		inputManager.removeListener(action);
	}
	
	public void allpass(){
		startspin=false;
		
		water3.setEnabled(false);
		
		keyMouse();
		rootNode.detachAllChildren();
		guiNode.detachAllChildren();
		
		 Sphere ball31 = new Sphere(32,32, 1f);
	        Ball31 = new Geometry("ball31", ball31);
	        Material sphereMatm = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	        sphereMatm.setTexture("ColorMap", assetManager.loadTexture("scene/ball32.jpg"));
	        Ball31.setMaterial(sphereMatm);
	        Ball31.setLocalTranslation(-8, -3, -23);
	        rootNode.attachChild(Ball31);
	        Sphere ball32 = new Sphere(32,32, 1f);
	        Ball32 = new Geometry("ball32", ball32);
	        Material sphereMatx = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	        sphereMatx.setTexture("ColorMap", assetManager.loadTexture("scene/ball31.jpg"));
	        Ball32.setMaterial(sphereMatx);
	        Ball32.setLocalTranslation(-10,0,-20);
	        rootNode.attachChild(Ball32);  
	        
	        
	        Sphere ball11 = new Sphere(32,32, 1f);
	        Ball11 = new Geometry("ball11", ball11);
	        Material sphereMatball11 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	        sphereMatball11.setTexture("ColorMap", assetManager.loadTexture("scene/waterball.jpg"));
	        Ball11.setMaterial(sphereMatball11);
	        Ball11.setLocalTranslation(-13, 5f, -19);
	        rootNode.attachChild(Ball11);
		
	        Sphere ball21 = new Sphere(32,32, 1f);
	        Ball21 = new Geometry("ball21", ball21);
	        Material sphereMatball21 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	        sphereMatball21.setTexture("ColorMap", assetManager.loadTexture("scene/stone.jpg"));
	        Ball21.setMaterial(sphereMatball21);
	        Ball21.setLocalTranslation(-10,-5,-17);
	        rootNode.attachChild(Ball21);
	        
	        permitpass2=true;
	        

	        
	        
	        emit = new ParticleEmitter("Emitter", Type.Triangle, 300);
			emit.setShape(new EmitterSphereShape(Vector3f.ZERO, 90f));
			emit.setGravity(0, 0, 150);
			emit.setLowLife(5);
			emit.setHighLife(10);
			emit.setInitialVelocity(new Vector3f(0, 0, 0));
			emit.setImagesX(15);
			emit.setNumParticles(1000);
			emit.setParticlesPerSec(300);

			Material mat = new Material(assetManager,
					"Common/MatDefs/Misc/Particle.j3md");
			mat.setTexture("Texture",
					assetManager.loadTexture("Effects/Smoke/Smoke.png"));
			emit.setMaterial(mat);
			emit.setStartColor(ColorRGBA.Blue);
			emit.setEndColor(ColorRGBA.White);
			emit2 = emit.clone();
			emit2.move(3, 0, 0);

			//rootNode.detachAllChildren();
			rootNode.attachChild(emit);
			rootNode.attachChild(emit2);  
	        
	        

		player = new PhysicsCharacter(new SphereCollisionShape(5), .01f);
		player.setPhysicsLocation(new Vector3f(0,0,10));
		player.setWalkDirection(new Vector3f(0,0,-1)); 
		flyCam.setEnabled(false);
	
		cam.setFrame(camLocation1, camLeft1, camUp1, camDir1);
		inputManager.addListener(actionListener);
		inputManager.removeListener(action);
		allpasstext();
		waves.stop();
		initEndMusic();
	}
	
	public void allpasstext(){
		tcount=0;
		guiFont = assetManager.loadFont("Interface/Fonts/font.fnt");
		helloText = new BitmapText(guiFont, false);
		helloText.setSize(40f);
		helloText.setLocalTranslation((float) (Toolkit.getDefaultToolkit()
				.getScreenSize().width * 0.5), (float) (0.5 * Toolkit
				.getDefaultToolkit().getScreenSize().height), 0);
		guiNode.attachChild(helloText);
		Timer timer = new Timer();
		class MyTask extends TimerTask {
			public void run() {
				if (tcount == 0) {
					helloText.setText("            机智如你");
				}
				if (tcount == 1) {
					helloText.setText(" 三体人在地球上安插的间谍智子已全部被找到");
				}
				if (tcount == 2) {
					helloText.setText("同时发现了他们在地球上\n"+"暗中建造的降维攻击曲率引擎");
				}
				if (tcount == 3) {
					helloText.setText("  人类通过智子改写了攻击目标并驱动装置");
				}
				if (tcount == 4)
					helloText.setText("    将三体空间降低到二维宇宙");
				if (tcount == 5)
					helloText.setText("       最终解除了三体危机\n"+"以及三体对宇宙中其他生命星球的威胁");
				
				if (tcount == 6) {
					helloText.setText("      感谢您的参与");
					
					
				}
				if(tcount==7){
					floatUp=true;
					helloText.setText("       开发人员：\n"+
				                      "       庞云奎     裴玉林\n"+
							          "       潘凌伟      李昊朔\n"+
				                      "\n\n"+
				                      "       特别感谢JME引擎的开发者\n"+
				                      "       以及JME开源社区的贡献者");
				}
				if (tcount == 10) {
					permitpass=true;
					floatUp=false;
					cancel();
					
				}
				
				tcount++;
			}
		}
		timer.schedule(new MyTask(), 1500, 2500);
	}
	
	public void initPass() {

		audio_nature = new AudioNode(assetManager, "Audio/gettin.wav", true);
		audio_nature.setLooping(false); // activate continuous playing
		audio_nature.setPositional(false);
		audio_nature.setVolume(3);
		rootNode.attachChild(audio_nature);
		audio_nature.play();
	}

	public void initExplosion(){
		ParticleEmitter fire = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
        Material mat_red = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat_red.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/shockwave.png"));
        fire.setMaterial(mat_red);
        fire.setImagesX(2); fire.setImagesY(2); // 2x2 texture animation
        fire.setStartColor(ColorRGBA.Blue);   // red
        fire.setEndColor(ColorRGBA.White); // yellow
        fire.setInitialVelocity(new Vector3f(0, 2, 0));
        fire.setStartSize(1.5f);
        fire.setEndSize(0.1f);
        fire.setGravity(0, -30, 0);
        fire.setLowLife(2f);
        fire.setHighLife(10f);
        fire.setVelocityVariation(1f); 
        fire.setParticlesPerSec(500);
        fire.setNumParticles(1000);
        fire.setLocalTranslation(new Vector3f(-180f, 75f,
				450f));
        rootNode.attachChild(fire);
  
        

	}
	
	public void initBoom() {
		audio_boom = new AudioNode(assetManager, "Audio/bomb.wav", true);
		audio_boom.setLooping(false); // activate continuous playing
		audio_boom.setPositional(false);
		audio_boom.setVolume(3);
		rootNode.attachChild(audio_boom);
		audio_boom.play();
	}
	
	public void initEndMusic() {
		audio_end = new AudioNode(assetManager, "Audio/end.wav", true);
		audio_end.setLooping(false); // activate continuous playing
		audio_end.setPositional(false);
		audio_end.setVolume(3);
		rootNode.attachChild(audio_end);
		audio_end.play();
	}
}
