package caterpillow.abstractprogram.structures;

import caterpillow.abstractprogram.objects.Obj;
import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.random.Parameters;

public class FunctionStructure extends Structure {

    private Parameters params;
    private String functionName;

    public FunctionStructure(CodeStatement structureStart, String functionName, Parameters params) {
        super(structureStart);
        this.params = params;
        this.functionName = functionName;
    }

    public int paramLength() {
        return params.len();
    }

    public String getFunctionName() {
        return functionName;
    }

    public Obj[] params() {
        return params.get();
    }
}
