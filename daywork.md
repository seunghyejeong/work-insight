# 230126

1. cce-check git push  :: 완료 
2. 오픈랩 :: 인증서 발급단계 

# 230127

1. 오픈랩 설치 
	:: paasta 배포 진행중



# 230130

1. 오픈랩 :: 오전중에 완료하기 
	:: 완료

2. 보안점검 
	- rapid7 설치중 
	- cce-check (common/bosh/service) release 재구성
	- bosh 배포 :: 완료
	- bosh cce-check :: 진행중 

3. 연말정산 자료 메일 회신하기 :: 완료 
	지원선은 부양가족인가.. 부양가족 아님

# 230131

1. bosh : cceh -check : 완료
   paasta : 

# 230131
registercontroller.java
boardController.java
logincontroller.java
index.jsp
boardlist.jsp

#230201
1. paasta 배포 및 cce-check
	:: 완료  
2. portal-api 배포 
	:: 완료 

# 230202
	1. 보안점검 
	 	- portal API/UI 
		  ::CCE 점검완료


	3. portal linux 실행
		:완료
	4. portal ui/api mysql 해당 파일 cce_scripts에서 찾고 돌리기 



CCE > Linux Info > CVE 

infra 
	linux			o
	linux info 		o
portal 
	cve 			ㅇ		


redis 			mariadb		paas-ta-on-demand	redis 
	linux 			o              o
	linux-info	    o              o
	cce (redis) 	o			   o              o
 	cve				
	mysql(mariadb)  o
	
	/var/vcap/store/mariadb/bin/mysql 
	mysql_8.0_v4.1.bin		
	/var/vcap/store/mariadb/mariadb.cnf



	#0206-7
	
	- 보안점검 
			
	service		linux 	  linux-info		cce 		 cve 
	
	rabbitmq	  o    	     o 				  o 		   o
	
	lifecycle     o          ㅇ 	           o           o
					(postgresql: o  )
	
	glusterfs     ㅇ 		  ㅇ   			 ㅇ 		  ㅇ	 


	mysql         ㅇ 		  ㅇ	  		  ㅇ          ㅇ
		
	gateway       ㅇ  	      ㅇ			  ㅇ          ㅇ 


	logging-ser   ㅇ 		  ㅇ 			  ㅇ 		  ㅇ 
	
	mongodb   	:: 배포 진행중 
	
	pinpoint      ㅇ 		  ㅇ  			  ㅇ 		  ㅇ 	


	pipeline      ㅇ		  ㅇ 			  ㅇ 	      ㅇ
	
	source-ctr    ㅇ 		  ㅇ 			  ㅇ 		  ㅇ 


/var/vcap/packages/pxc/bin/mysql
/var/vcap/jobs/pxc-mysql/config/my.cnf


/var/vcap/packages/influxdb/influxdb-1.8.10-1/usr/bin/influx
/var/vcap/jobs/influxdb/config/influxdb.conf
/var/vcap/store/influxdb/meta/meta.db

10.160.64.39
8086
1
root/PaaS-TA2022

Error: Link 
'portal-registration' 
in job 'paas-ta-portal-log-api'
from instance group 'log-api' 
consumes from deployment 'portal-api', 
but the deployment does not exist 
:: portal api service 필요 ..?


/var/vcap/packages/mariadb/bin/mysql

/var/vcap/packages/mariadb/bin/mariadb
/var/vcap/jobs/mariadb/config/mariadb.cnf



# 230209

공모전 시작작자가가가가가가 

[공모전]

공모전 운영자 포털
	http://paas-ta.co.kr/admin/admin.jsp 
	내부 ip로만 접속 가능 (wifi 사용 안됨)
	id: pass-ta
	pw: pstamaster19!
공모전 사용자 포털
	http://paas-ta.co.kr

1. 기능 분석 														(완료 2023.02.09)
	: txt형태로 가능 / 기능이 어떤 것들이 있는지 분석.
	: 관리자 / 사용자 페이지별로 분석 
	: 홈페이지에 어떤 기능이 있는것인지 분석용도임
	
2. 전자정부프레임워크 활용해서 기본틀(관리자 페이지) 만들기			(sitemesh 완료)

	: sitemesh 와 유사한 라이브러리 사용 (대표포털 user 참조)
	: 대표포털이 include 형태로 되어있을경우 라이브러리 사용

