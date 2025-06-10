
import java.util.Objects; 


//Key-Value 쌍을 저장하는 간단한 클래스
class Pair<K, V> {
	K key;                  // Key 값 저장
	V value;                // Value 값 저장
	Pair(K k, V v) {        // 생성자: key와 value를 받아서 저장
		key = k;
		value = v;
 }
}


interface SimpleMap<K, V> {
	void    put(K key, V value);   
	V       get(K key);              
	boolean containsKey(K key);      
	V       remove(K key);           
	int     size();                  
}


class ChainingHashMap<K, V> implements SimpleMap<K, V> {

 // 각 버킷의 연결리스트 노드 클래스 (key-value 쌍 + 다음 노드)
	private class Node<K, V> {
		Pair<K,V> data;     // 실제 Key-Value 쌍 저장
		Node<K,V> next;     // 다음 노드를 가리킴 (연결리스트)

		Node(Pair<K,V> d, Node<K,V> n) { // 생성자
			data = d;
        	next = n;
		}
	}
	
	//해쉬테이블의 실제 배열
	@SuppressWarnings("unchecked") //제네릭의 배열 경고 무시하기 ㅜ
	private Node <K, V> [] buckets = (Node<K, V>[]) new Node[16];
	private int size = 0; 
	
	//key-value쌍 삽입(중복이면 덮어씀)
	@Override
	public void put(K key,V value ) {
		Objects.requireNonNull(key,"Ket must not be null" );
		ensureLoadFactor(); //load factor(채워진 정도)가 0.75넘으면 배열 2배로 늘림
		
		int index = hash(key); //key의 해쉬값으로 인덱스 구함
		Node<K,V> node = buckets[index]; //해당 버킷(연결리스트) 시작점
		
		//연결리스트 순회, 이미 같은 key가 있으면 value만 덮어씀
		for(Node<K,V> n = node; n!= null ; n = n.next) {
			if(Objects.equals(n.data.key, key)) {
				n.data.value = value; //값만 변겨ㅑㅇ
				return;
			}
		}
		
		//key가 없으면 연결리스트 맨 앞에 새 노드 삽입
		buckets[index] = new Node<>(new Pair<>(key, value), node);
		size++; //전체 개수 증가
	}
	
	//key에 해당하는 value반환, 없으면 null
	@Override
	public V get(K key) {
		int index = hash(key); //인덱스 계산
		for (Node<K,V> n = buckets[index]; n != null; n = n.next) {
			if(Objects.equals(n.data.key, key)) {
				return n.data.value; //해당 value반환
			}
		}
		return null; //없으면 null반환
	}
	
	//key가 존재하는지 반환
	@Override
	public boolean containsKey(K key) {
		return get(key) != null;
	}
	
	//key에 해당하는 노드를 연결리스트에서 삭제하고 value반환
	@Override
	public V remove(K key) {
		int index = hash(key);
		Node<K, V> prev = null ; //이전 노드
		Node<K, V> curr = buckets[index]; //현재 노드(시작점)
		
		while (curr != null) {
			if (Objects.equals(curr.data.key, key)) { //key찾으면
				if(prev == null) {
					buckets[index] = curr.next; //head 노드 삭제
				}else {
					prev.next = curr.next;
				}
				size--;
				return curr.data.value; //삭제된 값 반환
			}
			prev = curr;
			curr = curr.next; //다음 노드로 이동
		}
		return null; //없으면 null
	}
	
	//저장된 쌍의 개수 반환
	@Override
	public int size() {
		return size;
	}
	
	//key를 받아서 배열 인덱스로 변환하는 해시 함수
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % buckets.length;
	}
	
	//load factor가 0.75초과시 버킷 배열 크기를 2배로
	private void ensureLoadFactor() {
		if ((double) size / buckets.length <= 0.75 ) return;
		rehash(buckets.length << 1); //새 크기는 기존의 2배
		
	}
	
	//버킷 크기 확장 후 모든 데이터를 다시 삽입(재해싱)
	@SuppressWarnings("unchecked") //제네릭의 배열 경고 무시하기 ㅜ
	private void rehash(int newCap) {
		Node<K, V>[] old = buckets; //기존 배열 백업
		buckets = (Node<K, V>[]) new Node[newCap]; //새배열 생성
		size = 0;
		
		for (Node<K, V> head : old)
			for (Node<K, V> n = head; n != null; n = n.next)
				put(n.data.key , n.data.value); //각 데이터를 새 배열에 삽입
	}
	//==========테스트용 클래스 ㅎㅎ =================
	
	private static final class Point {
        final int x, y; // 좌표값
        Point(int x, int y) { this.x = x; this.y = y; }

        @Override
        public int hashCode() { return 31 * x + y; } // x, y에 기반한 해시코드

        @Override
        public boolean equals(Object o) {
            return (o instanceof Point p) && p.x == x && p.y == y; // x, y 같으면 true
        }

        @Override
        public String toString() { return "(" + x + "," + y + ")"; } // 보기 편하게 출력
    }

    // 직접 실행해서 테스트하는 메인 함수
    public static void main(String[] args) {
        SimpleMap<Object, String> ch = new ChainingHashMap<>(); // HashMap 객체 생성
        test("ChainingHashMap", ch); // 테스트 함수 호출
    }

    // 해시맵의 각 기능을 테스트하는 함수
    private static void test(String name, SimpleMap<Object,String> m) {
        System.out.println("---- " + name + " ----");

        m.put("apple",  "fruit");                  // 문자열 삽입
        m.put(new Point(1,2), "P(1,2)");           // 객체(좌표) 삽입
        m.put(42,       "answer");                 // 숫자 삽입

        System.out.println("size = " + m.size()); // 현재 저장된 개수 출력
        System.out.println("get(\"apple\") = " + m.get("apple")); // apple의 값 출력
        System.out.println("get(Point(1,2)) = " + m.get(new Point(1,2))); // 좌표 객체 조회
        System.out.println("containsKey(42) = " + m.containsKey(42)); // 42 존재 여부
        System.out.println("remove(\"apple\") = " + m.remove("apple")); // apple 삭제
        System.out.println("size after remove = " + m.size()); // 삭제 후 개수
        System.out.println();
    }
	
	
}








