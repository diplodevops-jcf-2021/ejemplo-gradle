/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
    stage("Compile") {
        sh "./mvnw clean compile -e"    
    }
    stage("Test") {
        sh "./mvnw clean test -e"    
    }
    stage("Package") {
        sh "./mvnw clean package -e"    
    }
    stage("Run") {
        sh "nohup bash mvnw spring-boot:run &"
        sleep 20
    }
    stage("Test-app") {
        sh "curl -X GET 'http://localhost:8181/rest/mscovid/test?msg=porfin'"
    }
}

return this;