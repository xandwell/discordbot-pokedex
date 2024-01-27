package snaive.pokedex;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.util.EventListener;

public class Main {
    public static void main(String[] args) {

        JDA bot = JDABuilder.createDefault("MTIwMDMxMjMwMTE0ODk3OTI1MA.G0azda.XXjMOhpN1XCIOM7FSpXcZZIgZ4osC6P8nhf-_E")
                .setActivity(Activity.watching("your mom"))
                .addEventListeners()
                .build();

    }

}