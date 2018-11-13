package Calculator;

import java.io.*;
import java.util.List;

//Just for fast local tests
class Main {

    public static void main (String[] args) {
        ExpressionCalculator n = new ExpressionCalculator();

        try(BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)))
        {
            String s;
            while(!(s=br.readLine()).equals("")){
                List<String> expression = n.parse(s);
                boolean flag = n.flag;
                if (flag) {
                    for (String x : expression) System.out.print(x + " ");
                    bw.write(ExpressionCalculator.calc(expression).toString() + "\n");
                    bw.flush();
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
