package com.apress.prospring5.ch8.repos;

import com.apress.prospring5.ch8.entities.Album;
import com.apress.prospring5.ch8.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/7/17.
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
	List<Album> findBySinger(Singer singer);

	@Query("select a from Album a where a.title like %:title%")
	List<Album> findByTitle(@Param("title") String title);
}
