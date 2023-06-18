package caterpillow.abstractprogram.actions;

public class ContinueAction implements Action{
    private String label;

    public ContinueAction(String label) {
        this.label = label;
    }

    public ContinueAction() {

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
