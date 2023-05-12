workflow_msa_argocd


# argocd install

kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

- argocd CLI
curl -sSL -o argocd-linux-amd64 https://github.com/argoproj/argo-cd/releases/latest/download/argocd-linux-amd64
sudo install -m 555 argocd-linux-amd64 /usr/local/bin/argocd
rm argocd-linux-amd64

- 외부 노출을 위한 service tyupe 변경
kubectl patch svc argocd-server -n argocd -p '{"spec": {"type": "LoadBalancer"}}'

- password 확인
argocd admin initial-password -n argocd
hhmk8pE2fTkWNHtI

- login
1) CLI env must be able to commnicate with Argo CD API server. and using port fowarding thrugh this command
export ARGOCD_OPTS='--port-forward-namespace argocd'
argocd login ${NODE_IP}:${NODE_PORT}
argocd login 10.101.0.237:31874 

	```console
	E0511 01:10:33.187186 2156620 portforward.go:378] error copying from remote stream to local connection: readfrom tcp4 127.0.0.1:42025->127.0.0.1:41554: write tcp4 127.0.0.1:42025->127.0.0.1:41554: write: broken pipe
	```
	: 위의 ARGOCD_OPTS를 하면 안됨. 이미 NODEPORT로 연결해서 그런가.?.?
	`unset ARGOCD_OPTS`로 해제 후 재로그인
	
- repository add
kubectl config set-context --current --namespace=argocd

argocd app create guestbook --repo https://github.com/argoproj/argocd-example-apps.git --path guestbook --dest-server https://kubernetes.default.svc --dest-namespace default

repository를 등록하여 argocd로 관리할 수 있는 하나의 관리 창을 app이라 부름
- sync

argocd app get guestbook
```
Name:               argocd/guestbook
Project:            default
Server:             https://kubernetes.default.svc
Namespace:          default
URL:                https://10.101.0.237:31874/applications/guestbook
Repo:               https://github.com/argoproj/argocd-example-apps.git
Target:             
Path:               guestbook
SyncWindow:         Sync Allowed
Sync Policy:        <none>
Sync Status:        OutOfSync from  (53e28ff)
Health Status:      Missing

GROUP  KIND        NAMESPACE  NAME          STATUS     HEALTH   HOOK  MESSAGE
       Service     default    guestbook-ui  OutOfSync  Missing        
apps   Deployment  default    guestbook-ui  OutOfSync  Missing  
```
: Sync status: OutOfSync

argocd app sync guestbook
```
TIMESTAMP                  GROUP        KIND   NAMESPACE                  NAME    STATUS    HEALTH        HOOK  MESSAGE
2023-05-11T01:26:02+00:00            Service     default          guestbook-ui  OutOfSync  Missing              
2023-05-11T01:26:02+00:00   apps  Deployment     default          guestbook-ui  OutOfSync  Missing              

Name:               argocd/guestbook
Project:            default
Server:             https://kubernetes.default.svc
Namespace:          default
URL:                https://10.101.0.237:31874/applications/guestbook
Repo:               https://github.com/argoproj/argocd-example-apps.git
Target:             
Path:               guestbook
SyncWindow:         Sync Allowed
Sync Policy:        <none>
Sync Status:        Synced to  (53e28ff)
Health Status:      Progressing

Operation:          Sync
Sync Revision:      53e28ff20cc530b9ada2173fbbd64d48338583ba
Phase:              Succeeded
Start:              2023-05-11 01:26:02 +0000 UTC
Finished:           2023-05-11 01:26:02 +0000 UTC
Duration:           0s
Message:            successfully synced (all tasks run)

GROUP  KIND        NAMESPACE  NAME          STATUS  HEALTH       HOOK  MESSAGE
       Service     default    guestbook-ui  Synced  Healthy            service/guestbook-ui created
apps   Deployment  default    guestbook-ui  Synced  Progressing        deployment.apps/guestbook-ui created
```
: UI에서도 확인 가능

