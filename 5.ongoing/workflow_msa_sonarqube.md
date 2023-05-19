
# sonarqube install

sudo docker pull sonarqube:lts-community

sudo docker run -d --restart=always --name sonarqube -p 9000:9000 sonarqube:lts-community
http://[설치한 컴퓨터의 IP 주소]:9000 에 접속

-
http://10.101.0.104:9000/
id: admin
pw: tmdgP0425ab!
squ_4885a13cd7b49229a6ae3f52fa0168d14e13c750

sqa_6a51461b21cdc68c975367afc26bceb86e4b3aad

edu-msa-sonarqube
sonarqube
jenkins-sonarqube-token
-
jenkins home

1. sonarqube 환경설정
	- jenkins-> global configuration -> sonarqube scanner 등록
	- jenkins-> system 설정 -> sonarqube server 등록

sonar.login=b5b113ea36416fddf79f214bb6694cb902798277 
sonar.projectKey=always-try  
sonar.projectName=always-try   
sonar.host.url=http://localhost:9000/    
sonar.projectVersion=1.0
sonar.sourceEncoding=UTF-8

http://10.101.0.145//job/sonarqube/build?token=jenkins-sonarqube-token

---
ERROR: SonarQube installation defined in this job (http://10.101.0.104:9000) does not match any configured installation. Number of installations that can be configured: 1.
: server/scanner에 지정한 이름이 같아야 함. 그리고 server url를 적는게 아니라 지정한 이름을 적는거임

```groovy
sonarqube pipeline

pipeline {
    agent any
    stages {
        stage('SCM') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/seunghyejeong/gitops-repository.git']]])
            }
        }
        stage('Build && SonarQube analysis') {
            steps {
                withSonarQubeEnv('http://10.101.0.104:9000') {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
```

```groovy
각 프로젝트 Jenkinsfile

pipeline {
  agent any

  tools {
    maven 'maven' 
  }

  stages {
    stage('Checkout Application Git Branch') {
      steps {
        git url: 'https://github.com/seunghyejeong/gitops-repository.git', branch: 'main'
      }
      post {
        failure {
          echo 'Failed to clone repository'
        }
        success {
          echo 'Repository cloned successfully'
        }
      }
    }

    stage('Maven Jar Build') {
      steps {
        sh 'mvn -f /var/jenkins_home/workspace/msa-project/edu-msa-board-master/pom.xml clean package'
      }
      post {
        failure {
          echo 'Failed to build Maven'
        }
        success {
          echo 'Maven war built successfully!'
        }
      }
    }

    stage('SonarQube Analysis') {
      steps {
        withSonarQubeEnv('sonarqube') { // management에 추가한 sonarqube scanner 이름 
          script {
            def scannerHome = tool 'SonarScanner'
            "${scannerHome}/bin/sonar-scanner"
          }
        }
      }
    }

    stage('Docker Login') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'docker-token', passwordVariable: 'tlqkfak0315!', usernameVariable: 'seunghyejeong')]) {
          sh 'docker login -u seunghyejeong -p tlqkfak0315!'
        }   
      }
    }

    stage('Deploy our image') { 
      steps { 
        script {
          sh 'cd /var/jenkins_home/workspace/edu-msa-board/edu-msa-board-master && docker build -t edu-msa-board-master .'
          sh 'docker tag edu-msa-board-master seunghyejeong/edu-msa-board-master:latest'
          sh 'docker push seunghyejeong/edu-msa-board-master:latest'
        } 
      }
    }
  }
}
```


---
[ERROR] The goal you specified requires a project to execute but there is no POM in this directory (/var/jenkins_home/workspace/sonarqube). Please verify you invoked Maven from the correct directory. -> [Help 1]
[ERROR] 


exsist Jenkinsfile for sonarcube in root dir and array 
```groovy 
def projects = [
  'edu-msa-board-master',
  'edu-msa-comment-master',
  'edu-msa-file-master',
  'edu-msa-ui-master',
  'edu-msa-user-master'
]

pipeline {
  agent any
  tools {
    maven 'maven' 
  }

  stages {
    stage('Checkout Projects') {
      steps {
        git url: 'https://github.com/seunghyejeong/gitops-repository.git', branch: 'main'
      }
    }

    stage('SonarQube Analysis') {
      steps {
        script {
       	 withCredentials([
          string(credentialsId: 'jenkins-sonarqube', variable: 'SONAR_TOKEN')
       	 ]) {
          for (def project in projects) {
            dir(project) {
              withSonarQubeEnv('sonarqube') {
                sh 'mvn clean package sonar:sonar'
  }
              }
            }
          }
        }
      }
    }
  }
}
```