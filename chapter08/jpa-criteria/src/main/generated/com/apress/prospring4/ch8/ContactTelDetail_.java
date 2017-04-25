package com.apress.prospring4.ch8;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContactTelDetail.class)
public abstract class ContactTelDetail_ {

	public static volatile SingularAttribute<ContactTelDetail, String> telType;
	public static volatile SingularAttribute<ContactTelDetail, String> telNumber;
	public static volatile SingularAttribute<ContactTelDetail, Contact> contact;
	public static volatile SingularAttribute<ContactTelDetail, Long> id;
	public static volatile SingularAttribute<ContactTelDetail, Integer> version;

}

