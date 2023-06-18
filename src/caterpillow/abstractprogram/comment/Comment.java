package caterpillow.abstractprogram.comment;

import caterpillow.abstractprogram.actions.Action;

public class Comment implements Action {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
