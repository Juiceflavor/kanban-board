package com.api.kanban_board;

import com.api.kanban_board.models.CustomExcpetion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class KanbanBoardApplication {

    public static void main(String[] args) throws CustomExcpetion {
        SpringApplication.run(KanbanBoardApplication.class, args);

        /*BoardModel boardModel = BoardModel.fromData(1L,null,"awd","Finalizado");
        System.out.println(boardModel);

        BoardModel boardModel2 = BoardModel.create("New board", "awd");
        System.out.println(boardModel2);*/
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("*").allowedHeaders("*");
            }
        };
    }
}