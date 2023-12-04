import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String resposta = "sim";
        System.out.println("Escolha o nível de dificuldade (1-5 Quanto maior o numero, mais facil será): "); //********VALIDAR ENTRADA**************
        int nivelDificuldade = scanner.nextInt();

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
