package UIEngine;
import java.awt.*;

import CoreEngine.Mouse;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.SpriteFactory;

public class UICard {
    private enum ECardState{
        BaseState,
        HoverState,
        PressState
    }

    public UICard(Rectangle _rect, String _text, String _imagePath, UILambda _func,
        Color _baseColor, Color _hoverColor, Color _pressColor)
    {
        rect = _rect;
        text = _text;
        imagePath = _imagePath;
        func = _func;
        baseColor = _baseColor;
        hoverColor = _hoverColor;
        pressColor = _pressColor;
    }

    public void Update() throws Exception {
        if (rect.contains(Mouse.GetInstance().GetMousePos())){
            state = ECardState.HoverState;
            if (Mouse.GetInstance().Read() == Mouse.EEventType.LRelease){
                state = ECardState.PressState;
                func.func();
            }
        }
        else state = ECardState.BaseState;
    }

    public void Draw() {
        Draw(0);
    }
    public void Draw(int priority){
        Color cardColor = baseColor;
        switch(state){
        case HoverState:
            cardColor = hoverColor;
            break;
        case PressState:
            cardColor = pressColor;
            break;
        default:
             break;
        }

        GraphicsSystem.GetInstance().DrawRect(rect, cardColor, true, priority);
        GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, false, priority + 1);
        GraphicsSystem.GetInstance().DrawRect(new Rectangle(rect.x + (int)(rect.width * 0.1), rect.y + (int)(rect.height * 0.1), (int)(rect.width * 0.8), (int)(rect.height * 0.7)), Color.BLACK, false, priority + 2);
        GraphicsSystem.GetInstance().DrawSprite(SpriteFactory.GetInstance().GetSprite(imagePath), new Rectangle(rect.x + (int)(rect.width * 0.1), rect.y + (int)(rect.height * 0.1), (int)(rect.width * 0.8), (int)(rect.height * 0.7)), priority + 1);
        GraphicsSystem.GetInstance().DrawText(text, new Point(
            rect.x + (int)((rect.width - text.length() * 8) / 2),
            rect.y + (int)(0.9f * rect.height + 0.5f * FONTSIZE * 0.75f)
        ), new Font("Arial Bold", Font.PLAIN, FONTSIZE), Color.BLACK, priority + 3);
    }

    public void SetRectangle(Rectangle newRect){
        rect = newRect;
    }

    public void SetText(String newText){
        text = newText;
    }

    public void SetImage(String newImagePath){
        imagePath = newImagePath;
    }

    public void SetFunction(UILambda newFunc){
        func = newFunc;
    }

    public void SetBaseColor(Color newBaseColor){
        baseColor = newBaseColor;
    }

    public void SetHoverColor(Color newHoverColor){
        hoverColor = newHoverColor;
    }

    public void SetPressColor(Color newPressColor){
        pressColor = newPressColor;
    }

    private Rectangle rect;
    private String text;
    private String imagePath;
    private UILambda func;
    private Color baseColor;
    private Color hoverColor;
    private Color pressColor;
    private ECardState state = ECardState.BaseState;
    private final static int FONTSIZE = 16;
}
