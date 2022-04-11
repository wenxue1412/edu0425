package edu0425.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu0425.common.page.Pagination;
import edu0425.common.page.PaginationResult;
import edu0425.spring.dao.mapper.PopulationMapper;
import edu0425.spring.service.PopulationService;
import edu0425.spring.vo.PopulationInfo;
@Component//鐩稿綋浜� bean id 
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
	public List<PopulationInfo> getPopuListById(String did) {
		return popuMapper.getPopuListById(did);
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
	public PaginationResult<List<PopulationInfo>> getPopuPageByDname(String dname, Integer pageIndex, Integer pageSize){
		// TODO Auto-generated method stub
		Pagination pagination = new Pagination(pageIndex,pageSize);
		Integer totalCount = getPopuCount();
		pagination.setTotalCount(totalCount);
		List<PopulationInfo> result = new ArrayList<>();
		List<PopulationInfo> list = popuMapper.getPopuListByName(dname);
		if(Integer.parseInt(list.get(0).getDid())<100 && list.size() == 1) {
			List<PopulationInfo> list2= popuMapper.getPopuListById(list.get(0).getDid());
			result.addAll(list2);
		} else {
			result.addAll(list);
		}
		
		pagination.setCurrentPageCount(result.size());
		PaginationResult<List<PopulationInfo>> result2 = new PaginationResult<List<PopulationInfo>>(pagination,result);
		return result2;
	}

}
