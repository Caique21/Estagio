/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SVL;

import Controladoras.ctrDepartamento;
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

@WebServlet(name = "svlQualis", urlPatterns = {"/svlQualis"})
public class svlQualis extends HttpServlet
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
        
        
        try
        {
            if(parametros != null)
            {
                String filtro = parametros.substring(0, parametros.indexOf(";"));
                
                if(filtro.equals("get"))
                {
                    PrintWriter out = response.getWriter();
                    String departamento = parametros.substring(parametros.indexOf("=") + 1);
                    
                    ctrDepartamento ctrDep = new ctrDepartamento();
                    List<String> resultado = new ctrQualis().get(ctrDep.getCodigo(departamento));
                    if(resultado.size() > 0)
                    {
                        out.print("<table cellpadding=\"2\" class=\"centered highlight responsive-table\" style=\"margin-left:50px; width:70%;\" id=\"tabela_qualis\">");
                        out.print("<tr>");
                        out.print("<td><b>Documento</b></td>");
                        out.print("<td><b>Ano</b></td>");
                        out.print("<td></td>");
                        out.print("<td></td>");
                        out.print("</tr>");

                        for (int i = 0; i < resultado.size(); i++) 
                        {
                            String[] aux = resultado.get(i).split(";",2);

                            out.print("<tr>");
                            out.print("<td id=\"nome_documento\">" + aux[0] + "</td>");
                            out.print("<td>" + aux[1] + "</td>");
                            out.print("<td><div id=\"teste\">"
                                    + "<img src=\"Imagens/visualizar.png\" alt=\"Clique aqui para baixar\" id= \"botao-visualizar-qualis\" onclick=\"clickVisualizar('"+aux[0]+ ";" + aux[1] + "')\" />"
                                    + "<span id=\"testespan\">Baixar</span></div></td>");
                             out.print("<td id=\"remover_q\"><div id=\"remover\">"
                                    + "<img src=\"Imagens/lixeira.png\" alt=\"Clique aqui para remover\" id= \"btnRemoverQualis\" onclick=\"clickremover('"+aux[0]+ ";" + aux[1] + "')\"/>"
                                    + "<span id=\"span-remover\">Remover</span></div></td>");
                            out.print("</tr>");
                        }
                        out.print("</table>");
                    }
                    else
                    {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Não foram encontrados arquivos para este Departamento');");
                        out.println("location='ImportarQualis.jsp';");
                        out.println("</script>");
                    }
                }
                else if(filtro.equals("down"))
                {
                    ctrDepartamento ctrDep = new ctrDepartamento();
                    String nome_dep = parametros.substring(parametros.lastIndexOf(";") + 1);
                    String[] content = new String(parametros.getBytes("ISO-8859-1"),"UTF-8").split(";");
                    
                    OutputStream output = response.getOutputStream();
                    FileInputStream in = new ctrQualis().getArquivo(content[1], content[2], ctrDep.getCodigo(nome_dep));
                    byte[] buffer = new byte[4096];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                    {
                        output.write(buffer, 0, length);
                    }
                    output.flush();
                }
                else if(filtro.equals("del"))
                {
                    PrintWriter out = response.getWriter();
                    ctrDepartamento ctrDep = new ctrDepartamento();
                    String nome_dep = new String(request.getParameter("sel-dep").getBytes("ISO-8859-1"),"UTF-8");
                    String[] content = new String(parametros.getBytes("ISO-8859-1"),"UTF-8").split(";");
                    
                    if(new ctrQualis().apagar(content[1], content[2], ctrDep.getCodigo(nome_dep)))
                        out.print("ok");
                    else
                        out.print("nao");
                }
            }
            else
            {
                PrintWriter out = response.getWriter();
                Part filePart = request.getPart("fileInput"); // Retrieves <input type="file" name="file">
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = filePart.getInputStream();
                String[] content = fileName.split("_");
                

                ctrQualis ctr_qualis = new ctrQualis();
                ctrDepartamento ctr_dep = new ctrDepartamento();
                String nome_dep = new String(request.getParameter("sel-dep").getBytes("ISO-8859-1"),"UTF-8");
                if(ctr_qualis.salvar(fileName, fileContent,content[content.length - 2],ctr_dep.getCodigo(nome_dep)))
                    out.print("gravou");
                else
                    out.print("nao");
                    
            }
        }
        catch(IOException | ServletException e)
        {
            System.out.println(e.getMessage());
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

    private boolean getUltimo()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
