package Calculator;

import java.util.*;

public class ExpressionCalculator {

    final String OPERATORS = "+-/*";
    final String DELIMETERS = "()" + OPERATORS;

    public boolean flag = true;

    private boolean isDelimeter (String token){
        if (token.length() != 1) return false;
        for (int i = 0; i< DELIMETERS.length(); i++) {
            if (token.charAt(0) == DELIMETERS.charAt(i)) return true;
        }
        return false;
    }

    private boolean isOperator(String token){
        if (token.equals("u-") || token.equals("u+")) return true;
        for (int i=0; i<OPERATORS.length(); i++){
            if (token.charAt(0) == OPERATORS.charAt(i)) return true;
        }
        return false;
    }

    private int priority (String token){
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("/") || token.equals("*")) return 3;
        return 4;
    }

    //Формируем ОПЗ
    public List<String> parse (String infix){
        List<String> postfix = new ArrayList<String>();
        Deque<String> stack = new ArrayDeque<String>();
        List<String> wrongBrackets = Collections.singletonList("Неправильные скобки");
        List<String> incorrectExpression = Collections.singletonList("Некорректное выражение");
        StringTokenizer tokenizer = new StringTokenizer(infix, DELIMETERS, true);
        String prev = "";
        String curr = "";
        while (tokenizer.hasMoreTokens()){
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)
                    || isOperator(curr) && isOperator(prev) && !prev.equals("")
                    || (curr.equals("/") || curr.equals("*")) && prev.equals("")){
                //Некорректное выражение
                flag = false;
                return incorrectExpression;
            }
            if (curr.equals(" ")) continue;
            if (isDelimeter(curr)){
                if (curr.equals("(")) stack.push(curr);
                else if (curr.equals(")")){
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            //Неправильные скобки
                            flag = false;
                            return wrongBrackets;
                        }
                    }
                    stack.pop();
                }
                else {
                    //unary -
                    if (curr.equals("-") && (prev.equals("") || (isDelimeter(prev) && !prev.equals(")")))){
                        curr = "u-";
                    }
                    else {
                        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek())))
                            postfix.add(stack.pop());
                    }
                    //unary +
                    if (curr.equals("+") && (prev.equals("") || (isDelimeter(prev) && !prev.equals(")")))){
                        curr = "u+";
                    }
                    else {
                        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek())))
                            postfix.add(stack.pop());
                    }
                    stack.push(curr);
                }
            }
            else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()){
            if (isOperator(stack.peek())) {
                postfix.add(stack.pop());
            }
            else {
                //Неправильные скобки
                flag = false;
                return wrongBrackets;

            }
        }
        return postfix;
    }

    //Вытаскиваем элементы из стека и ведём подсчёт
    public static Double calc(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<Double>();
        for (String x : postfix) {
            if (x.equals("+")) stack.push(stack.pop() + stack.pop());
            else if (x.equals("-")) {
                Double b = stack.pop(), a = stack.pop();
                stack.push(a - b);
            }
            else if (x.equals("*")) stack.push(stack.pop() * stack.pop());
            else if (x.equals("/")) {
                Double b = stack.pop(), a = stack.pop();
                stack.push(a / b);
            }
            else if (x.equals("u-")) stack.push(-stack.pop());
            else if (x.equals("u+")) stack.push(stack.pop());
            else stack.push(Double.valueOf(x));
        }
        return stack.pop();
    }
}


