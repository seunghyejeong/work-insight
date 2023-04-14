kubestudy


ta-bami-master		10.170.70.5, 115.68.198.50
ta-bami-cluster-1 	10.170.70.186
ta-bami-cluster-2 	10.170.70.202
ta-bami-cluster-3	10.170.70.55
ta-bami-cluster-4	10.170.70.189
ta-bami-cluster-5	10.170.70.219
ta-bami-nfs			10.170.70.187, 115.68.198.53



/home/share/nfs 10.170.70.202(rw,no_root_squash,async)
/home/share/nfs 10.170.70.55(rw,no_root_squash,async)
/home/share/nfs 10.170.70.189(rw,no_root_squash,async)
/home/share/nfs 10.170.70.219(rw,no_root_squash,async)


#!/bin/bash

export MASTER_NODE_HOSTNAME=ta-bami-master
export MASTER_NODE_PUBLIC_IP=115.68.198.50
export MASTER_NODE_PRIVATE_IP=10.170.70.5

## Worker Node Count Info
export WORKER_NODE_CNT=5

## Add Worker Node Info
export WORKER1_NODE_HOSTNAME=ta-bami-cluster-1
export WORKER1_NODE_PRIVATE_IP=10.170.70.186
export WORKER2_NODE_HOSTNAME=ta-bami-cluster-2
export WORKER2_NODE_PRIVATE_IP=10.170.70.202
export WORKER3_NODE_HOSTNAME=ta-bami-cluster-3
export WORKER3_NODE_PRIVATE_IP=10.170.70.55
export WORKER4_NODE_HOSTNAME=ta-bami-cluster-4
export WORKER4_NODE_PRIVATE_IP=10.170.70.189
export WORKER5_NODE_HOSTNAME=ta-bami-cluster-5
export WORKER5_NODE_PRIVATE_IP=10.170.70.219

## Storage Type Info (eg. nfs, rook-ceph)
export STORAGE_TYPE=nfs
export NFS_SERVER_PRIVATE_IP=10.170.70.187



---
[Docker]

linux의 기능들을 발전시켰다 
: 해서 linux os를 사용하여 컨테이너를 설계함.

chroot
+ namespace
+ cgroup
ㅡㅡㅡㅡ
= kernel (containr의 기반)


MSA/Devops에 적합한 구조 
: 운영환경 자체도 컨테이너에 합께 포함시켜 다른 환경에서도 원하는 프로그램을 실행 할 수 있다.
: 환경제약이 없다.


ubuntu OS 버전 확인
cat /etc/lsb-release

Os 커널 버전 및 아키텍처 확인
uname -a


