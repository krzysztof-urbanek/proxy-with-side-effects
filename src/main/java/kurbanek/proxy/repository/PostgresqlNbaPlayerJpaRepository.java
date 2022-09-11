package kurbanek.proxy.repository;

import kurbanek.proxy.model.Number;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlNbaPlayerJpaRepository extends ReactiveCrudRepository<Number, Long> {}