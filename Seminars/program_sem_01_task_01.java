import java.util.Scanner;

//Дан массив двоичных чисел, например [1,1,0,1,1,1], 
//вывести максимальное количество подряд идущих 1.

public class program_sem_01_task_01 {
    static String get_sum_String(String str_1, String str_2){
        int s_1 = Integer.parseInt(str_1);
        int s_2 = Integer.parseInt(str_2);
        int result = s_1 + s_2;
        return "" + result;
    }

    static boolean is_digit(char ch) {
        for (int i = 0; i < 10; i++) {
            char c = Character.forDigit(i, 10);
            if (c == ch) {
                return true;
            }
        }
        return false;
    }

    final static String get_coefficient(String str) {
            StringBuilder strb = new StringBuilder();
            char minus = '-';
            //strb = strb.append("");
            if (str.charAt(0) == minus) {
                strb = strb.append("-");
                if (! is_digit(str.charAt(1))) {
                return "-1";
                } 
            } else if (! is_digit(str.charAt(0))) {
                return "1";
            } else {
                strb = strb.append(str.charAt(0));
            }
                      
            int i = 1;
            while (i < str.length() && is_digit(str.charAt(i))) {
                strb = strb.append(str.charAt(i++));
            }        
            return strb.toString();
        }

        static int get_count_X_in_string(String str) {
            int result = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == (char) 'X') {
                    result++;
                }
            }
            return result;
        }

        static String [] get_array_coefficients(String str) {      
            str = str.replace(" ", "");       
            str = str.replace("-", "+-");    
            if (str.charAt(0) == '+') {
                str = (String) str.subSequence(1, str.length());
            }
            String[] array = str.split("[+=]");
            int max_degree = get_count_X_in_string(array[0]); 
            String [] arr = new String [max_degree + 1]; 
          
            arr[0] = get_coefficient(array[0]); 
            int count_blank = 0;
            for (int i = 1; i < max_degree + 1; i++) {                
                int temp_degree = get_count_X_in_string(array[i - count_blank]);
                if (temp_degree == max_degree - i) {
                    String temp_coef = get_coefficient(array[i - count_blank]);           
                    arr[i] = temp_coef;  
                } else {
                    arr[i] = "0";
                    count_blank ++;
                }
            }
            return arr;
        }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter str: ");
        String str = sc.nextLine();
        String[] numbers_array = get_array_coefficients(str);
        System.out.printf("The size of array is: %d", numbers_array.length);
        System.out.println();
        for (String element : numbers_array) {
            System.out.println(element);
        }
        //String sum = get_sum_String(program_sem_01_task_01.get_coefficient(numbers_array[0]), program_sem_01_task_01.get_coefficient(numbers_array[1]));
        //System.out.printf("%s + %s = %s", numbers_array[0], numbers_array[1], sum);
   
        sc.close();
    }
}
