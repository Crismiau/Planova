package com.Planova.PlanovaCode.services;

import com.Planova.PlanovaCode.dto.VanueDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VanueService {
    private static final List<VanueDTO> vanues = new ArrayList<>();
    private static long nextId = 1;

    public void vanueTest(){

        VanueDTO stadio = new VanueDTO();
        stadio.setId(nextId++);
        stadio.setName("principal Stadium");
        stadio.setDirection("cALLE 16 #104-43");
        vanues.add(stadio);

        VanueDTO riwi = new VanueDTO();
        riwi.setId(nextId++);
        riwi.setName("Riwi S.A.S");
        riwi.setDirection("CALLE 23 #24-32");
        vanues.add(riwi);

    }

    public VanueDTO findByName(String name){
        return vanues.stream().filter(vanue -> vanue.getName().equalsIgnoreCase(name)).findFirst().orElse(null);

    }

}
