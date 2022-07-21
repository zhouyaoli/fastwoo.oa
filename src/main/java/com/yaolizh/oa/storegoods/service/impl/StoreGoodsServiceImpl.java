package com.yaolizh.oa.storegoods.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.storegoods.domain.StoreGoodsDO;
import com.yaolizh.oa.storegoods.service.StoreGoodsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StoreGoodsServiceImpl  extends BaseServiceImpl<StoreGoodsDO> implements StoreGoodsService {
	//@Autowired
//private StoreGoodsDao storeGoodsDao;
//
//@Override
//public StoreGoodsDO get(String id){
//	return storeGoodsDao.get(id);
//}
//
//@Override
//public List<StoreGoodsDO> list(Map<String, Object> map){
//	return storeGoodsDao.list(map);
//}
//
//
//@Override
//public int save(StoreGoodsDO storeGoods){
//	if(StringUtils.isEmpty(storeGoods.getId())){
//		storeGoods.setId(UuidUtil.getUUIDfor32());
//	}
//	storeGoods.setCreateTime(new Date());
//	return storeGoodsDao.save(storeGoods);
//}
//
//@Override
//public int update(StoreGoodsDO storeGoods){
//	storeGoods.setLastTime(new Date());
//	return storeGoodsDao.update(storeGoods);
//}
//
//@Override
//public int remove(String id){
//	return storeGoodsDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return storeGoodsDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<StoreGoodsDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (StoreGoodsDO storeGoods : list) {
			i++;
			if (StringUtils.isEmpty(storeGoods.getId())) {
				storeGoods.setCreator(loginInfo.getId() + "");
				storeGoods.setCreatorby(loginInfo.getUsername());
				storeGoods.setCreatorby(loginInfo.getName());
				storeGoods.setCreateDeptid(loginInfo.getDeptId() + "");
				storeGoods.setCreateDeptcode(loginInfo.getDeptId() + "");
				storeGoods.setCreateDeptname(loginInfo.getDeptName());
				save(storeGoods);
			} else {
				storeGoods.setUpdator(loginInfo.getId() + "");
				storeGoods.setUpdatorby(loginInfo.getUsername());
				storeGoods.setUpdatorby(loginInfo.getName());
				update(storeGoods);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
