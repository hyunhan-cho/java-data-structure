
// ArrayIntDeque: 배열 기반으로 구현한 정수형 덱(Dequeue)
public class ArrayIntDeque {
	
	private int[] elements; // 데이터를 저장하는 배열
	private int front; // 가장 앞쪽 요소 인덱스 확인하는 거
	private int back; // 가장 뒤쪽 요소 다음 인덱스 확인하는 거
	private int count; // 현재 저장된 요소 개수
	private static final int CAPACITY = 16; // 배열 초기 크기
	
	// 생성자
	public ArrayIntDeque() {
		this.elements = new int[CAPACITY];
		this.front = 0;
		this.back = 0;
		this.count = 0;
	}
	
	// addFirst : 앞쪽에 값 추가
	public void addFirst(int value) {
		if (this.count == this.elements.length) {
			grow(); // 배열 꽉 찼으면 확장
		}
		this.front = (this.front - 1 + this.elements.length) % this.elements.length; // 앞쪽 인덱스 감소 (순환 구조)
		this.elements[this.front] = value; // 값 넣기
		this.count += 1; // 개수 증가
	}
	
	// addLast : 뒤쪽에 값 추가
	public void addLast(int value) {
		if (this.count == this.elements.length) {
			grow(); // 배열 꽉 찼으면 확장
		}
		this.elements[this.back] = value; // 현재 back 위치에 값 저장
		this.back = (this.back + 1) % this.elements.length; // back 인덱스 증가 (순환 구조)
		this.count += 1; // 개수 증가
	}
	
	// removeFirst : 앞쪽 요소 제거하고 반환하기
	public int removeFirst() {
		if (this.count == 0) {
			throw new IllegalStateException(); // 비어있으면 에러
		}
		int value = this.elements[this.front]; // 현재 front 위치 값 꺼내기
		this.front = (this.front + 1) % this.elements.length; // front 인덱스 증가
		this.count -= 1; // 개수 감소
		return value; // 제거된 값 반환
	}
	
	// removeLast : 뒤쪽 요소 제거하고 반환하기
	public int removeLast() {
		if (this.count == 0) {
			throw new IllegalStateException(); // 비어있으면 에러
		}
		this.back = (this.back - 1 + this.elements.length) % this.elements.length; // back 인덱스 감소
		int value = this.elements[this.back]; // 감소한 위치 값 꺼내기
		this.count -= 1; // 개수 감소
		return value; // 제거된 값 반환
	}
	
	// peekFirst : 앞쪽 요소 값만 확인
	public int peekFirst() {
		if (this.count == 0) {
			throw new IllegalStateException(); // 비어있으면 에러
		}
		return this.elements[this.front]; // 현재 front 위치 값 반환
	}
	
	// peekLast : 뒤쪽 요소 값만 확인
	public int peekLast() {
		if (this.count == 0) {
			throw new IllegalStateException(); // 비어있으면 에러
		}
		return this.elements[(this.back - 1 + this.elements.length) % this.elements.length]; // back 이전 위치 값 반환
	}
	
	// size: 현재 크기 출력하기
	public int size() {
		return this.count; // 현재 저장된 요소 개수 반환
	}
	
	// grow: 배열 크기 작으면 늘리는 거 구현
	public void grow() {
		int[] new_array = new int[this.elements.length * 2]; // 새로운 배열 만들기
		for (int i = 0; i < this.count; i++) {
			new_array[i] = this.elements[(this.front + i) % this.elements.length]; // 기존 값들 순서대로 복사
		}
		this.elements = new_array; // 배열 교체
		this.front = 0; // front 초기화
		this.back = this.count; // back 초기화
	}
}
