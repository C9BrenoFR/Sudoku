import java.util.Scanner;

public class Main {

    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println("Qual tipo de jogo você quer jogar?");
        System.out.println("1 - Aleatório");
        System.out.println("2 - Criar Jogo");

        input = scanner.nextInt();
        scanner.nextLine();

        while (input != 1 && input != 2){
            input = scanner.nextInt();
            scanner.nextLine();
        }

        if(input == 1){
            System.out.println("Quantos valores padrão você quer?");
            input = scanner.nextInt();
            scanner.nextLine();
            Game game = new Game(input);
        }else if(input == 2){
            Game game = new Game();
            game.play();
        }

        game.showBoard();
        while (true){
            System.out.println("O que deseja fazer?");
            System.out.println("1 - Fazer Jogada");
            System.out.println("2 - Remover Jogada");
            System.out.println("3 - Dica");
            System.out.println("4 - Reiniciar");
            System.out.println("5 - Sair");



            input = scanner.nextInt();
            scanner.nextLine();

            while (input < 1 || input > 5){
                input = scanner.nextInt();
                scanner.nextLine();
            }


            switch (input){
                case 1 : try {
                    game.play();
                    game.showBoard();
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                case 2 : try {
                    game.remove();
                    game.showBoard();
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                case 3 : try {
                    game.tip();
                    game.showBoard();
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                case 4 : return;
                case 5 : System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        while (true)
            menu();
    }
}