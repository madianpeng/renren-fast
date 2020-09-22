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
import io.renren.modules.sys.entity.SysUserTokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 系统配置Redis
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class SysTokenRedis {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdateToken(SysUserTokenEntity tokenEntity) {
        if(tokenEntity == null){
            return ;
        }
        String key = RedisKeys.getSysTokenKey(tokenEntity.getToken());
        redisUtils.set(key, tokenEntity);
    }

    public void delete(String token) {
        String key = RedisKeys.getSysTokenKey(token);
        redisUtils.delete(key);
    }

    public SysUserTokenEntity get(String token){
        String key = RedisKeys.getSysTokenKey(token);
        return redisUtils.get(key, SysUserTokenEntity.class);
    }
}
