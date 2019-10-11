<%-- 
    Document   : login
    Created on : Sep 28, 2019, 5:37:20 PM
    Author     : Luciano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "logica.controladores.Fabrica" %>
<%@page import = "logica.controladores.IControladorUsuario"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <title>Iniciar sesion en uyTube</title>
            <%
                Fabrica f = Fabrica.getInstance();
                IControladorUsuario user = f.getIControladorUsuario();
                
                Boolean formInput = false;
                
                
                if (request.getParameter("user") != null) {
                    if (request.getParameter("password") != null) {
                        Integer _id = user.LoginUsuario(request.getParameter("user"), request.getParameter("password"));
                        if (_id != null) {
                            session.setAttribute("userid",_id);
                            session.setAttribute("user_dt", user.ConsultarUsuario(_id));
                            String redirectURL = "index.jsp";
                            response.sendRedirect(redirectURL);
                        }
                    }
                    formInput = true;
                }
            %>
    </head>
    <body>
        <main class="login-form">
            <div class="cotainer">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <h1>uyTube v2</h1>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Iniciar sesion</div>
                            <div class="card-body">
                                <form action="" method="">
                                    <div class="form-group row">
                                        <label for="user" class="col-md-4 col-form-label text-md-right">Usuario</label>
                                        <div class="col-md-6">
                                            <input type="text" id="user" class="form-control" name="user" required autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Contraseña</label>
                                        <div class="col-md-6">
                                            <input type="text" id="password" class="form-control" name="password" required>
                                        </div>
                                    </div>

                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            Iniciar sesion
                                        </button>
                                    </div>
                                    
                                    <% if (formInput) { %>
                                            <div class="alert alert-danger" role="alert">
                                                El usuario o la contraseña no son correctos. Intente nuevamente.
                                            </div>
                                        <%}
                                    %>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>

        </main>
    </body>
</html>
