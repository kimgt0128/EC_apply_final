package ECService.demo.repository;

import ECService.demo.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Optional<Apply> findById(Long applyId);

    //@Query("SELECT a FROM Apply a WHERE a.phoneNumber = :phoneNumber OR HEX(a.phoneNumber) = HEX(:phoneNumber)")
    //@Query(value = "SELECT LENGTH(a.phone_number), HEX(a.phone_number) FROM apply a", nativeQuery = true)
    Optional<Apply> findByPhoneNumber(String phoneNumber);

}
