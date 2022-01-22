package xyz.chenprime.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import xyz.chenprime.pojo.AppFile;

@Mapper
public interface FileMapper {
    @Insert("insert into files(name, url, type, oldname) VALUES " +
            "(#{name},#{url},#{type},#{oldname})")
    int addFile(AppFile file);
}
