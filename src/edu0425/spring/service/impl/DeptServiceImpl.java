package edu0425.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu0425.common.page.Pagination;
import edu0425.common.page.PaginationResult;
import edu0425.spring.dao.mapper.DeptMapper;
import edu0425.spring.service.DeptService;
import edu0425.spring.vo.DeptInfo;
@Component//相当于 bean id 
public class DeptServiceImpl implements DeptService{
	
	//private DeptDAO deptDAO;
	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<DeptInfo> getDeptList() {
		// TODO Auto-generated method stub
		//deptDAO = new DeptDAOImpl();
		return deptMapper.getDeptList();
	}
	@Override
	public DeptInfo getDeptById(Integer deptno) {
		return deptMapper.getDeptById(deptno);
	}

	@Override
	public Integer getDeptCount() {
		// TODO Auto-generated method stub
		//deptDAO = new DeptDAOImpl();
		return deptMapper.getDeptCount();
	}

	@Override
	public PaginationResult<List<DeptInfo>> getDeptPage(Integer pageIndex, Integer pageSize) {
		Pagination pagination = new Pagination(pageIndex,pageSize);
		Integer totalCount = getDeptCount();
		pagination.setTotalCount(totalCount);
		
		List<DeptInfo> list = deptMapper.getDeptPage(pagination.getCursor(), pagination.getOffset());
		pagination.setCurrentPageCount(list.size());
		
		PaginationResult<List<DeptInfo>> result = new PaginationResult<List<DeptInfo>>(pagination,list);
		return result;
	}

	@Override
	public Integer addDept(DeptInfo dept) {
		return deptMapper.addDept(dept);
	}

	@Override
	public Integer updateDept(DeptInfo dept) {
		return deptMapper.updateDept(dept);
	}
	
	public Integer deleteDept(Integer deptno) {
		return deptMapper.deleteDept(deptno);
	}

}
