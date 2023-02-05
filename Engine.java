public class Engine {
    
    private Engine() {
        Window.GetInstance();
    }

    public static Engine GetInstance(){
        if (instance == null)
            instance = new Engine();
        return instance;
    }

    public synchronized void EngineLoop() {
        BeginLoop();



        EndLoop();
    }

    private void BeginLoop(){
        //Timer.GetInstance().Update();
    }

    private void EndLoop() {
        Mouse.GetInstance().Pop();
        Keyboard.GetInstance().Pop();
    }

    private static Engine instance = null;
}
