package caterpillow.abstractprogram.structures.common;

import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.Structure;

public class NullStructure extends Structure {


    public NullStructure() {
        super(new CodeStatement(-999 , ""));
    }

}
