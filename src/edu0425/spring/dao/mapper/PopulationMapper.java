package edu0425.spring.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

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
	
	@Select ("select * from `2020-a` where did like #{did} '%' ")
	@ResultMap("PopulationInfoResult")
	List<PopulationInfo> getPopuListById(@Param("did")String did);
	
	@Select ("select * from `2020-a` where dname like '%'#{dname}'%'")
	@ResultMap("PopulationInfoResult")
	List<PopulationInfo> getPopuListByName(@Param("dname")String dname);
	
	
}
