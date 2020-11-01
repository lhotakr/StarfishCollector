import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import org.w3c.dom.Text;

public class TurtleMenu extends BaseScreen {
    public TurtleMenu(BaseGame g)
    { super (g);}

    public void create()
    {
        Texture waterTex = new Texture(Gdx.files.internal("assets/water.jpg"));
        waterTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        game.skin.add("waterTex", waterTex);
        uiTable.background(game.skin.getDrawable("waterTex"));

        Texture titleTex = new Texture(Gdx.files.internal("assets/starfish-collector.png"));

    }

    public void update(float dt)
    {

    }

}
