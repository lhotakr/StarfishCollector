import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;

public class TurtleGame extends BaseGame {


    public void create()
    {

        // initialize resources common to multiple screens and store to skin database
        BitmapFont uiFont = new BitmapFont(Gdx.files.internal("assets/cooper.fnt"));
        uiFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        skin.add("uiFont",uiFont);

        LabelStyle uiLabelStyle = new LabelStyle(uiFont, Color.BLUE);
        skin.add("uiLabelStyle", uiLabelStyle);

        TextButtonStyle uiTextButtonStyle = new TextButtonStyle();
        uiTextButtonStyle.font = uiFont;
        uiTextButtonStyle.fontColor = Color.NAVY;
        Texture upTex = new Texture(Gdx.files.internal("assets/ninepatch-1.png"));
        skin.add("buttonUp", new NinePatch(upTex, 26, 26, 16, 20));
        uiTextButtonStyle.up = skin.getDrawable("buttonUp");

        Texture overTex = new Texture(Gdx.files.internal("assets/ninepatch-2.png"));
        skin.add("buttonOver", new NinePatch(overTex, 26,26,16,20) );
        uiTextButtonStyle.over = skin.getDrawable("buttonOver");
        uiTextButtonStyle.overFontColor = Color.BLUE;

        Texture downTex = new Texture(Gdx.files.internal("assets/ninepatch-3.png"));
        skin.add("buttonDown", new NinePatch(downTex, 26,26,16,20) );
        uiTextButtonStyle.down = skin.getDrawable("buttonDown");
        uiTextButtonStyle.downFontColor = Color.BLUE;
        skin.add("uiTextButtonStyle", uiTextButtonStyle);

        TurtleMenu tm = new TurtleMenu(this);
        setScreen(tm);

        SliderStyle uiSliderStyle = new SliderStyle();
        skin.add("sliderBack", new Texture(Gdx.files.internal("assets/slider-after.png")));
        skin.add("sliderKnob", new Texture(Gdx.files.internal("assets/slider-knob.png")));
        skin.add("sliderAfter", new Texture(Gdx.files.internal("assets/slider-after.png")));
        skin.add("sliderBefore", new Texture(Gdx.files.internal("assets/slider-before.png")));

        uiSliderStyle.background = skin.getDrawable("sliderBack");
        uiSliderStyle.knob = skin.getDrawable("sliderKnob");
        uiSliderStyle.knobAfter = skin.getDrawable("sliderAfter");
        uiSliderStyle.knobBefore = skin.getDrawable("sliderBefore");

        skin.add("uiSliderStyle",uiSliderStyle);
    }
}
