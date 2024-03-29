package com.wimoor.amazon.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.wimoor.amazon.notifications.service.impl.AwsSQSService;
import com.wimoor.amazon.orders.service.IAmzOrderItemService;
import com.wimoor.amazon.product.service.IProductCaptureCatalogItemService;
import com.wimoor.amazon.product.service.IProductCaptureListingsItemService;
import com.wimoor.amazon.product.service.IProductCaptureProductPriceService;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationReadyEventListener.class);
    @Autowired
    AwsSQSService awsSQSService;
    @Autowired
    IAmzOrderItemService iAmzOrderItemService;
    @Autowired
    IProductCaptureListingsItemService  iProductCaptureListingsItemService;
    @Autowired
    IProductCaptureCatalogItemService iProductCaptureCatalogItemService;
    @Autowired
    IProductCaptureProductPriceService iProductCaptureProductPriceService;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent contextReadyEvent) {
        logger.info("程序已启动");
        if(!"prod".equals(awsSQSService.getProfiles())) {
    			return;
    		}
    	awsSQSService.runTask();
		//iAmzOrderItemService.runTask();
		//iProductCaptureListingsItemService.runTask();
		//iProductCaptureCatalogItemService.runTask();
		//iProductCaptureProductPriceService.runTask();
    	
    }
}

