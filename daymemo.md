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