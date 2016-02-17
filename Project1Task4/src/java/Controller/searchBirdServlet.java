/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.searchBirdModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.select.Elements;
import java.util.*;
import javax.servlet.RequestDispatcher;
import org.jsoup.nodes.Element;

/**
 *
 * @author Jin
 */
public class searchBirdServlet extends HttpServlet {
    /*public static void main(String[] args) {
        searchBirdModel model = new searchBirdModel();
        System.out.println("hello");
        String url = "http://nationalzoo.si.edu/scbi/migratorybirds/featured_photo/";
        try  {
            Elements options = model.extrateData(url);
            List<Element> birdList = options.subList(0, options.size());
            for(Element e : birdList) {
                System.out.println(e.val());
            }
        } catch (Exception e){
        }
    }*/
    
    private searchBirdModel model = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        model = new searchBirdModel();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet searchBird</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchBird at " + request.getContextPath() + "</h1>");
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
        
        //String url = "http://nationalzoo.si.edu/scbi/migratorybirds/featured_photo/";
        try  {
            String url = "http://nationalzoo.si.edu/scbi/migratorybirds/featured_photo/";
            String type = request.getParameter("birdType");
            //String type.request("birdType");
            System.out.println(type);
            Elements options = model.extrateData(url);
            List<String> birdList = new ArrayList<String>();
            
            for(Element e : options) {
                birdList.add(e.toString());
     
            }
            request.setAttribute("birdList", birdList);
            String nextView = "prompt.jsp";
            if(type != null) {
                url += "bird.cfm?pix=" + type;
                HashMap<String, ArrayList> pictures = model.extratePicture(url);
                System.out.println(pictures.size());
                List<String> authorList = new ArrayList<String>();
                for(String key : pictures.keySet()) {
                    
                    authorList.add(key);
                }
                
                Random random = new Random();
                String author = authorList.get(random.nextInt(authorList.size()));
                List<String> picList = pictures.get(author);
                String picture = picList.get(random.nextInt(picList.size()));
                System.out.println(author + "1");
                System.out.println(picture + "2");
                request.setAttribute("author", author);
                request.setAttribute("picture", picture);
                nextView = "result.jsp";
            }
            RequestDispatcher view = request.getRequestDispatcher(nextView);
            view.forward(request, response);
            //System.out.println(birdList.get(0));
        } catch (Exception e){
            return;
        }
        
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
