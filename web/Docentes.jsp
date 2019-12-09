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
        <link href="CSS/docentes.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="Imagens/unesp-logo3.png" sizes="16x16">
    </head>
    <body>
        
        <nav>
            <div class="nav-wrapper">
                <a href="index.jsp" class="brand-logo right">
                    <img src="Imagens/unesp-logo2-branco.png" alt="Logo Unesp" style="width: 90px;"/>
                </a>
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
        
        <div class="center" style="margin-top: 2%;">
            <label id="content-title"><b>Docentes</b></label>
            <form name="docente" method="POST" enctype="multipart/form-data" onsubmit="ImportarCurriculo()" id="docente">
                <div>
                    <input list="hosting-plan" name="sel-dep" type="text" placeholder="Selecione um Departamento" class="sel-dep" onchange="changeDepartamento()" id="sel-dep">
                    <datalist id="hosting-plan">
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
                
                <div id="div-docentes" hidden="true">
                    <label id="label-lista-docentes"><b>Lista de Docentes</b></label>
                    <br>
                    <div id="table-wrapper">
                        <div id="table-scroll">
                            <table class="centered highlight responsive-table" id="tabela-docentes">
                                <tbody id="table-docente-body">
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    <div id="informacoes-docente" hidden="true"> 
                        <div>
                            <a href="#div-dados-docente" class="collapsible" id="t">Dados do Docente</a>
                            <div class="content" id="div-dados-docente">
                                <div class="input-field col s6" style="width: 200px;">
                                    <input id="icon_prefix" type="text" class="validate">
                                    <label for="icon_prefix" style="background: transparent;">First Name</label>
                                </div>
                            </div>
                        </div>

                        <br>
                        <div>
                            <a href="#div-curriculo-docente" class="collapsible" id="t">Curriculo do Docente</a>
                            <div class="content" id="div-curriculo-docente">
                                <table class="centered highlight responsive-table" id="tabela_curriculo">
                                    <tbody>
                                        <tr id="tabela_curriculo-tr">
                                        </tr>
                                    </tbody>
                                </table>
                                <%--<div class="row">
                                    <div class="col s12">
                                        <ul class="tabs">
                                            <li class="tab col s3" onclick="clickCurriculoTab('dados_gerais')"><a href="#test1">Dados Gerais</a></li>
                                            <li class="tab col s3" onclick="clickCurriculoTab('formacao')"><a href="#test2">Test 2</a></li>
                                            <li class="tab col s3"><a href="#test3">Disabled Tab</a></li>
                                            <li class="tab col s3"><a href="#test4">Test 4</a></li>
                                        </ul>
                                    </div>
                                </div>
                                
                                <div id="identificacao" hidden="true"> 
                                        <a href="#div-dados-gerais" class="collapsible" id="t">Identificação</a>
                                        <div class="content" id="div-dados-gerais">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                        </div>
                                </div>
                                --%>
                                <br>
                                <input type="file" id="fileInput" name="fileInput" accept=".xml"/>
                                <button type="submit" id="fileExport">
                                    Importar
                                </button>
                                
                                <input type="text" id="nome-docente" name="nome-docente" hidden="true"/>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
           
            
        
        
        <script src="JS/docentes.js" type="text/javascript"></script>
        <script src="JS/materialize.js" type="text/javascript"></script>
        <script src="JS/jquery-3.4.1.js" type="text/javascript"></script>
    </body>
</html>

