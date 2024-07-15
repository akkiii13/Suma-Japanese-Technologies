package sjtxev.payments.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sjtxev.payments.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	// find list of payments by customer
	List<Payment> findByCustomerId(Long customerId);

	// get single payment by payment id and customer id
	Payment findByPaymentIdAndCustomerId(Long paymentId, Long customerId);

	// exists single payment by payment id and customer id
	Boolean existsByPaymentIdAndCustomerId(Long paymentId, Long customerId);

	@Query(value = "SELECT MAX(paymentId) FROM payment WHERE customerId = :customerId", nativeQuery = true)
	Long findMaxPaymentIdByCustomerId(@Param("customerId") Long customerId);

}
