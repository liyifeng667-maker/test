/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.lanfan.zhgd.api;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.jeeplus.aop.logging.annotation.ApiLog;
import com.jeeplus.core.excel.EasyExcelUtils;
import com.jeeplus.core.excel.ExcelOptions;
import com.jeeplus.core.excel.annotation.ExportMode;
import com.jeeplus.core.query.QueryWrapperGenerator;
import com.lanfan.zhgd.domain.PersonBank;
import com.lanfan.zhgd.service.PersonBankService;
import com.lanfan.zhgd.service.dto.PersonBankDTO;
import com.lanfan.zhgd.service.mapstruct.PersonBankWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 人员库Controller
 * @author shy
 * @version 2025-12-23
 */

@RestController
@RequestMapping(value = "/app")
public class App {

	@Autowired
	private PersonBankService personBankService;

	@Autowired
	private PersonBankWrapper personBankWrapper;

	@RequestMapping("test")
	public JSONObject test(@RequestBody JSONObject param){
		System.out.println(param.toString());
		return null;
	}

}
