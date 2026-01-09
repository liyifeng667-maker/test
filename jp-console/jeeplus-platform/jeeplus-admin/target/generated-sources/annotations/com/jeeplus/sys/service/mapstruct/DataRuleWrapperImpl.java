package com.jeeplus.sys.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.domain.DataRule;
import com.jeeplus.sys.service.dto.DataRuleDTO;
import com.jeeplus.sys.service.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-31T09:36:42+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_371 (Oracle Corporation)"
)
@Component
public class DataRuleWrapperImpl implements DataRuleWrapper {

    @Override
    public DataRule toEntity(DataRuleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DataRule dataRule = new DataRule();

        dataRule.setCreateBy( dtoCreateById( dto ) );
        dataRule.setUpdateBy( dtoUpdateById( dto ) );
        dataRule.setId( dto.getId() );
        dataRule.setCreateDate( dto.getCreateDate() );
        dataRule.setUpdateDate( dto.getUpdateDate() );
        dataRule.setDelFlag( dto.getDelFlag() );
        dataRule.setMenuId( dto.getMenuId() );
        dataRule.setName( dto.getName() );
        dataRule.setClassName( dto.getClassName() );
        dataRule.setField( dto.getField() );
        dataRule.setExpress( dto.getExpress() );
        dataRule.setValue( dto.getValue() );
        dataRule.setSqlSegment( dto.getSqlSegment() );
        dataRule.setRemarks( dto.getRemarks() );

        return dataRule;
    }

    @Override
    public DataRuleDTO toDTO(DataRule entity) {
        if ( entity == null ) {
            return null;
        }

        DataRuleDTO dataRuleDTO = new DataRuleDTO();

        dataRuleDTO.setCreateBy( dataRuleToUserDTO( entity ) );
        dataRuleDTO.setUpdateBy( dataRuleToUserDTO1( entity ) );
        dataRuleDTO.setId( entity.getId() );
        dataRuleDTO.setCreateDate( entity.getCreateDate() );
        dataRuleDTO.setUpdateDate( entity.getUpdateDate() );
        dataRuleDTO.setDelFlag( entity.getDelFlag() );
        dataRuleDTO.setMenuId( entity.getMenuId() );
        dataRuleDTO.setName( entity.getName() );
        dataRuleDTO.setClassName( entity.getClassName() );
        dataRuleDTO.setField( entity.getField() );
        dataRuleDTO.setExpress( entity.getExpress() );
        dataRuleDTO.setValue( entity.getValue() );
        dataRuleDTO.setSqlSegment( entity.getSqlSegment() );
        dataRuleDTO.setRemarks( entity.getRemarks() );

        return dataRuleDTO;
    }

    @Override
    public List<DataRule> toEntity(List<DataRuleDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DataRule> list = new ArrayList<DataRule>( dtoList.size() );
        for ( DataRuleDTO dataRuleDTO : dtoList ) {
            list.add( toEntity( dataRuleDTO ) );
        }

        return list;
    }

    @Override
    public List<DataRuleDTO> toDTO(List<DataRule> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DataRuleDTO> list = new ArrayList<DataRuleDTO>( entityList.size() );
        for ( DataRule dataRule : entityList ) {
            list.add( toDTO( dataRule ) );
        }

        return list;
    }

    @Override
    public Page<DataRule> toEntity(Page<DataRuleDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<DataRule> page1 = new Page<DataRule>();

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
    public Page<DataRuleDTO> toDTO(Page<DataRule> page) {
        if ( page == null ) {
            return null;
        }

        Page<DataRuleDTO> page1 = new Page<DataRuleDTO>();

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

    private String dtoCreateById(DataRuleDTO dataRuleDTO) {
        if ( dataRuleDTO == null ) {
            return null;
        }
        UserDTO createBy = dataRuleDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(DataRuleDTO dataRuleDTO) {
        if ( dataRuleDTO == null ) {
            return null;
        }
        UserDTO updateBy = dataRuleDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserDTO dataRuleToUserDTO(DataRule dataRule) {
        if ( dataRule == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( dataRule.getCreateBy() );

        return userDTO;
    }

    protected UserDTO dataRuleToUserDTO1(DataRule dataRule) {
        if ( dataRule == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( dataRule.getUpdateBy() );

        return userDTO;
    }
}
