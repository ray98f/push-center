package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.res.DicDataResDTO;
import com.zte.msg.pushcenter.pccore.entity.DicData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 14:58
 */
public interface DicDataMapper extends BaseMapper<DicData> {

    @Select({
            "<script>",
            "SELECT " +
                    " id, " +
                    " `key`, " +
                    " `value`, " +
                    " is_enable, " +
                    " type, " +
                    " `order`, " +
                    " description, " +
                    " updated_at  " +
                    "FROM " +
                    " `dic_data`",
            "<where>",
            "<if test=\"value != '' and value != null\">",
            " AND `value` LIKE CONCAT('%',#{value}, '%') ",
            "</if>",
            "<if test = \"type != '' and type != null\">",
            " AND type = #{type} ",
            "</if>",
            "<if test = \"is_enable != null\">",
            " AND is_enable = #{is_enable}",
            "</if>",
            "</where>",
            "</script>"
    })
    Page<DicDataResDTO> selectDicDataByPage(Page<DicDataResDTO> page,
                                            @Param("type") String type,
                                            @Param("value") String value,
                                            @Param("is_enable") Integer isEnable);
}
