package ksmart36.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart36.mybatis.domain.Goods;

@Mapper
public interface GoodsMapper {
	
	//1. 오더 테이블 삭제
	public int removeOrder(String goodsCode);
	//2. 상품 테이블 삭제
	public int removeGoods(String goodsCode);
	
	//상품목록 조건 검색
	public List<Goods> getSearchGoodsList(String sk, String sv);
		
	//상품정보 수정
	public int modifyGoods(Goods goods);
	
	//상품코드로 상품정보 조회
	public Goods getGoodsByGoodsCode(String goodsCode);
	
	//상품 정보 등록
	public int addGoods(Goods goods);
	
	//상품 목록 조회
	public List<Goods> getGoodsList();
	
}
