package edu0425.spring.service;

import java.util.List;

import edu0425.common.page.PaginationResult;
import edu0425.spring.vo.PopulationInfo;

public interface PopulationService {
	
	List<PopulationInfo>getPopulationList();

    Integer getPopuCount();
    
    PaginationResult<List<PopulationInfo>> getPopuPage(Integer pageIndex,Integer pageSize);

	PaginationResult<List<PopulationInfo>> getPopuPageByDname(String dname, Integer pageIndex,Integer pageSize);
	
	List<PopulationInfo> getPopuListById(String did);
}
