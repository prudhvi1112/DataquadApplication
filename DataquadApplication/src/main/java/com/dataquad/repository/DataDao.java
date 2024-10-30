package com.dataquad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataquad.model.DataModel;

@Repository
public interface DataDao extends JpaRepository<DataModel, String>
{

}
