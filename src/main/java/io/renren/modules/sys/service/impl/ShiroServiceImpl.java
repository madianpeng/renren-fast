/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;

import io.renren.common.utils.Constant;
import io.renren.common.utils.NonUtil;
import io.renren.modules.sys.dao.SysMenuDao;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.dao.SysUserTokenDao;
import io.renren.modules.sys.entity.SysMenuEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserTokenEntity;
import io.renren.modules.sys.redis.SysTokenRedis;
import io.renren.modules.sys.redis.SysUserRedis;
import io.renren.modules.sys.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Autowired
    private SysUserRedis sysUserRedis;

    @Autowired
    private SysTokenRedis sysTokenRedis;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList = new ArrayList();
        Set<String> permsSet = new HashSet<>();

        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsSet = sysUserRedis.get(userId);
            if (NonUtil.isNotNon(permsSet)) {
                return permsSet;
            }
            permsSet = new HashSet<>();
            permsList = sysUserDao.queryAllPerms(userId);

        }
        //用户权限列表
        for (String perms : permsList) {
            if (NonUtil.isNon(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        sysUserRedis.saveOrUpdateUserPerms(userId,permsSet);
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        SysUserTokenEntity sysUserToken = sysTokenRedis.get(token);
        if (NonUtil.isNon(sysUserToken)){
            return sysUserToken;
        }
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}
