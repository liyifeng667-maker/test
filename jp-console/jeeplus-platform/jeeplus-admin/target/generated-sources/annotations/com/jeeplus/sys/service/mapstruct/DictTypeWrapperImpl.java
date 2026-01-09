package com.jeeplus.sys.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.domain.DictType;
import com.jeeplus.sys.service.dto.DictTypeDTO;
import com.jeeplus.sys.service.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-31T09:36:43+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_371 (Oracle Corporation)"
)
@Component
public class DictTypeWrapperImpl implements DictTypeWrapper {

    @Override
    public DictType toEntity(DictTypeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DictType dictType = new DictType();

        dictType.setCreateBy( dtoCreateById( dto ) );
        dictType.setUpdateBy( dtoUpdateById( dto ) );
        dictType.setId( dto.getId() );
        dictType.setCreateDate( dto.getCreateDate() );
        dictType.setUpdateDate( dto.getUpdateDate() );
        dictType.setDelFlag( dto.getDelFlag() );
        dictType.setType( dto.getType() );
        dictType.setRemarks( dto.getRemarks() );

        return dictType;
    }

    @Override
    public DictTypeDTO toDTO(DictType entity) {
        if ( entity == null ) {
            return null;
        }

        DictTypeDTO dictTypeDTO = new DictTypeDTO();

        dictTypeDTO.setCreateBy( dictTypeToUserDTO( entity ) );
        dictTypeDTO.setUpdateBy( dictTypeToUserDTO1( entity ) );
        dictTypeDTO.setId( entity.getId() );
        dictTypeDTO.setCreateDate( entity.getCreateDate() );
        dictTypeDTO.setUpdateDate( entity.getUpdateDate() );
        dictTypeDTO.setDelFlag( entity.getDelFlag() );
        dictTypeDTO.setType( entity.getType() );
        dictTypeDTO.setRemarks( entity.getRemarks() );

        return dictTypeDTO;
    }

    @Override
    public List<DictType> toEntity(List<DictTypeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DictType> list = new ArrayList<DictType>( dtoList.size() );
        for ( DictTypeDTO dictTypeDTO : dtoList ) {
            list.add( toEntity( dictTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<DictTypeDTO> toDTO(List<DictType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DictTypeDTO> list = new ArrayList<DictTypeDTO>( entityList.size() );
        for ( DictType dictType : entityList ) {
            list.add( toDTO( dictType ) );
        }

        return list;
    }

    @Override
    public Page<DictType> toEntity(Page<DictTypeDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<DictType> page1 = new Page<DictType>();

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
    public Page<DictTypeDTO> toDTO(Page<DictType> page) {
        if ( page == null ) {
            return null;
        }

        Page<DictTypeDTO> page1 = new Page<DictTypeDTO>();

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

    private String dtoCreateById(DictTypeDTO dictTypeDTO) {
        if ( dictTypeDTO == null ) {
            return null;
        }
        UserDTO createBy = dictTypeDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(DictTypeDTO dictTypeDTO) {
        if ( dictTypeDTO == null ) {
            return null;
        }
        UserDTO updateBy = dictTypeDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserDTO dictTypeToUserDTO(DictType dictType) {
        if ( dictType == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( dictType.getCreateBy() );

        return userDTO;
    }

    protected UserDTO dictTypeToUserDTO1(DictType dictType) {
        if ( dictType == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( dictType.getUpdateBy() );

        return userDTO;
    }
}
