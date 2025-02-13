package ECService.demo.repository;

import ECService.demo.entity.Apply;
import ECService.demo.repository.mapping.ListInfoMapping;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplyRepository extends CrudRepository<Apply, Long> {
    Optional<Apply> findAllBy();
    Optional<Apply> findByPhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE Apply ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    @Query("SELECT COUNT(a) > 0 FROM  Apply a")
    boolean isExist();
}
