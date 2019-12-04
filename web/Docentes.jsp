<%-- 
    Document   : ImportarQualis
    Created on : 06/11/2019, 15:38:46
    Author     : carlo
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.FileReader" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>PASD - Docentes</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="CSS/materialize.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/materialize.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <nav>
            <div class="nav-wrapper" style="background-color: #346786;">
                <a href="index.jsp" class="brand-logo right"><img src="Imagens/UnespLogo.jpg" alt="Logo Unesp" style="width: 64px;"/></a>
                <ul id="nav-mobile" class="left hide-on-med-and-down">
                    <li><a href="DepartamentoPage.jsp">Departamento</a></li>
                    <li><a href="">Lattes</a></li>
                    <li><a href="">Relatórios</a></li>
                </ul>
                <div class="brand-logo center" style="font-size: 25px">
                    Programa de Assistência à Secretaria Departamental
                </div>
            </div>
        </nav>
        
        <div class="center" style="margin-top: 5%;">
            <label style="text-align: center; width: 75%;font-size: 35px; color: #346786;"><b>Docentes</b></label>
            <form name="importar qualis"  method="POST" enctype="multipart/form-data" onsubmit="ImportarCurriculo()" id="importar_qualis">
                <div>
                    <input list="hosting-plan" name="sel-dep" type="text" placeholder="Selecione um Departamento" class="sel-dep" onchange="changeDepartamento()" id="sel-dep" style="width: 400px;">
                    <datalist id="hosting-plan" class="testeabc">
                        <option value="Cartografia"></option>
                        <option value="Educação"></option>
                        <option value="Educação Física"></option>
                        <option value="Estatística"></option>
                        <option value="Física"></option>
                        <option value="Fisioterapia"></option>
                        <option value="Geografia"></option>
                        <option value="Matemática e Computação"></option>
                        <option value="Planejamento, Urbanismo e Ambiente"></option>
                        <option value="Quimica e Bioquimica"></option>
                    </datalist>
                </div>
                <br>
                
                <div id="div-docentes" hidden="true" style="margin-top: 15px; margin-left: 200px; margin-bottom: 5px;">
                    <table>
                        <tr>
                            nasjsnaidn
                        </tr>
                    </table>
                </div>
            </form>
        </div>
           
            
        
        
        <script src="JS/docentes.js" type="text/javascript"></script>
        <script src="JS/jquery-3.4.1.js" type="text/javascript"></script>
    </body>
</html>

