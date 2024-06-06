package OpekaLenZooAplication.OpekaLenZooAplication.SortByComp;

public class CheckSameWord {
    public int getCountMaxSameSymbols(String word1, String word2){
        int result = 0;
        char[] array1 = word1.toLowerCase().toCharArray();
        char[] array2 = word2.toLowerCase().toCharArray();
        int[][] cell = new int[array1.length][array2.length];
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] == array2[j]){
                    if (i > 0 && j > 0){
                        cell[i][j] = cell[i - 1][j - 1] + 1;
                    } else {
                        cell[i][j] = 1;
                    }
                }
                if (cell[i][j] > result){
                    result = cell[i][j];
                }
            }
        }
        return result;
    }
}
