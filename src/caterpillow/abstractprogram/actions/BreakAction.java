package caterpillow.abstractprogram.actions;

public class BreakAction implements Action {
    private String label;

    public BreakAction() {
    }

    public BreakAction(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
