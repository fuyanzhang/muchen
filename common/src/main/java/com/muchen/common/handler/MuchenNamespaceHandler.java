package com.muchen.common.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MuchenNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("export", new ExportServiceBeanDefinitionParser());
        registerBeanDefinitionParser("inport", new ImportportServiceBeanDefinitionParser());
    }
}
