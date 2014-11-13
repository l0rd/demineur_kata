package fr.zenika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Demineur {

    public static LineSubstitutionRule[] lineRules = {
            new LineSubstitutionRule("\\*\\.\\*", "*2*"),
            new LineSubstitutionRule("\\.\\*", "1*"),
            new LineSubstitutionRule("\\*\\.", "*1"),
            new LineSubstitutionRule("\\.", "0")
    } ;

    public String[] resolve(String[] input) {
        if (input == null) throw new BoardCannotBeNullException();
        checkBoardNotMalformed(input);

        String[] output = new String[input.length];

        for (int i = 0; i < input.length; i++) {
            String line = input[i];
            output[i] = treatLine(line);
        }

        return output;
    }

    private String treatLine(String line) {

        for (LineSubstitutionRule lineRule : lineRules) {
            line = lineRule.apply(line);
        }

        return line;
    }

    private void checkBoardNotMalformed(String[] input) {
        for (String line : input) {
            checkLineSize(input[0].length(), line);
            checkLineCharacters(line);
        }
    }

    private void checkLineCharacters(String line) {
        if (!line.matches("[.|*]*")) {
            throw new BoardMalformedException();
        }
    }

    private void checkLineSize(int size, String line) {
        if (line.length() != size) {
            throw new BoardMalformedException();
        }
    }
}
