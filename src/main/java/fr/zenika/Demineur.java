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

        String[] linesResultBoard = processBoardLines(board);
        String[] columnsResultBoard = processBoardColumns(board);

        Board mergedResultBoard = mergeResults(linesResultBoard, columnsResultBoard);
        return mergedResultBoard.toStringArray();
    }

    private Board mergeResults(String[] linesResultBoard, String[] columnsResultBoard) {
        int boardLines = linesResultBoard.length;
        int boardColumns = columnsResultBoard.length;
        Board mergedResults = new Board(boardLines, boardColumns);
        for (int i = 0; i < boardLines; i++) {
            for (int j = 0; j < boardColumns; j++) {
                if (linesResultBoard[i].charAt(j) == '*') {
                    mergedResults.setMineAtPosition(i, j);
                } else {
                    Integer numberOfLineNeighbors = Character.getNumericValue(linesResultBoard[i].charAt(j));
                    Integer numberOfColumnNeighbors = Character.getNumericValue(columnsResultBoard[j].charAt(i));
                    mergedResults.setNumberOfNeighboringMines(i, j, numberOfLineNeighbors + numberOfColumnNeighbors);
                }
            }
        }
        return mergedResults;
    }

    private String[] processBoardColumns(Board board) {
        int boardColumns =board.getNumberColumn() ;
        String[] columnsResultBoard = new String[boardColumns];
        for (int i = 0; i < boardColumns; i++) {
            columnsResultBoard[i]= treatSequence(board.getColumn(i));
        }
        return columnsResultBoard;
    }

    private String[] processBoardLines(Board board) {
        int boardLines = board.getNumberLine();
        String[] linesResultBoard = new String[boardLines];
        for (int i = 0; i < boardLines; i++) {
            linesResultBoard[i]= treatSequence(board.getLine(i));
        }
        return linesResultBoard;
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
