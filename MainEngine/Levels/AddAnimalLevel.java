package MainEngine.Levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.SpriteFactory;
import ParserEngine.XMLManager;
import UIEngine.UIButton;
import UIEngine.UILambda;
import UIEngine.UIInputBox;
import UIEngine.UISelectionMenu;
import UIEngine.UITextBox;

public class AddAnimalLevel extends ALevel {

	public AddAnimalLevel(String _name) {
		super(_name);
		
		gifSectionPoint = new Point(550 , 50);
		classificationSectionPoint = new Point(50, 300);
		caracteristiqueSectionPoint = new Point(550, 300);
		localistaionSectionPoint = new Point(50, 150);
		addSectionPoint = new Point(1100, 600);
		nomSectionPoint = new Point(50, 50);
		errorSectionPoint = new Point(650, 650);
		
		
		// Section Nom
		nomInputBox = new UIInputBox(new Rectangle(nomSectionPoint.x + 170, nomSectionPoint.y - 20, 160, 30), "");
		
		nomInputBox.SetNewMaximalSize(15);
		
		// Section Gif
		selectedGifTextBox = new UITextBox(new Rectangle(gifSectionPoint.x + 170, gifSectionPoint.y - 20, 180, 30), selectedGif);
		gifSelectionMenu = new UISelectionMenu(new Rectangle(gifSectionPoint.x + 170,  gifSectionPoint.y - 20 + 30, 180, 120 ));
		
		
		// Section Classification
		embranchementInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30, 160, 30), "");
		classeInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 50, 160, 30), "");
		ordreInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 100, 160, 30), "");
		familleInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 150, 160, 30), "");
		genreInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 200, 160, 30), "");
		nomScientifiqueInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 250, 160, 30), "");
		
		embranchementInputBox.SetNewMaximalSize(15);
		classeInputBox.SetNewMaximalSize(15);
		ordreInputBox.SetNewMaximalSize(15);
		familleInputBox.SetNewMaximalSize(15);
		genreInputBox.SetNewMaximalSize(15);
		nomScientifiqueInputBox.SetNewMaximalSize(15);
		
		// Section Caracteristique
		LongueurMaxInputBox = new UIInputBox(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170, caracteristiqueSectionPoint.y + 30, 160, 30), "");
		poidsMaxInputBox = new UIInputBox(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170, caracteristiqueSectionPoint.y + 30 + 50, 160, 30), "");
		longeviteMaxInputBox = new UIInputBox(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170, caracteristiqueSectionPoint.y + 30 + 100, 160, 30), "");
		typeDePeauInputBox = new UIInputBox(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170, caracteristiqueSectionPoint.y + 30 + 150, 160, 30), "");
		regimeAlimentaireInputBox = new UIInputBox(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170, caracteristiqueSectionPoint.y + 30 + 200, 160, 30), "");
		vitesseMaxInputBox = new UIInputBox(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170, caracteristiqueSectionPoint.y + 30 + 250, 160, 30), "");
		
		LongueurMaxInputBox.SetNewMaximalSize(10);
		LongueurMaxInputBox.SetNewAuthorizedChar(AUTHORIZEDCHARDECIMAL);
		poidsMaxInputBox.SetNewMaximalSize(10);
		poidsMaxInputBox.SetNewAuthorizedChar(AUTHORIZEDCHARDECIMAL);
		longeviteMaxInputBox.SetNewMaximalSize(10);
		longeviteMaxInputBox.SetNewAuthorizedChar(AUTHORIZEDCHARINTEGER);
		typeDePeauInputBox.SetNewMaximalSize(15);
		regimeAlimentaireInputBox.SetNewMaximalSize(15);
		vitesseMaxInputBox.SetNewMaximalSize(10);
		vitesseMaxInputBox.SetNewAuthorizedChar(AUTHORIZEDCHARDECIMAL);
		
		LongueurMaxUnitChoice1Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160, caracteristiqueSectionPoint.y + 30, 40, 30), "m",
				() -> {LongueurMaxSelectedUnit = "m";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		LongueurMaxUnitChoice2Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 40, caracteristiqueSectionPoint.y + 30, 40, 30), "mm",
				() -> {LongueurMaxSelectedUnit = "mm";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		
		poidsMaxUnitChoice1Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160, caracteristiqueSectionPoint.y + 30 + 50, 40, 30), "t",
				() -> {poidsMaxSelectedUnit = "t";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		poidsMaxUnitChoice2Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 40, caracteristiqueSectionPoint.y + 30 + 50, 40, 30), "kg",
				() -> {poidsMaxSelectedUnit = "kg";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		poidsMaxUnitChoice3Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 80, caracteristiqueSectionPoint.y + 30 + 50, 40, 30), "g",
				() -> {poidsMaxSelectedUnit = "g";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		poidsMaxUnitChoice4Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 120, caracteristiqueSectionPoint.y + 30 + 50, 40, 30), "mg",
				() -> {poidsMaxSelectedUnit = "mg";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		
		longeviteMaxUnitChoice1Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160, caracteristiqueSectionPoint.y + 30 + 100, 80, 30), "annees",
				() -> {longeviteSelectedUnit = "annees";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		longeviteMaxUnitChoice2Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 80, caracteristiqueSectionPoint.y + 30 + 100, 60, 30), "jours",
				() -> {longeviteSelectedUnit = "jours";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		
		vitesseMaxUnitChoice1Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160, caracteristiqueSectionPoint.y + 30 + 250, 60, 30), "km/h",
				() -> {vitesseMaxSelectedUnit = "km/h";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		vitesseMaxUnitChoice2Button = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 60, caracteristiqueSectionPoint.y + 30 + 250, 40, 30), "m/s",
				() -> {vitesseMaxSelectedUnit = "m/s";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		
		LongueurMaxInputBox.SetText(" " + LongueurMaxSelectedUnit);
		poidsMaxInputBox.SetText(" " + poidsMaxSelectedUnit);
		longeviteMaxInputBox.SetText(" " + longeviteSelectedUnit);
		vitesseMaxInputBox.SetText(" " + vitesseMaxSelectedUnit);
		
		// Section Localistaion
		selectedLocalisationsTextBox = new UITextBox(new Rectangle(localistaionSectionPoint.x + 170, localistaionSectionPoint.y - 20, 450, 30), selectedLocalisations);
		LocalisationSelectionMenu = new UISelectionMenu(new Rectangle(localistaionSectionPoint.x + 170,  localistaionSectionPoint.y - 20 + 30, 180, 120 ));
		
		LinkedHashMap<String, UILambda> items = new LinkedHashMap<String, UILambda>();
		
		items.put("Afrique", GetLocalisationItemLambdaFunct("Afrique"));
		items.put("Amérique", GetLocalisationItemLambdaFunct("Amérique"));
		items.put("Antarctique", GetLocalisationItemLambdaFunct("Antarctique"));
		items.put("Asie", GetLocalisationItemLambdaFunct("Asie"));
		items.put("Europe", GetLocalisationItemLambdaFunct("Europe"));
		items.put("Océanie", GetLocalisationItemLambdaFunct("Océanie"));
		
		LocalisationSelectionMenu.UpdateSelections(items);
		
		// Section Ajout
		
		addButton = new UIButton(new Rectangle(addSectionPoint.x, addSectionPoint.y, 100, 50), "Ajouter", () -> {AddAnimalToXmlFile();}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
	}

	@Override
	public void OnBegin(Object... params) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() throws Exception {
		// Retire l'unite de l'inputBox pour que l'utilisateur puisse modifier sa valeur
		LongueurMaxInputBox.SetText(LongueurMaxInputBox.GetText().substring(0, LongueurMaxInputBox.GetText().length() - (LongueurMaxSelectedUnit.length() + 1)));
		poidsMaxInputBox.SetText(poidsMaxInputBox.GetText().substring(0, poidsMaxInputBox.GetText().length() - (poidsMaxSelectedUnit.length() + 1)));
		longeviteMaxInputBox.SetText(longeviteMaxInputBox.GetText().substring(0, longeviteMaxInputBox.GetText().length() - (longeviteSelectedUnit.length() + 1)));
		vitesseMaxInputBox.SetText(vitesseMaxInputBox.GetText().substring(0, vitesseMaxInputBox.GetText().length() - (vitesseMaxSelectedUnit.length() + 1)));
		
		// Permets de restreindre les champs a l'utilisation d'un seul point
		UIInputBox[] decimalInputs = {LongueurMaxInputBox, poidsMaxInputBox, vitesseMaxInputBox};
		for(UIInputBox decimalInput : decimalInputs)
			if(decimalInput.GetText().contains("."))
				decimalInput.SetNewAuthorizedChar(AUTHORIZEDCHARINTEGER);
			else
				decimalInput.SetNewAuthorizedChar(AUTHORIZEDCHARDECIMAL);
		
		// Section Nom
		nomInputBox.Update();
		
		// Section Gif
		selectedGifTextBox.SetText(selectedGif);
		gifSelectionMenu.Update();
		RefreshSelectionMenuGif();
		
		// Section Classification
		embranchementInputBox.Update();
		classeInputBox.Update();
		ordreInputBox.Update();
		familleInputBox.Update();
		genreInputBox.Update();
		nomScientifiqueInputBox.Update();
		
		// Section Caracteristique
		LongueurMaxInputBox.Update();
		LongueurMaxUnitChoice1Button.Update();
		LongueurMaxUnitChoice2Button.Update();
		poidsMaxInputBox.Update();
		poidsMaxUnitChoice1Button.Update();
		poidsMaxUnitChoice2Button.Update();
		poidsMaxUnitChoice3Button.Update();
		poidsMaxUnitChoice4Button.Update();
		longeviteMaxInputBox.Update();
		longeviteMaxUnitChoice1Button.Update();
		longeviteMaxUnitChoice2Button.Update();
		typeDePeauInputBox.Update();
		regimeAlimentaireInputBox.Update();
		vitesseMaxInputBox.Update();
		vitesseMaxUnitChoice1Button.Update();
		vitesseMaxUnitChoice2Button.Update();
		
		// Section Localistaion
		selectedLocalisationsTextBox.SetText(selectedLocalisations);
		LocalisationSelectionMenu.Update();
		
		// Section Ajout
		addButton.Update();
		
		// Remets l'unite, apr�s que l'utilisateur a modifie l'inputBox
		LongueurMaxInputBox.SetText(LongueurMaxInputBox.GetText() + " " + LongueurMaxSelectedUnit);
		poidsMaxInputBox.SetText(poidsMaxInputBox.GetText() + " " + poidsMaxSelectedUnit);
		longeviteMaxInputBox.SetText(longeviteMaxInputBox.GetText() + " " + longeviteSelectedUnit);
		vitesseMaxInputBox.SetText(vitesseMaxInputBox.GetText() + " " + vitesseMaxSelectedUnit);
	}

	@Override
	public void Draw() throws Exception {
		GraphicsSystem.GetInstance().SetBackgroundColor(Color.MAGENTA);
		
		// Section Nom
		GraphicsSystem.GetInstance().DrawText("Nom:", nomSectionPoint, Color.BLACK);
		nomInputBox.Draw();
		
		// Section Gif
		GraphicsSystem.GetInstance().DrawText("Selectionnez un gif:", gifSectionPoint, Color.BLACK);
		selectedGifTextBox.Draw(10);
		gifSelectionMenu.Draw(10);
		GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite("Assets/GIF/" + selectedGif),  new Rectangle(gifSectionPoint.x + 390, gifSectionPoint.y - 25, 280, 280) , 1);
		
		// Section Classification
		GraphicsSystem.GetInstance().DrawText("Classification:", classificationSectionPoint, Color.BLACK);
		GraphicsSystem.GetInstance().DrawText("Embranchement:", new Point(classificationSectionPoint.x + 50, classificationSectionPoint.y + 50), Color.BLACK);
		embranchementInputBox.Draw(10);
		GraphicsSystem.GetInstance().DrawText("Classe:", new Point(classificationSectionPoint.x + 50, classificationSectionPoint.y + 100), Color.BLACK);
		classeInputBox.Draw(10);
		GraphicsSystem.GetInstance().DrawText("Ordre:", new Point(classificationSectionPoint.x + 50, classificationSectionPoint.y + 150), Color.BLACK);
		ordreInputBox.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Famille:", new Point(classificationSectionPoint.x + 50, classificationSectionPoint.y + 200), Color.BLACK);
		familleInputBox.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Genre:", new Point(classificationSectionPoint.x + 50, classificationSectionPoint.y + 250), Color.BLACK);
		genreInputBox.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Nom scientifique:", new Point(classificationSectionPoint.x + 50, classificationSectionPoint.y + 300), Color.BLACK);
		nomScientifiqueInputBox.Draw(10);
		
		// Section Caract�ristique
		GraphicsSystem.GetInstance().DrawText("Caract�ristique:", caracteristiqueSectionPoint, Color.BLACK);
		GraphicsSystem.GetInstance().DrawText("Longueur max:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 50), Color.BLACK);
		LongueurMaxInputBox.Draw(10);
		LongueurMaxUnitChoice1Button.Draw(10);
		LongueurMaxUnitChoice2Button.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Poids max:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 100), Color.BLACK);
		poidsMaxInputBox.Draw(10);
		poidsMaxUnitChoice1Button.Draw(10);
		poidsMaxUnitChoice2Button.Draw(10);
		poidsMaxUnitChoice3Button.Draw(10);
		poidsMaxUnitChoice4Button.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Longevite max:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 150), Color.BLACK);
		longeviteMaxInputBox.Draw(10);
		longeviteMaxUnitChoice1Button.Draw(10);
		longeviteMaxUnitChoice2Button.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Type de peau:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 200), Color.BLACK);
		typeDePeauInputBox.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Regime alimentaire:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 250), Color.BLACK);
		regimeAlimentaireInputBox.Draw(10);
		GraphicsSystem.GetInstance().DrawText("Vitesse max:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 300), Color.BLACK);
		vitesseMaxInputBox.Draw(10);
		vitesseMaxUnitChoice1Button.Draw(10);
		vitesseMaxUnitChoice2Button.Draw(10);
		
		// Section Localisations
		GraphicsSystem.GetInstance().DrawText("Localisations:", localistaionSectionPoint, Color.BLACK);
		selectedLocalisationsTextBox.Draw(10);
		LocalisationSelectionMenu.Draw(10);
		
		// Section Ajout
		addButton.Draw(10);
		
		if(drawEmptyError)
			GraphicsSystem.GetInstance().DrawText("Merci de sp�cifier tous les champs.", errorSectionPoint, Color.RED);
		
	}
	
	private void RefreshSelectionMenuGif() throws Exception{
    	LinkedHashMap<String, UILambda> items = new LinkedHashMap<String, UILambda>();
    	
    	final File folder = new File("./Assets/GIF");
    	for (final File fileEntry : folder.listFiles()) {
    		if(fileEntry.isFile()){
    			items.put(fileEntry.getName(), () -> {if(new File(fileEntry.getPath()).exists())selectedGif = fileEntry.getName();});
    		}
    	}
    	
    	gifSelectionMenu.UpdateSelections(items);
    }
	
	private UILambda GetLocalisationItemLambdaFunct(String name) {
		return () -> {
			List<String> selectedLocalisationsList = !selectedLocalisations.isEmpty() ? new ArrayList<String>(Arrays.asList(selectedLocalisations.split(", "))) : new ArrayList<String>();
			if(selectedLocalisationsList.contains(name)) 
				selectedLocalisationsList.remove(name);
			else
				selectedLocalisationsList.add(name);
			selectedLocalisations = String.join(", ", selectedLocalisationsList);
		};
	}
	
	private void AddAnimalToXmlFile() throws Exception {
		
		if(nomInputBox.GetText().isBlank() || selectedGif.isBlank() || embranchementInputBox.GetText().isEmpty() || classeInputBox.GetText().isBlank() || familleInputBox.GetText().isBlank() 
				|| genreInputBox.GetText().isBlank() || nomScientifiqueInputBox.GetText().isBlank() || selectedLocalisations.isBlank() || longeviteMaxInputBox.GetText().isBlank() 
				|| poidsMaxInputBox.GetText().isBlank() || longeviteMaxInputBox.GetText().isBlank() || typeDePeauInputBox.GetText().isBlank() 
				|| regimeAlimentaireInputBox.GetText().isBlank() || vitesseMaxInputBox.GetText().isBlank()) {
			drawEmptyError = true;
			return;
		}
		
		XMLManager xmlManagerInstance = XMLManager.GetInstance();
		
		Document animauxXML = xmlManagerInstance.GetDocument("./Assets/XML/animaux.xml");
		
		// Recuperation de l'element root animaux
		Element animauxElement = animauxXML.getDocumentElement();
		
		// Creation element animal
		Element animalElement = animauxXML.createElement("animal");
		animalElement.setAttribute("nom", nomInputBox.GetText());
		
		// Creation element gif
		Element gifElement = animauxXML.createElement("gif");
		gifElement.setAttribute("src", "./Assets/GIF/" + selectedGif);
		
		// Creation element classification
		Element classificationElement = animauxXML.createElement("classification");
		
		// Creation et Ajout des elements fils de classification
		Element embranchementElement = animauxXML.createElement("embranchement");
		embranchementElement.setTextContent(embranchementInputBox.GetText());
		classificationElement.appendChild(embranchementElement);
		
		Element classeElement = animauxXML.createElement("classe");
		classeElement.setTextContent(classeInputBox.GetText());
		classificationElement.appendChild(classeElement);
		
		Element ordreElement = animauxXML.createElement("ordre");
		ordreElement.setTextContent(ordreInputBox.GetText());
		classificationElement.appendChild(ordreElement);
		
		Element familleElement = animauxXML.createElement("famille");
		familleElement.setTextContent(familleInputBox.GetText());
		classificationElement.appendChild(familleElement);

		Element genreElement = animauxXML.createElement("genre");
		classeElement.setTextContent(genreInputBox.GetText());
		classificationElement.appendChild(genreElement);
		
		Element nomScientifiqueElement = animauxXML.createElement("nom_scientifique");
		nomScientifiqueElement.setTextContent(nomScientifiqueInputBox.GetText());
		classificationElement.appendChild(nomScientifiqueElement);
		
		// Creation element localisations
		Element localisationsElement = animauxXML.createElement("localisations");
		
		// Creation et Ajout des elements fils de localisations
		String[] localisations = selectedLocalisations.split(", ");
		for(String localisation : localisations) {
			Element localisationElement = animauxXML.createElement("localisation");
			localisationElement.setTextContent(localisation);
			localisationsElement.appendChild(localisationElement);
		}
		
		// Creation element caracteristique
		Element caracteristiqueElement = animauxXML.createElement("caracteristique");
		
		// Creation et Ajout des elements fils de caracteristique
		Element longueurMaxElement = animauxXML.createElement("longueur_max");
		longueurMaxElement.setAttribute("unite", LongueurMaxSelectedUnit);
		longueurMaxElement.setTextContent(longeviteMaxInputBox.GetText());
		caracteristiqueElement.appendChild(longueurMaxElement);
		
		Element poidsMaxElement = animauxXML.createElement("poids_max");
		poidsMaxElement.setAttribute("unite", poidsMaxSelectedUnit);
		poidsMaxElement.setTextContent(poidsMaxInputBox.GetText());
		caracteristiqueElement.appendChild(poidsMaxElement);
		
		Element longeviteMaxElement = animauxXML.createElement("longevite_max");
		longeviteMaxElement.setAttribute("unite", longeviteSelectedUnit);
		longeviteMaxElement.setTextContent(longeviteMaxInputBox.GetText());
		caracteristiqueElement.appendChild(longeviteMaxElement);
		
		Element typeDePeauElement = animauxXML.createElement("type_peau");
		typeDePeauElement.setTextContent(typeDePeauInputBox.GetText());
		caracteristiqueElement.appendChild(typeDePeauElement);
		
		Element regimeAlimentaireElement = animauxXML.createElement("regime");
		regimeAlimentaireElement.setTextContent(regimeAlimentaireInputBox.GetText());
		caracteristiqueElement.appendChild(regimeAlimentaireElement);
		
		Element vitesseMaxElement = animauxXML.createElement("vitesse_max");
		vitesseMaxElement.setAttribute("unite",vitesseMaxSelectedUnit);
		vitesseMaxElement.setTextContent(vitesseMaxInputBox.GetText());
		caracteristiqueElement.appendChild(vitesseMaxElement);
		
		
		// Ajout des elements fils de animal
		animalElement.appendChild(gifElement);
		animalElement.appendChild(classificationElement);
		animalElement.appendChild(localisationsElement);
		animalElement.appendChild(caracteristiqueElement);
		
		// Ajout de l'element fils de animaux
		animauxElement.appendChild(animalElement);
		
		xmlManagerInstance.WriteToFile(animauxXML, "./Assets/XML/animaux.xml");
		
		//Retour au menu
	}
	
	private final Point gifSectionPoint;
	private final Point classificationSectionPoint;
	private final Point caracteristiqueSectionPoint;
	private final Point localistaionSectionPoint;
	private final Point addSectionPoint;
	private final Point nomSectionPoint;
	private final Point errorSectionPoint;
	
	private UIInputBox nomInputBox;
	
	private UITextBox selectedGifTextBox;
	private UISelectionMenu gifSelectionMenu;
	private String selectedGif = "";
	
	private UIInputBox embranchementInputBox;
	private UIInputBox classeInputBox;
	private UIInputBox ordreInputBox;
	private UIInputBox familleInputBox;
	private UIInputBox genreInputBox;
	private UIInputBox nomScientifiqueInputBox;
	
	private UIInputBox LongueurMaxInputBox;
	private UIButton LongueurMaxUnitChoice1Button;
	private UIButton LongueurMaxUnitChoice2Button;
	private String LongueurMaxSelectedUnit = "m";
	private UIInputBox poidsMaxInputBox;
	private UIButton poidsMaxUnitChoice1Button;
	private UIButton poidsMaxUnitChoice2Button;
	private UIButton poidsMaxUnitChoice3Button;
	private UIButton poidsMaxUnitChoice4Button;
	private String poidsMaxSelectedUnit = "t";
	private UIInputBox longeviteMaxInputBox;
	private UIButton longeviteMaxUnitChoice1Button;
	private UIButton longeviteMaxUnitChoice2Button;
	private String longeviteSelectedUnit = "ann�es";
	private UIInputBox typeDePeauInputBox;
	private UIInputBox regimeAlimentaireInputBox;
	private UIInputBox vitesseMaxInputBox;
	private UIButton vitesseMaxUnitChoice1Button;
	private UIButton vitesseMaxUnitChoice2Button;
	private String vitesseMaxSelectedUnit = "km/h";
	
	private UITextBox selectedLocalisationsTextBox;
	private UISelectionMenu LocalisationSelectionMenu;
	private String selectedLocalisations = "";
	
	private UIButton addButton;
	
	private boolean drawEmptyError = false;
	
	private final String AUTHORIZEDCHARDECIMAL = "0123456789.";
	private final String AUTHORIZEDCHARINTEGER = "0123456789";
	
}
