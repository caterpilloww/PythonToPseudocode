package caterpillow;

import caterpillow.abstractprogram.random.CodeStatement;
import caterpillow.abstractprogram.structures.Structure;
import caterpillow.converter.parser.CodeParser;
import caterpillow.converter.transformer.Transformer;
import caterpillow.pseudocode.transformer.PseudoTransformer;
import caterpillow.pseudocode.transformer.PseudoTransformerConfig;
import caterpillow.python.parse.PyParser;
import caterpillow.python.parse.PythonFileParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Main {

    /*
     * this program turns code into my own shitty intermediary abstraction for general code, and then turns that back into other code
     * */

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the path to the input file as a command-line argument.");
            return;
        }

        String filePath = args[0];
        System.out.println("Input file path: " + filePath);
        try {
            cook(filePath);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void cook(String filePath) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        File file = new File(filePath);
        ArrayList<CodeStatement> statements = new PythonFileParser().getCodeStatements(filePath);
        System.out.println("\n\n\n\n");
        CodeParser parser = new PyParser(file.getName(), statements);
        Transformer transformer = new PseudoTransformer(new PseudoTransformerConfig());
        Structure parseResult = parser.parse();
        System.out.println("transforming...");
        ArrayList<String> result = transformer.transform(parseResult);
        for (String str : result) {
            System.out.println(str);
        }
        writeToFile(result, new File(file.getParentFile(), file.getName().replaceFirst("[.][^.]+$", "") + "-pseudo.txt"));
    }

    public static void writeToFile(ArrayList<String> strings, File destination) throws IOException {
        Files.write(destination.toPath(), strings);
    }
}
