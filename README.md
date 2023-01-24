![N0n0gMr](https://user-images.githubusercontent.com/103458457/210388718-498ba1f9-cd2a-4615-b689-8ead59e06baa.png)


In order to use this website you need to: 
- Install Java and Maven 
```sudo apt install default-jdk && sudo apt install maven```
- Install MySQLWorkbench 
```sudo apt install mysql-workbench-community```
- Import the db folder into MySQLWorkbench (you can import it into MySQL Server as well) 
- Import the project in VSCode and install the extensions for Tomcat Server (the extension will let you install the server as well) 
- Open a terminal in the project folder and type ```mvn clean package``` in the terminal
- Deploy the ```demo.war``` file in Tomcat and launch the website
