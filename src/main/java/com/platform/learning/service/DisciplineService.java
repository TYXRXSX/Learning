package com.platform.learning.service;

import com.platform.learning.dao.Discipline;
import com.platform.learning.repo.DisciplineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepo disciplineRepo;

    public Discipline getDiscipline(Long id) {
        return disciplineRepo.findById(id).get();
    }

    public Iterable<Discipline> getDisciplines(){
        return disciplineRepo.findAll();
    }

    public void save(Discipline discipline) {
        disciplineRepo.save(discipline);
    }

    public void delete(Long id) {
        disciplineRepo.deleteById(id);
    }
}
