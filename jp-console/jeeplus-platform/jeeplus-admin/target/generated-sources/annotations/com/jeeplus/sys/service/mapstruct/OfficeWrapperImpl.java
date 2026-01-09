package com.jeeplus.sys.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.domain.Office;
import com.jeeplus.sys.service.dto.AreaDTO;
import com.jeeplus.sys.service.dto.OfficeDTO;
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
public class OfficeWrapperImpl implements OfficeWrapper {

    @Override
    public List<Office> toEntity(List<OfficeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Office> list = new ArrayList<Office>( dtoList.size() );
        for ( OfficeDTO officeDTO : dtoList ) {
            list.add( toEntity( officeDTO ) );
        }

        return list;
    }

    @Override
    public List<OfficeDTO> toDTO(List<Office> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OfficeDTO> list = new ArrayList<OfficeDTO>( entityList.size() );
        for ( Office office : entityList ) {
            list.add( toDTO( office ) );
        }

        return list;
    }

    @Override
    public Page<Office> toEntity(Page<OfficeDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<Office> page1 = new Page<Office>();

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
    public Page<OfficeDTO> toDTO(Page<Office> page) {
        if ( page == null ) {
            return null;
        }

        Page<OfficeDTO> page1 = new Page<OfficeDTO>();

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
    public Office copyEntity(Office entity) {
        if ( entity == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( entity.getId() );
        office.setCreateDate( entity.getCreateDate() );
        office.setCreateBy( entity.getCreateBy() );
        office.setUpdateDate( entity.getUpdateDate() );
        office.setUpdateBy( entity.getUpdateBy() );
        office.setDelFlag( entity.getDelFlag() );
        office.setParentId( entity.getParentId() );
        office.setParentIds( entity.getParentIds() );
        office.setName( entity.getName() );
        office.setSort( entity.getSort() );
        List<Office> list = entity.getChildren();
        if ( list != null ) {
            office.setChildren( new ArrayList<Office>( list ) );
        }
        office.setAreaId( entity.getAreaId() );
        office.setCode( entity.getCode() );
        office.setType( entity.getType() );
        office.setGrade( entity.getGrade() );
        office.setAddress( entity.getAddress() );
        office.setZipCode( entity.getZipCode() );
        office.setMaster( entity.getMaster() );
        office.setPhone( entity.getPhone() );
        office.setFax( entity.getFax() );
        office.setEmail( entity.getEmail() );
        office.setUseable( entity.getUseable() );
        office.setRemarks( entity.getRemarks() );

        return office;
    }

    @Override
    public OfficeDTO copyDTO(OfficeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OfficeDTO officeDTO = new OfficeDTO();

        officeDTO.setId( dto.getId() );
        officeDTO.setCreateDate( dto.getCreateDate() );
        officeDTO.setCreateBy( dto.getCreateBy() );
        officeDTO.setUpdateDate( dto.getUpdateDate() );
        officeDTO.setUpdateBy( dto.getUpdateBy() );
        officeDTO.setDelFlag( dto.getDelFlag() );
        officeDTO.setParent( copyDTO( dto.getParent() ) );
        List<OfficeDTO> list = dto.getChildren();
        if ( list != null ) {
            officeDTO.setChildren( new ArrayList<OfficeDTO>( list ) );
        }
        officeDTO.setParentIds( dto.getParentIds() );
        officeDTO.setName( dto.getName() );
        officeDTO.setSort( dto.getSort() );
        officeDTO.setAreaDTO( dto.getAreaDTO() );
        officeDTO.setCode( dto.getCode() );
        officeDTO.setType( dto.getType() );
        officeDTO.setGrade( dto.getGrade() );
        officeDTO.setAddress( dto.getAddress() );
        officeDTO.setZipCode( dto.getZipCode() );
        officeDTO.setMaster( dto.getMaster() );
        officeDTO.setPhone( dto.getPhone() );
        officeDTO.setFax( dto.getFax() );
        officeDTO.setEmail( dto.getEmail() );
        officeDTO.setUseable( dto.getUseable() );
        officeDTO.setRemarks( dto.getRemarks() );
        List<String> list1 = dto.getChildDeptList();
        if ( list1 != null ) {
            officeDTO.setChildDeptList( new ArrayList<String>( list1 ) );
        }
        officeDTO.setDisabled( dto.isDisabled() );

        return officeDTO;
    }

    @Override
    public Office toEntity(OfficeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Office office = new Office();

        office.setAreaId( dtoAreaDTOId( dto ) );
        office.setParentId( dtoParentId( dto ) );
        office.setCreateBy( dtoCreateById( dto ) );
        office.setUpdateBy( dtoUpdateById( dto ) );
        office.setId( dto.getId() );
        office.setCreateDate( dto.getCreateDate() );
        office.setUpdateDate( dto.getUpdateDate() );
        office.setDelFlag( dto.getDelFlag() );
        office.setParentIds( dto.getParentIds() );
        office.setName( dto.getName() );
        office.setSort( dto.getSort() );
        office.setChildren( toEntity( dto.getChildren() ) );
        office.setCode( dto.getCode() );
        office.setType( dto.getType() );
        office.setGrade( dto.getGrade() );
        office.setAddress( dto.getAddress() );
        office.setZipCode( dto.getZipCode() );
        office.setMaster( dto.getMaster() );
        office.setPhone( dto.getPhone() );
        office.setFax( dto.getFax() );
        office.setEmail( dto.getEmail() );
        office.setUseable( dto.getUseable() );
        office.setRemarks( dto.getRemarks() );

        return office;
    }

    @Override
    public OfficeDTO toDTO(Office entity) {
        if ( entity == null ) {
            return null;
        }

        OfficeDTO officeDTO = new OfficeDTO();

        officeDTO.setAreaDTO( officeToAreaDTO( entity ) );
        officeDTO.setParent( officeToOfficeDTO( entity ) );
        officeDTO.setCreateBy( officeToUserDTO( entity ) );
        officeDTO.setUpdateBy( officeToUserDTO1( entity ) );
        officeDTO.setId( entity.getId() );
        officeDTO.setCreateDate( entity.getCreateDate() );
        officeDTO.setUpdateDate( entity.getUpdateDate() );
        officeDTO.setDelFlag( entity.getDelFlag() );
        officeDTO.setChildren( toDTO( entity.getChildren() ) );
        officeDTO.setParentIds( entity.getParentIds() );
        officeDTO.setName( entity.getName() );
        officeDTO.setSort( entity.getSort() );
        officeDTO.setCode( entity.getCode() );
        officeDTO.setType( entity.getType() );
        officeDTO.setGrade( entity.getGrade() );
        officeDTO.setAddress( entity.getAddress() );
        officeDTO.setZipCode( entity.getZipCode() );
        officeDTO.setMaster( entity.getMaster() );
        officeDTO.setPhone( entity.getPhone() );
        officeDTO.setFax( entity.getFax() );
        officeDTO.setEmail( entity.getEmail() );
        officeDTO.setUseable( entity.getUseable() );
        officeDTO.setRemarks( entity.getRemarks() );

        return officeDTO;
    }

    private String dtoAreaDTOId(OfficeDTO officeDTO) {
        if ( officeDTO == null ) {
            return null;
        }
        AreaDTO areaDTO = officeDTO.getAreaDTO();
        if ( areaDTO == null ) {
            return null;
        }
        String id = areaDTO.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoParentId(OfficeDTO officeDTO) {
        if ( officeDTO == null ) {
            return null;
        }
        OfficeDTO parent = officeDTO.getParent();
        if ( parent == null ) {
            return null;
        }
        String id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoCreateById(OfficeDTO officeDTO) {
        if ( officeDTO == null ) {
            return null;
        }
        UserDTO createBy = officeDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(OfficeDTO officeDTO) {
        if ( officeDTO == null ) {
            return null;
        }
        UserDTO updateBy = officeDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected AreaDTO officeToAreaDTO(Office office) {
        if ( office == null ) {
            return null;
        }

        AreaDTO areaDTO = new AreaDTO();

        areaDTO.setId( office.getAreaId() );

        return areaDTO;
    }

    protected OfficeDTO officeToOfficeDTO(Office office) {
        if ( office == null ) {
            return null;
        }

        OfficeDTO officeDTO = new OfficeDTO();

        officeDTO.setId( office.getParentId() );

        return officeDTO;
    }

    protected UserDTO officeToUserDTO(Office office) {
        if ( office == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( office.getCreateBy() );

        return userDTO;
    }

    protected UserDTO officeToUserDTO1(Office office) {
        if ( office == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( office.getUpdateBy() );

        return userDTO;
    }
}
