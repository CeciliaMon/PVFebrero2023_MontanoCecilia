package ar.edu.unju.escmi.pv;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PvFebrero2023MontanoCeciliaApplication {
 //implements CommandLineRunner
	/*@Autowired 
	private BCryptPasswordEncoder passwordEncoder;*/
	public static void main(String[] args) {
		SpringApplication.run(PvFebrero2023MontanoCeciliaApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String password = "272281";
		for(int i=0; i<2; i++) {
			String bCryptPassword = passwordEncoder.encode(password);
			System.out.println(bCryptPassword);
		}
	}*/
	
	

}
