import java.util.Random;

public class LabWork2 {
    public static void main(String[] args) {
        int studentNumber = 4;

        int C5 = studentNumber % 5;   // C5 = 4 % 5 = 4
        int C7 = studentNumber % 7;   // C7 = 4 % 7 = 4
        int C11 = studentNumber % 11; // C11 = 4 % 11 = 4


        System.out.println("C5: " + C5);
        System.out.println("C7: " + C7);
        System.out.println("C11: " + C11);

        try {
            Class<?> elementType = getElementType(C7);
            System.out.println("Тип елементів матриці: " + elementType.getSimpleName());

            int rows = 3;
            int cols = 3;
            long[][] A = generateRandomMatrix(rows, cols, Long.class);
            long[][] B = generateRandomMatrix(rows, cols, Long.class);

            System.out.println("Матриця A:");
            printMatrix(A);
            System.out.println("Матриця B:");
            printMatrix(B);

            long[][] C = null;
            switch (C5) {
                case 4:

                    C = multiplyMatrices(A, B);
                    System.out.println("C = A × B:");
                    printMatrix(C);
                    break;
                default:
                    throw new UnsupportedOperationException("Невідома дія для C5 = " + C5);
            }

            long result = 0;
            switch (C11) {
                case 4:
                    result = sumMaxEvenMinOddRows(C);
                    System.out.println("Сума найбільших елементів в парних рядках та найменших в непарних рядках матриці C: " + result);
                    break;
                default:
                    throw new UnsupportedOperationException("Невідома дія для C11 = " + C11);
            }

        } catch (Exception e) {
            System.err.println("Сталася помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Метод для визначення типу елементів матриці на основі C7
     * @param C7 Остача від ділення номера залікової книжки на 7
     * @return Клас типу елементів матриці
     */
    private static Class<?> getElementType(int C7) throws UnsupportedOperationException {
        switch (C7) {
            case 0:
                return double.class;
            case 1:
                return byte.class;
            case 2:
                return short.class;
            case 3:
                return int.class;
            case 4:
                return long.class;
            case 5:
                return char.class;
            case 6:
                return float.class;
            default:
                throw new UnsupportedOperationException("Невідомий тип для C7 = " + C7);
        }
    }

    /**
     * Метод для генерації випадкової матриці з типом long
     * @param rows Кількість рядків
     * @param cols Кількість стовпців
     * @param type Клас типу елементів
     * @return Згенерована матриця
     */
    private static long[][] generateRandomMatrix(int rows, int cols, Class<?> type) {
        long[][] matrix = new long[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(100); // Випадкові числа від 0 до 99
            }
        }
        return matrix;
    }

    /**
     * Метод для виводу матриці на екран
     * @param matrix Матриця для виводу
     */
    private static void printMatrix(long[][] matrix) {
        for (long[] row : matrix) {
            for (long val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Метод для множення матриць A і B
     * @param A Перша матриця
     * @param B Друга матриця
     * @return Добуток матриць A та B
     * @throws IllegalArgumentException Якщо кількість стовпців A не дорівнює кількості рядків B
     */
    private static long[][] multiplyMatrices(long[][] A, long[][] B) throws IllegalArgumentException {
        int aRows = A.length;
        int aCols = A[0].length;
        int bRows = B.length;
        int bCols = B[0].length;

        if (aCols != bRows) {
            throw new IllegalArgumentException("Кількість стовпців матриці A повинна дорівнювати кількості рядків матриці B.");
        }

        long[][] result = new long[aRows][bCols];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    /**
     * Метод для обчислення суми найбільших елементів в парних рядках та найменших в непарних рядках
     * @param matrix Матриця C
     * @return Сума відповідних елементів
     */
    private static long sumMaxEvenMinOddRows(long[][] matrix) {
        long sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            if ((i + 1) % 2 == 0) { // Парні рядки (нумерація з 1)
                long max = matrix[i][0];
                for (long val : matrix[i]) {
                    if (val > max) {
                        max = val;
                    }
                }
                System.out.println("Рядок " + (i + 1) + " (парний): max = " + max);
                sum += max;
            } else { // Непарні рядки
                long min = matrix[i][0];
                for (long val : matrix[i]) {
                    if (val < min) {
                        min = val;
                    }
                }
                System.out.println("Рядок " + (i + 1) + " (непарний): min = " + min);
                sum += min;
            }
        }
        return sum;
    }
}