# todo for CD

1) yaml파일을 ${PROJECT_DIR}/kuberenetes/로 cp

```yaml
edu-msa-ui
apiVersion: apps/v1
kind: Deployment
metadata:
  name: edu-msa-ui
  labels:
    app: ui-msa
spec:
  replicas: 1
  selector:
    matchLabels:
      app: ui-msa
  template:
    metadata:
      labels:
        app: ui-msa
    spec:
      containers:
      - name: ui-msa
        image: seunghyejeong/edu-msa-ui-master:45
        imagePullPolicy: Always
        ports:
        - containerPort: 28080
      imagePullSecrets:
         - name: edu-msa-secret
---
apiVersion: v1
kind: Service
metadata:
  name: edu-msa-ui
  labels:
    app: ui-msa
spec:
  ports:
  - nodePort: ${EDU_MSA_UI}
    port: 28080
    protocol: TCP
    targetPort: 28080
  selector:
    app: ui-msa
  type: NodePort
```

+ portnum
edu-msa-ui 			${EDU_MSA_UI} 			30001
edu-msa-zuul		${EDU_MSA_ZUUL}			30101
edu-msa-board		${EDU_MSA_BOARD}		30201
edu-msa-comment		${EDU_MSA_COMMENT}		30301
edu-msa-user 		${EDU_MSA_USER}			30401

mysql-msa-board		${MYSQL_MSA_BOARD}		30501
mysql-msa-comment	${MYSQL_MSA_COMMENT}	30601
mysql-msa-user		${MYSQL_MSA_USER}		30701
redis-msa-ui		${REDIS_MSA_UI}			30801



+ 이전에 해야 할 것들.
- do Jenkinsfile before maven build
1. api.properties 변수값 변경
	edu-msa-ui-master/src/main/resources/properties/api.properties
	```
	#ApiEndpoint=https://edu-msa-zuul.paas-ta.org
	ApiEndpoint=http://edu-msa-zuul.${NAMESPACE}.svc.cluster.local:28081
	ApiEndpoint=http://localhost:8080
	ApiMasterKey=MSA-PaaS-TA
	ApiKeySalt=PaaS-TA

	# kubernetes Redis Setting
	redis.hostname=${KUBERNETES_URL}
	redis.port=${REDIS_MSA_UI}
	redis.password=password
	```


- Do deploy with .yaml 
1. redis-msa-ui.yaml 배포
2. mysql-msa-*.yaml 배포
	- mysql-msa-board.yaml
		+ init.sql
		mysql -u root -p
		Enter password: password
		CREATE DATABASE msa_board default CHARACTER SET UTF8;
		USE msa_board;
		CREATE TABLE `TB_BOARD` (
		  `board_seq` int(11) NOT NULL AUTO_INCREMENT,
		  `board_title` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
		  `board_text` mediumtext COLLATE utf8_unicode_ci,
		  `write_user_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
		  `write_user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
		  `use_yn` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'Y',
		  `create_dt` datetime NOT NULL,
		  `update_dt` datetime NOT NULL,
		  PRIMARY KEY (`board_seq`)
		) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
	- mysql-msa-comment.yaml
		+ init.sql
		mysql -u root -p	
		Enter password: password
		CREATE DATABASE msa_comment default CHARACTER SET UTF8;
		USE msa_comment;
		CREATE TABLE `TB_COMMENT` (
		  `comment_seq` int(11) NOT NULL AUTO_INCREMENT,
		  `board_seq` int(11) NOT NULL,
		  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
		  `write_user_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
		  `write_user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
		  `use_yn` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'Y',
		  `create_dt` datetime NOT NULL,
		  `update_dt` datetime NOT NULL,
		  PRIMARY KEY (`comment_seq`)
		) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
	- mysql-msa-user.yaml
		+ init.sql
		mysql -u root -p
		Enter password: password
		CREATE DATABASE msa_user default CHARACTER SET UTF8;
		USE msa_user;
		CREATE TABLE `TB_USER` (
		  `user_seq` int(11) NOT NULL AUTO_INCREMENT,
		  `user_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
		  `user_passwd` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
		  `user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
		  `use_yn` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'Y',
		  `create_dt` datetime NOT NULL,
		  `update_dt` datetime NOT NULL,
		  PRIMARY KEY (`user_seq`),
		  UNIQUE KEY `id_key` (`user_id`)
		) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


