package caterpillow.abstractprogram.random;

import caterpillow.abstractprogram.objects.Obj;

public class Parameters {
    private Obj[] params;

    public Parameters(Obj[] params) {
        this.params = params;
    }

    public int len() {
        return params.length;
    }

    public Obj[] get() {
        return params;
    }
}
