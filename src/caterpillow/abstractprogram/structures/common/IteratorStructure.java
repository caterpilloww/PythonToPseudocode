package caterpillow.abstractprogram.structures.common;

import caterpillow.abstractprogram.expressions.common.ObjectExpression;
import caterpillow.abstractprogram.objects.Obj;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.Structure;

public class IteratorStructure extends Structure {
    private Obj obj;
    private ObjectExpression iterator;

    public IteratorStructure(CodeStatement structureStart, Obj obj, ObjectExpression iterable) {
        super(structureStart);
        this.obj = obj;
        this.iterator = iterable;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public ObjectExpression getIterator() {
        return iterator;
    }

    public void setIterator(ObjectExpression iterator) {
        this.iterator = iterator;
    }
}
