package edu0425.spring.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import edu0425.common.page.PaginationResult;
import edu0425.spring.vo.EmpInfo;
import edu0425.spring.vo.PopulationInfo;

public interface PopulationMapper {
	@Select("select * from `2020-a`")
	@ResultMap("PopulationInfoResult")
    List<PopulationInfo>getPopulationList();
	@Select("select count(*) as cnt from `2020-a`")
	Integer getPopuCount();
	
	@Select("select * from `2020-a` limit #{cursor},#{offset}")
	@ResultMap("PopulationInfoResult")
	List<PopulationInfo>getPopuPage(@Param("cursor")Integer cursor, @Param("offset")Integer offset);
	
	@Select ("select * from `2020-a` where did = #{did}")
	@ResultMap("PopulationInfoResult")
	PopulationInfo getPopuById(@Param("did")String did);
	
	@Select ("select * from `2020-a` where dname like '%#{dname}%'")
	@ResultMap("PopulationInfoResult")
	List<PopulationInfo> selectPopu(@Param("dname")String dname);
	
	
}
