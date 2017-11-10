package catfish.data.metadata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import catfish.data.metadata.domain.VariableGroup;

public interface VariableGroupRepository extends PagingAndSortingRepository<VariableGroup, Long>{

}
