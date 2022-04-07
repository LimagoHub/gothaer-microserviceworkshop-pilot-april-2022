package de.gothaer.smartbank24kreditvertragstore.adapter.eventlistener;



import de.gothaer.smartbank24kreditvertragstore.adapter.events.KreditantragEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventListener {

    @Bean
    public Consumer<KreditantragEvent> kreditvertragEmpfohlen(){
        return System.out::println;
    }


    @Bean
    public Consumer<KreditantragEvent> kreditvertragAbgelehnt (){
        return System.out::println;
    }


}
