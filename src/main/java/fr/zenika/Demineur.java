package fr.zenika;

public class Demineur {

    public static LineSubstitutionRule[] lineRules = {
            new LineSubstitutionRule("\\*\\.\\*", "*2*"),
            new LineSubstitutionRule("\\.\\*", "1*"),
            new LineSubstitutionRule("\\*\\.", "*1"),
            new LineSubstitutionRule("\\.", "0")
    };

    public String[] resolve(String[] input) {
        if (input == null) throw new BoardCannotBeNullException();
        checkBoardNotMalformed(input);

        int boardLines = input.length;
        int boardColumns = input[0].length();

        if (boardLines == 1) {
            String[] output = {treatLine(input[0])};
            return output;
        }

        if (boardLines == 2 && boardColumns == 1) {
            return treatColumn(input[0].substring(0,1) + input[1].substring(0, 1));
        }

        return null;
    }

    private String[] treatColumn(String column) {
        for (LineSubstitutionRule lineRule : lineRules) {
            column = lineRule.apply(column);
        }
        return column.split("");
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