# connect repositoiry 

argocd app create msaproject --repo https://github.com/seunghyejeong/gitops-repository.git --path edu-msa-ui-master/kubernetes --dest-server https://kubernetes.default.svc --dest-namespace default

argocd app create edu-msa-board --repo https://github.com/seunghyejeong/gitops-repository.git --path edu-msa-board-master/kubernetes --dest-server https://kubernetes.default.svc --dest-namespace default

argocd app sync edu-msa-board


# api.properties 수정
edu-msa-ui-master/src/main/resources/properties/api.properties
```
#ApiEndpoint=https://edu-msa-zuul.paas-ta.org
ApiEndpoint=http://edu-msa-zuul.default.svc.cluster.local:28081
ApiEndpoint=http://localhost:8080
ApiMasterKey=MSA-PaaS-TA
ApiKeySalt=PaaS-TA

#kubernetes Redis Setting
redis.hostname=10.101.0.104
redis.port=30801
redis.password=password
```
: Jenkinsfile에서 app.properties를 어떻게 변경하지??
	1. app.properties를 새로 만들어서 commit -> overwrite
	2. new-app.properties를 만들어 commit 후 maven build시 해당 파일 읽기
	3. app.properties안의 변수값을 변경하는 Jenkinsfile문 작성하기
--- 
굳이 그럴 이유 없어보임. 환경에 따라 변수값이 달라지는건데 그 때마다 api.properties만 git commit 해주면 되는거니까.
따지자면 1번 방법으로 한당 


sync까지는 완료됐으나 pod 배포에서 오류 ( 아마 configmap,init.sql로 추정)

