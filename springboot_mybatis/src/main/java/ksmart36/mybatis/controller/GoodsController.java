package ksmart36.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart36.mybatis.domain.Goods;
import ksmart36.mybatis.domain.Member;
import ksmart36.mybatis.mapper.GoodsMapper;
import ksmart36.mybatis.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;	
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@PostMapping("/modifyGoods")
	public String modifyGoods(Goods goods) {
		System.out.println("회원수정정보->" + goods);
		goodsService.modifyGoods(goods);
		return "redirect:/getGoodsList";
		
	}
	
	@GetMapping("/modifyGoods")
	public String modifyGoods(@RequestParam(value="goodsSellerId", required = false) String goodsSellerId
							 ,@RequestParam(value="goodsCode", required = false) String goodsCode
							 ,HttpSession session
							 ,Model model ) {
		String memberId = (String)session.getAttribute("SID");	
		System.out.println("로그인 아이디->"+memberId);
		System.out.println("판매자 아이디->"+goodsSellerId);
		System.out.println("상품코드->"+goodsCode);
		
		Goods goods = goodsService.getGoodsByGoodsCode(goodsCode);
		model.addAttribute("goods", goods);
		model.addAttribute("title", "상품정보수정");	
		if(memberId.equals(goodsSellerId)) {
			return "goods/modifyGoods";				
		}else {
			return "redirect:/getGoodsList";	
		}		
	}
	
	@PostMapping("/addGoods")
	public String addGoods(Goods goods
							,HttpSession session
							) {
		String memberId = (String)session.getAttribute("SID");				
		goods.setGoodsSellerId(memberId);
		System.out.println("GoodsMapper memberId-->" + memberId);
		System.out.println("GoodsMapper goods-->" + goods);
		goodsService.addGoods(goods);
		return "redirect:/getGoodsList";		
	}
	
	@RequestMapping(value="/addGoods")
	public String addGoodsList(Model model) {
		model.addAttribute("title", "상품등록");
		return "goods/addGoods";
	}
	
	@PostMapping("/getGoodsList")
	public String getGoodsList(Model model
								,@RequestParam(value="sk", required = false) String sk
								,@RequestParam(value="sv", required = false) String sv) {
		
		List<Goods> goodsList = goodsService.getSearchGoodsList(sk, sv);
		System.out.println("goodsList->" + goodsList);
		
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("title", "상품 목록 조회");
		
		return "goods/goodsList";
	}	
	//상품 목록 조회	
	@RequestMapping(value="/getGoodsList", method=RequestMethod.GET)
	public String getGoodsList(Model model) {
		
		List<Goods> goodsList = goodsService.getGoodsList();
		System.out.println("goodsList->" + goodsList);
		
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("title", "상품 목록 조회");
		
		return "goods/goodsList";
	}
}
