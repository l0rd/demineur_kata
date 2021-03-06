package fr.zenika;

public class Board {

    private Character[][] mineField;
    private int numberLine;
    private int numberColumn;

    public Board(String[] input) {

        numberLine = input.length;
        numberColumn = input[0].length();

        Character[][] map = new Character[numberLine][numberColumn];

        for (int i = 0; i < numberLine; i++) {
            for (int j = 0; j < numberColumn; j++) {
                map[i][j] = input[i].charAt(j);
            }
        }

        this.mineField = map;
    }

    public Board(int boardLines, int boardColumns) {
        numberLine = boardLines;
        numberColumn = boardColumns;
        Character[][] map = new Character[numberLine][numberColumn];
        this.mineField = map;
    }

    public int getNumberLine() {
        return numberLine;
    }

    public int getNumberColumn() {
            return numberColumn;
    }

    public String getLine(int lineNumber) {
        StringBuffer line = new StringBuffer();
        for (int i = 0; i < numberColumn; i++) {
            Character myChar = mineField[lineNumber][i];
            line.append(myChar);
        }
        return line.toString();
    }

    public String getColumn(int columnNumber) {
        StringBuffer column = new StringBuffer();
        for (int i = 0; i < numberLine; i++) {
            Character myChar = mineField[i][columnNumber];
            column.append(myChar);
        }
        return column.toString();
    }

    public void setNumberOfNeighboringMines(int line, int column, int minesNumber) {
        mineField[line][column] = Integer.toString(minesNumber).charAt(0);
    }

    public void setMineAtPosition(int line, int column) {
        mineField[line][column] = '*';
    }

    public String[] toStringArray() {
        String[] stringArrayBoard = new String[numberLine];
        for (int i = 0; i < numberLine; i++) {
            stringArrayBoard[i] = getLine(i);
        }
        return  stringArrayBoard;
    }

}
