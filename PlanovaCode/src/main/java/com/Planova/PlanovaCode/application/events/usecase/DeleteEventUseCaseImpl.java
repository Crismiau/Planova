package com.Planova.PlanovaCode.application.events.usecase;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.in.DeleteEventUseCase;
import com.Planova.PlanovaCode.domain.ports.out.EventRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {
    private  final EventRepositoryPort eventRepositoryPort;

    @Override
    @Transactional
    public boolean delete(Long id){
     if(eventRepositoryPort.findById(id).isEmpty()){
         return false;
     }
    eventRepositoryPort.deleteById(id);
     return true;
    }


}
