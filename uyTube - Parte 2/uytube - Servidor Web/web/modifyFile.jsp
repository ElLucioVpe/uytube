<%-- 
    Document   : ModifyFile
    Created on : Oct 4, 2019, 1:56:31 AM
    Author     : pagol
--%>

<%@page import="logica.dt.UsuarioDt"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <%
   File file ;
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
   
   
    //User sets
                 String userUp = (String)session.getAttribute("userx");
                 String pswdUp = (String)session.getAttribute("pswdx");
                 String nameUp = (String)session.getAttribute("namex");
                 String apellidoUp = (String)session.getAttribute("apellidox");
                 String mailUp = (String)session.getAttribute("mailx");
                 String fechaUp = (String)session.getAttribute("fechax");
                // Date FechaReal =(Date)session.getAttribute("fechax");
            
                 //Si viene directo de la bsd y no toco el datepicker puede estar en formato  - - - y si toco el datepicker / / /
                 //Entonces
                
                 java.util.Date fechaNacimiento = null;
                 if(fechaUp.contains("-")){
                    fechaUp=fechaUp.replace("-", "/");
                 
                 }  
                 
                 SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                 fechaNacimiento = sdf.parse(fechaUp);
                 
                 int _id = (Integer)session.getAttribute("userid");
                 
                 
                 //Canal sets
                 String descUp = (String)session.getAttribute("descx");
                 String visibility = (String)session.getAttribute("visx");
                 String catUp = (String)session.getAttribute("catx");
                 Boolean visUp = true;
                 
   
   //Relative Path
   ServletContext context = pageContext.getServletContext();
   File contextBasePath = new File(getServletContext().getRealPath(""));
   File path2 = new File(contextBasePath.getParent()).getParentFile();
   String pathFolder = path2.getParent();

   //Adding Path
   //String filePath = context.getInitParameter("file-upload");
   String filePath = pathFolder+"\\data\\imagenes\\";

   // Verify the content type
   String contentType = request.getContentType();
   
   if ((contentType.indexOf("multipart/form-data") >= 0)) {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
      
      try { 
          
         out.println("<html>");
         out.println("<head>");
         out.println("<title>JSP File upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("</br>");
          
         out.println(fechaUp);
         out.println("</br>");
         out.println(fechaNacimiento);
         out.println("</br>");
         //out.println(FechaReal);
         
         
         //if(upload.par)
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);

         // Process the uploaded file items
         Iterator i = fileItems.iterator();
         Iterator iP = fileItems.iterator();
         
         FileItem fP = (FileItem)iP.next();
         
        
         if(fP.getName().isEmpty()){
             
         out.println("No Image");
     
         }else{
         
         String fileName="";
         fileName = (String)session.getAttribute("userx")+".jpg";
         while ( i.hasNext () ) {
             
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ) {
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();
               //String fileName = username;
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            
               // Write the file
               if( fileName.lastIndexOf("\\") >= 0 ) {
                  file = new File( filePath + 
                  fileName.substring( fileName.lastIndexOf("\\"))) ;
               } else {
                  file = new File( filePath + 
                  fileName.substring(fileName.lastIndexOf("\\")+1)) ;
               }
               fi.write( file ) ;
               out.println("Uploaded Filename: " + filePath + 
               fileName + "<br>");
            }
         }
         
         }
         
               
                
                 
                 if(visibility.contains("privado")){
                 visUp=true;
                 }
                 if (visibility.contains("publico")){
                 visUp=false;
                 }

                 //Alta User
                    Fabrica f = Fabrica.getInstance();
                    IControladorUsuario user = f.getIControladorUsuario();
              
              
                  
                         
                     user.ModificarUsuario(_id,pswdUp, nameUp, apellidoUp, fechaNacimiento, catUp, descUp, visUp);
                     
                
                 
           
         
         
         out.println(userUp);
         out.println(pswdUp);
         out.println(nameUp); 
         out.println(apellidoUp); 
         out.println(mailUp); 
         
         out.println(visUp);
         out.println(visibility);
         out.println((String)session.getAttribute("catx"));
         
         out.println(catUp);
         out.println(descUp);
         
         
         out.println("</body>");
         out.println("</html>");
      } catch(Exception ex) {
         System.out.println(ex);
      }
   } else {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet upload</title>");  
      out.println("</head>");
      out.println("<body>");
      out.println("<p>No file uploaded</p>"); 
      out.println("</body>");
      out.println("</html>");
   }
%>

            
    </head>
    <body>
        
        User Modificado!
                            
    </body>
</html>
