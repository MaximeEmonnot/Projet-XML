package MainEngine.Levels;

import java.awt.*;

import GraphicsEngine.GraphicsSystem;
import UIEngine.UICard;

public class AnimalListLevel extends ALevel {

    public AnimalListLevel(String _name) throws Exception {
        super(_name);
       
        card = new UICard(new Rectangle(15, 15, 300, 500), "Test", "Assets/GIF/vache.gif", 
        () -> {System.out.println("Hello");}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
    }

    @Override
    public void OnBegin(Object... params) throws Exception {
       
    }

    @Override
    public void Update() throws Exception {
        card.Update();
    }

    @Override
    public void Draw() throws Exception {
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.MAGENTA);

        card.Draw(10);
    }
    
    private UICard card;
}
