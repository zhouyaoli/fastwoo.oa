package com.yaolizh.cyst.bigdata.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yaolizh.bigdata.echarts.service.BigDataService;
import com.yaolizh.bigdata.echarts.view.EChartView;
import com.yaolizh.fastwoo.common.controller.BaseController;
import com.yaolizh.fastwoo.common.jwt.view.LoginInfoView;
import com.yaolizh.fastwoo.common.utils.DateUtils;

/**
 * 
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2019-11-12 16:16:08
 */

@Controller
@RequestMapping("/front/bigdata")
public class BigDataController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BigDataController.class);

	@Autowired
	private BigDataService bigDataService;

	@GetMapping()
	public ModelAndView Board(@RequestParam Map<String, Object> param) {
		ModelAndView modelAndView = new ModelAndView("oa/bigData/bigData");
		modelAndView.addAllObjects(param);
		modelAndView.addObject("curDateAndDay", DateUtils.getCurrentDate() + DateUtils.getWeekOfDate(new Date()));
		return modelAndView;

	}

	//
	@RequestMapping("/getDistributeStstus")
	@ResponseBody
	public JSONObject getDistributeStstus(@RequestParam Map<String, Object> param) {
		JSONObject re = new JSONObject();
		List<EChartView> list = new ArrayList<EChartView>();
		try {
			// 查询列表数据
			LoginInfoView loginInfo = super.getLoginInfoView();
			if (null == loginInfo) {
				list = new ArrayList<EChartView>();
			} else {
				String targetId = loginInfo.getOrgId();
				StringBuffer sql = new StringBuffer(
						"	    select   '总数' as `name` ,count(1) as `value`    from echart_distributeStstus_view where target_id ='"+ targetId + "' ");
				sql.append(
						" union select   '未分配' as `name` ,count(1) as `value`    from echart_distributeStstus_view where target_id ='"+ targetId + "' and `value` = 0");
				sql.append(
						" union select   '已分配' as `name` ,count(1) as `value`        from echart_distributeStstus_view where target_id ='"+ targetId + "' and `value` =1");

				list = bigDataService.getEchartViewBysql(sql.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("出错啦 : " + e.getMessage());
			list = new ArrayList<EChartView>();
		}
		re.put("data", list);
		return re;

	}

	 
}
