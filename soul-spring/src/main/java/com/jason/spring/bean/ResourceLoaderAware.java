package com.jason.spring.bean;

import org.springframework.core.io.ResourceLoader;

public interface ResourceLoaderAware {
	void setResourceLoader(ResourceLoader resourceLoader);
}