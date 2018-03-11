package ru.bugmakers.utils.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Ivan
 */
public class EmailTextBuilder {

    private static final String TEXT_TEMPLATE;
    private static final String NAME = "%NAME%";
    private static final String LAST_NAME = "%LASTNAME%";
    private static final String BODY = "%BODY%";

    static {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("email.html")).getFile()))) {
            br.lines().forEach(sb::append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TEXT_TEMPLATE = sb.toString();
    }

    public static String build(String name, String lastName, String body) {
        return TEXT_TEMPLATE
                .replaceAll(NAME, name)
                .replaceAll(LAST_NAME, lastName)
                .replace(BODY, body);
    }

    public static String confirmBuild(String name, String lastName, String link) {
        String service = "<a href=\"https://www.musboom.ru/\">MusicBoom</a>";
        String confirmLink = String.format("<a href=\"%s\">ссылке</a>", link);
        String body = String.format("Вы зарегестрировались в сервисе %s, для подтвверждения своего аккаунта перейдите " +
                "по %s. Если вы не знаете о чем речь, просто проигнорируйте это сообщение.", service, confirmLink);
        return build(name, lastName, body);
    }

}
