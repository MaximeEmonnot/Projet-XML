package MainEngine.Levels;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import AudioEngine.AudioManager;
import GraphicsEngine.*;
import MainEngine.LevelManager;
import UIEngine.UIButton;

/*
 * Classe du Level de détails d'un animal
 * Définition de l'interface du Level
 * Fonctionnalités : Afficher caractéristiques de l'animal, jouer le cri de l'animal, affichage de la carte avec localisations de l'animal
 */
public class AnimalDetailsLevel extends ALevel {

    public AnimalDetailsLevel(String _name){
        super(_name);

        // Bouton retour au Level Liste (arrête le son en cours s'il y en a un)
        backButton = new UIButton(new Rectangle(100, 590, 250, 75), "Retour à la liste", 
        () -> {
            LevelManager.GetInstance().SetLevel("Animal List Level");
            AudioManager.GetInstance().StopSound();
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        // Jouer le cri de l'animal
        playShout = new UIButton(new Rectangle(410, 515, 350, 40), "Jouer le cri",
        () -> {}, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK);

        // Arrêter le cri de l'animal
        stopShout = new UIButton(new Rectangle(785, 515, 350, 40), "Arrêter le cri",
        () -> { AudioManager.GetInstance().StopSound(); }, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK);
    }

    @Override
    public void OnBegin(Object... params) {
        locations.clear();

        // Définition des différentes caractéristiques à afficher
        Node animal = (Node)params[0];
        name = animal.getAttributes().getNamedItem("nom").getTextContent();
        NodeList animalInfos = animal.getChildNodes();
       
        // On parcourt les différents éléments du Node pour définir les caractéristiques
        for (int i = 0; i < animalInfos.getLength(); i++){
            Node infoType = animalInfos.item(i);
            switch(infoType.getNodeName()){
                case "gif": {
                    gifPath = infoType.getAttributes().getNamedItem("src").getTextContent();
                }
                break;
                case "classification" :{
                    NodeList infos = infoType.getChildNodes();
                    for (int j = 0; j < infos.getLength(); j++){
                        Node info = infos.item(j);
                        switch (info.getNodeName()){
                            case "embranchement": {
                                embranchement = info.getTextContent();
                            }
                            break;
                            case "classe" : {
                                classe = info.getTextContent();
                            }
                            break;
                            case "ordre" : {
                                ordre = info.getTextContent();
                            }
                            break;
                            case "famille" : {
                                famille = info.getTextContent();
                            }
                            break;
                            case "genre" : {
                                genre = info.getTextContent();
                            }
                            break;
                            case "nom_scientifique" : {
                                nomScientifique = info.getTextContent();
                            }
                            break;
                            default: break;
                        }
                    }
                } 
                break;
                case "localisations" :{
                    NodeList infos = infoType.getChildNodes();
                    for (int j = 0; j < infos.getLength(); j++){
                        Node info = infos.item(j);
                        if (info.getNodeName().equals("localisation")) locations.add(info.getTextContent());
                    }
                }
                break;
                case "caracteristique":{
                    NodeList infos = infoType.getChildNodes();
                    for (int j = 0; j < infos.getLength(); j++){
                        Node info = infos.item(j);
                        switch(info.getNodeName()){
                            case "longueur_max" :
                            {
                                taille = info.getTextContent() + " " + info.getAttributes().getNamedItem("unite").getTextContent();
                            }
                            break;
                            case "poids_max":
                            {
                                poids = info.getTextContent() + " " + info.getAttributes().getNamedItem("unite").getTextContent();
                            }
                            break;
                            case "longevite_max" :
                            {
                                longevite = info.getTextContent() + " " + info.getAttributes().getNamedItem("unite").getTextContent();
                            }
                            break;
                            case "type_peau" :
                            {
                                typePeau = info.getTextContent();
                            }
                            break;
                            case "regime":
                            {
                                regime = info.getTextContent();
                            }
                            break;
                            case "vitesse_max" : {
                                vitesse = info.getTextContent() + " " + info.getAttributes().getNamedItem("unite").getTextContent();
                            }  
                            break;
                            case "cri" : {
                                playShout.SetFunction(() -> {
                                    AudioManager.GetInstance().PlaySound(info.getAttributes().getNamedItem("src").getTextContent());
                                });
                            }
                            default: break;
                        }
                    }
                } 
                break;
                default : break;
            }
        }
    }

    @Override
    public void Update() throws Exception {
        // Update des différents boutons
        backButton.Update();
        playShout.Update();
        stopShout.Update();
    }

    @Override
    public void Draw() {
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        
        // GIF
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(100, 50, 250, 250), Color.BLACK, false, 6);
        GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite(gifPath), new Rectangle(100, 50, 250, 250), 5);
        
        // Section Nom
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(375, 50, 800, 40), Color.WHITE, true, 5);
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(375, 50, 800, 40), Color.BLACK, false, 6);
        GraphicsSystem.GetInstance().DrawText("Nom : " + name, new Point(390, 75), Color.BLACK, 7);

        // Section Classification
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(375, 115, 800, 184), Color.WHITE, true, 5);
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(375, 115, 800, 184), Color.BLACK, false, 6);
        GraphicsSystem.GetInstance().DrawText("CLASSIFICATION", new Point(700, 150), Color.RED, 7);
        GraphicsSystem.GetInstance().DrawText("Embranchement : " + embranchement, new Point(390, 200), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Classe : " + classe, new Point(390, 235), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Ordre : " + ordre, new Point(390, 270), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Famille : " + famille, new Point(800, 200), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Genre : " + genre, new Point(800, 235), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Nom scientifique : " + nomScientifique, new Point(800, 270), Color.BLACK, 7);
        
        // Section Locations
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(100, 325, 250, 250), Color.WHITE, true, 5);
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(100, 325, 250, 250), Color.BLACK, false, 6);
        GraphicsSystem.GetInstance().DrawText("LOCALISATIONS", new Point(165, 360), Color.RED, 7);
        GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite("Assets/Images/Map.png"), new Rectangle(100, 375, 250, 200), 5);
        for (String location : locations){
            Point pointLoc = new Point();
            switch(location){
                case "Afrique": pointLoc = new Point(235, 465); break;
                case "Amérique du Nord": pointLoc = new Point(150, 439); break;
                case "Amérique du Sud": pointLoc = new Point(182, 477); break;
                case "Antarctique": pointLoc = new Point(230, 550); break;
                case "Asie": pointLoc = new Point(290, 425); break;
                case "Europe": pointLoc = new Point(230, 435); break;
                case "Océanie": pointLoc = new Point(310, 482); break;
                default: break;
            }
            GraphicsSystem.GetInstance().DrawRoundRect(new Rectangle(pointLoc, new Dimension(10, 10)), new Dimension(10, 10), Color.RED, true, 7);
            GraphicsSystem.GetInstance().DrawRoundRect(new Rectangle(pointLoc, new Dimension(10, 10)), new Dimension(10, 10), Color.BLACK, false, 7);
        }

        // Section Caractéristiques
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(375, 325, 800, 250), Color.WHITE, true, 5);
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(375, 325, 800, 250), Color.BLACK, false, 6);
        GraphicsSystem.GetInstance().DrawText("CARACTERISTIQUES", new Point(680, 360), Color.RED, 7);
        GraphicsSystem.GetInstance().DrawText("Taille : " + taille, new Point(390, 405), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Poids : " + poids, new Point(390, 450), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Longévité : " + longevite, new Point(390, 495), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Type de peau : " + typePeau, new Point(800, 405), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Régime : " + regime, new Point(800, 450), Color.BLACK, 7);
        GraphicsSystem.GetInstance().DrawText("Vitesse : " + vitesse, new Point(800, 495), Color.BLACK, 7);
        playShout.Draw(10);
        stopShout.Draw(10);

        backButton.Draw(10);
    }
    
    private UIButton backButton;
    private String name;
    private String gifPath;
    private String embranchement;
    private String classe;
    private String ordre;
    private String famille;
    private String genre;
    private String nomScientifique;
    private List<String> locations = new ArrayList<String>();
    private String taille;
    private String poids;
    private String longevite;
    private String typePeau;
    private String regime;
    private String vitesse;
    private UIButton playShout;
    private UIButton stopShout;
}
