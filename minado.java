import java.util.Scanner;

public class minado {

    static String[][] campo;

    static Scanner input = new Scanner(System.in);

    static int bombasFacil = 3;
    static int bombasMedio = 20;
    static int bombasDificil = 30;

    static String espacoSemBomba = "_";
    static String espacoComBomba = "X";

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

        exibirTabuleiro();

    }

    public static void exibirTabuleiro() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                System.out.print(campo[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rotinaFacil() {
        campo = new String[5][5];
        preencherCampo(1);
    }

    public static void rotinaMedio() {
        campo = new String[10][10];
        preencherCampo(2);
    }

    public static void rotinaDificil() {
        campo = new String[15][15];
        preencherCampo(3);
    }

    public static void preencherCampo(int dificuldade) {

        // preence com espaços vazios
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                campo[i][j] = espacoSemBomba;
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
            for (int i = 0; i < campo.length; i++) {
                for (int j = 0; j < campo[0].length; j++) {

                    if (!isNumeroMaximoBombas(numeroBombas)) {
                        if (ramdonBomba()) {
                            campo[i][j] = espacoComBomba;
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
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                if (campo[i][j].equals(espacoComBomba)) {
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

        if (aleatorio <= 0.1f) {
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
