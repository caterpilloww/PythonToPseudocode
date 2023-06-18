package caterpillow.abstractprogram.objects;

public abstract class ArrayObj extends Obj {
    public Obj subObj;

    public ArrayObj(String name, Obj subObj) {
        super(name);
        this.subObj = subObj;
    }
}
