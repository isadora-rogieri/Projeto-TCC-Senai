package util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class  TrataStrings {

    public static LocalDate converteData(String data) throws ParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  LocalDate.parse(data, format);
    }

    public static String removePontuacao(String texto) {
        return texto.replaceAll("\\p{Punct}", "");
    }

    public static String criptografaSenha(String senha) {
        try {

            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");

            byte[] senhaConvertida = algoritmo.digest(senha.getBytes(StandardCharsets.UTF_8));

            StringBuilder senhaFinal = new StringBuilder();
            for (byte b : senhaConvertida) {
                senhaFinal.append(String.format("%02X", 0xFF & b));
            }
            return senhaFinal.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        return "";
    }

}
