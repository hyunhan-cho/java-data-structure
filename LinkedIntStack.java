

//LinkedIntStack: 연결 리스트(Linked List)로 구현한 정수형 스택
public class LinkedIntStack {

    // Node 클래스 (내부 클래스)로 구혀한기 LInkedIntStack안에 클래스스
    private class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node top; // 스택의 맨 위 요소를 가리키는 일종의 포인터 생성
    private int size; // 스택에 쌓인 요소 개수 츣정하기 위한 size변수 생성

    // 생성자
    public LinkedIntStack() {
        this.top = null;
        this.size = 0;
    }

    // push: 스택에 값 추가
    public void push(int value) {
        Node n = new Node(value, this.top); // 새 노드를 만들고 기존 top을 다음 노드로 설정
        this.top = n; // top을 새 노드로 갱신
        this.size += 1;
    }

    // pop: 스택의 맨 위 값을 제거하고 반환
    public int pop() {
        if (this.top == null) {
            throw new IllegalAccessError(); // 비어있으면 에러 발생 ㅜㅜㅜ
        }
        int value = this.top.value; // 현재 top 값 저장
        this.top = this.top.next; // top을 다음 노드로 이동
        this.size -= 1;
        return value;
    }

    // peek: 스택의 맨 위 값 확인 (제거하지 않음)
    public int peek() {
        if (this.top == null) {
            throw new IllegalAccessError(); // 비어있으면 에러 발생 ㅜㅜ
        }
        return this.top.value;
    }

    // size: 현재 스택에 쌓여 있는 요소 수 반환
    public int size() {
        return this.size;
    }
}
