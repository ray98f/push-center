package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.entity.ApplicationInfo;
import com.zte.msg.pushcenter.pccore.mapper.ApplicationInfoMapper;
import com.zte.msg.pushcenter.pccore.service.ApplicationService;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/2/2 11:03
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> implements ApplicationService {
}
