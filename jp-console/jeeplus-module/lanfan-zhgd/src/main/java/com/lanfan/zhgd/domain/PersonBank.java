/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.lanfan.zhgd.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jeeplus.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 人员库Entity
 * @author shy
 * @version 2025-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lf_person_bank")
public class PersonBank extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
     * 人员名称
     */
	private String name;
	/**
     * 年龄
     */
	private String age;
	/**
     * 性别
     */
	private String gender;
	/**
     * 工号
     */
	private String jobNo;
	/**
     * 身份证
     */
	private String idCard;
	/**
     * 身份
     */
	private String identityType;
	/**
     * 准入状态
     */
	private String accessStatus;
	/**
     * 头像
     */
	private String avatar;
	/**
     * 头像文件路径
     */
	private String avatarPath;
	/**
     * 所属公司
     */
	private String company;
	/**
     * 相关项目
     */
	private String projectId;
	/**
     * 考试成绩id
     */
	private String examScoreId;
	/**
     * 出生年月
     */
	private Date birthday;
	/**
     * 手机号
     */
	private String phoneNumber;

}
