package io.renren.modules.bus.dao;

import io.renren.modules.bus.entity.BusMemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-18 16:52:22
 */
@Mapper
public interface BusMemberDao extends BaseMapper<BusMemberEntity> {
	
}
