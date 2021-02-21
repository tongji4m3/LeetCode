import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 2, 5, 4, 9, 3, 1, 2, 3, 7};
        Arrays.sort(a,(num1,num2)-> num2-num1);
        Arrays.sort(a);
        int[] x = new int[]{1,3,2};
        Arrays.sort(x);
        System.out.println(Arrays.toString(a));
    }
}
