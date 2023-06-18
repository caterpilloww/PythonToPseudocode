package caterpillow.converter.parser;

import caterpillow.abstractprogram.actions.Action;
import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.abstractprogram.objects.Obj;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.Structure;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class ParserConfig {
    protected ArrayList<Function<CodeStatement, Action>> actionRules;
    protected ArrayList<Function<CodeStatement, Structure>> structureRules;
    protected ArrayList<Function<String, Obj>> objRules;
    protected ArrayList<Function<String, Expression>> expressionRules;
    protected ArrayList<BiFunction<CodeStatement, Structure, Boolean>> structureLineRules;
    protected ArrayList<BiFunction<CodeStatement, Structure, Boolean>> structureEndRules;

    public ParserConfig() {
        this.actionRules = new ArrayList<>();
        this.structureRules = new ArrayList<>();
        this.objRules = new ArrayList<>();
        this.expressionRules = new ArrayList<>();
        this.structureLineRules = new ArrayList<>();
        this.structureEndRules = new ArrayList<>();

        createActionRules();
        createStructureRules();
        createStructureLineRules();
        createStructureEndRules();
        createObjRules();
        createExpressionRules();
    }

    protected abstract void createActionRules();

    protected abstract void createStructureRules();

    protected abstract void createStructureLineRules();

    protected abstract void createStructureEndRules();

    protected abstract void createObjRules();

    protected abstract void createExpressionRules();


    public ArrayList<Function<CodeStatement, Action>> getActionRules() {
        return actionRules;
    }

    public ArrayList<Function<CodeStatement, Structure>> getStructureRules() {
        return structureRules;
    }

    public ArrayList<BiFunction<CodeStatement, Structure, Boolean>> getStructureLineRules() {
        return structureLineRules;
    }

    public ArrayList<BiFunction<CodeStatement, Structure, Boolean>> getStructureEndRules() {
        return structureEndRules;
    }

    public ArrayList<Function<String, Obj>> getObjRules() {
        return objRules;
    }

    public ArrayList<Function<String, Expression>> getExpressionRules() {
        return expressionRules;
    }
}
