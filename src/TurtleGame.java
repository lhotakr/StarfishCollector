import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class TurtleGame extends BaseGame {


    public void create()
    {

        // initialize resources common to multiple screens and store to skin database
        BitmapFont uiFont = new BitmapFont(Gdx.files.internal("assets/cooper.fnt"));
        uiFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        skin.add("uiFont",uiFont);

        LabelStyle uiLabelStyle = new LabelStyle(uiFont, Color.BLUE);
        skin.add("uiLabelStyle", uiLabelStyle);

        // ini & start main game
        TurtleLevel tl = new TurtleLevel(this);
        setScreen(tl);

    }
}
