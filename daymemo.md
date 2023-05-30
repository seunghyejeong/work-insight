daymemo


[mysql]
# query문 수정
'utf8' is currently an alias for the character set UTF8MB3, but will be an alias for UTF8MB4 in a future release. Please consider using UTF8MB4 in order to be unambiguous 
	: 쿼리문 수정시 참고
# dbver guide 작성 요망
	참고) workbench도 번개모양 누르면 query문 모두 실행 가능 


[redis]
# 관리자 권한으로 실행 
	: window 11 대응 
	: (나) 속성-권한 변경으로 수정하여 사용함

[java]
# 환경 변수 설정
	시스템 변수 
	: path -> 'double' click

[IDE]
# `File` > `open project from file system` 선택 > `Directory` 선택 후 소스파일에서 Repository 선택
	: 알아듣기 애매한 문장

# 프로젝트 import 후 `run as` > `maven build`
	: maven build에 대한 프로젝트가 모두 해당하면 '모두'라고 명시하면 좋을듯 

# server 설정 
	: 각 프로젝트당 server에서 할 수 있는 모든 설정은 한번에 하고 가면 좋겠어요 

# server start issue
	: timeout
	: psta-web-user / psta-web-user-en
	: timeout setting 120(80초 모자름)
.



[PaaS-TA] 나무기술 파스-타 호환성 확인 사전협의 회의 일정 공유


안녕하세요. 개방형 클라우드 플랫폼 센터 호환 검증팀 정승혜입니다.

보내주신 나무기술의 파스-타 호환성 확인 신청서를 확인했습니다.

아래 일정을 참고하여 센터에 방문해 주시기 바랍니다.

사전협의 회의 전 아래 요청 항목을 회신 부탁드립니다.

감사합니다.



Reference :
----------------------------------------------------------------------------------------------------
- 일시 :: 2023년 5월 3일(수) 오전 10시
- 장소 :: 서울 상공회의소 본관 7층 7호 개방형 클라우드 플랫폼 센터(서울 중구 세종대로 39)

## 회신 요청 항목
- 방문자 성함 및 연락처
- 제품 소개서 및 아키텍처


to : dk.lee@namutech.co.kr
cc : hongjoon.kim@namutech.co.kr, HyoJin Jin <jins@irevit.co.kr>
	REVIT 이세영 전임님 <lsy77877@irevit.co.kr>



호환성 확인 서비스는 다양한 SW·서비스들이 파스-타 환경에서 동작하는지 확인하는 서비스임 
NKS의 경우 파스-타 환경에서 하나의 Service가 동작하는 것과 마찬가지 
그러므로 호환성을 위해서는 환경을 직접 구축 해보는것이 맞고
파스-타를 경험해보고 싶다면 NKS 환경으로도 무방해보임 
kubectl apply -f /home/ubuntu/workspace/dev-tools/paas-ta-container-platform-deployment/standalone/applications/ingress-nginx-1.4.0/deploy.yaml\


---

cd ~/edu-msa-file/Docker/edu-msa-board/
podman build --tag edu-msa-board .
podman tag edu-msa-board seunghyejeong/edu-msa-board:1.0
podman push seunghyejeong/edu-msa-board:1.0

cd ~/edu-msa-file/Docker/edu-msa-comment
podman build --tag edu-msa-comment .
podman tag edu-msa-comment seunghyejeong/edu-msa-comment:1.0
podman push seunghyejeong/edu-msa-comment:1.0

cd ~/edu-msa-file/Docker/edu-msa-user/
podman build --tag edu-msa-user .
podman tag edu-msa-user seunghyejeong/edu-msa-user:1.0
podman push seunghyejeong/edu-msa-user:1.0

cd ~/edu-msa-file/Docker/edu-msa-ui/ 
podman build --tag edu-msa-ui .
podman tag edu-msa-ui seunghyejeong/edu-msa-ui:1.0
podman push seunghyejeong/edu-msa-ui:1.0

