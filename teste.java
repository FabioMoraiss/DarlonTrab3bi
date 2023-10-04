import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class teste {
    public static void main(String[] args) throws IOException {
        String[][] stringgggggg = criarMatriz();
        try {
            gravarMatriz(stringgggggg, "queropao.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] stringonaaaaa;

        for (int i = 0; i < 2; i++) {

        }

        stringonaaaaa = importarMatriz("queropao.txt");

    }

    // Função para criar uma matriz 20x20<String>, preenchida com zeros
    public static String[][] criarMatriz() {
        String[][] matriz = new String[20][20];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = "" + Math.random();
            }
        }
        return matriz;
    }

    // Função para gravar os valores da matriz em um arquivo .txt
    public static void gravarMatriz(String[][] matriz, String nomeArquivo) throws Exception {
        FileWriter writer = new FileWriter(nomeArquivo);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                writer.write(matriz[i][j] + " ");
            }
            writer.write("\n");
        }
        writer.close();
    }

    // Função para importar os valores para uma nova matriz chamada ABACAXI<String>
    public static String[][] importarMatriz(String nomeArquivo) throws IOException {
        FileReader reader = new FileReader(nomeArquivo);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String linha;
        String[][] matriz = new String[20][20];
        int i = 0;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] valores = linha.split(" ");
            for (int j = 0; j < valores.length; j++) {
                matriz[i][j] = valores[j];
            }
            i++;
        }

        reader.close();
        return matriz;
    }

}