3. 데이터베이스 dump 및 구현 									 (완료 2023.02.09)    			
	: 백업 > (이세영전임님 도움)
	: 로컬에 DB 환경 구축 (정승혜 혼자 !!!)

4. 관리자 로그인 로직 생성 
	: 이전 로그인 로직 확인 및 재구현 
	: 예를 들어 내부 ip로만 접속이 가능하다던가 글조회시 비밀번호 입력 해야하는거 등등..

---------- 화면설계서 백전임님께서 공유해주시면 ------------
각 메뉴별 개발 (공지사항, 묻고답하기, 수상결과 등!! )
해당 메뉴들이 관리자 쪽에서 활용될 수 있으면 좋겠다.

# 230210

- 전자정부프레임워크 활용하여 기본틀 만들기 
	sitemesh
- 관리자 로그인 로직 생성 


# 230213 

1. 관리자 페이지 만들기 
2. 관리자 로그인 로직 생성 
	: 이전 로그인 로직 확인 및 재구현 
	: 예를 들어 내부 ip로만 접속이 가능하다던가 글조회시 비밀번호 입력 해야하는거 등등..



# 230215

1. db에 ws/ss 백업 	 :: 완료 
2. project - db 연결 :: 완료 
3. 게시판 만들기 	 :: 이따해보자 

---------------------
1. 구글 드라이브에 파일 만들기 
:완료

# 230216
--- main

1. 호환성 사전 협의 회의 
2. 보안점검
--- sub
1. login/register/userdao 만들기



계정 정보 미리 드릴게요 ...ㅎㅎ 
---
luna vsphere

host
- 10.0.20.13
user
- ubuntu
pw
- PaaS-TA@luna

====================================================

luna rapid-7 vsphere

host
- 10.0.20.15
user
- ubuntu
pw
- PaaS-TA@luna

username: paasta

pw: PaaS-TA@2023

# 230217
---main 

1. paasta 보안점검
2. 호환성 
	:메일송부완료
	---sub
1. userdao/ qnadao 만들어서 db연결시키기
	:완료
	

퇴근전 백업 하기 

9:05 AM Monday, February 20, 2023

--main
1. paasta 보안점검

--sub
1. boarddao/daoimpl/mapper.xml/test까지완료하기



# 230221

1. qna/notice board :: 완료 
2. 페이징 처리 
3. 보안점검


Test
    public void deleteTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        Integer bno = boardDao.selectAll().get(0).getBno();
        assertTrue(boardDao.delete(bno, boardDto.getWriter())==1);
        assertTrue(boardDao.count()==0);
    
        assertTrue(boardDao.insert(boardDto)==1);
        bno = boardDao.selectAll().get(0).getBno();
        assertTrue(boardDao.delete(bno, boardDto.getWriter()+"222")==0);
        assertTrue(boardDao.count()==1);
    
        assertTrue(boardDao.delete(bno, boardDto.getWriter())==1);
        assertTrue(boardDao.count()==0);
    
        assertTrue(boardDao.insert(boardDto)==1);
        bno = boardDao.selectAll().get(0).getBno();
        assertTrue(boardDao.delete(bno+1, boardDto.getWriter())==0);
        assertTrue(boardDao.count()==1);
    }

   @Test
    public void updateTest() throws Exception {
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);

        Integer bno = boardDao.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);
        boardDto.setBno(bno);
        boardDto.setTitle("yes title");
        assertTrue(boardDao.update(boardDto)==1);
    
        BoardDto boardDto2 = boardDao.select(bno);
        assertTrue(boardDto.equals(boardDto2));
    }

# 230222

- 게시판 페이징처리 
- sitemesh 재적용

# 230223

- 페이징처리
- write modify
	::못함 ㅠ 
- 보안점검
	파스타 
	:: nginx, UAA 조심 / 
	paasta , paasta-min , portal-container 
	:: cce완료 

	
	 ps -ef | grep nginx
	/var/vcap/packages/bpm/bin/tini -w -s -- /var/vcap/jobs/blobstore/bin/blobstore_nginx
	 /var/vcap/packages/nginx_webdav/sbin/nginx -c /var/vcap/jobs/blobstore/config/nginx.conf -g worker_processes 2;
	:client_body_temp_path
	ps -ef | grep nginx



# 230224 

