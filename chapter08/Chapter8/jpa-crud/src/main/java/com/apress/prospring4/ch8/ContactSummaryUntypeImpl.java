package com.apress.prospring4.ch8;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.Iterator;

@Service("contactSummaryUntype")
@Repository
@Transactional
public class ContactSummaryUntypeImpl {
     @PersistenceContext
     private EntityManager em;

     @Transactional(readOnly=true)
     public void displayAllContactSummary() {
        List result = em
                .createQuery("select c.firstName, c.lastName, t.telNumber "
                    + "from Contact c left join c.contactTelDetails t "
                    + " where t.telType='Home'").getResultList();
        int count = 0;

        for (Iterator i = result.iterator(); i.hasNext();) {
            Object[] values = (Object[]) i.next();

            System.out.println(++count + ": " + values[0] + ", " 
               + values[1] + ", " + values[2]);
        }
    }
}
