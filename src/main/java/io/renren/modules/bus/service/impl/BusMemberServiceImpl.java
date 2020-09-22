package io.renren.modules.bus.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.bus.dao.BusMemberDao;
import io.renren.modules.bus.entity.BusMemberEntity;
import io.renren.modules.bus.service.BusMemberService;


@Service("busMemberService")
public class BusMemberServiceImpl extends ServiceImpl<BusMemberDao, BusMemberEntity> implements BusMemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BusMemberEntity> page = this.page(
                new Query<BusMemberEntity>().getPage(params),
                new QueryWrapper<BusMemberEntity>()
        );

        return new PageUtils(page);
    }

}