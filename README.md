# REST-API-using-Spring-Boot-Maven

Project details:
The project fetches the JSON data from given link and export the data into TSV file. The JSON data parsing and deserialization is done using
GSON library. The TSV data file will be stored in project folder only. The data will be imported from the tsv file to serve the two 
REST GET requests.

Requirements for code execution:
> JAVA 1.8
> Maven 3.3.9

Instructions to execute the code:
> Extract the zip file in your local system
> Open the Command prompt/ Terminal and go to path of the extracted project folder
> run the command 'mvn spring-boot:run'
> wait for the spring boot application to run. The tomcat server will start at the port 8080. 
> The Rest server is now listening at 'localhost:8080'
> I have implemented two functionalities which servers the data in json format
         > Get Oncogenes. REST endpoint - localhost:8080/proj/oncogenes
         > Get records having no entrezGeneId. REST endpoint -  localhost:8080/proj/missingEntrezId

