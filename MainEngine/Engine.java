package MainEngine;
import java.awt.*;

import CoreEngine.Keyboard;
import CoreEngine.Mouse;
import CoreEngine.Timer;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.SpriteFactory;

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
    }
    private void Draw(){
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.MAGENTA);
        GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite("Assets/Images/test.png"), Mouse.GetInstance().GetMousePos(), 1);
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