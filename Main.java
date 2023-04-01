import CoreEngine.Window;
import ExceptionEngine.EngineException;
import MainEngine.Engine;

public class Main
{
    public static void main(String[] args)
    {
        // Paramètres d'exécution
        System.setProperty("jdk.xml.xpathExprGrpLimit", "2000");
        System.setProperty("jdk.xml.xpathExprOpLimit", "2000");
        // Simple exécution de la boucle du moteur
        try{
            Window.GetInstance();
            while(true){
                long beginFrame = System.currentTimeMillis();
                Engine.GetInstance().EngineLoop();
                // On ne souhaite avoir que 60 cycles par secondes, mais nous pouvons le modifier via le paramètre FPS
                while(System.currentTimeMillis() - beginFrame < (1 / FPS) * 1000);
            } 
        }
        catch(Exception e){
            EngineException.ShowMessageBox(e, "Java Swing Engine Exception");
        }
    }

    private final static float FPS = 60.f;
};