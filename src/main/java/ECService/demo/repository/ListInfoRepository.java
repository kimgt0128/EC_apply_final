package ECService.demo.repository;

import ECService.demo.entity.Apply;
import ECService.demo.repository.mapping.ListInfoMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListInfoRepository extends JpaRepository<Apply, Long> {
    List<ListInfoMapping> findAllBy();
}
