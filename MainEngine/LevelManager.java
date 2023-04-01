package MainEngine;

import java.util.HashMap;
import java.util.Map;

import ExceptionEngine.EngineException;
import MainEngine.Levels.ALevel;

/*
 * Singleton responsable de la gestion des différents Level du moteur
 * Permet le lien entre les différents Level, en appelant le nom du level
 */
public class LevelManager {
    private LevelManager() {
        levels = new HashMap<String, ALevel>();
    }

    public static synchronized LevelManager GetInstance() {
        if (instance == null) instance = new LevelManager();
        return instance;
    }

    // Update du Level en cours
    public void Update() throws Exception {
        currentLevel.Update();
    }

    // Draw du Level en cours
    public void Draw() throws Exception {
        currentLevel.Draw();
    }

    // Ajout d'un Level, avec appel de la méthode OnBegin (avec paramètres) si premier Level ajouté
    public void AddLevel(ALevel newLevel, Object... params) throws Exception {
        if (!levels.values().contains(newLevel)) levels.put(newLevel.GetName(), newLevel);
        else throw new EngineException("Error : This level already exists");

        if (currentLevel == null) {
            currentLevel = newLevel;
            currentLevel.OnBegin(params);
        }
    }

    // Changement de Level
    public void SetLevel(String nextLevel, Object... params) throws Exception {
        if (!levels.containsKey(nextLevel)) throw new EngineException("Error : This level doesn't exist");

        currentLevel = levels.get(nextLevel);
        currentLevel.OnBegin(params);
    }

    private static LevelManager instance;
    private Map<String, ALevel> levels;
    private ALevel currentLevel;
}
