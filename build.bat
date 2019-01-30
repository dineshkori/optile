title "Build Entire Project"
set PROJECT_HOME=C:\Users\DineshKori\githibfinal\optile
cd %PROJECT_HOME%


set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181
set MAVEN_HOME=CC:\Projects\Ecom_Dev_HardRock_FE\apache-maven-2.2.1
set PATH=%PATH%;%JAVA_HOME%\bin;%MAVEN_HOME%\bin;

echo %PATH%

echo "Building baseJobs Project"
call mvn -f %PROJECT_HOME%\baseJobs\pom.xml clean install package
echo "Building baseJobs Project"

echo "Building JobImplementor Project"
call mvn -f %PROJECT_HOME%\JobImplementor\pom.xml clean install package

echo "Building scheduler Project"
call  mvn -f %PROJECT_HOME%\scheduler\pom.xml clean install package

title "Running Scheduler Application"
call java -jar %PROJECT_HOME%\scheduler\target\scheduler-0.0.1-SNAPSHOT.jar