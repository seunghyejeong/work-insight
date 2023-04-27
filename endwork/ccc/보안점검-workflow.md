보안 점검

bosh paasta 
	 https://m80.mailplug.com/webmail/lists#%7B%22s_fnum%22%3A%221%22%2C%22s_mread%22%3A%22%22%2C%22view%22%3A258%2C%22list%22%3Atrue%2C%22type%22%3A%22%22%2C%22t%22%3A1675037206621%2C%22mailrefresh%22%3A%22%22%2C%22mdl%22%3A%22lists%22%7D
	 : 보안점검 진행한 git branch로 clone하기 
	 : git clone -b oss-01 --single-branch https://github.com/PaaS-TA/paasta-deployment.git
	 : git clone https://github.com/PaaS-TA/portal-deployment.git -b oss-01



paasta-service
	 https://mail.google.com/mail/u/0/#inbox/FMfcgzGrcPGCXqDNHsTHXknTcwLzdQCQ

보안점검 순서
		bosh -> paasta -> //   portal -> service


rapid7: 
	보안점검프로그램
	guide: 
		rapid7-insightvm-install-guide.md
	INFO
		ID 		: paasta
		PW 		: PaaS-TA@2023
		url		: http://bindingip(203.255.255.123):3780
		login
		key		: mail로 전달-> install

보안점검 ip
	inception: 203.255.255.99
	rapid: 203.255.255.123
	bosh: 10.160.61.6
	paasta-ta-bami-core: 203.255.255.108
	paasta-ta-bami-ui-ip: 203.255.255.124
	paasta-ta-bami-api:	203.255.255.126

bosh 로그인 
	bosh alias-env micro-bosh -e 10.160.61.6 --ca-cert <(bosh int ./openstack/creds.yml --path /director_ssl/ca)

	export CREDHUB_CLIENT=credhub-admin
	export CREDHUB_SECRET=$(bosh int --path /credhub_admin_client_secret openstack/creds.yml)
	export CREDHUB_CA_CERT=$(bosh int --path /credhub_tls/ca openstack/creds.yml)


