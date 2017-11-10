package catfish.data.metadata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import catfish.data.metadata.domain.NominalType;

public interface NominalTypeRepository extends PagingAndSortingRepository<NominalType, Long>{

}
