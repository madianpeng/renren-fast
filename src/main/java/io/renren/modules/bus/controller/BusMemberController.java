package io.renren.modules.bus.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "用户信息模块")
@RequestMapping("bus/busmember")
public class BusMemberController extends AbstractController {
    @Autowired
    private BusMemberService busMemberService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("用户列表")
    @RequiresPermissions("bus:busmember:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = busMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{memberId}")
    @ApiOperation("获取用户信息")
    @RequiresPermissions("bus:busmember:info")
    public R info(@PathVariable("memberId") Long memberId) {
        BusMemberEntity busMember = busMemberService.getById(memberId);

        return R.ok().put("busMember", busMember);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存用户信息")
    @RequiresPermissions("bus:busmember:save")
    public R save(@RequestBody BusMemberEntity busMember) {
        busMember.setCreateUserId(getUserId());
        busMember.setCreateTime(new Date());
        busMemberService.save(busMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation("修改用户信息")
    @RequiresPermissions("bus:busmember:update")
    public R update(@RequestBody BusMemberEntity busMember) {
        busMemberService.updateById(busMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("用户信息删除")
    @RequiresPermissions("bus:busmember:delete")
    public R delete(@RequestBody Long[] memberIds) {
        busMemberService.removeByIds(Arrays.asList(memberIds));

        return R.ok();
    }

}
