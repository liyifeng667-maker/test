package com.jeeplus.sys.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.domain.Role;
import com.jeeplus.sys.service.dto.RoleDTO;
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
public class RoleWrapperImpl implements RoleWrapper {

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setCreateBy( dtoCreateById( dto ) );
        role.setUpdateBy( dtoUpdateById( dto ) );
        role.setId( dto.getId() );
        role.setCreateDate( dto.getCreateDate() );
        role.setUpdateDate( dto.getUpdateDate() );
        role.setDelFlag( dto.getDelFlag() );
        role.setName( dto.getName() );
        role.setEnName( dto.getEnName() );
        role.setSysData( dto.getSysData() );
        role.setUseable( dto.getUseable() );
        role.setRemarks( dto.getRemarks() );

        return role;
    }

    @Override
    public RoleDTO toDTO(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setCreateBy( roleToUserDTO( entity ) );
        roleDTO.setUpdateBy( roleToUserDTO1( entity ) );
        roleDTO.setId( entity.getId() );
        roleDTO.setCreateDate( entity.getCreateDate() );
        roleDTO.setUpdateDate( entity.getUpdateDate() );
        roleDTO.setDelFlag( entity.getDelFlag() );
        roleDTO.setName( entity.getName() );
        roleDTO.setEnName( entity.getEnName() );
        roleDTO.setSysData( entity.getSysData() );
        roleDTO.setUseable( entity.getUseable() );
        roleDTO.setRemarks( entity.getRemarks() );

        return roleDTO;
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtoList.size() );
        for ( RoleDTO roleDTO : dtoList ) {
            list.add( toEntity( roleDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleDTO> toDTO(List<Role> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( entityList.size() );
        for ( Role role : entityList ) {
            list.add( toDTO( role ) );
        }

        return list;
    }

    @Override
    public Page<Role> toEntity(Page<RoleDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<Role> page1 = new Page<Role>();

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
    public Page<RoleDTO> toDTO(Page<Role> page) {
        if ( page == null ) {
            return null;
        }

        Page<RoleDTO> page1 = new Page<RoleDTO>();

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

    private String dtoCreateById(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }
        UserDTO createBy = roleDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }
        UserDTO updateBy = roleDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserDTO roleToUserDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( role.getCreateBy() );

        return userDTO;
    }

    protected UserDTO roleToUserDTO1(Role role) {
        if ( role == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( role.getUpdateBy() );

        return userDTO;
    }
}
