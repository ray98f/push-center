package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.msg.pushcenter.pccore.entity.Dic;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 9:54
 */
public interface DicService extends IService<Dic> {


    Dic selectAllDic();
}
