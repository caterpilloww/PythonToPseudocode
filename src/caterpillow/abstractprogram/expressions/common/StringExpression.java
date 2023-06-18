package caterpillow.abstractprogram.expressions.common;

import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.transformable.CodeElement;

public abstract class StringExpression extends Expression {
    private CodeElement[] concatenatedElements;

    public StringExpression(String originalCode, CodeElement[] concatenatedElements) {
        super(originalCode);
        this.concatenatedElements = concatenatedElements;
    }
}
