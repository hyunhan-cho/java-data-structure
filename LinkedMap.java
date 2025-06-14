public class LinkedMap<K, V>{
    class Node<K, V>{
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V> front = null;
    private int size = 0;

    // put() : key에 해당하는 위치에 value 저장
    public void put(K key, V value){
        Node<K, V> curr = front;
        //이미 존재하는 key가 있다면 value를 업데이트
        while (curr != null) {
            if (curr.key.equals(key)) {
                curr.value = value; // 기존 값 업데이트
                return;
            }
            curr = curr.next;
        }

        //key가 없다면 수행할 동작
        front = new Node<>(key, value, front); // 새로운 노드를 앞에 추가
        size++;
    }

    public V get(K key){
        for (Node<K, V> curr = front; curr != null; curr = curr.next) {
            if (curr.key.equals(key)) {
                return curr.value; // 해당 key의 value 반환
            }
        }
        return null; // 해당 key가 없으면 null 반환
    }

    public int size(){
        return size;
    }

    public boolean containsKey(K key){
        for (Node<K, V> curr = front; curr != null; curr = curr.next) {
            if (curr.key.equals(key)) {
                return true; // 해당 key가 존재하면 true 반환
            }
        }
        return false; // 해당 key가 없으면 false 반환
    }

    public V remove(K key){
        Node<K, V> curr = front;
        Node<K, V> prev = null;
        while(curr != null){
            if(curr.key == key){
                //key가 존재하면 수행할 동작
                if (prev == null){
                    front = curr.next; // 첫 번째 노드를 제거
                } else {
                    prev.next = curr.next; // 이전 노드의 다음 노드를 현재 노드의 다음 노드로 설정
                }
                size--; // 크기 감소
                return curr.value; // 제거한 노드의 값을 반환
            }
            prev = curr;
            curr = curr.next;
        }
        return null; // 해당 key가 없으면 null 반환
    }
    
    public static void main(String[] args) {
        LinkedMap<String, Integer> map = new LinkedMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        System.out.println(map.get("apple")); // 1
        System.out.println(map.size()); // 2
        System.out.println(map.containsKey("banana")); // true
        System.out.println(map.remove("apple")); // 1
        System.out.println(map.size()); // 1
    }
}