package hyunhan;

public class LinkedIntStack {
	private class Node{
		int value;
		Node next;
		
		Node(int value, Node next){
			this.value = value;
			this.next = next;
		}
	}
	
	private Node top;
	private int size;
	
	//생성자
	public LinkedIntStack() {
		this.top = null;
		this.size = 0;
	}
	//push 스택에 값 추가
	public void push(int value) {
		Node n = new Node(value, this.top) ;
			this.top = n; //top을 새 노드로 갱신하는 코드
			this.size+= 1;
	}
	//pop 스택에 맨 위에 값 뽑아버리기
	//peek 스택 위에 뭔 값 있늕 확인하기
	//size 스택 크기 확인하기
	
}
