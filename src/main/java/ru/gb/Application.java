package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.model.Employee;
import ru.gb.model.Project;
import ru.gb.model.Timesheet;
import ru.gb.repository.EmployeeRepository;
import ru.gb.repository.ProjectRepository;
import ru.gb.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);
		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setName("Project #" + i);
			projectRepo.save(project);
		}

		EmployeeRepository employeeRepo = ctx.getBean(EmployeeRepository.class);
		for (int i = 1; i <= 5; i++) {
			Employee employee = new Employee();
			employee.setName("Employee #" + i);
			employeeRepo.save(employee);
		}

		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}
	}
}
