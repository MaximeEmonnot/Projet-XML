import java.awt.Color;
import java.awt.Point;

public class Engine {
    
    private Engine() {}

    public static Engine GetInstance(){
        if (instance == null) instance = new Engine();
        return instance;
    }

    public synchronized void EngineLoop() {
        BeginLoop();
        Update();
        Draw();
        EndLoop();
    }

    private void Update(){

    }
    private void Draw(){
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.MAGENTA);
        GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite("Images/test.png"), Mouse.GetInstance().GetMousePos(), 1);
    }

    private void BeginLoop(){
        Timer.GetInstance().Update();
    }

    private void EndLoop() {
        Mouse.GetInstance().Pop();
        Keyboard.GetInstance().Pop();

        GraphicsSystem.GetInstance().Render();
    }

    private static Engine instance = null;
}
