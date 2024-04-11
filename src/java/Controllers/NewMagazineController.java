package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Magazine;
import Model.MagazinesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author This PC
 */
@WebServlet(urlPatterns = {"/NewMagazineController"})
public class NewMagazineController extends HttpServlet {

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
            out.println("<title>Servlet NewMagazineController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewMagazineController at " + request.getContextPath() + "</h1>");
            // Lấy dữ liệu từ form được gửi đến Servlet
            String id = request.getParameter("id");
            String title = request.getParameter("title");
            String publisher = request.getParameter("publisher");
            String p = request.getParameter("price");
            if (Validation(id, title, publisher, p)==("")) {
                Magazine m = new Magazine(id, title, publisher, Double.parseDouble(p));
                try {
                    MagazinesDAO dao = new MagazinesDAO();
                    if (dao.newMagazine(m)) {
                        out.print("New Magazine Inserted!!!");
                        out.print("<form action=\"index.jsp\" method=\"post\">\n<br><br>"
                                + " <input type=\"submit\" value=\"List all magazines\">\n"
                                + " </form>");
                    } else {
                        out.print("FAIL");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.print("ERROR: " + e.getMessage());
                }
            } else {
                out.println(Validation(id, title, publisher, p));
            }

            out.println("</body>");
            out.println("</html>");
        }
    }
    public String Validation(String id, String title, String publisher, String price) {
    if (!id.matches("^M\\d{3}$")) {
        return "Maz must follow M + 3 digits";
    }
    MagazinesDAO dao = new MagazinesDAO();
    ArrayList<Magazine> list = dao.getAll(id);
    
    if (!list.isEmpty()) {
        return "This maz id existed";
    }
    if (title.isEmpty()) {
        return "Please input title";
    }
    if (publisher.isEmpty()) {
        return "Please input publisher";
    }
    try {
        double p = Double.parseDouble(price);
    } catch (NumberFormatException e) {
        return "Price must be a number";
    }
    return "";
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

} 
