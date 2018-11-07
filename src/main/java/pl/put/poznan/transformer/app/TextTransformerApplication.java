package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.TextTransformer;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        TextTransformer t = new TextTransformer(new String[]{"kappa"});
        for (int i = -10; i < 10; i++) {
            System.out.println(t.transformNumbers(String.valueOf(i)));
        }
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
