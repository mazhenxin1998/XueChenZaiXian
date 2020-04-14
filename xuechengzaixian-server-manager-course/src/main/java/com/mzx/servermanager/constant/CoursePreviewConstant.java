package com.mzx.servermanager.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author ZhenXinMa
 * @date 2020/4/13 15:25
 */
public class CoursePreviewConstant {

    @Value(value = "${xuechengzaixian.course.publish.dataUrlPre}")
    public static String PUBLISH_DATA_URL_PRE;

    @Value(value = "${xuechengzaixian.course.publish.siteId}")
    public static String PUBLISH_SITE_ID;

    @Value(value = "${xuechengzaixian.course.publish.templateId}")
    public static String PUBLISH_TEMPLATE_ID;

    @Value(value = "${xuechengzaixian.course.publish.previewUrl}")
    public static String PUBLISH_PREVIEW_URL;

    @Value(value = "${xuechengzaixian.course.publish.pageWebPath}")
    public static String PUBLISH_PAGE_WEB_PATH;

    @Value(value = "${xuechengzaixian.course.publish.pagePhysicalPath}")
    public  static String PUBLISH_PAGE_PHYSICAL_PATH;

}
