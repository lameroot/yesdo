package ru.yesdo.service;

import org.springframework.stereotype.Service;
import ru.yesdo.graph.service.MerchantGraphService;
import ru.yesdo.model.Merchant;
import ru.yesdo.model.data.MerchantData;
import ru.yesdo.repository.MerchantRepository;

import javax.annotation.Resource;

/**
 * Created by lameroot on 18.02.15.
 */
@Service
public class MerchantService {

    @Resource
    private MerchantRepository merchantRepository;
    @Resource
    private MerchantGraphService merchantGraphService;

    public Merchant create(MerchantData merchantData) {
        Merchant merchant = new Merchant();

        return merchant;
    }
}
