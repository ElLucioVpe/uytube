<%-- 
    Document   : uploadFile
    Created on : Oct 4, 2019, 1:56:31 AM
    Author     : pagol
--%>

<%@page import="java.lang.System.Logger.Level"%>
<%@page import="org.jboss.logging.Logger"%>
<%@page import="java.text.ParseException"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="logica.controladores.IControladorUsuario"%>
<%@page import="logica.controladores.Fabrica"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLClassLoader"%>
<%@page import="java.util.Locale"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="include/header.jsp" %>  
        <title>Te has registrado!! - uyTube</title>

        <%
   File file ;
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;

   //Relative Path
   ServletContext context = pageContext.getServletContext();
   File contextBasePath = new File(getServletContext().getRealPath(""));
   File path2 = new File(contextBasePath.getParent()).getParentFile();
   String pathFolder = path2.getParent();
   //Adding Path
   //String filePath = context.getInitParameter("file-upload");
   File properties = new File(System.getProperty("catalina.home")+"/conf");
   URL[] urls = {properties.toURI().toURL()};
   ClassLoader loader = new URLClassLoader(urls);
   ResourceBundle bundle = ResourceBundle.getBundle("uytube_conf", Locale.getDefault(), loader);
   
   String filePath = bundle.getString("data")+"//imagenes//";
   
   // Verify the content type
   String contentType = request.getContentType();
   if ((contentType.indexOf("multipart/form-data") >= 0)) {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      
      // Location to save data that is larger than maxMemSize.
       factory.setRepository(new File("//temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
      try { 
          
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Alta User Finalizado</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("</br>");
         
         
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);

         // Process the uploaded file items
         Iterator i = fileItems.iterator();
         Iterator iP = fileItems.iterator();
         
         FileItem fP = (FileItem)iP.next();
         
         String fileName="";
        
         if(fP.getName().isEmpty()){
             
         //out.println("No Image");
     
         }else{
         while ( i.hasNext () ) {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ) {
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();
               //String fileName = fi.getName();
               fileName = (String)session.getAttribute("userx")+".jpg";
               //String fileName = username;
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            
               // Write the file
               if( fileName.lastIndexOf("//") >= 0 ) {
                  file = new File( filePath + 
                  fileName.substring( fileName.lastIndexOf("//"))) ;
               } else {
                  file = new File( filePath + 
                  fileName.substring(fileName.lastIndexOf("//")+1)) ;
               }
               fi.write( file ) ;
               //out.println("Uploaded Filename: " + filePath + fileName + "<br>");
            }
         }
         
         }
         
                 
                 //User sets
                 String userUp = (String)session.getAttribute("userx");
                 String pswdUp = (String)session.getAttribute("pswdx");
                 String nameUp = (String)session.getAttribute("namex");
                 String apellidoUp = (String)session.getAttribute("apellidox");
                 String mailUp = (String)session.getAttribute("mailx");
                 String fechaUp = (String)session.getAttribute("fechax");
                 int IdUsuarioCreate = -1;
                 
                 
                 //Canal sets
                 String canalUp = (String)session.getAttribute("canalx");
                 String descUp = (String)session.getAttribute("descx");
                 String visibility = (String)session.getAttribute("visx");
                 String catUp = (String)session.getAttribute("catx");
                 Boolean visUp = true;
                 
                 if(visibility.contains("privado")){
                 visUp=true;
                 }
                 if (visibility.contains("publico")){
                 visUp=false;
                 }
                 
                 
                 
                 //Alta User
                 Fabrica f = Fabrica.getInstance();
                 IControladorUsuario user = f.getIControladorUsuario();
              
                 SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                 java.util.Date fechaNacimiento = sdf.parse(fechaUp); 
                    
                 user.AltaUsuario(userUp, pswdUp, nameUp,apellidoUp, mailUp, fechaNacimiento, fileName);  
                    
                 IdUsuarioCreate = user.obtenerIdUsuario(userUp);
                     
                 if(IdUsuarioCreate!=-1){out.println(descUp);
                 	user.AltaCanal(canalUp, visUp, catUp, IdUsuarioCreate, descUp);
                 }
                 
           
         
         /*
         out.println(userUp); 
         out.println(pswdUp);
         out.println(canalUp);
         out.println(nameUp); 
         out.println(apellidoUp); 
         out.println(mailUp); 
         out.println(fechaNacimiento.toString()); 
         out.println(fileName);  
         out.println(IdUsuarioCreate); 
         
         out.println(visUp);
         out.println(visibility);
         out.println((String)session.getAttribute("catx"));
         
         out.println(catUp);
         out.println(descUp);
         out.println(filePath);
         */
        
        
         out.println("User creado con exito!");
         out.println("</body>");
         out.println("</html>");
      } catch(Exception ex) {
         System.out.println(ex);
      }
   } else {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Alta User Error</title>");  
      out.println("</head>");
      out.println("<body>");
      out.println("<p>Problema en el alta</p>"); 
      out.println("</body>");
      out.println("</html>");
   }
%>

            
    </head>
    <body>
    </body>
</html>
