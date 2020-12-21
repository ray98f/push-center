package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.entity.Dic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:29
 */
@Mapper
public interface DicMapper extends BaseMapper<Dic> {

    /**
     * 查询字典表
     *
     * @return
     */
    @Select("SELECT * FROM `dic`")
    Dic selectDic();
}
