package com.example.idus_exam.order;

import com.example.idus_exam.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByUserIdx(Long idx, Pageable pageable);
    @EntityGraph(attributePaths = {"user"})
    Optional<Orders> findTop1ByUserIdxOrderByOrderDateDesc(Long userIdx);
}
