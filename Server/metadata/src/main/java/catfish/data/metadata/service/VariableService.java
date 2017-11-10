package catfish.data.metadata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import catfish.data.metadata.repository.VariableRepository;
import catfish.data.metadata.repository.VariableTypeRepository;

@Component
public class VariableService {

    @Autowired
    private VariableRepository variableRepository;
    
    @Autowired
    private VariableTypeRepository variableTypeRepository;

    public VariableRepository getVariableRepository() {
        return variableRepository;
    }

    public void setVariableRepository(VariableRepository variableRepository) {
        this.variableRepository = variableRepository;
    }

    public VariableTypeRepository getVariableTypeRepository() {
        return variableTypeRepository;
    }

    public void setVariableTypeRepository(
            VariableTypeRepository variableTypeRepository) {
        this.variableTypeRepository = variableTypeRepository;
    }
    
}
