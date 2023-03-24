package MainEngine.Levels;

import java.awt.*;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import CoreEngine.Mouse;

import java.util.ArrayList;

import GraphicsEngine.GraphicsSystem;
import MainEngine.LevelManager;
import ParserEngine.XMLManager;
import UIEngine.UIButton;
import UIEngine.UICard;

public class AnimalListLevel extends ALevel {

    public AnimalListLevel(String _name) throws Exception {
        super(_name);

        addNewAnimal = new UIButton(new Rectangle(925, 595, 250, 75), "Add new animal", 
        () -> { LevelManager.GetInstance().SetLevel("Add Animal Level"); }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
    }

    @Override
    public void OnBegin(Object... params) throws Exception {
       cards.clear();

       NodeList result = XMLManager.GetInstance().SimpleQuery("Assets/XML/animaux.xml", "//animal");
       
       for (int i = 0; i < result.getLength(); i++){
            Node node = result.item(i);
            // Récupération du nom
            String name = node.getAttributes().getNamedItem("name").getTextContent();

            // Récupération du GIF
            String gifPath = node.getChildNodes().item(1).getAttributes().getNamedItem("src").getTextContent();

            cards.add(new UICard(new Rectangle(0, 0, 250, 250), name, gifPath, () -> { System.out.println(name);},
             Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY));
       }
    }

    @Override
    public void Update() throws Exception {
        
        switch(Mouse.GetInstance().Read()){
            case WheelDown: if (++pageIndex * 8 >= cards.size()) pageIndex--; break;
            case WheelUp: if (--pageIndex < 0) pageIndex = 0; break;
            default: break;
        }

        for (int i = pageIndex * 8; i < ((pageIndex + 1) * 8 < cards.size() ? pageIndex * 8 + 8 : cards.size()); i++){
            UICard card = cards.get(i);
            
            // Définition de la position des Card
            card.SetRectangle(new Rectangle(100 + (i % 4) * 275, 50 + ((i - pageIndex * 8) / 4) * 275, 250, 250));

            // Update final
            card.Update();
        } 
        addNewAnimal.Update();
    }

    @Override
    public void Draw() throws Exception {
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.MAGENTA);

        for (int i = pageIndex * 8; i < ((pageIndex + 1) * 8 < cards.size() ? pageIndex * 8 + 8 : cards.size()); i++) cards.get(i).Draw(10);

        addNewAnimal.Draw(15);
    }
    
    private List<UICard> cards = new ArrayList<UICard>();
    private int pageIndex = 0;
    private UIButton addNewAnimal;
}
