package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
<<<<<<< HEAD
    Task findTaskById(Long taskId);
=======
>>>>>>> 4794e3347754cdf3015c1a1c8339b3196e413df2
}
