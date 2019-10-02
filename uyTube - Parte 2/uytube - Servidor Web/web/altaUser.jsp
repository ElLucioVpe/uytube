<%-- 
    Document   : altaUser
    Created on : Sep 28, 2019, 5:28:28 PM
    Author     : pagol
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        <title>Crear usuario</title>
        
            <%
                Fabrica f = Fabrica.getInstance();
                IControladorUsuario user = f.getIControladorUsuario();
                
                Boolean formInput = false;
                
                if (request.getParameter("user") != null) {
                    if (request.getParameter("password") != null) {
                        //Integer _id = user.LoginUsuario(request.getParameter("user"), request.getParameter("password"));
                      //Date date = SimpleDateFormat.parse(request.getParameter("user"));
                      //Pasarlo a Date me tira problema, fixeando eso.
                      //user.AltaUsuario(request.getParameter("user"), request.getParameter("password"), request.getParameter("name"),request.getParameter("apellido"), request.getParameter("mail"), SimpleDateFormat.parse(request.getParameter("fecha")), request.getParameter("img"));
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
                        <h1>uyTube</h1>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Crear User</div>
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
                                            <input type="password" id="password" class="form-control" name="password" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="name" class="col-md-4 col-form-label text-md-right">Nombre</label>
                                        <div class="col-md-6">
                                            <input type="text" id="password" class="form-control" name="name" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="apellido" class="col-md-4 col-form-label text-md-right">Apellido</label>
                                        <div class="col-md-6">
                                            <input type="text" id="password" class="form-control" name="apellido" required>
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="form-group row">
                                        <label for="mail" class="col-md-4 col-form-label text-md-right">Mail</label>
                                        <div class="col-md-6">
                                            <input type="text" id="password" class="form-control" name="mail" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="fecha" class="col-md-4 col-form-label text-md-right">Fecha Nacimiento</label>
                                        <div class="col-md-6">
                                            <input type="text" id="password" class="form-control" name="fecha" required>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group row">
                                        <label for="img" class="col-md-4 col-form-label text-md-right">Img</label>
                                        <div class="col-md-6">
                                            <input type="text" id="password" class="form-control" name="img" required>
                                        </div>
                                    </div>

                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            Crear
                                        </button>
                                    </div>
                                    
                                    <%
                                        if (formInput) { %>
                                            <div class="alert alert-danger" role="alert">
                                                Hay un campo vacio
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