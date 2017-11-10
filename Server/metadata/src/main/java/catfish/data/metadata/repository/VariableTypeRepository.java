package catfish.data.metadata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import catfish.data.metadata.domain.VariableType;

@Transactional
public interface VariableTypeRepository extends PagingAndSortingRepository<VariableType, Long>{
    VariableType findByName(String name);
}
