<%-- 
    Document   : DepartamentoPage
    Created on : 06/11/2019, 15:37:53
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>PASD - Departamentos</title>
        <meta charset="UTF-8">
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

        <div class="row">
            <div class="col s12 m7" style="width: 20%;">
                <div class="card">
                    <div class="card-image center">
                        <a href="ImportarQualis.jsp"><img src="Imagens/import.png" alt="" id="icon"/></a>
                    </div>
                    <br>
                    <div class="card-action center">
                        <a href="ImportarQualis.jsp">Qualis</a>
                    </div>
                </div>
            </div>
            
            <div class="col s11 m7" style="width: 20%;">
                <div class="card">
                    <div class="card-image center">
                        <a href=""><img src="Imagens/docente.png" alt="" id="icon" /></a>
                    </div>
                    <br>
                    <div class="card-action center">
                        <a href="#">Docentes</a>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="JS/jquery-3.4.1.js" type="text/javascript"></script>
        <script src="JS/main.js" type="text/javascript"></script>
        <script src="JS/materialize.js" type="text/javascript"></script>
        <script src="JS/materialize.min.js" type="text/javascript"></script>
   </body>
</html>

