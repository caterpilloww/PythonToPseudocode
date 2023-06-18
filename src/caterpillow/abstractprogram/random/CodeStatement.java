package caterpillow.abstractprogram.random;

public class CodeStatement {
    private int indents;
    private String line;

    public CodeStatement(String line) {
        this.line = line;
    }

    public CodeStatement(int indents, String line) {
        this.indents = indents;
        this.line = line.trim();
    }

    public int getIndents() {
        return indents;
    }

    public void setIndents(int indents) {
        this.indents = indents;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
