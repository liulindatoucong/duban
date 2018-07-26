package com.sound.controller;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sound.batteries.serivce.BatteriesServiceInterface;
import com.sound.model.BtsBatteriesPackDetailInfoModel;
import com.sound.model.BtsBatteriesPackDetailModelMsg;
import com.sound.model.ResponseMessageM;

@RestController
public class BatteriesRestController {
	
	private BatteriesServiceInterface batteriesService;
	
	@ResponseBody
	@RequestMapping(path = "/batteriesaction/fileload", method = RequestMethod.POST)
	public ResponseMessageM dealWithBatteriesInfo(HttpServletRequest request)
	{
		ResponseMessageM rmm = new ResponseMessageM();
		rmm.setFlag("false");
		File excelFile = null;
		try {
			String username = (String) request.getSession().getAttribute("currentusername");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile mfile = multipartRequest.getFile("file");
			// 获取文件名
	        String fileName = mfile.getOriginalFilename();
	        // 获取文件后缀
	        String prefix=fileName.substring(fileName.lastIndexOf("."));
	        excelFile = File.createTempFile("tempName", prefix);
	        mfile.transferTo(excelFile);
	        
			String result = batteriesService.saveFileInfo(excelFile,"3002",username);
			if(result.isEmpty())
			{
				rmm.setFlag("true");
			}
			else
			{
				rmm.setMsg(result);
			}
			
			
		}catch(Exception e)
		{
			rmm.setFlag("false");
			rmm.setMsg("出现异常，请及时联系管理人员");
		}finally {
			if(excelFile != null)
			{
				excelFile.delete();
			}
		}	
		return rmm;
		
	}
	
	/**
	 * 获取预览数据
	 * @author liulin
	 * @date 2018年7月25日 下午2:49:45
	 * @Description: 获取预览数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "/batteriesaction/getpreinfo", method = RequestMethod.POST)
	public BtsBatteriesPackDetailModelMsg getPreviewInfo(HttpServletRequest request)
	{
		BtsBatteriesPackDetailModelMsg result = new BtsBatteriesPackDetailModelMsg();
		Set<String> packNumSet = new HashSet<String>();
		Set<String> moduleSet = new HashSet<String>();
		result.setFlag("true");
		try {
			String username = (String) request.getSession().getAttribute("currentusername");
			List<BtsBatteriesPackDetailInfoModel> bpm = batteriesService.getPreviewInfo(username);
			for(BtsBatteriesPackDetailInfoModel bp:bpm)
			{
				packNumSet.add(bp.getPackcode());
				moduleSet.add(bp.getMoudlecode());
			}
			result.setCellNum(String.valueOf(bpm.size()));
			result.setPackNum(String.valueOf(packNumSet.size()));
			result.setModuleNum(String.valueOf(moduleSet.size()));
			result.setDetailinfos(bpm);
		}
		catch(Exception e)
		{
			result.setFlag("false");
			result.setMsg("请联系管理员    "+e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(path = "/batteriesaction/saveinfo", method = RequestMethod.POST)
	public ResponseMessageM saveBatteriesInfoFromPreview(HttpServletRequest request)
	{
		ResponseMessageM rm = new ResponseMessageM();
		try {
			String username = (String) request.getSession().getAttribute("currentusername");
			rm.setFlag("true");
			batteriesService.saveBatteriesInfoFromPreview(username);
			rm.setMsg("保存成功，请及时上传");
		}
		catch(Exception e)
		{
			rm.setFlag("false");
			rm.setMsg("请联系管理员    "+e.getMessage());
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(path = "/batteriesaction/upload2jl", method = RequestMethod.POST)
	public ResponseMessageM uploadBatteries2JILI(HttpServletRequest request)
	{
		ResponseMessageM rm = new ResponseMessageM();
		try {
			String username = (String) request.getSession().getAttribute("currentusername");
			rm.setFlag("true");
			batteriesService.uploadInfo2JiLi(username);
		}
		catch(Exception e)
		{
			rm.setFlag("false");
			rm.setMsg("请联系管理员    "+e.getMessage());
		}
		return rm;
	}
	
	
	
	@Autowired
	@Required
	public void setCityService(@Qualifier(value = "defaultBatteriesService") BatteriesServiceInterface batteriesService) {
		this.batteriesService = batteriesService;
	}

	
}
