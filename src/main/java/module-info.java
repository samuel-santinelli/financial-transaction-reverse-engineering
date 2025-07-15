module financial.transaction.reverse.engineering {
    requires spring.context;
    requires spring.web;
    requires jakarta.persistence;
    requires static lombok;
    requires spring.data.jpa;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.annotation;
    requires org.springdoc.openapi.ui;
    requires io.swagger.v3.oas.annotations;
    opens com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.controller to spring.beans, spring.core, spring.web;
    opens com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.service to spring.beans, spring.core;
    opens com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.dto to com.fasterxml.jackson.databind, spring.core;

}