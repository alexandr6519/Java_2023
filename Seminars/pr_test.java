
// Даны два файла, в каждом из которых находится запись
// многочлена. Сформировать файл содержащий сумму
// многочленов.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class pr_test {
    static boolean is_digit(char ch) {
        for (int i = 0; i < 10; i++) {
            char c = Character.forDigit(i, 10);
            if (c == ch) {
                return true;
            }
        }
        return false;
    }

    static int get_count_X_in_string(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == (char) 'X' || str.charAt(i) == (char) 'x') {
                result++;
            }
        }
        return result;
    }

    static String get_sum_String(String str_1, String str_2) {
        int s_1 = Integer.parseInt(str_1);
        int s_2 = Integer.parseInt(str_2);
        int result = s_1 + s_2;
        return "" + result;
    }
    static String [] get_sum_coefficients(String [] numbers_array_1, String [] numbers_array_2) {
        int size_1 = numbers_array_1.length;
        int size_2 = numbers_array_2.length;
        int dif_len = size_1 - size_2;
        int size = size_1 < size_2 ? size_2 : size_1;
        String[] coefficients = new String[size];

        if (dif_len > 0) {
            for (int i = 0; i < dif_len; i++) {
                coefficients[i] = numbers_array_1[i];
            }
            for (int i = dif_len; i < size_1; i++) {
                coefficients[i] = get_sum_String(numbers_array_1[i], numbers_array_2[i - dif_len]);
            }
        } else if (dif_len < 0) {
            for (int i = 0; i < dif_len; i++) {
                coefficients[i] = numbers_array_2[i];;
            }
            for (int i = dif_len; i < size_2; i++) {
                coefficients[i] = get_sum_String(numbers_array_1[i - dif_len],  numbers_array_2[i]);
            }
        } else {
            for (int i = 0; i < size_1; i++) {
                coefficients[i] = get_sum_String(numbers_array_1[i], numbers_array_2[i]);
            }
        }
        return coefficients;
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

    static String get_coefficient(String str) {
        StringBuilder strb = new StringBuilder();
        char minus = '-';
        if (str.charAt(0) == minus) {
            strb = strb.append("-");
            if (!is_digit(str.charAt(1))) {
                return "-1";
            }
        } else if (!is_digit(str.charAt(0))) {
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
    
    static String get_new_equation(String [] coefficients) {
        StringBuilder stb = new StringBuilder();
        int size = coefficients.length;
        for (int i = 0; i < size - 1; i++) {
            int temp = Integer.parseInt(coefficients[i]);
            if (temp < 0) {
                stb = stb.append("(").append(temp).append(")");
            } else {
                stb = stb.append(temp);
            }
            for (int j = 0; j < size - i - 1; j++) {
                stb = stb.append(" * X");
            }
            stb = stb.append(" + ");
        }
        stb = stb.append("(").append(coefficients[size - 1]).append(")").append(" = 0");
        return stb.toString();
    }
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br_1 = new BufferedReader(new FileReader("file_1.txt"));
        String equation_1 = br_1.readLine();
        String[] numbers_array_1 = get_array_coefficients(equation_1);
        br_1.close();

        BufferedReader br_2 = new BufferedReader(new FileReader("file_2.txt"));
        String equation_2 = br_2.readLine();
        String[] numbers_array_2 = get_array_coefficients(equation_2);
        br_2.close();

        String [] sum_coefficients = get_sum_coefficients(numbers_array_1, numbers_array_2);
        String equation_3 = get_new_equation(sum_coefficients);    

        try (FileWriter fw = new FileWriter("file_3.txt", false)) {
            fw.write(equation_1);
            fw.write("\n");
            fw.write(equation_2);
            fw.write("\n");
            fw.write(equation_3);
            fw.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
