
package com.gestionCinema.commun;


public class Patterns {

    /*
     * @Pattern regexp
     */
    public static final String PATTERN_SCRIPT = "^|(^(\\w|é|ç|à|â|û|ï|è|ù|,|;|:|\\(|\\)|\\.|\\*|'|’|\")((?!<script>|script>|</script|<|>).)*$)";
    public static final String PATTERN_PASSWORD = "^((?!admin|sonelgaz|elit).)*$";
    public static final String PATTERN_EMAIL = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})|$";
    public static final String PATTERN_IP = "^(25[0–5]|2[0–4][0–9]|[01]?[0–9][0–9]?).(25[0–5]|2[0–4][0–9]|[01]?[0–9][0–9]?).(25[0–5]|2[0–4][0–9]|[01]?[0–9][0–9]?).(25[0–5]|2[0–4][0–9]|[01]?[0–9][0–9]?)$";
    public static final String PATTERN_COLOR = "^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    public static final String TELEPHONE = "^|(0[0-9]{2}[ ][0-9]{2}[ ][0-9]{2}[ ][0-9]{2})";
    public static final String ALPHANUM_PATTERN = "^|(^(\\w|\\s|é|ç|à|â|û|ï|è|ù|,|;|:|\\(|\\)|\\.|\\*|'|’|\")(\\w|\\+|\\s|é|ç|à|â|û|ï|è|ù|,|;|:|-|\\(|\\)|\\.|\\*|/|'|’|\")*$)";

    /*
     * @Pattern messages
     */
    public static final String MSG_VERIFICATION_SCRIPT = "Vous ne pouvez pas utiliser les scripts, les espaces et les caractères spéciaux au début de ce champ";
    public static final String MSG_VERIFICATION_PASSWORD = "Vous ne pouvez pas utiliser les mots : 'admin, sonelgaz,elit' dans le mot de passe";
    public static final String MSG_VERIFICATION_EMAIL = "L'adresse mail n'est pas valide";
    public static final String MSG_VERIFICATION_COULEUR = "Couleur non valide";
}
