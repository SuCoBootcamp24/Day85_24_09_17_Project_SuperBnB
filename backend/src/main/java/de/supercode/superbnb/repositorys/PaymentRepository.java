package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.entities.person.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
