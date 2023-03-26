package MainEngine.Levels;

import java.awt.*;
import java.util.List;

import javax.xml.xquery.XQResultSequence;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import GraphicsEngine.GraphicsSystem;
import MainEngine.LevelManager;
import ParserEngine.XMLManager;
import UIEngine.UIButton;
import UIEngine.UICard;
import UIEngine.UIInputBox;

public class AnimalListLevel extends ALevel {

    public AnimalListLevel(String _name) throws Exception {
        super(_name);

        addNewAnimal = new UIButton(new Rectangle(925, 590, 250, 75), "Ajouter un animal", 
        () -> { LevelManager.GetInstance().SetLevel("Add Animal Level"); }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        previous = new UIButton(new Rectangle(1190, 162, 50, 150), "↑",
        () -> { pageIndex--; }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        next = new UIButton(new Rectangle(1190, 312, 50, 150), "↓",
        () -> { pageIndex++; }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        searchBox = new UIInputBox(new Rectangle(100, 590, 420, 75), "entrez une requête xpath");
        searchButton = new UIButton(new Rectangle(520, 590, 105, 75), "Rechercher", 
        () -> { 
            try{
                bHasSearchError = false;
                InitializeListFromResult(XMLManager.GetInstance().SimpleQuery("Assets/XML/animaux.xml", searchBox.GetText()));
            }
            catch(Exception e){
                bHasSearchError = true;
            }
        },
         Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        transformHTML = new UIButton(new Rectangle(650, 590, 250, 75), "Transformer un HTML",
        () -> {XMLManager.GetInstance().TransformDocument("Assets/XML/animaux.xml", "Assets/XML/animaux.xsl", "Assets/XML/animaux.html");}, 
        Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
    }

    @Override
    public void OnBegin(Object... params) throws Exception {
       cards.clear();
       pageIndex = 0;

       InitializeListFromResult(XMLManager.GetInstance().GetDocument("Assets/XML/animaux.xml").getElementsByTagName("animal"));
    }

    @Override
    public void Update() throws Exception {
        
        if (pageIndex - 1 >= 0) previous.Update();
        if ((pageIndex + 1) * 8 < cards.size()) next.Update(); 

        for (int i = pageIndex * 8; i < ((pageIndex + 1) * 8 < cards.size() ? pageIndex * 8 + 8 : cards.size()); i++){
            UICard card = cards.get(i);
            
            // Définition de la position des Card
            card.SetRectangle(new Rectangle(100 + (i % 4) * 275, 50 + ((i - pageIndex * 8) / 4) * 275, 250, 250));

            // Update final
            card.Update();
        } 
        addNewAnimal.Update();
        searchBox.Update();
        searchButton.Update();
        transformHTML.Update();
    }

    @Override
    public void Draw() throws Exception {
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);

        for (int i = pageIndex * 8; i < ((pageIndex + 1) * 8 < cards.size() ? pageIndex * 8 + 8 : cards.size()); i++) cards.get(i).Draw(10);

        if (pageIndex - 1 >= 0) previous.Draw(10);
        if ((pageIndex + 1) * 8 < cards.size()) next.Draw(10); 

        addNewAnimal.Draw(15);
        searchBox.Draw(15);
        searchButton.Draw(15);
        transformHTML.Draw(15);
        
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(1190, 590, 50, 50), Color.WHITE, true, 15);
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(1190, 590, 50, 50), Color.BLACK, false, 15);
        GraphicsSystem.GetInstance().DrawText(Integer.toString(pageIndex + 1) + " / " + Integer.toString((int)(cards.size() / 8) + (((cards.size() % 8) == 0) ? 0 : 1)), new Point(1200, 622), Color.BLACK, 15);
    
        if (bHasSearchError) GraphicsSystem.GetInstance().DrawText("Error : Wrong syntax", new Point(145, 655), Color.RED, 15);
    }

    private void InitializeListFromResult(XQResultSequence result) throws Exception {

        cards.clear();

        while (result.next()){
            Node node = result.getNode();

            // Récupération du nom
            String name = node.getAttributes().getNamedItem("nom").getTextContent();

            // Récupération du GIF
            NodeList childNodes = node.getChildNodes();
            String gifPath = "";
            for (int j = 0; j < childNodes.getLength(); j++){
                Node child = childNodes.item(j);
                if (child.getNodeName().equals("gif")){
                    gifPath = child.getAttributes().getNamedItem("src").getTextContent();
                    break;
                }
            }

            cards.add(new UICard(new Rectangle(0, 0, 250, 250), name, gifPath, () -> { LevelManager.GetInstance().SetLevel("Animal Details Level", node);},
            Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY));
        }
    }

    private void InitializeListFromResult(NodeList result){

        cards.clear();

        for (int i = 0; i < result.getLength(); i++){
            Node node = result.item(i);
            // Récupération du nom
            String name = node.getAttributes().getNamedItem("nom").getTextContent();

            // Récupération du GIF
            NodeList childNodes = node.getChildNodes();
            String gifPath = "";
            for (int j = 0; j < childNodes.getLength(); j++){
                Node child = childNodes.item(j);
                if (child.getNodeName().equals("gif")){
                    gifPath = child.getAttributes().getNamedItem("src").getTextContent();
                    break;
                }
            }

            cards.add(new UICard(new Rectangle(0, 0, 250, 250), name, gifPath, () -> { LevelManager.GetInstance().SetLevel("Animal Details Level", node);},
            Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY));
       }
    }
    
    private List<UICard> cards = new ArrayList<UICard>();
    private int pageIndex = 0;
    private UIButton addNewAnimal;
    private UIButton previous;
    private UIButton next;

    private UIInputBox searchBox;
    private UIButton searchButton;
    private boolean bHasSearchError = false;

    private UIButton transformHTML;
}
