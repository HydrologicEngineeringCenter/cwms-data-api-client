# Generating Updated Swagger YAML for CDAC

## Steps
1.	Run CDA with your updated endpoints (ensure your new endpoints are present)
2.	Go to Swagger docs (usually located somewhere like this: http://localhost:7001/cwms-data/swagger-docs)
3.	Copy all JSON from page, paste it into [Swagger Editor](https://editor.swagger.io/)
4.	Copy formatted YAML from Swagger Editor, paste it into the [CWMS Radar Swagger YAML](cwms-radar-swagger.yaml) file in CDAC cwms-radar-model
5.	Run Gradle task “clean” from build task folder
6.	Run Gradle task `./gradlew generateSwaggerCodeCwmsRadar` from build task folder
7.	Select the file tree and reload from disk (ensure to include the build folder in the “cwms-radar-model” project)
8.	Locate newly generated objects in 
        “cwms-radar-model/build/swagger-code-cwmsRadar/src/main/java/mil/army/usace/hec/cwms/radar/client/model/”
