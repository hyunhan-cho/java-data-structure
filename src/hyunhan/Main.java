package hyunhan;

class Animal{
	private String name;
	
	public Animal(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void makeSound() {	
	}
	
	public void eatFood(String food) {
		System.out.println(this.name + "가" + food + "를 먹습니다");
	}
}

class Dog extends Animal{
	public Dog(String name) {
		super(name);
	}
	
	@Override
	public void makeSound() {
		System.out.println(getName() + "가 멍멍 짓습니다");
	}
	
	public void wagTail() {
		System.out.println(getName() + "꼬리를 흔듭니다");
	}
	
}

class Cat extends Animal{
	public Cat(String name) {
		super(name);
	}
	
	@Override
	public void makeSound() {
		System.out.println(getName() + "가 야옹야옹 웁니다");
	}
	
}

class Bird extends Animal{
	public Bird(String name) {
		super(name);
	}
	
	@Override
	public void makeSound() {
		System.out.println(getName() + "가 삐약삐약하고 웁니다");
	}
	
	public void fly() {
		System.out.println(getName() + "하늘로 날아갑니다.");
	}	
}

public class Main{
	public static void main(String[] args) {
		Animal dog = new Dog("멍멍이");
		Animal cat = new Cat("야옹이");
		Animal bird = new Bird("삐약이");
		
		dog.makeSound();
		cat.makeSound();
		bird.makeSound();
		
		dog.eatFood("뼈다귀");
		cat.eatFood("물고기");
		bird.eatFood("지렁이");
		
		((Dog) dog).wagTail();
		((Bird) bird).fly();
	}
}

