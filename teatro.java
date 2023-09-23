import java.io.BufferedReader;
import java.io.FileReader;
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

    public static void main(String[] args) {

        menuPrinciapl();

    }

    public static void menuPrinciapl() {
        boolean continuar = true;
        int tentativas = 0;

        while (continuar) {
            if (tentativas == 0) {
                Digitando("Bem vindo usuario !");
            } else {
                Digitando("bem vindo novamente usuario !");
            }

            Digitando("você deseja acessar o modo ADM ou cliente ? digite 0 ou 1");
            int admOUcliente = input.nextInt();

            switch (admOUcliente) {
                case 0:
                    menuADM();
                    break;

                case 1:
                    menuCliente();
                    break;

                default:
                    Digitando("vc digitou uma opcao invalida");
                    LinhaVazia();
            }

            if (tentativas == 0) {
                tentativas++;
            }
        }

    }

    //menu de ADM
    public static void menuADM() {

    }

    //menu cliente
    public static void menuCliente() {
        
    }

    // funcoes de texto
    public static void Digitando(String x) {
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

    public static void LinhaVazia() {
        System.out.println();
    }
}