package com.lanfan.zhgd.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.service.dto.UserDTO;
import com.lanfan.zhgd.domain.PersonBank;
import com.lanfan.zhgd.service.dto.PersonBankDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-31T09:36:52+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_371 (Oracle Corporation)"
)
@Component
public class PersonBankWrapperImpl implements PersonBankWrapper {

    @Override
    public PersonBank toEntity(PersonBankDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PersonBank personBank = new PersonBank();

        personBank.setCreateBy( dtoCreateById( dto ) );
        personBank.setUpdateBy( dtoUpdateById( dto ) );
        personBank.setId( dto.getId() );
        personBank.setCreateDate( dto.getCreateDate() );
        personBank.setUpdateDate( dto.getUpdateDate() );
        personBank.setDelFlag( dto.getDelFlag() );
        personBank.setName( dto.getName() );
        personBank.setAge( dto.getAge() );
        personBank.setGender( dto.getGender() );
        personBank.setJobNo( dto.getJobNo() );
        personBank.setIdCard( dto.getIdCard() );
        personBank.setIdentityType( dto.getIdentityType() );
        personBank.setAccessStatus( dto.getAccessStatus() );
        personBank.setAvatar( dto.getAvatar() );
        personBank.setAvatarPath( dto.getAvatarPath() );
        personBank.setCompany( dto.getCompany() );
        personBank.setProjectId( dto.getProjectId() );
        personBank.setExamScoreId( dto.getExamScoreId() );
        personBank.setBirthday( dto.getBirthday() );
        personBank.setPhoneNumber( dto.getPhoneNumber() );

        return personBank;
    }

    @Override
    public PersonBankDTO toDTO(PersonBank entity) {
        if ( entity == null ) {
            return null;
        }

        PersonBankDTO personBankDTO = new PersonBankDTO();

        personBankDTO.setCreateBy( personBankToUserDTO( entity ) );
        personBankDTO.setUpdateBy( personBankToUserDTO1( entity ) );
        personBankDTO.setId( entity.getId() );
        personBankDTO.setCreateDate( entity.getCreateDate() );
        personBankDTO.setUpdateDate( entity.getUpdateDate() );
        personBankDTO.setDelFlag( entity.getDelFlag() );
        personBankDTO.setName( entity.getName() );
        personBankDTO.setAge( entity.getAge() );
        personBankDTO.setGender( entity.getGender() );
        personBankDTO.setJobNo( entity.getJobNo() );
        personBankDTO.setIdCard( entity.getIdCard() );
        personBankDTO.setIdentityType( entity.getIdentityType() );
        personBankDTO.setAccessStatus( entity.getAccessStatus() );
        personBankDTO.setAvatar( entity.getAvatar() );
        personBankDTO.setAvatarPath( entity.getAvatarPath() );
        personBankDTO.setCompany( entity.getCompany() );
        personBankDTO.setProjectId( entity.getProjectId() );
        personBankDTO.setExamScoreId( entity.getExamScoreId() );
        personBankDTO.setBirthday( entity.getBirthday() );
        personBankDTO.setPhoneNumber( entity.getPhoneNumber() );

        return personBankDTO;
    }

    @Override
    public List<PersonBank> toEntity(List<PersonBankDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PersonBank> list = new ArrayList<PersonBank>( dtoList.size() );
        for ( PersonBankDTO personBankDTO : dtoList ) {
            list.add( toEntity( personBankDTO ) );
        }

        return list;
    }

    @Override
    public List<PersonBankDTO> toDTO(List<PersonBank> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PersonBankDTO> list = new ArrayList<PersonBankDTO>( entityList.size() );
        for ( PersonBank personBank : entityList ) {
            list.add( toDTO( personBank ) );
        }

        return list;
    }

    @Override
    public Page<PersonBank> toEntity(Page<PersonBankDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<PersonBank> page1 = new Page<PersonBank>();

        page1.setPages( page.getPages() );
        page1.setRecords( toEntity( page.getRecords() ) );
        page1.setTotal( page.getTotal() );
        page1.setSize( page.getSize() );
        page1.setCurrent( page.getCurrent() );
        page1.setSearchCount( page.isSearchCount() );
        page1.setOptimizeCountSql( page.isOptimizeCountSql() );
        page1.setHitCount( page.isHitCount() );
        List<OrderItem> list1 = page.getOrders();
        if ( list1 != null ) {
            page1.setOrders( new ArrayList<OrderItem>( list1 ) );
        }
        page1.setCountId( page.getCountId() );
        page1.setMaxLimit( page.getMaxLimit() );

        return page1;
    }

    @Override
    public Page<PersonBankDTO> toDTO(Page<PersonBank> page) {
        if ( page == null ) {
            return null;
        }

        Page<PersonBankDTO> page1 = new Page<PersonBankDTO>();

        page1.setPages( page.getPages() );
        page1.setRecords( toDTO( page.getRecords() ) );
        page1.setTotal( page.getTotal() );
        page1.setSize( page.getSize() );
        page1.setCurrent( page.getCurrent() );
        page1.setSearchCount( page.isSearchCount() );
        page1.setOptimizeCountSql( page.isOptimizeCountSql() );
        page1.setHitCount( page.isHitCount() );
        List<OrderItem> list1 = page.getOrders();
        if ( list1 != null ) {
            page1.setOrders( new ArrayList<OrderItem>( list1 ) );
        }
        page1.setCountId( page.getCountId() );
        page1.setMaxLimit( page.getMaxLimit() );

        return page1;
    }

    private String dtoCreateById(PersonBankDTO personBankDTO) {
        if ( personBankDTO == null ) {
            return null;
        }
        UserDTO createBy = personBankDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(PersonBankDTO personBankDTO) {
        if ( personBankDTO == null ) {
            return null;
        }
        UserDTO updateBy = personBankDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserDTO personBankToUserDTO(PersonBank personBank) {
        if ( personBank == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( personBank.getCreateBy() );

        return userDTO;
    }

    protected UserDTO personBankToUserDTO1(PersonBank personBank) {
        if ( personBank == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( personBank.getUpdateBy() );

        return userDTO;
    }
}
