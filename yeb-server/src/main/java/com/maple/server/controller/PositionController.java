package com.maple.server.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.maple.common.R;
import com.maple.server.pojo.Position;
import com.maple.server.service.IPositionService;
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
@RequestMapping("/system/config/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;


    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public R getAllPositions(){
        List<Position> list = positionService.list();
        if(CollectionUtils.isEmpty(list)){
            return R.error().message("获取职位列表失败");
        }
        return R.success().data("list",list);
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public R addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if(positionService.save(position)){
            return R.success().message("添加成功");
        }
        return R.error().message("添加失败");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public R updatePosition(@RequestBody Position position){
        if(positionService.updateById(position)){
            return R.success().message("更新成功");
        }
        return R.error().message("更新失败");
    }

    @ApiOperation(value = "删除职位")
    @DeleteMapping("/deleteById/{id}")
    public R deletePosition(@PathVariable("id") Integer id){
        if(positionService.removeById(id)){
            return R.success().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @ApiOperation(value = "批量删除职位")
    @DeleteMapping("/deleteByIds/{ids}")
    public R deletePositionsByIds(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return R.success().message("删除成功");
        }
        return R.error().message("删除失败");
    }
}
