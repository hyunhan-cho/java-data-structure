// Linkintdeque.java
// 파일명 'Linkintdeque.java'에 맞춰 클래스 이름을 수정했습니다.
public class Linkintdeque {

    
    private class Node {
        int value;
        Node next;
        Node prev;
    
        Node(int value, Node next, Node prev){
          this.value = value;
          this.next = next;
          this.prev = prev;
        }
    }

    private Node front; // 가장 앞쪽 요소
    private Node back; //  가장 뒤쪽 요소 
    private int  size;// 요소 개수

  //생성자
  public Linkintdeque(){
    this.front = new Node(0, null, null);
    this.back = new Node(0, null, null);
    
    this.front.next = this.back; //센티넬끼리 연결
    this.back.prev = this.front; //센티넬끼리 연결

    this.size = 0;
  }

  //addBetween
  private void addBetween(int value, Node prev, Node next) {
    Node newNode = new Node(value, next, prev);
    prev.next = newNode; // 이전 노드의 다음을 새 노드로 설정
    next.prev = newNode; // 다음 노드의 이전을 새 노드로 설정
  }

  //unlink:주어진 노드를 연결에서 제거
  private int unlink(Node node) {
    int value = node.value; // 제거할 노드의 값을 저장
    Node prev = node.prev; // 이전 노드
    Node next = node.next; // 다음 노드

    prev.next = next; // 이전 노드의 다음을 다음 노드로 설정
    next.prev = prev; // 다음 노드의 이전을 이전 노드로 설정
    size--; // 크기 감소

    return node.value; // 제거한 노드의 값 반환
  }

  // addFirst: 덱의 앞에 요소 추가
  public void addFirst(int value){
    addBetween(value, this.front, this.front.next);
    this.size++; // 크기 증가
  }

  // addLast: 덱의 뒤에 요소 추가
  public void addLast(int value){
    addBetween(value, this.back.prev, this.back);
    this.size++; // 크기 증가
  }

  // removeFirst: 덱의 앞에서 요소 제거
  public int removeFirst(){
    if (this.size == 0){
      throw new IllegalStateException("Deque is empty");
    } else {
      return unlink(this.front.next); // front.next 노드를 제거하고 그 값을 반환
    }
  }

  // removeLast: 덱의 뒤에서 요소 제거
  public int removeLast(){
    if (this.size == 0){
      throw new IllegalStateException("Deque is empty");
    } else {
      return unlink(this.back.prev); // back.prev 노드를 제거하고 그 값을 반환
    }
  }

  // isEmpty: 덱이 비어있는지 확인
  public boolean isEmpty() {
    return this.size == 0; // size가 0이면 true 반환
  }

  //peekFirst: 덱의 앞에서 요소 확인
  public int peekFirst(){
    if (this.size == 0){
      throw new IllegalStateException("Deque is empty");
    }
    return this.front.next.value; // front.next 노드의 값을 반환
  }

  public int peekLast(){
    if (this.size == 0){
      throw new IllegalStateException("Deque is empty");
    }
    return this.back.prev.value;
  }


}