- 페이징..
- 보안점검 엑셀 


/usr/sbin/apache2 
/etc/apache2/apache2.conf


/var/vcap/store/mariadb/bin/mysql
/var/vcap/store/mariadb/mariadb.cnf
/var/vcap/data/root_tmp/mysql.sock


/var/vcap/data/packages/apache2/b7ab7f1bb35ffb1c1af451c3d752c649b767ca71/conf/httpd.conf

= portal 
	control-tomcat min4vms 	:: 완료 
	database-nginx 	min4vms	:: 완료 
= portal api
	binary stroage apache 	:: 
- tomcat
	 find / -name "java*" | grep bin
	 ps -ef | grep tomcat
	 export JAVA_HOME=/var/vcap/data/packages/uaa/fe018723680f91c9919be3fcc14987968fb8dfad/jdk/

	 ${JAVA_HOME}/bin/java -version
	 openjdk version "11.0.17" 2022-10-18 LTS
	 OpenJDK Runtime Environment (build 11.0.17+7-LTS)
	 OpenJDK 64-Bit Server VM (build 11.0.17+7-LTS, mixed mode)

	 /var/vcap/data/uaa/tomcat/conf/
	 /var/vcap/packages/uaa/tomcat
	 /var/vcap/data/uaa/tomcat/webapps

	 /var/vcap/data/packages/uaa/fe018723680f91c9919be3fcc14987968fb8dfad/tomcat/webapps
	/var/vcap/data/uaa/tomcat/webapps

	/usr/sbin/apache2

	/etc/apache2/

	/var/vcap/packages/uaa/jdk/bin/java
	/var/vcap/data/uaa/tomcat/conf/
	/var/vcap/data/packages/uaa/fe018723680f91c9919be3fcc14987968fb8dfad/tomcat/webapps



	paasta-7vms 
		: control - Tomcat 수정 
		: singleton-blobstore - nginx 
	
	paasta-4vms control uaa log 	
	
	/var/vcap/packages/nginx_webdav/sbin/nginx
	 /var/vcap/jobs/blobstore/bin/blobstore_nginx
	 /var/vcap/jobs/blobstore/config/nginx.conf 



# 230227

 - 페이징
 - 읽기 쓰기 수정 삭제 기능

	 

오후(화,목,금)
 - k8s docs
 - minikube
	 - 기본 익히기



# 230302

- k8s 실습
- pod 
- 

# 230303

- replicaset
- deployment
- service 

# 230306

- 호환성 support
- kubernetes service / ingress :: 완
- k8s: RabbitMQ 구성 & sample App 연결
- k8s kubespray/kubeedm으로 두번 다 설치 해보깅

# 230307

- kuberntes 환경축하기 : 완료
- kubespray 환경구축하기 : 

# 230308
- 


# 230308
kubespray 환경구축하기 : 

k8s : mariadb Config

- init.sql 적용(ConfigMap) 및 store data pvc 연결  
		mariadb에있는 기본적으로 생성되어있는 DB를 자동으로 insert 하는 것 
		mariadb의 개인 저장소 외에 blob storage와 연결 
- mariadb backup script 생성 (CronJob)
		mariadb의 백업 log를 시간순으로 정렬 / 그룹으로 맵핑 (CronJob) -> 주기적으로 backup하는 script
- Remote Client Access : mariadb.((system_domain)) -> nodePort"
		NodePort로 외부 접속 가능하게


mariadb

init.sql
apiVersion: v1
kind: ConfigMap
metadata:
  name: mariadb-initdb-config
data:
  init.sql: |
    CREATE DATABASE IF NOT EXISTS mydata;
    USE mydata;
    CREATE TABLE friends (id INT, name VARCHAR(256), age INT, gender VARCHAR(3));
    INSERT INTO friends VALUES (1, 'Abhijeet', 32, 'm');
    INSERT INTO friends VALUES (2, 'Anjali', 29, 'f');
    INSERT INTO friends VALUES (3, 'Aayush', 27, 'm');
pvc 
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mariadb-pv-claim # pvc 이름
spec:
  storageClassName: mariadb-storage-class
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 50Gi


cronJob
https://kubernetes.io/ko/docs/tasks/job/automated-tasks-with-cron-jobs/



# 230309
kubespray 환경구축하기

#020310

nfs volume 생성 및 mount
pod deploy(test-pod)
configmap: 진행중



