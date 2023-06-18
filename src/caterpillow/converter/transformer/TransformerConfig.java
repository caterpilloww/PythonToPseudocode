package caterpillow.converter.transformer;

import caterpillow.abstractprogram.actions.Action;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.Structure;
import caterpillow.transformable.CodeElement;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class TransformerConfig {
    // idk how to do this

    public TransformerConfig() {
        setup();
    }

    public void setup() {

    }

    public Function<String, String> camelCase = s -> Character.toLowerCase(s.charAt(0)) + s.substring(1);
    public Function<String, String> tallCamelCase = s -> Character.toUpperCase(s.charAt(0)) + s.substring(1);
    public Function<String, String> toLower = s -> s.toLowerCase();
    public Function<String, String> toUpper = s -> s.toUpperCase();

    public int indentLength;

    public abstract String formatField(String field);

    public abstract String formatFunction(String field);

    public abstract String formatControl(String field);

    public abstract ArrayList<Function<CodeStatement, Action>> getAction();

    public abstract ArrayList<Function<CodeStatement, CodeElement>> getExpression();

    public abstract ArrayList<Function<CodeStatement, Structure>> getStructure();


}
