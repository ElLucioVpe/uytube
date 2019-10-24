Para utilizar cualquiera de los programas primero debera crear la base de datos "uytube" en mysql, ademas de en caso de ser necesario
crear el usuario "uytubeAdmin", de contraseña "admin123" con permisos totales sobre esta.

Ademas siga las siguientes instrucciones para configurar el servidor web y la ruta de imagenes:

Edite el archivo "uytube_conf.properties" poniendo en la variable "data" la localizacion de la carpeta data, ejemplo:
data=/home/user/uytube/data

Entre a la ruta de tomcat, por ejemplo:
C:\Program Files\Apache Software Foundation\Tomcat 9.0
	o
/opt/tomcat/latest

Coloque el archivo "UyTube.war" en \webapps.

Luego vaya a \conf coloque el archivo "uytube_conf.properties" ahi y edite el archivo server.xml agregando la siguiente linea dentro del localhost:
<Context  docBase="<ruta de la carpeta data>\imagenes" path="/images"/>

De modo que quede de la siguiente manera:
<Host name="localhost"  appBase="webapps"unpackWARs="true" autoDeploy="true">
	....
	<Context  docBase="<ruta de la carpeta data>\imagenes" path="/images"/>
	....
</Host>	

Ahora deberia poder entrar al manager de tomcat (localhost:8080/manager/html) y ejecutar la aplicación 
/Uytube.
