/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SVL;

import Controladoras.ctrDepartamento;
import Controladoras.ctrDocente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlo
 */
@WebServlet(name = "svlDocente", urlPatterns = {"/svlDocente"})
public class svlDocente extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        String parametros = null;
        if(request.getParameter("evento") != null)
            parametros = new String(request.getParameter("evento").getBytes("ISO-8859-1"),"UTF-8");
        
        if(parametros != null)
        {
            String filtro = parametros.substring(0, parametros.indexOf(";"));
            
            if(filtro.equals("get"))
            {
                PrintWriter out = response.getWriter();
                String departamento = parametros.substring(parametros.indexOf("=") + 1);

                ctrDepartamento ctrDep = new ctrDepartamento();
                List<String>resultado = new ctrDocente().get(ctrDep.getCodigo(departamento));
                
                for (String i : resultado) 
                {
                    out.print("<tr onclick=\"clickDocente('"+i+"')\">");
                    out.print("<td>");
                    out.print(i);
                    out.print("</td>");
                    out.print("</tr>");
                }
            }
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
