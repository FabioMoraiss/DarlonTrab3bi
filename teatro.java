import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class teatro {
    // CORES
    public static final String rst = "\u001B[0m"; // resetar cores
    public static final String gre = "\u001B[32m"; // verde para as respostas
    public static final String yll = "\u001B[33m"; // amarelo para perguntas
    public static final String red = "\u001B[31m"; // vermelho para valores invalidos
    public static final String cyn = "\u001B[36m"; // ciano para iniciando problema
    public static final String neg = "\u001B[1m"; // negrito
    // scanner
    static Scanner input = new Scanner(System.in);

    // variaveis globais
    static int senhaAdministrador = 123;
    static float precoIngresso = -1;
    static float precoIngressoEstudante = -1;
    static float precoPadrao = 50;
    static String[][] teatro = new String[15][15];
    static String arquivoTeatro = "teatro.txt";
    static String arquivoPreco = "preco.txt";
    static String lugarLivre = "_";
    static String lugarOcupado = "X";
    static String lugarEstudante = "E";
    static String lugarReservado = "R";

    public static void main(String[] args) {
        try {
            rotina_lugares();
            rotina_preco();
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuPrinciapl();

    }

    public static void menuPrinciapl() {
        boolean continuar = true;
        int tentativas = 0;

        while (continuar) {
            if (tentativas == 1) {

                Digitar("bem vindo de volta menu principal usuario !");
            } else {
                Digitar("Bem vindo  ao menu principal usuario !");
            }

            Digitar("você deseja acessar o modo ADM ou cliente ? digite 1 ou 2");
            int admOUcliente = input.nextInt();

            switch (admOUcliente) {
                case 1:
                    menuADM();
                    continuar = false;
                    break;

                case 2:
                    menuCliente();
                    continuar = false;
                    break;

                default:
                    Digitar("vc digitou uma opcao invalida");
                    PulaLinha();
            }

            if (tentativas == 0) {
                tentativas++;
            }
        }

    }

    // rotinas de execução
    public static void rotina_lugares() throws Exception {

        if (verificarArquivoExistente(arquivoTeatro)) {
            teatro = lerTeatro(arquivoTeatro, teatro);
        } else {
            teatro = preencherNovoTeatro(teatro);
            gravarTeatro(teatro, arquivoTeatro);
        }
    }

    public static void rotina_preco() throws Exception {

        if (verificarArquivoExistente(arquivoPreco)) {
            precoIngresso = getPreco(arquivoPreco);
            precoIngressoEstudante = precoIngresso / 2f;
        } else {
            setPreco(precoPadrao);
        }
    }

    public static String[][] preencherNovoTeatro(String[][] x) {

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = lugarLivre;
            }
        }

        return x;
    }

    public static void gravarTeatro(String[][] x, String y) throws Exception {
        FileWriter w = new FileWriter(y);
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                w.write(x[i][j] + " ");
            }
            w.write("\n");
        }
        w.close();
    }

    public static String[][] lerTeatro(String nomeArquivo, String[][] x) throws Exception {

        FileReader rd = new FileReader(nomeArquivo);
        BufferedReader bf = new BufferedReader(rd);

        String linha;
        int i = 0;
        while ((linha = bf.readLine()) != null) {
            String[] valores = linha.split(" ");
            for (int j = 0; j < valores.length; j++) {
                x[i][j] = valores[j];
            }
            i++;
        }

        rd.close();
        bf.close();
        return x;
    }

    public static boolean verificarArquivoExistente(String nomeArquivo) {
        File a = new File(nomeArquivo);
        return a.exists();
    }

    public static float getPreco(String nomeArquivo) throws FileNotFoundException {
        File ab = new File(arquivoPreco);
        Scanner importador = new Scanner(ab);
        float numero = Float.parseFloat(importador.nextLine());
        importador.close();
        return numero;

    }

    public static void setPreco(float preco) throws Exception {
        precoIngresso = preco;
        precoIngressoEstudante = precoIngresso / 2f;
        FileWriter w = new FileWriter(arquivoPreco);
        w.write(preco + "");
        w.close();

    }

    // CRUD teatro
    public static int contadorDeAssentos(String estado) {
        int contador = 0;
        for (int i = 0; i < teatro.length; i++) {
            for (int j = 0; j < teatro[i].length; j++) {
                if (teatro[i][j].equals(estado)) {
                    contador++;
                }
            }
        }

        return contador;
    }

    public static float faturamentoEstudantes() {
        float contador = 0;
        for (int i = 0; i < teatro.length; i++) {
            for (int j = 0; j < teatro[i].length; j++) {
                if (teatro[i][j].equals(lugarEstudante)) {
                    contador++;
                }
            }
        }

        contador = contador * precoIngressoEstudante;

        return contador;
    }

    public static float faturamentoTotal() {
        float contadorE = 0;
        float contadorI = 0;
        float total = 0;
        for (int i = 0; i < teatro.length; i++) {
            for (int j = 0; j < teatro[i].length; j++) {
                if (teatro[i][j].equals(lugarEstudante)) {
                    contadorE++;
                }

                if (teatro[i][j].equals(lugarOcupado)) {
                    contadorI++;
                }
            }
        }

        total = (contadorE * precoIngressoEstudante) + (contadorI * precoIngresso);
        return total;
    }

    public static void exibirEstatisticasAssentos() {

        Digitar("atualmente temos: ");
        Digitar(contadorDeAssentos(lugarLivre) + " lugares livres");
        Digitar(contadorDeAssentos(lugarOcupado) + " lugares ocupados");
        Digitar(contadorDeAssentos(lugarReservado) + " lugares reservados");
        Digitar(contadorDeAssentos(lugarEstudante) + " lugares de estudante");

    }

    public static void exibirNEstudantes() {
        Digitar("atualmente temos " + contadorDeAssentos(lugarEstudante) + "lugares de estudantes no teatro");
    }

    public static void exibirFaturamentoEstudantes() {
        Digitar("o faturamento dos ingressos de estudantes é R$" + faturamentoEstudantes());
    }

    public static void exibirFaturamentoTotal() {
        Digitar("o faturamento dos ingressos total é de R$" + faturamentoTotal());
    }

    public static void exibirMapaTeatro() {
        PulaLinha();
        Digitar("################################");
        Digitar("\t   PALCO");
        Digitar("################################");
        PulaLinha();

        for (int i = 0; i < teatro.length; i++) {
            for (int j = 0; j < teatro[i].length; j++) {
                Digitar(teatro[i][j] + " ");
            }
        }
    }

    // menu de ADM
    public static void menuADM() {
        boolean continuar = true;
        while (continuar) {
            if (senhaADM()) {
                Digitar("Bem vindo Administrador do sistema");
                Digitar("qual ação deseja realziar ?Digite o numero da opção");
                Digitar("1 para vizualizar quantidades totais de lugares livres, reservados e confirmados");
                Digitar("2 numero total de ingressos de estudantes");
                Digitar("3 valor total arrecadado com os ingressos.");
                Digitar("4 visualizar mapa de assentos");
                Digitar("0 para sair do mono ADMINISTRADOR");
                int opcao = input.nextInt();

                switch (opcao) {
                    case 1:
                        exibirEstatisticasAssentos();
                        break;

                    case 2:
                        exibirFaturamentoEstudantes();
                        break;

                    case 3:
                        exibirFaturamentoTotal();
                        break;

                    case 4:
                        //
                        break;

                    case 0:
                        Digitar("saindo do menu adm");
                        PulaLinha();
                        continuar = false;

                    default:
                        Digitar("opção invalida");

                }

            }
        }

    }

    public static boolean senhaADM() {
        int tentativas = 0;
        boolean continuar = true;
        while (continuar) {

            if (tentativas == 3) {
                Digitar("numero de tentativas maximas excedias ! acesso completamente NEGADO !");
                return false;
            }
            if (tentativas > 0) {
                Digitar("vamos tentar de novo !");
            }

            Digitar("digite a senha de administrador OU digite 0 para sair");
            int senhaDigitada = input.nextInt();

            if (senhaDigitada == senhaAdministrador) {
                Digitar("Acesso permitido");
                return true;
            } else if (senhaDigitada == 0) {
                Digitar("voltando para o menu Principal");
                return false;
            } else {
                Digitar("incorreta");
                tentativas++;
            }

        }

        return false; // gambiarra pra fazer a funçao funcionar

    }

    // menu cliente
    public static void menuCliente() {

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