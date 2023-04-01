package MainEngine.Levels;

import java.awt.*;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import GraphicsEngine.GraphicsSystem;
import MainEngine.LevelManager;
import ParserEngine.XMLManager;
import UIEngine.UIButton;
import UIEngine.UICard;
import UIEngine.UIInputBox;

/*
 * Classe du Level de liste des animaux
 * Définition de l'interface du Level
 * Fonctionnalités : Afficher liste, recherche simple, boutons vers les autres niveaux
 */
public class AnimalListLevel extends ALevel {

    public AnimalListLevel(String _name) throws Exception {
        super(_name);

        // Ajouter un nouvel animal
        addNewAnimal = new UIButton(new Rectangle(925, 590, 250, 75), "Ajouter un animal", 
        () -> { LevelManager.GetInstance().SetLevel("Add Animal Level"); }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        // Parcourir la liste des animaux
        previous = new UIButton(new Rectangle(1190, 162, 50, 150), "↑",
        () -> { pageIndex--; }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        next = new UIButton(new Rectangle(1190, 312, 50, 150), "↓",
        () -> { pageIndex++; }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        // Recherche simple
        searchButton = new UIButton(new Rectangle(100, 590, 250, 75), "Rechercher", 
        () -> { 
            SimpleSearch();
        },
         Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

         // Recherche avancée
         advancedSearchButton = new UIButton(new Rectangle(375, 590, 250, 75), "Recherche avancée", 
         () -> { 
            LevelManager.GetInstance().SetLevel("Advanced Search Level");
         },
          Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

         // Bouton transformation XSLT
        transformHTML = new UIButton(new Rectangle(650, 590, 250, 75), "Transformer un HTML",
        () -> {
            XMLManager.GetInstance().TransformDocument("Assets/XML/animaux.xml", "Assets/XML/animaux.xsl", "Assets/Out/animaux.html");

            NodeList animals = XMLManager.GetInstance().SimpleQuery("Assets/XML/animaux.xml", "//animal");
            for (int i = 0; i < animals.getLength(); i++){
                Node animal = animals.item(i);
                String name = animal.getAttributes().getNamedItem("nom").getTextContent();
                XMLManager.GetInstance().TransformNode(animal, "Assets/XML/animal.xsl", "Assets/Out/" + name + ".html");
            }
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
    }

    @Override
    public void OnBegin(Object... params) throws Exception {
       cards.clear();
       pageIndex = 0;

       // Section recherche simple
       searchEmbranchementBox = new UIInputBox(new Rectangle(100, 50, 250, 75), "Embranchement");
       searchClasseBox = new UIInputBox(new Rectangle(100, 125, 250, 75), "Classe");
       searchOrdreBox = new UIInputBox(new Rectangle(100, 200, 250, 75), "Ordre");
       searchFamilleBox = new UIInputBox(new Rectangle(100, 275, 250, 75), "Famille");
       searchGenreBox = new UIInputBox(new Rectangle(100, 350, 250, 75), "Genre");
       searchNomScientifiqueBox = new UIInputBox(new Rectangle(100, 425, 250, 75), "Nom scientifique");
       searchNomBox = new UIInputBox(new Rectangle(100, 500, 250, 75), "Nom");
       bHasSearchError = false;

       // Initialisation de la liste des animaux à afficher
       if (params.length != 0) InitializeListFromResult((NodeList)params[0]);
       else InitializeListFromResult(XMLManager.GetInstance().GetDocument("Assets/XML/animaux.xml").getElementsByTagName("animal"));
    }

    @Override
    public void Update() throws Exception {
        
        // Update des différentes cartes de animaux
        if (pageIndex - 1 >= 0) previous.Update();
        if ((pageIndex + 1) * 6 < cards.size()) next.Update(); 

        for (int i = pageIndex * 6; i < ((pageIndex + 1) * 6 < cards.size() ? pageIndex * 6 + 6 : cards.size()); i++){
            UICard card = cards.get(i);
            
            // Définition de la position des Card
            card.SetRectangle(new Rectangle(375 + (i % 3) * 275, 50 + ((i - pageIndex * 6) / 3) * 275, 250, 250));

            // Update final
            card.Update();
        } 
        // Update des boutons
        addNewAnimal.Update();
        advancedSearchButton.Update();
        transformHTML.Update();
        // Update section recherche simple
        searchEmbranchementBox.Update();
        searchClasseBox.Update();
        searchOrdreBox.Update();
        searchFamilleBox.Update();
        searchGenreBox.Update();
        searchNomScientifiqueBox.Update();
        searchNomBox.Update();
        searchButton.Update();
    }

    @Override
    public void Draw() throws Exception {
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);

        // Affichage des cartes
        for (int i = pageIndex * 6; i < ((pageIndex + 1) * 6 < cards.size() ? pageIndex * 6 + 6 : cards.size()); i++) cards.get(i).Draw(10);

        // Affichage des boutons de parcours de la liste
        if (pageIndex - 1 >= 0) previous.Draw(10);
        if ((pageIndex + 1) * 6 < cards.size()) next.Draw(10); 

        // Affichage des boutons en bas d'interface
        addNewAnimal.Draw(15);
        advancedSearchButton.Draw(15);
        transformHTML.Draw(15);

        // Affichage section recherche simple
        searchEmbranchementBox.Draw(15);
        searchClasseBox.Draw(15);
        searchOrdreBox.Draw(15);
        searchFamilleBox.Draw(15);
        searchGenreBox.Draw(15);
        searchNomScientifiqueBox.Draw(15);
        searchNomBox.Draw(15);
        searchButton.Draw(15);
        
        // Affichage de l'indication du nombre de pages (si des cartes sont dans la liste)
        if (cards.size() != 0){
            GraphicsSystem.GetInstance().DrawRect(new Rectangle(1190, 590, 50, 50), Color.WHITE, true, 15);
            GraphicsSystem.GetInstance().DrawRect(new Rectangle(1190, 590, 50, 50), Color.BLACK, false, 15);
            GraphicsSystem.GetInstance().DrawText(Integer.toString(pageIndex + 1) + " / " + Integer.toString((int)(cards.size() / 6) + (((cards.size() % 6) == 0) ? 0 : 1)), new Point(1200, 622), Color.BLACK, 15);
        }

        // Affichage du message d'erreur
        if (bHasSearchError) GraphicsSystem.GetInstance().DrawText("Error : Wrong syntax", new Point(145, 655), Color.RED, 15);
    }

    /*
     * 
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
    */

    // Initialisation des cartes à partir du résultat d'une requête XPath
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

    // Recherche simple 
    private void SimpleSearch() throws Exception {
        bHasSearchError = false;

        // Initialisation des paramètres
        String name = searchNomBox.GetText();
        String embranchement = searchEmbranchementBox.GetText();
        String classe = searchClasseBox.GetText();
        String ordre = searchOrdreBox.GetText();
        String famille = searchFamilleBox.GetText();
        String genre = searchGenreBox.GetText();
        String scientificName = searchNomScientifiqueBox.GetText();

        // Début commande
        String command = "//animal";

        // Ecriture du prédicat
        String predicate = "";
        if (!name.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(translate(@nom,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"), translate(\"" + name + "\",\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"))";
        if (!embranchement.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(translate(classification/embranchement,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"), translate(\"" + embranchement + "\",\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"))";
        if (!classe.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(translate(classification/classe,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"),translate(\"" + classe + "\",\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"))";
        if (!ordre.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(translate(classification/ordre,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"),translate(\"" + ordre + "\",\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"))";
        if (!famille.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(translate(classification/famille,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"),translate(\"" + famille + "\",\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"))";
        if (!genre.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(translate(classification/genre,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"),translate(\"" + genre + "\",\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"))";
        if (!scientificName.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(translate(classification/nom_scientifique,\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"),\"" + scientificName + "\",\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"abcdefghijklmnopqrstuvwxyz\"))";

        // Ajout du prédicat, s'il n'est pas vide
        if (!predicate.isEmpty()) command += "[" + predicate + "]";

        // Execution de la requête XPath, si une erreur de syntaxe est survenue, elle sera récupérée et affichée
        try {
            InitializeListFromResult(XMLManager.GetInstance().SimpleQuery("Assets/XML/animaux.xml", command));
        }
        catch(Exception e) {
            e.printStackTrace();
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
