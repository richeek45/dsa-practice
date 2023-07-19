package matrix;
import java.util.Stack;

public class LargestAreaInHistogram {
    static void findMaxArea(int[] hist) {
        // In the histogram find the left boundary and right boundary
        // which contains the first smaller element which is smaller than hist[i]
        Stack<Integer> stack = new Stack<>();
        // we push -1 to the stack because for some elements there will be no previous smaller element in the
        // array and we can store -1 as the index for previous smaller.
        stack.push(-1);
        int maxArea = hist[0];
        int len = hist.length;
        int[] leftSmaller = new int[len];
        int[] rightSmaller = new int[len];
        // Initializing the leftSmaller and rightSmaller elements to -1 and len-1
        for (int i = 0; i < len; i++) {
            leftSmaller[i] = -1;
            rightSmaller[i] = len-1;
        }

        // for find the left smaller index to calculate the area
        int i = 0;
        while(i < len) {
            int top = stack.peek();
            while (!stack.isEmpty() && stack.peek() != -1 && hist[top] > hist[i]) {
                // if the current element is smaller than element with index stored on the top of stack
                // then, we pop the top element and store the current element index as the
                // right_smaller for the popped element.
                rightSmaller[top] = i;
                stack.pop();
            }
            if (i > 0 && hist[i] == hist[i-1]) {
                // since the previous element has already calculated the left smaller and the element at i
                // is same leftSmaller will also be the same
                // we are not updating the right smaller because it get calculated for element in stack
                leftSmaller[i] = leftSmaller[i-1];
            } else {
                // All the larger top elements are popped in the while loop
                // Element with the index stored on the top of the stack is always smaller than the current element.
                // Therefore the left_smaller[i] will always be s.top().
                leftSmaller[i] = top;
            }
            stack.push(i++);
        }

        for (int j = 0; j < len; j++) {
            int area = hist[j] * (rightSmaller[j] - leftSmaller[j] - 1);
            maxArea = Math.max(maxArea, area);
        }
        System.out.println(maxArea);
    }

    static void findMaxArea2(int[] hist) {
        // if hist[i] is larger than top element in the stack push to the stack
        // else pop the top element and calculate the area of the top element
        Stack<Integer> stack = new Stack<>();

        int N = hist.length;
        int i = 0;
        int maxArea = hist[0];
        while (i < N) {
            if (stack.isEmpty() || hist[i] >= hist[stack.peek()]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                // calculate the area of the top element with left smaller hist[i]
                // and right smaller as current stack.peek() in the stack
                int length = stack.empty() ? i : (i - stack.peek() - 1);
                int area = hist[top] * length;
                maxArea = Math.max(area, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            // calculate the area of the top element with left smaller hist[i]
            // and right smaller as current stack.peek() in the stack
            int length = stack.empty() ? i : (i - stack.peek() - 1);
            int area = hist[top] * length;
            maxArea = Math.max(area, maxArea);
        }
        System.out.println(maxArea);
    }

    public static void main(String[] args) {
        int[] histogram =  {6, 2, 5, 4, 5, 1, 6};
//        findMaxArea(histogram);
        findMaxArea2(histogram);
    }
}
