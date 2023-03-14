package MainEngine.Levels;

public abstract class ALevel {

    public ALevel(String _name) {
        name = _name;
     }

    public abstract void OnBegin(Object... params) throws Exception;
    public abstract void Update() throws Exception;
    public abstract void Draw() throws Exception;

    public String GetName() {
        return name;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof ALevel)) return false;
        ALevel rhs = (ALevel)o;
        return name.equals(rhs.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    private String name;
}
