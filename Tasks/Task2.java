package Tasks;

import java.util.concurrent.ThreadLocalRandom;

class KnightTour
{
    public static final int N = 8;

    public static final int xcoordinate[] = {1, 1, 2, 2, -1, -1, -2, -2};

    public static final int ycoordinate[] = {2, -2, 1, -1, 2, -2, 1, -1};



    // the 8x8 chessboard
    boolean limits(int x, int y)
    {
        return ((x >= 0 && y >= 0) &&
                (x < N && y < N));
    }



    /* Checks whether a square is valid and empty or not */
    boolean isempty(int a[], int x, int y)
    {
        return (limits(x, y)) && (a[y * N + x] < 0);
    }




    /* Returns the number of empty squares adjacent to (x, y) */
    int getDegree(int a[], int x, int y)
    {
        int count = 0;
        for (int i = 0; i < N; ++i)
            if (isempty(a, (x + xcoordinate[i]),
                    (y + ycoordinate[i])))
                count++;

        return count;
    }







    BoardCell nextMove(int a[], BoardCell cell)
    {
        int min_degree_idx = -1, c,
                min_degree = (N + 1), nextx, nexty;
        int start = ThreadLocalRandom.current().nextInt(1000) % N;
        for (int count = 0; count < N; ++count)
        {
            int i = (start + count) % N;
            nextx = cell.x + xcoordinate[i];
            nexty = cell.y + ycoordinate[i];
            if ((isempty(a, nextx, nexty)) &&
                    (c = getDegree(a, nextx, nexty)) < min_degree)
            {
                min_degree_idx = i;
                min_degree = c;
            }
        }

        if (min_degree_idx == -1)
            return null;
        // Store coordinates of next point
        nextx = cell.x + xcoordinate[min_degree_idx];
        nexty = cell.y + ycoordinate[min_degree_idx];

        a[nexty * N + nextx] = a[(cell.y) * N +
                (cell.x)] + 1;

        // Update next point
        cell.x = nextx;
        cell.y = nexty;

        return cell;
    }

    /* displays the chessboard with knight's moves */
    void print(int a[])
    {
        for (int i = 0; i < N; ++i)
        {
            for (int j = 0; j < N; ++j)
                System.out.printf("%d\t", a[j * N + i]);
            System.out.printf("\n");
        }
    }

    boolean neighbour(int x, int y, int xx, int yy)
    {
        for (int i = 0; i < N; ++i)
            if (((x + xcoordinate[i]) == xx) &&
                    ((y + ycoordinate[i]) == yy))
                return true;
        return false;}

    boolean findClosedTour()
    {

        int a[] = new int[N * N];
        for (int i = 0; i < N * N; ++i)
            a[i] = -1;
        int startx = 0;
        int starty = 0;

        // Current points are same as initial points
        BoardCell cell = new BoardCell(startx, starty);

        a[cell.y * N + cell.x] = 1; // Mark first move.

        BoardCell ret = null;
        for (int i = 0; i < N * N - 1; ++i)
        {
            ret = nextMove(a, cell);
            if (ret == null)
                return false;
        }

        if (!neighbour(ret.x, ret.y, startx, starty))
            return false;

        print(a);
        return true;
    }

    public static void main(String[] args)
    {
        // While we don't get a solution
        while (!new KnightTour().findClosedTour())
        {
            ;
        }
    }
}

class BoardCell
{
    int x;
    int y;

    public BoardCell(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
