package io.renren.modules.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.bus.entity.BusMemberEntity;

import java.util.Map;

/**
 * 用户信息
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-18 16:52:22
 */
public interface BusMemberService extends IService<BusMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

