package Tasks;

public class Task1 {
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int GRAY = 2;



    public static int[][] tileBoard(int[][] board,int rowxg , int colxg) {
        int n = board.length;
        int[][] tiling = board;

        // base when input n =1
        if (n == 2) {
            if(rowxg ==0 && colxg==0) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i==0 && j==0) {
                            continue;
                        }
                        tiling[i][j]=1;
                    }
                }
            }

            if(rowxg ==1 && colxg==0) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i==1 && j==0) {
                            continue;
                        }
                        tiling[i][j]=0;
                    }
                }
            }
            if(rowxg ==0 && colxg==1) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i==0 && j==1) {
                            continue;
                        }
                        tiling[i][j]=0;
                    }
                }
            }
            if(rowxg ==1 && colxg==1) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i==1 && j==1) {
                            continue;
                        }
                        tiling[i][j]=1;
                    }
                }
            }

        }

        else {
            int[][] quad1 = new int[n/2][n/2];
            int[][] quad2 = new int[n/2][n/2];
            int[][] quad3 = new int[n/2][n/2];
            int[][] quad4 = new int[n/2][n/2];

            boolean f1 = false;
            boolean f2 = false;
            boolean f3 = false;
            boolean f4 = false;

            int[][] tiling1= new int[n/2][n/2];
            int[][] tiling2= new int[n/2][n/2];
            int[][] tiling3= new int[n/2][n/2];
            int[][] tiling4= new int[n/2][n/2];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < n/2 && j < n/2) {
                        quad1[i][j] = board[i][j];
                    } else if (i < n/2 && j >= n/2) {
                        quad2[i][j-n/2] = board[i][j];
                    } else if (i >= n/2 && j < n/2) {
                        quad3[i-n/2][j] = board[i][j];
                    } else {
                        quad4[i-n/2][j-n/2] = board[i][j];
                    }
                }
            }

            int t = quad2.length;

            for(int i =0;i<t ; i++) {
                for(int j=0;j<t;j++) {
                    if(quad1[i][j]==9 ||quad1[i][j]==2) {
                        f1=true;
                        break;
                    }
                }
            }
            for(int i =0;i<t ; i++) {
                for(int j=0;j<t;j++) {
                    if(quad2[i][j]==9 ||quad2[i][j]==2) {
                        f2=true;
                        break;
                    }
                }
            }
            for(int i =0;i<t ; i++) {
                for(int j=0;j<t;j++) {
                    if(quad3[i][j]==9 ||quad3[i][j]==2) {
                        f3=true;
                        break;
                    }
                }
            }
            for(int i =0;i<t ; i++) {
                for(int j=0;j<t;j++) {
                    if(quad4[i][j]==9 ||quad4[i][j]==2) {
                        f4=true;
                        break;
                    }
                }
            }
            if(f1) {
                quad2[t-1][0]=2;
                quad3[0][t-1]=2;
                quad4[0][0]=2;
                if(rowxg > t/2){
                    rowxg -= t;
                }
                if(colxg > t/2){
                    colxg -= t;
                }

                tiling1 = tileBoard(quad1,rowxg,colxg);
                tiling2 = tileBoard(quad2,t-1,0);
                tiling3 = tileBoard(quad3,0,t-1);
                tiling4 = tileBoard(quad4,0,0);
            }

            if(f2) {
                quad1[t-1][t-1]=2;
                quad3[0][t-1]=2;
                quad4[0][0]=2;
                tiling1 = tileBoard(quad1,t-1,t-1);
                if(rowxg > t/2){
                    rowxg -= t;
                }
                if(colxg > t/2){
                    colxg -= t;
                }
                tiling2 = tileBoard(quad2,rowxg,colxg);
                tiling3 = tileBoard(quad3,0,t-1);
                tiling4 = tileBoard(quad4,0,0);
            }

            if(f3) {
                quad1[t-1][t-1]=2;
                quad2[t-1][0]=2;
                quad4[0][0]=2;
                tiling1 = tileBoard(quad1,t-1,t-1);
                tiling2 = tileBoard(quad2,t-1,0);
                if(rowxg > t/2){
                    rowxg -= t;
                }
                if(colxg > t/2){
                    colxg -= t;
                }
                tiling3 = tileBoard(quad3,rowxg,colxg);
                tiling4 = tileBoard(quad4,0,0);
            }


            if(f4) {
                quad1[t-1][t-1]=2;
                quad2[t-1][0]=2;
                quad3[0][t-1]=2;
                tiling1 = tileBoard(quad1,t-1,t-1);
                tiling2 = tileBoard(quad2,t-1,0);
                tiling3 = tileBoard(quad3,0,t-1);
                if(rowxg > t/2){
                    rowxg -= t;
                }
                if(colxg > t/2){
                    colxg -= t;
                }
                tiling4 = tileBoard(quad4,rowxg,colxg);
            }

            // Merge the solutions of the quadrants
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < n/2 && j < n/2) {
                        tiling[i][j] = tiling1[i][j];
                    } else if (i < n/2 && j >= n/2) {
                        tiling[i][j] = tiling2[i][j-n/2];
                    } else if (i >= n/2 && j < n/2) {
                        tiling[i][j] = tiling3[i-n/2][j];
                    } else {
                        tiling[i][j] = tiling4[i-n/2][j-n/2];
                    }
                }
            }
        }
        return tiling;
    }
    public static void main(String[] args) {
        int n =4;
        int[][] board = new int[(int) Math.pow(2, n)][(int) Math.pow(2, n)];
        board[1][0] = 9;
        int[][] tiling = tileBoard(board,1,0);

        for (int[] row : board) {

            for (int cell: row) {

                if (cell == 9) {

                    System.out.print("X ");

                } else if (cell == 1) {

                    System.out.print("B ");
                } else if (cell == 0) {
                    System.out.print("W ");
                } else if (cell == 2) {
                    System.out.print("G ");
                }
            }
            System.out.println();
        }
    }

}
