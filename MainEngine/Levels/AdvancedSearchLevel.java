package MainEngine.Levels;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

import java.util.Arrays;

import GraphicsEngine.GraphicsSystem;
import MainEngine.LevelManager;
import ParserEngine.XMLManager;
import UIEngine.*;

/*
 * Classe du Level de recherche avancée
 * Définition de l'interface du Level
 * Fonctionnalités : Recherche simple avec recherche des localisations, et des caractéristiques (d'une certaine taille, plus grand que, plus petit...)
 */
public class AdvancedSearchLevel extends ALevel {

    public AdvancedSearchLevel(String _name) {
        super(_name);

        // Section Localisation
        africaButton = new UIButton(new Rectangle (455, 125, 180, 75), "Afrique", 
        () -> {
            AddLocalisation("Afrique");
            if (localisations.GetText().contains("Afrique")) africaButton.SetBaseColor(Color.LIGHT_GRAY);
            else africaButton.SetBaseColor(Color.WHITE);
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        northAmericaButton = new UIButton(new Rectangle (635, 125, 180, 75), "Amérique du Nord", 
        () -> {
            AddLocalisation("Amérique du Nord");
            if (localisations.GetText().contains("Amérique du Nord")) northAmericaButton.SetBaseColor(Color.LIGHT_GRAY);
            else northAmericaButton.SetBaseColor(Color.WHITE);
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        southAmericaButton = new UIButton(new Rectangle (815, 125, 180, 75), "Amérique du Sud", 
        () -> {
            AddLocalisation("Amérique du Sud");
            if (localisations.GetText().contains("Amérique du Sud")) southAmericaButton.SetBaseColor(Color.LIGHT_GRAY);
            else southAmericaButton.SetBaseColor(Color.WHITE);
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        antarticaButton = new UIButton(new Rectangle (995, 125, 180, 75), "Antarctique", 
        () -> {
            AddLocalisation("Antarctique");
            if (localisations.GetText().contains("Antarctique")) antarticaButton.SetBaseColor(Color.LIGHT_GRAY);
            else antarticaButton.SetBaseColor(Color.WHITE);
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        asiaButton = new UIButton(new Rectangle (545, 200, 180, 75), "Asie", 
        () -> {
            AddLocalisation("Asie");
            if (localisations.GetText().contains("Asie")) asiaButton.SetBaseColor(Color.LIGHT_GRAY);
            else asiaButton.SetBaseColor(Color.WHITE);
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        europeButton = new UIButton(new Rectangle (725, 200, 180, 75), "Europe", 
        () -> {
            AddLocalisation("Europe");
            if (localisations.GetText().contains("Europe")) europeButton.SetBaseColor(Color.LIGHT_GRAY);
            else europeButton.SetBaseColor(Color.WHITE);
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        oceaniaButton = new UIButton(new Rectangle (905, 200, 180, 75), "Océanie", 
        () -> {
            AddLocalisation("Océanie");
            if (localisations.GetText().contains("Océanie")) oceaniaButton.SetBaseColor(Color.LIGHT_GRAY);
            else oceaniaButton.SetBaseColor(Color.WHITE);
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        // Section Caractéristiques
        // Taille
        lengthLessButton = new UIButton(new Rectangle(455, 350, 25, 25), "<", 
        () -> {
            lengthOperator = "<";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        lengthEqualButton = new UIButton(new Rectangle(455, 375, 25, 25), "=", 
        () -> {
            lengthOperator = "=";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        lengthMoreButton = new UIButton(new Rectangle(455, 400, 25, 25), ">", 
        () -> {
            lengthOperator = ">";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        meterButton = new UIButton(new Rectangle(715, 350, 80, 25), "m",
         () -> { 
            lengthUnit = "m"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        centimeterButton = new UIButton(new Rectangle(715, 375, 80, 25), "cm",
         () -> { 
            lengthUnit = "cm"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        millimeterButton = new UIButton(new Rectangle(715, 400, 80, 25), "mm",
         () -> { 
            lengthUnit = "mm"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        // Poids
        weightLessButton = new UIButton(new Rectangle(455, 425, 25, 25), "<", 
        () -> {
            weightOperator = "<";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        weightEqualButton = new UIButton(new Rectangle(455, 450, 25, 25), "=", 
        () -> {
            weightOperator = "=";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        weightMoreButton = new UIButton(new Rectangle(455, 475, 25, 25), ">", 
        () -> {
            weightOperator = ">";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        tonButton = new UIButton(new Rectangle(715, 425, 40, 37), "t",
         () -> { 
            weightUnit = "t"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        kilogramButton = new UIButton(new Rectangle(715, 462, 40, 38), "kg",
         () -> { 
            weightUnit = "kg"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        gramButton = new UIButton(new Rectangle(755, 425, 40, 37), "g",
         () -> { 
            weightUnit = "g"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        milligramButton = new UIButton(new Rectangle(755, 462, 40, 38), "mg",
         () -> { 
            weightUnit = "mg"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        // Longévité
        longevityLessButton = new UIButton(new Rectangle(455, 500, 25, 25), "<", 
        () -> {
            longevityOperator = "<";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        longevityEqualButton = new UIButton(new Rectangle(455, 525, 25, 25), "=", 
        () -> {
            longevityOperator = "=";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        longevityMoreButton = new UIButton(new Rectangle(455, 550, 25, 25), ">", 
        () -> {
            longevityOperator = ">";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        yearsButton = new UIButton(new Rectangle(715, 500, 80, 37), "années",
         () -> { 
            longevityUnit = "années"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        daysButton = new UIButton(new Rectangle(715, 537, 80, 38), "jours",
         () -> { 
            longevityUnit = "jours"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        // Vitesse
        speedLessButton = new UIButton(new Rectangle(835, 500, 25, 25), "<", 
        () -> {
            speedOperator = "<";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        speedEqualButton = new UIButton(new Rectangle(835, 525, 25, 25), "=", 
        () -> {
            speedOperator = "=";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        speedMoreButton = new UIButton(new Rectangle(835, 550, 25, 25), ">", 
        () -> {
            speedOperator = ">";
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        kilometerHourButton = new UIButton(new Rectangle(1095, 500, 80, 37), "km/h",
         () -> { 
            speedUnit = "km/h"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        meterSecondButton = new UIButton(new Rectangle(1095, 537, 80, 38), "m/s",
         () -> { 
            speedUnit = "m/s"; 
        }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);

        // Section bas de l'interface
        backButton = new UIButton(new Rectangle(100, 590, 250, 75), "Retour", 
        () -> { LevelManager.GetInstance().SetLevel("Animal List Level"); }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
        searchButton = new UIButton(new Rectangle(925, 590, 250, 75), "Rechercher", 
        () -> {
            NodeList result = ComplexSearch();
            if (result != null) LevelManager.GetInstance().SetLevel("Animal List Level", result); 
            else bHasSearchError = true;
            }, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
    }

    @Override
    public void OnBegin(Object... params) throws Exception {  
        bHasSearchError = false;

        // Section Classification
        searchEmbranchementBox = new UIInputBox(new Rectangle(100, 50, 250, 75), "Embranchement");
        searchClasseBox = new UIInputBox(new Rectangle(100, 125, 250, 75), "Classe");
        searchOrdreBox = new UIInputBox(new Rectangle(100, 200, 250, 75), "Ordre");
        searchFamilleBox = new UIInputBox(new Rectangle(100, 275, 250, 75), "Famille");
        searchGenreBox = new UIInputBox(new Rectangle(100, 350, 250, 75), "Genre");
        searchNomScientifiqueBox = new UIInputBox(new Rectangle(100, 425, 250, 75), "Nom scientifique");
        searchNomBox = new UIInputBox(new Rectangle(100, 500, 250, 75), "Nom");

        // Section Localisation
        localisations = new UITextBox(new Rectangle(455, 50, 720, 75), "");
        africaButton.SetBaseColor(Color.WHITE);
        northAmericaButton.SetBaseColor(Color.WHITE);
        southAmericaButton.SetBaseColor(Color.WHITE);
        antarticaButton.SetBaseColor(Color.WHITE);
        asiaButton.SetBaseColor(Color.WHITE);
        europeButton.SetBaseColor(Color.WHITE);
        oceaniaButton.SetBaseColor(Color.WHITE);

        // Section Caractéristiques
        // Taille
        lengthInput = new UIInputBox(new Rectangle(480, 350, 235, 75), "Taille");
        lengthInput.SetNewAuthorizedChar("0123456789.");
        lengthOperator = "<";
        lengthUnit = "m";
        // Poids
        weightInput = new UIInputBox(new Rectangle(480, 425, 235, 75), "Poids");
        weightInput.SetNewAuthorizedChar("0123456789.");
        weightOperator = "<";
        weightUnit = "t";
        // Longévité
        longevityInput = new UIInputBox(new Rectangle(480, 500, 235, 75), "Longévité");
        longevityInput.SetNewAuthorizedChar("0123456789");
        longevityOperator = "<";
        longevityUnit = "années";
        // Peau
        skinInput = new UIInputBox(new Rectangle(835, 350, 340, 75), "Type de peau");
        // Régime
        dietInput = new UIInputBox(new Rectangle(835, 425, 340, 75), "Régime alimentaire");
        // Vitesse
        speedInput = new UIInputBox(new Rectangle(860, 500, 235, 75), "Vitesse");
        speedInput.SetNewAuthorizedChar("0123456789.");
        speedOperator = "<";
        speedUnit = "km/h";
    }

    @Override
    public void Update() throws Exception {

        // Permets de restreindre les champs a l'utilisation d'un seul point
		UIInputBox[] decimalInputs = {lengthInput, weightInput, speedInput};
		for(UIInputBox decimalInput : decimalInputs)
			if(decimalInput.GetText().contains("."))
				decimalInput.SetNewAuthorizedChar("0123456789");
			else
				decimalInput.SetNewAuthorizedChar("0123456789.");

        // Section Classification
        searchEmbranchementBox.Update();
        searchClasseBox.Update();
        searchOrdreBox.Update();
        searchFamilleBox.Update();
        searchGenreBox.Update();
        searchNomScientifiqueBox.Update();
        searchNomBox.Update();

        // Section Localisation
        africaButton.Update();
        northAmericaButton.Update();
        southAmericaButton.Update();
        antarticaButton.Update();
        asiaButton.Update();
        europeButton.Update();
        oceaniaButton.Update();

        // Section Caractéristiques
        // Taille
        lengthInput.Update();
        lengthLessButton.Update();
        lengthEqualButton.Update();
        lengthMoreButton.Update();
        lengthLessButton.SetBaseColor(lengthOperator.equals("<") ? Color.LIGHT_GRAY : Color.WHITE);
        lengthEqualButton.SetBaseColor(lengthOperator.equals("=") ? Color.LIGHT_GRAY : Color.WHITE);
        lengthMoreButton.SetBaseColor(lengthOperator.equals(">") ? Color.LIGHT_GRAY : Color.WHITE);
        meterButton.Update();
        centimeterButton.Update();
        millimeterButton.Update();
        meterButton.SetBaseColor(lengthUnit.equals("m") ? Color.LIGHT_GRAY : Color.WHITE);
        centimeterButton.SetBaseColor(lengthUnit.equals("cm") ? Color.LIGHT_GRAY : Color.WHITE);
        millimeterButton.SetBaseColor(lengthUnit.equals("mm") ? Color.LIGHT_GRAY : Color.WHITE);
        // Poids
        weightInput.Update();
        weightLessButton.Update();
        weightEqualButton.Update();
        weightMoreButton.Update();
        weightLessButton.SetBaseColor(weightOperator.equals("<") ? Color.LIGHT_GRAY : Color.WHITE);
        weightEqualButton.SetBaseColor(weightOperator.equals("=") ? Color.LIGHT_GRAY : Color.WHITE);
        weightMoreButton.SetBaseColor(weightOperator.equals(">") ? Color.LIGHT_GRAY : Color.WHITE);
        tonButton.Update();
        kilogramButton.Update();
        gramButton.Update();
        milligramButton.Update();
        tonButton.SetBaseColor(weightUnit.equals("t") ? Color.LIGHT_GRAY : Color.WHITE);
        kilogramButton.SetBaseColor(weightUnit.equals("kg") ? Color.LIGHT_GRAY : Color.WHITE);
        gramButton.SetBaseColor(weightUnit.equals("g") ? Color.LIGHT_GRAY : Color.WHITE);
        milligramButton.SetBaseColor(weightUnit.equals("mg") ? Color.LIGHT_GRAY : Color.WHITE);
        // Longévité
        longevityInput.Update();
        longevityLessButton.Update();
        longevityEqualButton.Update();
        longevityMoreButton.Update();
        longevityLessButton.SetBaseColor(longevityOperator.equals("<") ? Color.LIGHT_GRAY : Color.WHITE);
        longevityEqualButton.SetBaseColor(longevityOperator.equals("=") ? Color.LIGHT_GRAY : Color.WHITE);
        longevityMoreButton.SetBaseColor(longevityOperator.equals(">") ? Color.LIGHT_GRAY : Color.WHITE);
        yearsButton.Update();
        daysButton.Update();
        yearsButton.SetBaseColor(longevityUnit.equals("années") ? Color.LIGHT_GRAY : Color.WHITE);
        daysButton.SetBaseColor(longevityUnit.equals("jours") ? Color.LIGHT_GRAY : Color.WHITE);
        // Peau
        skinInput.Update();
        // Régime
        dietInput.Update();
        // Vitesse
        speedInput.Update();
        speedLessButton.Update();
        speedEqualButton.Update();
        speedMoreButton.Update();
        speedLessButton.SetBaseColor(speedOperator.equals("<") ? Color.LIGHT_GRAY : Color.WHITE);
        speedEqualButton.SetBaseColor(speedOperator.equals("=") ? Color.LIGHT_GRAY : Color.WHITE);
        speedMoreButton.SetBaseColor(speedOperator.equals(">") ? Color.LIGHT_GRAY : Color.WHITE);
        kilometerHourButton.Update();
        meterSecondButton.Update();
        kilometerHourButton.SetBaseColor(speedUnit.equals("km/h") ? Color.LIGHT_GRAY : Color.WHITE);
        meterSecondButton.SetBaseColor(speedUnit.equals("m/s") ? Color.LIGHT_GRAY : Color.WHITE);

        // Section bas de l'interface
        backButton.Update();
        searchButton.Update();
    }

    @Override
    public void Draw() throws Exception {
       GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);

       // Section Classification
       searchEmbranchementBox.Draw(15);
       searchClasseBox.Draw(15);
       searchOrdreBox.Draw(15);
       searchFamilleBox.Draw(15);
       searchGenreBox.Draw(15);
       searchNomScientifiqueBox.Draw(15);
       searchNomBox.Draw(15);

       // Section Localisation
       localisations.Draw(15);
       africaButton.Draw(15);
       northAmericaButton.Draw(15);
       southAmericaButton.Draw(15);
       antarticaButton.Draw(15);
       asiaButton.Draw(15);
       europeButton.Draw(15);
       oceaniaButton.Draw(15);

       // Section Caractéristiques
       // Taille
       lengthInput.Draw(15);
       lengthLessButton.Draw(15);
       lengthEqualButton.Draw(15);
       lengthMoreButton.Draw(15);
       meterButton.Draw(15);
       centimeterButton.Draw(15);
       millimeterButton.Draw(15);
       // Poids
       weightInput.Draw(15);
       weightLessButton.Draw(15);
       weightEqualButton.Draw(15);
       weightMoreButton.Draw(15);
       tonButton.Draw(15);
       kilogramButton.Draw(15);
       gramButton.Draw(15);
       milligramButton.Draw(15);
       // Longévité
       longevityInput.Draw(15);
       longevityLessButton.Draw(15);
       longevityEqualButton.Draw(15);
       longevityMoreButton.Draw(15);
       yearsButton.Draw(15);
       daysButton.Draw(15);
       // Peau
       skinInput.Draw(15);
       // Régime
       dietInput.Draw(15);
       // Vitesse
       speedInput.Draw(15);
       speedLessButton.Draw(15);
       speedEqualButton.Draw(15);
       speedMoreButton.Draw(15);
       kilometerHourButton.Draw(15);
       meterSecondButton.Draw(15);
    
       // Section bas de l'interface
        backButton.Draw(10);
        searchButton.Draw(10);
        if (bHasSearchError) GraphicsSystem.GetInstance().DrawText("Error : Wrong syntax", new Point(960, 655), Color.RED, 15);

    }   

    private void AddLocalisation(String name) {
		List<String> selectedLocalisationsList = !localisations.GetText().isEmpty() ? new ArrayList<String>(Arrays.asList(localisations.GetText().split(", "))) : new ArrayList<String>();
		if(selectedLocalisationsList.contains(name)) 
			selectedLocalisationsList.remove(name);
		else
			selectedLocalisationsList.add(name);
		localisations.SetText(String.join(", ", selectedLocalisationsList));
	}

    // Construction de la requête XPath à partir des différentes informations 
    private NodeList ComplexSearch() throws Exception {
        NodeList output = null;

        // Variables Classification
        String name = searchNomBox.GetText();
        String embranchement = searchEmbranchementBox.GetText();
        String classe = searchClasseBox.GetText();
        String ordre = searchOrdreBox.GetText();
        String famille = searchFamilleBox.GetText();
        String genre = searchGenreBox.GetText();
        String scientificName = searchNomScientifiqueBox.GetText();

        // Variables Caractéristiques
        String length = lengthInput.GetText();
        String weight = weightInput.GetText();
        String longevity = longevityInput.GetText();
        String skin = skinInput.GetText();
        String diet = dietInput.GetText();
        String speed = speedInput.GetText();

        String command = "//animal";

        String predicate = "";

        // Partie Classification
        if (!name.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(@nom,\"" + name + "\")";
        if (!embranchement.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/embranchement,\"" + embranchement + "\")";
        if (!classe.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/classe,\"" + classe + "\")";
        if (!ordre.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/ordre,\"" + ordre + "\")";
        if (!famille.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/famille,\"" + famille + "\")";
        if (!genre.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/genre,\"" + genre + "\")";
        if (!scientificName.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(classification/nom_scientifique,\"" + scientificName + "\")";

        // Partie Localisation
        if (!localisations.GetText().isEmpty()){
            for (String localisation : Arrays.asList(localisations.GetText().split(", "))){
                predicate += (predicate.isEmpty() ? "" : " and ") + "localisations/localisation='" + localisation + "'";
            }
        }

        // Partie Caractéristiques
        if (!length.isEmpty()) {
            float meterRatio = 1.f;
            float centimeterRatio = 1.f;
            float millimeterRatio = 1.f;
            switch(lengthUnit){
                case "m" : 
                {
                    meterRatio = 1.f;
                    centimeterRatio = 100.f;
                    millimeterRatio = 1000.f;
                }
                break;
                case "cm" : 
                {
                    meterRatio = 0.01f;
                    centimeterRatio = 1.f;
                    millimeterRatio = 10.f;
                }
                break;
                case "mm" :
                {
                    meterRatio = 0.001f;
                    centimeterRatio = 0.1f;
                    millimeterRatio = 1.f;
                }
                default: break;
            }

            String condition = "(caracteristique/longueur_max/@unite='m' and caracteristique/longueur_max " + lengthOperator + " " + new BigDecimal(Float.parseFloat(length) * meterRatio) + ")"
            + " or (caracteristique/longueur_max/@unite='cm' and caracteristique/longueur_max " + lengthOperator + " " + new BigDecimal(Float.parseFloat(length) * centimeterRatio) + ")"
            + " or (caracteristique/longueur_max/@unite='mm' and caracteristique/longueur_max " + lengthOperator + " " + new BigDecimal(Float.parseFloat(length) * millimeterRatio) + ")";

            predicate += (predicate.isEmpty() ? "" : " and ") + "(" + condition + ")";
        }
        if (!weight.isEmpty()) {
            float tonRatio = 1.f;
            float kilogramRatio = 1.f;
            float gramRatio = 1.f;
            float milligramRatio = 1.f;

            switch (weightUnit) {
                case "t":
                {
                    tonRatio = 1.f;
                    kilogramRatio = 1000.f;
                    gramRatio = 1000000.f;
                    milligramRatio = 1000000000.f;
                }
                break;
                case "kg" : 
                {
                    tonRatio = 0.001f;
                    kilogramRatio = 1.f;
                    gramRatio = 1000.f;
                    milligramRatio = 1000000.f;
                }
                break;
                case "g" :
                {
                    tonRatio = 0.000001f;
                    kilogramRatio = 0.001f;
                    gramRatio = 1.f;
                    milligramRatio = 1000.f;
                }
                break;
                case "mg" :
                {
                    tonRatio = 0.000000001f;
                    kilogramRatio = 0.000001f;
                    gramRatio = 0.001f;
                    milligramRatio = 1.f;
                }
                break;
                default: break;
            }
            
            String condition = "(caracteristique/poids_max/@unite='t' and caracteristique/poids_max " + weightOperator + " " + new BigDecimal(Float.parseFloat(weight) * tonRatio) + ")"
            + " or (caracteristique/poids_max/@unite='kg' and caracteristique/poids_max " + weightOperator + " " + new BigDecimal(Float.parseFloat(weight) * kilogramRatio) + ")"
            + " or (caracteristique/poids_max/@unite='g' and caracteristique/poids_max " + weightOperator + " " + new BigDecimal(Float.parseFloat(weight) * gramRatio) + ")"
            + " or (caracteristique/poids_max/@unite='mg' and caracteristique/poids_max " + weightOperator + " " + new BigDecimal(Float.parseFloat(weight) * milligramRatio) + ")";

            predicate += (predicate.isEmpty() ? "" : " and ") + "( " + condition + " )";
        }
        if (!longevity.isEmpty()){
            float yearsRatio = 1.f;
            float daysRatio = 1.f;

            switch(longevityUnit){
                case "années" :
                {
                    yearsRatio = 1.f;
                    daysRatio = 365.25f;
                }
                break;
                case "jours" :
                {
                    yearsRatio = 1.f / 365.25f;
                    daysRatio = 1.f;
                }
                break;
                default:break;
            }

            String condition = "(caracteristique/longevite_max/@unite='années' and caracteristique/longevite_max " + longevityOperator + " " + new BigDecimal(Float.parseFloat(longevity) * yearsRatio) + ")"
            + " or (caracteristique/longevite_max/@unite='jours' and caracteristique/longevite_max " + longevityOperator + " " + new BigDecimal(Float.parseFloat(longevity) * daysRatio) + ")";

            predicate += (predicate.isEmpty() ? "" : " and ") + "( " + condition + " )"; 
        } 
        if (!skin.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(caracteristique/type_peau, \""+ skin + "\")";
        if (!diet.isEmpty()) predicate += (predicate.isEmpty() ? "" : " and ") + "contains(caracteristique/regime, \""+ diet + "\")";
        if (!speed.isEmpty()) {
            float kilometerHourRatio = 1.f;
            float meterSecondRatio = 1.f;

            switch(speedUnit){
                case "km/h" :
                {
                    kilometerHourRatio = 1.f;
                    meterSecondRatio = 1.f / 3.6f;
                }
                break;
                case "m/s" :
                {
                    kilometerHourRatio = 3.6f;
                    meterSecondRatio = 1.f;
                }
                break;
                default:break;
            }

            String condition = "(caracteristique/vitesse_max/@unite='km/h' and caracteristique/vitesse_max " + speedOperator + " " + new BigDecimal(Float.parseFloat(speed) * kilometerHourRatio) + ")"
            + " or (caracteristique/vitesse_max/@unite='m/s' and caracteristique/vitesse_max " + speedOperator + " " + new BigDecimal(Float.parseFloat(speed) * meterSecondRatio) + ")";

            predicate += (predicate.isEmpty() ? "" : " and ") + "( " + condition + " )";
        }

        if (!predicate.isEmpty()) command += "[" + predicate + "]";

        try {
            System.out.println(command);
            output = XMLManager.GetInstance().SimpleQuery("Assets/XML/animaux.xml", command);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return output;
    }
    
    // Section Classification
    private UIInputBox searchEmbranchementBox;
    private UIInputBox searchClasseBox;
    private UIInputBox searchOrdreBox;
    private UIInputBox searchFamilleBox;
    private UIInputBox searchGenreBox;
    private UIInputBox searchNomScientifiqueBox;
    private UIInputBox searchNomBox;
    
    // Section Localisation
    private UITextBox localisations;
    private UIButton africaButton;
    private UIButton northAmericaButton;
    private UIButton southAmericaButton;
    private UIButton antarticaButton;
    private UIButton asiaButton;
    private UIButton europeButton;
    private UIButton oceaniaButton;

    // Section Caractéristiques
    private UIInputBox lengthInput;
    private UIButton meterButton;
    private UIButton centimeterButton;
    private UIButton millimeterButton;
    private UIButton lengthLessButton;
    private UIButton lengthEqualButton;
    private UIButton lengthMoreButton;
    private String lengthOperator;
    private String lengthUnit;
    private UIInputBox weightInput;
    private UIButton tonButton;
    private UIButton kilogramButton;
    private UIButton gramButton;
    private UIButton milligramButton;
    private UIButton weightLessButton;
    private UIButton weightEqualButton;
    private UIButton weightMoreButton;
    private String weightOperator;
    private String weightUnit;
    private UIInputBox longevityInput;
    private UIButton yearsButton;
    private UIButton daysButton;
    private UIButton longevityLessButton;
    private UIButton longevityEqualButton;
    private UIButton longevityMoreButton;
    private String longevityOperator;
    private String longevityUnit;
    private UIInputBox skinInput;
    private UIInputBox dietInput;
    private UIInputBox speedInput;
    private UIButton kilometerHourButton;
    private UIButton meterSecondButton;
    private UIButton speedLessButton;
    private UIButton speedEqualButton;
    private UIButton speedMoreButton;
    private String speedOperator;
    private String speedUnit;

    private boolean bHasSearchError = false;

    // Boutons du bas de l'interface
    private UIButton backButton;
    private UIButton searchButton;
}
