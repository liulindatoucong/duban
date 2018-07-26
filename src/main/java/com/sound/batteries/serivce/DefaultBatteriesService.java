package com.sound.batteries.serivce;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sound.batteries.mybatis.dao.BatteriesInfoDao;
import com.sound.batteries.mybatis.dao.MetrialDao;
import com.sound.batteries.mybatis.dao.PreviewDao;
import com.sound.batteries.mybatis.model.BtsBatteriesPackDetailInfo;
import com.sound.batteries.mybatis.model.BtsMoudlecell;
import com.sound.batteries.mybatis.model.BtsMoudlecellPreview;
import com.sound.batteries.mybatis.model.BtsPackcode;
import com.sound.batteries.mybatis.model.BtsPackcodePreview;
import com.sound.batteries.mybatis.model.BtsPackmodule;
import com.sound.batteries.mybatis.model.BtsPackmodulePreview;
import com.sound.batteries.mybatis.model.BtsUploadLog;
import com.sound.batteries.mybatis.model.Metrial;
import com.sound.batteries.utils.AESClass;
import com.sound.batteries.utils.BatteryProduceEnum;
import com.sound.batteries.utils.ExcelFileReadUtil;
import com.sound.batteries.utils.HmacUtil;
import com.sound.batteries.utils.JILIINFO;
import com.sound.model.BtsBatteriesPackDetailInfoModel;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonSerializer;

@Service("defaultBatteriesService")
public class DefaultBatteriesService implements BatteriesServiceInterface {

	private PreviewDao previewDao;
	
	private BatteriesInfoDao batteriesInfoDao;
	
	private MetrialDao metrialDao;
	
	private final String CREATE = "10";
	
	private final String UPLOAD = "30";
	
	@Transactional
	@Override
	public String saveFileInfo(File file, String site, String userName) throws Exception {
		ExcelFileReadUtil er = ExcelFileReadUtil.getInstance();
		List<String[]> dataInfos = er.getExcelData(file);
		return createDataObject(dataInfos, site, userName);
	}

	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午11:04:40
	 * @Description: 创建数据对象并保存到数据库，同时校验数据是否正确
	 * @param dataInfos
	 * @param site
	 * @param userName
	 * @throws Exception
	 */
	private String createDataObject(List<String[]> dataInfos, String site, String userName) throws Exception
	{
		
		List<BtsMoudlecellPreview> btsMoudlecellPreviews = new ArrayList<BtsMoudlecellPreview>();
		List<BtsPackcodePreview> btsPackcodePreviews = new ArrayList<BtsPackcodePreview>();
		List<BtsPackmodulePreview> btsPackmodulePreviews = new ArrayList<BtsPackmodulePreview>();
		Map<String, String> packCodes2Handles = new HashMap<String, String>();
		Map<String, String> moduleCodes2Handles = new HashMap<String, String>();
		for(String[] dataInfo:dataInfos)
		{
			if(StringUtils.isEmpty(dataInfo[0])||
					StringUtils.isEmpty(dataInfo[1])||
					StringUtils.isEmpty(dataInfo[2])||
					StringUtils.isEmpty(dataInfo[3]))
			{
				throw new Exception("前四列必须的数据不能为空");
			}
			
			if(!packCodes2Handles.containsKey(dataInfo[1]))
			{
				createBtsPackcodePreview(dataInfo, btsPackcodePreviews, packCodes2Handles, site, userName);
			}
			
			if(!moduleCodes2Handles.containsKey(dataInfo[2]))
			{
				createBtsPackmodulePreview(dataInfo, btsPackmodulePreviews, packCodes2Handles, moduleCodes2Handles);
			}
			createBtsMoudlecellPreview(dataInfo, btsMoudlecellPreviews, moduleCodes2Handles);
		}
		previewDao.deletePackcodePreviews(userName);
		
		previewDao.insertPackcodePreviews(btsPackcodePreviews);
		previewDao.insertPackmodulePreviews(btsPackmodulePreviews);
		previewDao.insertModulecellPreviews(btsMoudlecellPreviews);
		
		return checkInfoIsExists();
		
	}
	
