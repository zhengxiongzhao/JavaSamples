package com.jason.spring.beans;

import org.springframework.core.io.ResourceLoader;

public class ResourceBean implements ResourceLoaderAware {  
    private ResourceLoader resourceLoader;  
    @Override  
    public void setResourceLoader(ResourceLoader resourceLoader) {  
        this.resourceLoader = resourceLoader;  
    }  
    public ResourceLoader getResourceLoader() {  
        return resourceLoader;  
    }  
}  
   