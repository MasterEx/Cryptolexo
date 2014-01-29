package Cryptolexo;

/**
 *
 * @author Periklis Ntanasis <pntanasis@gmail.com>
 */
public class WordList {

    private static final String[] list = {
        "HELLO",
        "HISTORY",
        "MUSIC",
        "PARTY",
        "CAKE",
        "BIRD",
        "COUNTRY",
        "ACTION",
        "WOLF",
        "SPIDER",
        "VENOM",
        "GAME",
        "ICE",
        "SATISFACTION",
        "MUG",
        "ELEPHANT",
        "LION",
        "SCHOOL",
        "ANT",
        "ACTRESSES",
        "AUREOLES",
        "MULTILEVEL",
        "ALLERGIST",
        "SUGAR",
        "UNFRAMED",
        "OVERESTIMATING",
        "ENORMITIES",
        "MIRE",
        "BUSIES",
        "STREWTH",
        "TIDED",
        "IMMERSION",
        "TANGIER",
        "LAGGARD",
        "MOLESTED",
        "BALLS",
        "XVII",
        "SPORTSCAST",
        "SHUTTERS",
        "OVERCAPITALIZED",
        "UNDETECTABLE",
        "LIKELINESS",
        "TABLING",
        "CANDY",
        "COINCIDENTALLY",
        "PAPERGIRLS",
        "DIFFERENTIATING",
        "WITH",
        "SEEDERS",
        "CLAPBOARDING",
        "LUMBERED",
        "HUMPHED",
        "NETWORK",
        "HOLISTICALLY",
        "UNBUTTON",
        "VIEWING",
        "SPILLAGES",
        "PARTAKING",
        "OPENING",
        "EJECTIONS",
        "STABLEMATE",
        "GERIATRICIAN",
        "COMPETING",
        "PROPELLANT",
        "SOAPSTONE",
        "QUELLS",
        "DISINHERITANCE",
        "STERILIZE",
        "INITIATE",
        "WASHSTANDS",
        "HANDIER",
        "GYMNASTS",
        "GENTEELLY",
        "HARANGUED",
        "OVERTAXED",
        "DISEMBOWELLING",
        "STARTLES",
        "DIRECTORY",
        "PARAPET",
        "DECLARE",
        "HARASSMENT",
        "HUBCAPS",
        "SNORKELED",
        "IMPULSIVE",
        "NIGHTMARES",
        "ABSTENTION",
        "CONJECTURAL",
        "ALUMINA",
        "PHYSICS",
        "INITIATIVE",
        "CONCAVENESS",
        "HAZER",
        "DOG",
        "CLOP",
        "SITUATE",
        "PASTES",
        "ORIGINATE",
        "OSTENSIBLY",
        "METHOD",
        "POOLROOMS",
        "PLACARDS",
        "SENSUALITY",
        "WELSHER",
        "LIFTOFF",
        "STRIPTEASED",
        "COWHAND",
        "QUAFF",
        "SOUPS",
        "GARLANDED",
        "NAB",
        "PONDERER",
        "RICHEST",
        "REVOLUTIONARIES",
        "NIGHTIES",
        "COFFEECAKE",
        "BARRED",
        "PLUSHER",
        "GEWGAW",
        "TRANSLITERATE",
        "HORRIFICALLY",
        "CERVICAL",
        "PASSBOOKS",
        "LABOURING",
        "SENSITIZE",
        "DOCKER",
        "CRACKERS",
        "MALINGERING",
        "FOOTHILL",
        "ANXIOUSNESS",
        "ROCKINESS",
        "DESCALED",
        "EMPANELS",
        "HANDYMAN",
        "CHARGER",
        "AUTOBIOGRAPHY",
        "GLOWINGLY",
        "AFTERLIVES",
        "BLINDINGLY",
        "CORNMEAL",
        "AARDVARKS",
        "JOYLESSLY",
        "BPS",
        "DEPROGRAMMING",
        "LETTERER",
        "ASTUTER",
        "FLEECIER",
        "WORRYWART",
        "DEFINERS",
        "IRRIGABLE",
        "CONGRUITIES",
        "COUNTERARGUMENT",
        "BULLION",
        "POOP",
        "CHORTLING",
        "CROWFEET",
        "DAZES",
        "CHASTENESS",
        "EXCEPTIONAL",
        "CHASTENED",
        "WINDS",
        "MINUSCULES",
        "CONNIVANCE",};

    public static String[] getAllWords() {
        return list;
    }

    public static String[] getWords(int n) {
        String[] array = list.clone();
        shuffle(array, n);
        String[] retArr = new String[n];
        System.arraycopy(array, 0, retArr, 0, n);
        return retArr;
    }

    public static String[] getWords(int n, int length) {
        String[] array = list.clone();
        shuffle(array, n);
        String[] retArr = new String[n];
        int i = 0, c = 0;
        while (i < n && i < array.length) {
            if (array[i].length() <= length) {
                retArr[c] = array[i];
                c++;
            } else {
                n++;
            }
            i++;
        }
        return retArr;
    }

    private static void shuffle(String[] array, int n) {
        for (int i = array.length - 1; i > 0; i--) {
            String tmp = array[i];
            int random = Utils.random(i - 1);
            array[i] = array[random];
            array[random] = tmp;
        }
    }

}
