package MainEngine.Levels;

import java.awt.*;
import java.util.Arrays;

import CoreEngine.Mouse;
import GraphicsEngine.*;
import UIEngine.UIInputBox;

public class TestLevel extends ALevel {

    public TestLevel(String _name){
        super(_name);

        test = new UIInputBox(new Rectangle(15, 15, 64, 64), "Test");
    }

    @Override
    public void OnBegin(Object... params) {
       for(Object param : Arrays.asList(params)){
            System.out.println(param);
       }
    }

    @Override
    public void Update() throws Exception {
        test.Update();
    }

    @Override
    public void Draw() {
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.MAGENTA);
        GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite("Assets/Images/cat-standing.gif"), Mouse.GetInstance().GetMousePos(), 1);
   
        test.Draw(3);
    }
    
    UIInputBox test;
}
