package caterpillow.pseudocode.transformer;

import caterpillow.abstractprogram.actions.Action;
import caterpillow.abstractprogram.actions.AssignmentAction;
import caterpillow.abstractprogram.actions.ReturnAction;
import caterpillow.abstractprogram.comment.Comment;
import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.abstractprogram.expressions.common.FunctionExpression;
import caterpillow.abstractprogram.expressions.common.ObjectExpression;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.FunctionStructure;
import caterpillow.abstractprogram.structures.Structure;
import caterpillow.abstractprogram.structures.common.IfElseStructure;
import caterpillow.abstractprogram.structures.common.IteratorStructure;
import caterpillow.abstractprogram.structures.common.MainStructure;
import caterpillow.abstractprogram.structures.common.WhileStructure;
import caterpillow.converter.transformer.Transformer;
import caterpillow.converter.transformer.TransformerConfig;

import java.util.ArrayList;
import java.util.Arrays;

public class PseudoTransformer extends Transformer {
    public PseudoTransformer(TransformerConfig config) {
        super(config);
    }

    @Override
    public void fixStructure(Structure structure) {
        if (structure instanceof IfElseStructure && structure.getCodeBlocks().size() > 2) {
            IfElseStructure ifElseStructure = (IfElseStructure) structure;
            CodeStatement structureLine = ifElseStructure.getStructureLines().get(1);
            String exp = structureLine.getLine().substring(5);
            IfElseStructure expandedStructure = new IfElseStructure(new CodeStatement(structure.getIndentationLevel(), "if " + exp), ifElseStructure.getExpressions().get(1));
            expandedStructure.addElements(ifElseStructure.getCodeBlocks().get(1));
            ifElseStructure.getExpressions().set(1, new ObjectExpression(""));
            ifElseStructure.getStructureLines().set(1, new CodeStatement(structureLine.getIndents(), "ELSE"));
            ifElseStructure.getCodeBlocks().set(1, new ArrayList<>(Arrays.asList(new Action[]{expandedStructure})));
            while (ifElseStructure.getCodeBlocks().size() > 2) {
                expandedStructure.addStructureLine(ifElseStructure.getStructureLines().remove(2));
                expandedStructure.addElements(ifElseStructure.getCodeBlocks().remove(2));
            }
        }
    }


    public String[] transformAction(Action action) {
        StringBuilder sb = new StringBuilder();
        switch (action.getClass().getSimpleName()) {
            case "AssignmentAction":
                AssignmentAction aa = (AssignmentAction) action;
                sb.append(config.formatField(aa.getTarget().getName()));
                sb.append(" = ");
                sb.append(transformExpression(aa.getExpression())[0]);
                return new String[]{sb.toString()};
            case "FunctionExpression":
                FunctionExpression fe = (FunctionExpression) action;
                // special case
                if (fe.getFunctionName().startsWith("print(")) {
                    if (fe.getFunctionName().contains("(f\"")) {
                        return new String[]{"Display " + fe.getFunctionName().substring(7, fe.getFunctionName().length() - 1)
                                .replace("{", "\" ")
                                .replace("}", " \"")
                                .replace("\"\"", "")
                                .replace("  ", " ")};
                    } else
                        return new String[]{"Display " + fe.getFunctionName().substring(6, fe.getFunctionName().length() - 1)}; // sussy
                }
                sb.append(config.formatFunction(fe.getFunctionName()));
                // im not formatting the arguments, screw u everything is in the function name
//                sb.append("(");
//                sb.append(fe.getArgs())
                return new String[]{sb.toString()};
            case "Comment":
                Comment cm = (Comment) action;
                sb.append("\'");
                sb.append(cm.getComment());
                return new String[]{sb.toString()};
            case "BreakAction":
                return new String[]{"BREAK"};
            case "ReturnAction":
                return new String[]{"RETURN " + transformExpression(((ReturnAction) action).getReturnExpression())[0]};
        }
        return new String[0];
    }

    @Override
    public String[] transformExpression(Expression expression) {
        // basic n00b sht
        System.out.println(expression);
        System.out.println(expression.getOriginalCode());
        return new String[]{expression.getOriginalCode().replace(" and ", " AND ").replace(" or ", " OR ")};
    }

    @Override
    public String[] transformStructure(Structure structure) {
        fixStructure(structure);
        if (structure instanceof IfElseStructure) {
            return new String[]{"IF " + config.formatField(structure.getStructureLines().get(0).getLine().substring(3))};
        } else if (structure instanceof WhileStructure) {
            return new String[]{"WHILE " + config.formatField(structure.getStructureLines().get(0).getLine().substring(6))};
        } else if (structure instanceof FunctionStructure) {
            return new String[]{"BEGIN " + config.formatFunction(structure.getStructureLines().get(0).getLine().substring(4)).replace("()", "")};
        } else if (structure instanceof IteratorStructure) {
            IteratorStructure iteratorStructure = (IteratorStructure) structure;
            return new String[]{"list = " + transformExpression(iteratorStructure.getIterator())[0],
                    "FOR Counter = 0 TO Length of list STEP 1",
                    indent + iteratorStructure.getObj().getName() + " = list(Counter)"
            };
        } else if (structure instanceof MainStructure) {
            return new String[]{"", "BEGIN " + ((MainStructure) structure).getStructureLines().get(0).getLine().toUpperCase()};
        }
        return new String[]{structure.getStructureLines().get(0).getLine()};
    }

    @Override
    public String[] transformStructureLine(Structure structure, int lineCount) {
        if (structure instanceof IfElseStructure) {
            return new String[]{"ELSE"};
        }
        return new String[]{structure.getStructureLines().get(lineCount).getLine().toUpperCase()};
    }

    @Override
    public String[] transformStructureEnd(Structure structure) {
        if (structure instanceof IfElseStructure) {
            return new String[]{"ENDIF"};
        } else if (structure instanceof WhileStructure) {
            return new String[]{"ENDWHILE"};
        } else if (structure instanceof FunctionStructure) {
            return new String[]{"END " + config.formatFunction(((FunctionStructure) structure).getFunctionName()), ""};
        } else if (structure instanceof IteratorStructure) {
            return new String[]{"NEXT Counter"};
        } else if (structure instanceof MainStructure) {
            return new String[]{"END " + ((MainStructure) structure).getStructureLines().get(0).getLine().toUpperCase()};
        }
        return new String[]{"END" + structure.getClass().getSimpleName().toUpperCase(), ""};
    }


}
