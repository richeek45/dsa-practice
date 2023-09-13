package Graph;

public class FloodFill {
    // In MS-Paint, when we take the brush to a pixel and click,
    // the color of the region of that pixel is replaced with a new selected color.
    // Following is the problem statement to do this task.
    //Given a 2D screen, location of a pixel in the screen and a color, replace color of the given pixel
    // and all adjacent same colored pixels with the given color.
    static void printScreen(int[][] screen, int M, int N) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(screen[i][j] +  " ");
            }
            System.out.println();
        }
    }

    static boolean withinBounds(int x, int y, int M, int N) {
        return (x >= 0 && x < M && y >=0 && y < N);
    }

    // Flood fill Algorithm – how to implement fill() in paint?
    static void floodFill(int[][] screen, int x, int y, int M, int N, int prevColor, int newColor) {
        // O(M * N) -> due to recursion stack
        if (withinBounds(x, y, M, N)) {
            if (screen[x][y] != prevColor) {
                return;
            }

            // already modified into newColor
            if (screen[x][y] == newColor) {
                return;
            }

            screen[x][y] = newColor;
            floodFill(screen, x+1, y, M, N, prevColor, newColor);
            floodFill(screen, x-1, y, M, N, prevColor, newColor);
            floodFill(screen, x, y+1, M, N, prevColor, newColor);
            floodFill(screen, x, y-1, M, N, prevColor, newColor);
        }
    }


    public static void main(String[] args) {
        int[][] screen = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };
        int prevColor = 2;
        int newColor = 3;
        int x = 4, y = 4; // coordinated should be on the prevColor otherwise it will not work
        int M = screen.length; // row
        int N = screen[0].length; // col

        floodFill(screen, x, y, M, N, prevColor, newColor);
        printScreen(screen, M, N);

    }
}
