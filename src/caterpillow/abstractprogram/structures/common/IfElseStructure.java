package caterpillow.abstractprogram.structures.common;

import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.ExpressionStructure;

public class IfElseStructure extends ExpressionStructure {
    public IfElseStructure(CodeStatement structureStart) {
        super(structureStart);
    }

    public IfElseStructure(CodeStatement structureStart, Expression expression) {
        super(structureStart, expression);
    }
}
