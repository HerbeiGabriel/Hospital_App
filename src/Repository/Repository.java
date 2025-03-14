package Repository;

import Domain.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository<ID, T> implements IRepository<ID, T> {

    Map<ID, T> map = new HashMap<>();

    public void add(ID id, T obj) {
        if (map.containsKey(id)) {
            throw new IllegalArgumentException("ID already exists");
        }
        map.put(id, obj);
    }

    public void delete(ID id) {
        if(id==null){
            throw new NullPointerException("ID cannot be null");
        }
        else if(!map.containsKey(id)){
            throw new NullPointerException("ID not found");
        }
        map.remove(id);
    }

    public void update(ID id, T obj) {
        if(!map.containsKey(id)){
            throw new IllegalArgumentException("ID does not exists");
        }
        map.put(id, obj);
    }

    public T get(ID id) {
        return map.get(id);
    }

    public Iterable<T> getAll() {
        return map.values();
    }

    public ArrayList<T> getlist() {
        return new ArrayList<>(map.values());
    }


}
