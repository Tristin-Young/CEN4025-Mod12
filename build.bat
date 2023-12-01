@echo on

REM Navigate to the Maven project directory
cd C:\user\trist\Desktop\Fall23\CEN4025\Assignment12\

REM Run Maven clean and package
mvn clean package

REM Check if the WAR file was created successfully
IF EXIST "target\Assignment5P2-1.0-SNAPSHOT.war" (
    REM Copy the WAR file to Tomcat's webapps directory
    copy "target\Assignment5P2-1.0-SNAPSHOT.war" C:\apache-tomcat-9.0.83\webapps\

    REM Navigate to Tomcat's bin directory and start the server
    cd C:\apache-tomcat-9.0.83\bin\
    call startup.bat
) ELSE (
    echo "Build failed: WAR file not found."
)