cd ~/edu-msa-file/Docker/edu-msa-zuul/
podman build --tag edu-msa-zuul .
podman tag edu-msa-zuul seunghyejeong/edu-msa-zuul:1.0
podman push seunghyejeong/edu-msa-zuul:1.0

deployment.apps/edu-msa-comment
IfNotPresent Always


docker pull seunghyejeong/edu-msa-ui:1.0


---
(예시) 

ㅇ미들웨어 : WEB/WAS ... ...

ㅇ백엔드 서비스 : AI, DevOPS 도구 ... ...


미들웨어(탑재형)		: saas/모듈(ex;화상회의/pdf전환 등) web/was, 라이브러리 
백엔드 서비스(연동형)	: AI플랫폼, 빅데이터 플랫폼 등 별도 플랫폼 형태로 제공되는 SW


연동형	파스-타 외부에 구축되어 파스-타와 서비스브로커 또는 
		별도 API를 통해 연계·활용되는 SW
		- DB, MessageQueue, Storage 등 백엔드 서비스
		- AI플랫폼, 빅데이터 플랫폼 등 별도 플랫폼 형태로 제공되는 SW
탑재형	파스-타에서 제공하는 컨테이너 엔진에서 실행 가능한 앱, 빌드팩, 패키지형태로 
		파스-타의 컨테이너 상에 설치되어 작동하는 SW
		- WEB/WAS, 라이브러리 등의 미들웨어성 SW
		- SaaS, 모듈

Middleware ? backend service ? Tell me which type is it with the closest type
What is the closest type of backend service?

티맥스클라우드				PaaS platform(backend)
인프라닉스					PaaS platform(backend)
오픈나루						web-agent(middleware)
아울시스템즈					DB service(backend)
큐브리드						DB service(backend)
글로브포인트					VR platform(backend)
구름							AI/SW platform(backend)
원더풀플랫폼					API (middleware)
팀플백						AI platform (backend)
로데브						management service(backend)
두드림시스템					API(middleware)
티맥스에이아이				saas(middleware)
위두커뮤니케이션즈			saas(middleware)
에이콘컴퍼니					VR service(backend)
버추얼랩						DB service(backend)
알티베이스					DB service(backend)
네이버 클라우드 				service-broker(backend)
네이버 클라우드 				service-broker(backend)
네이버 클라우드 				service-broker(backend)
케이에듀텍 					saas(middleware)
비즈데이터					saas(middleware)
스패로우 					security management service(backend)
사이람 						saas(middleware)
제온스 						saas(middleware)
비즈니스온커뮤니케이션		management service(backend)
심플랫폼						saas (middleware)
소프트인 					saas (middleware)					
엠에이치엔씨티				saas (middleware)
유니닥스						saas (middleware)
인스웨이브시스템즈 			management service(backend)
메디치소프트 					AI platform(backend)
메디치소프트					AI platform(backend) 
씨앤에프시스템(주) 			DB service(backend)


ㅇ미들웨어 : web-agent, API, SaaS

ㅇ백엔드 서비스 : PaaS platform, DB service, VR platform, AI/SW platform, management service, service-broker, security management service 





# playpark 점검

bosh vms
bosh -d portal-api 
monit summary
monit restart all
var/vcap/sys/log  error.log 확인

사용자가 몰려 서버 불안정할 경우
mariadb max connection 조절 
기존 150 -> 500

# 담당업무

playpark, qna 전담



trainer
trainer2023!@#

https://api.paas-ta.org


admin
pstamaster23!




VCAP_SERVICES: {
  "Mysql-DB": [
    {
      "binding_guid": "300abf9d-b6a6-43d2-a9ef-0ba3646ad5b1",
      "binding_name": null,
      "credentials": {
        "hostname": "4467ccc4-cfb3-4a30-a290-d0ef1bc397d0.proxy.default.mysql.bosh",
        "name": "op_817ac390_830a_4ca2_9cfd_4c8bcfde6709",
        "password": "5e0ec22deeaa4f03",
        "port": "13307",
        "uri": "mysql://d1d80674a1aff50f:5e0ec22deeaa4f03@4467ccc4-cfb3-4a30-a290-d0ef1bc397d0.proxy.default.mysql.bosh:13307/op_817ac390_830a_4ca2_9cfd_4c8bcfde6709",
        "username": "d1d80674a1aff50f"
      },
      "instance_guid": "817ac390-830a-4ca2-9cfd-4c8bcfde6709",
      "instance_name": "mysql-service-instance",
      "label": "Mysql-DB",
      "name": "mysql-service-instance",
      "plan": "Mysql-Plan2-10con",
      "provider": null,
      "syslog_drain_url": null,
      "tags": [
        "mysql",
        "document"
      ],
      "volume_mounts": []
    }
  ]
}

