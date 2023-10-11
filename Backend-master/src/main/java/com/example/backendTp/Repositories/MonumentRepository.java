package com.example.backendTp.Repositories;

import com.example.backendTp.Beans.Monument;
import com.example.backendTp.Beans.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonumentRepository extends JpaRepository<Monument,Integer> {


}
