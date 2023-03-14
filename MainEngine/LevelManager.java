package MainEngine;

import java.util.HashMap;
import java.util.Map;

import ExceptionEngine.EngineException;
import MainEngine.Levels.ALevel;

public class LevelManager {
    private LevelManager() {
        levels = new HashMap<String, ALevel>();
    }

    public static synchronized LevelManager GetInstance() {
        if (instance == null) instance = new LevelManager();
        return instance;
    }


    public void Update() throws Exception {
        currentLevel.Update();
    }

    public void Draw() throws Exception {
        currentLevel.Draw();
    }

    public void AddLevel(ALevel newLevel, Object... params) throws Exception {
        if (!levels.values().contains(newLevel)) levels.put(newLevel.GetName(), newLevel);
        else throw new EngineException("Error : This level already exists");

        if (currentLevel == null) {
            currentLevel = newLevel;
            currentLevel.OnBegin(params);
        }
    }

    public void SetLevel(String nextLevel, Object... params) throws Exception {
        if (!levels.containsKey(nextLevel)) throw new EngineException("Error : This level doesn't exist");

        currentLevel = levels.get(nextLevel);
        currentLevel.OnBegin(params);
    }

    private static LevelManager instance;
    private Map<String, ALevel> levels;
    private ALevel currentLevel;
}
