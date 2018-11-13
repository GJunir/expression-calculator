package Servlets;

import Calculator.ExpressionCalculator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;

public class MainServlet extends  HttpServlet {

    private String expressionResult(String s) {

        ExpressionCalculator n = new ExpressionCalculator();
        List<String> expression = n.parse(s);
        if (n.flag)
        return n.calc(expression).toString();
        else
            return expression.toString();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String expression = new String();
        String button = new String();
        if (button!=null){
            expression = request.getParameter("expressionField");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println((expressionResult(expression)));
    }
}
