package ru.bugmakers.utils;

import com.fasterxml.uuid.Generators;

public class UuidGenerator {
    public static String timeBasedUuidGenerate() {
        return Generators.timeBasedGenerator().generate().toString().replaceAll("-", "");
    }
}
