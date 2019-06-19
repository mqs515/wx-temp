package com.wx.temp.demo.domian;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class WechatTemplate {

    /**
     * 用户OpenId
     */
    private String touser;

    /**
     * 模板id
     */
    private String template_id;

    /**
     * 跳转地址
     */
    private String page;

    /**
     * 表单提交场景下为formid，支付场景下为prepay_id
     */
    private String form_id;

    /**
     * 模板消息内容
     */
    private Map<String, WechatTemplateItem> data;

    /**
     * 需要放大的关键字，如：keyword1.DATA
     */
    private String emphasis_keyword;
}
