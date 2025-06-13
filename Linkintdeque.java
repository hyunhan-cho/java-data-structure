package com.example;

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
      
}