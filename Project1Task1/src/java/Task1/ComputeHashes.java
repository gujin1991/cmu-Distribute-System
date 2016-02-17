/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//***
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
//***

/**
 *
 * @author Jin
 */
public class ComputeHashes extends HttpServlet {

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
            BASE64Encoder Encoder = new BASE64Encoder();
            String msg = request.getParameter("userinput");
            
            String type = request.getParameter("md5");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComputeHashes</title>");            
            out.println("</head>");
            out.println("<body>");            
            out.println("<h1>You can do anything with dream!</h1>");
            if(type == null) {
                byte[] secretByte = getSecretString(msg, "SHA1");
                String baseStr = Encoder.encode(secretByte);
                String secretStr = getHexString(secretByte);
                out.println("<p>SHA1(Hex): " + secretStr + "</p>");
                out.println("<p>SHA1(Base 64): " + baseStr + "</p>");
            } else {
                byte[] secretByte = getSecretString(msg, "MD5");
                String baseStr = Encoder.encode(secretByte);
                String secretStr = getHexString(secretByte);
                out.println("<p>MD5(Hex): " + secretStr + "</p>");
                out.println("<p>MD5(Base 64): " + baseStr + "</p>");
            }
            
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            return;
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
        processRequest(request, response);
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

    public String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring( 1 ); 
        }
        return result; 
    }
    
    public byte[] getSecretString(String input, String type) {
        try {  
  
      // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）  

      MessageDigest messageDigest =MessageDigest.getInstance(type);  
  
  
      // 输入的字符串转换成字节数组  
  
      byte[] inputByteArray = input.getBytes();  
  
  
  
      // inputByteArray是输入字符串转换得到的字节数组  
  
      messageDigest.update(inputByteArray);  
  
  
  
      // 转换并返回结果，也是字节数组，包含16个元素  
  
      return messageDigest.digest();  
  
  
  
      // 字符数组转换成字符串返回  
  
        
  
       
  
   } catch (Exception e) {  
  
      return null;  
  
   }  

    }

}