echo $(bosh int ~/workspace/cce2023/paasta-deployment/bosh/openstack/creds.yml --path /admin_password


PostgreSQL 수동 점검
	port 5432


	수동점검:
		1. 해당 bin file있는곳으로 이동
		2. source *.bin

	수동점검 예시:
		### PostgreSQL 점검 스크립트를 실행하겠습니다 #####

		ip입력: bosh ip

		1. postgreSQL 실행파일이 존재하는 디렉터리 경로를 입력해주세요.
		(ex. /usr/pgsql-10/bin/ 입력): 
		/var/packages/postgres-13/bin
 /var/vcap/data/packages/postgres-11.17/cd687b9e0e8c42c83e42d421db7e024c6bfe81e2/bin/
		2. PostgreSQL 설정파일들이 존재하는 디렉터리 경로를 입력해주세요.
		(ex. /var/lib/pgsql/10/data 입력): 
		/var/vcap/store/postgres-13
		/var/vcap/store/postgres/postgres-11.17
		3. 관리자 계정에 부여된 IP를 입력해 주세요. (ex. localhost 또는 127.0.0.1): 
		localhost

		4. 사용 중인 DataBase 명을 입력해주세요. (ex. postgres):
		postgres

		5. Port 번호를 입력 해주세요. (ex. 5432):
		5432

		6. PostgreSQL 구동 계정을 입력 해주세요. (ex. postgres):
		vcap


		진단 완료

/var/vcap/data/packages/postgres-11.17/cfddf6f9e50350da3e21bbb51adfaff2485be624/bin/postgres -D 

/var/vcap/store/postgres/postgres-11.17 

-h 0.0.0.0 -p 5524


여러개 zip폴더 압출 해제하기  
	for i in *.zip;
	do 
		unzip $i -d /path/to/put;
	done

파일명과 같은 파일폴더를 만들며 해당폴더에 unzip
	
	```shell
	for file in `ls *.zip`;
	do
	       unzip "${file}" -d "${file:0:-4}";
	done
	```

linux_info 보안점검
	
	각 vm으로 던져서 스크립트 돌리기.

	bosh -e micro-bosh -d paasta  scp ./cce-scripts.zip api/0887902e-70cd-44f9-a2fe-4f631d02b453:/tmp/

	가져오기

	bosh -e micro-bosh -d paasta scp diego-api/7cd8ac48-5903-44d8-ac92-4bc739889447:/tmp/scripts/*.txt ~/workspace/user/bami/linuxinfo/


portal-ui/api mariadb 돌리기
	각 vm들어가서 스크립트 실행 
	실행 정보 = CCE_SCIPRT

	/var/vcap/store/mariadb/bin/mysql
	/var/vcap/store/mariadb/mariadb.cnf
	/var/vcap/data/root_tmp/mysql.sock

service
	Linux (cce-common) / Linux-info / 해당 cce-check / CVE

	최소배포 
		각 vars file 에서 변경
		instance 갯수 1
	
	내부ip사용
		haproxy_public_network_name vip -> default
		public_networks_name을 vip→ default로 바꾼다.
	
	서비스 puglic ip
		gateway
			z7 10.160.60.60
		gluster 
			z4 10.160.64.60
		lifecycle
			z7 10.160.60.61
		logging service 
			?
		mongdb
			10.160.63.10~21
		mysql
			10.160.164.100
		pinpoint
			10.160.60.62
		pipeline
			10.160.63.30~42
			haproxy == paasta haproxy?
		pinpoint ㅇ
			10.160.60.24


- gateway-service :: linux info, mysql, tomcat 진행 보류
 

- pipeline:
	Task 1791 | 01:58:16 | 
	Deprecation: Global 'properties' are deprecated. Please define 'properties' at the job level.
	Task 1791 | 01:58:17 | 
	Error: 
	Instance group 'ci_server' has 1 instances but was allocated 2 static IPs in network 'default'
	:: ci server는 instance 2개 


- failing이 떠도 linux-info는 진행

- loggingservice:
	loggingservice는 guide대로 진행

	Error: Link 
	'portal-registration' 
	in job 'paas-ta-portal-log-api'
	from instance group 'log-api' 
	consumes from deployment 'portal-api', 
	but the deployment does not exist 
	:: portal api service 필요 ..?

- mongodb

	Evaluating manifest:
	  Error 'operation [3] in 
	  operations/cce-check.yml failed': 
	  Expected to find exactly one matching array item for path '/instance_groups/name=mongodb_slave3' 
	  but found 0
 	mongdb는 cce-check.yml파일이 변경되어야함.
 	(default instance가 3개인데 2개로 바뀜 slaves3/master3 삭제 후 배포해야함 )

# 0208

 	보안점검 완룡ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ

 	

 CVE CCE 보안 점검 및 결과서 작성 완료했습니다. 
 	- ap_oss-01_bosh_director 
 	- ap_oss-01_core
 	- ap_oss-01_portal_oss-01
 	- ap_oss-01_service_oss-01

 	씨앤에프시스템 2/16일 오전10시 
	
	
	
vsphere

inception 정보드립니다. 배포 완료될 때마다 말씀 드릴게여
---
ta-luna-inception(vsphere)
10.0.20.13

ubuntu
PaaS-TA@luna

##scp

## bosh 에서 txt 파일 꺼내오기
scp -i jumpbox.key scp jumpbox@10.0.1.6:/tmp/Linux_253e1aab-48f4-4047-48e1-4ce94ef6c290_10.0.1.6_0914_Ref.txt .

## VM 에서 txt 파일 꺼내오기 
bosh -d paasta scp api/0:/tmp/Linux_ff35d9ad-1572-49be-8293-0fff0f6d541f_10.0.201.127_1014_Ref.txt .

bosh
10.0.21.10 


scp -i jumpbox.key -o StrictHostKeyChecking=no jumpbox@10.0.21.10:/tmp/* .

계정 정보 미리 드릴게요 ...ㅎㅎ 
---
# luna vsphere

host
- 10.0.20.13
user
- ubuntu
pw
- PaaS-TA@luna

====================================================

# luna rapid-7 vsphere

host
- 10.0.20.15
user
- ubuntu
pw
- PaaS-TA@luna

username: paasta
pw: PaaS-TA@2023

====================================================

#Deployment 삭제
$ bosh -e micro-bosh delete-deployment -d paasta
$ bosh -e micro-bosh delete-deployment -d portal-ui
$ bosh -e micro-bosh delete-deployment -d portal-api

#가비지 삭제
$ bosh -e micro-bosh clean-up --all

#Bosh 삭제
$ ./delete-deploy-{iaas}.sh

#완전초기화 작업
$ rm -rf $HOME/.bosh
$ rm -rf $HOME/.cf
$ rm -rf $HOME/.credhub
$ rm -rf $HOME/.uaac.yml
$ rm -rf $HOME/workspace/paasta-5bosh en.0/deployment/bosh-deployment/aws/creds.yml

가능(코어 없이 배포 성공)
gateway
glusterfs
lifecycle
mongodb
pinpoint
pipeline
source control
--------------------
불가능( 코어 없이 배포 실패)
logging-service --> core 및 portal api 필요
mysql
rabbitmq
redis 



- singleblobstore nginx  10.10.1.17
	 /var/vcap/jobs/blobstore/config/nginx.conf
	 /var/vcap/packages/nginx_webdav
	 
	/var/vcap/packages/nginx_webdav/sbin/nginx
	 
	 /var/vcap/jobs/blobstore/config/nginx.conf
	 
	 
- api
	/var/vcap/packages/bpm/bin/tini -w -s -- /var/vcap/packages/nginx/sbin/nginx -c /var/vcap/jobs/cloud_controller_ng/config/nginx.conf
    /var/vcap/packages/nginx/sbin/nginx -c /var/vcap/jobs/cloud_controller_ng/config/nginx.conf
	
- uaa
	/var/vcap/packages/uaa/jdk/bin/java
	/var/vcap/data/uaa/tomcat/conf/logging.properties
	/var/vcap/data/uaa/cert-cache/cacerts
	/var/vcap/data/uaa/tomcat
	/var/vcap/data/uaa/tomcat
	/var/vcap/sys/log/uaa/
	/var/vcap/data/uaa/tomcat/temp
	
	
	보시 파스타는 12존에서하고  서비스는 34567존으로 하기로했어용
	/var/vcap/store/mariadb/bin/mysql
/var/vcap/store/mariadb/mariadb.cnf
/var/vcap/store/mariadb-10.5.17-linux-x86_64/include/mysql/server/mysql/psi/mysql_socket.h
/var/vcap/data/root_tmp/mysql.sock
/var/tmp/mysql.sock




/var/vcap/packages/uaa/jdk/bin/java 
/var/vcap/data/uaa/tomcat/conf/logging.properties
/var/vcap/jobs/uaa/config/log4j2.properties
/var/vcap/data/uaa/tomcat/bin/tomcat-juli.jar
/var/vcap/data/uaa/tomcat
/var/vcap/data/packages/uaa/fe018723680f91c9919be3fcc14987968fb8dfad/tomcat/conf/tomcat-users.xml
/var/vcap/data/packages/uaa/fe018723680f91c9919be3fcc14987968fb8dfad/tomcat/conf/tomcat-users.xsd
/var/vcap/data/packages/uaa/fe018723680f91c9919be3fcc14987968fb8dfad/tomcat/webapps




/var/vcap/packages/nginx/sbin/nginx
/var/vcap/jobs/cloud_controller_ng/config/nginx.conf
/var/vcap/packages/nginx/sbin/nginx
/var/vcap/jobs/cloud_controller_ng/config/nginx.conf

/var/vcap/jobs/uaa/config
/var/vcap/jobs/uaa/bin/uaa
 /var/vcap/packages/uaa/jdk/bin/java
 /var/vcap/data/uaa/tomcat/conf/logging.properties
/var/vcap/sys/log/uaa/
/var/vcap/data/uaa/cert-cache/cacerts
/var/vcap/data/uaa/tomcat 


/var/vcap/packages/uaa/jdk/bin/java
/var/vcap/data/uaa/tomcat/conf/logging.properties

/var/vcap/data/packages/uaa/fe018723680f91c9919be3fcc14987968fb8dfad/tomcat/conf/tomcat-users.xml
/var/vcap/data/packages/uaa/fe018723680f91c9919be3fcc14987968fb8dfad/tomcat/conf/tomcat-users.xsd





/var/vcap/data/packages/postgres-11.17/cd687b9e0e8c42c83e42d421db7e024c6bfe81e2/bin
/var/vcap/store/postgres/postgres-11.17
5524