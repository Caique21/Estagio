<%-- 
    Document   : index
    Created on : 06/11/2019, 15:27:39
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>PASD - Planilha Complemento de Adendo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="CSS/materialize.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/materialize.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/planilha.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="Imagens/unesp-logo3.png" sizes="16x16">
        <style>
            .tabs .indicator
            {
                background-color: #05aae6;
            }
            .tabs .tab a:focus, .tabs .tab a:focus.active
            {
                background: transparent;
            }
        </style>
    </head>
    <body>
        <nav>
            <div class="nav-wrapper" style="background-color: #346786;">
                <a href="index.jsp" class="brand-logo right"><img src="Imagens/unesp-logo2-branco.png" alt="Logo Unesp" style="width: 90px;"/></a>
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
            <label id="content-title"><b>Planilha Complemento de Adendo</b></label>
            
            <!--<div class="progress">
                <div class="indeterminate"></div>
            </div>-->

<<<<<<< HEAD
            <div class="row" style="height: 200px;">
=======
            <div class="row" id="bar">
>>>>>>> 5402ea68c6e18978b3bb0c1fafc837fae3a5de57
                <div class="col s12">
                    <ul class="tabs tabs-fixed-width z-depth-1" style="height: 55px;">
                        <li class="tab"><a href="#test-swipe-1" style="color: blue;">AGD</a></li>
                        <li class="tab"><a href="#test-swipe-2" style="color: blue;">Test 2</a></li>
                        <li class="tab"><a href="#test-swipe-3" style="color: blue;">Test 3</a></li>
                        <li class="tab"><a href="#test8" style="color: blue;">Test 4</a></li>
                        <li class="tab"><a href="#test9" style="color: blue;">Test 5</a></li>
                        <li class="tab"><a href="#test10" style="color: blue;">Disabled Tab</a></li>
                        <li class="tab"><a href="#test11" style="color: blue;">Test 7</a></li>
                        <li class="tab"><a href="#test12" style="color: blue;">Test 8</a></li>
                        <li class="tab"><a href="#test13" style="color: blue;">Test 9</a></li>
                        <li class="tab"><a href="#test14" style="color: blue;">Disabled Tab</a></li>
                        <li class="tab"><a href="#test15" style="color: blue;">Test 11</a></li>
                        <li class="tab"><a href="#test5" style="color: blue;">Test 1</a></li>
                        <li class="tab"><a href="#test6" style="color: blue;">Test 2</a></li>
                        <li class="tab"><a href="#test7" style="color: blue;">Test 3</a></li>
                        <li class="tab"><a href="#test8" style="color: blue;">Test 4</a></li>
                        <li class="tab"><a href="#test9" style="color: blue;">Test 5</a></li>
                        <li class="tab"><a href="#test10" style="color: blue;">Disabled Tab</a></li>
                        <li class="tab"><a href="#test11" style="color: blue;">Test 7</a></li>
                        <li class="tab"><a href="#test12" style="color: blue;">Test 8</a></li>
                        <li class="tab"><a href="#test13" style="color: blue;">Test 9</a></li>
                        <li class="tab"><a href="#test14" style="color: blue;">Disabled Tab</a></li>
                        <li class="tab"><a href="#test15" style="color: blue;">Test 11</a></li>
                    </ul>
                    <div>
                        <div id="test-swipe-1" class="col s12">
                            <div id="table-wrapper">
                                <div id="table-scroll">
                                    <p style="width: 100%; color: blue; font-size: 150%;"><b>AULAS</b></p>
                                    <p style="width: 100%; color: blue; font-size: 100%;"><b>GRADUAÇÃO</b></p>
                                    
                                    <div class="row" style="width: 100%; overflow: hidden;">
                                        <div class="input-field col s2" style="float: left; margin-left: 30%;">
                                            <input value="5151" id="numero_disciplinas" type="text" readonly="">
                                            <label class="active" for="numero_disciplinas" style="color: black;" >Número de Disciplinas</label>
                                        </div>
                                        
                                        <div style="width:100%;margin-left: 70%;">
                                            <div class="input-field col s2" style="float: left;">
                                                <input value="5151" id="carga_horaria_total" type="text" readonly="">
                                                <label class="active" for="carga_horaria_total" style="color: black;">Carga Horaria Total</label>
                                            </div>
                                            
                                            <div class="input-field col s2" style="float: left;">
                                                <input value="5151" id="total_alunos" type="text" readonly="">
                                                <label class="active" for="total_alunos" style="color: black;">Total de Alunos</label>
                                            </div>
                                        </div>
                                        
                                    </div>
                                    
                                    <table class="centered highlight responsive-table" id="tabela_curriculo">
                                        <tbody>
                                            <tr id="tabela_curriculo-tr">
                                                scasca
                                            </tr>
                                            <tr>
                                                ascjac
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div id="test-swipe-2" class="col s12">Test 2</div>
                        <div id="test-swipe-3" class="col s12">Test 3</div>
                    </div>
                </div>
            </div>
         </div>
                
        <script src="JS/jquery-3.4.1.js" type="text/javascript"></script>
        <script src="JS/materialize.js" type="text/javascript"></script>
        <script src="JS/materialize.min.js" type="text/javascript"></script>
        <script src="JS/planilha.js" type="text/javascript"></script>
   </body>
</html>