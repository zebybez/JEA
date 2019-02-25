package data.interfaces;

import java.util.List;

public interface BaseDao<DO> {
    DO persist(DO object);
    DO find(Long id);
    DO merge(DO object);
    void delete(DO object);
}
