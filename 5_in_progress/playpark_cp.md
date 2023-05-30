
# ap - cp playpark 연동
- playpark ap에 cp playpark를 service로 등록한다.

https://github.com/PaaS-TA/paas-ta-container-platform/blob/master/install-guide/container-platform-portal/paas-ta-container-platform-portal-deployment-service-guide.md

- 3.1.2를 수행하기 전 keycloak 설정을 진행한다.
	: tls적용.pdf 참고 => CP 설치 전까지 진행
	: https://github.com/PaaS-TA/paas-ta-container-platform/blob/master/install-guide/container-platform-portal/paas-ta-container-platform-portal-deployment-keycloak-tls-setting-guide.md#2-keycloak-tls-%EC%84%A4%EC%A0%95
	2번 guide까지 진행
- 이후 공식 guide로 돌아와 설치 진행
- guide 4의 `컨테이너 플랫폼 포털 사용자 인증 구성 Deployment 다운로드`는 AP Inception에서 진행


1. cf_api_url: https://api.45.248.73.45.nip.io
	paasta-api-haproxy-public-ip
	$cf target

	

# issue
sudo helm install -f ../values/cp-pipeline-broker.yaml cp-pipeline-broker cp-pipeline-repository/cp-pipeline-broker -n cp-pipeline

ubuntu@paasta-playpark-cp-master01:~/workspace/container-platform/cp-pipeline-deployment/script$ sudo helm install -f ../values/cp-pipeline-broker.yaml cp-pipeline-broker cp-pipeline-repository/cp-pipeline-broker -n cp-pipeline
Error: INSTALLATION FAILED: failed to download "cp-pipeline-repository/cp-pipeline-broker"

sudo helm install -f ../values/cp-pipeline-app.yaml cp-pipeline-app cp-pipeline-repository/cp-pipeline-app -n cp-pipeline > /dev/null 2>&1


routing_cf_api_url: https://api.$system_domain

https://api.paas-ta.org


metadata:
  name: cp-pipeline-broker
  namespace: {NAMESPACE}

spec:
  replicas: 1

image:
  registry: {REPOSITORY_URL}
  project: {REPOSITORY_PROJECT_NAME}
  name: cp-pipeline-broker
  tags: {IMAGE_TAGS}
  pullPolicy: {IMAGE_PULL_POLICY}
  secret: {IMAGE_PULL_SECRET}

service:
  type: {SERVICE_TYPE}
  protocol: {SERVICE_PROTOCOL}
  port: 8083
  targetPort: 8083
  nodePort: 30083

configmap:
  name: cp-pipeline-configmap

  : istio-gateway에서 30083을 port를 쓰고 있었음 


cf create-service-broker cp-pipeline-service-broker admin cloudfoundry http://45.248.73.56:30083

cf service-access
cf enable-service-access container-platform-pipeline

cf create-service-broker cp-source-control-service-broker admin cloudfoundry http://45.248.73.56:30093

offering                      plans                                                   description                                                                                                                              broker
Mysql-DB                      Mysql-Plan1-10con, Mysql-Plan2-10con                    A simple mysql implementation                                                                                                            mysql-service-broker
delivery-pipeline             delivery-pipeline-shared, delivery-pipeline-dedicated   A paasta source control service for application development.provision parameters : parameters {owner : owner}                            delivery-pileline
p-paasta-sourcecontrol        Default                                                 A paasta source control service for application development.provision parameters : parameters {owner : {owner}, org_name : {org_name}}   paasta-sourcecontrol-broker
cp-portal-service-broker      Advenced                                                For Container Service Plans, You can choose plan about CPU, Memory, disk.                                                                cp-portal-service-broker
container-platform-pipeline   container-platform-pipeline-shared                      A paasta container platform pipeline service for application development.provision parameters : parameters {owner : owner}               cp-pipeline-service-broker
scm-manager                   Shared                                                  A paasta source control service for application development.provision parameters : parameters {owner : {owner}, org_name : {org_name}}   cp-source-control-service-broker


cf create-service-broker cp-pipeline-service-broker admin cloudfoundry http://45.248.73.56:30083
cf create-service-broker cp-portal-service-broker admin cloudfoundry http://45.248.73.56:32704