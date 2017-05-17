package com.apress.prospring5.ch9.repos;

import com.apress.prospring5.ch9.entities.Album;
import com.apress.prospring5.ch9.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/7/17.
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
	List<Album> findBySinger(Singer singer);
}
