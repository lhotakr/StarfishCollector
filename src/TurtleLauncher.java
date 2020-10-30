import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class TurtleLauncher {
    public static void main (String[] args)
    {
        //System.err.close();
        //System.setErr(System.out);

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        // change config settings
        config.width = 1000;
        config.height = 800;
        config.title = "Starfish Collector";

        TurtleGame myProgram = new TurtleGame();
        LwjglApplication launcher = new LwjglApplication(myProgram);
    }

}
