package caterpillow.pseudocode.transformer;

import caterpillow.abstractprogram.actions.Action;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.Structure;
import caterpillow.converter.transformer.TransformerConfig;
import caterpillow.transformable.CodeElement;

import java.util.ArrayList;
import java.util.function.Function;

public class PseudoTransformerConfig extends TransformerConfig {

    @Override
    public void setup() {
        this.indentLength = 4;
    }

    @Override
    public String formatField(String field) {
        return tallCamelCase.apply(field);
    }

    @Override
    public String formatFunction(String field) {
        return tallCamelCase.apply(field);
    }

    @Override
    public String formatControl(String field) {
        return toUpper.apply(field);
    }

    @Override
    public ArrayList<Function<CodeStatement, Action>> getAction() {
        return null;
    }

    @Override
    public ArrayList<Function<CodeStatement, CodeElement>> getExpression() {
        return null;
    }

    @Override
    public ArrayList<Function<CodeStatement, Structure>> getStructure() {
        return null;
    }
}
