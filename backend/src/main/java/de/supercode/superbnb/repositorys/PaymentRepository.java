package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.entities.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
