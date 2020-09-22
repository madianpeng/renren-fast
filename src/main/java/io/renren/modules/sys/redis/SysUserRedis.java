/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.redis;


import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.sys.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 系统配置Redis
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class SysUserRedis {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdateUserPerms(Long userId,Set<String> perms) {
        if(perms == null){
            return ;
        }
        String key = RedisKeys.getSysUserKey(userId.toString());
        redisUtils.set(key, perms);
    }

    public void delete(Long userId) {
        String key = RedisKeys.getSysUserKey(userId.toString());
        redisUtils.delete(key);
    }

    public Set<String> get(Long userId){
        String key = RedisKeys.getSysConfigKey(userId.toString());
        return redisUtils.get(key, Set.class);
    }
}
