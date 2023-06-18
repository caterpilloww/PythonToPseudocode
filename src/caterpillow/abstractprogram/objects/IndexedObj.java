package caterpillow.abstractprogram.objects;

import caterpillow.abstractprogram.expressions.common.NumberExpression;

public class IndexedObj extends Obj {
    private Obj indexedObj;
    private NumberExpression numberExpression;

    public IndexedObj(String name, Obj indexedObj, NumberExpression numberExpression) {
        super(name);
        this.indexedObj = indexedObj;
        this.numberExpression = numberExpression;
    }

    public Obj getIndexedObj() {
        return indexedObj;
    }

    public void setIndexedObj(Obj indexedObj) {
        this.indexedObj = indexedObj;
    }

    public NumberExpression getNumberExpression() {
        return numberExpression;
    }

    public void setNumberExpression(NumberExpression numberExpression) {
        this.numberExpression = numberExpression;
    }
}
