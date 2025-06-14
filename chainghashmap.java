import java.util.Objects;
import java.util.ArrayList;


//key - Value 쌍을 저장하는 간단한 클래스
class Pair<K, V> {
    K  key;
    V value;
    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

interface SimpleMap<K, V> {
    void put(K key, V value); // key에 해당하는 위치에 value 저장
    V get(K key); // key에 해당하는 value 반환
    boolean containsKey(K key); // key가 존재하는지 확인
    V remove(K key); // key에 해당하는 항목 제거 후 value 반환
    int size(); // 현재 저장된 항목의 개수 반환
}

public class chainghashmap<K, V> implements SimpleMap<K, V> {
    private class Node<K,V> {
        Pair<K, V> data;
        Node<K, V> next;

        Node(Pair<K, V> d, Node n){
            data = d;
            next = n;
        }
    }

    @SuppressWarnings("unchecked")
    private Node<K, V>[] buckets = new Node[16]; // 초기 버킷 크기
    private int size = 0; // 현재 저장된 항목의 개수

    //key-value쌍을 삽입(중복이면 덮어씀)
    @Override
    public void put(K key, V value){

        Objects.requireNonNull(key, "Key must not be null");
        ensureLoadFactor(); // 로드 팩터 확인 및 리사이즈
        int index = Math.abs(key.hashCode() % buckets.length); // 해시코드로 인덱스 계산
        Node<K, V> node = buckets[index]; //해당버킷 , 연결리스트 시작점

        //연결리스트 순회하기
        while (node != null) {
            if (node.data.key.equals(key)) { // 이미 존재하는 key라면
                node.data.value = value; // value 업데이트
                return;
            }
            node = node.next; // 다음 노드로 이동
        }

        // 새로운 노드를 생성하여 해당 버킷의 시작점에 추가
        Node<K, V> newNode = new Node<>(new Pair<>(key, value), buckets[index]);
        buckets[index] = newNode;
        size++; // 항목 개수 증가

    }

    

    private void ensureLoadFactor() {
        if (size >= buckets.length * 0.75) { // 로드 팩터가 0.75 이상이면 리사이즈
            rehash(buckets.length * 2);
        }
    }

    private void rehash(int newCapacity) {
        Node<K, V>[] newBuckets = new Node[newCapacity];
        for (Node<K, V> bucket : buckets) {
            Node<K, V> current = bucket;
            while (current != null) {
                int newIndex = Math.abs(current.data.key.hashCode() % newCapacity);
                Node<K, V> next = current.next;
                current.next = newBuckets[newIndex];
                newBuckets[newIndex] = current;
                current = next;
            }
        }
        buckets = newBuckets;
    }
