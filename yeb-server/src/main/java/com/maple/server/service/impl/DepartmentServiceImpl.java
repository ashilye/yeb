package com.maple.server.service.impl;

import com.maple.common.R;
import com.maple.server.pojo.Department;
import com.maple.server.mapper.DepartmentMapper;
import com.maple.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartments(Integer parentId) {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    /**
     * 添加部门
     * @param dep
     * @return
     */
    @Override
    public R addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1 == dep.getResult()){
            return R.success().message("添加成功").data("dep",dep);
        }

        return R.error().message("添加失败");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public R deleteDep(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleteDep(department);
        if (-2 == department.getResult()){
            return R.error().message("该部门下有子部门，删除失败！");
        }
        if (-1 == department.getResult()){
            return R.error().message("该部门下有员工，删除失败！");
        }
        if (1 == department.getResult()){
            return R.success().message("删除成功！");
        }
        return R.error().message("删除失败");
    }
}
