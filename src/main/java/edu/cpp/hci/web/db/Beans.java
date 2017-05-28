package edu.cpp.hci.web.db;

import edu.cpp.hci.web.db.koofers.KoofersProfessorDB;
import edu.cpp.hci.web.db.koofers.KoofersRatingDB;
import edu.cpp.hci.web.db.koofers.impl.KoofersProfessorDBImpl;
import edu.cpp.hci.web.db.koofers.impl.KoofersRatingDBImpl;
import edu.cpp.hci.web.db.rmp.RMPProfessorDB;
import edu.cpp.hci.web.db.rmp.RMPRatingDB;
import edu.cpp.hci.web.db.rmp.RMPTagDB;
import edu.cpp.hci.web.db.rmp.impl.RMPProfessorDBImpl;
import edu.cpp.hci.web.db.rmp.impl.RMPRatingDBImpl;
import edu.cpp.hci.web.db.rmp.impl.RMPTagDBImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public RMPTagDB rmpTagDB() {
        return new RMPTagDBImpl();
    }

    @Bean
    public RMPRatingDB rmpRatingDB() {
        return new RMPRatingDBImpl();
    }

    @Bean
    public RMPProfessorDB rmpProfessorDB() {
        return new RMPProfessorDBImpl();
    }

    @Bean
    public KoofersProfessorDB koofersProfessorDB() {
        return new KoofersProfessorDBImpl();
    }

    @Bean
    public KoofersRatingDB koofersRatingDB() {
        return new KoofersRatingDBImpl();
    }

}
