pipeline{
    agent any
    stages{
        stage("build & test"){
            steps{
                echo "======== ${env.STAGE_NAME} ========"
                sh "./gradlew clean build"
            }
        }
        stage("sonar"){
            steps{
                script {
                    def scannerHome = tool 'sonar-scanner';
                    withSonarQubeEnv('sonarcube-server') { // If you have configured more than one global server connection, you can specify its name
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                    }
                }
            }
        }
        stage("run"){
            steps{
                echo "======== ${env.STAGE_NAME} ========"
                sh "nohup ./gradlew bootRun"
            }
        }
        stage("test"){
            steps{
                echo "======== ${env.STAGE_NAME} ========"
                sh "curl -X GET 'http://localhost:8181/rest/mscovid/test?msg=testing'"
            }
        }
        stage("nexus"){
            steps{
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
    }
}