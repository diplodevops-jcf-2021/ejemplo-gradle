pipeline{
    agent any
    
    environment {
	    STAGE = ''
	}
    
    parameters {
        choice choices: ['gradle', 'maven'], description: 'Indicar herramienta construcción', name: 'buildTool'
    }

    stages{
        stage("Pipeline"){
            steps{
                script{
                    filename = params.buildTool == "maven" ? 'maven.groovy' : 'gradle.groovy'
	                echo filename
	                def ejecucion = load filename
	                ejecucion.call()
                }
            }
        }
    }
    post {
        failure {
            slackSend message: "[Javier Contreras][${env.JOB_NAME}][${params.buildTool}] Ejecución fallida en stage [${STAGE}]."
        }
        success {
            slackSend message: "[Javier Contreras][${env.JOB_NAME}][${params.buildTool}] Ejecución exitosa."

        }
    }
}