	private String checkInfoIsExists()
	{
		StringBuffer sb = new StringBuffer("");
		List<BtsMoudlecellPreview> result1 = previewDao.checkModulecellPreviews();
		List<BtsPackcodePreview> result2 = previewDao.checkPackcodePreviewsExists();
		List<BtsPackmodulePreview> result3 = previewDao.checkPackmodulePreviewsExists();
		if(result1!=null&&!result1.isEmpty())
		{
			BtsMoudlecellPreview bp = result1.get(0);
			sb.append("数据已经存在：电芯号："+bp.getCellcode()+"，状态："+getStatus(bp.getStatus())+"\n");
		}
		if(result3!=null&&!result3.isEmpty())
		{
			BtsPackmodulePreview bp = result3.get(0);
			sb.append("数据已经存在：模块号："+bp.getMoudlecode()+"，状态："+getStatus(bp.getStatus())+"\n");
		}
		if(result2!=null&&!result2.isEmpty())
		{
			BtsPackcodePreview bp = result2.get(0);
			sb.append("数据已经存在：电芯PACK号："+bp.getPackcode()+"，状态："+getStatus(bp.getStatus())+"\n");
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午11:15:42
	 * @Description: 将状态翻译为中文解释
	 * @param status
	 * @return
	 */
	private String getStatus(String status)
	{
		if(CREATE.equals(status))
		{
			return "在系统中已存在，请确认导入数据是否正确";
		}
		else if(UPLOAD.equals(status))
		{
			return "在系统中已上传至整车企业，请确认导入数据是否正确";
		}
		else
		{
			return "出现错误的状态"+status;
		}
	}
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午8:59:07
	 * @Description: 创建pack对象
	 * @param dataInfos
	 * @param btsPackcodePreviews
	 * @param packCodes2Handles
	 * @param site
	 * @param userName
	 */
	private void createBtsPackcodePreview(String[] dataInfos, List<BtsPackcodePreview> btsPackcodePreviews, Map<String, String> packCodes2Handles, String site, String userName)
	{
		BtsPackcodePreview bp = new BtsPackcodePreview();
		bp.setPackhandle(createUUID());
		bp.setPlant(site);
		bp.setPackorder(dataInfos[0]);
		bp.setPackcode(dataInfos[1]);
		bp.setPackmaterial(dataInfos[4]);
		bp.setStatus(CREATE);
		bp.setCreatetime(new Date());
		bp.setCreateuser(userName);
		btsPackcodePreviews.add(bp);
		packCodes2Handles.put(dataInfos[1], bp.getPackhandle());
	}

	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午9:24:46
	 * @Description: 创建pack模块对象
	 * @param dataInfos
	 * @param btsPackmodulePreviews
	 * @param packCodes2Handles
	 * @param moduleCodes2Handles
	 */
	private void createBtsPackmodulePreview(String[] dataInfos, List<BtsPackmodulePreview> btsPackmodulePreviews, Map<String, String> packCodes2Handles, Map<String, String> moduleCodes2Handles)
	{
		BtsPackmodulePreview bp = new BtsPackmodulePreview();
		bp.setMoudlehandle(createUUID());
		bp.setMoudlecode(dataInfos[2]);
		bp.setPackcode(dataInfos[1]);
		String packHandle = packCodes2Handles.get(dataInfos[1]);
		bp.setPackhandle(packHandle);
		bp.setStatus(CREATE);
		bp.setMoudlematerial(dataInfos[5]);
		btsPackmodulePreviews.add(bp);
		moduleCodes2Handles.put(dataInfos[2], bp.getMoudlehandle());
		
	}
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年7月25日 上午9:30:57
	 * @Description: 创建电芯对象
	 * @param dataInfos
	 * @param btsMoudlecellPreviews
	 * @param moduleCodes2Handles
	 */
	private void createBtsMoudlecellPreview(String[] dataInfos, List<BtsMoudlecellPreview> btsMoudlecellPreviews, Map<String, String> moduleCodes2Handles)
	{
		BtsMoudlecellPreview bp = new BtsMoudlecellPreview();
		bp.setCellhandle(createUUID());
		bp.setCellcode(dataInfos[3]);
		bp.setMoudlecode(dataInfos[2]);
		String muduleHandle = moduleCodes2Handles.get(dataInfos[2]);
		bp.setMoudlehandle(muduleHandle);
		bp.setStatus(CREATE);
		bp.setCellmaterial(dataInfos[6]);
		btsMoudlecellPreviews.add(bp);
	}
	
	
	/**
	 * 产生唯一ID
	 * @author liulin
	 * @date 2018年7月25日 上午9:20:48
	 * @Description: TODO
	 * @return
	 */
	private String createUUID()
	{
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
	
	public PreviewDao getPreviewDao() {
		return previewDao;
	}

	@Autowired
	@Required
	public void setPreviewDao(@Qualifier("defaultPreviewDao")PreviewDao previewDao) {
		this.previewDao = previewDao;
	}

	@Override
	public List<BtsBatteriesPackDetailInfoModel> getPreviewInfo(String username) {
		List<BtsBatteriesPackDetailInfo> detailInfos = previewDao.getPreviewInfo(username);
		List<BtsBatteriesPackDetailInfoModel> detailInfoModules = new ArrayList<BtsBatteriesPackDetailInfoModel>();
		for(BtsBatteriesPackDetailInfo detailInfo:detailInfos)
		{
			BtsBatteriesPackDetailInfoModel detailInfoModel = new BtsBatteriesPackDetailInfoModel();
			BeanUtils.copyProperties(detailInfo,detailInfoModel);
			detailInfoModules.add(detailInfoModel);
		}
		return detailInfoModules;
	}
	
	@Transactional
	@Override
	public void saveBatteriesInfoFromPreview(String username)
	{
		batteriesInfoDao.savePackFromPreview(username);
		batteriesInfoDao.saveModuleFromPreview(username);
		batteriesInfoDao.saveCellFromPreview(username);	
		previewDao.deletePackcodePreviews(username);
	}

	public BatteriesInfoDao getBatteriesInfoDao() {
		return batteriesInfoDao;
	}

	@Autowired
	@Required
	public void setBatteriesInfoDao(@Qualifier("defaultBatteriesInfoDao")BatteriesInfoDao batteriesInfoDao) {
		this.batteriesInfoDao = batteriesInfoDao;
	}
	
	@Override
	public void uploadInfo2JiLi(String username) throws Exception
	{
		List<BtsPackcode> bpcs = batteriesInfoDao.getNeedUploadBtsPackcodeByUser(username);
		for(BtsPackcode bpc:bpcs)
		{
			sendOneBatteriesPack2JiLi(bpc, username);
		}
		
	}
	
	/**
	 * 上传单个pack信息到吉利
	 * @author liulin
	 * @date 2018年7月25日 下午7:48:05
	 * @Description: TODO
	 * @param bpc
	 * @throws Exception 
	 */
	@Transactional
	private void sendOneBatteriesPack2JiLi(BtsPackcode bpc, String username) throws Exception
	{
		Date startDate = new Date();
		String responseMsg = "";
		String requestMsg = "";
		String status = "";
		try {
			List<String> moduleHandle = new ArrayList<String>();
			List<String> cellHandle = new ArrayList<String>();
			Metrial ma = metrialDao.getMetrialbyCode(bpc.getPackmaterial());
			Map<String, Object> packInfo = new HashMap<String, Object>();
			packInfo.put("code", bpc.getPackcode());
			packInfo.put("moduleList", getModuleInfo(ma, bpc.getPackhandle(), moduleHandle, cellHandle));
			packInfo.put("serial", ma.getSystemcode());
			packInfo.put("modelId", ma.getPackmodle());
			packInfo.put("systemId", ma.getSystemid());
			packInfo.put("systemModelId", ma.getSystemmodelid());
			packInfo.put("orderNo", bpc.getPackorder());
			List<Map<String, Object>> packInfos = new ArrayList<Map<String, Object>>();
			packInfos.add(packInfo);
			requestMsg = new JsonSerializer().deep(true).serialize(packInfos);
					
			Map map = send(requestMsg);	
			analysisReturnInfo(map, bpc.getPackhandle(), moduleHandle, cellHandle, status);
	        responseMsg = new JsonSerializer().deep(true).serialize(map);
		}
		catch(Exception e)
		{
			responseMsg = responseMsg + e.getMessage();
			throw e;
		}
		finally 
		{
			insertLog(startDate, username, bpc.getPlant(), status, requestMsg, responseMsg);
		}
		
	}
	
	/**
	 * 解析返回结果
	 * @author liulin
	 * @date 2018年7月26日 上午11:23:28
	 * @Description: TODO
	 * @param map
	 * @throws Exception 
	 */
	private void analysisReturnInfo(Map map,String packHandle, List<String> moduleHandle, List<String> cellHandle, String status) throws Exception
	{
		String code = String.valueOf( map.get("code"));
		if(BatteryProduceEnum.RESULTCODE0.code.equals(code))
		{
			status = "Y";
			updateStatus(packHandle, moduleHandle, cellHandle);
		}
		else
		{
			status = "N";
			String result="";
	        if(isNotEmpty(map.get("data"))){
	            result = AESClass.decrypt(map.get("data").toString());
	        }else {
	            result = AESClass.decrypt(map.get("msg").toString());
	        }
	        String errorMsg = BatteryProduceEnum.getDes(code);
	        throw new Exception(result + " : " + errorMsg);
		}
	}
	
	/**
	 * 记录日志
	 * @author liulin
	 * @date 2018年7月26日 上午11:10:20
	 * @Description: TODO
	 * @param startDate
	 */
	private void insertLog(Date startDate, String username, String plant, String status, String requestMsg, String responseMsg)
	{
		BtsUploadLog log = new BtsUploadLog();
		log.setHandle(createUUID());
		log.setPlant(plant);
		log.setEndtime(new Date());
		log.setIp(JILIINFO.IP);
		log.setRequestmsg(requestMsg);
		log.setResponsemsg(responseMsg);
		log.setStarttime(startDate);
		log.setUser(username);
		log.setStatus(status);
		batteriesInfoDao.insertUploadLog(log);
	}
	
	/**
	 * 更新上传的电芯状态为30
	 * @author liulin
	 * @date 2018年7月26日 上午11:02:34
	 * @Description: TODO
	 * @param packHandle
	 * @param moduleHandle
	 * @param cellHandle
	 */
	private void updateStatus(String packHandle, List<String> moduleHandle, List<String> cellHandle)
	{
		batteriesInfoDao.updateNeedUploadbtsMoudlecell(cellHandle);
		batteriesInfoDao.updateNeedUploadBtsPackmodule(moduleHandle);
		batteriesInfoDao.updateNeedUploadBtsPackcode(packHandle);
	}
	
	/**
	 * 获取模组信息
	 * @author liulin
	 * @date 2018年7月26日 上午9:52:14
	 * @Description: TODO
	 * @param ma
	 * @param packHandle
	 * @return
	 */
	private List<Map<String, Object>> getModuleInfo(Metrial ma, String packHandle, List<String> moduleList, List<String> cellLists)
	{
		List<Map<String, Object>> moduleInfos = new ArrayList<Map<String, Object>>();
		List<BtsPackmodule> modules = batteriesInfoDao.getNeedUploadBtsPackmoduleByPackHandle(packHandle); 
		for(BtsPackmodule btsPackmodule:modules)
		{
			Map<String, Object> moduleInfo = new HashMap<String, Object>();
			moduleInfo.put("code", btsPackmodule.getMoudlecode());
			moduleInfo.put("cellList", getCellList(btsPackmodule.getMoudlehandle(), cellLists));
			moduleInfo.put("modelId", ma.getModelid());
			moduleInfo.put("cellModelId", ma.getCellmodelid());
			moduleInfos.add(moduleInfo);
			moduleList.add(btsPackmodule.getMoudlehandle());
		}
		
		return moduleInfos;
	}
	
	/**
	 * 获取电芯编号
	 * @author liulin
	 * @date 2018年7月26日 上午9:46:21
	 * @Description: TODO
	 * @param moduleHandle
	 * @return
	 */
	private List<String> getCellList(String moduleHandle, List<String> cellLists)
	{
		List<String> cellList = new ArrayList<String>();
		List<BtsMoudlecell> cells = batteriesInfoDao.getNeedUploadbtsMoudlecellByModuleHandle(moduleHandle);
		for(BtsMoudlecell cell:cells)
		{
			cellList.add(cell.getCellcode());
		}
		cellLists.addAll(cellList);
		return cellList;
	}
	
	/**
	 * 发送到吉利
	 * @author liulin
	 * @date 2018年7月26日 上午9:56:16
	 * @Description: TODO
	 * @param packInfo
	 * @throws Exception 
	 */
	private Map send(String requestMsg) throws Exception
	{
		Map<String, Object> requestMap = new HashMap<String, Object>();
		String enStr = AESClass.encrypt(requestMsg);
		requestMap.put("requestMsg", enStr);
        requestMap.put("timestamp", System.currentTimeMillis());
        String sign = HmacUtil.byteArrayToHexString(HmacUtil.encryptHMAC(enStr.getBytes(), JILIINFO.AES_KEY));
        requestMap.put("sign", sign);
        HttpResponse response = getRequest(JILIINFO.IP, JILIINFO.TOKEN, requestMap).send().unzip().unzip();
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(response.body(), Map.class);
        return map;
	}
	
    private boolean isNotEmpty(Object o) {
        if (o == null) {
            return false;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return false;
        } else {
            return true;
        }
    }
    
    private String FilterNull(Object o) {
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "" ;
    }
	
    /**
     * 装载请求
     * @author liulin
     * @date 2018年7月26日 上午11:32:35
     * @Description: TODO
     * @param url
     * @param accessToken
     * @param params
     * @return
     */
	private HttpRequest getRequest(String url, String accessToken, Map<String, Object> params) {
        HttpRequest request = HttpRequest.post(url);
        request.header("Authorization", "Bearer " + accessToken);
        request.header("Content-Type", "application/json; charset=utf-8");
        request.acceptEncoding("gzip");
        request.body((new JsonSerializer()).serialize(params));
        return request;
    }

	public MetrialDao getMetrialDao() {
		return metrialDao;
	}

	@Autowired
	@Required
	public void setMetrialDao(@Qualifier("defaultMetrialDao")MetrialDao metrialDao) {
		this.metrialDao = metrialDao;
	}
	
	
}
