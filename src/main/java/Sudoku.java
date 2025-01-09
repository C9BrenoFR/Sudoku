public class Sudoku {
    private int[][] board = new int[9][9];

    public Sudoku() {}

    public boolean isValid(int line, int column, int value){
        if(value < 1 || value > 9) return false;

        for (int i = 0; i < 9; i++){
            if (this.board[line][i] == value) return false;
        }

        for (int i = 0; i < 9; i++){
            if (this.board[i][column] == value) return false;
        }

        while (line != 0 && line != 3 && line != 6){
            line--;
        }

        while (column != 0 && column != 3 && column != 6){
            column--;
        }

        for (int i = line; i < line+2; i++){
            for (int j = column; j < column+2; j++){
                if (this.board[i][j] == value) return false;
            }
        }

        return true;
    }

    public int getVal(int line, int column) {
        if (line < 1 || line > 9)
            throw new IllegalArgumentException("Linha Inválida - tente valor de 1 a 9");
        else if (column < 1 || column > 9)
            throw new IllegalArgumentException("Coluna Inválida - tente valor de 1 a 9");
        return board[--line][--column];
    }

    public void setVal(int line, int column, int value) {
        if (line < 1 || line > 9)
            throw new IllegalArgumentException("Linha Inválida - tente valor de 1 a 9");
        else if (column < 1 || column > 9)
            throw new IllegalArgumentException("Coluna Inválida - tente valor de 1 a 9");
        else if (!this.isValid(line-1, column-1, value))
            throw new IllegalArgumentException("Valor Inválido!");
        this.board[--line][--column] = value;
    }

    public void removeVal(int line, int column){
        if (line < 1 || line > 9)
            throw new IllegalArgumentException("Linha Inválida - tente valor de 1 a 9");
        else if (column < 1 || column > 9)
            throw new IllegalArgumentException("Coluna Inválida - tente valor de 1 a 9");
        this.board[line][column] = 0;
    }

    public boolean isFull(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (this.board[i][j] == 0) return false;
            }
        }
        return true;
    }
}
