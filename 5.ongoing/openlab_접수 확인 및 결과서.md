openlab_접수 확인 및 결과서


---
admin, 도메인 기본 접속정보와 동일

# 포탈 정보
고려대 : portal-web-user.apps.openlab-04.kr
한국공학대 : portal-web-user.apps.openlab-05.kr
상명대 : portal-web-user.apps.openlab-06.kr
숭실대 : portal-web-user.apps.openlab-07.kr


# 포탈을 이용한 샘플앱 배포
고려대 : dd.apps.openlab-04.kr
한국공학대 : dd.apps.openlab-05.kr
상명대 : dd.apps.openlab-06.kr
숭실대 : dd.apps.openlab-07.kr

# 이슈사항 
- object storage가 pod형태로 정상적으로 기동하지 않아 VM 수동 설치하여 구성

# Object Stroage 정보
고려대 : 133.186.223.127
한국공학대 : 133.186.228.36
상명대 : 172.25.3.102
숭실대 : 172.25.5.136

keystone port : 5000
proxy port : 10008

고려대, 한국공학대 : ubuntu 22.04
상명대, 숭실대 : ubuntu 20.04

sidecar는 service-broker를 제공하고 있지 않아 이전과 같은 환경에서 제공 될 수 없다. 단독 mysql-service를 이용해야 하고 
---







--- 
QnA

# 상명대
Q:
안녕하세요, 데이터베이스 관련 문의 드립니다. 작년 강의시에는 db service/nfs service를 마켓플레이스에서 바인딩해서 강의를 진행했습니다. 현재 db/nfs 리소스를 따로 전달받았는데요,  지난번 설명해주실때 사이드카 플랫폼에서는 마켓플레이스에 커스텀 서비스 브로커 등록이 불가하다 하셨습니다. CSP에서는 현재 마켓플레이스에 서비스가 하나도 등록되어있지 않구요. 그럼 전달해주신 db 계정정보를 40명의 학생이 공유하여서 (옛스러운 방식으로) db를 사용해야 하는 상황인가요?

A:
db 계정정보를 40명이서 공유하는 방식보단 학생별 계정을 부여하시여 각각 계정/database별 사용을 권장드립니다
이전에 말씀해주신 바와 같이 서비스 브로커를 통한 마켓플레이스 지원 및 App SSH 접근 지원이 불가하여 타 오픈랩을 운영하는 대학에서도 이슈가 발생하고 있습니다.
해당부분 내부적으로 검토하여 효율적인 운영을 하실 수 있도록 검토 중이오니 정책이 변경되면 내용 공유드리도록 하겠습니다. 


# 한국공학대


Q: 
안녕하세요. NHN 포털은 확인하였습니다. 감사합니다. DB는 파스타의 서비스로 제공해주는 DB를 말씀드린 거였습니다. 포털에 들어갔더니 카탈로그에서 잡히지 않더군요. 파스타에서 제공하는 MySQL DB를 사용할 수 있는 방법이 없을까요? 실습하는 학생들도 그런 방식으로 써야 합니다. 
A: 
교수님 안녕하세요. 
현재 사용하시는 오픈랩 플랫폼은 파스-타 사이드카 플랫폼입니다. 
기존 활용하셨던 파스-타 어플리케이션 플랫폼의 기능은 거의 사용하실 수 있으나, 일부 기능의 제한이 있습니다. 

말씀하신 "파스타에서 제공하는 MySQL DB를 사용할 수 있는 방법"은 Service-broker 를 통한 서비스 방식입니다.
Service-broker 를 통한 마켓플레이스 등록은 현재 사이드카 플랫폼 내에서 지원하고 있지 않습니다. 
하여 CSP사(NHN클라우드)에서 제공하는 서비스나, 별도의 서비스를 구축하여 사용하는 제약조건이 있습니다. 
(언급해 주신 Mysql 역시 별도 서비스를 구축하여 사용하므로 App Source 내에서 db connection을 설정해주셔야 합니다.)

해당부분 참고 부탁드리겠습니다. 



# 폴리텍

