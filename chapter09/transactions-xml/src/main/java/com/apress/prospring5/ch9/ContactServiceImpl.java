package com.apress.prospring5.ch9;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contactService")
@Repository
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    /**
     * API  changed in  2.0.0.M3 findOne became findById
     * @param id
     * @return
     */
    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public long countAll() {
        return contactRepository.countAllContacts();
    }

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}
