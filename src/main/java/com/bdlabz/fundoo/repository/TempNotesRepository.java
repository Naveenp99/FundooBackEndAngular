package com.bdlabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdlabz.fundoo.entitymodel.TempNotes;

public interface TempNotesRepository extends JpaRepository<TempNotes, Long> {

}
