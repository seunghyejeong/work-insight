
*230524

QNA 1
---
안녕하세요.

최근에 플레이파크 계정을 발급받은 (주)토란입니다.



컨테이너 플랫폼인 쿠버네티스를 이용한 방법으로 테스트를 하고 싶은데,

파스타에 대해서 잘 알지 못하다보니 발급 받은 계정으로 로그인하여 확인하여도 어디에서 어떻게 셋팅하는지 찾는데 어려움을 겪고 있습니다.



쿠버네티스 관련 셋팅을 체험판 플레이 파크에서 가능한지 알고 싶고, 만약에 가능하다면 그 방법에 대해 조언을 구할 수 있을까요?

---


1. 플레이파크 ap/cp 계정이 관리가 별도로 되고있음
사업관리팀에 ap/cp기준으로 발급 되었는지 체크.

	: 그러면 정리를하면 
		- 사업관리팀에서는 ap/cp 계정을 따로 발급하지 않고
		- 발급받은 계정으로 플레이파크를 들어가면 cp랑 연동되는 환경이 있어야하는데->이게 없음 : 플레이파크 cp는 클러스터로만 구축이 되어있는 상태. 
		- 이용할 수 있는 환경이 없어서 접근이 불가능했음.

2. 쿠버네티스 관련 세팅은 관련 가이드 첨부

---

답변대기


Playpark container platform 활용에 시스템 오류가 있어 점검 진행중입니다. 해당 부분 점검 완료 후 재안내 드리도록 하겠습니다.

감사합니다.






QNA2: 완료
---

안녕하세요

 

단독형 배포 포털 설치 가이드 관련 문의드립니다.

https://github.com/PaaS-TA/paas-ta-container-platform/blob/master/install-guide/container-platform-portal/paas-ta-container-platform-portal-deployment-standalone-guide.md#4.3

가이드 진행중 3.1.3. 컨테이너 플랫폼 포털 배포 스크립트 실행을 하였는데 

첨부 파일처럼 해당 메시지가 지속적으로 나옵니다.

 

감사합니다.

A:
1)파일경로?
2)재설치로 이한 이름 중복?
3) source uninstall-cp-portal.sh로 삭제 후 재설치
4) root로 진행?????



문의하신 사항에 대한 답변 드립니다.

해당 오류는 cluster 설치시 일부 구성요소가 누락되어 나타나는 오류로 확인됩니다.
cp-portal과 cp-cluster를 모두 삭제 후 재설치를 진행해 주시기 바랍니다.
설치 후 아래 명령어를 통해 해당 리소스가 설치 되었는지 확인하시면 됩니다.

`kubectl get all -n vault`
`/home/ubuntu/.env/`


감사합니다.


1. 삭제 후 재설치 요망 
2. Docker Hub Pull 횟수 체크


1) cp-portal-vars.sh
---
#!/bin/bash

source cp-portal-vars.sh

# copy and source the vault vars file
cp $VAULT_VARS_PATH/$VAULT_VARS .
---

cp /home/ubuntu/.env/cp-vault-vars.sh .


2) deploy-cp-portal.sh
---
# VAULT
VAULT_IP="${K8S_MASTER_NODE_IP}"                                                             # vault url
VAULT_PORT="31654"                                                                           # vault port
VAULT_ROLE_NAME="cp_role"                                                                    # vault role name
VAULT_VARS_PATH="/home/ubuntu/.env/"                                                         # vault vars file path
VAULT_VARS="cp-vault-vars.sh"                                                                # vault vars file name
---








QNA3: 완료
---
안녕하세요. 위세아이텍 연구기획부 주임 이종찬입니다.

PaaS-TA의 CP에서 APP 배포시 할당된 Container에 GPU 리소스를 할당하고자하는데요
PaaS-TA 문서에서는 자료를 찾을 수 없어서

CP의 GPU 리소스를 할당 여부와 방법이 궁금하여 메일드립니다.




A:
gpu 사용 여부는 iaas측에서 지원하는 것
iaas에서 gpu리소스를 할당 받을 수 있다고 해도 사용유무에는 cluster내에 관련 driver가 설치되어야함
그러나 공식적으로 cp 내에는 지원되고 있지 않음
하지만 사용자가 원하면 가능

cp에서 공식적으로 지원하지않는다 
iaas에서 할당 여부와 클러스터 내의 사용자가 설치하면 사용할 수 있다 



PaaS-TA container platform은 GPU 리소스 할당을 지원하지 않습니다. 
쿠버네티스 공식 문서에서 GPU 관련 정보를 제공하오니 아래 url을 참고 부탁드립니다. 

https://kubernetes.io/ko/docs/tasks/manage-gpus/scheduling-gpus/

감사합니다.




k8s gpu guide링크 첨부.





QNA4: 완료
---

안녕하세요

kubespray 설치중 문의 사항이 있어 질문 남깁니다.

source deploy-cp-cluster.sh 로 쿠버네티스 클러스터 설치중 

첨부 화면(문의.png)에서 넘어가질 않습니다.

하지만 kubectl get nodes / kubectl get pods -n kube-system

명령어로는 정상적으로 보입니다(문의2.png) 

Kubespray 설치가 제대로 완료된걸까요?

 
A:

문의.png는 kube-flow 설치 단계 중 'Deploy' 과정입니다. 
이 과정은 시간이 오래 걸릴 수 있으며 중간에 강제종료 되었다면 남은 설치 과정도 진행되지 못했을 것으로 예상됩니다.
그리하여 문의2.png에서 보이는 kube-system running상태 만으로 정상적으로 설치가 되었다고 볼 수 없습니다.
설치되지 못한 구성요소들은 portal 등을 배포하는데 필수적인 구성요소이기 때문에 재설치를 권장드립니다.


문의.png는 kube-flow 설치 과정 중 'Deploy' 단계를 보여주고 있습니다.
이 단계는 시간이 오래 걸릴 수 있으며, 중간에 강제 종료된 경우 남은 설치 과정도 진행되지 못했을 것으로 예상됩니다.
따라서 문의2.png에서 출력되는 상태만으로는 설치가 정상적으로 완료된 것으로 보기 어렵습니다.
설치되지 못한 구성 요소들은 portal 등을 배포하는 데 필수적이므로, 재설치를 권장 드립니다.


QNA5: 완료
---

안녕하세요.

어플리케이션에 바인딩 된 mysql 서비스에 접속하기 위해 cf env <어플리케이션 명>을 통해 mysql credentials를 확인 해본 결과 교육 자료에서는 ip주소로 나오는데 실제 화면에서는 ip 주소로 나오지 않습니다.

DB에 접속하기 위한 ip 주소 정보는 어디에 나와 있을까요?


A:

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
