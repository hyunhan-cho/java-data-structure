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

    
}

