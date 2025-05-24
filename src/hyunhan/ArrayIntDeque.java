package hyunhan;

// ArrayIntDeque: 배열 기빈으로 구현한 정수형 덱(Dequeue)
public class ArrayIntDeque {
	
	private int[] elements; //데이터를 저장하는 배열
	private int front; //가장 앞쪽 요소 인덱스 확인하는 거
	private int back; //가장 뒤쪽 요소 다음 인덱스 확인하는 거
	private int count; //현재 저장된 요소 개수
	private static final int CAPACITY = 16; //배열 초기 크기
	
	//생성자
	public ArrayIntDeque() {
		this.elements = new int[CAPACITY];
		this.front = 0;
		this.back = 0;
		this.count = 0;
	}
	
	//addFirst : 앞쪽에 값 추가
	public void addFirst(int value) {
		if(this.count == this.elements.length) {
			grow();
		}
		
	}
	//addLast : 뒤쪽에 값 추가
	
	//removeFirst : 앞쪽 요소 제거하고 반환하기
	
	//removeLast : 뒤쪽 요소 제거하고 반환하기
	
	//peekFirst : 앞쪽 요소 값만 확인
	
	//peekLast : 뒤쪽 요소 값만 확인
	
	//size: 현재 크기 출력하기
	
	//grow:배열 크기 작은면 늘리는 거 구현
	public void grow() {
		int [] new_array = new int[this.elements.length * 2]
	}
	
}
