package com.post.post;

import com.post.post.entity.User;
import com.post.post.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class PostApplication implements CommandLineRunner{
	UserRepo userRepo;
	@Autowired
	public void setUserRepo(UserRepo userRepo){
		this.userRepo = userRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(PostApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setUsername("admin");
//		user.setPassword("100");
//		userRepo.save(user);
	}
}
