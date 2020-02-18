# Errata for *Pro Spring 5*

On **page 368** [technical accuracy]:
 
The paragraph has two errors "The getter method of the attribute contactTelDetails is annotated with @OneToMany, which indicates the one-to-many relationship with the Album class. Several attributes are passed to the annotation. The mappedBy attribute indicates the property in the Album class that provides the association (that is, linked up by the foreign-key definition in the FK_ALBUM_SINGER table). The cascade attribute means that the update operation should “cascade” to the child. The orphanRemoval attribute means that after the albums have been updated, those entries that no longer exist in the set should be deleted from the database. The following code snippet shows the updated code in the Album class for the association mapping:"

***
