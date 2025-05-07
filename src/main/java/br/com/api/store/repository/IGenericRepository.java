package br.com.api.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

@NoRepositoryBean
public interface IGenericRepository<T, ID> extends JpaRepository<T, ID>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM #{#entityName} e where e.id = :id")
	int deleteByIdWithReturn(@Param("id") ID id);

}
