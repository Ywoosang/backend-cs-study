## toString 올바르게 사용하기

toString은 Java에서 객체를 문자열로 표현하기 위한 메서드다. 모든 Java 클래스는 기본적으로 Object 클래스를 상속받으므로, Object 클래스에 정의되어 있는 toString 메서드를 사용할 수 있다.

### 사용 용도는?
- toString 은 디버깅을 위해 설계된 메서드다.  특이한 케이스를 제외하면 toString 은 로깅과 디버깅만을 위해 사용되는 것이 올바르다.

toString 이 잘 구현된 클래스는 사용하기 편하고, 디버깅이 쉽다. 객체를 출력하기만 하면, 객체가 가지고 있는 모든 정보를 확인할 수 있다. 
직접 호출하지 않아도, 로깅을 하거나 에러메세지를 출력할 때에도 유용하게 사용할 수 있다.

### 어떻게 만들어야 할까?
- toString은 객체의 상태를 간결하고 유용하게 표현하는 데 집중해야 한다.
  - 메서드에는 모든 필드를 포함할 필요는 없으며, 필요한 정보만 선택적으로 포함한다.
- 동일 프로젝트 내에서는 toString의 포맷 스타일을 통일한다. 
  - ex) ClassName[field1=value1, field2=value2] 
- 객체의 필드가 다시 자기 자신이나 순환 참조를 가지는 경우, toString이 무한 재귀 호출로 이어질 수 있으므로 주의한다.
- 비밀번호, 보안 토큰, 개인 식별 정보 등 민감한 정보를 포함하지 않도록 한다.

### toString을 재정의 해야하는 이유는?
toString 메서드를 항상 재정의한다.(이펙티브 자바 3판 아이템 12)  
toString 의 일반 규약은**간결하면서 사람이 읽기 쉬운 형태의 유익한 정보**를 반환하는 것이다. 기본 toString 메서드는 `클래스이름@16진수로_표시된_해시코드` 형태의 문자열을 반환하므로 사람이 읽기 쉽고 유의미한 정보를 출력하도록 하려면 재정의가 필요하다.
```java
// java.lang 패키지의 Object클래스의 toString 메서드의 구현
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

예시코드

```java
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}
```
Override 전: `User@1b6d3586`  
Override 후:`User{name='John', age=25}`



