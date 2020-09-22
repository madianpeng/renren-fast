package io.renren.modules.bus.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.bus.entity.BusMemberEntity;
import io.renren.modules.bus.service.BusMemberService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户信息
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-18 16:52:22
 */
@RestController
@RequestMapping("bus/busmember")
public class BusMemberController extends AbstractController {
    @Autowired
    private BusMemberService busMemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bus:busmember:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = busMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}")
    @RequiresPermissions("bus:busmember:info")
    public R info(@PathVariable("memberId") Long memberId){
		BusMemberEntity busMember = busMemberService.getById(memberId);

        return R.ok().put("busMember", busMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bus:busmember:save")
    public R save(@RequestBody BusMemberEntity busMember){
        busMember.setCreateUserId(getUserId());
        busMember.setCreateTime(new Date());
		busMemberService.save(busMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bus:busmember:update")
    public R update(@RequestBody BusMemberEntity busMember){
		busMemberService.updateById(busMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bus:busmember:delete")
    public R delete(@RequestBody Long[] memberIds){
		busMemberService.removeByIds(Arrays.asList(memberIds));

        return R.ok();
    }

}
