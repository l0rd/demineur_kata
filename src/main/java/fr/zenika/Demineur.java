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
        int boardColumns = board.getNumberColumn();

        String[] linesResultBoard = new String[boardLines];
        for (int i = 0; i < boardLines; i++) {
            linesResultBoard[i]= treatSequence(board.getLine(i));
        }

        String[] columnsResultBoard = new String[boardColumns];
        for (int i = 0; i < boardColumns; i++) {
            columnsResultBoard[i]= treatSequence(board.getColumn(i));
        }

        String[][] mergedResultBoard = new String[boardLines][boardColumns];
        for (int i = 0; i < boardLines; i++) {
            for (int j = 0; j < boardColumns; j++) {
                if (linesResultBoard[i].charAt(j) == '*') {
                    mergedResultBoard[i][j] = "*";
                } else {
                    Integer sumOfResults = Character.getNumericValue(linesResultBoard[i].charAt(j))+
                            Character.getNumericValue(columnsResultBoard[j].charAt(i));
                    mergedResultBoard[i][j] = sumOfResults.toString();
                }
            }
        }

        String[] outputResultBoard = new String[boardLines];
        for (int i = 0; i < boardLines; i++) {
            StringBuilder builder = new StringBuilder();
            for(String s : mergedResultBoard[i]) {
                builder.append(s);
            }
            outputResultBoard[i] = builder.toString();
        }

        return outputResultBoard;
    }

    private String treatSequence(String sequence) {
        for (SubstitutionRule rule : rules) {
            sequence = rule.apply(sequence);
        }
        return sequence;
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
