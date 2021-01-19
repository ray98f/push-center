package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.entity.Dic;
import com.zte.msg.pushcenter.pccore.mapper.DicMapper;
import com.zte.msg.pushcenter.pccore.service.DicService;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 9:54
 */
@Service
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements DicService {


    @Override
    public Dic selectAllDic() {
        return getBaseMapper().selectAllDic();
    }
}
