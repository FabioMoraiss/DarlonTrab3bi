import java.util.Scanner;

public class minado {

    static String[][] campoBack;
    static String[][] campoFront;

    static Scanner input = new Scanner(System.in);

    static int bombasFacil = 3;
    static int bombasMedio = 20;
    static int bombasDificil = 30;

    static String espacoUNKNOW = "?";
    static String espacoSemBomba = "_";
    static String espacoComBomba = "X";
    static String espacoComBandeira = "B";

    public static void main(String[] args) {
        MenuPrincipal();
    }

    public static void MenuPrincipal() {
        Digitar("bem vindo jogador");
        Digitar("selecione a dificuldade que gostaria de jogar");
        Digitar("1 para facil");
        Digitar("2 para medio");
        Digitar("3 para dificil");
        int nivelDeDificuldade = input.nextInt();

        switch (nivelDeDificuldade) {
            case 1:
                rotinaFacil();
                break;

            case 2:
                rotinaMedio();
                break;

            case 3:
                rotinaDificil();
                break;

            default:
                Digitar("opcao indisponivel");
        }

        rotina_criar_Campo_Front(campoBack);


        PulaLinha();
        exibirTabuleiroVedadeiro();
        PulaLinha();


        if(jogar()){
            Digitar("vc ganahou o jogo ^^");
        } else{
            Digitar("VOCÊ PERDEU O JOGO !");
        }

    }

    public static boolean jogar() {
        exibirTabuleiro();

        PulaLinha();
        Digitar("qual será o seu prox passo ?\n Digite a linha e a coluna \n digite 0 para pisar e 1 para bandeirar ");
        int linha = input.nextInt();
        int coluna = input.nextInt();
        int acao = input.nextInt();


        if(verificarMovimentoValido(linha, coluna, acao)) {
            realizarMovimento(linha, coluna, acao);
        } else {
            Digitar("movimento invalido");
        }

        if(verificarSePerdeu()) {
            return false;
        }

    


        return false;
    }

    public static void rotina_criar_Campo_Front(String[][] campoBack) {
        campoFront = new String[campoBack.length -2][campoBack[0].length -2];

        preencherCammpFront(campoFront);

    }

    public static void preencherCammpFront(String[][] campoFront) {
        for (int i = 0; i < campoFront.length; i++) {
            for (int j = 0; j < campoFront[0].length; j++) {
                campoFront[i][j] = espacoUNKNOW;
            }
        }
    }

    public static boolean verificarMovimentoValido(int l, int c, int act) {
        if(l <=campoFront.length && c <= campoFront[0].length) {
           if(act == 0 || act == 1) {
            return true;
           } 
        }

        return false;
    }

    public static void realizarMovimento(int l, int c, int act) {
        if(act == 1) {
            campoFront[l][c] = espacoComBandeira;
        }

        if(act ==0) {
            verificarLocalExatoBomba(l, c);
        }
    }

    public static boolean verificarSePerdeu() {
        for (int i = 0; i < campoFront.length; i++) {
            for (int j = 0; j < campoFront[0].length; j++) {
                if(campoFront[i][j].equals(espacoComBomba)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void verificarLocalExatoBomba(int l, int c) {
        if(campoBack[l+1][c+1].equals(espacoComBomba)) {
            campoFront[l][c] = espacoComBomba;
        } else {
            int bombinhas = contadorBombasProximas(l,c);
            if(bombinhas == 0) {
                campoFront[l][c] = espacoSemBomba;
            } else{
                campoFront[l][c] =  bombinhas + "";
            }
        }
    }

    public static int contadorBombasProximas(int l, int c) {
        int contador = 0;
        
        //na direita    add na coluna
        if(campoBack[l+1][c+1 + 1].equals(espacoComBomba)) {
            contador++;
        }

        //na esquerda   sub na coluna
        if(campoBack[l+1][c+1 -1].equals(espacoComBomba)) {
            contador++;
        }

        //em cima   sub da linha
        if(campoBack[l+1 -1][c+1].equals(espacoComBomba)) {
            contador++;
        }


        //em baixo  add na linha
        if(campoBack[l+1 + 1][c+1].equals(espacoComBomba)) {
            contador++;
        }

        //canto superior direito    sub da linha | add na coluna
        if(campoBack[l+1 - 1][c+1 +1].equals(espacoComBomba)) {
            contador++;
        }

        //canto inferior dirieto    add na linha | add na coluna
        if(campoBack[l+1 + 1][c+1 +1].equals(espacoComBomba)) {
            contador++;
        }

        //canto superior esquerdo   sub na linha | sub na coluna
         if(campoBack[l+1 -1][c+1 -1].equals(espacoComBomba)) {
            contador++;
        }

        //canto inferior esquerdo   add na linha | sub na coluna
         if(campoBack[l+1 + 1][c+1 -1].equals(espacoComBomba)) {
            contador++;
        }

        return contador;

    }


    public static void exibirTabuleiro() {
        for (int i = 0; i < campoFront.length; i++) {
            for (int j = 0; j < campoFront[0].length; j++) {
                System.out.print(campoFront[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void exibirTabuleiroVedadeiro() {
         for (int i = 0; i < campoBack.length; i++) {
            for (int j = 0; j < campoBack[0].length; j++) {
                System.out.print(campoBack[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rotinaFacil() {
        campoBack = new String[5 + 2][5 + 2];
        preencherCampo(1);
    }

    public static void rotinaMedio() {
        campoBack = new String[10 + 2][10 + 2];
        preencherCampo(2);
    }

    public static void rotinaDificil() {
        campoBack = new String[15 + 2][15 + 2];
        preencherCampo(3);
    }

    public static void preencherCampo(int dificuldade) {

        // preence com espaços vazios
        for (int i = 0; i < campoBack.length; i++) {
            for (int j = 0; j < campoBack[i].length; j++) {
                campoBack[i][j] = espacoSemBomba;
            }
        }

        dicidirLugarBombas(dificuldade);
    }

    public static void dicidirLugarBombas(int dificuldade) {
        int numeroBombas = -1;
        switch (dificuldade) {
            case 1:
                numeroBombas = bombasFacil;
                break;

            case 2:
                numeroBombas = bombasMedio;
                break;

            case 3:
                numeroBombas = bombasDificil;
                break;
        }
        while (!isNumeroMaximoBombas(numeroBombas)) {

            for (int i = 1; i < campoBack.length -1; i++) {
                for (int j = 1; j < campoBack[0].length -1 ; j++) {

                    if (!isNumeroMaximoBombas(numeroBombas)) {
                        if (ramdonBomba()) {
                            campoBack[i][j] = espacoComBomba;
                        }
                    } else {
                        i = 999;
                        j = 999;
                    }
                }
            }
        }

    }

    public static boolean isNumeroMaximoBombas(int numeroDeBombas) {
        int contador = 0;
        for (int i = 0; i < campoBack.length; i++) {
            for (int j = 0; j < campoBack[i].length; j++) {
                if (campoBack[i][j].equals(espacoComBomba)) {
                    contador++;
                }
            }
        }

        if (contador == numeroDeBombas) {
            return true;
        }

        return false;

    }

    public static boolean ramdonBomba() {
        double aleatorio = Math.random();

        if (aleatorio <= 0.0001f) {
            return true;
        }

        return false;
    }

    // funcoes de texto
    public static void Digitar(String x) {
        for (int i = 0; i < x.length(); i++) {
            System.out.print(x.charAt(i));
            Esperar();
        }
        System.out.println();
    }

    public static void Esperar() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void PulaLinha() {
        System.out.println();
    }

}