```
Q: 
파스타 상세정보에 보면, API_앤드포인트가 안보이는데, 표시되는 곳이 있나요? 메일로 API Endpoint 알려주셔서 그걸로 접속했는데, 콘솔에선 볼수 있나 해서요.
A: 
안녕하세요 교수님 

보내주신 이미지에 나와있는 GlobalDNS 앞에 'api.'을 붙여서 접속하시면 됩니다. 
'api.'까지 들어가있는 API Endpoint 정보는 나와있지 않은 것으로 보입니다.

네이버클라우드 PaaS-TA 사용가이드에 보면 
cf CLI 인증시 Global DNS 정보를 이용해서 실행하라고 되어있습니다.

$ cf login -a api.sys.openlab-03.kr --sso

아래 가이드 링크와 해당 부분 캡처 이미지 첨부드립니다.

감사합니다.
```
```
Q:
전에 주신 manifest 파일에서 포트 번호를 11001번으로 변경했는데.배포가 안되서요.배포 가능한 포트번호 영역이 있나요? 8080 으로 변경햇을때, 배포는 잘되고, 접속도 됩니다.
A: 
안녕하세요 교수님 

어제 문의주신 내용 찾아보니 이전 buildpack은 cf CLI로 원하는 포트를 추가하여 사용할 수 있도록 지원했으나,
현재 paketo buildpack은 8080 포트로만 사용하도록 지원하고 있습니다.

또한, 한 컨테이너 안에서 하나의 포트로만 사용이 가능합니다.
부득이하게 여러개의 포트 사용이 필요하시다면 직접 도커 이미지로 말아서 쿠버네티스 환경에 배포하는 방법이 있습니다.

감사합니다.

Q:
cf push 마다 독립적인 컨테이너에 배포되는 건가요?
만약 어플2개를 만들어 각 어플마다 cf push를 한다면, 각 yml 파일마다 server_port는 8080 동일하게 하면 되나요?
A:
네 맞습니다.  
따로 특별한 설정을 하지않고 cf push로 앱 배포를 할 경우 각각의 컨테이너에 앱이 배포됩니다. 
server_port도 8080으로 동일하게 지정해주시면 됩니다. 

Q:
ssh 접속하는데. 접속이 안되는데. 이유가 뭘까요??ㅠㅠ
추가적으로.서비스를 앱에 바인딩 한후, restage 에러가 발생됩니다.어제 기억으로 네이버파스타가 restage를 지원하지 않는다고 들었는데. 서비스 바인딩 한후, restage 대신 restart 하면 될까요? cf restart kopo01App 명령어로요
A:
교수님 ssh와 서비스 바인딩 restage 관련 문의 답변입니다.

사이드카 환경에서는 ssh는 지원하지 않으며 restage의 경우 image 재구성에 이슈가 있어 지원하지 않는다는 표현을 드리고 있습니다. 

AP의 경우 app bind 후 restage 과정을 진행하였으나 
sidecar의 경우 app bind 후 cf set-env 환경 변수 설정 후 restage를 해야 하는 과정으로 진행 해주시면 됩니다.
(아까 공유드린 네이버 클라우드 플랫폼 사용가이드에서 "서비스 Bind" 3번 항목)

Q:
SSH가 지원되지 않는다면, MYSQL 서비스를 생성 후, 테이블 생성은 어떻게 해야 할까요? 
이전에는 SSH 터널링으로 내 PC와 포트를 연결해서 클라이언트툴인 SQL-GATE 등 파스타의 MYSQL에 접속한 뒤, 테이블을 생성했습니다.

A:
안녕하세요. 교수님

mysql 내부 및 외부에서 접근하는 방법에 대해 안내 드립니다.

service instance 를 생성하게 되면 NCP 콘솔상의 Cloud DB for Mysql 서비스에 새로운 mysql 인스턴스가 생성됩니다.
생성된 mysql 인스턴스에 접속하는 방법은 아래와 같습니다. 

# 제약조건 : 신규로 생성된 mysql의 경우 해당 ACG(방화벽)에 inbound 대상을 추가해줘야 합니다. 

1. 터널링을 통한 외부 접속 

tunnel vm 정보 
server name : paasta-admin-inception
public ip : 211.188.69.123
user : ncloud
pw : E9+B!$46GDAg

url  : db-1htq9-kr1.vpc-cdb.gov-ntruss.com
user : bind된 user (cf env <appname> 으로 확인가능)
pw : bind된 pw (cf env <appname> 으로 확인가능)

2. 내부 inception 에서 mysql client cli 를 통한 접근 

server name : paasta-admin-inception
public ip : 211.188.69.123
user : ncloud
pw : E9+B!$46GDAg

$ mysql -h db-1htq9-kr1.vpc-cdb.gov-ntruss.com -u nsb_168378574358 -p ILTiX2LpqjObesk_to4=

혹 위의 과정이 번거로우시다면 서비스 바인딩 형태가 아닌 독립적인 mysql 서비스를 구성해 놓았으니 해당 서비스 이용하시는 방법도 있습니다 : paasta-mysql 

Q: redis 생성 에러
A:
안녕하세요 교수님

어제 문의주셨던 redis 서비스 생성 오류 답변드립니다.

해당 이슈는 CloudDB for Redis 서비스에 신규 버전이 출시되면서 서비스 브로커에서 생성하는 ConfigGroup 버전과 일치하지 않아 발생한 문제입니다.

아래 예시처럼 cloudRedisImageProductCode 설정을 추가해서 재시도 부탁드립니다.

cf create-service cdbredis Simple-HA redis-sample -c '{

      "cloudRedisUserName": "****",

      "cloudRedisUserPassword": "****",

      "cloudRedisServiceName": "redis-sample",

      "cloudRedisServerNamePrefix":"nsb",

      "cloudRedisImageProductCode": "SW.VDBAS.VRDS.LNX64.CNTOS.0708.REDIS.624.B050"

  }'


해당 가이드 url 같이 공유드립니다.
https://guide-gov.ncloud-docs.com/docs/k8s-paasta-paastansb-redis
```


