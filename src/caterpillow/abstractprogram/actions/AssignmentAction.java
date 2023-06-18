package caterpillow.abstractprogram.actions;

import caterpillow.abstractprogram.objects.Obj;
import caterpillow.abstractprogram.expressions.Expression;

public class AssignmentAction implements Action {
    private Obj target;
    private Expression expression;

    public AssignmentAction(Obj target, Expression expression) {
        super();
        this.target = target;
        this.expression = expression;
    }

    public Obj getTarget() {
        return target;
    }

    public AssignmentAction setTarget(Obj target) {
        this.target = target;
        return this;
    }

    public Expression getExpression() {
        return expression;
    }

    public AssignmentAction setExpression(Expression expression) {
        this.expression = expression;
        return this;
    }
}
