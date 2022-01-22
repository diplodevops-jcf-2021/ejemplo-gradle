pipeline{
    agent any

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
}