```console
ubuntu@ta-bami-cluster-1:~/workspace/practice-gitops/gitops-repository/edu-msa-board-master/kubernetes$ k describe pod/mysql-msa-board-84444d6977-fmxmk -n default 
Name:             mysql-msa-board-84444d6977-fmxmk
Namespace:        default
Priority:         0
Service Account:  default
Node:             ta-bami-cluster-4/10.101.0.237
Start Time:       Thu, 11 May 2023 07:23:46 +0000
Labels:           app=mysql-board
                  pod-template-hash=84444d6977
Annotations:      cni.projectcalico.org/containerID: b68ed575144765c40ecc37ba677cadfa0521cd9afe7f8a6f94c1640021816936
                  cni.projectcalico.org/podIP: 10.233.87.203/32
                  cni.projectcalico.org/podIPs: 10.233.87.203/32
Status:           Running
IP:               10.233.87.203
IPs:
  IP:           10.233.87.203
Controlled By:  ReplicaSet/mysql-msa-board-84444d6977
Containers:
  mysql-board:
    Container ID:   cri-o://52608193aba57dc79f161525e9fd708e8f6898524ecb97954e746b94a71aa665
    Image:          mysql:5.6
    Image ID:       docker.io/library/mysql@sha256:20575ecebe6216036d25dab5903808211f1e9ba63dc7825ac20cb975e34cfcae
    Port:           3306/TCP
    Host Port:      0/TCP
    State:          Waiting
      Reason:       CrashLoopBackOff
    Last State:     Terminated
      Reason:       Error
      Exit Code:    1
      Started:      Thu, 11 May 2023 07:30:51 +0000
      Finished:     Thu, 11 May 2023 07:30:58 +0000
    Ready:          False
    Restart Count:  6
    Environment:
      MYSQL_ROOT_PASSWORD:  password
    Mounts:
      /docker-entrypoint-initdb.d/init.sql from mysql-msa-board-config (rw)
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-6mvgq (ro)
Conditions:
  Type              Status
  Initialized       True 
  Ready             False 
  ContainersReady   False 
  PodScheduled      True 
Volumes:
  mysql-board:
    Type:       EmptyDir (a temporary directory that shares a pod's lifetime)
    Medium:     
    SizeLimit:  <unset>
  mysql-msa-board-config:
    Type:      ConfigMap (a volume populated by a ConfigMap)
    Name:      mysql-msa-board-config
    Optional:  false
  kube-api-access-6mvgq:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    ConfigMapOptional:       <nil>
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type     Reason     Age                   From               Message
  ----     ------     ----                  ----               -------
  Normal   Scheduled  10m                   default-scheduler  Successfully assigned default/mysql-msa-board-84444d6977-fmxmk to ta-bami-cluster-4
  Normal   Pulled     10m                   kubelet            Successfully pulled image "mysql:5.6" in 11.532021554s (11.532033495s including waiting)
  Normal   Pulled     9m51s                 kubelet            Successfully pulled image "mysql:5.6" in 2.583014569s (2.583021545s including waiting)
  Normal   Pulled     9m24s                 kubelet            Successfully pulled image "mysql:5.6" in 3.169824105s (3.16983157s including waiting)
  Normal   Created    8m46s (x4 over 10m)   kubelet            Created container mysql-board
  Normal   Started    8m46s (x4 over 10m)   kubelet            Started container mysql-board
  Normal   Pulled     8m46s                 kubelet            Successfully pulled image "mysql:5.6" in 2.719183107s (2.71919867s including waiting)
  Normal   Pulling    7m50s (x5 over 10m)   kubelet            Pulling image "mysql:5.6"
  Normal   Pulled     7m47s                 kubelet            Successfully pulled image "mysql:5.6" in 2.656200636s (2.656216566s including waiting)
  Warning  BackOff    11s (x41 over 9m41s)  kubelet            Back-off restarting failed container
```

```yaml
apiVersion:  v1
kind: ConfigMap
metadata:
  name: mysql-msa-board-config
data:
  init.sql: |
    CREATE DATABASE msa_board default CHARACTER SET UTF8;
    USE msa_board;
      CREATE TABLE `TB_BOARD` (
        `board_seq` int(11) NOT NULL AUTO_INCREMENT,
        `board_title` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
		`board_text` mediumtext COLLATE utf8_unicode_ci,
		`write_user_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
		`write_user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
		`use_yn` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'Y',
		`create_dt` datetime NOT NULL,
		`update_dt` datetime NOT NULL,
		PRIMARY KEY (`board_seq`)
	  ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql-msa-board
spec:
  selector:
    matchLabels:
      app: mysql-board
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: mysql-board
    spec:
      containers:
      - image: mysql:5.6
        name: mysql-board
        env:
        - name: MYSQL_ROOT_PASSWORD
          value: password
        ports:
        - containerPort: 3306
          name: mysql-board
        volumeMounts:
        - name: mysql-msa-board-config
          mountPath: /docker-entrypoint-initdb.d/init.sql
      volumes:
      - name: mysql-board
      - name: mysql-msa-board-config
        configMap:
          name: mysql-msa-board-config


---
apiVersion: v1
kind: Service
metadata:
  name: mysql-msa-board
spec:
  type: NodePort
  ports:
  - port: 3306
    protocol: TCP
    targetPort: 3306
    nodePort: 30501
  selector:
    app: mysql-board
```

