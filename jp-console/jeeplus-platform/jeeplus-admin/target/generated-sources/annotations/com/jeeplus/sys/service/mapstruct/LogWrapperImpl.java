package com.jeeplus.sys.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.sys.domain.Log;
import com.jeeplus.sys.service.dto.LogDTO;
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
public class LogWrapperImpl implements LogWrapper {

    @Override
    public Log toEntity(LogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Log log = new Log();

        log.setCreateBy( dtoCreateById( dto ) );
        log.setUpdateBy( dtoUpdateById( dto ) );
        log.setId( dto.getId() );
        log.setCreateDate( dto.getCreateDate() );
        log.setDelFlag( dto.getDelFlag() );
        log.setType( dto.getType() );
        log.setTitle( dto.getTitle() );
        log.setRemoteAddr( dto.getRemoteAddr() );
        log.setRequestUri( dto.getRequestUri() );
        log.setRequestType( dto.getRequestType() );
        log.setMethod( dto.getMethod() );
        log.setParams( dto.getParams() );
        log.setResult( dto.getResult() );
        log.setUserAgent( dto.getUserAgent() );
        log.setException( dto.getException() );
        log.setRecordTime( dto.getRecordTime() );
        log.setUpdateDate( dto.getUpdateDate() );
        log.setRemarks( dto.getRemarks() );

        return log;
    }

    @Override
    public LogDTO toDTO(Log entity) {
        if ( entity == null ) {
            return null;
        }

        LogDTO logDTO = new LogDTO();

        logDTO.setCreateBy( logToUserDTO( entity ) );
        logDTO.setUpdateBy( logToUserDTO1( entity ) );
        logDTO.setId( entity.getId() );
        logDTO.setCreateDate( entity.getCreateDate() );
        logDTO.setUpdateDate( entity.getUpdateDate() );
        logDTO.setDelFlag( entity.getDelFlag() );
        logDTO.setType( entity.getType() );
        logDTO.setTitle( entity.getTitle() );
        logDTO.setRemoteAddr( entity.getRemoteAddr() );
        logDTO.setRequestUri( entity.getRequestUri() );
        logDTO.setRequestType( entity.getRequestType() );
        logDTO.setMethod( entity.getMethod() );
        logDTO.setParams( entity.getParams() );
        logDTO.setResult( entity.getResult() );
        logDTO.setUserAgent( entity.getUserAgent() );
        logDTO.setException( entity.getException() );
        logDTO.setRecordTime( entity.getRecordTime() );
        logDTO.setRemarks( entity.getRemarks() );

        return logDTO;
    }

    @Override
    public List<Log> toEntity(List<LogDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Log> list = new ArrayList<Log>( dtoList.size() );
        for ( LogDTO logDTO : dtoList ) {
            list.add( toEntity( logDTO ) );
        }

        return list;
    }

    @Override
    public List<LogDTO> toDTO(List<Log> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LogDTO> list = new ArrayList<LogDTO>( entityList.size() );
        for ( Log log : entityList ) {
            list.add( toDTO( log ) );
        }

        return list;
    }

    @Override
    public Page<Log> toEntity(Page<LogDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<Log> page1 = new Page<Log>();

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
    public Page<LogDTO> toDTO(Page<Log> page) {
        if ( page == null ) {
            return null;
        }

        Page<LogDTO> page1 = new Page<LogDTO>();

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

    private String dtoCreateById(LogDTO logDTO) {
        if ( logDTO == null ) {
            return null;
        }
        UserDTO createBy = logDTO.getCreateBy();
        if ( createBy == null ) {
            return null;
        }
        String id = createBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dtoUpdateById(LogDTO logDTO) {
        if ( logDTO == null ) {
            return null;
        }
        UserDTO updateBy = logDTO.getUpdateBy();
        if ( updateBy == null ) {
            return null;
        }
        String id = updateBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserDTO logToUserDTO(Log log) {
        if ( log == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( log.getCreateBy() );

        return userDTO;
    }

    protected UserDTO logToUserDTO1(Log log) {
        if ( log == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( log.getUpdateBy() );

        return userDTO;
    }
}
