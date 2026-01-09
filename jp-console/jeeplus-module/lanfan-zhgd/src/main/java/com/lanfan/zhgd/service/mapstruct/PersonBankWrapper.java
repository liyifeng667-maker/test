/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.lanfan.zhgd.service.mapstruct;


import com.jeeplus.core.mapstruct.EntityWrapper;
import com.lanfan.zhgd.service.dto.PersonBankDTO;
import com.lanfan.zhgd.domain.PersonBank;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 *  PersonBankWrapper
 * @author shy
 * @version 2025-12-23
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {} )
public interface PersonBankWrapper extends EntityWrapper<PersonBankDTO, PersonBank> {

    PersonBankWrapper INSTANCE = Mappers.getMapper(PersonBankWrapper.class);
}

