

//LinkedIntQueue: 연결 리스트(Linked List)로 구현한 정수형 큐
public class LinkedIntQueue {
	
	//Node클래스 (내부 클래스)
	private class Node{
		int value;
		Node next;
		
		public Node (int value, Node next){
			this.value = value;
			this.next = next;
		}
	}
	
	private Node front;
	private Node back;
	private int size;
	
	//생성자
	public LinkedIntQueue() {
		this.front = null;
		this.back = null;
		this.size = 0;
	}
	
	//enqeue 큐의 뒤에 값 추가
	public void enqueue(int value) {
		Node n = new Node(value, null); //새 노드 생성(next는 null)
		if (this.size == 0) {
			this.front = n;
		}else {
			this.back.next = n;}
		this.back = n;
		this.size += 1;
	}
	
	//deqeue 큐의 앞에서 값 제거하고 반환
	public int dequeue() {
		if (this.size == 0) {
			throw new IllegalAccessError(); //비어있으면 에러
		}
		int value = this.front.value;
		this.front = this.front.next;
		this.size -= 1;
		if (this.size == 0) {
			this.back = null;
		}
		return value;
	}
	
	//peek: 큐의 맨 앞 값 확인
	public int peek() {
		if (this.size == 0) {
			throw new IllegalAccessError(); //비어있으면 에러
		}
		return this.front.value;
		}
	
	//size: 현재 큐의 위치에 저장된 요소 확인
	public int size() {
		return this.size;
	}
}
