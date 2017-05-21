package edu.cpp.hci.web.db;

import edu.cpp.hci.web.db.impl.RMPProfessorDBImpl;
import edu.cpp.hci.web.db.impl.RMPRatingDBImpl;
import edu.cpp.hci.web.db.impl.RMPTagDBImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public RMPTagDB tagDB() {
        return new RMPTagDBImpl();
    }

    @Bean
    public RMPRatingDB ratingDB() {
        return new RMPRatingDBImpl();
    }

    @Bean
    public RMPProfessorDB professorDB() {
        return new RMPProfessorDBImpl();
    }
}
