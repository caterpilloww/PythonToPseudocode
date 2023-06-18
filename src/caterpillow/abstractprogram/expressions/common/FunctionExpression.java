package caterpillow.abstractprogram.expressions.common;

import caterpillow.abstractprogram.actions.Action;
import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.abstractprogram.random.Arguments;

public class FunctionExpression extends Expression implements Action {

    private String functionName; // TODO: turn into array later for those stacked calls
    private Arguments args;

    // screw this
    public FunctionExpression(String functionName, Arguments args) {
        super("ppoopyy");
    }

    public FunctionExpression(String function) {
        super(function);
        this.functionName = function;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Arguments getArgs() {
        return args;
    }

    public void setArgs(Arguments args) {
        this.args = args;
    }
}
