package com.propane.libmanv1.identity.repository;

import com.propane.libmanv1.identity.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
