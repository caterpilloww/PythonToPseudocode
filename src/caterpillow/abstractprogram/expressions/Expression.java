package caterpillow.abstractprogram.expressions;

import caterpillow.transformable.CodeElement;

public abstract class Expression implements CodeElement {

    private String originalCode;

    public Expression(String originalCode) {
        this.originalCode = originalCode; // will delete if i ever want to expand this
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

}
