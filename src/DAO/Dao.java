package DAO;

import java.util.LinkedList;

public interface Dao<Obj> {

    Obj get(int place);

    int save(Obj obj);

    LinkedList<Obj> getAll();

    void update(Obj obj, int i);

    void delete(int place);
}
