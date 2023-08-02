package samsungcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samsungcompany.model.Kitchen;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
}
