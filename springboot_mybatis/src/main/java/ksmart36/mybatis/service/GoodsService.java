package ksmart36.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ksmart36.mybatis.domain.Goods;
import ksmart36.mybatis.domain.Member;
import ksmart36.mybatis.mapper.GoodsMapper;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	//상품목록 삭제
	public int removeGoods(String goodsCode) {
		int result =0;
		
		Goods goods = goodsMapper.getGoodsByGoodsCode(goodsCode);
		if(goods != null) {
			//1. 주문 테이블 삭제
			result += goodsMapper.removeOrder(goodsCode);	
			//2. 상품테이블 삭제
			result += goodsMapper.removeGoods(goodsCode);			
		}		
		return result;		
	}
	//상품목록 조건 검색
	public List<Goods> getSearchGoodsList(String sk, String sv){
		// 상품들의 정보가 담긴 list객체
		List<Goods> goodsList = goodsMapper.getSearchGoodsList(sk, sv);


		return goodsList;		
	}
	//상품 정보 수정
	public int modifyGoods(Goods goods) {
		return goodsMapper.modifyGoods(goods);
	}
	
	//상품코드로 상품정보 조회
	public Goods getGoodsByGoodsCode(String goodsCode) {
		Goods goods = goodsMapper.getGoodsByGoodsCode(goodsCode);
		
		return goods;		
	}
	
	//상품 추가
	public int addGoods(Goods goods) {
		int result = goodsMapper.addGoods(goods);
		
		return result;
	}
	
	public List<Goods> getGoodsList(){
		//회원들이 정보가 담긴 list객체
		List<Goods> goodsList = goodsMapper.getGoodsList();			
		
		return goodsList;
	}
}
