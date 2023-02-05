import java.awt.Color;

public class Engine {
    
    private Engine() {}

    public static Engine GetInstance(){
        if (instance == null) instance = new Engine();
        return instance;
    }

    public synchronized void EngineLoop() throws Exception {
        BeginLoop();
        Update();
        Draw();
        EndLoop();
    }

    private void Update() throws Exception {
        switch (Mouse.GetInstance().Read()){
        case LRelease:
            AudioManager.GetInstance().PlaySound("Sounds/free_bird.wav");
            break;
        case RRelease:
            AudioManager.GetInstance().StopSound();
            break;
        default:
            break;
        }
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
