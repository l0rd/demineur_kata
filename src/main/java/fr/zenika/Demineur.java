package fr.zenika;

public class Demineur {

    public static SubstitutionRule[] rules = {
            new SubstitutionRule("\\*\\.\\*", "*2*"),
            new SubstitutionRule("\\.\\*", "1*"),
            new SubstitutionRule("\\*\\.", "*1"),
            new SubstitutionRule("\\.", "0"),
    };

    public String[] resolve(final String[] input) {

        if (input == null) throw new BoardCannotBeNullException();
        checkBoardNotMalformed(input);

        Board board = new Board(input);
        int boardLines = board.getNumberLine();
        // int boardColumns = board.getNumberColumn();

        if (boardLines == 1) {
            String line = treatLine(board.getLine(0));
            return new String[]{line};
        } else {
            String column = treatColumn(board.getColumn(0));
            return column.split("");
        }
    }

    private String treatColumn(String column) {
        for (SubstitutionRule rule : rules) {
            column = rule.apply(column);
        }
        return column;
    }

    private String treatLine(String line) {
        for (SubstitutionRule rule : rules) {
            line = rule.apply(line);
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
