package caterpillow.abstractprogram.structures.common;

import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.ExpressionStructure;

public class WhileStructure extends ExpressionStructure {

    public WhileStructure(CodeStatement structureStart) {
        super(structureStart);
    }

    public WhileStructure(CodeStatement structureStart, Expression expression) {
        super(structureStart, expression);
    }

}
