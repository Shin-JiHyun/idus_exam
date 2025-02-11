package com.example.idus_exam.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;

public class DynamicRoutingDataSource  extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //스프링에서 제공해주는 트랜젝션기능을 이용해서 readonly이면 SLAVE, 아니면 MASTER
        String dataSourceName = isCurrentTransactionReadOnly() ? "SLAVE":"MASTER";
        System.out.println("dataSourceName: " + dataSourceName);
        return dataSourceName;
    }
}
