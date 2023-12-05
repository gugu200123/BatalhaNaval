import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String resposta = "sim";
        int nivelDificuldade;
        System.out.println("********************************");
        System.out.println("*SEJA BEM VINDO A BATALHA NAVAL*");
        System.out.println("********************************");
        do {
            System.out.println("Escolha o nível de dificuldade (1-3 Quanto maior o numero, mais facil será): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número válido entre 1 e 3.");
                scanner.next(); // Limpa o buffer do scanner
            }
            nivelDificuldade = scanner.nextInt();

            if (nivelDificuldade < 1 || nivelDificuldade > 3) {
                System.out.println("Por favor, digite um número entre 1 e 3.");
            }
        } while (nivelDificuldade < 1 || nivelDificuldade > 5);
        // Criando uma instância do jogo com base no nível de dificuldade escolhido
        JogoBatalhaNaval jogo = new JogoBatalhaNaval(nivelDificuldade);

        // Iniciando o jogo
        jogo.iniciarJogo();

        System.out.println("Deseja jogar novamente? (Digite 'sim' para jogar novamente ou 'nao' para sair do jogo.): ");

        while (!resposta.equals("não")) {
            resposta = scanner.next().toLowerCase();
            if (resposta.equals("nao")) {
                System.out.println("Obrigado por jogar! Encerrando o programa.");
                break;
            }
            else if (resposta.equals("sim")) {
                jogo.reiniciarJogo(nivelDificuldade);
            }
            else {
                System.out.println("Resposta Invalida, digite novamente");
            }
        }

    }
}
