import java.util.Stack;

public class T1020 {
    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        Solution solution = new Solution();
        int v = solution.numEnclaves(grid);
        System.out.println(v);
    }
}
class Solution {
    public int numEnclaves(int[][] grid) {
        int total = grid.length * grid.length;
        int toatlZeroNum = 0;
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                toatlZeroNum += grid[i][j];
            }
        }
        toatlZeroNum = total - toatlZeroNum;
        int bfsNum = 0;
        int bfsZeroNum = 0;
        Stack<Point> stack = new Stack<>();
        for (int index = 0; index < grid.length; index++) {
            grid[0][index] += 10;
            stack.push(new Point(0, index));
        }
        for (int index = 0; index < grid.length; index++) {
            if(grid[index][0] < 10) {
                stack.push(new Point(index, 0));
                grid[index][0] += 10;
            }
        }
        for (int index = 0; index < grid.length; index++) {
            if(grid[index][grid.length - 1] < 10) {
                stack.push(new Point(index, grid.length - 1));
                grid[index][grid.length - 1] += 10;
            }
        }
        for (int index = 0; index < grid.length; index++) {
            if(grid[grid.length - 1][index] < 10) {
                stack.push(new Point(grid.length - 1, index));
                grid[grid.length - 1][index] += 10;
            }
        }
        while(!stack.isEmpty()) {
            Point point = stack.pop();
            bfsNum++;
            if (grid[point.i][point.j] == 10) {
                grid[point.i][point.j] = -1;
                bfsZeroNum++;
            } else if (grid[point.i][point.j] == 11) {
                if (isPut(point.i + 1, point.j, grid)) {
                    stack.push(new Point(point.i + 1, point.j));
                    grid[point.i + 1][point.j] += 10;
                }
                if (isPut(point.i - 1, point.j, grid)) {
                    stack.push(new Point(point.i - 1, point.j));
                    grid[point.i - 1][point.j] += 10;
                }
                if (isPut(point.i, point.j + 1, grid)) {
                    stack.push(new Point(point.i, point.j + 1));
                    grid[point.i][point.j + 1] += 10;
                }
                if (isPut(point.i, point.j - 1, grid)) {
                    stack.push(new Point(point.i, point.j - 1));
                    grid[point.i][point.j - 1] += 10;
                }
            }
        }
        return total - bfsNum - (toatlZeroNum - bfsZeroNum);
    }

    boolean isPut(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid.length && grid[i][j] > 0 && grid[i][j] < 10;
    }
}
class Point {
    int i;
    int j;
    Point(int i, int j) {
        this.i = i;
        this.j = j;
    }
}