package cz.upce.fei.muller.trie.manager;

/**
 * @author Vojtěch Müller
 */
public final class TrieUtils {

    /* ASCII CODES*/
    public static final int LOWER_CASE_BEGIN = 97;
    public static final int LOWER_CASE_END = 123;
    public static final int UPPER_CASE_BEGIN = 65;
    public static final int UPPER_CASE_END = 91;

    public static Character[] getLowerCaseCharacters(){
        return buildCharacter(LOWER_CASE_BEGIN,LOWER_CASE_END);
    }

    public static Character[] getUpperCaseCharacters(){
        return buildCharacter(UPPER_CASE_BEGIN,UPPER_CASE_END);
    }

    public static int getCharacterPositionAtArray(Character character){
        int charValue = character.charValue();
        if(character>=LOWER_CASE_BEGIN&&charValue<LOWER_CASE_END){
            return charValue-LOWER_CASE_BEGIN;
        }else if(character>=UPPER_CASE_BEGIN&&charValue<UPPER_CASE_END){
            return charValue-UPPER_CASE_BEGIN;
        }else{
            throw new IllegalArgumentException("Nepovolené rozmezí!!");
        }
    }

    private static Character[] buildCharacter(int from,int to){
        Character[] characters = new Character[to-from];
        int j = 0;
        for (int i = from; i < to; i++) {
            characters[j]=new Character((char)i);
            j++;
        }
        return characters;
    }

}
