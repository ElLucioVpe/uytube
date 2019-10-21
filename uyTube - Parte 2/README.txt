Para utilizar cualquiera de los progrmas primero debera iniciar la base de datos ejecutando
runUyTubeServer.bat, que debe estar en la misma carpeta que hsqldb.jar.

Ademas siga las siguientes instrucciones para configurar la ruta de imagenes del servidor:

Entre a la ruta de tomcat, por ejemplo:
C:\Program Files\Apache Software Foundation\Tomcat 9.0

Vaya a \conf y edite el archivo server.xml y agregue la siguiente linea entro del localhost:
<Context  docBase="<ruta al archivo .jar>\data\imagenes" path="/images"/>

De modo que quede de la siguiente manera:
<Host name="localhost"  appBase="webapps"unpackWARs="true" autoDeploy="true">
	....
	<Context  docBase="<ruta al archivo .jar>\data\imagenes" path="/images"/>
	....
</Host>	