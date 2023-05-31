package ru.hogwarts.school.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.AvatarList;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {

    Optional<Avatar> findByStudent_Id(long id);

}