```
apiVersion: v1
kind: ConfigMap
metadata:
  name: mysql-msa-board-config
data:
  init.sql: |
    CREATE DATABASE IF NOT EXISTS msa_board DEFAULT CHARACTER SET UTF8;
    USE msa_board;
    CREATE TABLE IF NOT EXISTS `TB_BOARD` (
      `board_seq` int(11) NOT NULL AUTO_INCREMENT,
      `board_title` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
      `board_text` mediumtext COLLATE utf8_unicode_ci,
      `write_user_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
      `write_user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
      `use_yn` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'Y',
      `create_dt` datetime NOT NULL,
      `update_dt` datetime NOT NULL,
      PRIMARY KEY (`board_seq`)
    ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
---

apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql-msa-board
spec:
  selector:
    matchLabels:
      app: mysql-board
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: mysql-board
    spec:
      containers:
      - image: mysql:5.6
        name: mysql-board
        env:
        - name: MYSQL_ROOT_PASSWORD
          value: password
        ports:
        - containerPort: 3306
          name: mysql-board
        volumeMounts:
        - name: mysql-msa-board-config
          mountPath: /docker-entrypoint-initdb.d
      volumes:
      - name: mysql-msa-board-config
        configMap:
          name: mysql-msa-board-config

---
apiVersion: v1
kind: Service
metadata:
  name: mysql-msa-board
spec:
  type: NodePort
  ports:
  - port: 3306
    targetPort: 3306
    nodePort: 30501
  selector:
    app: mysql-board
```

argocd app create edu-msa-board --repo https://github.com/seunghyejeong/gitops-repository.git --path edu-msa-board-master/kubernetes --dest-server https://kubernetes.default.svc --dest-namespace default

argocd app sync edu-msa-board


- edu-msa-zuul

issue
```
#7 [2/3] COPY edu-msa-zuul-1.0.0.war edu-msa-zuul-1.0.0.war
#7 ERROR: failed to calculate checksum of ref moby::k63vwqtkb3x65v9lse94893o2: "/edu-msa-zuul-1.0.0.war": not found
------
 > [2/3] COPY edu-msa-zuul-1.0.0.war edu-msa-zuul-1.0.0.war:
------
Dockerfile:3
--------------------
   1 |     FROM openjdk:8-jdk-alpine
   2 |     ARG JAR_FILE=edu-msa-zuul-1.0.0.war
   3 | >>> COPY ${JAR_FILE} edu-msa-zuul-1.0.0.war
   4 |     COPY application.yml /application.yml
   5 |     ENTRYPOINT ["java","-jar","-Dspring.config.location=application.yml","/edu-msa-zuul-1.0.0.war"]
--------------------
ERROR: failed to solve: failed to compute cache key: failed to calculate checksum of ref moby::k63vwqtkb3x65v9lse94893o2: "/edu-msa-zuul-1.0.0.war": not found
```
war 파일이 target에 생성되어 있음

```Dockerfile
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=edu-msa-zuul-1.0.0.war
COPY ${JAR_FILE} edu-msa-zuul-1.0.0.war
COPY application.yml /application.yml
ENTRYPOINT ["java","-jar","-Dspring.config.location=application.yml","/edu-msa-zuul-1.0.0.war"]
```
```console
root@jenkins-858cddb78b-mz6zb:/var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master/target# ls
classes		    edu-msa-zuul-1.0.0.war	     generated-sources	     maven-archiver  surefire-reports
edu-msa-zuul-1.0.0  edu-msa-zuul-1.0.0.war.original  generated-test-sources  maven-status    test-classes
```
```Dockerfile
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=/var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master/target/edu-msa-zuul-1.0.0.war
COPY ${JAR_FILE} edu-msa-zuul-1.0.0.war
COPY application.yml /application.yml
ENTRYPOINT ["java","-jar","-Dspring.config.location=application.yml","/edu-msa-zuul-1.0.0.war"]
```
/var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master/target/edu-msa-zuul-1.0.0.war
/var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master/target/edu-msa-zuul-1.0.0.war

