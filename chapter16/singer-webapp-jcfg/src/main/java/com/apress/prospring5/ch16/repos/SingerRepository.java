package com.apress.prospring5.ch16.repos;

		import com.apress.prospring5.ch16.entities.Singer;
		import org.springframework.data.repository.PagingAndSortingRepository;

public interface SingerRepository extends PagingAndSortingRepository<Singer, Long> {
}
