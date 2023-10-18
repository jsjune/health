//package com.example.health.ignore;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//@RequiredArgsConstructor
//@Slf4j
//@Profile("production")
//public class RoutingDataSource extends AbstractRoutingDataSource {
//    private final DataSourceKey dataSourceKey;
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
//        if (isReadOnly) {
//            log.info("Connection Slave");
//            return dataSourceKey.getDefaultSlaveKey();
//        } else {
//            log.info("Connection Master");
//            return dataSourceKey.getMasterKey();
//        }
//    }
//}
