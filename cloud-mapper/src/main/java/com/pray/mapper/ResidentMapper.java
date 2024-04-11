package com.pray.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pray.entity.po.Resident;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Rainy-Heights
* @description 针对表【resident】的数据库操作Mapper
* @createDate 2023-08-24 13:00:41
* @Entity com.pray.entity.po.Resident
*/
@Mapper
public interface ResidentMapper extends BaseMapper<Resident> {

}




