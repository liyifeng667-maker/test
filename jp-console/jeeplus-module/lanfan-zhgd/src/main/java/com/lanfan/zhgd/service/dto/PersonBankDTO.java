/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.lanfan.zhgd.service.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.core.query.Query;
import com.jeeplus.core.query.QueryType;
import com.alibaba.excel.annotation.ExcelProperty;
import com.jeeplus.core.excel.converter.ExcelDictDTOConverter;
import com.jeeplus.core.excel.annotation.ExcelDictProperty;
import com.jeeplus.core.service.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 人员库DTO
 * @author shy
 * @version 2025-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonBankDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	        
	/**
     * 人员名称
     */
    @Query(type = QueryType.LIKE)
	@ExcelProperty("人员名称") 
	private String name;
	        
	/**
     * 年龄
     */
	@ExcelProperty("年龄") 
	private String age;
	        
	/**
     * 性别
     */
	@ExcelProperty(value = "性别", converter = ExcelDictDTOConverter.class)
	@ExcelDictProperty("lf_gender")
	private String gender;
	        
	/**
     * 工号
     */
	@ExcelProperty("工号") 
	private String jobNo;
	        
	/**
     * 身份证
     */
	@ExcelProperty("身份证") 
	private String idCard;
	        
	/**
     * 身份
     */
    @Query(type = QueryType.LIKE)
	@ExcelProperty(value = "身份", converter = ExcelDictDTOConverter.class)
	@ExcelDictProperty("lf_identity_type")
	private String identityType;
	        
	/**
     * 准入状态
     */
    @Query(type = QueryType.LIKE)
	@ExcelProperty(value = "准入状态", converter = ExcelDictDTOConverter.class)
	@ExcelDictProperty("lf_access_status")
	private String accessStatus;
	        
	/**
     * 头像
     */
	@ExcelProperty("头像") 
	private String avatar;
	        
	/**
     * 头像文件路径
     */
	@ExcelProperty("头像文件路径") 
	private String avatarPath;
	        
	/**
     * 所属公司
     */
	@ExcelProperty("所属公司") 
	private String company;
	        
	/**
     * 相关项目
     */
	@ExcelProperty("相关项目") 
	private String projectId;
	        
	/**
     * 考试成绩id
     */
	@ExcelProperty("考试成绩id") 
	private String examScoreId;
	        
	/**
     * 出生年月
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelProperty("出生年月") 
	private Date birthday;
	        
	/**
     * 手机号
     */
	@ExcelProperty("手机号") 
	private String phoneNumber;

}
