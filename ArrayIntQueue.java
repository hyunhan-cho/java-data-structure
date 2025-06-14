//ArrayIntQueue: 배열(Array)로 구현한 정수형 큐
public class ArrayIntQueue{
  private int[] elements;
  private int front, end, count;
  private static final int DEFAULT_CAPACITY = 16;

  //생성자
  public ArrayIntQueue(){
    this.elements = new int[DEFAULT_CAPACITY];
    this.front = 0;
    this.end = 0;
    this.count = 0;
  }

  // ✅ grow: 배열 크기를 두 배로 확장
  private void grow() {
      int[] new_array = new int[this.elements.length * 2]; 
    // 두 배 크기 배열 생성
      for (int i = this.front; i < this.front +             this.elements.length; i++) {
          new_array[i] = this.elements[i % this.elements.length]; // 순서대로 복사 (원형 대응)
      }
      this.elements = new_array; // 배열 교체
  }
  
  //enqueue: 큐의 뒤에 값 추가
  public void enqueue(int value){
    if (this.count == this.elements.length){
      grow();
    }
    this.elements[this.end] = value;
    this.end = (this.end + 1) % this.elements.length;
    this.count += 1;
  }

  //dequeue: 큐의 앞에서 값 제거하고 반환
  public int dequeue(){
    if (this.count == 0){
      throw new IllegalStateException("Queue is empty");
    }
    int value = this.elements[this.front];
    this.front = (this.front + 1) % this.elements.length;
    this.count -= 1;  
    return value;
  }

  //peek: 큐의 맨 앞 값 확인
  public int peek(){
    if (this.count == 0){
      throw new IllegalStateException("Queue is empty");
    }
    return this.elements[this.front];
  }
}