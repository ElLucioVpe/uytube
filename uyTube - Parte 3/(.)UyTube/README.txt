Para utilizar cualquiera de los programas primero debera crear la base de datos "uytube" en mysql, ademas de en caso de ser necesario
crear el usuario "uytubeAdmin", de contraseña "admin123" con permisos totales sobre esta.

Ademas siga las siguientes instrucciones para configurar el servidor web y el central:

En las pc de ambos servidores (o en una en caso de estar los dos en la misma) posicione la carpeta /.Uytube en su carpeta de usuario (home), ahi encontrara los archivos .properties.

Edite el archivo "uytube_conf.properties" y coloque ahi los datos de configuración necesarios, ejemplo:

url=192.168.1.21:8080/UyTube/
url_images=http://192.168.1.21:8080/UyTube/images/
data=/home/user/.UyTube/data
images=/home/user/.UyTube/data/imagenes

Lo mismo en el archivo "uytube_webservices", ejemplo:

serviceIP=192.168.1.21
serviceUsuario=:9990/ws/cUsuario
serviceVideo=:9991/ws/cVideo
serviceCategoria=:9992/ws/cCategoria

Entre a la ruta de tomcat, en unix "/opt/tomcat/latest".

Coloque el archivo "UyTube.war" en \webapps.

En dicho lugar en donde tenga el servidor central vaya a \conf y edite el archivo server.xml agregando la siguiente linea dentro del localhost:
<Context  docBase="<ruta de la carpeta data>\imagenes" path="/images"/>

De modo que quede de la siguiente manera:
<Host name="localhost"  appBase="webapps"unpackWARs="true" autoDeploy="true">
	....
	<Context  docBase="<ruta de la carpeta de imagenes>" path="/images"/>
	....
</Host>	

Ahora deberia poder entrar al manager de tomcat (localhost:8080/manager/html) y ejecutar la aplicación 
/Uytube.