```Jenkinsfile
pipeline {
  agent any

  tools{
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
sh 'mvn -f /var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master/pom.xml clean package'
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

stage('Docker Login'){
steps{
withCredentials([usernamePassword(credentialsId: 'docker-token', passwordVariable: 'tlqkfak0315!', usernameVariable: 'seunghyejeong')]) {
sh 'docker login -u seunghyejeong -p tlqkfak0315!'
}   
}
}

stage('Deploy our image') { 
steps { 
script {
	sh 'cp /var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master/target/edu-msa-zuul-1.0.0.war /var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master'
    sh 'cd /var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master && docker build -t edu-msa-zuul-master .'
    sh 'docker tag edu-msa-zuul-master seunghyejeong/edu-msa-zuul-master:latest'
    sh 'docker push seunghyejeong/edu-msa-zuul-master:latest'
} 
}
}

}
}
```
```
```Dockerfile
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=edu-msa-zuul-1.0.0.war
COPY ${JAR_FILE} edu-msa-zuul-1.0.0.war
COPY application.yml /application.yml
ENTRYPOINT ["java","-jar","-Dspring.config.location=application.yml","/edu-msa-zuul-1.0.0.war"]
```
- war파일 path 수정
`	sh 'cp /var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master/target/edu-msa-zuul-1.0.0.war /var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master'` 로 수정해줌
조건1: Dockerfile과 같은 디렉토리 내에 있어야함
	war생성 파일위치: /var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master/target/edu-msa-zuul-1.0.0.war
	Dockerfile 파일위치: /var/jenkins_home/workspace/edu-msa-zuul/edu-msa-zuul-master



argocd app create edu-msa-board --repo https://github.com/seunghyejeong/gitops-repository.git --path edu-msa-board-master/kubernetes --dest-server https://kubernetes.default.svc --dest-namespace default



argocd app create edu-msa-board \
    --repo https://github.com/seunghyejeong/gitops-repository.git \
    --path edu-msa-board-master/kubernetes \
    --dest-server https://kubernetes.default.svc \
    --dest-namespace default

argocd app sync edu-msa-board

argocd app create edu-msa-comment \
    --repo https://github.com/seunghyejeong/gitops-repository.git \
    --path edu-msa-comment-master/kubernetes \
    --dest-server https://kubernetes.default.svc \
    --dest-namespace default

argocd app sync edu-msa-comment

argocd app create edu-msa-user \
    --repo https://github.com/seunghyejeong/gitops-repository.git \
    --path edu-msa-user-master/kubernetes \
    --dest-server https://kubernetes.default.svc \
    --dest-namespace default
    
argocd app sync edu-msa-user

argocd app create edu-msa-ui \
    --repo https://github.com/seunghyejeong/gitops-repository.git \
    --path edu-msa-ui-master/kubernetes \
    --dest-server https://kubernetes.default.svc \
    --dest-namespace default
    
argocd app sync edu-msa-ui

argocd app create edu-msa-zuul \
    --repo https://github.com/seunghyejeong/gitops-repository.git \
    --path edu-msa-zuul-master/kubernetes \
    --dest-server https://kubernetes.default.svc \
    --dest-namespace default
    
argocd app sync edu-msa-zuul

# Dockerfile 수정
```
# Start with a base image containing Tomcat server running with JRE8
FROM maven:3.6.0-jdk-8-slim AS build

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and src directories to the container
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn package

# Start with a base image containing Tomcat server running with JRE8
FROM tomcat:9-jre8-alpine

# Set the working directory to /usr/local/tomcat
WORKDIR /usr/local/tomcat

# Copy the custom server.xml file to the container
COPY server.xml ./conf

