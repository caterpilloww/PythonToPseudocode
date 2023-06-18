package caterpillow.python.parse;

import caterpillow.FileParser;
import caterpillow.abstractprogram.random.CodeStatement;

import java.util.ArrayList;

public class PythonFileParser extends FileParser {
    @Override
    public ArrayList<CodeStatement> parseChars(char[] chars) {
        ArrayList<CodeStatement> statements = new ArrayList<>();
        int whitespaceLength = 0;
        int whitespaceCounter = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c == '\n') {
                if (sb.length() != 0) {
                    // this bit assumes that the first instance of whitespace will be 1 indent
                    if (whitespaceCounter > 0 && whitespaceLength == 0)
                        whitespaceLength = whitespaceCounter;

                    statements.add(new CodeStatement(whitespaceLength == 0 ? 0 : whitespaceCounter / whitespaceLength, sb.toString().trim()));
                }
                sb.setLength(0);
                whitespaceCounter = 0;
            } else if (sb.length() == 0 && c == ' ') {
                whitespaceCounter++;
                continue;
            } else {
                sb.append(c);
            }
        }
        return statements;
    }
}