db ip: proxy ip (10.0.12.71)

1) bosh를 통해 배포된 service로 접근하는 하기 
        -  ` bosh -d mysql ssh mysql/0 `
2) 관리자 모드 실행
        - ` sudo su `
3) JDBC url을 사용하여 ping 
  - ` ping f96e17f8-7fe7-467b-b2e8-c6ea071068b5.proxy.default.mysql.bosh `

mysql -u d1d80674a1aff50f -h 10.0.12.71 -p --port 13307를 통해 접속ㅊ을 확인 할 수 있다.

그러나 이는 ssh터널링을 했기 때문에 별도의 처리 없이 가능하고 로컬 클라이언트 툴을 이용시에는 proxycap을 이용하거나 별도의 vm환경이 필요하다.


---
안됨


안녕하세요 해당 문의에 대해 답변드립니다.

ip를 알지 않아도 cf env APP_NAME으로 출력되는 정보로 접속 할 수 있습니다.
cf ssh를 통한 ssh 터널링을 진행한 후 로컬에서 접속을 시도해보시기 바랍니다.

1. `$cf ssh APP_NAME LOCAL_PORT_NUM:HOSTNAME:APP_PORT_NUM `
2. client tool을 이용한 접속 
	- hostname: localhost / port: local_port_num
* LOCAL_PORT_NUM: 로컬에서 사용할 임의의 포트 번호

접속이 확인된 후에도 ip 주소를 알고싶은 경우에는 PaaS 관리자에게 별도 문의바랍니다.

cf ssh 터널링 관련 참고영상 함께 첨부드립니다. 
https://youtu.be/g04tk1EDIT4?t=3212

감사합니다.





hostname 내부도메인으로 접속이 된다면 ip를 알지 않아도 접속 가능함.
guide url 함께 ㅇ첨부


DB에 접속하기 위한 ip 정보를 찾는 방법입니다.
해당 database ip는 설치시 생성된 mysql의 proxy ip입니다. 이는 mysql ssh 터널링으로 직접 확인 하실 수도 있습니다.
* ssh: 보안 접속 시스템

- ㄷ턋
mysql proxy ip 확인하기
$ bosh -e ${BOSH_ENVIRONMENT} -d mysql vms

- ssh 터널링으로 확인하기
1) bosh를 통해 배포된 service로 접근하기 
$ bosh -d mysql ssh mysql/0
2) 관리자 모드 실행 
$ sudo su
3) ping 명령어로 ip 확인하기
$ping HOSTNAME
---




cf ssh 터널링을 통해 hostname으로도 접속 가능


+ 접속이 확인된 후에도 ip 주소를 알고싶은 경우에는 PaaS 관리자에게 별도 문의바랍니다.



cf ssh mysql-sample-app -L 9999:10.10.4.14:3306

cf ssh mysql-sample-app -L 5002:4467ccc4-cfb3-4a30-a290-d0ef1bc397d0.proxy.default.mysql.bosh:13307



안녕하세요 해당 문의에 대해 답변드립니다.

ip 주소를 알지 않아도 cf env {APP_NAME}으로 출력되는 정보로 DB에 접속 할 수 있습니다.
cf ssh를 통한 ssh 터널링을 진행한 후 로컬에서 접속을 시도해보시기 바랍니다.

아래는 ssh 터널링을 하여 툴에 접속하는 예시입니다.

1. 앱에 바인딩 된 mysqldb 정보 확인
$ cf env {mysql 서비스가 바인딩 된 APP_NAME}

