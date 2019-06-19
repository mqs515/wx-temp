package com.wx.temp.demo.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.temp.demo.api.WechatApi;
import com.wx.temp.demo.config.WechatConf;
import com.wx.temp.demo.domian.WechatTemplate;
import com.wx.temp.demo.domian.WechatTemplateItem;
import com.wx.temp.demo.util.MapUtil;
import com.wx.temp.demo.util.WebUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author Mqs
 * @Date 2019/6/19 23:50
 * @Desc
 */
@RequestMapping("/wx")
@RestController
public class WxTempController {

    @GetMapping("/template_send")
    public Map<String, Object> templateSend() {
        String accessToken = WechatApi.getAccessToken();
        JSONObject body = JSON.parseObject(WebUtil.getBody());

        // 填充模板数据
        WechatTemplate wechatTemplate = WechatTemplate.builder()
                .touser("openId")
                .template_id(WechatConf.templateId)
//                .page("/index.html")
                .form_id("formId")
                /**
                 * 模板内容填充：随机字符
                 * 购买地点 {{keyword1.DATA}}
                 * 购买时间 {{keyword2.DATA}}
                 * 物品名称 {{keyword3.DATA}}
                 * 交易单号 {{keyword4.DATA}}
                 * -> {"keyword1": {"value":"xxx"}, "keyword2": ...}
                 */
                .data(MapUtil.newHashMap(
                        "keyword1", new WechatTemplateItem(RandomUtil.randomString(10)),
                        "keyword2", new WechatTemplateItem(DateUtil.now()),
                        "keyword3", new WechatTemplateItem(RandomUtil.randomString(10)),
                        "keyword4", new WechatTemplateItem(RandomUtil.randomNumbers(10))
                ))
                .emphasis_keyword("")
                .build();

        WechatApi.templateSend(accessToken, wechatTemplate);
        return MapUtil.newHashMap("status", 0);
    }
}
