# 데이터베이스 락(Lock)

DBMS에서는 락을 통해 동시성을 제어하게 된다. 이때 락의 범위를 비즈니스에서 필요한 최소한으로 설정해야 한다.

## 락(Lock) 이란?

여러 트랜잭션이 동시에 동일한 데이터에 접근하거나 수정하는 것을 제어해 데이터 일관성과 무결성을 보장하기 위한 기법이다.

[격리수준](트랜잭션_격리수준.md)이 트랜잭션이 데이터를 **읽을 때** 다른 트랜잭션에서 변경 중인 데이터나 커밋되지 않은 데이터를 어떻게 다룰지 결정하는 정책이라면, 
락(Lock)은 트랜잭션이 데이터를 **수정할 때** 다른 트랜잭션이 그 데이터를 수정하거나 읽지 못하게 자동으로 잠금을 거는 방식이다.

Lock 을 통해 한 트랜잭션이 특정 데이터에 대해 작업을 수행하는 동안 다른 트랜잭션이 해당 데이터에 동시 접근하거나 수정하는 것을 제한함으로써 충돌 및 불일치를 방지할 수 있다.

## 공유락과 베타락

### 공유락(Shared Lock)

Shared Lock은 데이터를 읽을 때 사용되어지는 Lock이다.
Read Lock, S Lock이라고도 불린다.  
Shared Lock 끼리는 동시에 접근이 가능하다. 즉, 동시에 여러 사용자가 수정은 할 수 없지만 데이터를 읽을 수는 있다. 하지만 Shared Lock이 설정된 데이터에 Exclusive Lock을 사용할 수는 없다.

### 베타락(Exclusive Lock)

Exclusive Lock 은 데이터를 수정하거나 삭제하는 작업을 할 때 사용되는 Lock이다. 배타락이 걸리면 해당 자원(행 또는 테이블)에 대해 다른 트랜잭션은 읽기나 쓰기 작업을 할 수 없으며, 트랜잭션이 완료될 때까지(즉, 커밋 또는 롤백이 이루어질 때까지) 해당 락은 유지된다.
Exclusive Lock은 동시에 여러 트랜잭션에서 설정될 수 없으며, 오직 하나의 트랜잭션만이 데이터를 수정할 수 있도록 한다.

배타락은 Write Lock, X Lock라고도 불리며, 주로 UPDATE, DELETE, INSERT 같은 쓰기 작업에 사용된다.

배타락이 걸린 자원은 다른 트랜잭션에서 읽기조차 불가능하다. 이 점이 공유락과의 가장 큰 차이점이다.


## 락 설정 범위 (Lock-Level)

락 설정 범위(Level)는 Lock이 걸리는 대상의 범위를 정의한다.
공유락과 배타락은 그 대상에 대해 트랜잭션이 어떤 작업을 할 수 있는지를 결정한다.

예를 들면
- 테이블 수준에서 배타락이 걸리면 해당 테이블 전체에 대해 다른 트랜잭션이 읽거나 쓸 수 없다.
- 행 수준에서 공유락이 걸리면 특정 행을 다른 트랜잭션에서 읽을 수 있지만 수정은 불가능하다.


### 데이터베이스 수준 잠금(Database-Level Lock)

데이터베이스 전체에 락을 설정한다. 보통 유지보수나 데이터베이스의 중요한 업데이트 작업에 사용되며, 일반적으로는 잘 사용되지 않는다.

### 테이블 수준 잠금(Table-Level Lock)

테이블 전체를 잠그는 방식이다. 테이블 전체에 대한 동시성 제어가 필요할 때 사용되며, DDL(테이블 구조 변경) 작업에서 자주 사용된다.  
예를 들어, LOCK TABLES 명령으로 테이블 전체에 배타락을 걸면 다른 트랜잭션이 그 테이블을 읽거나 쓸 수 없다.

### 페이지 수준 잠금(Page-Level Lock)

디스크 상의 물리적인 데이터 페이지에 대해 락을 설정하는 방식이다. 여러 행이 한 페이지에 저장되기 때문에, 페이지 수준 잠금은 하나의 페이지에 속하는 여러 행을 보호하는 방식이다.  
MySQL에서는 자주 사용되지 않는다.  

### 행 수준 잠금(Row-Level Lock)

특정 행(Row)에 대해 락을 설정하는 방식이다. InnoDB 스토리지 엔진에서는 주로 이 방식을 사용한다.
다른 행에 대한 동시 접근을 허용하여 동시성을 높일 수 있다. 

SELECT ... FOR UPDATE 또는 UPDATE, DELETE 문에서 자동으로 행 수준 잠금이 설정된다.
