package Repository;

import java.util.ArrayList;

public abstract class DataRepo<ID, T> extends Repository<ID, T> {

    public abstract void UpdateDB(ID id,T t);
    public abstract void ReadDB();
    public abstract void WriteDB(T t);
    public abstract void DeleteDB(ID id);

    @Override
    public void add(ID id, T t) {
    super.add(id, t);
    WriteDB(t);
    }

    @Override
    public void delete(ID id) {
    super.delete(id);
    DeleteDB(id);
    }

    @Override
    public void update(ID id, T obj) {
    super.update(id, obj);
    UpdateDB(id, obj);
    }
}
