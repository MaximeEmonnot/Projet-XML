package UIEngine;
import java.awt.*;

import CoreEngine.Keyboard;
import CoreEngine.Mouse;
import GraphicsEngine.GraphicsSystem;

public class UIInputBox {
    
    public UIInputBox(Rectangle _rect, String _description){
        rect = _rect;
        description = _description;
    }

    public void Update(){
        if (Mouse.GetInstance().Read() == Mouse.EEventType.LRelease)
            bIsFocused = rect.contains(Mouse.GetInstance().GetMousePos());

        if (bIsFocused){
            char c = Keyboard.GetInstance().ReadChar();
            if (authorizedChar.contains(Character.toString(c))) text += c;
        }
    }

    public void Draw(){
        Draw(0);
    }
    public void Draw(int priority){
        GraphicsSystem.GetInstance().DrawRect(rect, Color.WHITE, true, priority);
        GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, false, priority + 1);
        String textToDraw = new String();
        Color textColor = Color.BLACK;
        Font textFont = null;
        if (!bIsFocused){
            textToDraw = description;
            textColor = Color.GRAY;
            textFont = new Font("Arial Bold", Font.ITALIC, 16);
        }
        else{
            textToDraw = text;
            textFont = new Font("Arial Bold", Font.PLAIN, 16);
        } 
        GraphicsSystem.GetInstance().DrawText(textToDraw, new Point(
            rect.x + (int)(0.1f * rect.width),
            rect.y + (int)(0.6f * rect.height)
        ), textFont, textColor, priority + 2);
    }

    private Rectangle rect;
    private String description;
    private String text;
    private boolean bIsFocused = false;
    private final static String authorizedChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?!,;.:/\\\'\"°+-*@&éèêàùç²{([|])}=_><%$€#~ ";
}
