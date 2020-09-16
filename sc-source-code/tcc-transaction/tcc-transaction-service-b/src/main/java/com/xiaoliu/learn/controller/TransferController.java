package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.client.ServiceAClient;
import com.xiaoliu.learn.client.ServiceCClient;
import com.xiaoliu.learn.client.ServiceDClient;
import com.xiaoliu.learn.service.AccountService;
import com.xiaoliu.learn.utils.HttpClient4;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 转账Controller
 * @author: liufb
 * @create: 2020/8/24 13:19
 **/
@Compensable(
        interfaceClass = TransferService.class,
        confirmableKey = "transferServiceConfirm",
        cancellableKey = "transferServiceCancel")
@RequestMapping("/transfer")
@RestController
public class TransferController implements TransferService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ServiceAClient serviceAClient;
    @Autowired
    private ServiceCClient serviceCClient;
    @Autowired
    private ServiceDClient serviceDClient;

    @PutMapping("/in")
    @Override
    @Transactional
    public void transferIn(String aAcctId, String bAcctId, double amount) {
        serviceAClient.transferOut(aAcctId, amount);
        accountService.tryTransferIn(bAcctId, amount);
        serviceCClient.save(aAcctId, bAcctId, amount);
        serviceDClient.send("18888888888");
//        throw new RuntimeException();
    }

    @PutMapping("/out")
    @Override
    @Transactional
    public void transferOut(String bAcctId, String aAcctId, double amount) {
        accountService.frozenAmount(bAcctId, amount);
        serviceAClient.transferIn(aAcctId, amount);
        serviceCClient.save(bAcctId, aAcctId, amount);
        serviceDClient.send("18888888888");
        // 使用RestTemplate发起请求调用，下游服务也要引入bytetcc，因为bytetcc对ClientHttpRequest做了拦截
        // restTemplate.postForEntity("http://127.0.0.1:6008/message/notcc/send", "19999999999", String.class);
        // 所以只要不使用ClientHttpRequest，下游服务就可以不引入bytetcc接入到本事务中，可以以此来整合TCC分布式事务和可靠消息最终一致性方案
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", "19999999999");
        HttpClient4.doPost("http://127.0.0.1:6008/message/notcc/send", paramMap);
    }
}
