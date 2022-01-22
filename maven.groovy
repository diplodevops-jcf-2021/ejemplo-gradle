/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
    stage("Compile") {
        steps {
            sh "./mvnw clean compile -e"    
        }
    }
    stage("Test") {
        steps {
            sh "./mvnw clean test -e"    
        }
    }
    stage("Package") {
        steps {
            sh "./mvnw clean package -e"    
        }
    }
    stage("Run") {
        steps {
            sh "nohup bash mvnw spring-boot:run &"
            sleep 20
        }
    }
    stage("Test-app") {
        steps {
            sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=porfin'"
        }
    }
}

return this;