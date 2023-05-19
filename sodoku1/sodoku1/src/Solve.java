
public class Solve
{
    public static int [][] fullgrid = new int[9][9];
    public static int [][] puzzeldGrid= new int[9][9];
    public static int [][] solvedGrid = new int[9][9];

    public int[][] helpgrid= new int[9][9];

//-----------------------------------------------------------------------------
// function Name: mix
// ---------------
//
// General :
// This function shuffles an array of integers in a random order.
//
// Parameters :
// p - an array of integers that will be shuffled.
//
// Return Value :
// This function does not return a value (void).
//
//-----------------------------------------------------------------------------
    public void  mix(int p[])
    {
        int i,j, help;
        for (i=1;i<10;i++)
            p[i-1]=i;
        for(i=0;i<9;i++)
        {
            j=(int) (Math.random()*(8+1));
            help= p[i];
            p[i]=p[j];
            p[j]=help;
        }
    }
//-----------------------------------------------------------------------------
// function Name: isOk
// ---------------
//
// General : Determines whether a given value can be placed in a given position of a Sudoku grid.
//
// Parameters :
// grid: a 2D array representing the Sudoku grid.
// row: an integer representing the row index of the position to be checked.
// col: an integer representing the column index of the position to be checked.
// c: an integer representing the value to be checked.
//
// Return Value :
// Returns a boolean value indicating whether the given value c can be placed in the given position (row, col) of the grid.
//
//-----------------------------------------------------------------------------
    public static boolean isOk(int[][] grid, int row, int col, int c)
    {
        int i, j;
        if (grid[row][col] != 0) {
            return false;
        }
        for (i = 0; i < 9; i++) //in col
        {
            if (i != row) {
                if (grid[i][col] == c)
                    return false;
            }
        }
        for (i = 0; i < 9; i++)//in row
        {
            if (i != col) {
                if (grid[row][i] == c)
                    return false;
            }
        }
        //small sqears
        int r1,c1;
        r1=row/3*3;
        c1=col/3*3;
        for(i=0;i<3;i++)
            for(j=0;j<3;j++)
                if((r1+i!=row)||(c1+j!=col))
                    if(grid[r1+i][c1+j]==c)
                        return false;
        return true;

    }
//-----------------------------------------------------------------------------
// function Name: create_full
// ---------------
//
// General: This function is used to generate a Sudoku grid recursively.
// It uses a backtracking algorithm to fill in the cells of the grid, one by one.
//
// Parameters:
// row - an integer representing the current row in the grid.
// col - an integer representing the current column in the grid.
//
// Return Value:
// A boolean value representing whether or not a solution for the grid was found.
// True indicates a solution was found, while false indicates no solution was found.
//
//-----------------------------------------------------------------------------

    public boolean create_full(int row,int col)
    {
        int[] candidate=new int[9];
        boolean done;
        int i=0;
        mix(candidate);
        if( row==8&&col==8)
        {
            while (isOk(fullgrid,row,col,candidate[i])==false)
                i++;
            if(i==9)
                return false;
            fullgrid[8][8]=candidate[i];
            return true;
        }
        for (i=0;i<9;i++)
        {
            if(isOk(fullgrid,row,col,candidate[i]))
            {
                fullgrid[row][col]=candidate[i];
                if(col==8)
                    done=create_full(row+1,0);
                else
                    done=create_full(row,col+1);
                if (done==false)
                    fullgrid[row][col]=0;
                else
                    return true;
            }
        }
        return false;
    }
//-----------------------------------------------------------------------------
// function Name: solveduko
// ---------------
//
// General : Solves a Sudoku grid using recursive backtracking algorithm
// and returns the number of solutions found.
//
// Parameters :
// None
//
// Return Value :
// int - the number of solutions found for the Sudoku grid
//
//-----------------------------------------------------------------------------
    public static int solveduko()
    {
            int i ,j,num,k;
            i=j=0;
            for (i=0;i<9;i++)
            {
                for (j=0;j<9&&solvedGrid[i][j]!=0;j++)
                {
                    if(j==8)
                        break;
                }
                if(solvedGrid[i][j]==0)
                    break;
            }
            if (i==9)
                return 1;
            k=0;
            for (num=1;num<10;num++)
            {
                if(isOk(solvedGrid,i,j,num))
                {
                    solvedGrid[i][j]=num;
                    k+=solveduko();
                    if(k==0)
                        solvedGrid[i][j]=0;
                }
            }
            return k;
    }
//-----------------------------------------------------------------------------
// function Name: canBeSolved
// ---------------
//
// General : Checks if a Sudoku puzzle can be solved using backtracking algorithm.
//
// Parameters :
// helpgrid - a 2D array representing the Sudoku puzzle to be solved.
//
// Return Value :
// Returns an integer value. If the puzzle can be solved, returns 1. If the puzzle can have multiple
// solutions, returns 2. If the puzzle cannot be solved, returns 0.
//
//-----------------------------------------------------------------------------
    public int canBeSolved( int helpgrid[][])
    {
        int i, j, num, k;
        i=j=0;
        for (i=0;i<9;i++)
        {
            for (j=0;j<9 && helpgrid[i][j]!=0;j++)
            {
                if (j==8)
                    break;
            }
            if (helpgrid[i][j]==0)
                break;
        }
        if (i==9)
            return 1;
        k=0;
        for (num=1;num<10;num++)
        {
            if(isOk(helpgrid,i,j,num))
            {
                helpgrid[i][j]=num;
                k+=canBeSolved(helpgrid);
                helpgrid[i][j]=0;
                if(k==2)
                    return 2;
            }
        }
        return k;
    }
//-----------------------------------------------------------------------------
// function Name: solvedukoPuzzle
// ---------------
//
// General : Solves the Sudoku puzzle by copying the values from puzzeldGrid to solvedGrid and then
// calls the solveduko() function.
//
// Parameters : None
//
// Return Value : Integer value, which represents the number of solutions possible.
//
//
//-----------------------------------------------------------------------------
    public int solvedukoPuzzle()
    {
        int i,j;
        for(i=0;i<9;i++)
            for (j=0;j<9;j++)
                solvedGrid[i][j]=puzzeldGrid[i][j];
        return (solveduko());
    }
//-----------------------------------------------------------------------------
// function Name: createSodukoPuzzle
// ---------------
//
// General : Creates a Sudoku puzzle by removing numbers from a solved Sudoku grid
//
// Parameters : None
//
// Return Value : None
//
//-----------------------------------------------------------------------------
    public void createSodukoPuzzle()
    {
        int[] cand1=new int[9];
        int[] cand2=new int[9];
        int i,j,t;
        mix(cand1);
        mix(cand2);
        for (i=0;i<9;i++)
            for (j=0;j<9;j++)
                puzzeldGrid[i][j]=fullgrid[i][j];
        for (i=0;i<9;i++)
            for (j=0;j<9;j++)
            {
                t=puzzeldGrid[cand1[i]-1][cand2[j]-1];
                puzzeldGrid[cand1[i]-1][cand2[j]-1]=0;
                if (canBeSolved(puzzeldGrid)!=1)
                            puzzeldGrid[cand1[i]-1][cand2[j]-1]=t;

            }
    }

    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
