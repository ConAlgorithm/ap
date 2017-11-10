package catfish.data.metadata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import catfish.data.metadata.domain.NominalValue;

public interface NominalValueRepository extends PagingAndSortingRepository<NominalValue, Long>{

}
