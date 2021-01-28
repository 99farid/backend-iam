package com.lawencon.elearning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.lawencon.elearning.model.Mahasiswa;
import com.lawencon.elearning.model.Universitas;
import com.lawencon.elearning.service.MahasiswaService;

@SpringBootApplication
@ComponentScan(basePackages = "com.lawencon")
public class App  {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MahasiswaService mhs) {
		return args -> {
			Mahasiswa m = new Mahasiswa();
//			Mahasiswa m = mhs.findById("18c82164-b2b8-4102-8c32-071a73459a12");
			m.setNama("mhs");
//			m.setId("7c4673bf-518e-4198-a5b8-14890613755e");
//			m.setVersion(1L);   

			Universitas univ = new Universitas();
			univ.setId("1"); 
//			univ.setVersion(11L); 
			
//			System.out.println(univ.getVersion());
			m.setUniversitas(univ);

			mhs.save(m);

		};
	}
}
