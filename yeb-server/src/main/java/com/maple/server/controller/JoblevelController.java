package com.maple.server.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.maple.common.R;
import com.maple.server.pojo.Joblevel;
import com.maple.server.pojo.Position;
import com.maple.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public R getAllJoblevels(){
        List<Joblevel> list = joblevelService.list();
        if(CollectionUtils.isEmpty(list)){
            return R.error().message("获取职称列表失败");
        }
        return R.success().data("list",list);
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public R addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if(isExist(joblevel)){
            return R.error().message("添加失败,该职称已存在");
        }
        if(joblevelService.save(joblevel)){
            return R.success().message("添加成功");
        }
        return R.error().message("添加失败");
    }

    @ApiOperation(value = "更新职称")
    @PutMapping("/")
    public R updateJoblevel(@RequestBody Joblevel joblevel){
        if(isExist(joblevel)){
            return R.error().message("更新失败,该职称已存在");
        }
        if(joblevelService.updateById(joblevel)){
            return R.success().message("更新成功");
        }
        return R.error().message("更新失败");
    }

    @ApiOperation(value = "删除职称")
    @DeleteMapping("/deleteById/{id}")
    public R deleteJoblevel(@PathVariable Integer id){
        if(joblevelService.removeById(id)){
            return R.success().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/deleteByIds/{ids}")
    public R deleteJoblevels(Integer[] ids){
        if(joblevelService.removeByIds(Arrays.asList(ids))){
            return R.success().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    /**
     * 判断是否存在
     * @param joblevel
     * @return
     */
    private boolean isExist(Joblevel joblevel){
        List<Joblevel> list = joblevelService.list();
        if(!CollectionUtils.isEmpty(list)){
            for (Joblevel item : list) {
                if(joblevel.getName().equalsIgnoreCase(item.getName()) && joblevel.getTitleLevel().equalsIgnoreCase(item.getTitleLevel())){
                    return true;
                }
            }
        }
        return false;
    }

}
