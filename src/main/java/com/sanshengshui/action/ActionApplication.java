package com.sanshengshui.action;


import com.sanshengshui.action.common.bean.CustomRepositoryFactoryBean;
import com.sanshengshui.action.dao.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableJpaRepositories(repositoryFactoryBeanClass =
		CustomRepositoryFactoryBean.class)
@SpringBootApplication
public class ActionApplication {
	@Autowired
	PeopleRepository peopleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ActionApplication.class, args);
	}
}
