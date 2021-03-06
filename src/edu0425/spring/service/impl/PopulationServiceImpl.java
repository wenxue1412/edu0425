package edu0425.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu0425.common.page.Pagination;
import edu0425.common.page.PaginationResult;
import edu0425.spring.dao.DeptDAO;
import edu0425.spring.dao.mapper.DeptMapper;
import edu0425.spring.dao.mapper.PopulationMapper;
import edu0425.spring.service.DeptService;
import edu0425.spring.service.PopulationService;
import edu0425.spring.vo.DeptInfo;
import edu0425.spring.vo.PopulationInfo;
@Component//相当于 bean id 
public class PopulationServiceImpl implements PopulationService{
	
	@Autowired
	private PopulationMapper popuMapper;

	@Override
	public List<PopulationInfo> getPopulationList() {
		// TODO Auto-generated method stub
		//deptDAO = new DeptDAOImpl();
		return popuMapper.getPopulationList();
	}
	@Override
	public List<PopulationInfo> getPopuById(String did) {
		return popuMapper.getPopuById(did);
	}

	@Override
	public Integer getPopuCount() {
		// TODO Auto-generated method stub
		//deptDAO = new DeptDAOImpl();
		return popuMapper.getPopuCount();
	}

	@Override
	public PaginationResult<List<PopulationInfo>> getPopuPage(Integer pageIndex, Integer pageSize) {
		Pagination pagination = new Pagination(pageIndex,pageSize);
		Integer totalCount = getPopuCount();
		pagination.setTotalCount(totalCount);
		
		List<PopulationInfo> list = popuMapper.getPopuPage(pagination.getCursor(), pagination.getOffset());
		pagination.setCurrentPageCount(list.size());
		
		PaginationResult<List<PopulationInfo>> result = new PaginationResult<List<PopulationInfo>>(pagination,list);
		return result;
	}
	@Override
	public PaginationResult<List<PopulationInfo>> getPopuByDname(String dname, Integer pageIndex, Integer pageSize){
		// TODO Auto-generated method stub
		Pagination pagination = new Pagination(pageIndex,pageSize);
		Integer totalCount = getPopuCount();
		pagination.setTotalCount(totalCount);
		List<PopulationInfo> list = popuMapper.getPopuByDname(dname);
		for(int i = 0 ;i < list.size();i++) {
			if(Integer.parseInt(list.get(i).getDid())<100) {
			List<PopulationInfo> list2= popuMapper.getPopuById(list.get(i).getDid());
			list.addAll(list2);
			}
		}
		pagination.setCurrentPageCount(list.size());
		PaginationResult<List<PopulationInfo>> result2 = new PaginationResult<List<PopulationInfo>>(pagination,list);
		return result2;
	}

}
