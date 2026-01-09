/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.lanfan.zhgd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanfan.zhgd.domain.PersonBank;
import com.lanfan.zhgd.mapper.PersonBankMapper;

/**
 * 人员库Service
 * @author shy
 * @version 2025-12-23
 */
@Service
@Transactional
public class PersonBankService extends ServiceImpl<PersonBankMapper, PersonBank> {

}
