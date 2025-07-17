# Generating Updated Swagger YAML for CDAC

## Steps
1.	Run CDA with your updated endpoints (ensure your new endpoints are present)
2.	Go to Swagger docs (usually located somewhere like this: http://localhost:7001/cwms-data/swagger-docs)
3.	Copy all JSON from page, paste it into [Swagger Editor](https://editor.swagger.io/)
4.	Copy formatted YAML from Swagger Editor, paste it into the [CWMS Data API Swagger YAML](cwms-data-api-swagger.yaml) file in CDAC cwms-data-api-model
5.	Run Gradle task “clean” from build task folder
6.	Run Gradle task `./gradlew generateSwaggerCodeCwmsDataApi` from build task folder
7.	Select the file tree and reload from disk (ensure to include the build folder in the “cwms-data-api-model” project)
8.	Locate newly generated objects in 
        “cwms-data-api-model/build/swagger-code-cwmsDataApi/src/main/java/mil/army/usace/hec/cwms/data/api/client/model/”
