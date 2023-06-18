package caterpillow;

import caterpillow.abstractprogram.random.CodeStatement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileParser {

    public ArrayList<CodeStatement> getCodeStatements(String path) {
        char[] chars;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            chars = new char[(int) file.length()];
            fr.read(chars);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return parseChars(chars);
    }

    public abstract ArrayList<CodeStatement> parseChars(char[] chars);


}
