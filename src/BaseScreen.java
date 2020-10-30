import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class BaseScreen implements Screen, InputProcessor {

    protected Game game;

    protected Stage mainStage;
    protected Stage uiStage;
    protected Table uiTable;

    public final int viewWidth = 640;
    public final int viewHeight = 480;

    private boolean paused;


    public BaseScreen(Game g)
    {

        game = g;

        mainStage = new Stage (new FitViewport(viewWidth, viewHeight));
        uiStage = new Stage(new FitViewport(viewWidth, viewHeight));

        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);

        paused = false;

        InputMultiplexer im = new InputMultiplexer(this, uiStage, mainStage);
        Gdx.input.setInputProcessor(im);

        create();
    }

    public abstract void create();

    public abstract void update(float dt);

    // gameloop code; update, then render
    public void render(float dt)
    {
        uiStage.act(dt);

        // only pause gameplay events, not UI events
        if(!isPaused())
        {
            mainStage.act(dt);
            update(dt);
        }

        // render
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
    }

    // pause methods
    public boolean isPaused()
    { return paused; }

    public void setPaused(boolean b)
    { paused = b; }

    public void togglePaused()
    { paused = !paused; }

    public void resize(int width, int height)
    {
        mainStage.getViewport().update(width,height,true);
        uiStage.getViewport().update(width,height,true);
    }

    // methods required by Screen interface
    public void pause() {}
    public void resume() {}
    public void dispose() {}
    public void show() {}
    public void hide() {}

    // methods required by InputProcessor interface
    public boolean keyDown(int keycode)
    { return false; }
    public boolean keyUp(int keycode)
    { return false; }
    public boolean keyTyped(char c)
    { return false; }
    public boolean mouseMoved(int screenX, int screenY)
    { return false; }
    public boolean scrolled(int amount)
    { return false; }
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    { return false; }
    public boolean touchDragged(int screenX, int screenY, int pointer)
    { return false; }
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    { return false; }

}