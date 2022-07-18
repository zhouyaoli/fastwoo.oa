package com.yaolizh.oa.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yaolizh.fastwoo.common.controller.BaseController;

/**
 * 
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2019-09-17 17:58:50
 */

@Controller
public class IndexController extends BaseController {

	@GetMapping({ "/", "" })
	String welcome(Model model) {

		// return "redirect:/blog";
		return "redirect:/login";
		// return "redirect:/front/bigdata";
	}

	
}
