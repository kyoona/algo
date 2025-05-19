import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Juyeon_25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Double> grades = new HashMap<>();

        grades.put("A+", 4.5);
        grades.put("A0", 4.0);
        grades.put("B+", 3.5);
        grades.put("B0", 3.0);
        grades.put("C+", 2.5);
        grades.put("C0", 2.0);
        grades.put("D+", 1.5);
        grades.put("D0", 1.0);
        grades.put("F", 0.0);

        double credits = 0;
        double total = 0;

        for (int i = 0; i < 20; i++) {
            String[] line = br.readLine().split(" ");

            if (!line[2].equals("P")) {
                double credit = Double.parseDouble(line[1]);
                double point = grades.get(line[2]);

                credits += credit;
                total += credit * point;
            }
        }

        double gpa = total / credits;

        System.out.println(gpa);
    }
}
