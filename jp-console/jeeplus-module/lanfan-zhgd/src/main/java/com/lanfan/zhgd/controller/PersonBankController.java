/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.lanfan.zhgd.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.google.common.collect.Lists;
import com.jeeplus.core.excel.EasyExcelUtils;
import com.jeeplus.core.excel.ExcelOptions;
import com.jeeplus.core.excel.annotation.ExportMode;
import com.jeeplus.core.query.QueryWrapperGenerator;
import com.jeeplus.aop.logging.annotation.ApiLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanfan.zhgd.domain.PersonBank;
import com.lanfan.zhgd.service.dto.PersonBankDTO;
import com.lanfan.zhgd.service.mapstruct.PersonBankWrapper;
import com.lanfan.zhgd.service.PersonBankService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 人员库Controller
 * @author shy
 * @version 2025-12-23
 */

@Api(tags ="人员库")
@RestController
@RequestMapping(value = "/zhgd/personBank")
public class PersonBankController {

	@Autowired
	private PersonBankService personBankService;

	@Autowired
	private PersonBankWrapper personBankWrapper;

	/**
	 * 人员库列表数据
	 */
	@ApiLog("查询人员库列表数据")
	@ApiOperation(value = "查询人员库列表数据")
	@PreAuthorize("hasAuthority('zhgd:personBank:list')")
	@GetMapping("list")
	public ResponseEntity<IPage<PersonBank>> list(PersonBankDTO personBankDTO, Page<PersonBank> page) throws Exception {
		QueryWrapper queryWrapper = QueryWrapperGenerator.buildQueryCondition (personBankDTO, PersonBankDTO.class);
		IPage<PersonBank> result = personBankService.page (page, queryWrapper);
		return ResponseEntity.ok (result);
	}


	/**
	 * 根据Id获取人员库数据
	 */
	@ApiLog("根据Id获取人员库数据")
	@ApiOperation(value = "根据Id获取人员库数据")
	@PreAuthorize("hasAnyAuthority('zhgd:personBank:view','zhgd:personBank:add','zhgd:personBank:edit')")
	@GetMapping("queryById")
	public ResponseEntity<PersonBankDTO> queryById(String id) {
		return ResponseEntity.ok ( personBankWrapper.toDTO ( personBankService.getById ( id ) ) );
	}

	/**
	 * 保存人员库
	 */
	@ApiLog("保存人员库")
	@ApiOperation(value = "保存人员库")
	@PreAuthorize("hasAnyAuthority('zhgd:personBank:add','zhgd:personBank:edit')")
	@PostMapping("save")
	public  ResponseEntity <String> save(@Valid @RequestBody PersonBankDTO personBankDTO) {
		//新增或编辑表单保存
		if (personBankDTO.getAvatarPath() != null){
			personBankDTO.setAvatarPath(personBankDTO.getAvatarPath().replace("amp;",""));
		}
		personBankService.saveOrUpdate (personBankWrapper.toEntity (personBankDTO));
        return ResponseEntity.ok ( "保存人员库成功" );
	}


	/**
	 * 删除人员库
	 */
	@ApiLog("删除人员库")
	@ApiOperation(value = "删除人员库")
	@PreAuthorize("hasAuthority('zhgd:personBank:del')")
	@DeleteMapping("delete")
	public ResponseEntity <String> delete(String ids) {
		String idArray[] = ids.split(",");
        personBankService.removeByIds ( Lists.newArrayList ( idArray ) );
		return ResponseEntity.ok( "删除人员库成功" );
	}
	
	/**
     * 导出人员库数据
     *
     * @param personBankDTO
     * @param page
     * @param response
     * @throws Exception
     */
    @ApiLog("导出人员库数据")
    @PreAuthorize("hasAnyAuthority('zhgd:personBank:export')")
    @GetMapping("export")
    public void exportFile(PersonBankDTO personBankDTO, Page <PersonBank> page, ExcelOptions options, HttpServletResponse response) throws Exception {
        String fileName = options.getFilename ( );
		QueryWrapper queryWrapper = QueryWrapperGenerator.buildQueryCondition (personBankDTO, PersonBankDTO.class);
        if ( ExportMode.current.equals ( options.getMode ( ) ) ) { // 导出当前页数据
            
        } else if ( ExportMode.selected.equals ( options.getMode ( ) ) ) { // 导出选中数据
            queryWrapper.in ( "id", options.getSelectIds () );
        } else { // 导出全部数据
            page.setSize ( -1 );
            page.setCurrent ( 0 );
        }
        List < PersonBank> result = personBankService.page ( page, queryWrapper ).getRecords ( );
        EasyExcelUtils.newInstance ( personBankService, personBankWrapper ).exportExcel ( result,  options.getSheetName ( ), PersonBankDTO.class, fileName,options.getExportFields (), response );
    }

    /**
     * 导入人员库数据
     *
     * @return
     */
    @PreAuthorize("hasAnyAuthority('zhgd:personBank:import')")
    @PostMapping("import")
    public ResponseEntity importFile(MultipartFile file) throws IOException {
        String result = EasyExcelUtils.newInstance ( personBankService, personBankWrapper ).importExcel ( file, PersonBankDTO.class );
        return ResponseEntity.ok ( result );
    }

    /**
     * 下载导入人员库数据模板
     *
     * @param response
     * @return
     */
    @PreAuthorize ("hasAnyAuthority('zhgd:personBank:import')")
    @GetMapping("import/template")
    public void importFileTemplate(HttpServletResponse response) throws IOException {
        String fileName = "人员库数据导入模板.xlsx";
        List<PersonBankDTO> list = Lists.newArrayList();
        EasyExcelUtils.newInstance ( personBankService, personBankWrapper ).exportExcel ( list,  "人员库数据", PersonBankDTO.class, fileName, null, response );
    }

}
