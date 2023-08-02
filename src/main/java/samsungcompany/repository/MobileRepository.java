package samsungcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samsungcompany.model.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Long> {
}
