/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
    stage("build & test"){
        STAGE = env.STAGE
        echo "======== ${env.STAGE_NAME} ========"
        sh "./gradlew clean build"
    }
    stage("sonar"){
        STAGE = env.STAGE
        script {
            def scannerHome = tool 'sonar-scanner';
            withSonarQubeEnv('sonarcube-server') { // If you have configured more than one global server connection, you can specify its name
                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
            }
        }
    }
    stage("run"){
        STAGE = env.STAGE
        echo "======== ${env.STAGE_NAME} ========"
        sh "nohup ./gradlew bootRun &"
        sh "sleep 10"
    }
    stage("test"){
        STAGE = env.STAGE
        echo "======== ${env.STAGE_NAME} ========"
        sh "curl -X GET 'http://localhost:8181/rest/mscovid/test?msg=testing'"
    }
    stage("nexus"){
        STAGE = env.STAGE
        echo "======== ${env.STAGE_NAME} ========"
        nexusPublisher nexusInstanceId: 'test-nexus',
        nexusRepositoryId: 'ejemplo-gradle',
        packages: [
            [
                $class: 'MavenPackage',
                mavenAssetList: [
                    [classifier: '', extension: '', filePath: "${env.WORKSPACE}/build/libs/DevOpsUsach2020-0.0.1.jar"]
                ],
                mavenCoordinate: [
                    artifactId: 'DevOpsUsach2020',
                    groupId: 'com.devopsusach2020',
                    packaging: 'jar',
                    version: '0.0.1'
                ]
            ]
        ]
    }
}

return this;