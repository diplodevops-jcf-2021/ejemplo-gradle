/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
    stage("Compile") {
        STAGE = env.STAGE
        sh "./mvnw clean compile -e"    
    }
    stage("Test") {
        STAGE = env.STAGE
        sh "./mvnw clean test -e"    
    }
    stage("Package") {
        STAGE = env.STAGE
        sh "./mvnw clean package -e"    
    }
    stage("Run") {
        STAGE = env.STAGE
        sh "nohup bash mvnw spring-boot:run &"
        sleep 20
    }
    stage("Test-app") {
        STAGE = env.STAGE
        sh "curl -X GET 'http://localhost:8181/rest/mscovid/test?msg=porfin'"
    }
}

return this;