*230515


Q: 
로그인한 정보는 cf env kopo01App 명령어를 통해 아이디, 패스워드 확인을 했습니다.

궁금한 점은 mysql 서비스 생성할때, 계정 정보를 입력하는데, 그 계정으로 접속이 안되는 건가요?
클라이언트 툴로 생성된 계정 정보를 확인하니, poly 계정은 생성되어서요.
poly 계정에 접속하니, 접속은 되는데, myDB로 생성한 DB에 접속 권한이 없네요.

DB접속은 cf env의 계정말고, 서비스 생성될때, 설정한 내가 만든 계정인 poly 계정으로 접속하면 되나요?

A: 
네 cf env의 계정과 'poly'계정은 database에 같은 권한을 가지고 있으며, poly 계정 또는 cf env계정 둘 다 사용이 가능하므로 기호에 맞는 계정을 사용하시면 됩니다.


cf env이 아닌 새로 설정한 계정으로 접속 가능한지 문의
각 계정의 권한 조회 및 로그인 테스트 진행 후 안내 

```console
+--------------------------------------------------------------------------------------------------------------------------------------+
| Grants for nsb_168378574358@%                                                                                                        |
+--------------------------------------------------------------------------------------------------------------------------------------+
| GRANT PROCESS, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT, CREATE USER ON *.* TO `nsb_168378574358`@`%` WITH GRANT OPTION |
| GRANT SHOW_ROUTINE ON *.* TO `nsb_168378574358`@`%` WITH GRANT OPTION                                                                |
| GRANT ALL PRIVILEGES ON `myDB`.* TO `nsb_168378574358`@`%` WITH GRANT OPTION                                                         |
| GRANT SELECT ON `mysql`.* TO `nsb_168378574358`@`%` WITH GRANT OPTION                                                                |
| GRANT SELECT, EXECUTE ON `sys`.* TO `nsb_168378574358`@`%` WITH GRANT OPTION                                                         |
| GRANT SELECT ON `performance_schema`.* TO `nsb_168378574358`@`%` WITH GRANT OPTION                                                   |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone_leap_second` TO `nsb_168378574358`@`%`                                            |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone_name` TO `nsb_168378574358`@`%`                                                   |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone_transition_type` TO `nsb_168378574358`@`%`                                        |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone_transition` TO `nsb_168378574358`@`%`                                             |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone` TO `nsb_168378574358`@`%`                                                        |
+--------------------------------------------------------------------------------------------------------------------------------------+
+--------------------------------------------------------------------------------------------------------------------------+
| Grants for poly@%                                                                                                        |
+--------------------------------------------------------------------------------------------------------------------------+
| GRANT PROCESS, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT, CREATE USER ON *.* TO `poly`@`%` WITH GRANT OPTION |
| GRANT SHOW_ROUTINE ON *.* TO `poly`@`%` WITH GRANT OPTION                                                                |
| GRANT ALL PRIVILEGES ON `myDB`.* TO `poly`@`%` WITH GRANT OPTION                                                         |
| GRANT SELECT ON `mysql`.* TO `poly`@`%` WITH GRANT OPTION                                                                |
| GRANT SELECT, EXECUTE ON `sys`.* TO `poly`@`%` WITH GRANT OPTION                                                         |
| GRANT SELECT ON `performance_schema`.* TO `poly`@`%` WITH GRANT OPTION                                                   |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone_leap_second` TO `poly`@`%`                                            |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone_name` TO `poly`@`%`                                                   |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone_transition_type` TO `poly`@`%`                                        |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone_transition` TO `poly`@`%`                                             |
| GRANT INSERT, UPDATE, DELETE ON `mysql`.`time_zone` TO `poly`@`%`                                                        |
+--------------------------------------------------------------------------------------------------------------------------+
11 rows in set (0.00 sec)

