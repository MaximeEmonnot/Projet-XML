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

        searchEmbranchementBox = new UIInputBox(new Rectangle(100, 50, 250, 75), "Embranchement");
        searchClasseBox = new UIInputBox(new Rectangle(100, 125, 250, 75), "Classe");
        searchOrdreBox = new UIInputBox(new Rectangle(100, 200, 250, 75), "Ordre");
        searchFamilleBox = new UIInputBox(new Rectangle(100, 275, 250, 75), "Famille");
        searchGenreBox = new UIInputBox(new Rectangle(100, 350, 250, 75), "Genre");
        searchNomScientifiqueBox = new UIInputBox(new Rectangle(100, 425, 250, 75), "Nom scientifique");
        searchNomBox = new UIInputBox(new Rectangle(100, 500, 250, 75), "Nom");
        searchButton = new UIButton(new Rectangle(100, 590, 250, 75), "Rechercher", 
        () -> { 
            SimpleSearch();
        },
         Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

         advancedSearchButton = new UIButton(new Rectangle(375, 590, 250, 75), "Recherche avancée", 
         () -> { 
            LevelManager.GetInstance().SetLevel("Advanced Search Level");
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

       if (params.length != 0) InitializeListFromResult((NodeList)params[0]);
       else InitializeListFromResult(XMLManager.GetInstance().GetDocument("Assets/XML/animaux.xml").getElementsByTagName("animal"));
    }

    @Override
    public void Update() throws Exception {
        
        if (pageIndex - 1 >= 0) previous.Update();
        if ((pageIndex + 1) * 6 < cards.size()) next.Update(); 

        for (int i = pageIndex * 6; i < ((pageIndex + 1) * 6 < cards.size() ? pageIndex * 6 + 6 : cards.size()); i++){
            UICard card = cards.get(i);
            
            // Définition de la position des Card
            card.SetRectangle(new Rectangle(375 + (i % 3) * 275, 50 + ((i - pageIndex * 6) / 3) * 275, 250, 250));

            // Update final
            card.Update();
        } 
        addNewAnimal.Update();
        searchEmbranchementBox.Update();
        searchClasseBox.Update();
        searchOrdreBox.Update();
        searchFamilleBox.Update();
        searchGenreBox.Update();
        searchNomScientifiqueBox.Update();
        searchNomBox.Update();
        searchButton.Update();
        advancedSearchButton.Update();
        transformHTML.Update();
    }

    @Override
    public void Draw() throws Exception {
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);

        for (int i = pageIndex * 6; i < ((pageIndex + 1) * 6 < cards.size() ? pageIndex * 6 + 6 : cards.size()); i++) cards.get(i).Draw(10);

        if (pageIndex - 1 >= 0) previous.Draw(10);
        if ((pageIndex + 1) * 6 < cards.size()) next.Draw(10); 

        addNewAnimal.Draw(15);
        searchEmbranchementBox.Draw(15);
        searchClasseBox.Draw(15);
        searchOrdreBox.Draw(15);
        searchFamilleBox.Draw(15);
        searchGenreBox.Draw(15);
        searchNomScientifiqueBox.Draw(15);
        searchNomBox.Draw(15);
        searchButton.Draw(15);
        advancedSearchButton.Draw(15);
        transformHTML.Draw(15);
        
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(1190, 590, 50, 50), Color.WHITE, true, 15);
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(1190, 590, 50, 50), Color.BLACK, false, 15);
        GraphicsSystem.GetInstance().DrawText(Integer.toString(pageIndex + 1) + " / " + Integer.toString((int)(cards.size() / 6) + (((cards.size() % 6) == 0) ? 0 : 1)), new Point(1200, 622), Color.BLACK, 15);
    
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

    private void SimpleSearch() throws Exception {
        bHasSearchError = false;

        String name = searchNomBox.GetText();
        String embranchement = searchEmbranchementBox.GetText();
        String classe = searchClasseBox.GetText();
        String ordre = searchOrdreBox.GetText();
        String famille = searchFamilleBox.GetText();
        String genre = searchGenreBox.GetText();
        String scientificName = searchNomScientifiqueBox.GetText();

        String command = "//animal";

        String predicate = "";

        if (!name.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(@nom,\"" + name + "\")";
        if (!embranchement.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/embranchement,\"" + embranchement + "\")";
        if (!classe.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/classe,\"" + classe + "\")";
        if (!ordre.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/ordre,\"" + ordre + "\")";
        if (!famille.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/famille,\"" + famille + "\")";
        if (!genre.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/genre,\"" + genre + "\")";
        if (!scientificName.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/nom_scientifique,\"" + scientificName + "\")";

        if (!predicate.isEmpty()) command += "[" + predicate + "]";

        try {
            InitializeListFromResult(XMLManager.GetInstance().SimpleQuery("Assets/XML/animaux.xml", command));
        }
        catch(Exception e) {
            bHasSearchError = true;
        }
    }
    
    private List<UICard> cards = new ArrayList<UICard>();
    private int pageIndex = 0;
    private UIButton addNewAnimal;
    private UIButton previous;
    private UIButton next;

    private UIInputBox searchEmbranchementBox;
    private UIInputBox searchClasseBox;
    private UIInputBox searchOrdreBox;
    private UIInputBox searchFamilleBox;
    private UIInputBox searchGenreBox;
    private UIInputBox searchNomScientifiqueBox;
    private UIInputBox searchNomBox;
    private UIButton searchButton;
    private UIButton advancedSearchButton;
    private boolean bHasSearchError = false;

    private UIButton transformHTML;
}
