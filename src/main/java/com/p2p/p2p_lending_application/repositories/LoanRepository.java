package com.p2p.p2p_lending_application.repositories;

import com.p2p.p2p_lending_application.models.User;
import com.p2p.p2p_lending_application.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByBorrowerAndApprovedIsFalse(User borrower);
    List<Loan> findAllByBorrower(User borrower);
    List<Loan> findAllByLenderIsNull();
    List<Loan> findAllByLenderIsNotNull();
    List<Loan> findAllByLender(User borrower);
    Optional<Loan> findByIdAndApprovedIsFalse(Long loanId);
}
