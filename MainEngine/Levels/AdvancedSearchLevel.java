package MainEngine.Levels;

import java.awt.*;

import GraphicsEngine.GraphicsSystem;
import MainEngine.LevelManager;
import UIEngine.*;

public class AdvancedSearchLevel extends ALevel {

    public AdvancedSearchLevel(String _name) {
        super(_name);

        backButton = new UIButton(new Rectangle(100, 590, 250, 75), "Retour", 
        () -> { LevelManager.GetInstance().SetLevel("Animal List Level"); }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        searchButton = new UIButton(new Rectangle(925, 590, 250, 75), "Rechercher", 
        () -> { LevelManager.GetInstance().SetLevel("Animal List Level"); }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
    }

    @Override
    public void OnBegin(Object... params) throws Exception {  
    }

    @Override
    public void Update() throws Exception {
        backButton.Update();
        searchButton.Update();
    }

    @Override
    public void Draw() throws Exception {
       GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);

        backButton.Draw(10);
        searchButton.Draw(10);
    }   
    
    private UIButton backButton;
    private UIButton searchButton;
}
