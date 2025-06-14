import java.util.ArrayList;

public class ArrayMap<K, V>{
  class Pair<K, V>{
    K key;
    V value;

    Pair(K key, V value){
      this.key = key;
      this.value = value;
    }
  }

  private ArrayList<Pair<K, V>> items = new ArrayList<>();

  // put() : key에 해당하는 위치에 value 저장
  public void put(K key, V value){
    for(Pair<K, V> e : items ){
      if (e.key.equals(key)){
        e.value = value; // 기존 값 업데이트
        return;
      }
    }
    items.add(new Pair<>(key, value)); // 새로운 항목 추가
  }

  public V get(K key ){
    for(Pair<K, V> e : items ){
      if (e.key.equals(key)){
        return e.value; // 해당 key의 value 반환
      }
    }
    return null; // 해당 key가 없으면 null 반환
  }

  public boolean containsKey(K key){
    for (Pair<K, V> e : items){
      if (e.key.equals(key)){
        return true; // 해당 key가 존재하면 true 반환
      }
    }
    return false; // 해당 key가 없으면 false 반환
  }

  public V remove(K key){
    for (int i = 0; i < items.size(); i++){
      if (items.get(i).key.equals(key)){
        V out = items.get(i).value; // 제거할 값 저장
        items.remove(i); // 항목 제거
        return out; // 제거한 값 반환
      }  
    }return null; // 해당 key가 없으면 null 반환
  }

  public static void main(String[] args){
    ArrayMap<String, Integer> arrayMap = new ArrayMap<>();
    arrayMap.put("apple", 1);
    arrayMap.put("banana", 2);
    arrayMap.put("apple", 3);

    System.out.println(arrayMap.get("apple")); // 3
    System.out.println(arrayMap.containsKey("banana"));
  }
  
}