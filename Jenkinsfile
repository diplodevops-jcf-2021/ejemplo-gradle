pipeline{
    agent any

    parameters {
        choice choices: ['gradle', 'maven'], description: 'Indicar herramienta construcción', name: 'buildTool'
    }

    stages{
        stage("Pipeline"){
            steps{
                
                echo "======== ${env.STAGE_NAME} ========"
                script{
                    filename = params.buildTool == "maven" ? 'maven.groovy' : 'gradle.groovy'
	                def ejecucion = load filename
	                ejecucion.call()
                }
            }
        }
    }
}