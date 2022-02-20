package fr.uga.l3.miage.astrocatalog.host.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostSystemRepository extends JpaRepository<HostSystem, Long> {

    List<HostSystem> findAllByNumberOfStar(int numberOfStar);

}


