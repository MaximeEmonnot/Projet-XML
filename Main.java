public class Main
{
    public static void main(String[] args)
    {
        try{
            Window.GetInstance();
            while(true){
                long beginFrame = System.currentTimeMillis();
                Engine.GetInstance().EngineLoop();
                while(System.currentTimeMillis() - beginFrame < (1 / FPS) * 1000);
            } 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private final static float FPS = 60.f;
};