package com.apress.prospring4.ch7;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
    private static final Log LOG = LogFactory.getLog(ContactDaoImpl.class);

    private SessionFactory sessionFactory;

    @Transactional(readOnly=true)
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
    }

    @Transactional(readOnly=true)
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().
            getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Transactional(readOnly=true)
    public Contact findById(Long id) {
        return (Contact) sessionFactory.getCurrentSession().
            getNamedQuery("Contact.findById").
            setParameter("id", id).uniqueResult();
    }

    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        LOG.info("Contact saved with id: " + contact.getId());
        return contact;
    }

    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        LOG.info("Contact deleted with id: " + contact.getId());   
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
