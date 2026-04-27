package com.myorganisation.vibehub.startup;

import com.myorganisation.vibehub.model.NumberOfUser;
import com.myorganisation.vibehub.repository.NumberOfUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserCounterInitializer {

    @Autowired
    private NumberOfUserRepository numberOfUserRepository;

    @Bean
    public CommandLineRunner userCounterInitialized() {
        return args -> {
            NumberOfUser numberOfUser = numberOfUserRepository.findById(1L).orElse(null);

            if(numberOfUser == null) {
                numberOfUser = new NumberOfUser();
                numberOfUser.setId(1L);
                numberOfUser.setCounter(0L);
                numberOfUserRepository.save(numberOfUser);
            }
        };
    }
}