```

```console
# cf 계정에 org,space role 부여

cf login -a api.sys.openlab-03.kr --sso --skip-ssl-validation
CF login as customer account (root account)

cf create-org admin
cf target -o admin

cf create-space admin -o admin
cf target -o admin -s admin

cf set-org-role 9eda1f10-d7ff-11ec-84eb-005056a73730 admin OrgManager
cf set-space-role 9eda1f10-d7ff-11ec-84eb-005056a73730 admin admin SpaceManager
cf set-space-role 9eda1f10-d7ff-11ec-84eb-005056a73730 admin admin SpaceDeveloper



cf set-quota test-org trainee


864113a0-d10b-11ec-9996-005056a7e1b2 root
9eda1f10-d7ff-11ec-84eb-005056a73730 paasta-ta-01
893253b0-d7fc-11ec-9996-005056a7e1b2 paasta-ta-03
```


*230516


Q:
로그 관련해서 궁금한 점 있습니다.

이전에는 cf ssh가 가능해서, Spring Boot의 Logback로 로그 파일을 생성하고, 그 로그 보면서 디버깅이 가능했는데, 

cf ssh가 안되니, Logback에서 생성되는 로그 내용을 확인할수 없어요.

혹시 엘라스틱서치와 같은 ELK로 로그 생성하면 되는지요?

파스타에서 ELK 제공하는지 해서요.

파스타와 상관없이 별도로 네이버에서 제공하는 ELK를 써야 하는지 해서요.

A:
cloud log analytics ?
가 아닌 엄청 복잡한 로그 형태를 console에 출력해야하는 번거로움.



[NCP]
wokernode NotReady 상태 발생 
```
[일반 답변] [네이버 클라우드 플랫폼] 문의에 답변드립니다.

안녕하세요. 네이버 클라우드 플랫폼입니다.



Kubernetes Service 이용 중 불편을 드려 죄송합니다.



paasta-nodepools-dev-w-nl6 노드가 커널 버그로 인해서 10:23 ~ 11:02 사이에 kubelet이 재시작 되었고 이로 인해 노드상태가 NotReady로 표시되었습니다.



공공 클라우드의 경우 KISA 클라우스 서비스 보안 인증 CSAP 권고에 따라 5.4.0-132-generic 커널을 사용하고 있으나, 해당 버전에 현재 버그가 존재하는 상황입니다.



커널 버그는 아래 링크 참고 부탁드립니다.

- https://bugs.launchpad.net/ubuntu/+source/containerd/+bug/1996678



내부적으로 위 커널 버그 조치 방안을 모색 중이며 고객님의 안정적인 서비스 이용을 위해 지속적으로 모니터링을 진행중입니다. 사용 도중 문제 발생 시 문의 주시면 빠르게 해결할 수 있도록 하겠습니다.



더 궁금하신 사항은 언제든지 고객지원으로 문의 부탁드립니다.

감사합니다.
```

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
openlab을 사용함에있어 각 대학이 추구하는 교육 과정에 맞지 않은 환경을 제공하고 있다고 판단 (cf ssh 불가능, db 외부접속 제한 등) 하여 이러한 환경을 바꾸고 재설치하는 안건으로 NIA와 협의 및 내부 검토 진행중 해당 관련 문의가 오면 응대 23.05.16
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>