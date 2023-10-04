public class teste2 {
    public static void main(String[] args) {
        int[][] matriz = new int[15][15];

        // Preencha a matriz com valores (isso é apenas um exemplo, você pode preencher
        // com seus próprios valores)
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                matriz[i][j] = i * 15 + j + 1;
            }
        }

        // Imprima a matriz com índices de linha e coluna
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.printf("[%d][%d] = %d\t", i, j, matriz[i][j]);
            }
            System.out.println(); // Avança para a próxima linha após imprimir uma linha completa
        }
    }
}
