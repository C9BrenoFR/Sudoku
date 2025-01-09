import com.sun.jdi.Value;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Sudoku sudoku = new Sudoku();
    private String[] protectedValues;
    private static final String PLAY_SUCCESS_MESSAGE = "Jogada Registrada com sucesso";

    public Game() {
    }
    public Game(int numbers) {
        protectedValues = new String[numbers];

        Random random = new Random();
        for(int i = 0; i < numbers; i++){
            int line = random.nextInt(1,9 );
            int column = random.nextInt(1,9 );

            while(true){
                if (sudoku.getVal(line, column) ==0 )
                    break;
                line = random.nextInt(1,9 );
                column = random.nextInt(1,9 );
            }

            int randomNumber = random.nextInt(9 ) + 1;
            while (true){
                if (sudoku.isValid(line-1, column-1, randomNumber))
                    break;

                randomNumber = random.nextInt(1,9);
            }
            System.out.println(line + " " + column + " " + randomNumber);
            protectedValues[i] = "(" + line + "," + column + ")";
            sudoku.setVal(line, column, randomNumber);
        }
    }

    public void showBoard(){
        System.out.println("Atual tabuleiro: ");
        System.out.println("   1 2 3 | 4 5 6 | 7 8 9");
        System.out.println("-------------------------");
        for (int i = 1; i <= 9; i++){
            System.out.print(i + "| ");
            for (int j = 1; j <= 9; j++){
                System.out.print(sudoku.getVal(i, j) + " ");
                if (j == 3 || j == 6){
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i == 3 || i == 6){
                System.out.println("-------------------------");
            }
        }
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite uma ou mais jogadas no formato (linha,coluna,valor)");
        String input = scanner.nextLine();

        input = input.replace("(", "").replace(")", "").replace(" ", ",");
        String[] numbers = input.split(",");


        if (numbers.length%3 != 0)
            throw new IllegalArgumentException("Formato de texto de inválido, formato correto: (linha,coluna,valor)");

        int lineI = 0, columnI = 1, valueI = 2;

        for (int i = 0; i < numbers.length / 3; i++){
            int line = Integer.parseInt(numbers[lineI]);
            int column = Integer.parseInt(numbers[columnI]);
            int value = Integer.parseInt(numbers[valueI]);

            lineI += 3;
            columnI += 3;
            valueI += 3;

            System.out.println(i + " jogada: " + "(" + line + "," + column + "," + value + ")");

            if (sudoku.getVal(line,column) != 0)
                throw new IllegalArgumentException("A jogada " + "(" + line + "," + column + "," + value + ") não foi inserida, pois ja possui um valor nesse espaço");
            try {
                sudoku.setVal(line, column, value);
                System.out.println(PLAY_SUCCESS_MESSAGE);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void remove(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite A posição de remoção (linha,coluna)");
        String input = scanner.nextLine();

        input = input.replace("(", "").replace(")", "");
        String[] numbers = input.split(",");

        try {
            sudoku.removeVal(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void tip(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite A posição da dica (linha,coluna)");
        String input = scanner.nextLine();


        input = input.replace("(", "").replace(")", "");
        String[] numbers = input.split(",");

        int line = Integer.parseInt(numbers[1]);
        int column = Integer.parseInt(numbers[2]);

        for (int i = 1; i <= 9; i++){
            if(sudoku.isValid(line,column,i)){
                sudoku.setVal(line,column, i);
                break;
            }
        }
    }
}
