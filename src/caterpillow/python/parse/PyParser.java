package caterpillow.python.parse;


import caterpillow.abstractprogram.actions.*;
import caterpillow.abstractprogram.comment.Comment;
import caterpillow.abstractprogram.expressions.common.FunctionExpression;
import caterpillow.abstractprogram.expressions.common.ListLiteralExpression;
import caterpillow.abstractprogram.expressions.common.ObjectExpression;
import caterpillow.abstractprogram.objects.InferencedObj;
import caterpillow.abstractprogram.objects.Obj;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.random.Parameters;
import caterpillow.abstractprogram.structures.FunctionStructure;
import caterpillow.abstractprogram.structures.Structure;
import caterpillow.abstractprogram.structures.common.*;
import caterpillow.converter.parser.CodeParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PyParser extends CodeParser {

    public PyParser(String fileName, ArrayList<CodeStatement> inputCode) {
        super(fileName, inputCode);
    }

    @Override
    public void appendAction(Action action) {
        if (currentStructure() instanceof NullStructure && !(action instanceof Structure) && !(action instanceof Comment)) {
            MainStructure main = new MainStructure(new CodeStatement(-1, fileName.split("\\.(?=[^\\.]+$)")[0]));
            appendAction(main);
            stack.push(main);
        }
        super.appendAction(action);
    }

    @Override
    public Structure parseNewStructure(CodeStatement line) {
        Structure structure = super.parseNewStructure(line);
        if (structure != null) {
            for (int i = 0; i < currentStructure().getIndentationLevel() - line.getIndents() + 1; i++) {
                popStructure();
            }
        }
        return structure;
    }

    @Override
    public Action parseAction(CodeStatement line) {
        Action action = super.parseAction(line);
        if (action != null) {
            for (int i = 0; i < currentStructure().getIndentationLevel() - line.getIndents() + 1; i++) {
                popStructure();
            }
        }
        return action;
    }

    @Override
    protected void createActionRules() {
        // Comment
        actionRules.add(statement -> statement.getLine().startsWith("#") ? new Comment(statement.getLine().substring(1).trim()) : null);

        // Assignment Action
        actionRules.add(statement -> {
            String line = statement.getLine();
            String[] words = line.split(" ");
            if (words.length < 3)
                return null;
            if (!line.endsWith(":") && words[1].endsWith("=")) {
                return new AssignmentAction(parseObject(words[0]), parseExpression(String.join(" ", Arrays.copyOfRange(words, 2, words.length))));
            }
            return null;
        });

        // Function call
        actionRules.add(statement -> {
            String line = statement.getLine();
            if (line.endsWith(")")) {
                // proper f string check later?
//                String functionName = line.substring(0, line.indexOf("("));
//                String args = line.substring(line.indexOf("("), line.length() - 1);
//                return new FunctionExpression();
                // FK THIS
                return new FunctionExpression(line);
            }
            return null;
        });

        actionRules.add(statement -> {
            String line = statement.getLine();
            if (line.startsWith("return ")) {
                return new ReturnAction(new ObjectExpression(line.substring(7)));
            }
            return null;
        });
        actionRules.add(statement -> {
            String line = statement.getLine();
            if (line.equals("continue")) {
                return new ContinueAction();
            }
            return null;
        });
        actionRules.add(statement -> {
            String line = statement.getLine();
            if (line.equals("break")) {
                return new BreakAction();
            }
            return null;
        });
        // Comment lmao
        actionRules.add(statement -> new Comment(statement.getLine()));
    }

    @Override
    protected void createStructureRules() {
        structureRules.add(statement -> {
            String line = statement.getLine();
            if (line.endsWith(":") && line.startsWith("if "))
                return new IfElseStructure(statement, parseExpression(line.substring(3, line.length() - 1)));
            return null;
        });

        structureRules.add(statement -> {
            String line = statement.getLine();
            if (line.endsWith(":") && line.startsWith("def ")) {
                String[] splitParams = line.substring(4, line.length() - 2).split(", ");
                Obj[] objParams = new Obj[splitParams.length];
                for (int i = 0; i < splitParams.length; i++) {
                    objParams[i] = new InferencedObj(splitParams[i].split(" ")[0]);
                }
                return new FunctionStructure(statement, line.substring(4, line.indexOf("(")), new Parameters(objParams));
            }
            return null;
        });

        structureRules.add(statement -> {
            String line = statement.getLine();
            if (line.endsWith(":") && line.startsWith("while "))
                return new WhileStructure(statement, parseExpression(line.substring(6, line.length() - 1)));
            return null;
        });

        structureRules.add(statement -> {
            String line = statement.getLine();
            String[] split = line.split(" ");
            if (line.endsWith(":") && split[0].equals("for") && split.length > 3 && split[2].equals("in")) {
                int expressionStart = 3 + split[0].length() + split[1].length() + split[2].length(); // idc
                return new IteratorStructure(statement, new InferencedObj(split[1]), (ObjectExpression) parseExpression(line.substring(expressionStart, line.length() - 1)));
            }
            return null;
        });

    }

    @Override
    protected void createStructureLineRules() {
        structureLineRules.add((statement, structure) -> {
            if (structure instanceof IfElseStructure) {
                IfElseStructure ifElseStructure = (IfElseStructure) structure;
                String line = statement.getLine();
                if (line.endsWith(":") && line.startsWith("elif ")) {
                    ifElseStructure.addStructureLine(statement, parseExpression(line.substring(5, line.length() - 2)));
                    return true;
                }
            }
            return false;
        });

        structureLineRules.add((statement, structure) -> {
            if (structure instanceof IfElseStructure) {
                IfElseStructure ifElseStructure = (IfElseStructure) structure;
                String line = statement.getLine();
                System.out.print(line + " : " + line.equals("else:"));
                if (line.equals("else:")) {
                    ifElseStructure.addStructureLine(statement, null);
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    protected void createStructureEndRules() {
//        structureEndRules.add((statement, structure) -> {
//            System.out.print(statement.getIndents() + " - " + structure.getIndentationLevel() + " ");
//            if (statement.getIndents() == structure.getIndentationLevel()) {// so the code continues
//                System.out.print("ended structure " + structure.getClass().getSimpleName() + " ");
//                popStructure();
//            }
//            return false;
//        });
    }

    @Override
    protected void createObjRules() {
        objRules.add(s -> new InferencedObj(s));
    }

    @Override
    protected void createExpressionRules() {
        expressionRules.add(s -> {
            if (s.trim().startsWith("[") && s.trim().endsWith("]")) {
                String[] split = s.substring(1, s.length() - 1).split(", ");
                return new ListLiteralExpression(s, Arrays.stream(split).map(InferencedObj::new).collect(Collectors.toList()).toArray(new Obj[split.length])); // noob and has bug potential butw ho care
            }
            return null;
        });
        expressionRules.add(s -> new ObjectExpression(s));
    }

    @Override
    public void finalize() {
        while (stack.size() > 1) {
            stack.pop();
        }
    }
}