# Remove any existing files in the webapps directory of Tomcat
RUN rm -rf ./webapps/*

# Copy the WAR file generated by Maven into the webapps directory of Tomcat
ARG JAR_FILE=*.war
COPY --from=build /app/target/${JAR_FILE} ./webapps/edu-msa-comment-1.0.0.war

# Expose the port the application will listen on
EXPOSE 28083

# Start the Tomcat server
# CMD ["catalina.sh", "run"]
```

# mysql-comment.yaml 수정
```
apiVersion: v1
kind: ConfigMap
metadata:
  name: mysql-msa-board-config
data:
  init.sql: |
    CREATE DATABASE IF NOT EXISTS msa_board DEFAULT CHARACTER SET UTF8;
    USE msa_board;
    CREATE TABLE IF NOT EXISTS `TB_BOARD` (
      `board_seq` int(11) NOT NULL AUTO_INCREMENT,
      `board_title` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
      `board_text` mediumtext COLLATE utf8_unicode_ci,
      `write_user_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
      `write_user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
      `use_yn` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'Y',
      `create_dt` datetime NOT NULL,
      `update_dt` datetime NOT NULL,
      PRIMARY KEY (`board_seq`)
    ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
---

apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql-msa-board
spec:
  selector:
    matchLabels:
      app: mysql-board
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: mysql-board
    spec:
      containers:
      - image: mysql:5.6
        name: mysql-board
        env:
        - name: MYSQL_ROOT_PASSWORD
          value: password
        ports:
        - containerPort: 3306
          name: mysql-board
        volumeMounts:
        - name: mysql-msa-board-config
          mountPath: /docker-entrypoint-initdb.d
      volumes:
      - name: mysql-msa-board-config
        configMap:
          name: mysql-msa-board-config

---
apiVersion: v1
kind: Service
metadata:
  name: mysql-msa-board
spec:
  type: NodePort
  ports:
  - port: 3306
    targetPort: 3306
    nodePort: 30501
  selector:
    app: mysql-board
```
: IF NOT EXSIST 제거
: volume: -name: mysql-comment 추가
: DATABASES -> DATABASE 수정
: volume: subPath: init.sql 추가


```zuul Dockerfile
# Start with a base image containing Tomcat server running with JRE8
FROM maven:3.6.0-jdk-8-slim AS build

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and src directories to the container
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn package

# Start with a base image containing Tomcat server running with JRE8
FROM tomcat:9-jre8-alpine

# Set the working directory to /usr/local/tomcat
WORKDIR /usr/local/tomcat

# Copy the custom server.xml file to the container
COPY server.xml ./conf

# Remove any existing files in the webapps directory of Tomcat
RUN rm -rf ./webapps/*

# Copy the WAR file generated by Maven into the webapps directory of Tomcat
ARG JAR_FILE=*.war
COPY --from=build /app/target/${JAR_FILE} ./webapps/edu-msa-comment-1.0.0.war

# Expose the port the application will listen on
EXPOSE 28083

# Start the Tomcat server
CMD ["catalina.sh", "run"]
```

seunghyejeong/edu-msa-user-master:latest
seunghyejeong/edu-msa-user-master:latest


Q: CICD가 CI일때 버튼 눌러서 pipeline 수행하고 CD 할때 argo 접속 후 repo sync/app 배포 클릭: 이게 자동CI/CD 인지?
git-argocd sync 자동
Q: argoCD를 통해 배포되는 앱의 namespace가 argocd? jenkins?와 같아야하는지 혹은 상관없는지??
제약조건없으면 상관없음 (argocd쪽에서 제약조건 있으면 따로 표기)
Q: api.properties에 기입하는 KUBERNETES_URL은 어떤 app(edu-msa-ui혹은 argocd가 배포된 workernode ip? 아니면 master-node?)의 IP를 적어야하는지?
dbms: ${KUBERNETES_IP}는 DB가 바라보는 cluster IP
DB가 private zone에 배포됐을 시에는 내부끼리 통신가능하며 배포한 cluster의 namespace ip를 적어주면 보안상 안전.



소나큐브
argocd sync 자동?
