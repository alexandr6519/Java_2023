
import java.util.Scanner;

public class program_01 {

public static int[] getRandomArray(int min, int max, int n){
    int[] array = new int[n];
    for (int i = 0; i < n; i++)
        array[i] = (int)(Math.random()*((max - min) + 1)) + min;
    return array;
}
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.printf("Enter number: ");
        int k = scn.nextInt();
        int [] array_coefficients = program_01.getRandomArray(0, 100, k + 1);
        StringBuilder st = new StringBuilder();
        st = st.append(array_coefficients[0]);
        for (int i = 1; i < k + 1; i++) {
            st = st.append(" + ").append(array_coefficients[i]);
            for (int j = 0; j < i; j++)
                st = st.append(" * ").append("X");
        }
        System.out.println(st.append(" = 0"));
        //System.out.printf("%s and %d", name, x);
        scn.close();
    }
}
