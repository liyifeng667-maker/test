package com.jeeplus.sys.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.domain.SysConfig;
import com.jeeplus.sys.domain.vo.SysConfigVo;
import com.jeeplus.sys.service.dto.SysConfigDTO;
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
public class SysConfigWrapperImpl implements SysConfigWrapper {

    @Override
    public SysConfig toEntity(SysConfigDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysConfig sysConfig = new SysConfig();

        sysConfig.setCreateBy( dtoCreateById( dto ) );
        sysConfig.setUpdateBy( dtoUpdateById( dto ) );
        sysConfig.setId( dto.getId() );
        sysConfig.setCreateDate( dto.getCreateDate() );
        sysConfig.setUpdateDate( dto.getUpdateDate() );
        sysConfig.setDelFlag( dto.getDelFlag() );
        sysConfig.setSmtp( dto.getSmtp() );
        sysConfig.setPort( dto.getPort() );
        sysConfig.setMailName( dto.getMailName() );
        sysConfig.setMailPassword( dto.getMailPassword() );
        sysConfig.setAccessKeyId( dto.getAccessKeyId() );
        sysConfig.setAccessKeySecret( dto.getAccessKeySecret() );
        sysConfig.setSignature( dto.getSignature() );
        sysConfig.setTemplateCode( dto.getTemplateCode() );
        sysConfig.setDefaultTheme( dto.getDefaultTheme() );
        sysConfig.setDefaultLayout( dto.getDefaultLayout() );
        sysConfig.setProductName( dto.getProductName() );
        sysConfig.setLogo( dto.getLogo() );
        sysConfig.setHomeUrl( dto.getHomeUrl() );
        sysConfig.setMultiAccountLogin( dto.getMultiAccountLogin() );
        sysConfig.setSingleLoginType( dto.getSingleLoginType() );
        sysConfig.setRemarks( dto.getRemarks() );

        return sysConfig;
    }

    @Override
    public SysConfigDTO toDTO(SysConfig entity) {
        if ( entity == null ) {
            return null;
        }

        SysConfigDTO sysConfigDTO = new SysConfigDTO();

        sysConfigDTO.setCreateBy( sysConfigToUserDTO( entity ) );
        sysConfigDTO.setUpdateBy( sysConfigToUserDTO1( entity ) );
        sysConfigDTO.setId( entity.getId() );
        sysConfigDTO.setCreateDate( entity.getCreateDate() );
        sysConfigDTO.setUpdateDate( entity.getUpdateDate() );
        sysConfigDTO.setDelFlag( entity.getDelFlag() );
        sysConfigDTO.setSmtp( entity.getSmtp() );
        sysConfigDTO.setPort( entity.getPort() );
        sysConfigDTO.setMailName( entity.getMailName() );
        sysConfigDTO.setMailPassword( entity.getMailPassword() );
        sysConfigDTO.setAccessKeyId( entity.getAccessKeyId() );
        sysConfigDTO.setAccessKeySecret( entity.getAccessKeySecret() );
        sysConfigDTO.setSignature( entity.getSignature() );
        sysConfigDTO.setTemplateCode( entity.getTemplateCode() );
        sysConfigDTO.setDefaultTheme( entity.getDefaultTheme() );
        sysConfigDTO.setDefaultLayout( entity.getDefaultLayout() );
        sysConfigDTO.setProductName( entity.getProductName() );
        sysConfigDTO.setLogo( entity.getLogo() );
        sysConfigDTO.setHomeUrl( entity.getHomeUrl() );
        sysConfigDTO.setMultiAccountLogin( entity.getMultiAccountLogin() );
        sysConfigDTO.setSingleLoginType( entity.getSingleLoginType() );
        sysConfigDTO.setRemarks( entity.getRemarks() );

        return sysConfigDTO;
    }

    @Override
    public List<SysConfig> toEntity(List<SysConfigDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysConfig> list = new ArrayList<SysConfig>( dtoList.size() );
        for ( SysConfigDTO sysConfigDTO : dtoList ) {
            list.add( toEntity( sysConfigDTO ) );
        }

        return list;
    }

    @Override
    public List<SysConfigDTO> toDTO(List<SysConfig> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SysConfigDTO> list = new ArrayList<SysConfigDTO>( entityList.size() );
        for ( SysConfig sysConfig : entityList ) {
            list.add( toDTO( sysConfig ) );
        }

        return list;
    }

    @Override
    public Page<SysConfig> toEntity(Page<SysConfigDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<SysConfig> page1 = new Page<SysConfig>();

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
    public Page<SysConfigDTO> toDTO(Page<SysConfig> page) {
        if ( page == null ) {
            return null;
        }

        Page<SysConfigDTO> page1 = new Page<SysConfigDTO>();

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
    public SysConfigVo toVo(SysConfig sysConfig) {
        if ( sysConfig == null ) {
            return null;
        }

        SysConfigVo sysConfigVo = new SysConfigVo();

        sysConfigVo.setDefaultTheme( sysConfig.getDefaultTheme() );
        sysConfigVo.setDefaultLayout( sysConfig.getDefaultLayout() );
        sysConfigVo.setProductName( sysConfig.getProductName() );
        sysConfigVo.setLogo( sysConfig.getLogo() );

        return sysConfigVo;
    }

    private String dtoCreateById(SysConfigDTO sysConfigDTO) {
        if ( sysConfigDTO == null ) {
            return null;
        }
        UserDTO createBy = sysConfigDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(SysConfigDTO sysConfigDTO) {
        if ( sysConfigDTO == null ) {
            return null;
        }
        UserDTO updateBy = sysConfigDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserDTO sysConfigToUserDTO(SysConfig sysConfig) {
        if ( sysConfig == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( sysConfig.getCreateBy() );

        return userDTO;
    }

    protected UserDTO sysConfigToUserDTO1(SysConfig sysConfig) {
        if ( sysConfig == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( sysConfig.getUpdateBy() );

        return userDTO;
    }
}
