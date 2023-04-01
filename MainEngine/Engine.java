package MainEngine;
import CoreEngine.Keyboard;
import CoreEngine.Mouse;
import CoreEngine.Timer;
import GraphicsEngine.GraphicsSystem;
import MainEngine.Levels.AddAnimalLevel;
import MainEngine.Levels.AdvancedSearchLevel;
import MainEngine.Levels.AnimalListLevel;
import MainEngine.Levels.AnimalDetailsLevel;

/*
 * Singleton représentant le moteur de l'applciation
 * Exécution des levels (Update, Draw)
 */
public class Engine {
    
    private Engine() throws Exception {
        // Initialisation des levels
        LevelManager.GetInstance().AddLevel(new AnimalListLevel("Animal List Level"));
        LevelManager.GetInstance().AddLevel(new AddAnimalLevel("Add Animal Level"));
        LevelManager.GetInstance().AddLevel(new AnimalDetailsLevel("Animal Details Level"));
        LevelManager.GetInstance().AddLevel(new AdvancedSearchLevel("Advanced Search Level"));
        
    }

    public static Engine GetInstance() throws Exception {
        if (instance == null) instance = new Engine();
        return instance;
    }

    // Boucle principale
    public synchronized void EngineLoop() throws Exception {
        BeginLoop();
        Update();
        Draw();
        EndLoop();
    }

    // Update du moteur (Level en cours dans le LevelManager)
    private void Update() throws Exception {
        LevelManager.GetInstance().Update();
    }
    // Draw du moteur (Level en cours dans le LevelManager)
    private void Draw() throws Exception {
        LevelManager.GetInstance().Draw();
    }

    // Début de la boucle principale (Update du Timer)
    private void BeginLoop(){
        Timer.GetInstance().Update();
    }

    // Fin de la boucle principale (Pop des évènements et affichage)
    private void EndLoop() {
        Mouse.GetInstance().Pop();
        Keyboard.GetInstance().Pop();

        GraphicsSystem.GetInstance().Render();
    }

    private static Engine instance = null;

}
