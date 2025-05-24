package hyunhan;

//링크드인트덱 -> 양방향 연결 리스트로 구현할 예정.
public class LinkedIntDeque {
	private class Node{
		int value;
		Node prev;
		Node next;
		
		Node(int value, Node prev, Node next){
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private int size; //요소 개수
	private Node front; //가장 맨 앞에 부분 (즉 센티넬)
	private Node back; //가장 뒤에 부분 (즉 센티넬)
	
	//링크드인트덱 생성자 구현하기
	public LinkedIntDeque() {
		this.size = 0;
		this.front = new Node(0, null, null);
		this.back = new Node(0, null, null);
		
		//두 노드끼리 연결하는 것
		this.front.next = this.back;
		this.back.prev = this.front;
	}
	
	// addFirst: 앞에 요소 추가
	public void addFirst(int value) {
		addBetween(value, this.front, this.front.next);
        this.size += 1;
	}
	// addLast: 뒤에 요소 추가
	public void addLast(int value) {
		addBetween(value, this.back.prev, this.back);
		this.size += 1;
	}
	// removeFirst: 앞쪽 요소 제거하고 반환
	public int removeFirst() {
        if (this.size == 0) {
            throw new IllegalAccessError(); // 비어있으면 에러
        }
        int value = unlink(this.front.next); // front 다음 노드 제거
        this.size -= 1;
        return value;
    }
	// removeLast: 뒤쪽 요소 제거하고 반환
	public int removeLast() {
		if(this.size == 0) {
			throw new IllegalAccessError(); //비어있으면 에러
		}
		int value =  unlink(this.back.prev); // back 전에 노드 제거하기
		this.size -= 1;
		return value;
	}
	// peekFirst: 앞쪽 요소 값만 확인, 즉 반환
	public int peekFirst() {
		 if (this.size == 0) {
	            throw new IllegalAccessError();
	        }
	        return this.front.next.value;
	}
	// peekLast: 뒤쪽 요소 값만 확인, 즉 반환
	public int peekLast() {
		 if (this.size == 0) {
	            throw new IllegalAccessError();
	        }
	        return this.front.next.value;
	}
	// addBetween: prev와 next 사이에 새 노드 삽입
    private void addBetween(int value, Node prev, Node next) {
        Node n = new Node(value, prev, next);
        prev.next = n;
        next.prev = n;
    }

    // unlink: 주어진 노드를 연결에서 제거하고 값 반환
    private int unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node.value;
    }
}
