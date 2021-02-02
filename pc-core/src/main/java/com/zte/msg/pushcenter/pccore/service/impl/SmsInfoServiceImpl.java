package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.mapper.SmsInfoMapper;
import com.zte.msg.pushcenter.pccore.service.SmsInfoService;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/2/2 10:54
 */
@Service
public class SmsInfoServiceImpl extends ServiceImpl<SmsInfoMapper, SmsInfo> implements SmsInfoService {
}
