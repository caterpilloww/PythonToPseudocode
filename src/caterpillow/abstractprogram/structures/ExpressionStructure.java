package caterpillow.abstractprogram.structures;

import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.abstractprogram.random.CodeStatement;

import java.util.ArrayList;

public abstract class ExpressionStructure extends Structure {
    protected ArrayList<Expression> expressions;

    public ExpressionStructure(CodeStatement structureStart) {
        super(structureStart);
        this.expressions = new ArrayList<>();
    }

    public ExpressionStructure(CodeStatement structureStart, Expression expression) {
        super(structureStart);
        this.expressions = new ArrayList<>();
        this.expressions.add(expression);
    }

//    public Expression parseExpression(String structureStart) {
//        return new BooleanExpression(structureStart.substring(Math.min(structureStart.contains("(") ? structureStart.indexOf("(") : 1000, structureStart.contains(" ") ? structureStart.indexOf(" ") : 1000)));
//    }

    public Expression getExpression() {
        return expressions.get(0);
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    public void addStructureLine(CodeStatement structureLine, Expression expression) {
        super.addStructureLine(structureLine);
        this.expressions.add(expression);
    }

}
