package catfish.data.metadata.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import catfish.data.metadata.domain.Variable;

@Transactional
public interface VariableRepository extends PagingAndSortingRepository<Variable, Long>{
    Variable findByName(String name);
}
