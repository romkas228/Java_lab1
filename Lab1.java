import java.util.Arrays;
import java.util.Random;

public class Lab1 {

    private static final int ROWS_B = 4;
    private static final int COLS_B = 3;

    public static void main(String[] args) {
        float[][] matrixB;
        float[][] matrixC;
        float finalSumResult;
        
        try {
            System.out.println("### 1. Ініціалізація та заповнення матриці B (float)");
            matrixB = initializeMatrixB(ROWS_B, COLS_B);
            printMatrix("Матриця B", matrixB);

            System.out.println("\n### 2. Виконання Дії 1: Транспонування (C = B^T)");
            matrixC = transposeMatrix(matrixB);
            printMatrix("Результат Дії 1: Матриця C", matrixC);

            System.out.println("\n### 3. Виконання Дії 2: Обчислення суми елементів C");
            finalSumResult = calculateSpecialSum(matrixC);
            
            System.out.println("\n--- Результати ---");
            System.out.printf("Результат Дії 2 (Сума): %.2f%n", finalSumResult);

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка вхідних даних: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Виникла непередбачена помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

   
    private static float[][] initializeMatrixB(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Розміри матриці повинні бути додатними.");
        }
        float[][] matrix = new float[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextFloat() * 10.0f; 
            }
        }
        return matrix;
    }

    private static float[][] transposeMatrix(float[][] matrixB) {
        int rowsB = matrixB.length;
        if (rowsB == 0) {
             return new float[0][0];
        }
        int colsB = matrixB[0].length;
        
        float[][] matrixC = new float[colsB][rowsB];

        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                matrixC[j][i] = matrixB[i][j];
            }
        }
        return matrixC;
    }


    private static float calculateSpecialSum(float[][] matrixC) {
        if (matrixC.length == 0 || matrixC[0].length == 0) {
            System.out.println("Матриця C порожня. Сума = 0.");
            return 0.0f;
        }

        int rows = matrixC.length;
        int cols = matrixC[0].length;
        float totalSum = 0.0f;

        for (int j = 0; j < cols; j++) {
            float extremum = matrixC[0][j]; 
            
            if (j % 2 == 0) { 
                for (int i = 1; i < rows; i++) {
                    if (matrixC[i][j] > extremum) {
                        extremum = matrixC[i][j];
                    }
                }
                System.out.printf("Стовпець %d (парний): Max елемент = %.2f%n", j, extremum);
            } else { 
                for (int i = 1; i < rows; i++) {
                    if (matrixC[i][j] < extremum) {
                        extremum = matrixC[i][j];
                    }
                }
                System.out.printf("Стовпець %d (непарний): Min елемент = %.2f%n", j, extremum);
            }
            totalSum += extremum;
        }
        return totalSum;
    }
    
  
    private static void printMatrix(String title, float[][] matrix) {
        System.out.println(title + " (" + matrix.length + "x" + (matrix.length > 0 ? matrix[0].length : 0) + "):");
        for (float[] row : matrix) {
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%.2f", row[i]);
                if (i < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
}