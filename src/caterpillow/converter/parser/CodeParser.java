package caterpillow.converter.parser;

import caterpillow.abstractprogram.actions.Action;
import caterpillow.abstractprogram.comment.Comment;
import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.abstractprogram.objects.Obj;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.Structure;
import caterpillow.abstractprogram.structures.common.NullStructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class CodeParser extends ParserConfig {
    public ArrayList<CodeStatement> code;
    public Deque<Structure> stack;
    public String fileName;


    /*
     * Parse checking order:
     * -> Structure elements
     *   -> expressions
     * -> Actions
     *   -> blah
     *
     *
     * Abstraction for code
     * -> Structure
     *   -> Basic Structure
     *       -> Try-catch
     *       -> Expression Structure
     *           -> While
     *       -> Multi-Expression Structure
     *           -> If-Else
     * -> Action
     *   -> Assignment
     *       -> Object
     *       -> Expression
     *           -> String/Number/Boolean/Unknown
     *   -> Function Expression
     *       -> Return Type
     *       -> Arguments
     *   -> Comment
     *  */


    public CodeParser(String fileName, ArrayList<CodeStatement> inputCode) {
        super();
        this.code = inputCode;
        this.fileName = fileName;
        this.stack = new ArrayDeque<>();
    }


    public Structure parse() {
        Structure baseStructure = new NullStructure();
        stack.push(baseStructure);
        for (CodeStatement line : code) {
            System.out.print(line.getIndents() + " : " + line.getLine() + " -> ");
            parseLine(line);
        }
        finalize();
        return baseStructure;
    }

    public void parseLine(CodeStatement line) {


        Structure structure = parseNewStructure(line);
        if (structure != null) {
            appendAction(structure);
            stack.push(structure);
            System.out.println(structure.getClass().getSimpleName());
            return;
        }

        if (parseStructureLine(line)) {
            System.out.println(currentStructure().getClass().getSimpleName() + " new section");
            return;
        }

        if (parseStructureEnd(line)) {
            System.out.println(currentStructure().getClass().getSimpleName() + " end");
            popStructure();
            return;
        }

        Action action = parseAction(line);
        if (action != null) {
            appendAction(action);
            System.out.println(action.getClass().getSimpleName() + " in " + getStackString());
            return;
        }

        appendComment(line);
        System.out.println("comment");

    }

    public Action parseAction(CodeStatement line) {
        for (Function<CodeStatement, Action> func : getActionRules()) {
            Action val = func.apply(line);
            if (val != null)
                return val;
        }
        return null;
    }

    public Structure parseNewStructure(CodeStatement line) {
        for (Function<CodeStatement, Structure> func : getStructureRules()) {
            Structure val = func.apply(line);
            if (val != null)
                return val;
        }
        return null;
    }

    public boolean parseStructureLine(CodeStatement line) {
        for (BiFunction<CodeStatement, Structure, Boolean> func : getStructureLineRules()) {
            boolean val = func.apply(line, currentStructure());
            if (val)
                return true;
        }
        return false;
    }

    public boolean parseStructureEnd(CodeStatement line) {
        for (BiFunction<CodeStatement, Structure, Boolean> func : getStructureEndRules()) {
            boolean val = func.apply(line, currentStructure());
            if (val)
                return true;
        }
        return false;
    }

    public Obj parseObject(String code) {
        for (Function<String, Obj> func : getObjRules()) {
            Obj val = func.apply(code);
            if (val != null)
                return val;
        }
        return null;
    }

    public Expression parseExpression(String code) {
        for (Function<String, Expression> func : getExpressionRules()) {
            Expression val = func.apply(code);
            if (val != null)
                return val;
        }
        return null;
    }

    public String getStackString() {
        StringBuilder sb = new StringBuilder();
        for (Structure structure : stack) {
            sb.append(structure.getClass().getSimpleName() + " ");
        }
        return sb.toString();
    }

    public Structure currentStructure() {
        return stack.peek();
    }

    public void popStructure() {
        stack.pop();
    }

    public void appendAction(Action action) {
        stack.peek().addElement(action);
    }

    public void appendComment(CodeStatement statement) {
        stack.peek().addElement(new Comment(statement.getLine()));
    }

    public void finalize() {

    }
}
