package com.maple.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.common.R;
import com.maple.server.pojo.Department;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);



    /**
     * 添加部门
     * @param dep
     * @return
     */
    R addDep(Department dep);

    /**
     * 删除部门
     * @param id
     * @return
     */
    R deleteDep(Integer id);
}
