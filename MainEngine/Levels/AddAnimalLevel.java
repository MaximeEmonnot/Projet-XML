package MainEngine.Levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedHashMap;

import CoreEngine.Mouse;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.SpriteFactory;
import UIEngine.UIButton;
import UIEngine.UIInputBox;
import UIEngine.UISelectionMenu;
import UIEngine.UITextBox;

public class AddAnimalLevel extends ALevel {

	public AddAnimalLevel(String _name) {
		super(_name);
		
		gifSectionPoint = new Point(100 , 50);
		classificationSectionPoint = new Point(100, 200);
		
		selectedGifTextBox = new UITextBox(new Rectangle(gifSectionPoint.x + 170, gifSectionPoint.y - 20, 180, 30), selectedGif);
		gifSelectionButton = new UISelectionMenu(new Rectangle(gifSectionPoint.x + 170,  gifSectionPoint.y - 20 + 30, 180, 80 ));
		
		embranchementInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30, 130, 30), "");
		classeInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 50, 130, 30), "");
		ordreInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 100, 130, 30), "");
		familleInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 150, 130, 30), "");
		genreInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 200, 130, 30), "");
		nomScientifiqueInputBox = new UIInputBox(new Rectangle(classificationSectionPoint.x + 50 + 170, classificationSectionPoint.y + 30 + 250, 130, 30), "");
		
	}

	@Override
	public void OnBegin(Object... params) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() throws Exception {
		
		selectedGifTextBox.SetText(selectedGif);
		gifSelectionButton.Update();
		
		embranchementInputBox.Update();
		classeInputBox.Update();
		ordreInputBox.Update();
		familleInputBox.Update();
		genreInputBox.Update();
		nomScientifiqueInputBox.Update();
		
		
		RefreshSelectionMenuGraphs();
	}

	@Override
	public void Draw() throws Exception {
		GraphicsSystem.GetInstance().SetBackgroundColor(Color.MAGENTA);
		
		GraphicsSystem.GetInstance().DrawText("Selectionnez un gif:", gifSectionPoint, Color.BLACK);
		selectedGifTextBox.Draw(10);
		gifSelectionButton.Draw(10);
		GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite("Assets/GIF/" + selectedGif), new Point(gifSectionPoint.x + 370, gifSectionPoint.y - 20), 1);
		
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
		
	}
	
	private void RefreshSelectionMenuGraphs() throws Exception{
    	LinkedHashMap<String, UIButton.Lambda> items = new LinkedHashMap<String, UIButton.Lambda>();
    	
    	final File folder = new File("./Assets/GIF");
    	for (final File fileEntry : folder.listFiles()) {
    		if(fileEntry.isFile()){
    			items.put(fileEntry.getName(), () -> {if(new File(fileEntry.getPath()).exists())selectedGif = fileEntry.getName();});
    		}
    	}
    	
    	gifSelectionButton.UpdateSelections(items);
    }
	
	private String selectedGif = "";
	
	private UITextBox selectedGifTextBox;
	private UISelectionMenu gifSelectionButton;
	private UIInputBox embranchementInputBox;
	private UIInputBox classeInputBox;
	private UIInputBox ordreInputBox;
	private UIInputBox familleInputBox;
	private UIInputBox genreInputBox;
	private UIInputBox nomScientifiqueInputBox;
	
	private final Point gifSectionPoint;
	private final Point classificationSectionPoint;

}
