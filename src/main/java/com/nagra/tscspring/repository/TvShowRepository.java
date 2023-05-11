package com.nagra.tscspring.repository;

import com.nagra.tscspring.entity.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {
}