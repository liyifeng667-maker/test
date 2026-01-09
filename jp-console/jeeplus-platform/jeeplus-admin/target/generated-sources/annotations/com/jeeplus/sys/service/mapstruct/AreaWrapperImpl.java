package com.jeeplus.sys.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.domain.Area;
import com.jeeplus.sys.service.dto.AreaDTO;
import com.jeeplus.sys.service.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-31T09:36:41+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_371 (Oracle Corporation)"
)
@Component
public class AreaWrapperImpl implements AreaWrapper {

    @Override
    public List<Area> toEntity(List<AreaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Area> list = new ArrayList<Area>( dtoList.size() );
        for ( AreaDTO areaDTO : dtoList ) {
            list.add( toEntity( areaDTO ) );
        }

        return list;
    }

    @Override
    public List<AreaDTO> toDTO(List<Area> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AreaDTO> list = new ArrayList<AreaDTO>( entityList.size() );
        for ( Area area : entityList ) {
            list.add( toDTO( area ) );
        }

        return list;
    }

    @Override
    public Page<Area> toEntity(Page<AreaDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<Area> page1 = new Page<Area>();

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
    public Page<AreaDTO> toDTO(Page<Area> page) {
        if ( page == null ) {
            return null;
        }

        Page<AreaDTO> page1 = new Page<AreaDTO>();

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

    @Override
    public Area toEntity(AreaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Area area = new Area();

        area.setParentId( dtoParentId( dto ) );
        area.setCreateBy( dtoCreateById( dto ) );
        area.setUpdateBy( dtoUpdateById( dto ) );
        area.setId( dto.getId() );
        area.setCreateDate( dto.getCreateDate() );
        area.setUpdateDate( dto.getUpdateDate() );
        area.setDelFlag( dto.getDelFlag() );
        area.setParentIds( dto.getParentIds() );
        area.setName( dto.getName() );
        area.setSort( dto.getSort() );
        area.setChildren( toEntity( dto.getChildren() ) );
        area.setCode( dto.getCode() );
        area.setType( dto.getType() );
        area.setRemarks( dto.getRemarks() );

        return area;
    }

    @Override
    public AreaDTO toDTO(Area entity) {
        if ( entity == null ) {
            return null;
        }

        AreaDTO areaDTO = new AreaDTO();

        areaDTO.setParent( areaToAreaDTO( entity ) );
        areaDTO.setCreateBy( areaToUserDTO( entity ) );
        areaDTO.setUpdateBy( areaToUserDTO1( entity ) );
        areaDTO.setId( entity.getId() );
        areaDTO.setCreateDate( entity.getCreateDate() );
        areaDTO.setUpdateDate( entity.getUpdateDate() );
        areaDTO.setDelFlag( entity.getDelFlag() );
        areaDTO.setChildren( toDTO( entity.getChildren() ) );
        areaDTO.setParentIds( entity.getParentIds() );
        areaDTO.setName( entity.getName() );
        areaDTO.setSort( entity.getSort() );
        areaDTO.setCode( entity.getCode() );
        areaDTO.setType( entity.getType() );
        areaDTO.setRemarks( entity.getRemarks() );

        return areaDTO;
    }

    @Override
    public Area copyEntity(Area entity) {
        if ( entity == null ) {
            return null;
        }

        Area area = new Area();

        area.setId( entity.getId() );
        area.setCreateDate( entity.getCreateDate() );
        area.setCreateBy( entity.getCreateBy() );
        area.setUpdateDate( entity.getUpdateDate() );
        area.setUpdateBy( entity.getUpdateBy() );
        area.setDelFlag( entity.getDelFlag() );
        area.setParentId( entity.getParentId() );
        area.setParentIds( entity.getParentIds() );
        area.setName( entity.getName() );
        area.setSort( entity.getSort() );
        List<Area> list = entity.getChildren();
        if ( list != null ) {
            area.setChildren( new ArrayList<Area>( list ) );
        }
        area.setCode( entity.getCode() );
        area.setType( entity.getType() );
        area.setRemarks( entity.getRemarks() );

        return area;
    }

    @Override
    public AreaDTO copyDTO(AreaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AreaDTO areaDTO = new AreaDTO();

        areaDTO.setId( dto.getId() );
        areaDTO.setCreateDate( dto.getCreateDate() );
        areaDTO.setCreateBy( dto.getCreateBy() );
        areaDTO.setUpdateDate( dto.getUpdateDate() );
        areaDTO.setUpdateBy( dto.getUpdateBy() );
        areaDTO.setDelFlag( dto.getDelFlag() );
        areaDTO.setParent( copyDTO( dto.getParent() ) );
        List<AreaDTO> list = dto.getChildren();
        if ( list != null ) {
            areaDTO.setChildren( new ArrayList<AreaDTO>( list ) );
        }
        areaDTO.setParentIds( dto.getParentIds() );
        areaDTO.setName( dto.getName() );
        areaDTO.setSort( dto.getSort() );
        areaDTO.setCode( dto.getCode() );
        areaDTO.setType( dto.getType() );
        areaDTO.setRemarks( dto.getRemarks() );

        return areaDTO;
    }

    private String dtoParentId(AreaDTO areaDTO) {
        if ( areaDTO == null ) {
            return null;
        }
        AreaDTO parent = areaDTO.getParent();
        if ( parent == null ) {
            return null;
        }
        String id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoCreateById(AreaDTO areaDTO) {
        if ( areaDTO == null ) {
            return null;
        }
        UserDTO createBy = areaDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(AreaDTO areaDTO) {
        if ( areaDTO == null ) {
            return null;
        }
        UserDTO updateBy = areaDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected AreaDTO areaToAreaDTO(Area area) {
        if ( area == null ) {
            return null;
        }

        AreaDTO areaDTO = new AreaDTO();

        areaDTO.setId( area.getParentId() );

        return areaDTO;
    }

    protected UserDTO areaToUserDTO(Area area) {
        if ( area == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( area.getCreateBy() );

        return userDTO;
    }

    protected UserDTO areaToUserDTO1(Area area) {
        if ( area == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( area.getUpdateBy() );

        return userDTO;
    }
}
