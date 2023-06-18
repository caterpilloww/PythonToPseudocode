package caterpillow.abstractprogram.random;

import caterpillow.abstractprogram.expressions.Expression;

public class Arguments {
    private Expression[] args;

    public Arguments(Expression[] args) {
        this.args = args;
    }

    public int len() {
        return args.length;
    }

    public Expression[] get() {
        return args;
    }
}
