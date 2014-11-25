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

    public int getNumberLine() {
        return numberLine;
    }

    //  Not yet needed... But soon :P !
    //    public int getNumberColumn() {
    //        return numberColumn;
    //    }

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


}
