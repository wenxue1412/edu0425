package edu0425.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu0425.common.page.Pagination;
import edu0425.common.page.PaginationResult;
import edu0425.spring.dao.mapper.EmpMapper;
import edu0425.spring.service.EmpService;
import edu0425.spring.vo.EmpInfo;

@Component
public class EmpServiceImpl implements EmpService {

	//private EmpDAO empDAO;
	@Autowired
	private EmpMapper empMapper;

	@Override
	public List<EmpInfo> getEmpList() {
		// empDAO = new EmpDAOImpl();
		return empMapper.getEmpList();
	}

	@Override
	public EmpInfo getEmpById(Integer empno) {
		// TODO Auto-generated method stub
		return empMapper.getEmpById(empno);
	}

	@Override
	public Integer getEmpCount() {
		// empDAO = new EmpDAOImpl();
		return empMapper.getEmpCount();

	}

	@Override
	public PaginationResult<List<EmpInfo>> getEmpPage(Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		Pagination pagination = new Pagination(pageIndex, pageSize);
		Integer totalCount = getEmpCount();
		pagination.setTotalCount(totalCount);

		List<EmpInfo> list = empMapper.getEmpPage(pagination.getCursor(), pagination.getOffset());
		pagination.setCurrentPageCount(list.size());

		PaginationResult<List<EmpInfo>> result = new PaginationResult<List<EmpInfo>>(pagination, list);
		return result;
	}

	@Override
	public Integer addEmp(EmpInfo emp) {
		return empMapper.addEmp(emp);
	}

	@Override
	public Integer updateEmp(EmpInfo emp) {
		return empMapper.updateEmp(emp);
	}

	public Integer deleteEmp(Integer empno) {
		return empMapper.deleteEmp(empno);
	}

}
