package caterpillow.converter.transformer;

import caterpillow.abstractprogram.actions.Action;
import caterpillow.abstractprogram.expressions.Expression;
import caterpillow.abstractprogram.structures.Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Transformer {
    protected String indent;
    protected TransformerConfig config;

    public Transformer(TransformerConfig config) {
        this.config = config;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < config.indentLength; i++)
            sb.append(' ');
        this.indent = sb.toString();
        System.out.println("indent ->" + this.indent + "<-");
    }

    public void fixStructure(Structure structure) {

    }


    public ArrayList<String> transform(Structure basicStructure) {
        fixStructure(basicStructure);
        Structure activeStructure = basicStructure;
        ArrayList<String> codeLines = parseCodeBlock(activeStructure.getCodeBlock(), 0);
        return codeLines;
    }

    public ArrayList<String> parseCodeBlock(ArrayList<Action> actions, int recursionDepth) {
        ArrayList<String> codeLines = new ArrayList<>();
        for (Action action : actions) {
            // recursion
            if (action instanceof Structure) {
                Structure structure = (Structure) action;
                for (int i = 0; i < structure.getCodeBlocks().size(); i++) {
                    codeLines.addAll(Arrays.asList(i == 0 ? transformStructure(structure) : transformStructureLine(structure, i)));
                    insertCodeBlock(codeLines, parseCodeBlock(structure.getCodeBlocks().get(i), recursionDepth + 1));
                }
                codeLines.addAll(Arrays.asList(transformStructureEnd(structure)));
            }
            codeLines.addAll(Arrays.asList(transformAction(action)));
        }
        return codeLines;
    }

    public void insertCodeBlock(ArrayList<String> outer, ArrayList<String> inner) {
        ArrayList<String> copy = new ArrayList<>();
        for (String line : inner) {
            copy.add(indent + line);
        }
        outer.addAll(copy);
    }

    public abstract String[] transformAction(Action action);

    public abstract String[] transformExpression(Expression expression);

    public abstract String[] transformStructure(Structure structure);

    public abstract String[] transformStructureLine(Structure structure, int lineCount);

    public abstract String[] transformStructureEnd(Structure structure);

}
