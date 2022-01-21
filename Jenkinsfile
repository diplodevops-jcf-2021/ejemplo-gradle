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
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-maven -Dsonar.java.binaries=build"
                    }
                }
            }
        }
        stage("run"){
            steps{
                echo "======== ${env.STAGE_NAME} ========"
            }
        }
        stage("test"){
            steps{
                echo "======== ${env.STAGE_NAME} ========"
            }
        }
        stage("nexus"){
            steps{
                echo "======== ${env.STAGE_NAME} ========"
            }
        }
    }
}