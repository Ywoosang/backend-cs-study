
인스턴스를 종료하지 않는다면 VPC 를 삭제하지 못함

EC2 인스턴스 종료
- 종료 방지 기능 변경
  - 활성화 체크 해제

- 
- 네트워크 인터페이스 분리 
- 인스턴스 i-0c2e85d8de1bb1367에서 네트워크 인터페이스 eni-08aea2c549284fdb0을(를) 분리하지 못했습니다.
  The network interface at device index 0 and networkCard index 0 cannot be detached.

네트워크 인터페이스 삭제
- 




키페어 삭제
- 인스턴스에 사용중이었다면 삭제



- Actions -> Instance State -> Terminate

Elastic IP 삭제
- VPC 대시보드 -> Elastic IPs -> 


오류: 인스턴스가 종료되지 않을 수 있습니다. 'disableApiTermination' 
- 인스턴스 종료 방지 기능 때문


퍼블릭 IP 주소 private ip 주소