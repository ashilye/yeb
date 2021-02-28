package com.maple.server.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.maple.common.R;
import com.maple.server.pojo.Position;
import com.maple.server.pojo.Role;
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
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;


    @ApiOperation(value = "获取所有职位")
    @GetMapping("/")
    public R getAllPositions(){
        List<Position> list = positionService.list();
        if(CollectionUtils.isEmpty(list)){
            return R.error().message("获取职位列表失败");
        }
        return R.success().data("list",list);
    }

    @ApiOperation(value = "添加职位")
    @PostMapping("/")
    public R addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if(isExist(position)){
            return R.error().message("添加失败,该职位已存在");
        }
        if(positionService.save(position)){
            return R.success().message("添加成功");
        }
        return R.error().message("添加失败");
    }

    @ApiOperation(value = "更新职位")
    @PutMapping("/")
    public R updatePosition(@RequestBody Position position){
        if(isExist(position)){
            return R.error().message("更新失败,该职位已存在");
        }
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
    public R deletePositions(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return R.success().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    /**
     * 判断是否存在
     * @param position
     * @return
     */
    private boolean isExist(Position position){
        List<Position> list = positionService.list();
        if(!CollectionUtils.isEmpty(list)){
            for (Position item : list) {
                if(position.getName().equalsIgnoreCase(item.getName())){
                    return true;
                }
            }
        }
        return false;
    }
}
