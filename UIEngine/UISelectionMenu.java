package UIEngine;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import CoreEngine.Mouse;
import GraphicsEngine.GraphicsSystem;

/*
 * Classe d'un menu de sélection. Définit une liste de boutons à cliquer
 */
public class UISelectionMenu {
	
	public UISelectionMenu(Rectangle _rect) {
		this.rect = _rect;
		items = new LinkedHashMap<String, UILambda>();
		buttons = new ArrayList<UIButton>();
		rect.height -= rect.height % ITEM_HEIGHT;
		scrollBarHeight = items.size() <= MaxItems() ? rect.height : rect.height / ((float)items.size() /  MaxItems());
		UpdateButtons();
	}
	
	public void UpdateSelections(LinkedHashMap<String, UILambda> _items) {
		if(items.keySet().equals(_items.keySet()))
			return;
		this.items = _items;
		scrollBarHeight = items.size() <= MaxItems() ? rect.height : rect.height / ((float)items.size() / MaxItems());
		scrollPosition = 0;
		UpdateButtons();
	}
	
	// Update du menu
	// Parcours du menu à la molette
	public void Update() throws Exception {
		if (scrollPosition > 0 && Mouse.GetInstance().Read() == Mouse.EEventType.WheelUp && rect.contains(Mouse.GetInstance().GetMousePos())){
			scrollPosition--;
			UpdateButtons();
		}else if(scrollPosition < items.size() - MaxItems() && Mouse.GetInstance().Read() == Mouse.EEventType.WheelDown && rect.contains(Mouse.GetInstance().GetMousePos())) {
			scrollPosition++;
			UpdateButtons();
		}
		for(UIButton button : buttons) {
			button.Update();
		}
	}
	
	// Affichage du menu
	// Affichage des différents boutons
	// Affichage de la barre de scroll
	public void Draw(int priority) {
		
		final float y = items.size() <= MaxItems() ? rect.y : rect.y + (rect.height - scrollBarHeight) * (scrollPosition / (float)(items.size() - MaxItems())) + 1;
		final Rectangle rectScrollBar = new Rectangle(rect.x + rect.width - PADDING, (int) y, PADDING, (int) scrollBarHeight);
		GraphicsSystem.GetInstance().DrawRect(rectScrollBar, Color.WHITE, true, priority + 1);
		
		final Rectangle rectScrollBg = new Rectangle(rect.x + rect.width - PADDING, rect.y, PADDING, rect.height);
		GraphicsSystem.GetInstance().DrawRect(rectScrollBg, Color.BLACK, true, priority);
		GraphicsSystem.GetInstance().DrawRect(rectScrollBg, Color.BLACK, false, priority + 1);
		
		final Rectangle selectBg = new Rectangle(rect.x, rect.y, rect.width - PADDING, rect.height);
		GraphicsSystem.GetInstance().DrawRect(selectBg, Color.WHITE, true, priority);
		GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, false, priority + 1);
		for(UIButton button : buttons) {
			button.Draw(priority + 1);
		}
		
	}

	public void SetItemHeight(int newItemHeight) {
		ITEM_HEIGHT = newItemHeight;
		UpdateButtons();
	}

	public void SetScrollBarSize(int size){
		PADDING = size;
		UpdateButtons();
	}
	
	private void UpdateButtons() {
		buttons.clear();
		String[] itemsName = items.keySet().toArray(new String[items.keySet().size()]);
		
		for(int i = 0; i < MaxItems() && i < items.size(); i++) {
			Rectangle _rect = new Rectangle(rect.x, rect.y + ITEM_HEIGHT * i, rect.width - PADDING, ITEM_HEIGHT);
			buttons.add(new UIButton(_rect, itemsName[i + scrollPosition], items.get(itemsName[i + scrollPosition]), Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY));
		}
		
	}
	
	private int MaxItems() {
		
		return rect.height / ITEM_HEIGHT;
	}
	
	private Rectangle rect;
	private LinkedHashMap<String, UILambda> items;
	private ArrayList<UIButton> buttons;
	private int scrollPosition = 0;
	private float scrollBarHeight;
	
	private int ITEM_HEIGHT = 30;
	private int PADDING = 20;

}