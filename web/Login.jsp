<%-- 
    Document   : Login
    Created on : 09/12/2019, 08:56:24
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link href="CSS/login.css" rel="stylesheet" type="text/css"/>
        <title>Login</title>
        <link rel="icon" type="image/png" href="Imagens/unesp-logo3.png" sizes="16x16">
    </head>

    <body>
        <nav>
            <div class="nav-wrapper">
                <a href="index.jsp" class="brand-logo right">
                    <img src="Imagens/unesp-logo2-branco.png" alt="Logo Unesp" style="width: 90px;"/>
                </a>
                <ul id="nav-mobile" class="left hide-on-med-and-down">
                </ul>
                <div class="brand-logo center" style="font-size: 25px"></div>
            </div>
        </nav>
        
        <div class="section"></div>
        <main>
            <center>
                <div class="section"></div>

                
                <div class="section"></div>

                <div class="container">
                    
                    <div class="z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

                        <form class="col s12" action="svlLogin" id="login" name="login">
                            <h5 class="indigo-text">Faça o login para continuar</h5>
                            <div class='row'>
                                <div class='col s12'>
                                </div>
                            </div>

                            <div class='row'>
                                <div class='input-field col s12'>
                                    <i class="material-icons prefix">account_circle</i>
                                    <input class='validate' type='email' name='email' id='email'/>
                                    <label for='email'>Email</label>
                                </div>
                            </div>

                            <div class='row'>
                                <div class='input-field col s12'>
                                    <i class="material-icons prefix">https</i>
                                    <input class='validate' type='password' name='password' id='password'/>
                                    <label for='password'>Senha</label>
                                </div>
                                <label style='float: left;'>
                                    <a class='blue-text' href='#!'><b>Esqueceu seu Email?</b></a>
                                </label>
                                <label style='float: right;'>
                                    <a class='blue-text' href='#!'><b>Esqueceu sua Senha?</b></a>
                                </label>
                            </div>

                            <br />
                            <center>
                                <div class='row'>
                                    <button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect' style="background-color: #346786;">Login</button>
                                </div>
                            </center>
                        </form>
                    </div>
                </div>
                <a href="#!">Primeiro Acesso</a>
            </center>

            <div class="section"></div>
            <div class="section"></div>
        </main>

        <script src="JS/login.js" type="text/javascript"></script>
        
    </body>
</html>