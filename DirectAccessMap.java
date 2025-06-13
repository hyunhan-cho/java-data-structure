public class DirectAccessMap <V> {
    class Pair <V>  {
        int key;
        V value;

        Pair(int key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    //배열의 크기를 크게 잡고, key를 인덱스로 사용
    @SuppressWarnings("unchecked")
    private Pair<V>[] table = (Pair<V>[]) new Pair[100]; //0~99인덱스를 직접 접근
    private int size = 0; //현재 저장된 항목 수

    //put() : key에 해당하는 위치에 value저장
    public void put(int key, V value){
        table[key] = new Pair<>(key, value);
        size++;
    }

    //get() : key에 해당하는 위치의 value를 반환
    public V get(int key) {
        if (key < 0 || key >= table.length) {
            return null; // 유효하지 않은 키
        }
        Pair<V> pair = table[key];
        return (pair != null) ? pair.value : null; // 해당 위치에 값이 없으면 null 반환
    }

    //containsKey() : key에 해당하는 위치에 값이 있는지 확인
    public boolean containsKey(int key){
        return (key >= 0 && key < table.length && table[key] != null);
    }

    //remove() : key에 해당하는 위치의 값을 제거
    public V remove(int key){
        if (table[key] != null){
            V out = table[key].value;
            table[key] = null; // 해당 위치를 비움
            size--;
            return out; // 제거된 값 반환
        }
        return null; // 해당 위치에 값이 없으면 null 반환
    }

    //size() : 현재 저장된 항목 수 반환
    public int size() {
        return size;
    }

    //clear() : 모든 항목 제거
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null; // 모든 위치를 비움
        }
        size = 0; // 항목 수 초기화
    }

    //테스트용 main 메소드
    public static void main(String[] args){
        DirectAccessMap<Integer> daMap = new DirectAccessMap<>();
        daMap.put(1, 100);
        System.out.println(daMap.get(1)); // 100
        System.out.println(daMap.containsKey(1)); // true
        System.out.println(daMap.remove(1)); // 100
        System.out.println(daMap.get(1)); // null
        System.out.println(daMap.size()); // 0
    }
}

