import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TurtleMenu extends BaseScreen {
    public TurtleMenu(BaseGame g)
    { super (g);}



    public void create()
    {
        // pozadí vody
        Texture waterTex = new Texture(Gdx.files.internal("assets/water.jpg"));
        waterTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        game.skin.add("waterTex", waterTex);
        uiTable.background(game.skin.getDrawable("waterTex"));

        // nadpis
        Texture titleTex = new Texture(Gdx.files.internal("assets/starfish-collector.png"));
        titleTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        Image titleImage = new Image(titleTex);

        // "vytvořeno pomocí libGdx"
        Texture libgdxTex = new Texture(Gdx.files.internal("assets/created-libgdx.png"));
        libgdxTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        Image libgdxImage = new Image(libgdxTex);

        TextButton startButton = new TextButton("Start", game.skin, "uiTextButtonStyle");
        startButton.addListener(
                new InputListener()
                {
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
                    {
                        return true;
                    }
                    public void touchUp (InputEvent event, float x, float y, int pointer, int button)
                    {
                        game.setScreen(new TurtleLevel(game));
                    }
                }
        );

        TextButton quitButton = new TextButton("Quit", game.skin, "uiTextButtonStyle");
        quitButton.addListener(
                new InputListener()
                {
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
                    {
                        return true;
                    }
                    public void touchUp (InputEvent event, float x, float y, int pointer, int button)
                    {
                        Gdx.app.exit();
                    }
                }
        );

        float w = startButton.getWidth();
        uiTable.add(titleImage).colspan(2);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.add(quitButton).width(w);
        uiTable.row();
        uiTable.add(libgdxImage).colspan(2).right().padTop(50);


    }

    public void update(float dt)
    {

    }

}
