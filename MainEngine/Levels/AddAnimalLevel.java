package MainEngine.Levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import CoreEngine.Mouse;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.SpriteFactory;
import UIEngine.UIButton;
import UIEngine.UIButton.Lambda;
import UIEngine.UIInputBox;
import UIEngine.UISelectionMenu;
import UIEngine.UITextBox;

public class AddAnimalLevel extends ALevel {

	public AddAnimalLevel(String _name) {
		super(_name);
		
		gifSectionPoint = new Point(500 , 100);
		classificationSectionPoint = new Point(50, 300);
		caracteristiqueSectionPoint = new Point(500, 300);
		localistaionSectionPoint = new Point(50, 50);
		addSectionPoint = new Point(1100, 600);
		
		
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
		LongueurMaxInputBox.SetNewAuthorizedChar("0123456789.");
		poidsMaxInputBox.SetNewMaximalSize(10);
		poidsMaxInputBox.SetNewAuthorizedChar("0123456789.");
		longeviteMaxInputBox.SetNewMaximalSize(10);
		longeviteMaxInputBox.SetNewAuthorizedChar("0123456789");
		typeDePeauInputBox.SetNewMaximalSize(15);
		regimeAlimentaireInputBox.SetNewMaximalSize(15);
		vitesseMaxInputBox.SetNewMaximalSize(10);
		vitesseMaxInputBox.SetNewAuthorizedChar("0123456789.");
		
		LongueurMaxUnitChoice1 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160, caracteristiqueSectionPoint.y + 30, 40, 30), "m",
				() -> {LongueurMaxSelectedUnit = "m";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		LongueurMaxUnitChoice2 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 40, caracteristiqueSectionPoint.y + 30, 40, 30), "mm",
				() -> {LongueurMaxSelectedUnit = "mm";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		
		poidsMaxInputBoxUnitChoice1 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160, caracteristiqueSectionPoint.y + 30 + 50, 40, 30), "t",
				() -> {poidsMaxSelectedUnit = "t";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		poidsMaxInputBoxUnitChoice2 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 40, caracteristiqueSectionPoint.y + 30 + 50, 40, 30), "kg",
				() -> {poidsMaxSelectedUnit = "kg";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		poidsMaxInputBoxUnitChoice3 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 80, caracteristiqueSectionPoint.y + 30 + 50, 40, 30), "g",
				() -> {poidsMaxSelectedUnit = "g";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		poidsMaxInputBoxUnitChoice4 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 120, caracteristiqueSectionPoint.y + 30 + 50, 40, 30), "mg",
				() -> {poidsMaxSelectedUnit = "mg";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		
		longeviteMaxInputBoxUnitChoice1 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160, caracteristiqueSectionPoint.y + 30 + 100, 80, 30), "annees",
				() -> {longeviteSelectedUnit = "annees";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		longeviteMaxInputBoxUnitChoice2 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 80, caracteristiqueSectionPoint.y + 30 + 100, 60, 30), "jours",
				() -> {longeviteSelectedUnit = "jours";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		
		vitesseMaxInputBoxUnitChoice1 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160, caracteristiqueSectionPoint.y + 30 + 250, 60, 30), "km/h",
				() -> {vitesseMaxSelectedUnit = "km/h";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		vitesseMaxInputBoxUnitChoice2 = new UIButton(new Rectangle(caracteristiqueSectionPoint.x + 50 + 170 + 160 + 60, caracteristiqueSectionPoint.y + 30 + 250, 40, 30), "m/s",
				() -> {vitesseMaxSelectedUnit = "m/s";}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
		
		LongueurMaxInputBox.SetText(" " + LongueurMaxSelectedUnit);
		poidsMaxInputBox.SetText(" " + poidsMaxSelectedUnit);
		longeviteMaxInputBox.SetText(" " + longeviteSelectedUnit);
		vitesseMaxInputBox.SetText(" " + vitesseMaxSelectedUnit);
		
		// Section Localistaion
		selectedLocalisationsTextBox = new UITextBox(new Rectangle(localistaionSectionPoint.x + 170, localistaionSectionPoint.y - 20, 450, 30), selectedLocalisations);
		LocalisationSelectionMenu = new UISelectionMenu(new Rectangle(localistaionSectionPoint.x + 170,  localistaionSectionPoint.y - 20 + 30, 180, 120 ));
		
		LinkedHashMap<String, UIButton.Lambda> items = new LinkedHashMap<String, UIButton.Lambda>();
		
		items.put("Afrique", GetLocalisationItemLambdaFunct("Afrique"));
		items.put("Am�rique", GetLocalisationItemLambdaFunct("Am�rique"));
		items.put("Antarctique", GetLocalisationItemLambdaFunct("Antarctique"));
		items.put("Asie", GetLocalisationItemLambdaFunct("Asie"));
		items.put("Europe", GetLocalisationItemLambdaFunct("Europe"));
		items.put("Oc�anie", GetLocalisationItemLambdaFunct("Oc�anie"));
		
		LocalisationSelectionMenu.UpdateSelections(items);
		
		// Section Ajout
		
		addButton = new UIButton(new Rectangle(addSectionPoint.x, addSectionPoint.y, 100, 50), "Ajouter", () -> {}, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY);
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
		LongueurMaxUnitChoice1.Update();
		LongueurMaxUnitChoice2.Update();
		poidsMaxInputBox.Update();
		poidsMaxInputBoxUnitChoice1.Update();
		poidsMaxInputBoxUnitChoice2.Update();
		poidsMaxInputBoxUnitChoice3.Update();
		poidsMaxInputBoxUnitChoice4.Update();
		longeviteMaxInputBox.Update();
		longeviteMaxInputBoxUnitChoice1.Update();
		longeviteMaxInputBoxUnitChoice2.Update();
		typeDePeauInputBox.Update();
		regimeAlimentaireInputBox.Update();
		vitesseMaxInputBox.Update();
		vitesseMaxInputBoxUnitChoice1.Update();
		vitesseMaxInputBoxUnitChoice2.Update();
		
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
		
		// Section Gif
		GraphicsSystem.GetInstance().DrawText("Selectionnez un gif:", gifSectionPoint, Color.BLACK);
		selectedGifTextBox.Draw(10);
		gifSelectionMenu.Draw(10);
		GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite("Assets/GIF/" + selectedGif),  new Rectangle(gifSectionPoint.x + 420, gifSectionPoint.y - 75, 280, 280) , 1);
		
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
		LongueurMaxUnitChoice1.Draw(10);
		LongueurMaxUnitChoice2.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Poids max:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 100), Color.BLACK);
		poidsMaxInputBox.Draw(10);
		poidsMaxInputBoxUnitChoice1.Draw(10);
		poidsMaxInputBoxUnitChoice2.Draw(10);
		poidsMaxInputBoxUnitChoice3.Draw(10);
		poidsMaxInputBoxUnitChoice4.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Longevite max:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 150), Color.BLACK);
		longeviteMaxInputBox.Draw(10);
		longeviteMaxInputBoxUnitChoice1.Draw(10);
		longeviteMaxInputBoxUnitChoice2.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Type de peau:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 200), Color.BLACK);
		typeDePeauInputBox.Draw(10);	
		GraphicsSystem.GetInstance().DrawText("Regime alimentaire:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 250), Color.BLACK);
		regimeAlimentaireInputBox.Draw(10);
		GraphicsSystem.GetInstance().DrawText("Vitesse max:", new Point(caracteristiqueSectionPoint.x + 50, caracteristiqueSectionPoint.y + 300), Color.BLACK);
		vitesseMaxInputBox.Draw(10);
		vitesseMaxInputBoxUnitChoice1.Draw(10);
		vitesseMaxInputBoxUnitChoice2.Draw(10);
		
		// Section Localisations
		GraphicsSystem.GetInstance().DrawText("Localisations:", localistaionSectionPoint, Color.BLACK);
		selectedLocalisationsTextBox.Draw(10);
		LocalisationSelectionMenu.Draw(10);
		
		// Section Ajout
		addButton.Draw(10);
		
	}
	
	private void RefreshSelectionMenuGif() throws Exception{
    	LinkedHashMap<String, UIButton.Lambda> items = new LinkedHashMap<String, UIButton.Lambda>();
    	
    	final File folder = new File("./Assets/GIF");
    	for (final File fileEntry : folder.listFiles()) {
    		if(fileEntry.isFile()){
    			items.put(fileEntry.getName(), () -> {if(new File(fileEntry.getPath()).exists())selectedGif = fileEntry.getName();});
    		}
    	}
    	
    	gifSelectionMenu.UpdateSelections(items);
    }
	
	private UIButton.Lambda GetLocalisationItemLambdaFunct(String name) {
		return () -> {
			List<String> selectedLocalisationsList = !selectedLocalisations.isEmpty() ? new ArrayList<String>(Arrays.asList(selectedLocalisations.split(", "))) : new ArrayList<String>();
			if(selectedLocalisationsList.contains(name)) 
				selectedLocalisationsList.remove(name);
			else
				selectedLocalisationsList.add(name);
			selectedLocalisations = String.join(", ", selectedLocalisationsList);
		};
	}
	
	private final Point gifSectionPoint;
	private final Point classificationSectionPoint;
	private final Point caracteristiqueSectionPoint;
	private final Point localistaionSectionPoint;
	private final Point addSectionPoint;
	
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
	private UIButton LongueurMaxUnitChoice1;
	private UIButton LongueurMaxUnitChoice2;
	private String LongueurMaxSelectedUnit = "m";
	private UIInputBox poidsMaxInputBox;
	private UIButton poidsMaxInputBoxUnitChoice1;
	private UIButton poidsMaxInputBoxUnitChoice2;
	private UIButton poidsMaxInputBoxUnitChoice3;
	private UIButton poidsMaxInputBoxUnitChoice4;
	private String poidsMaxSelectedUnit = "t";
	private UIInputBox longeviteMaxInputBox;
	private UIButton longeviteMaxInputBoxUnitChoice1;
	private UIButton longeviteMaxInputBoxUnitChoice2;
	private String longeviteSelectedUnit = "annees";
	private UIInputBox typeDePeauInputBox;
	private UIInputBox regimeAlimentaireInputBox;
	private UIInputBox vitesseMaxInputBox;
	private UIButton vitesseMaxInputBoxUnitChoice1;
	private UIButton vitesseMaxInputBoxUnitChoice2;
	private String vitesseMaxSelectedUnit = "km/h";
	
	private UITextBox selectedLocalisationsTextBox;
	private UISelectionMenu LocalisationSelectionMenu;
	private String selectedLocalisations = "";
	
	private UIButton addButton;
	
}
