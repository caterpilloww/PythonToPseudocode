package caterpillow.abstractprogram.structures;

import caterpillow.abstractprogram.actions.Action;
import caterpillow.abstractprogram.random.CodeStatement;

import java.util.ArrayList;

/*something like
 * if(expression){
 *       code
 *       code
 * }
 *
 * or
 *
 * if expression:
 *      code
 *      code
 *
 * */
public abstract class Structure implements Action {
    private int indentationLevel;
    private ArrayList<CodeStatement> structureLines;
    private ArrayList<ArrayList<Action>> codeBlocks;

    public Structure(CodeStatement structureStart) {
        this.structureLines = new ArrayList<CodeStatement>();
        this.indentationLevel = structureStart.getIndents();
        this.codeBlocks = new ArrayList<>();
        this.addStructureLine(structureStart);
    }

    public ArrayList<CodeStatement> getStructureLines() {
        return structureLines;
    }

    public void setStructureLines(ArrayList<CodeStatement> structureLines) {
        this.structureLines = structureLines;
    }

    public void addElement(Action action) {
        this.codeBlocks.get(codeBlocks.size() - 1).add(action);
    }

    public void addStructureLine(CodeStatement structureLine) {
        this.structureLines.add(structureLine);
        this.codeBlocks.add(new ArrayList<>());
    }

    public void addElements(ArrayList<Action> actions) {
        this.codeBlocks.get(codeBlocks.size() - 1).addAll(actions);
    }

    public int getIndentationLevel() {
        return indentationLevel;
    }

    public void setIndentationLevel(int indentationLevel) {
        this.indentationLevel = indentationLevel;
    }

    public ArrayList<ArrayList<Action>> getCodeBlocks() {
        return codeBlocks;
    }

    public ArrayList<Action> getCodeBlock() {
        return getCodeBlocks().get(0);
    }

    public void setCodeBlocks(ArrayList<ArrayList<Action>> codeBlocks) {
        this.codeBlocks = codeBlocks;
    }

}
