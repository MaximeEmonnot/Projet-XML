package MainEngine.Levels;

import java.awt.Color;

import GraphicsEngine.GraphicsSystem;

public class AdvancedSearchLevel extends ALevel {

    public AdvancedSearchLevel(String _name) {
        super(_name);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void OnBegin(Object... params) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void Update() throws Exception {
        // TODO Auto-generated method stub
      
    }

    @Override
    public void Draw() throws Exception {
        // TODO Auto-generated method stub
       GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
    }
    
}
