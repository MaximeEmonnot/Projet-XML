public class Main
{
    public static void main(String[] args)
    {
        try{
            while(true) Engine.GetInstance().EngineLoop();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
};