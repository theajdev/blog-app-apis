pipeline{
    agent any
    tools{
        maven 'mvn-3.8.7'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/theajdev/blog-app-apis']])
                sh 'mvn install:install-file -Dfile=./lib/libs/log.jar -DgroupId=log -DartifactId=log -Dversion=1.0 -Dpackaging=jar'
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t ajaykamble10/blog-app-apis .'
                }
            }
        }
        stage('Push image to hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'docker-cli-login', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u ajaykamble10 -p ${dockerhubpwd}'  
                    sh 'docker push ajaykamble10/blog-app-apis' 
                  }
               }
            }
        }
    }
}
