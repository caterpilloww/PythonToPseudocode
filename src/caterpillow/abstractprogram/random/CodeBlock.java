package caterpillow.abstractprogram.random;

import caterpillow.abstractprogram.structures.Structure;
import caterpillow.transformable.CodeElement;

import java.util.ArrayList;

public class CodeBlock {
    private Structure structure;
    private ArrayList<CodeElement> lines;

    public CodeBlock(Structure structure) {
        this.structure = structure;
        this.lines = new ArrayList<>();
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public ArrayList<CodeElement> getLines() {
        return lines;
    }

    public void setLines(ArrayList<CodeElement> lines) {
        this.lines = lines;
    }

    public void addAction(CodeElement action) {
        lines.add(action);
    }
}
