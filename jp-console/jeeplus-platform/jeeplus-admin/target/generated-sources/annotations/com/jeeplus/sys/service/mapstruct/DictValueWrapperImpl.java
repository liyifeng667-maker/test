package com.jeeplus.sys.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.domain.DictValue;
import com.jeeplus.sys.service.dto.DictTypeDTO;
import com.jeeplus.sys.service.dto.DictValueDTO;
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
public class DictValueWrapperImpl implements DictValueWrapper {

    @Override
    public DictValue toEntity(DictValueDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DictValue dictValue = new DictValue();

        dictValue.setCreateBy( dtoCreateById( dto ) );
        dictValue.setUpdateBy( dtoUpdateById( dto ) );
        dictValue.setId( dto.getId() );
        dictValue.setCreateDate( dto.getCreateDate() );
        dictValue.setUpdateDate( dto.getUpdateDate() );
        dictValue.setDelFlag( dto.getDelFlag() );
        dictValue.setLabel( dto.getLabel() );
        dictValue.setValue( dto.getValue() );
        dictValue.setSort( dto.getSort() );

        return dictValue;
    }

    @Override
    public DictValueDTO toDTO(DictValue entity) {
        if ( entity == null ) {
            return null;
        }

        DictTypeDTO dictTypeDTO = null;

        DictValueDTO dictValueDTO = new DictValueDTO( dictTypeDTO );

        dictValueDTO.setCreateBy( dictValueToUserDTO( entity ) );
        dictValueDTO.setUpdateBy( dictValueToUserDTO1( entity ) );
        dictValueDTO.setId( entity.getId() );
        dictValueDTO.setCreateDate( entity.getCreateDate() );
        dictValueDTO.setUpdateDate( entity.getUpdateDate() );
        dictValueDTO.setDelFlag( entity.getDelFlag() );
        dictValueDTO.setLabel( entity.getLabel() );
        dictValueDTO.setValue( entity.getValue() );
        dictValueDTO.setSort( entity.getSort() );

        return dictValueDTO;
    }

    @Override
    public List<DictValue> toEntity(List<DictValueDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DictValue> list = new ArrayList<DictValue>( dtoList.size() );
        for ( DictValueDTO dictValueDTO : dtoList ) {
            list.add( toEntity( dictValueDTO ) );
        }

        return list;
    }

    @Override
    public List<DictValueDTO> toDTO(List<DictValue> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DictValueDTO> list = new ArrayList<DictValueDTO>( entityList.size() );
        for ( DictValue dictValue : entityList ) {
            list.add( toDTO( dictValue ) );
        }

        return list;
    }

    @Override
    public Page<DictValue> toEntity(Page<DictValueDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<DictValue> page1 = new Page<DictValue>();

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
    public Page<DictValueDTO> toDTO(Page<DictValue> page) {
        if ( page == null ) {
            return null;
        }

        Page<DictValueDTO> page1 = new Page<DictValueDTO>();

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

    private String dtoCreateById(DictValueDTO dictValueDTO) {
        if ( dictValueDTO == null ) {
            return null;
        }
        UserDTO createBy = dictValueDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(DictValueDTO dictValueDTO) {
        if ( dictValueDTO == null ) {
            return null;
        }
        UserDTO updateBy = dictValueDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserDTO dictValueToUserDTO(DictValue dictValue) {
        if ( dictValue == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( dictValue.getCreateBy() );

        return userDTO;
    }

    protected UserDTO dictValueToUserDTO1(DictValue dictValue) {
        if ( dictValue == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( dictValue.getUpdateBy() );

        return userDTO;
    }
}
