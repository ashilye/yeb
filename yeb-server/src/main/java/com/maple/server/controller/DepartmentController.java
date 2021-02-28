package com.maple.server.controller;


import com.maple.common.R;
import com.maple.server.pojo.Department;
import com.maple.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public R getAllDepartments(){
        List<Department> list = departmentService.getAllDepartments(-1);
        if(CollectionUtils.isEmpty(list)){
            return R.error().message("查询所有部门失败");
        }
        return R.success().data("list",list);
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public R addDep(@RequestBody Department department){
        return departmentService.addDep(department);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public R deleteDep(@PathVariable Integer id){
        return departmentService.deleteDep(id);
    }
}
