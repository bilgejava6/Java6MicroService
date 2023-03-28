package com.muhammet.service;

import com.muhammet.dto.request.UserProfileSaveRequestDto;
import com.muhammet.manager.IElasticServisManager;
import com.muhammet.mapper.IUserProfileMapper;
import com.muhammet.rabbitmq.model.SaveAuthModel;
import com.muhammet.repository.IUserProfileRepository;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    private final IElasticServisManager elasticServisManager;
    public UserProfileService(IUserProfileRepository repository,
                              IElasticServisManager elasticServisManager){
        super(repository);
        this.repository=repository;
        this.elasticServisManager=elasticServisManager;
    }

    public Boolean saveDto(UserProfileSaveRequestDto dto) {
        save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
        return true;
    }

    public void saveRabbit(SaveAuthModel model){
        UserProfile profile = IUserProfileMapper.INSTANCE.toUserProfile(model);
        save(profile);
       // elasticServisManager.addUser(profile);
    }

    /**
     * Bu uzun süren bir işlemi simüle etmek için Thred Kullanılarak
     * bekletilmiş bir method tur.
     * - Bu method, Kağıt kelimesi için her seferinde aynı sonucu mu üretir?
     * -> muhammet  => MUHAMMET
     * @param name
     * @return
     */

    @Cacheable(value = "getUpperName")
    public String getUpper(String name){
        try{
            Thread.sleep(3000L);
        }catch (Exception e){

        }
        return name.toUpperCase();
    }

    @CacheEvict(value = "getUpperName", allEntries = true)
    public void clearCache(){
        System.out.println("Tüm kayıtları temizledik");
    }
}
