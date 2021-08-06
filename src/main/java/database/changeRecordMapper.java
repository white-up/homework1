package database;

import entity.changeRecord;

import java.util.List;

public interface changeRecordMapper {
    public List<changeRecord> findAll();
}