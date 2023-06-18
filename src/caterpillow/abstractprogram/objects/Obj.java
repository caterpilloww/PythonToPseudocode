package caterpillow.abstractprogram.objects;

import caterpillow.transformable.CodeElement;

public abstract class Obj implements CodeElement {
    private String name;

    public Obj(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
