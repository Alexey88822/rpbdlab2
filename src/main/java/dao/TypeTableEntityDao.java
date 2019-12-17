package dao;

import classes.TypeTableEntity;

import java.util.List;

public interface TypeTableEntityDao {
    public void addType(TypeTableEntity type);
    public int findIdByType(String type);
    public TypeTableEntity findTypeById(int id);
    public List<TypeTableEntity> showAllTypes();
}
