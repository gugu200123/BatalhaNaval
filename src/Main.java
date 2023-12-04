import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String resposta = "sim";
        while (resposta != "não") {
            System.out.println("Escolha o nível de dificuldade (1-3): ");
            int nivelDificuldade = scanner.nextInt();

            // Criando uma instância do jogo com base no nível de dificuldade escolhido
            JogoBatalhaNaval jogo = new JogoBatalhaNaval(nivelDificuldade);

            // Iniciando o jogo
            jogo.iniciarJogo();

            System.out.println("Deseja jogar novamente? (Digite 'sim' para jogar novamente): ");
            resposta = scanner.next().toLowerCase();

            if (resposta.equals("não")) {
                System.out.println("Obrigado por jogar! Encerrando o programa.");
                break;
            }else{
                jogo.reiniciarJogo(nivelDificuldade);
            }
        }


    }
}
