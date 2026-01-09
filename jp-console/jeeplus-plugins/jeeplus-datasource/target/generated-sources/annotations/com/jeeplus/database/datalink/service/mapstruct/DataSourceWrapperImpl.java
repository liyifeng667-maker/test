package com.jeeplus.database.datalink.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeeplus.database.datalink.domain.DataSource;
import com.jeeplus.database.datalink.service.dto.DataSourceDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-31T09:36:56+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_371 (Oracle Corporation)"
)
@Component
public class DataSourceWrapperImpl implements DataSourceWrapper {

    @Override
    public List<DataSource> toEntity(List<DataSourceDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DataSource> list = new ArrayList<DataSource>( dtoList.size() );
        for ( DataSourceDTO dataSourceDTO : dtoList ) {
            list.add( toEntity( dataSourceDTO ) );
        }

        return list;
    }

    @Override
    public List<DataSourceDTO> toDTO(List<DataSource> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DataSourceDTO> list = new ArrayList<DataSourceDTO>( entityList.size() );
        for ( DataSource dataSource : entityList ) {
            list.add( toDTO( dataSource ) );
        }

        return list;
    }

    @Override
    public Page<DataSource> toEntity(Page<DataSourceDTO> page) {
        if ( page == null ) {
            return null;
        }

        Page<DataSource> page1 = new Page<DataSource>();

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
    public Page<DataSourceDTO> toDTO(Page<DataSource> page) {
        if ( page == null ) {
            return null;
        }

        Page<DataSourceDTO> page1 = new Page<DataSourceDTO>();

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
    public DataSource toEntity(DataSourceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DataSource dataSource = new DataSource();

        dataSource.setEnName( dto.getPollName() );
        dataSource.setDriver( dto.getDriverClassName() );
        dataSource.setUrl( dto.getUrl() );
        dataSource.setUsername( dto.getUsername() );
        dataSource.setPassword( dto.getPassword() );

        return dataSource;
    }

    @Override
    public DataSourceDTO toDTO(DataSource entity) {
        if ( entity == null ) {
            return null;
        }

        DataSourceDTO dataSourceDTO = new DataSourceDTO();

        dataSourceDTO.setPollName( entity.getEnName() );
        dataSourceDTO.setDriverClassName( entity.getDriver() );
        dataSourceDTO.setUrl( entity.getUrl() );
        dataSourceDTO.setUsername( entity.getUsername() );
        dataSourceDTO.setPassword( entity.getPassword() );

        return dataSourceDTO;
    }
}
