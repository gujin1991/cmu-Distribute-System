/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task2Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigInteger;

/**
 *
 * @author Jin
 */
public class BigCalc extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BigCalc</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BigCalc at " + request.getContextPath() + "</h1>");
            //out.println(newForm);
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println();
        String firstOperand = request.getParameter("first");
        BigInteger bfOperand = new BigInteger(firstOperand);
        String secondOperand = request.getParameter("second");
        BigInteger bsOperand = new BigInteger(secondOperand);
        int operation = Integer.parseInt(request.getParameter("operation"));
        String res = "";
        switch(operation) {
            case 1:
                res += bfOperand.add(bsOperand);
                break;
            case 2:
                res += bfOperand.multiply(bsOperand);
                break;
            case 3:
                if(bfOperand.gcd(bsOperand).intValue() == 1)
                    res += "Yes";
                else
                    res += "No";
                break;
            case 4:
                res += bfOperand.mod(bsOperand);
                break;
            case 5:
                res += bfOperand.modInverse(bsOperand);
                break;
            case 6:
                res += bfOperand.pow(bsOperand.intValue());
                break;
            
        }
        response.getWriter().write(res);
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
