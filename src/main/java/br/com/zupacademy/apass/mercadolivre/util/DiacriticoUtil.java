package br.com.zupacademy.apass.mercadolivre.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class DiacriticoUtil {

    public static String removeDiacriticos(String stringComDiacriticos) {
        String nfdNormalizedString = Normalizer.normalize(stringComDiacriticos, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
