package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.entities.Guardian;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.repositories.GuardianRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }



    public Guardian addGuardian(Guardian guardian, Student student) {
        if (guardian == null) {
            throw new IllegalArgumentException("Guardian cannot be null");
        }
        guardian.setActive(true);
        guardian.setCreatedDate(new Date());
        guardian.setStudent(student);
        return guardianRepository.save(guardian);
    }



    public List<Guardian> getAllGuardians() {
        List<Guardian> guardians = guardianRepository.findAll();
        List<Guardian> activeGuardians = new ArrayList<>();
        for (Guardian guardian : guardians) {
            if (guardian.isActive()) {
                activeGuardians.add(guardian);
            }
        }
        return activeGuardians;
    }



    public Guardian getById(Long id) {
        Optional<Guardian> guardian = guardianRepository.findById(id);
        if (guardian.isPresent() && guardian.get().isActive()) {
            return guardian.get();
        }
        return null;
    }



    public Guardian updateGuardian(Long id, String name, String relationship,
                                   String phoneNumber, Student student) {
        Guardian guardianToUpdate = getById(id);
        if (guardianToUpdate == null) {
            return null;
        }
        guardianToUpdate.setName(name);
        guardianToUpdate.setRelationship(relationship);
        guardianToUpdate.setPhoneNumber(phoneNumber);
        guardianToUpdate.setStudent(student);
        guardianToUpdate.setUpdatedDate(new Date());
        return guardianRepository.save(guardianToUpdate);
    }


























}
