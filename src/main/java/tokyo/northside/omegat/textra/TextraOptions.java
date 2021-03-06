package tokyo.northside.omegat.textra;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Data class for TexTra configuration Options.
 * Also have combination limitation knowledge.
 * @author Hiroshi Miura
 */
public class TextraOptions {
    private String username;
    private String apikey;
    private String secret;
    private Mode mode;
    private String sourceLang;
    private String targetLang;

    private Set<Combination> combination = new HashSet<>();

    {
        combination.addAll(createCombinations(
                Arrays.asList(Mode.generalN, Mode.patentN, Mode.patent_claimN),
                Arrays.asList("ja", "en", "zh-CN", "zh-TW")));
        combination.addAll(createCombinations(
                Arrays.asList(Mode.generalN, Mode.patentN, Mode.patent_claimN),
                Arrays.asList("ko", "ja")));
        combination.addAll(createCombinations(Mode.generalN, "en",
                Arrays.asList("fr", "pt", "fr", "id", "my", "th", "vi", "es")));
    }

    /**
     * utility function to create Set of combination from set of String.
     */
    private Set<Combination> createCombinations(final List<Mode> modes,
                                             final List<String> languages) {
        Set<Combination> outSet = new HashSet<>();
        for (String source: languages) {
            for (String target: languages) {
                if (source.startsWith("zh") && target.startsWith("zh")) {
                    continue;
                } else if (source.equals(target)) {
                    continue;
                }
                for (Mode m: modes) {
                    outSet.add(new Combination(m, source, target));
                }
            }
        }
        return outSet;
    }
    private Set<Combination> createCombinations(final Mode m,
                                             final String source,
                                             final List<String> languages) {
        Set<Combination> outSet = new HashSet<>();
        for (String target: languages) {
            if (source.equals(target)) {
                continue;
            }
            outSet.add(new Combination(m, source, target));
            outSet.add(new Combination(m, target, source));
        }
        return outSet;
    }

    /**
     * Class for check mode, language combination.
     */
    private class Combination {
        private Mode mode;
        private String sLang;
        private String tLang;

        /**
         * Constructor.
         * @param mode mode.
         * @param sLang source language.
         * @param tLang target language.
         */
        Combination(final Mode mode, final String sLang, final String tLang) {
            this.mode = mode;
            this.sLang = sLang;
            this.tLang = tLang;
        }

        /**
         * Override equals.
         * @param o other object.
         * @return true if three values are equals.
         */
        @Override
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Combination)) {
                return false;
            }
            Combination other = (Combination) o;
            return other.mode.equals(this.mode) && other.sLang.toLowerCase().equals(this.sLang
                    .toLowerCase()) && other.tLang.toLowerCase().equals(this.tLang.toLowerCase());
        }

        /**
         * Return hashCode based on triplet strings.
         * @return hash code.
         */
        @Override
        public int hashCode() {
            return (mode.name() + sLang.toLowerCase() + tLang.toLowerCase()).hashCode();
        }
    }

    /**
     * Check if parameter combination is valid or not.
     * @return true is combination is valid, otherwise false.
     */
    public boolean isCombinationValid() {
        return combination.contains(new Combination(mode, sourceLang, targetLang));
    }

    /**
     * Translation mode.
     * These name is as same as an part of API URL.
     */
    public enum Mode {
        /** generic translation.
         * English/Japanese/Chinese/Korean
         */
        generalN,
        /** address translation.
         * Japanese/English
         */
        patentN,
        /** JPO claim translation.
         * Chinese/Japanese
         */
        patent_claimN,
    }

    /**
     * Getter of username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter of username.
     * @param username to set.
     * @return this object.
     */
    @SuppressWarnings("HiddenField")
    public TextraOptions setUsername(final String username) {
        this.username = username;
        return this;
    }

    /**
     * Getter of ApiKey.
     * @return apikey.
     */
    public String getApikey() {
        return apikey;
    }

    /**
     * Setter of Apikey.
     * @param apiKey to set.
     * @return this object.
     */
    @SuppressWarnings("HiddenField")
    public TextraOptions setApikey(final String apiKey) {
        this.apikey = apiKey;
        return this;
    }

    /**
     * Getter of secret.
     * @return secret.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Setter of secret.
     * @param secret to set.
     * @return this object.
     */
    @SuppressWarnings("HiddenField")
    public TextraOptions setSecret(final String secret) {
        this.secret = secret;
        return this;
    }

    /**
     * Getter of mode.
     * @return TranslateMode enum value.
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Setter of mode.
     * @param mode TranslateMode.
     * @return this object.
     */
    @SuppressWarnings("HiddenField")
    public TextraOptions setMode(final Mode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Get mode name.
     * @return API mode string for target URL.
     */
    public String getModeName() {
        return mode.name();
    }

    /**
     * Setter of mode by String.
     * @param name String.
     * @return this object.
     */
    public TextraOptions setMode(final String name) {
        if (name != null) {
            mode = Enum.valueOf(Mode.class, name);
        }
        return this;
    }

    /**
     * Ask mode.
     * @param name Mode name.
     * @return true if mode name equals.
     */
    public boolean isMode(final String name) {
        return mode.name().equals(name);
    }

    /**
     * Set language options.
     * <p>
     *     Format language string as like "zh-CN", "en", "ja"
     *     to be a proper case.
     *     Because OmegaT may give language in capital letter such as "EN".
     * </p>
     * @param sLang source language.
     * @param tLang target language.
     * @return this object.
     */
    public TextraOptions setLang(final String sLang, final String tLang) {
        this.sourceLang = formatLang(sLang);
        this.targetLang = formatLang(tLang);
        return this;
    }

    private String formatLang(final String lang) {
        String result;
        int index = lang.indexOf("-");
        if (index != -1) {
            result = lang.substring(0, index).toLowerCase()
                    + lang.substring(index).toUpperCase();
        } else {
            result = lang.toLowerCase();
        }
        return result;
    }

    /**
     * Get source language string.
     * @return source language.
     */
    String getSourceLang() {
        return sourceLang;
    }

    /**
     * Get target language string.
     * @return target language.
     */
    String getTargetLang() {
        return targetLang;
    }
}
