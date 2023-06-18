package caterpillow.abstractprogram.actions;

import caterpillow.abstractprogram.expressions.Expression;

public class ReturnAction implements Action {
    private Expression returnExpression;

    public ReturnAction(Expression returnExpression) {
        this.returnExpression = returnExpression;
    }

    public Expression getReturnExpression() {
        return returnExpression;
    }

    public void setReturnExpression(Expression returnExpression) {
        this.returnExpression = returnExpression;
    }
}
