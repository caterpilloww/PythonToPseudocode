package caterpillow.abstractprogram.expressions.common;

import caterpillow.abstractprogram.objects.Obj;

public class ListLiteralExpression extends ObjectExpression {
    private Obj[] objects;

    public ListLiteralExpression(String originalCode, Obj[] objects) {
        super(originalCode);
        this.objects = objects;
    }

    public Obj[] getObjects() {
        return objects;
    }

    public void setObjects(Obj[] objects) {
        this.objects = objects;
    }
}
