import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoBatalhaNaval {
    private char[][] tabuleiroNavios;
    private char[][] tabuleiroVisivel;
    private char[][] tabuleiroNaviosBot;
    private char[][] tabuleiroVisivelBot;
    private Scanner scanner;
    private int naviosRestantes;
    private int naviosRestantesBot;
    private int naviosAfundadosJogador;
    private int naviosAfundadosBot;
    private String nomeJogador;
    private int pontuacaoJogador;

    private static final String ARQUIVO_LEADERBOARD = "leaderboard.txt";

    public JogoBatalhaNaval(int nivelDificuldade) {
        // Ajuste da quantidade de navios com base na dificuldade escolhida
        int quantidadeNavios = nivelDificuldade * 2 + 1;

        tabuleiroNavios = new char[8][8]; // Tamanho padrão do tabuleiro
        tabuleiroVisivel = new char[8][8];
        tabuleiroNaviosBot = new char[8][8]; // Tamanho padrão do tabuleiro
        tabuleiroVisivelBot = new char[8][8];
        scanner = new Scanner(System.in);
        naviosRestantes = 0;
        naviosRestantesBot = 0;
        naviosAfundadosJogador = 0;
        naviosAfundadosBot = 0;
        nomeJogador = solicitarNomeJogador(); // Solicitação do nome do jogador
        pontuacaoJogador = 0;

        inicializarTabuleiros();
        colocarNavios(tabuleiroNavios, quantidadeNavios);
        inicializarTabuleirosBot();
        colocarNaviosBot(tabuleiroNaviosBot, quantidadeNavios);
    }



    private void inicializarTabuleiros() {
        inicializarTabuleiro(tabuleiroNavios);
        inicializarTabuleiro(tabuleiroVisivel);
    }
    private void inicializarTabuleirosBot() {
        inicializarTabuleiroBot(tabuleiroNaviosBot);
        inicializarTabuleiroBot(tabuleiroVisivelBot);
    }

    private void inicializarTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = '~';
            }
        }
    }
    private void inicializarTabuleiroBot(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = '~';
            }
        }
    }

    private void exibirTabuleiro(char[][] tabuleiro) {
        System.out.println("Tabuleiro:");
        System.out.print("  ");
        for (int i = 0; i < tabuleiro[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tabuleiro[i].length; j++) {
                // Mostra apenas água e tiros, sem revelar a posição dos navios
                if (tabuleiro[i][j] == '~' || tabuleiro[i][j] == 'X' || tabuleiro[i][j] == 'O') {
                    System.out.print(tabuleiro[i][j] + " ");
                }
                else {
                    System.out.print("~ "); // Esconde a posição dos navios
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void colocarNavios(char[][] tabuleiro, int quantidadeNavios) {
        for (int i = 0; i < quantidadeNavios; i++) {
            int linha, coluna;
            char direcao;

            // Gere aleatoriamente a direção do navio (V para vertical, H para horizontal)
            direcao = (Math.random() < 0.5) ? 'V' : 'H';

            do {
                linha = (int) (Math.random() * tabuleiro.length);
                coluna = (int) (Math.random() * tabuleiro[0].length);
            } while (!podeColocarNavio(tabuleiro, linha, coluna, direcao));

            // Coloque o navio no tabuleiro com base na direção
            for (int j = 0; j < 3; j++) {
                if (direcao == 'V') {
                    tabuleiro[linha + j][coluna] = 'N';
                }
                else {
                    tabuleiro[linha][coluna + j] = 'N';
                }
            }

            // Incrementa naviosRestantes (se existir)
            naviosRestantes += 3; // Cada navio agora tem 3 blocos
        }
    }
    private void colocarNaviosBot(char[][] tabuleiro, int quantidadeNavios) {
        for (int i = 0; i < quantidadeNavios; i++) {
            int linha, coluna;
            char direcao;

            // Gere aleatoriamente a direção do navio (V para vertical, H para horizontal)
            direcao = (Math.random() < 0.5) ? 'V' : 'H';

            do {
                linha = (int) (Math.random() * tabuleiro.length);
                coluna = (int) (Math.random() * tabuleiro[0].length);
            } while (!podeColocarNavio(tabuleiro, linha, coluna, direcao));

            // Coloque o navio no tabuleiro com base na direção
            for (int j = 0; j < 3; j++) {
                if (direcao == 'V') {
                    tabuleiro[linha + j][coluna] = 'N';
                }
                else {
                    tabuleiro[linha][coluna + j] = 'N';
                }
            }

            // Incrementa naviosRestantes (se existir)
            naviosRestantesBot += 3; // Cada navio agora tem 3 blocos
        }
    }

    private boolean podeColocarNavio(char[][] tabuleiro, int linha, int coluna, char direcao) {
        if (direcao == 'V') {
            // Verifica se há espaço na vertical
            if (linha + 2 >= tabuleiro.length) {
                return false;
            }

            for (int j = 0; j < 3; j++) {
                if (tabuleiro[linha + j][coluna] == 'N') {
                    return false;
                }
            }

            return true;
        }
        else {
            // Verifica se há espaço na horizontal
            if (coluna + 2 >= tabuleiro[0].length) {
                return false;
            }

            for (int j = 0; j < 3; j++) {
                if (tabuleiro[linha][coluna + j] == 'N') {
                    return false;
                }
            }

            return true;
        }
    }

    private String solicitarNomeJogador() {
        System.out.println("Informe seu nome: ");
        return scanner.nextLine();
    }

    private void jogadaJogador() {
        int linha, coluna;
        do {
            System.out.print("Informe a linha (0-" + (tabuleiroNavios.length - 1) + "): ");
            linha = scanner.nextInt();
            System.out.print("Informe a coluna (0-" + (tabuleiroNavios[0].length - 1) + "): ");
            coluna = scanner.nextInt();
        } while (!validarJogadaJogador(linha, coluna));
    }

    private void jogadaBot() {
        int linha, coluna;
        do {
            linha = (int) (Math.random() * tabuleiroNaviosBot.length);
            coluna = (int) (Math.random() * tabuleiroNaviosBot[0].length);
        } while (tabuleiroVisivelBot[linha][coluna] == 'X' || tabuleiroVisivelBot[linha][coluna] == 'O');

    }

    private boolean validarJogadaJogador(int linha, int coluna) {
        if (linha < 0 || linha >= tabuleiroNavios.length || coluna < 0 || coluna >= tabuleiroNavios[0].length) {
            System.out.println("Jogada inválida. Tente novamente.");
            return false;
        }

        if (tabuleiroVisivel[linha][coluna] == 'X' || tabuleiroVisivel[linha][coluna] == 'O') {
            System.out.println("Algum jogador já jogou nesta posição. Tente novamente.");
            return false;
        }

        if (tabuleiroNavios[linha][coluna] == 'N') {
            System.out.println("Parabéns " + nomeJogador + "! Você acertou um navio!");
            tabuleiroVisivel[linha][coluna] = 'X';
            naviosRestantes--;
            naviosAfundadosJogador++;
            pontuacaoJogador += 10; // Incrementa a pontuação por navio atingido

            if (naviosRestantes == 0) {
                return true; // Jogador ganhou
            }
        }
        else {
            System.out.println("Água! Tente novamente.");
            tabuleiroVisivel[linha][coluna] = 'O';
        }

        return true;
    }

    private boolean validarJogadaBot(int linha, int coluna) {
        if (linha < 0 || linha >= tabuleiroNaviosBot.length || coluna < 0 || coluna >= tabuleiroNaviosBot[0].length) {
            System.out.println("Jogada inválida. Tente novamente.");
            return false;
        }

        if (tabuleiroVisivelBot[linha][coluna] == 'X' || tabuleiroVisivelBot[linha][coluna] == 'O') {
            System.out.println("Algum jogador já jogou nesta posição. Tente novamente.");
            return false;
        }

        if (tabuleiroNaviosBot[linha][coluna] == 'N') {
            System.out.println("O bot acertou um navio");
            tabuleiroVisivelBot[linha][coluna] = 'X';
            naviosRestantesBot--;
            naviosAfundadosBot++;

            if (naviosRestantesBot == 0) {
                return true; // bot ganhou
            }
        }
        else {
            System.out.println("O bot errou a jogada");
            tabuleiroVisivelBot[linha][coluna] = 'O';
        }

        return true;
    }

    private void exibirResultadoFinal() {
        System.out.println("Navios afundados por " + nomeJogador + ": " + naviosAfundadosJogador);
        System.out.println("Navios afundados pelo Bot: " + naviosAfundadosBot);

        if (naviosAfundadosJogador > naviosAfundadosBot) {
            System.out.println("Parabéns, " + nomeJogador + "! Você ganhou afundando mais navios.");
        }
        else if (naviosAfundadosJogador < naviosAfundadosBot) {
            System.out.println("Você perdeu! O Bot afundou mais navios.");
        }
        else {
            System.out.println("Empate! Ambos afundaram a mesma quantidade de navios.");
        }

        System.out.println("Sua pontuação: " + pontuacaoJogador);
        salvarPontuacao();
    }

    private void salvarPontuacao() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_LEADERBOARD, true))) {
            writer.println(nomeJogador + "," + pontuacaoJogador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exibirLeaderboard() {
        List<String[]> leaderboard = carregarLeaderboard();
        if (!leaderboard.isEmpty()) {
            System.out.println("Leaderboard:");

            for (int i = 0; i < Math.min(5, leaderboard.size()); i++) {
                String[] entry = leaderboard.get(i);
                System.out.println((i + 1) + ". " + entry[0] + " - Pontuação: " + entry[1]);
            }
        }
        else {
            System.out.println("Leaderboard vazio.");
        }
    }

    private List<String[]> carregarLeaderboard() {
        List<String[]> leaderboard = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_LEADERBOARD))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",");
                leaderboard.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    public void iniciarJogo() {
        exibirTabuleiro(tabuleiroVisivel);
        exibirTabuleiro(tabuleiroVisivelBot);

        while (naviosRestantes > 0 && naviosRestantesBot > 0) {
            jogadaJogador();
            exibirTabuleiro(tabuleiroVisivel);
            jogadaBot();
            exibirTabuleiro(tabuleiroVisivelBot);

        }

        exibirResultadoFinal();
        exibirLeaderboard();


        System.out.println("Obrigado por jogar!");
    }


    void reiniciarJogo(int nivelDificuldade) {
        // Reinicializa os dados para uma nova partida
        tabuleiroNavios = new char[8][8];
        tabuleiroVisivel = new char[8][8];
        tabuleiroNaviosBot = new char[8][8];
        tabuleiroVisivelBot = new char[8][8];
        naviosRestantes = 0;
        naviosRestantesBot = 0;
        naviosAfundadosJogador = 0;
        naviosAfundadosBot = 0;
        pontuacaoJogador = 0;
        int quantidadeNavios = nivelDificuldade * 2 + 1;
        inicializarTabuleiros();
        colocarNavios(tabuleiroNavios, quantidadeNavios); // Defina a quantidade de navios para a nova partida
        inicializarTabuleirosBot();
        colocarNavios(tabuleiroNaviosBot, quantidadeNavios); // Defina a quantidade de navios para a nova partida
        iniciarJogo(); // Inicia a nova partida
    }

}
