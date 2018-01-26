
/* this class is a other way to solve sudoku, but did not used into this project
 * this project use the way tutor recommended
 * 
 * */

public class CheckRules {
    public  int matrix[][];
    public CheckRules(int matrix[][]){
        this.matrix=matrix;
    }
    public void backTrace(int i, int j) {
        if (i == 8 && j == 9) {
            //succeed,print
            System.out.println("get answer");
            printArray();
            return;
        }

        if (j == 9) {
            i++;
            j = 0;
        }

        //if i,j is empty
        if (matrix[i][j] == 0) {
            for (int k = 1; k <= 9; k++) {
                //any number of 1-9, cover to cell
                if (check(i, j, k)) {
                    matrix[i][j] = k;
                    backTrace(i, j + 1);
                    //initialize
                    matrix[i][j] = 0;
                }
            }
        } else {
            backTrace(i, j + 1);
        }
    }

    /**
     * check
     *
     * @param row    the row of valued cell
     * @param line   the line of valued cell
     * @param number the number of valued cell
     * @return
     */
    public boolean check(int row, int line, int number) {
        //check number of row repetition or not 
        for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == number || matrix[i][line] == number) {
                return false;
            }
        }
        //check number of square repetition or not 
        int tempRow = row / 3;
        int tempLine = line / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[tempRow * 3 + i][tempLine * 3 + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * print square
     */
    public void printArray() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