e.g) 
$ cf env testapp

2. cf ssh를 활용한 포워딩
$ cf ssh {APP_NAME} -L {local port}:{mysql의 hostname}:{mysql의 port}
-> mysql의 hostname과 port는 cf env에서 확인 가능하며, local port의 경우 현재 사용하는 로컬 환경에서 사용하고 있지 않는 임의의 포트 입력

e.g)
$ cf ssh testapp -L 9999:abcdef-1234567.proxy.default.mysql.bosh:13306
 
3. ssh 터널링 유지한 상태에서 로컬환경에서 mysql 접속 툴을 사용하여 접속


접속이 확인된 후에도 ip 주소를 알고싶은 경우에는 PaaS 관리자에게 별도 문의바랍니다.

cf ssh 터널링 관련 참고영상 함께 첨부드립니다. 
https://youtu.be/g04tk1EDIT4?t=3212

감사합니다.



1. 앱에 바인딩 된 mysqldb 정보 확인
$ cf env {mysql 서비스가 바인딩 된 앱명}

e.g) 
$ cf env testapp

2. cf ssh를 활용한 포워딩
$ cf ssh {앱명} -L {local port}:{mysql host ip}:{mysql host port}
-> mysql 관련 host ip와 port는 cf env에서 확인 가능하며, local port의 경우 현재 사용하는 로컬 환경에서 사용하고 있지 않는 임의의 포트 입력

e.g)
$ cf ssh testapp -L 9999:172.27.0.9:13306
 
3. ssh 터널링 유지한 상태에서 로컬환경에서 mysql 접속 툴을 사용하여 접속

감사합니다.




---
- zip 다운 -> cf push(sidecar에 올라가는)까지의 과정을 담은 guide (ppt)
- 환경 NHN test환경 사용
- guide는 portal-ui/cf push 두가지 나오면 좋고 아니면 portal-ui
- \\192.168.0.24\PaaS-TA\03. 2022년 자료\03. 행사\01. 대국민홍보\2022년 8차 파스-타 테크 엣지\발표자료 : env 참고하여 작성 (템플릿 사용 가능)



PaaS-TA on Ncloud 는 공공기관용에서만 서비스를 제공합니다. 





문의하신 사항에 대한 답변 드립니다.

PaaS-TA on Ncloud는 네이버 클라우드 플랫폼 공공기관용에서만 서비스를 제공합니다.
공공기관용 PaaS-TA on Ncloud에 대한 설치 지원 및 자세한 내용은 해당 CSP사 측에 문의하시기 바랍니다.
민간기관용에 PaaS-TA 설치 지원은 "공공기관(별도 사업을 수행하지 않는 기관)"만 지원해 드립니다. 


PaaS-TA on Ncloud에 대한 정보는 아래 링크에서 확인 가능합니다.
https://www.gov-ncloud.com/product/hybridPrivateCloud/paasTaonNcloud

감사합니다.


/home/ubuntu/workspace/board-vm-main/out/artifacts/boardDBProject_jar


DuplicatesStrategy.INCLUDE
DuplicatesStrategy.INCLOUDE


build/libs/boardDBProject-0.0.1-SNAPSHOT.jar


tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}



build/libs/boardDBProject-0.0.1-SNAPSHOT.jar


applications:
- name: bootsample-230523
  memory: 2048M
  path: build/libs/boardDBProject-0.0.1-SNAPSHOT.jar 
  buildpacks:
    - paketo-buildpacks/java
  env:
    BP_JVM_VERSION: 11
    server_port: 8080
    BP_SPRING_CLOUD_BINDING_DISABLE: true


i7nmidbjzx3bimc02x82

[오픈랩] 한국공학대 springboot-sample-app 파스타 적용하기 가이드 작성 완료하여 메일 공유드렸습니다. 확인 부탁드립니다

[호환성 - 메디치소프트]
사전협의 
30일에는 센터 일정이 있어서 ㄴㄴ
6 5~ 9 원하는 시간대가 있으면 후보