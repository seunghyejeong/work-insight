ncp 202


[VPC]
- 10.0.0.0/8 172.16.0.0/12 192.168.0.0/16
- 최소 /28 ~ 최대 /16

#ACG & NACL- 방화벽
	ACG(서버방화벽)				NACL(네트워크방화벽)
----------------------------------------------------------
	서버(NIC)단위				subnet 단위 
----------------------------------------------------------
	Allow 규칙헤 한하여 지원 		Allow, Deny 규칙 모두 지원
	Response 트래픽 자동 허용		Response 트래픽에 대한 Allow 규칙이 추가적으로 필요
	:allow가 허용 되었다는 것은 	: Deny 규칙으로 인해 Response 트래픽에 대한 allow 규칙이
	응답이 자동으로 가능하다는 것    추가적으로 필요
----------------------------------------------------------
	모든 규칙을 확인하여 판단		우선순위에 따라 규칙을 반영

- VPC에 종속적이며 subnet단위로 활동 하므로 VPC를 삭제하면 NACL / ACG도 삭제


#subnet
- Public Subnet
	server만 위치 시킬 수 있으며 공인 ip 할당 가능
- Private Subnet 
	server를 포함한 loadbalancer, db, bare metal과 같은 다양한 상품 위치
	Redis와 같은 외부에서는 접속할 일이 없는 middleware 성격을 가진 프로그램은
	private subnet에만 설치할 수 있다.


#NAT Gateway
- 기본적인 NAT Gateway는 매번 바뀌고 사용자가 알 수 없음
- 특정 서버와 통신할 때 NAT ip를 알아야하고 고정 되어야함
- 이 때 사용하는 것이 NAT gateway이다



[Load balancer]

VPC
방법1. Target Group : 논리적인 grouping으로 관리적 측면에서 효율성을 높임
	<프로토콜>
	TCP 		: Network Load Balancer
	Proxy_TCP	: Network Proxy Load Balancer
	HTTP 		: Application Load Balancer
	HTTPS		: Application Load Balancer
방법2. Load balancer 부하처리 성능에 따라 선택 
	Small	최소 30,000
	Medium	최소 60,000
	Large	최소 90,000 개의 분산 처리 보장
Load balancer 알고리즘
	Round Robin
	Least Connection
	Source IP Hash
	제외 : weighted (클라우드 환경에서 유용하지 않음)