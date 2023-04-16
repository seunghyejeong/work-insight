# k8s: DevOps for MSA

- CI/CD 환경 구축 및 MSA application 구현 
- helm + jenkins + argo CD (gitOps) + sonarqube
https://tech.kakao.com/2021/07/16/devops-for-msa/
\\192.168.0.24\파트폴더\기술지원팀\05.산출물\2023_산출물\0.sub_task_산출물\DevOps

## 1. 카카오톡, 배민 사례

배포도구:helm

1. 1.클러스터 분리

2. 1.service mesh
   종류: ngnix,istioci 등

3. ci/cd구축

   - jenkins가  ci/cd 모두 하지만 편의성을 위해 
     cI : jenkins
     cd : argo, spinnaker 등 

   + gitops 배포할 k8s 리소스를 git repository에 올려놓으면 소스의 차이를 감지하고
   이를 클러스터에 동기화 해주는 자동화 어플리케이션.
   이는 지속적으로 배포가 이루어지도록 해줌
   git commit을 통해 구축되므로 별도 설정이나 시스템 구축 없이도 로그를 쌓을 수 있다.

   - jenkins script 작성
     파이프라인 스크립트
     : 시스템 스펙 / 사용자 스펙을 분리한 다음 필요한 부분만 오버라이딩해서 쉐도잉으로 
     가져다 쓸 수 있도록 함 

> jenkins, grgo, sonarqube, kubeflow 등 대두분의 인프라는 오픈소스로 되어있음
> 카카오의 경우 helm설치를 바로 하지 않고 kustomize를 이용하여 필요한 부분만 패치해서 씀
> : base를 바탕으로 동일한 애플리케이션의 여러 버전을 저의하는 오버레이 방식
> : 필요한 부분만 패치하여 사용할 수 있는 템플릿화 도구

5. helm을 kusomize로 전환하게 하는 도구 사용 : ship

### CQRS?

Query Command 

event 전파
Eventually Consistency: 데이터는 언젠가는 다 맞춰진다
zero-payload방식 사용: 이벤트 발행시 최소정보만 보내게설정

## 2. fastcampus 강의