# 230313

configmap
deployment
service

작성

# 230314

호환성 검증 시나리오 작성 :: 수정사항이 있어 일시 중단

mariadb 업로드 


#230315
검증결과서 작성 및 업로드



# 230320
- 목표설정 대상자 : 레빗 전직원 & 메가존 전직원

- 목표설정 일정 : ~3월 24일 (금)

# 230321
readyonly 파일 허용되게 배포해보기.
안되면 일단  임시 경로로 바꾼 후 pod띄운 후 coronjob 진행 
backup용 pod 띄워

ㅡㅡ
비번안쓴다했는데ㅡㅡ
1



# 230323

2023년도 다 적
확인서 발급 완료건
22년 완료된 것 기준으로 업데이트 

email 첨부파일 -> 현황  보고 
google sheet -> 업데이트 시키기 

- 2022년 포탈 등재 완료된 것 업데이트
- 2023년 업데이트 된 건 모두 업데이트 ( 담당자/ 연락처/ 메일 ) : 완료
- 

\\192.168.0.24\PaaS-TA\파트폴더\기술지원팀\03.포털\03.공모전 포털
vm os 20.04 

svn -> git private repository 생성 ( 구축 후 다시 )

45.248.73.44

    String dbUSERNAME = "edmadmin";
    String dbPASSWORD = "master77!!";
    
    tomcat 9버전 (20.04에선 8버전 지원x)


    master77!!

# 20230324

DB작업 완료
db test 완료
port 전환 
:: 완료


#230329
- mariadb config
- ncp 시험


# 230330
- sub-task 주제파악하고 해내기;;;;;;;ㅏㅣㅜ;ㅣㅏㅓㅡ
- 호환성 전화 돌리기


# 230330

cronjob 진행중


# 230331

 mariadb-backup-script: 완료 
 산출물 작성 :: 완료



# 230403
docker 공부

# 230404
docker-jenkins 공부

# 230405

openlab 설치지원

# 230406
ssu,smu 도메인 설치 지원
pub-vm1 test 진행 guide 작성

paasta123!@#
*.openlab-06.kr    210.90.173.188
*.openlab-07.kr    210.90.173.190


# 230407
- 호환성 문의 응대
개인 공부
- k8s
도메인 등록 지원

# 230410
카카오 csp 조사
호환성 검증 시나리오 :: 진행중

이노그리드 인프라닉스  

# 230411

검증 시나리오 :: 완료

# 230412

테스트 환경 검증
ncp 공부

# 230413

28080에서 LB를 타고 Cluster로 오는건 알겠는데 그 이후  어떤 pod로 떨어지는지 ?

신청자 정보 테이블 날리기
회사정보 최대한 마스킹
이미지 로고 숨기기 

CV-RS-2022-008_파스-타 확장성 검증 시나리오 결과서_네이버클라우드
CV-RS-2022-013_파스-타 확장성 검증 시나리오 결과서_kt_cloud
CV-RS-2022-019_파스-타 호환성 검증 시나리오 결과서_케이에듀텍
CV-RS-2022-034_파스-타 호환성 검증 시나리오 결과서_유니닥스

# 230414

1. k8s: DevOps for MSA

   - CI/CD 환경 구축 및 MSA application 구현 

     helm + jenkins + argo CD (gitOps) + sonarqube
     https://tech.kakao.com/2021/07/16/devops-for-msa/
     \\192.168.0.24\파트폴더\기술지원팀\05.산출물\2023_산출물\0.sub_task_산출물\DevOps

2. openlab

   - IAM계정

   

# 230415

2.  

# 230417

젠킨스 , cicd 개념 공부 



# 230418

휴가

# 230419

1. 오픈랩 문의건 : 오픈랩 기술지원현황시트에 기입하기

- CI tool :: jenkins(git연동/install plugin/user생성)
- CD tool :: ArgoCD (CLI,app(ui))

  

# 230420

1. sub-task : ingress 설정
2. SW진단 가이드 test






# 230421

1. openstack project별 instance 모두 삭제하기.
	paasta-edu :: 완료
	paasta-monitoring :: 없음(완료)
	paasta-cvs :: 완료

2. 호환성 진행 : 나무기술 



# 230424
1. argocd 진행중

# 230425
1. argocd 진행중

# 230325
