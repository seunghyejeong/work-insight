### **쿠버네티스 MySQL 배포 기본**

프로파일
 김정철 ・ 2019. 10. 27. 15:14
URL 복사  이웃추가 
본문 기타 기능
Service, Deploy, Volume, Secret를 모두 사용하는 예제이므로  간단히 기재 해 둠, 



#Docker로 MySQL을 생성한다면, 옵션을 비밀번호, PORT, 볼륨등을 주어 배포

docker run -e MYSQL_ROOT_PASSWORD=testsss -e MYSQ~~~



영구저장소를 위해 PV와 이를 마운트하기 위한 PVC 그리고 비밀번호를 등의 민감정보를 위한 Secret를 미리 생성해 둔다. 



1) PV 준비 (영구저장소)

k create -f local-volumes.yaml  

(아래는 예제일 뿐, PROC환경에서는 "host 볼륨"은 사용하지 않는다.  만약 사용할 경우 해당 볼륨 재사용을 위해 pod 생성시 기 생성된 Worker Node를 지정이 필요)



apVersion: v1

kind: PersistentVolume

metadata:

  name: "local-volume"

  lasbels:

    type: local

spec:

  capacity:

    storage: "5Gi"

  accessModes:

    - "ReadWriteOnce"

  hostPath:

    path: /tmp/localvolume

  persistentVolumeReclaimPolicy: Recycle





2)  PVC 준비 및 바운딩

k create -f mysql-pv-claim.yaml

밋업 내용을 보니,  PVC의 요청 볼륨이 1Gi 이었음에도 불구하고 관련 PV의 모든 볼륨(5Gi)을 가져옴.

 클라우드의 경우엔 쪼개지나, 온프레미스에선 아직은 따로따로 모두 만들어 ..주어야하나보다. 

(그리고 해당 pv의 내용을 pvc가 가져올때 무엇을 기준으로 매핑하여 가져오는지 명확하지 않은데,, 검토필요)



kind: PersistentVolumeClaim

apiversion: v1

metadata:

  name: mysql_pv_claim

  labels:

    type: pv_claim

  annotations:

    volume.beta.kubernetes.io/storage-class: ""

spec:

  storageClassName: manual

  accessModes:

  - ReadWriteOnce

  resources:

    requests:
    
      storage: 1Gi      



env:  key : value 로 넣을 수 있지만,  위의 경우 비밀번호는 미리 생성한 secret을 이용





3. Deployment 정의 

k mysql.yaml  

(일반적으로 디플로이먼트가 가진 스펙은 템플릿을 기준으로 만듦,  템플릿이 replicase 정의)



apiVersion: apps/v1beta2

kind: Deployment

metadata:

  name: mysql

spec:

  selector:

    matchLabels:
    
      app: mysql
    
    strategy:
    
      type: recreate
    
    template:
    
      metadata:
    
        labels:
    
          app: mysql
    
      spec:
    
        containers:
    
        - image: mysql:5.7.8
    
          name: mysql
    
          env:
    
          - name:_MYSQL_ROOT_PASSWORD
    
            valueFrom:
    
              secretKeyRef:
    
                name: mysql-credential
    
                key: password
    
          - name: MYSQL_DATABASE
    
            value: petclinic
    
          ports:
    
          - containerPort: 3306
    
            name: mysql
    
          volumeMounts:
    
          - name: mysql-persistent-storage
    
            mountPath: /var/lib/mysql
    
       volumes:
    
       - name: mysql-persistent-storage
    
         persistentVolumeClaim: 
    
           claimName: mysql-pv-claim



4. Service 정의 

k mysql-service.yaml(서비스 생성)

(서비스는 셀럭터의 라벨기준으로 관련 디플로이먼트의 셀렉터를 매핑)

apiVersino: v1

kind: Service

metadata:

  name: mysql

  labels:

    app: spring-petclinic

spec:

  type: NodePort

  ports:

    - port: 3306
    
      protocol: TCP
    
      targetPort: 3306
    
      nodePort: 32001

  selector:               

    app: mysql





5) Secret 정의 

#Secret 정보 등록하기  (너무 간단해서 yaml 파일로 만들지 않고 아래처럼 진행)



echo -n "root" > ./username

echo -n "petclinic" > ./password



#시크릿생성하기 (두 파일에서 읽어와서 시크릿을 만들자)

k create secret generic mysql-credential --from-fle=./username --from-file=./password



필요없는 파일 삭제 

rm useranme password



  



6) 생성 

1. PV-> PVC-> -> Secret -> 디플로이먼트 -> 서비스 



확인

k get deply 

k get svc

k get pod

k logs "pod names"



#DB 적재 테스트

mysql -h "mysql_node_ip" -u root -p password -P 32001 petclinic < test.sql  (db 스키마, 테이블, 데이터 생성 sql)
[출처] 쿠버네티스 MySQL 배포 기본|작성자 김정철