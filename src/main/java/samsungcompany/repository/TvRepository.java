package samsungcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samsungcompany.model.Tv;

public interface TvRepository extends JpaRepository<Tv, Long> {
}
