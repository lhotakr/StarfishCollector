import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TurtleLevel extends BaseScreen {

    private BaseActor ocean;
    private ArrayList<BaseActor> rockList;
    private ArrayList<BaseActor> starfishList;
    private PhysicsActor turtle;
    private int mapWidth = 800;
    private int mapHeight = 600;

    private float audioVolume;
    private Sound waterDrop;
    private Music instrumental;
    private Music oceanSurf;

    public TurtleLevel (BaseGame g)
    { super(g); }

    private Label starfishLeftLabel;

    public void create()
    {

        ocean = new BaseActor();
        ocean.setTexture(new Texture(Gdx.files.internal("assets/water.jpg")));
        ocean.setPosition(0,0);
        mainStage.addActor(ocean);

        BaseActor overlay = ocean.clone();
        overlay.setPosition(-50, -50);
        overlay.setColor(1,1,1, 0.25f);
        uiStage.addActor(overlay);

        BaseActor rock = new BaseActor();
        rock.setTexture(new Texture(Gdx.files.internal("assets/rock.png")));
        rock.setEllipseBoundary();
        rockList = new ArrayList<BaseActor>();
        int[] rockCoords = {200,0, 200,100, 250,200, 360,200, 470,200 };
        for (int i = 0; i < 5; i++)
        {
            BaseActor r = rock.clone();
            // obtain coords from the array, both x and y, at the same time
            r.setPosition(rockCoords[2*i], rockCoords[2*i+1]);
            mainStage.addActor(r);
            rockList.add(r);
        }

        BaseActor starfish = new BaseActor();
        starfish.setTexture(new Texture(Gdx.files.internal("assets/starfish.png")));
        starfish.setEllipseBoundary();

        starfishList = new ArrayList<BaseActor>();
        int[] starfishCoords = {400,100, 100,400, 150,200, 100, 100};
        for (int i = 0; i < 4; i++)
        {
            BaseActor s = starfish.clone();
            s.setPosition(starfishCoords[2*i], starfishCoords[2*i+1]);
            mainStage.addActor(s);
            starfishList.add(s);
        }

        turtle = new PhysicsActor();
        TextureRegion[] frames = new TextureRegion[6];
        for (int n = 1; n <= 6; n++)
        {
            String fileName = "assets/turtle-" + n + ".png";
            Texture tex = new Texture(Gdx.files.internal(fileName));
            tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            frames[n-1] = new TextureRegion(tex);
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);

        Animation anim = new Animation(0.1f, framesArray, PlayMode.LOOP);
        turtle.storeAnimation("swim", anim);

        Texture frame1 = new Texture(Gdx.files.internal("assets/turtle-1.png"));
        turtle.storeAnimation("rest", frame1);

        turtle.setOrigin(turtle.getWidth()/2, turtle.getHeight()/2);
        turtle.setPosition(20, 20);
        turtle.setRotation(90);
        turtle.setEllipseBoundary();
        turtle.setMaxSpeed(100);
        turtle.setDeceleration(200);
        mainStage.addActor(turtle);

        waterDrop = Gdx.audio.newSound(Gdx.files.internal("assets/Water_Drop.ogg"));
        instrumental = Gdx.audio.newMusic(Gdx.files.internal("assets/Master_of_the_Feast.ogg"));
        oceanSurf = Gdx.audio.newMusic(Gdx.files.internal("assets/Ocean_Waves.ogg"));

        starfishLeftLabel = new Label("Starfish Left: 8",game.skin,"uiLabelStyle");

        audioVolume = 0.8f;
        instrumental.setLooping(true);
        instrumental.setVolume(audioVolume);
        instrumental.play();
        oceanSurf.setLooping(true);
        oceanSurf.setVolume(audioVolume);
        oceanSurf.play();

        uiTable.add(starfishLeftLabel);

        Texture pauseTexture = new Texture(Gdx.files.internal("assets/pause.png"));
        game.skin.add("pauseImage", pauseTexture);
        ButtonStyle pauseStyle = new ButtonStyle();
        pauseStyle.up = game.skin.getDrawable("pauseImage");
        Button pauseButton = new Button(pauseStyle);

        pauseButton.addListener(
                new InputListener()
                {
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
                    {
                        togglePaused();
                        return true;
                    }
                }
        );

        uiTable.pad(10);
        uiTable.add(starfishLeftLabel);
        uiTable.add().expandX();
        uiTable.add(pauseButton);
        uiTable.row();
        uiTable.add().colspan(3).expandY();

        overlay.toBack();

    }

    public void update(float dt)
    {
        // process input
        turtle.setAccelerationXY(0,0);

        if (Gdx.input.isKeyPressed(Keys.LEFT))
            turtle.rotateBy(90*dt);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            turtle.rotateBy(-90*dt);
        if (Gdx.input.isKeyPressed(Keys.UP))
            turtle.accelerateForward(100);

        // set correct animation
        if (turtle.getSpeed() > 1 && turtle.getAnimationName().equals("rest"))
            turtle.setActiveAnimation("swim");
        if (turtle.getSpeed() < 1 && turtle.getAnimationName().equals("swim"))
            turtle.setActiveAnimation("rest");

        // bound turtle to the screen
        turtle.setX(MathUtils.clamp(turtle.getX(), 0, mapWidth - turtle.getWidth()));
        turtle.setY(MathUtils.clamp(turtle.getY(), 0, mapHeight - turtle.getHeight()));

        for (BaseActor r : rockList)
        {
            turtle.overlaps(r, true);
        }

        ArrayList<BaseActor> removeList = new ArrayList<BaseActor>();
        for (BaseActor s : starfishList)
        {
            if (turtle.overlaps(s, false))
                removeList.add(s);
        }
        for (BaseActor b : removeList)
        {
            b.remove();             // remove from stage
            starfishList.remove(b); // remove from list used by update
            waterDrop.play(audioVolume);
        }

        starfishLeftLabel.setText( "Starfish Left: " + starfishList.size() );
    }

    public void dispose()
    {
        waterDrop.dispose();
        instrumental.dispose();
        oceanSurf.dispose();
    }

}