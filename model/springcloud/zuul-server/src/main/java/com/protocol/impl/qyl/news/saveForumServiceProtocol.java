package com.protocol.impl.qyl.news;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: HYF
 * @Date: 2018/7/25
 * @Time:17:59
 * @Description:
 */
@Service("saveForumServiceProtocol")
public class saveForumServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(saveForumServiceProtocol.class);
    private final static String interfaceDesc = "发布论坛";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
                return outMap;
            String channelId = String.valueOf(inputMap.get("channelId"));
            if (VerifyUtils.isEmpty(code, outMap, channelId,"channelId",logger,interfaceDesc))
                return outMap;
            String title = String.valueOf(inputMap.get("title"));
            if (VerifyUtils.isEmpty(code, outMap, title,"title",logger,interfaceDesc))
                return outMap;
            String content = String.valueOf(inputMap.get("content"));
            if (VerifyUtils.isEmpty(code, outMap, content,"content",logger,interfaceDesc))
                return outMap;
            String token = String.valueOf(inputMap.get("token"));
            if (VerifyUtils.isEmpty(code, outMap, token,"token",logger,interfaceDesc))
                return outMap;
//            校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,null,logger,interfaceDesc))
                return outMap;
            //签名正确，放过请求
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}",interfaceDesc,outMap.get("msg"));
        }
        return outMap;
    }
}