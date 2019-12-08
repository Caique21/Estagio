/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SVL;

import Controladoras.ctrDepartamento;
import Controladoras.ctrDocente;
import Controladoras.ctrQualis;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author carlo
 */
@MultipartConfig(
        location = "/",
        fileSizeThreshold = 1024 * 1024, // 1MB *      
        maxFileSize = 1024 * 1024 * 100, // 100MB **
        maxRequestSize = 1024 * 1024 * 10 * 10 // 100MB ***
)
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
            else if(filtro.equals("getDocumento"))
            {
                PrintWriter out = response.getWriter();
                String docente = parametros.substring(parametros.indexOf("doc=") + 4,parametros.lastIndexOf(";"));
                String departamento = parametros.substring(parametros.lastIndexOf(";")+1);
                ctrDepartamento ctrDep = new ctrDepartamento();
                
                if(new ctrDocente().getCurriculo(docente,ctrDep.getCodigo(departamento)))
                {
                    out.print("<td id=\"identificacao-lattes\"></td>");
                    out.print("<td><div id=\"div-baixar-curriculo\">");
                    out.print("<img src=\"Imagens/visualizar.png\" onclick=\"baixarCurriculo()\" alt=\"Baixar Arquivo\" id=\"btn-baixar-curriculo\"/>");
                    out.print("<span id=\"span-baixar\">Baixar</span>");
                    out.print("</div></td>");
                    
                    out.print("<td><div id=\"div-remover-curriculo\">");
                    out.print("<img src=\"Imagens/lixeira.png\" onclick=\"removerCurriculo()\" alt=\"RemoverCurriculo\" id=\"btn-remover-curriculo\"/>");
                    out.print("<span id=\"span-remover\">Remover</span>");
                    out.print("</div></td>");
                }
                else
                    out.print("<lable>Nenhum currículo importado</label>");
            }
            else if(filtro.equals("del"))
            {
                PrintWriter out = response.getWriter();
                ctrDepartamento ctr_dep = new ctrDepartamento();
                String departamento = new String(request.getParameter("sel-dep").getBytes("ISO-8859-1"),"UTF-8");
                String docente = request.getParameter("nome-docente");

                if(new ctrDocente().apagar(docente,ctr_dep.getCodigo(departamento)))
                    out.print("ok");
                else
                    out.print("nao");
            }
            else if(filtro.equals("down"))
            {
                String docente = new String(parametros.substring(parametros.lastIndexOf(";") + 1).getBytes("ISO-8859-1"),"UTF-8");

                OutputStream output = response.getOutputStream();
                FileInputStream in = new ctrDocente().getArquivo(docente);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0)
                {
                    output.write(buffer, 0, length);
                }
                output.flush();
            }
        }
        else
        {
            PrintWriter out = response.getWriter();
            String docente = request.getParameter("nome-docente");
            Part filePart = request.getPart("fileInput"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();

            ctrDocente ctr_doc = new ctrDocente();
            ctrDepartamento ctr_dep = new ctrDepartamento();
            String departamento = new String(request.getParameter("sel-dep").getBytes("ISO-8859-1"),"UTF-8");
            if(ctr_doc.salvar(docente,fileContent,ctr_dep.getCodigo(departamento)))
                out.print("salvou");
            else
                out.print("nao");
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
