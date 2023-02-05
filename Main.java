public class Main
{
    public static void main(String[] args)
    {
        try{
            Window.GetInstance();

            while(true){
                long beginFrame = System.currentTimeMillis();
                Engine.GetInstance().EngineLoop();
                while(System.currentTimeMillis() - beginFrame < 17);
            } 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
};