package com.min.edu;

// 부모가(java.lang.Object) 자식을 복제 하는 것
// 부모의 메소드인 clone을 사용하여 복제
// 부모 클래스는 자식에게 구현은 강요 특징 강요하지 못함
// 값은 같은데  reference와 hashcode가 다른 객체를 부모를 통해 만든다
public class UserInfoVo implements Cloneable{

	private String name;
	private String addr;
	private int age;
	
	public UserInfoVo(String name, String addr, int age) {
		super();
		this.name = name;
		this.addr = addr;
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserInfoVo [name=" + name + ", addr=" + addr + ", age=" + age + "]";
	}

	// throws  사용시 예외가 발생할 수 있음
	// CloneNotSupportException : 복제를 지원하지 않는다
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		return super.clone();
//	}
	
	public Object createClone() {
		Object obj = null;
		try {
			obj = this.clone();
		} catch (CloneNotSupportedException e) { //this가 복제가능한 클래스인가요? 
			e.printStackTrace();
		}
		return obj;
	}
	
	
}





